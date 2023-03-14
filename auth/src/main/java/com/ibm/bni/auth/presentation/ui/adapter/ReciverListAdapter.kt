package com.ibm.bni.auth.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ibm.bni.auth.data.remote.model.ReceiverFields
import com.ibm.bni.auth.databinding.ItemBeneficiarySelectBinding

class ReciverListAdapter : RecyclerView.Adapter<ReciverListAdapter.ViewHolder>() {

    private var receivers = listOf<ReceiverFields>()
    var onItemClick: ((ReceiverFields) -> Unit)? = null

    fun setReceivers(receiverFields: List<ReceiverFields>) {
        receivers = receiverFields
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemBeneficiarySelectBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(receiver :ReceiverFields) {
            val name = receiver.receiverName
            binding.receiverName.text = receiver.receiverName
            binding.nameInitials.text = name.replace("^\\s*([a-zA-Z]).*\\s+([a-zA-Z])\\S+$", "$1$2")
            binding.receiverBank.text = receiver.receiverBankName +"-"+receiver.receiverAcNumber
            binding.receiverCountry.text = receiver.receiverCountryName+"-"+receiver.receiverCurrency
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemBeneficiarySelectBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = receivers.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        receivers[position].let {
            holder.bindData(it)
        }
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(receivers[position])
        }
    }
}