package com.tfh.coolmusic.ui.adapter.main

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.tfh.coolmusic.R
import com.tfh.coolmusic.databinding.ItemMianListBinding

class MainListAdapter : BaseQuickAdapter<String,BaseDataBindingHolder<ItemMianListBinding>>(R.layout.item_mian_list){

    override fun convert(holder: BaseDataBindingHolder<ItemMianListBinding>, bean: String) {
        with(holder.dataBinding!!){
            item = bean
            executePendingBindings()
        }
    }

}