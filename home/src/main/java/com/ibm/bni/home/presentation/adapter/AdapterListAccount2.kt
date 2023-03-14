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

class AdapterListAccount2(
    val context: Context,
    val onItemClickCallback2: ClickCallback,
) : RecyclerView.Adapter<AdapterListAccount2.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_debit_account, parent, false)
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

        holder.productType.text = item.productType
        holder.currentBalance.text = item.currentBalance.toLocalString()
        holder.cardview.setOnClickListener {
            onItemClickCallback2.onItemClicked(item)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val productType: TextView = view.findViewById(R.id.productType)
        val currentBalance: TextView = view.findViewById(R.id.currentBalance)
        val cardview: LinearLayout = view.findViewById(R.id.card_account)
    }

    interface ClickCallback {
        fun onItemClicked(data: ListAccountResult)
    }
}