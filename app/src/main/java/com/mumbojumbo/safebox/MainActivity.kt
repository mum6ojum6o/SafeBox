package com.mumbojumbo.safebox

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.mumbojumbo.safebox.adapters.CategoryAdapter
import com.mumbojumbo.safebox.room.entities.Category

class MainActivity : AppCompatActivity(), CategoryFragment.CategoryFragmentListener,CreateCategoryFragment.CreateFragmentListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_layout,CategoryFragment.newInstance("","",this),"CATEGORY")
        //transaction.addToBackStack("CATEGORY")
        transaction.commit()

    }

    override fun clicked() {
        var transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_layout,
            CreateCategoryFragment.newInstance("","",this),"ADD_CATEGORY")
        transaction.commit()
    }

    override fun changeListener(category: Category) {
        var viewModel = ViewModelProvider(this).get(CategoryViewModel::class.java)
        viewModel.addCategory(category)
    }
}
