package com.example.wallpaper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.wallpaper.Fragments.DownloadFragment
import com.example.wallpaper.Fragments.HomeFragment
import com.example.wallpaper.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var  binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        replaceFragment(HomeFragment())

        binding.icHome.setOnClickListener {

           /* Toast.makeText(this,"Home",Toast.LENGTH_SHORT).show()*/
            replaceFragment(HomeFragment())
        }

        binding.icDownload.setOnClickListener {
            /*Toast.makeText(this,"Download",Toast.LENGTH_SHORT).show()*/
            replaceFragment(DownloadFragment())
        }
    }
    fun replaceFragment(fragment:Fragment){

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentreplace, fragment)
        transaction.commit()
    }
}