package com.mumbojumbo.safebox

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.mumbojumbo.safebox.room.AppDatabase
import com.mumbojumbo.safebox.room.entities.Credential
import com.mumbojumbo.safebox.room.repositories.CredentialRepository

class CredentialViewModel(application: Application, categoryId:Int): AndroidViewModel(application) {
    private val credentialRepository:CredentialRepository
    private val credentialsByCategoryId:LiveData<List<Credential>>

    init {
        val credentialDao = AppDatabase.getDatabase(application,viewModelScope).credentialDao()
        credentialRepository = CredentialRepository(credentialDao,""+ categoryId)
        credentialsByCategoryId = credentialRepository.credentials
    }

    fun getAllCredentialsByCategoryId():LiveData<List<Credential>>
            = credentialsByCategoryId

    fun addCredential(credential:Credential)
            = credentialRepository.insert(credential)
}