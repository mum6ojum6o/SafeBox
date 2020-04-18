package com.mumbojumbo.safebox.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.mumbojumbo.safebox.R
import com.mumbojumbo.safebox.room.entities.Category
import com.mumbojumbo.safebox.viewholders.CategoryViewHolder

class CategoryAdapter(var categories:List<Category>):RecyclerView.Adapter<CategoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val layout = layoutInflater.inflate(R.layout.category_item,parent,false)
        return CategoryViewHolder(layout)
    }
    override fun getItemCount()=categories.size
    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.updateHoldeItem(categories?.get(position).name)
    }
    fun updateCategories(categories: List<Category>){
        this.categories=categories
    }
}