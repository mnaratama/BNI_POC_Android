package com.ibm.bni.home.presentation.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ibm.bni.home.R
import com.ibm.bni.home.data.local.model.QuickLinksModel
import java.util.*
import kotlin.collections.ArrayList

class AdapterQuickLinks(
    val context: Context,
    val onItemClickCallback2: ClickCallback,
) : RecyclerView.Adapter<AdapterQuickLinks.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_quicklinks, parent, false)
        )
    }

    private var list = ArrayList<QuickLinksModel>()

    fun setListItem(itemList: ArrayList<QuickLinksModel>) {
        this.list = itemList
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

        holder.llQuicklinks.setOnClickListener {
            onItemClickCallback2.onItemClicked(item)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.title)
        val image: ImageView = view.findViewById(R.id.image)
        val llQuicklinks: LinearLayout = view.findViewById(R.id.llQuicklinks)
    }

    interface ClickCallback {
        fun onItemClicked(data: QuickLinksModel)
    }
}