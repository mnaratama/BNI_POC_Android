package com.ibm.bni.auth.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.ibm.bni.auth.R
import com.ibm.bni.auth.data.remote.model.GreetingData
import com.ibm.bni.auth.data.remote.model.NewAndHotDealResult
import com.ibm.bni.auth.databinding.LayoutGreetingItemBinding
import com.smarteist.autoimageslider.SliderViewAdapter

class GreetingAdapter : SliderViewAdapter<GreetingAdapter.NewsAdapterVH>() {
    private var datas = arrayListOf<GreetingData>()

    fun setData(newDatas: ArrayList<GreetingData>) {
        datas.clear()
        datas = newDatas
        notifyDataSetChanged()
    }

    class NewsAdapterVH(private val root: LayoutGreetingItemBinding) : ViewHolder(root.root) {
        fun bindData(data: GreetingData) {
            with(root) {
                when(data.id){
                    "1"-> imgNews.setImageDrawable(ContextCompat.getDrawable(root.context, R.drawable.greeting_image_1))
                    "2"-> imgNews.setImageDrawable(ContextCompat.getDrawable(root.context, R.drawable.banner_security2))
                }
            }
        }
    }

    override fun getCount(): Int = datas.size

    override fun onCreateViewHolder(parent: ViewGroup): NewsAdapterVH {
        return NewsAdapterVH(
            LayoutGreetingItemBinding.inflate(
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