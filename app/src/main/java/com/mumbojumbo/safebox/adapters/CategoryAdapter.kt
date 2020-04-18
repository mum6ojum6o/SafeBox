package com.mumbojumbo.safebox.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mumbojumbo.safebox.R
import com.mumbojumbo.safebox.room.entities.Category
import com.mumbojumbo.safebox.viewholders.CategoryViewHolder

class CategoryAdapter(var categories:List<Category>,var categoryClickListener: CategoryClickListener):RecyclerView.Adapter<CategoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val layout = layoutInflater.inflate(R.layout.category_item,parent,false)
        return CategoryViewHolder(layout)
    }
    override fun getItemCount()=categories.size
    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.updateHolderItem(categories?.get(position),categoryClickListener)
    }
    fun updateCategories(categories: List<Category>){
        this.categories=categories
    }

    interface CategoryClickListener{
        fun onCategoryItemClick(category: Category?)
    }
}