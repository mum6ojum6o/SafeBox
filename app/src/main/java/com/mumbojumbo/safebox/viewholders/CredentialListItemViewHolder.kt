package com.mumbojumbo.safebox.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mumbojumbo.safebox.R
import com.mumbojumbo.safebox.adapters.CredentialListAdapter
import com.mumbojumbo.safebox.room.entities.Credential


class CredentialListItemViewHolder(view: View):
    RecyclerView.ViewHolder(view),View.OnClickListener{
    var description:TextView
    var viewCredentials:ImageView
    var credential:Credential?=null
    var credentialItemClickListener:CredentialListAdapter.CredentialItemClickListener?=null
    init{
        description = view.findViewById<TextView>(R.id.tv_description)
        viewCredentials = view.findViewById<ImageView>(R.id.iv_view_credentials)
        view.setOnClickListener(this)
    }

    fun setData(credential: Credential){
        this.credential=credential
    }
    fun setClickListener(credentialItemClickListener: CredentialListAdapter.CredentialItemClickListener){
        this.credentialItemClickListener = credentialItemClickListener
    }

    fun populateUI(){
        description.text=credential?.description
    }

    override fun onClick(v: View?) {
        this.credentialItemClickListener?.onCredentialItemClick(this.credential)
    }

}