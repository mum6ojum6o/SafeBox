package com.mumbojumbo.safebox

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.mumbojumbo.safebox.adapters.CredentialListAdapter
import com.mumbojumbo.safebox.room.entities.Credential

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
const val CATEGORY_ID = "ID"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CredentialListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CredentialListFragment : Fragment(),View.OnClickListener,CredentialListAdapter.CredentialItemClickListener {
    private val TAG="CredentialListFragment"
    private var param1: Int? = null
    private var param2: String? = null
    private var fabAddCredential : FloatingActionButton?=null
    private var credentialListView:RecyclerView?=null
    private var clickListener:ClickListener?=null
    private lateinit var adapter: CredentialListAdapter
    private lateinit var credentialViewModel:CredentialViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(CATEGORY_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var layout =  inflater.inflate(R.layout.fragment_credential_list, container, false)
        fabAddCredential = layout.findViewById<FloatingActionButton>(R.id.fab_addCredential)
        credentialListView = layout.findViewById<RecyclerView>(R.id.rv_credential_list)
        //Toast.makeText(activity,"Category_ID:"+param1,Toast.LENGTH_SHORT).show()
        activity?.actionBar?.title="Credentials"
        activity?.actionBar?.show()
        fabAddCredential?.setOnClickListener(this);
        var layoutManager = LinearLayoutManager(activity)
        adapter = CredentialListAdapter(ArrayList<Credential>(),
            (this) as CredentialListAdapter.CredentialItemClickListener)

        credentialListView?.layoutManager = layoutManager
        credentialListView?.adapter = adapter

        setupViewModel()
        return layout
    }

    companion object {
        /********************************************************
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 is the Category Id clicked on.
         * @return A new instance of fragment CredentialListFragment.
         **********************************************************/
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: Int, param2: String,clickListener: ClickListener) =
            CredentialListFragment().apply {
                arguments = Bundle().apply {
                    putInt(CATEGORY_ID, param1)
                }

                this.clickListener =clickListener
            }
    }

    interface ClickListener{
        fun onAddButtonClick(id:Int)
        fun credentialItemClick(credential: Credential)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.fab_addCredential -> this.clickListener?.onAddButtonClick(this.param1!!);
        }
    }

    fun setupViewModel(){
        //credentialViewModel = ViewModelProvider(this).get(CredentialViewModel::class.java)
        credentialViewModel = AddCredentialVIewModelFactory<CredentialViewModel>(activity?.application!! ,param1?:-1 )
            .create(CredentialViewModel::class.java)

        credentialViewModel.getAllCredentialsByCategoryId().observe(viewLifecycleOwner, Observer{
            credentials:List<Credential> -> run{

                Log.d("CredentialListFragment","Credential List updated {${credentials.size}}")
                adapter.updateCredentialList(credentials)
                adapter.notifyDataSetChanged()
            }
        })
    }

    override fun onCredentialItemClick(credential: Credential?) {
        if(credential!=null)
            clickListener?.credentialItemClick(credential!!)
    }
}
