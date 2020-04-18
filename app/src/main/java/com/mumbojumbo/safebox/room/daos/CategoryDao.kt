package com.mumbojumbo.safebox.room.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mumbojumbo.safebox.room.entities.Category

@Dao
interface CategoryDao {
    @Query("SELECT * FROM categories ORDER BY name ASC")
    fun getAllCategories():LiveData<List<Category>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(category:Category)

    @Query("DELETE from categories")
    fun deleteAll()

}