package com.mumbojumbo.safebox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ListCredentialActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_credential)
        var id = intent.getIntExtra("ID",0)
        var transaction = supportFragmentManager.beginTransaction()
        var credFrag = CredentialListFragment.newInstance(id,"")
        transaction.replace(R.id.credential_list_fragment_container,credFrag)
        //transaction.addToBackStack(null)
        transaction.commit()
    }
}
