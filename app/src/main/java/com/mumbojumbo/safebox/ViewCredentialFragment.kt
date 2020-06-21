package com.mumbojumbo.safebox

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import com.mumbojumbo.safebox.room.entities.Credential
import kotlinx.android.synthetic.main.fragment_add_credential.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val CREDENTIAL = "CREDENTIAL"


/**
 * A simple [Fragment] subclass.
 * Use the [ViewCredentialFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ViewCredentialFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var credential: Credential? = null
    private var usernameEditText: EditText?=null
    private var passwordEditText: EditText?=null
    private var confirmPasswordEditText: EditText?=null
    private var descEditText: EditText?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            credential = it.getParcelable<Credential>(CREDENTIAL)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var layout = inflater.inflate(R.layout.fragment_add_credential,container,false)
        usernameEditText = layout.findViewById(R.id.et_username)
        passwordEditText = layout.findViewById(R.id.et_pwd)
        confirmPasswordEditText = layout.findViewById(R.id.et_confirmpwd)
        descEditText = layout.findViewById(R.id.et_description)
        populateUI()
        return layout
    }

    fun populateUI(){
        usernameEditText?.setText(credential?.username)
        passwordEditText?.setText(credential?.password)
        passwordEditText?.isEnabled = false
        confirmPasswordEditText?.setText(credential?.password)
        confirmPasswordEditText?.isEnabled = false
        descEditText?.setText(credential?.description)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ViewCredentialFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: Credential) =
            ViewCredentialFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(CREDENTIAL, param1)
                }
            }
    }
}
