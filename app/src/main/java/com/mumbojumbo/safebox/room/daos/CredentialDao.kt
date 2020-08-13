package com.mumbojumbo.safebox.room.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mumbojumbo.safebox.room.entities.Category
import com.mumbojumbo.safebox.room.entities.Credential

@Dao
interface CredetialDao{
    @Query("SELECT * FROM credential WHERE categoryId=:categoryId ORDER BY description ASC")
    fun getAllCredentialByCategory(categoryId:String):LiveData<List<Credential>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(credential: Credential)

    @Query("SELECT * FROM credential where id=:id")
    fun getCredentialById(id:String):LiveData<Credential>
}