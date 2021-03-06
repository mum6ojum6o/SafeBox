package com.mumbojumbo.safebox

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.mumbojumbo.safebox.room.entities.Category

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CreateCategoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CreateCategoryFragment : Fragment(),View.OnClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var btnAdd: Button
    private lateinit var etCategoryName: EditText
    private lateinit var createFragmentListener:CreateFragmentListener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var layout = inflater.inflate(R.layout.fragment_create_category, container, false)
        btnAdd = layout.findViewById<Button>(R.id.btn_save)
        btnAdd.setOnClickListener(this)
        etCategoryName = layout.findViewById<EditText>(R.id.et_cat_name)
        return layout
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CreateCategoryFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String,createFragmentListener: CreateFragmentListener) =
            CreateCategoryFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
                this.createFragmentListener = createFragmentListener
            }

    }

    interface CreateFragmentListener{
        fun changeListener(category: Category)
    }
    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btn_save -> this.createFragmentListener.changeListener(
                Category(
                    0,
                    etCategoryName.text.toString()
                )
            )
        }
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.frame_layout,CategoryFragment.newInstance("","",activity as MainActivity),"CATEGORY")
            ?.commit()
    }
}
