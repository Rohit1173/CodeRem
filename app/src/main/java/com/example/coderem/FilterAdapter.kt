package com.example.coderem

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView

class FilterAdapter(private var list: MutableList<String>): RecyclerView.Adapter<FilterAdapter.ViewHolder>() {
    class ViewHolder(view: View):RecyclerView.ViewHolder(view) {
          val btn:Button=view.findViewById(R.id.btn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.filter, parent, false)

        return FilterAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item =list[position]
        holder.btn.text=item
    }

    override fun getItemCount(): Int {
        return list.size
    }
}