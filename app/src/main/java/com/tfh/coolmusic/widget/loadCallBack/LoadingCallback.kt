package com.tfh.coolmusic.widget.loadCallBack

import android.content.Context
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import com.github.ybq.android.spinkit.style.Circle
import com.kingja.loadsir.callback.Callback
import com.tfh.coolmusic.R

class LoadingCallback : Callback() {

    private lateinit var loadingView: Circle

    override fun onCreateView(): Int {
        return R.layout.layout_loading
    }

    override fun onAttach(context: Context?, view: View?) {
        loadingView = Circle()
        loadingView.color = context?.resources?.getColor(R.color.theme_color)!!
        view?.findViewById<AppCompatImageView>(R.id.iv_loading)?.setImageDrawable(loadingView)
        loadingView.start()
    }

    override fun onDetach() {
        super.onDetach()
        loadingView.stop()
    }

}