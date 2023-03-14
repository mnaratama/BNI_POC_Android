package com.ibm.bni.home.presentation.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ibm.bni.home.R
import com.ibm.bni.home.data.remote.model.ListTransactionResult
import com.ibm.bni.home.presentation.toLocalString
import java.util.*

class AdapterListTransaction(
    val context: Context,
    val onItemClickCallback2: ClickCallback,
) : RecyclerView.Adapter<AdapterListTransaction.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_transaction, parent, false)
        )
    }

    private var list = listOf<ListTransactionResult>()

    fun setListItem(itemList: List<ListTransactionResult>) {
        this.list = itemList
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

        holder.transactionType.text = item.transactionType
        holder.amount.text = "-IDR "+ item.amount.toLocalString()
        holder.cardTransaction.setOnClickListener {
            onItemClickCallback2.onItemClicked(item)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val transactionType: TextView = view.findViewById(R.id.transactionType)
        val amount: TextView = view.findViewById(R.id.amount)
        val cardTransaction: LinearLayout = view.findViewById(R.id.card_transaction)
    }

    interface ClickCallback {
        fun onItemClicked(data: ListTransactionResult)
    }
}