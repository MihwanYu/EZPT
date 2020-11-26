package com.example.ezpt

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_recycler.view.*

class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.Holder>() {
    var listData = mutableListOf<user>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recycler, parent, false)
        return Holder(view)
    }
    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val user = listData.get(position)
        holder.setData(user)
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var inputData:user? = null
        init {
            itemView.contact_btn.setOnClickListener {
                val intent = Intent(itemView.context, ChatActivity::class.java)
                itemView.context.startActivity(intent)
            }
        }
        fun setData(data: user) {

            itemView.username_textview.text = data.username
            itemView.sex_textview.text = data.sex
            itemView.region_textview.text = data.region
            itemView.description_textview.text = data.description
            this.inputData =data
        }
    }
}