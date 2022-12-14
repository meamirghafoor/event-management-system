package com.example.event_management

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.event_management.databinding.ActivityHomeBinding
import com.google.android.material.navigation.NavigationView
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.nav_header_home.*
import java.io.File


class home : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.appBarHome.toolbar)
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_home)
        setprofile()
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,R.id.nav_book,R.id.nav_cancle
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
//        setprofile()

    }

    private fun setprofile() {
        val intent = intent
        val extras = intent.extras
        val username= extras?.getString("username")
        val name= extras?.getString("name")
        val email= extras?.getString("email")
        val sdb=FirebaseStorage.getInstance().reference.child("Users/$username")
        val localfile= File.createTempFile("tempImage","jpg")
        sdb.getFile(localfile).addOnSuccessListener {
            val bitmap=BitmapFactory.decodeFile(localfile.absolutePath)
            imageView.setImageBitmap(bitmap)
            header_name.setText(name)
            sub_header.setText(email)
            //username_header.setText("meamir")

        }

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_home)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}