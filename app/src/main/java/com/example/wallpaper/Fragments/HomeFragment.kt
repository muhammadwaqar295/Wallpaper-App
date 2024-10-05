package com.example.wallpaper.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wallpaper.Adapter.BomAdapter
import com.example.wallpaper.Adapter.CatAdapter
import com.example.wallpaper.Adapter.colortoneAdapter
import com.example.wallpaper.Model.bomModel
import com.example.wallpaper.Model.catModel
import com.example.wallpaper.Model.colortoneModel
import com.example.wallpaper.databinding.FragmentHomeBinding
import com.google.firebase.firestore.FirebaseFirestore


class HomeFragment : Fragment() {

    /*lateinit var bd:FirebaseFirestore*/
    lateinit var binding: FragmentHomeBinding
     lateinit var db: FirebaseFirestore


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding=FragmentHomeBinding.inflate(layoutInflater,container,false)



       db = FirebaseFirestore.getInstance();
        db!!.collection("bestofmonth").addSnapshotListener { value, error ->
            val listBestOfTheMonth= arrayListOf<bomModel>()
            val data = value?.toObjects(bomModel::class.java)


            listBestOfTheMonth.addAll(data!!)

            binding.rcvBom.layoutManager=LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            binding.rcvBom.adapter= BomAdapter(requireContext(),listBestOfTheMonth)


        }

        db!!.collection("thecolortone").addSnapshotListener { value, error ->

            val listTheColorTone= arrayListOf<colortoneModel>()
            val data = value?.toObjects(colortoneModel::class.java)


            listTheColorTone.addAll(data!!)

            binding.rcvTct.layoutManager=LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            binding.rcvTct.adapter= colortoneAdapter(requireContext(),listTheColorTone)

        }

        db!!.collection("categories").addSnapshotListener { value, error ->

            val listofCategory= arrayListOf<catModel>()
            val data = value?.toObjects(catModel::class.java)


            listofCategory.addAll(data!!)

            binding.rcvCat.layoutManager=GridLayoutManager(requireContext(), 2)
            binding.rcvCat.adapter=CatAdapter(requireContext(),listofCategory)

        }
        return binding.root
    }

}