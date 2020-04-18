package com.mumbojumbo.safebox.viewholders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mumbojumbo.safebox.R
import kotlinx.android.synthetic.main.category_item.view.*

class CategoryViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
     val categoryName:TextView
    init {
        categoryName = itemView.findViewById<TextView>(R.id.tv_category)
    }
    fun updateHoldeItem(text:String?) {
        categoryName.text=text
    }
}