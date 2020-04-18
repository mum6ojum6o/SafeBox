package com.mumbojumbo.safebox.room.repositories

import androidx.lifecycle.LiveData
import com.mumbojumbo.safebox.room.daos.CategoryDao
import com.mumbojumbo.safebox.room.daos.CredetialDao
import com.mumbojumbo.safebox.room.entities.Credential

class CredentialRepository(val credentialDao: CredetialDao,categoryId:String) {
    val credentials:LiveData<List<Credential>> = credentialDao.getAllCredentialByCategory(categoryId)

    fun insert(credential:Credential){
        credentialDao.add(credential)
    }
}