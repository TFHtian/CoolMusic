package com.tfh.coolmusic.widget.loadCallBack

import com.kingja.loadsir.callback.Callback
import com.tfh.coolmusic.R

class ErrorCallback : Callback() {

    override fun onCreateView(): Int {
        return R.layout.layout_error
    }

}