package com.tfh.coolmusic.app.base.activity

import android.content.res.Resources
import android.os.Bundle
import androidx.databinding.ViewDataBinding
import com.tfh.commonlibrary.base.activity.BaseVmDbActivity
import com.tfh.commonlibrary.base.viewmodel.BaseViewModel
import com.tfh.commonlibrary.util.LogUtils
import com.tfh.coolmusic.data.event.EventMessage
import com.tfh.coolmusic.ext.dismissLoadingExt
import com.tfh.coolmusic.ext.showLoadingExt
import com.tfh.coolmusic.util.EventBusUtils
import me.jessyan.autosize.AutoSizeCompat
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * @author tianfenghui
 * @date 2021/4/20.
 * @description 实现 databinding基类
 */
abstract class BaseActivity<VM : BaseViewModel, DB : ViewDataBinding> : BaseVmDbActivity<VM, DB>(){

    abstract override fun layoutId(): Int

    abstract override fun initView(savedInstanceState: Bundle?)

    override fun onCreate(savedInstanceState: Bundle?) {
        if (isRegisteredEventBus()) {
            EventBusUtils.register(this);
        }
        super.onCreate(savedInstanceState)
    }

    /**
     * 创建liveData观察者
     */
    override fun createObserver() {}

    /**
     * show加载loading
     */
    override fun showLoading(message: String) {
        showLoadingExt(message)
    }

    /**
     * hide加载loading
     */
    override fun dismissLoading() {
        dismissLoadingExt()
    }

    /**
     * 注册eventbus
     */
    open fun isRegisteredEventBus(): Boolean {
        return true
    }

    /**
     * 接收到分发的事件
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    open fun onReceiveEvent(event: EventMessage<*>?) {
    }

    /**
     * 接受到分发的粘性事件
     */
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    open fun onReceiveStickyEvent(event: EventMessage<*>?) {

    }

    override fun onDestroy() {
        super.onDestroy()
        if (isRegisteredEventBus()) {
            EventBusUtils.unregister(this);
        }
        mBinding.unbind()
    }

    override fun getResources(): Resources {
        //解决一些奇怪的适配问题
        //https://github.com/JessYanCoding/AndroidAutoSize/issues/13
        //平板 大多数界面是默认以宽为基准适配，如果适配手机，大多数界面需要以高为基准适配
        try {
            runOnUiThread {
                AutoSizeCompat.autoConvertDensityBaseOnWidth(super.getResources(), 667f)
            }
        } catch (e: Exception) {
            LogUtils.debugInfo("适配异常$e")
        }
        return super.getResources()
    }

    // 如果某个页面需要以高为基准适配，重写 getResource 需要返回这个，super上面的就会被覆盖
    // 执行系统的 Resources
    fun getSuperResources(): Resources {
        return super.getResources()
    }

}