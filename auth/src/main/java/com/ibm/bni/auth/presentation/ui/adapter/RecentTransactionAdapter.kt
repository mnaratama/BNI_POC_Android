package com.ibm.bni.auth.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ibm.bni.auth.data.remote.model.Transaction
import com.ibm.bni.auth.databinding.LayoutRecentTransactionItemBinding
import com.ibm.bni.auth.presentation.ui.toLocalString

class RecentTransactionAdapter : RecyclerView.Adapter<RecentTransactionAdapter.ViewHolder>() {

    private var datas = listOf<Transaction>()

    fun setData(newDatas: List<Transaction>) {
        datas = newDatas
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: LayoutRecentTransactionItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(data:Transaction) {
            binding.lblTransaction.text = data.transactionType
            binding.valueTransaction.text = "-IDR "+data.amount.toLocalString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutRecentTransactionItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        datas[position].let {
            holder.bindData(it)
        }
    }
}