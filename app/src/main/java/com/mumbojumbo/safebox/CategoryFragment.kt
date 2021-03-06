package com.mumbojumbo.safebox

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mumbojumbo.safebox.adapters.CategoryAdapter
import com.mumbojumbo.safebox.room.entities.Category

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CategoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CategoryFragment : Fragment(),View.OnClickListener,CategoryAdapter.CategoryClickListener {
    lateinit var recyclerView: RecyclerView
    lateinit var addButton: Button
    lateinit var adapter: CategoryAdapter
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var categoryFragmentListener:CategoryFragmentListener?= null
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
        var layout = inflater.inflate(R.layout.fragment_category, container, false)
        recyclerView = layout.findViewById<RecyclerView>(R.id.rv_categories)
        addButton = layout.findViewById<Button>(R.id.btn_add_cat)
        addButton.setOnClickListener(this)
        adapter = CategoryAdapter( ArrayList<Category>(),this as CategoryAdapter.CategoryClickListener)
        var llm = LinearLayoutManager(recyclerView.context)
        recyclerView.layoutManager = llm
        recyclerView.adapter = adapter
        setupViewModel()
        return layout
    }


    private fun setupViewModel(){
        var categoryViewModel = ViewModelProvider(this).get(CategoryViewModel::class.java)
        categoryViewModel.getAllCategories().observe(viewLifecycleOwner, Observer {
                categories:List<Category>->run{
                adapter.updateCategories(categories)
                adapter.notifyDataSetChanged()
            }
        })
    }
    companion object {
        /************************************************
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.C
         *********************************************************
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CategoryFragment.
         ****************************************************/
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String, clickListener:CategoryFragmentListener) =
            CategoryFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
                categoryFragmentListener = clickListener
            }
    }

    interface CategoryFragmentListener{
        fun clicked();
    }

    override fun onClick(v: View?) {
        if(categoryFragmentListener!=null){
            categoryFragmentListener!!.clicked();
        }
    }

    override fun onCategoryItemClick(category: Category?) {
        var intent = Intent(activity,ListCredentialActivity::class.java)
        intent.putExtra("ID",category?.id)
        startActivity(intent)
    }
}
