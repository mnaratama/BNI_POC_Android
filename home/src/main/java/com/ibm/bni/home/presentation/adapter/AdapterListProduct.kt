package com.ibm.bni.home.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.ibm.bni.home.databinding.ItemProductBinding
import com.smarteist.autoimageslider.SliderViewAdapter

class AdapterListProduct : SliderViewAdapter<AdapterListProduct.NewsAdapterVH>() {
    private var datas = arrayListOf<String>()

    fun setData(newDatas: ArrayList<String>) {
        datas.clear()
        datas = newDatas
        notifyDataSetChanged()
    }

    class NewsAdapterVH(private val root: ItemProductBinding) : ViewHolder(root.root) {
        fun bindData(data: String) {
            with(root) {
                title.text = data
            }
        }
    }

    override fun getCount(): Int = datas.size

    override fun onCreateViewHolder(parent: ViewGroup): NewsAdapterVH {
        return NewsAdapterVH(
            ItemProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(viewHolder: NewsAdapterVH, position: Int) {
        with(datas[position]) {
            viewHolder.bindData(this)
        }
    }
}
