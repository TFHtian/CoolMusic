package com.tfh.coolmusic.viewmodel.main

import androidx.lifecycle.MutableLiveData
import com.tfh.commonlibrary.base.viewmodel.BaseViewModel
import kotlinx.coroutines.delay

/**
 * @author tianfenghui
 * @date 2021/5/4.
 * @description
 */
class MainVm : BaseViewModel(){

    var isSuccess = MutableLiveData<Boolean>().apply {
        value = false
    }

    fun getMainData(){
        launchUI {
            delay(2000)
            isSuccess.value = true
        }
    }

}