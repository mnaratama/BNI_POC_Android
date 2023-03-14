package com.ibm.bni.home.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ibm.bni.home.data.remote.model.ListAccountResult
import com.ibm.bni.home.databinding.ItemDebitAccountBinding
import com.ibm.bni.home.presentation.toLocalString

class AdapterListAccount(val onclick:(data:ListAccountResult)->Unit) : RecyclerView.Adapter<AdapterListAccount.ViewHolder>() {

    private var datas = listOf<ListAccountResult>()

    fun setData(newDatas: List<ListAccountResult>) {
        datas = newDatas
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemDebitAccountBinding) : RecyclerView.ViewHolder(binding.root) {
        val rootView = binding.cardAccount
        fun bindData(data: ListAccountResult) {
            binding.productType.text = data.productType
            binding.currentBalance.text = "-IDR "+ data.currentBalance.toLocalString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemDebitAccountBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        datas[position].let {data->
            holder.bindData(data)
            holder.rootView.setOnClickListener {
                onclick.invoke(data)
            }
        }
    }


}