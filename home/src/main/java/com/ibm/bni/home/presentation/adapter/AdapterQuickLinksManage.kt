package com.ibm.bni.home.presentation.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ibm.bni.home.R
import com.ibm.bni.home.data.local.model.QuickLinksModel
import java.util.*
import kotlin.collections.ArrayList

class AdapterQuickLinksManage(
    val context: Context,
    val onItemClickCallback2: ClickCallback,
) : RecyclerView.Adapter<AdapterQuickLinksManage.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_quicklinks_manage, parent, false)
        )
    }

    private var list = ArrayList<QuickLinksModel>()

    fun setListItem(itemList: ArrayList<QuickLinksModel>) {
        val contentsQuickLinksManage = arrayListOf<QuickLinksModel>()
        itemList.forEach {
            if (it.status) {
                contentsQuickLinksManage.add(it)
            }
        }
        this.list = contentsQuickLinksManage
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

        holder.title.text = item.title
        when (item.id) {
            1 -> {
                holder.image.setImageResource(R.drawable.ic_item_quiclinks)
            }
            2 -> {
                holder.image.setImageResource(R.drawable.ic_quicklinks_2)
            }
            3 -> {
                holder.image.setImageResource(R.drawable.ic_quicklinks_3)
            }
            4 -> {
                holder.image.setImageResource(R.drawable.ic_quicklinks_4)
            }
            5 -> {
                holder.image.setImageResource(R.drawable.ic_quicklinks_5)
            }
            6 -> {
                holder.image.setImageResource(R.drawable.ic_quicklinks_6)
            }
            7 -> {
                holder.image.setImageResource(R.drawable.ic_quicklinks_7)
            }
            8 -> {
                holder.image.setImageResource(R.drawable.ic_quicklinks_8)
            }
            9 -> {
                holder.image.setImageResource(R.drawable.ic_quicklinks_9)
            }
            10 -> {
                holder.image.setImageResource(R.drawable.ic_quicklinks_10)
            }
        }

        if (item.status) {
            holder.imageUpdate.setImageResource(R.drawable.ic_close_quicklinks)
        } else {
            holder.imageUpdate.setImageResource(R.drawable.ic_add)
        }

        holder.rlClick.setOnClickListener {
            onItemClickCallback2.onItemClicked(item)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.title)
        val image: ImageView = view.findViewById(R.id.image)
        val imageUpdate: ImageView = view.findViewById(R.id.btn_update)
        val rlClick: RelativeLayout = view.findViewById(R.id.rlClick)
    }

    interface ClickCallback {
        fun onItemClicked(data: QuickLinksModel)
    }
}