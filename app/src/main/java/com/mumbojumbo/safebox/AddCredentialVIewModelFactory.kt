package com.mumbojumbo.safebox

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mumbojumbo.safebox.CredentialViewModel
class AddCredentialVIewModelFactory<T>(val application: Application,val categoryId:Int) :
    ViewModelProvider.Factory {



    var credentialViewModel:CredentialViewModel = getCredentialViewModelFactory(application,categoryId)



    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        try {
            return  credentialViewModel as T
        }catch (e:InstantiationException){
            throw InstantiationException("Unable to create an Instance of {$modelClass}")
        }catch(e: IllegalAccessException){
            throw IllegalAccessException("Unable to create an Instance of {$modelClass}")
        }
    }

    fun getCredentialViewModelFactory(application: Application, categoryId:Int)=CredentialViewModel(application,categoryId)

}