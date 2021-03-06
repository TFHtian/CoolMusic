package com.tfh.coolmusic.app.base.fragment

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import com.tfh.commonlibrary.base.fragment.BaseVmDbFragment
import com.tfh.commonlibrary.base.viewmodel.BaseViewModel
import com.tfh.coolmusic.data.event.EventMessage
import com.tfh.coolmusic.ext.dismissLoadingExt
import com.tfh.coolmusic.ext.hideSoftKeyboard
import com.tfh.coolmusic.ext.showLoadingExt
import com.tfh.coolmusic.util.EventBusUtils
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * @author tianfenghui
 * @date 2021/4/20.
 * @description 实现databinding 基类fragment
 */
abstract class BaseFragment<VM : BaseViewModel, DB : ViewDataBinding> : BaseVmDbFragment<VM, DB>() {

    /**
     * 当前Fragment绑定的视图布局
     */
    abstract override fun layoutId(): Int


    abstract override fun initView(savedInstanceState: Bundle?)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (isRegisteredEventBus()) {
            EventBusUtils.register(this);
        }
        super.onViewCreated(view, savedInstanceState)
    }

    /**
     * 懒加载 只有当前fragment视图显示时才会触发该方法
     */
    override fun lazyLoadData() {}

    /**
     * 创建LiveData观察者 Fragment执行onViewCreated后触发
     */
    override fun createObserver() {}

    /**
     * Fragment执行onViewCreated后触发
     */
    override fun initData() {}

    /**
     * show加载框
     */
    override fun showLoading(message: String) {
        showLoadingExt(message)
    }

    /**
     * hide加载框
     */
    override fun dismissLoading() {
        dismissLoadingExt()
    }

    override fun onPause() {
        super.onPause()
        hideSoftKeyboard(activity)
    }

    /**
     * 延迟加载 防止 切换动画还没执行完毕时数据就已经加载好了，这时页面会有渲染卡顿  bug
     * 这里传入你想要延迟的时间，延迟时间可以设置比转场动画时间长一点 单位： 毫秒
     * 不传默认 300毫秒
     * @return Long
     */
    override fun lazyLoadTime(): Long {
        return 300
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

}