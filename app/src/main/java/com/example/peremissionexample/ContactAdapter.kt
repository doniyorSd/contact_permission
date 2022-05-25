package com.example.peremissionexample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.daimajia.swipe.SwipeLayout
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter
import com.daimajia.swipe.implments.SwipeItemRecyclerMangerImpl
import com.example.peremissionexample.databinding.ItemContactBinding


class ContactAdapter(var list: ArrayList<Contact>, var onClick: OnClick) :
    RecyclerSwipeAdapter<ContactAdapter.MyViewHolder>() {
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun onBind(contact: Contact, position: Int) {
            val bind = ItemContactBinding.bind(itemView)
            bind.tv1.text = contact.name
            bind.tv2.text = contact.phoneNumber
            bind.bottomWrapper.setOnClickListener {
                onClick.smsClick(contact, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        mItemManger.bindView(holder.itemView, position)
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getSwipeLayoutResourceId(position: Int): Int {
        return R.id.swipe_layout
    }

    interface OnClick {
        fun smsClick(contact: Contact, position: Int)
    }
}