package com.tfh.coolmusic.app.network.api

import com.tfh.commonlibrary.network.BaseResponse

data class ApiResponse<T>(var result: Int, var code: Int, var message: String,var data: T) :
    BaseResponse<T>() {

    override fun isSuccess() = result == 1

    override fun getResponseCode() = code

    override fun getResponseData() = data

    override fun getResponseMsg() = message

}