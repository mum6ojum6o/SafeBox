package com.mumbojumbo.safebox

import android.graphics.Color.red
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.commit
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.mumbojumbo.safebox.room.entities.Credential
import kotlinx.android.synthetic.main.fragment_add_credential.*
import kotlinx.android.synthetic.main.fragment_add_credential.et_username
import kotlinx.android.synthetic.main.fragment_add_credential_md.*

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_CATID = "CATEGORRY_ID"

/**
 * A simple [Fragment] subclass.
 * Use the [AddCredentialFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddCredentialFragment : Fragment(),View.OnClickListener {
    private var categoryId: Int? = null
    private var credential:Credential? = null
    private  lateinit var fabSave: FloatingActionButton
    private  lateinit var fabReset : FloatingActionButton
    private lateinit var fabCancel: FloatingActionButton
    private lateinit var credentialViewModel: CredentialViewModel

    private lateinit var saveButtonClickListener:SaveButtonClickListener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

            if(it.containsKey(ARG_CATID))
                categoryId = it.getInt(ARG_CATID)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Toast.makeText(activity,"New Credential for categoryId : "+categoryId, Toast.LENGTH_SHORT).show()
        var layout =  inflater.inflate(R.layout.fragment_add_credential_md, container, false)
        fabSave = layout.findViewById(R.id.fab_save)
        fabReset = layout.findViewById(R.id.fab_reset)
        fabCancel = layout.findViewById(R.id.fab_cancel)
        fabSave.setOnClickListener(this)
        fabCancel.setOnClickListener(this)
        fabReset.setOnClickListener(this)
        setupViewModel()


        return layout
    }

    override fun onResume() {
        super.onResume()
        if(credential!=null) {
            populateUI()
        }
    }

    fun populateUI(){
        et_username.setText(credential?.username)
        et_password.setText(credential?.password)
        et_confirm_password.setText(credential?.password)
        et_desc.setText(credential?.description)
    }


    companion object {
        @JvmStatic
        fun newInstance(param1: Int, listener: SaveButtonClickListener) =
            AddCredentialFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_CATID, param1)
                }
                saveButtonClickListener = listener
            }

        fun newInstance(credential: Credential,listener:SaveButtonClickListener)=
            AddCredentialFragment().apply {
                saveButtonClickListener = listener
                this.credential = credential
            }

    }


    fun resetViews(){
        et_username.setText(R.string.enter_username)
        et_password.text?.clear()
        et_confirm_password.setText("Re-enter Password")
        et_desc.setText("Enter Description")
        textFieldLayout_password.error = null
        textFieldLayout_username.error = null
        textFieldLayout_confirm_password.error = null
        textFieldLayout_desc.error = null

    }
//method to validate credentials

fun validateFormEntries():Boolean{
        var result = false;

        if(et_username.text.length > 0 && !et_username.text.equals(R.string.enter_username)){
            textFieldLayout_username.error = null
            if((et_confirm_password.text?.length ?:0) > 0 ){
                textFieldLayout_password.error = null

                if(et_desc.length() > 0){
                    textFieldLayout_desc.error = null
                    if(et_confirm_password.length()  > 0 && (et_confirm_password.text?.toString()?.equals(et_password.text?.toString()))?:false ){
                        textFieldLayout_confirm_password.error = null
                        Toast.makeText(activity,"Validations Complete",Toast.LENGTH_SHORT).show()
                        result = true
                    }
                    else{
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            textFieldLayout_confirm_password.error=resources.getString(R.string.confirm_password_error)
                        }
                    }
                }
                else {
                    //Highlight label under desc
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        //et_desc.setBackgroundColor(resources.getColor(R.color.colorAccent,null))
                        textFieldLayout_desc.error=resources.getString(R.string.description_error)
                    }
                }
            }else{
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    //et_password.setBackgroundColor(resources.getColor(R.color.colorAccent,null))
                    textFieldLayout_password.error= resources.getString(R.string.password_error)
                }
            }
        }else{

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                //et_username.setBackgroundColor(resources.getColor(R.color.colorAccent,null))
                textFieldLayout_username.error = resources.getString(R.string.username_error)
            }
        }

        return result
    }

    fun saveCredentialsToDB(){

        if(validateFormEntries()){
             var credential = Credential(credential?.id?:0,
                 et_username.text.toString(),
             et_password.text.toString(),et_desc.text.toString(),
                 this.categoryId?:credential?.categoryId?:-1)
             credentialViewModel.addCredential(credential)
            var supportFragmentManager = activity?.supportFragmentManager
            var fragmentTransaction = supportFragmentManager?.beginTransaction()
            supportFragmentManager?.popBackStackImmediate()
            fragmentTransaction?.commit()
            //saveButtonClickListener.redirectToCredentialListFragment(categoryId?:-1) //redirect
           // activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit()

        }
    }

    fun setupViewModel(){
        credentialViewModel =
            AddCredentialVIewModelFactory<CredentialViewModel>(activity?.application!!,categoryId?:-1)
                .create(CredentialViewModel::class.java)
    }
    override fun onClick(v: View?) {
        when(v?.id){
            R.id.fab_save -> {
                Toast.makeText(activity,"You clicked Save",Toast.LENGTH_SHORT).show()
                saveCredentialsToDB()

            }
            R.id.fab_cancel -> {
                //saveButtonClickListener.redirectToCredentialListFragment(categoryId?:-1)
                var supportFragmentManager = activity?.supportFragmentManager
                var fragmentTransaction = supportFragmentManager?.beginTransaction()
                supportFragmentManager?.popBackStackImmediate()
                fragmentTransaction?.commit()
            }
            R.id.fab_reset -> resetViews()
        }
    }

    interface SaveButtonClickListener{
        fun redirectToCredentialListFragment(categoryId:Int)
    }

}
