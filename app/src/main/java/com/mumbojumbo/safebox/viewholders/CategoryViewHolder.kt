package com.mumbojumbo.safebox.viewholders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mumbojumbo.safebox.R
import com.mumbojumbo.safebox.adapters.CategoryAdapter
import com.mumbojumbo.safebox.room.entities.Category
import kotlinx.android.synthetic.main.category_item.view.*

class CategoryViewHolder(itemView: View):RecyclerView.ViewHolder(itemView),View.OnClickListener {
    val categoryName:TextView
    var category:Category?=null
    var categoryClickListener:CategoryAdapter.CategoryClickListener?=null
    init {
        categoryName = itemView.findViewById<TextView>(R.id.tv_category)
    }
    fun updateHolderItem(category:Category,categoryClickListener:CategoryAdapter.CategoryClickListener) {
        this.categoryClickListener = categoryClickListener
        this.category = category
        categoryName.text=category.name
        categoryName.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        categoryClickListener?.onCategoryItemClick(category)
    }
}