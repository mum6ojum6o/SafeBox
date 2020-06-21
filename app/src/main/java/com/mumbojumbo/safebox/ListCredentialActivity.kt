package com.mumbojumbo.safebox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.mumbojumbo.safebox.room.entities.Credential

class ListCredentialActivity : AppCompatActivity(),CredentialListFragment.ClickListener,AddCredentialFragment.SaveButtonClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_credential)
        var id = intent.getIntExtra("ID",0)
        /*var transaction = supportFragmentManager.beginTransaction()
        var credFrag = CredentialListFragment.newInstance(id,"",this as CredentialListFragment.ClickListener)
        transaction.add(R.id.credential_list_fragment_container,credFrag)
        //transaction.addToBackStack(null)
        transaction.commit()*/
        redirectToCredentialListFragment(id)
        //Toast.makeText(this,"ID:"+id,Toast.LENGTH_SHORT).show()

    }

    override fun onAddButtonClick(id: Int) {
        var transaction = supportFragmentManager.beginTransaction()
        var addNewCredentialFlag = AddCredentialFragment.newInstance(id, this as AddCredentialFragment.SaveButtonClickListener)
        transaction.add(R.id.credential_list_fragment_container,addNewCredentialFlag)
        transaction.commit()
    }

    override fun credentialItemClick(credential: Credential) {
        Toast.makeText(this,"Credential id:${credential?.id} and categoryId: ${credential?.categoryId}",Toast.LENGTH_SHORT).show()
        var transaction = supportFragmentManager.beginTransaction()
        var viewCredential = ViewCredentialFragment.newInstance(credential)
        transaction.replace(R.id.credential_list_fragment_container,viewCredential)
        transaction.commit()
    }

    override fun redirectToCredentialListFragment(categoryId: Int) {
        var transaction = supportFragmentManager.beginTransaction()
        var credFrag = CredentialListFragment.newInstance(categoryId,"",this as CredentialListFragment.ClickListener)
        transaction.replace(R.id.credential_list_fragment_container,credFrag)
        transaction.commit()
    }
}
