package com.mumbojumbo.safebox

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
const val CATEGORY_ID = "ID"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CredentialListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CredentialListFragment : Fragment() {
    private var param1: Int? = null
    private var param2: String? = null
    private var fabAddCredential : FloatingActionButton?=null
    private var credentialListView:RecyclerView?=null
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
        return layout
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CredentialListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: Int, param2: String) =
            CredentialListFragment().apply {
                arguments = Bundle().apply {
                    putInt(CATEGORY_ID, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
