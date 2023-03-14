package com.ibm.bni.auth.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.ibm.bni.auth.R
import com.ibm.bni.auth.data.remote.model.NewAndHotDealResult
import com.ibm.bni.auth.databinding.LayoutNewAndHotDealItemBinding
import com.smarteist.autoimageslider.SliderViewAdapter

class NewAndHotDealAdapter : SliderViewAdapter<NewAndHotDealAdapter.NewsAdapterVH>() {
    private var datas = arrayListOf<NewAndHotDealResult>()

    fun setData(newDatas: ArrayList<NewAndHotDealResult>) {
        datas.clear()
        datas = newDatas
        notifyDataSetChanged()
    }

    class NewsAdapterVH(private val root: LayoutNewAndHotDealItemBinding) : ViewHolder(root.root) {
        fun bindData(data: NewAndHotDealResult) {
            with(root) {
                messageNews.text = data.promoTitle
                when(data.idDrawable){
                    1->imgNews.setImageDrawable(ContextCompat.getDrawable(root.context,R.drawable.promo1))
                    2->imgNews.setImageDrawable(ContextCompat.getDrawable(root.context,R.drawable.promo2))
                    3->imgNews.setImageDrawable(ContextCompat.getDrawable(root.context,R.drawable.promo3))
                }
                if(data.point==null){
                    textPoint.visibility = View.GONE
                }else{
                    textPoint.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun getCount(): Int = datas.size

    override fun onCreateViewHolder(parent: ViewGroup): NewsAdapterVH {
        return NewsAdapterVH(
            LayoutNewAndHotDealItemBinding.inflate(
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
