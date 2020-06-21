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
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.mumbojumbo.safebox.room.entities.Credential
import kotlinx.android.synthetic.main.fragment_add_credential.*

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_CATID = "CATEGORRY_ID"

/**
 * A simple [Fragment] subclass.
 * Use the [AddCredentialFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddCredentialFragment : Fragment(),View.OnClickListener {
    private var categoryId: Int? = null
    private  lateinit var fabSave: FloatingActionButton
    private  lateinit var fabReset : FloatingActionButton
    private lateinit var fabCancel: FloatingActionButton
    private lateinit var credentialViewModel: CredentialViewModel

    private lateinit var saveButtonClickListener:SaveButtonClickListener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            categoryId = it.getInt(ARG_CATID)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Toast.makeText(activity,"New Credential for categoryId : "+categoryId, Toast.LENGTH_SHORT).show()
        var layout =  inflater.inflate(R.layout.fragment_add_credential, container, false)
        fabSave = layout.findViewById(R.id.fab_save)
        fabReset = layout.findViewById(R.id.fab_reset)
        fabCancel = layout.findViewById(R.id.fab_cancel)
        fabSave.setOnClickListener(this)
        fabCancel.setOnClickListener(this)
        fabReset.setOnClickListener(this)
        setupViewModel()
        return layout
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Category Id.
         * @return A new instance of fragment AddCredentialFragment.
         */
        @JvmStatic
        fun newInstance(param1: Int, listener: SaveButtonClickListener) =
            AddCredentialFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_CATID, param1)
                }
                saveButtonClickListener = listener
            }
    }

    fun resetViews(){
        et_username.setText(R.string.enter_username)
        et_pwd.text.clear()
        et_confirmpwd.setText("Re-enter Password")
        et_description.text.clear()
    }
//method to validate credentials

fun validateFormEntries():Boolean{
        var result = false;

        if(et_username.text.length > 0 && !et_username.text.equals(R.string.enter_username)){
            if(et_pwd.text.length > 0 ){
                if(et_description.length() > 0){
                    if(et_confirmpwd.length() > 0 || !et_confirmpwd.text.equals(et_pwd.text)){
                        Toast.makeText(activity,"Validations Complete",Toast.LENGTH_SHORT).show()
                        result = true
                    }
                    else{
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            et_confirmpwd.setBackgroundColor(resources.getColor(R.color.colorAccent,null))
                            et_pwd.setBackgroundColor(resources.getColor(R.color.colorAccent,null))

                        }

                    }

                }
                else {
                    //Highlight label under desc
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        et_description.setBackgroundColor(resources.getColor(R.color.colorAccent,null))
                    }
                }
            }else{
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    et_pwd.setBackgroundColor(resources.getColor(R.color.colorAccent,null))
                }
            }
        }else{

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                et_username.setBackgroundColor(resources.getColor(R.color.colorAccent,null))
            }
        }

        return result
    }

    fun saveCredentialsToDB(){
        if(validateFormEntries()){
             var credential = Credential(0,
                 et_username.text.toString(),
             et_pwd.text.toString(),et_description.text.toString(),
                 this.categoryId?:-1)

             credentialViewModel.addCredential(credential)
            saveButtonClickListener.redirectToCredentialListFragment(categoryId?:-1) //redirect

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
                saveButtonClickListener.redirectToCredentialListFragment(categoryId?:-1)
            }
            R.id.fab_reset -> resetViews()
        }
    }

    interface SaveButtonClickListener{
        fun redirectToCredentialListFragment(categoryId:Int)
    }

}
