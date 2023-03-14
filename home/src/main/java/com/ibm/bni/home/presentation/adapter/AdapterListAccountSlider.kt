package com.ibm.bni.home.presentation.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.ibm.bni.home.R
import com.ibm.bni.home.data.local.model.QuickLinksModel
import com.ibm.bni.home.data.remote.model.ListAccountResult
import com.ibm.bni.home.presentation.toLocalString
import java.util.*
import kotlin.collections.ArrayList

class AdapterListAccountSlider(
    val context: Context,
    val onItemClickCallback2: ClickCallback,
) : RecyclerView.Adapter<AdapterListAccountSlider.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_slider, parent, false)
        )
    }

    private var list = listOf<ListAccountResult>()

    fun setListItem(itemList: List<ListAccountResult>) {
        this.list = itemList
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

        holder.currentBalance.text = "Rp "+ item.currentBalance.toLocalString()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val currentBalance: TextView = view.findViewById(R.id.currentBalance)
    }

    interface ClickCallback {
        fun onItemClicked(data: ListAccountResult)
    }
}