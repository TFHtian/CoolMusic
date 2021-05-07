package com.tfh.coolmusic.ui.activity.main

import android.os.Bundle
import android.view.View
import androidx.lifecycle.observe
import com.tfh.coolmusic.R
import com.tfh.coolmusic.app.base.activity.BaseLoadSirActivity
import com.tfh.coolmusic.databinding.ActivityMainBinding
import com.tfh.coolmusic.ext.showEmpty
import com.tfh.coolmusic.ext.showLoading
import com.tfh.coolmusic.viewmodel.main.MainVm
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseLoadSirActivity<MainVm,ActivityMainBinding>() {

    override fun layoutId(): Int {
        return R.layout.activity_main
    }

    override fun loadSirView(): View {
        return cl_content
    }

    override fun initView(savedInstanceState: Bundle?) {

    }

    override fun createObserver() {
        mViewModel.isSuccess.observe(this){
            if (it){
                loadsir.showEmpty()
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