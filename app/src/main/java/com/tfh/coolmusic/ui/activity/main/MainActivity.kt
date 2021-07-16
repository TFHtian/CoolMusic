package com.tfh.coolmusic.ui.activity.main

import android.os.Bundle
import android.view.View
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.tfh.coolmusic.R
import com.tfh.coolmusic.app.base.activity.BaseRefreshActivity
import com.tfh.coolmusic.databinding.ActivityMainBinding
import com.tfh.coolmusic.ext.init
import com.tfh.coolmusic.ext.loadListData
import com.tfh.coolmusic.ext.showLoading
import com.tfh.coolmusic.ui.adapter.main.MainListAdapter
import com.tfh.coolmusic.viewmodel.main.MainVm
import com.wuhenzhizao.titlebar.widget.CommonTitleBar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseRefreshActivity<MainVm, ActivityMainBinding>() {

    private val mainAdapter: MainListAdapter by lazy { MainListAdapter() }

    override fun layoutId(): Int {
        return R.layout.activity_main
    }

    override fun getTitleBar(): CommonTitleBar? {
        return titlebar
    }

    override fun getRefreshLayout(): SmartRefreshLayout {
        return refresh_layout
    }

    override fun loadSirView(): View {
        return cl_content
    }

    override fun initView(savedInstanceState: Bundle?) {
        rv_main.init(LinearLayoutManager(this), mainAdapter)
    }

    override fun createObserver() {
        mViewModel.mainDataState.observe(this){
            if (mViewModel.isRefresh.value){
                loadsir.showSuccess()
            }
            loadListData(it, mainAdapter, loadsir,refresh_layout)
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