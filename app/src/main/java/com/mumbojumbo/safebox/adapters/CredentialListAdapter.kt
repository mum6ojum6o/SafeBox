package com.mumbojumbo.safebox.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mumbojumbo.safebox.R
import com.mumbojumbo.safebox.room.entities.Credential
import com.mumbojumbo.safebox.viewholders.CredentialListItemViewHolder

class CredentialListAdapter(var credentialList:List<Credential>,var credentialItemClickListener: CredentialItemClickListener):
    RecyclerView.Adapter<CredentialListItemViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CredentialListItemViewHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
        var layout = layoutInflater.inflate(R.layout.credential_item,parent,false)
        return CredentialListItemViewHolder(layout)
    }

    override fun getItemCount()=credentialList.size

    override fun onBindViewHolder(holder: CredentialListItemViewHolder, position: Int) {
        var credential = credentialList[position]
        holder.setData(credential)
        holder.setClickListener(credentialItemClickListener)
        holder.populateUI()
    }

    interface CredentialItemClickListener{
        fun onCredentialItemClick(credential:Credential?);
    }

    fun updateCredentialList(list:List<Credential>){
        this.credentialList = list
    }
}