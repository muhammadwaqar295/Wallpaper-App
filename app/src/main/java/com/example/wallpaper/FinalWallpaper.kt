package com.example.wallpaper

import android.app.WallpaperManager
import android.content.ContentValues
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.Image
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.wallpaper.databinding.ActivityFinalWallpaperBinding
import com.google.firebase.inject.Deferred
import kotlinx.coroutines.*
import java.io.File
import java.io.IOException
import java.io.OutputStream
import java.net.URL
import java.util.*

class FinalWallpaper : AppCompatActivity() {


    lateinit var binding: ActivityFinalWallpaperBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinalWallpaperBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var url = intent.getStringExtra("link")

        val  urlImage = URL(url)


        Glide.with(this).load(url).into(binding.finalWallpaper)

        binding.btnsetwallpaper.setOnClickListener {

            val result: kotlinx.coroutines.Deferred<Bitmap?> = GlobalScope.async {
                urlImage.toBitmap()
            }
            GlobalScope.launch(Dispatchers.Main) {

                val wallpaperManager=WallpaperManager.getInstance(applicationContext)
                wallpaperManager.setBitmap(result.await())
            }

        }

        binding.btndownload.setOnClickListener {
            var result: kotlinx.coroutines.Deferred<Bitmap?> = GlobalScope.async {
                urlImage.toBitmap()
            }
            GlobalScope.launch (Dispatchers.Main){

                saveImage(result.await())
            }

        }

    }
    fun URL.toBitmap(): Bitmap? {
        return try {
            BitmapFactory.decodeStream(openStream())
        } catch (e: IOException) {
            null
        }
    }




    private fun saveImage(image: Bitmap?) {

        val random1 = Random().nextInt(528985)

        val random2 = Random().nextInt(529584)

        val name = "AMOLED-${random1 + random2}"

        val data: OutputStream

        try {

            val resolver = contentResolver

            val contentValues = ContentValues()
            contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, "$name.jpg")
            contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
            contentValues.put(

                MediaStore.MediaColumns.RELATIVE_PATH,
                Environment.DIRECTORY_PICTURES + File.separator + "Amoled Wallpaper"

            )

            val imageUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
            data = resolver.openOutputStream(Objects.requireNonNull(imageUri)!!)!!
            image?.compress(Bitmap.CompressFormat.JPEG, 100, data)

            Objects.requireNonNull<OutputStream>(data)

            Toast.makeText(this, "Image Save", Toast.LENGTH_SHORT).show()

        } catch (e: Exception) {

            Toast.makeText(this, "Image Not Save", Toast.LENGTH_SHORT).show()

        }

    }
}

