package com.mumbojumbo.safebox

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.mumbojumbo.safebox.room.AppDatabase
import com.mumbojumbo.safebox.room.entities.Category
import com.mumbojumbo.safebox.room.repositories.CategoryRepository

class CategoryViewModel(application:Application):AndroidViewModel(application) {
    private val categoryRepository:CategoryRepository
    private val allCategories:LiveData<List<Category>>

    init{
        val categoryDao = AppDatabase.getDatabase(application,viewModelScope).categoryDao()
        categoryRepository = CategoryRepository(categoryDao)
        allCategories = categoryRepository.allCats
    }

    fun getAllCategories():LiveData<List<Category>> = allCategories

    fun addCategory(category: Category) = categoryRepository.insert(category)
}