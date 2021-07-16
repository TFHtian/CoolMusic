package com.tfh.coolmusic.ui.activity.main

import android.os.Bundle
import android.view.View
import androidx.lifecycle.observe
import com.tfh.coolmusic.R
import com.tfh.coolmusic.app.base.activity.BaseTitleBarActivity
import com.tfh.coolmusic.databinding.ActivityMainBinding
import com.tfh.coolmusic.ext.showLoading
import com.tfh.coolmusic.viewmodel.main.MainVm
import com.wuhenzhizao.titlebar.widget.CommonTitleBar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseTitleBarActivity<MainVm,ActivityMainBinding>() {

    override fun layoutId(): Int {
        return R.layout.activity_main
    }

    override fun getTitleBar(): CommonTitleBar {
        return titlebar
    }

    override fun loadSirView(): View {
        return cl_content
    }

    override fun initView(savedInstanceState: Bundle?) {

    }

    override fun createObserver() {
        mViewModel.isSuccess.observe(this){
            if (it){
                loadsir.showSuccess()
            }
        }
    }

    override fun initData() {
        mViewModel.getMainData()
    }

    override fun onReLoad() {
        loadsir.showLoading()
        mViewModel.getMainData()
    }

}