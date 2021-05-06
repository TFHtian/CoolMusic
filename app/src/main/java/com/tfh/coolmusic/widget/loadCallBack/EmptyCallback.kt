package com.tfh.coolmusic.widget.loadCallBack

import com.kingja.loadsir.callback.Callback
import com.tfh.coolmusic.R

class EmptyCallback : Callback() {

    override fun onCreateView(): Int {
        return R.layout.layout_empty
    }

}