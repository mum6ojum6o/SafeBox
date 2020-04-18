package com.mumbojumbo.safebox.room.repositories

import androidx.lifecycle.LiveData
import com.mumbojumbo.safebox.room.daos.CategoryDao
import com.mumbojumbo.safebox.room.entities.Category

class CategoryRepository(private val categoryDao: CategoryDao) {
    val allCats:LiveData<List<Category>> = categoryDao.getAllCategories()

    fun insert(category: Category) =  Thread(Runnable(){categoryDao.add(category)}).start()
}