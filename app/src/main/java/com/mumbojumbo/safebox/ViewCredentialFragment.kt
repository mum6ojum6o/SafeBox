package com.mumbojumbo.safebox

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.mumbojumbo.safebox.room.entities.Credential

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val CREDENTIAL = "CREDENTIAL"


/**
 * A simple [Fragment] subclass.
 * Use the [ViewCredentialFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ViewCredentialFragment : Fragment(), View.OnClickListener {

    private  lateinit var credential: Credential
    private var usernameTextView: TextView?=null
    private var passwordTextView: TextView?=null
    //private var confirmPasswordEditText: EditText?=null
    private var descTextView: TextView?=null
    lateinit var editFab: FloatingActionButton
    private var editButtonClickListener:EditButtonClickListener?=null
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
        var layout = inflater.inflate(R.layout.fragment_display_credentials,container,false)
        usernameTextView = layout.findViewById(R.id.tv_userId_display)
        passwordTextView = layout.findViewById(R.id.tv_password_display)
        //confirmPasswordEditText = layout.findViewById(R.id.et_confirmpwd)
        descTextView = layout.findViewById(R.id.tv_description_display)
        editFab = layout.findViewById(R.id.edit_floatingActionButton)
        editFab.setOnClickListener(this)
        populateUI()
        return layout
    }

    fun populateUI(){
        usernameTextView?.setText(credential?.username)
        passwordTextView?.setText(credential?.password)
        //passwordTextView?.isEnabled = false
        //confirmPasswordEditText?.setText(credential?.password)
        //confirmPasswordEditText?.isEnabled = false
        descTextView?.setText(credential?.description)
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
        fun newInstance(param1: Credential, clickListener:EditButtonClickListener) =
            ViewCredentialFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(CREDENTIAL, param1)
                }
                editButtonClickListener = clickListener
            }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.edit_floatingActionButton ->
                editButtonClickListener?.editButtonClick(credential)
        }
    }

    interface EditButtonClickListener{
        fun editButtonClick(credentialId:Credential);
    }

}
