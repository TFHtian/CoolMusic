package com.tfh.coolmusic.viewmodel.main

import androidx.lifecycle.MutableLiveData
import com.tfh.coolmusic.app.base.viewmodel.BaseRefreshViewModel
import com.tfh.coolmusic.data.entity.state.ListDataUiState
import kotlinx.coroutines.delay

/**
 * @author tianfenghui
 * @date 2021/5/4.
 * @description
 */
class MainVm : BaseRefreshViewModel(){

    var mainDataState: MutableLiveData<ListDataUiState<String>> = MutableLiveData()

    fun getMainData(){
        launchUI {
            delay(2000)
            val listDataUiState =
                ListDataUiState(
                    isSuccess = true,
                    isRefresh = isRefresh.value,
                    isEmpty = getData().isEmpty(),
                    isFirstEmpty = isRefresh.value && getData().isEmpty(),
                    listData = getData()
                )
            mainDataState.value = listDataUiState
        }
    }

    private fun getData(): ArrayList<String>{
        var list = ArrayList<String>()
        for (index in 0..10){
            list.add("测试$index")
        }
        return list
    }

    private fun loadData(){
        val moreList = arrayListOf<String>()
        launchUI {
            delay(2000)
            val listDataUiState =
                ListDataUiState(
                    isSuccess = true,
                    isRefresh = isRefresh.value,
                    isFirstEmpty = isRefresh.value && getData().isEmpty(),
                    listData = moreList
                )
            mainDataState.value = listDataUiState
        }
    }

    override fun refreshData() {
        getMainData()
    }

    override fun loadMoreData() {
        loadData()
    }

}