package com.tfh.coolmusic.ui.activity.main

import android.os.Bundle
import androidx.lifecycle.observe
import com.kingja.loadsir.core.LoadService
import com.tfh.coolmusic.R
import com.tfh.coolmusic.app.base.activity.BaseActivity
import com.tfh.coolmusic.databinding.ActivityMainBinding
import com.tfh.coolmusic.ext.loadServiceInit
import com.tfh.coolmusic.ext.showEmpty
import com.tfh.coolmusic.ext.showLoading
import com.tfh.coolmusic.viewmodel.main.MainVm
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainVm,ActivityMainBinding>() {

    //界面状态管理者
    private lateinit var loadsir: LoadService<Any>

    override fun layoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView(savedInstanceState: Bundle?) {
        //状态页配置
        loadsir = loadServiceInit(cl_content) {
            //点击重试时触发的操作
            loadsir.showLoading()
            mViewModel.getMainData()
        }
        loadsir.showLoading()
    }

    override fun createObserver() {
        mViewModel.isSuccess.observe(this){
            if (it){
                loadsir.showEmpty()
            }
        }
    }

}