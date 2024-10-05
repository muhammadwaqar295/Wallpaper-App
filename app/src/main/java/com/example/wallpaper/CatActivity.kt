package com.example.wallpaper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.wallpaper.Adapter.CatAdapter
import com.example.wallpaper.Adapter.CatimagesAdapter
import com.example.wallpaper.Model.bomModel
import com.example.wallpaper.Model.catModel
import com.example.wallpaper.databinding.ActivityCatBinding
import com.google.firebase.firestore.FirebaseFirestore

class CatActivity : AppCompatActivity() {

    lateinit var binding:   ActivityCatBinding
    lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCatBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val db = FirebaseFirestore.getInstance()
        val uid = intent.getStringExtra("uid")
        val name = intent.getStringExtra("name")


        db.collection("categories").document(uid!!).collection("wallpaper")
            .addSnapshotListener { value, error ->

                val listofCatWallpaper = arrayListOf<bomModel>()
                val data = value?.toObjects(bomModel::class.java)


                listofCatWallpaper.addAll(data!!)
                binding.catTitle.text=name.toString()
                binding.catcount.text="${listofCatWallpaper.size} Wallpaper Available"

                binding.catRCV.layoutManager =
                    StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                binding.catRCV.adapter=CatimagesAdapter(this,listofCatWallpaper)
            }


    }
}