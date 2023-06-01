package com.test.strangerthings.adapter

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.RecyclerView
import com.test.strangerthings.R
import com.test.strangerthings.model.QuotesItem
import kotlinx.coroutines.NonDisposableHandle.parent

class MyAdapter : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    private var myList = emptyList<QuotesItem>()

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val quoteText: TextView = itemView.findViewById(R.id.quoteTextView)
        val authorText: TextView = itemView.findViewById(R.id.authorTextView)
        val copyImage: ImageView = itemView.findViewById(R.id.copyButton)
        val shareImage: ImageView = itemView.findViewById(R.id.copyButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.sample_quotes_layout, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return myList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.quoteText.text = myList[position].quote
        holder.authorText.text = myList[position].author
        holder.copyImage.setOnClickListener(View.OnClickListener {

            val quotes = myList[position].quote.toString()
            val author = myList[position].author.toString()

            Toast.makeText(holder.itemView.context, "${quotes + author}", Toast.LENGTH_LONG)
                .toString()
        })
    }

    fun setData(newList: List<QuotesItem>) {
        myList = newList
        notifyDataSetChanged()
    }
}