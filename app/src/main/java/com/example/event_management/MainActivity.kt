package com.example.event_management

import android.app.ProgressDialog
import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.net.toUri
import com.example.event_management.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var db : DatabaseReference
    private lateinit var sdb : StorageReference
    lateinit var imguri: Uri
    var user=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val progress=ProgressDialog(this)
        binding.chosimg.setOnClickListener {
            pickimagefromgallery()
        }
        binding.btnrgs.setOnClickListener {
            var name=binding.ed1.text.toString()
            var username=binding.ed4.text.toString()
            user=binding.ed4.text.toString()
            var password=binding.ed5.text.toString()
            var contact=binding.ed2.text.toString()
            var email=binding.ed3.text.toString()
            if(name.isNotEmpty() && username.isNotEmpty() && password.isNotEmpty()&& contact.isNotEmpty()&& email.isNotEmpty() && binding.img.drawable !=null) {
                //code of insert data into firebase
                val user = Firebase_Data(name, username, password,contact,email,imguri.toString())
                db=FirebaseDatabase.getInstance().getReference("Users")
                db.child(username).get().addOnSuccessListener {
                    if(!it.exists()){
                        progress.setMessage("Creating Account...")
                        progress.setCancelable(false)
                        progress.show()
                        db.child(username).setValue(user).addOnSuccessListener {
                            var ad = AlertDialog.Builder(this)
                            ad.setTitle("Message")
                            ad.setMessage("Account registered successfully")
                            ad.setPositiveButton("Ok", null)
                            ad.show()
                            uploadimageprofile()
                            binding.ed1.text.clear()
                            binding.ed2.text.clear()
                            binding.ed3.text.clear()
                            binding.ed4.text.clear()
                            binding.ed5.text.clear()
                            progress.dismiss()
                            binding.img.setImageDrawable(null)
                        }.addOnFailureListener {
                            progress.dismiss()
                            var ad = AlertDialog.Builder(this)
                            ad.setTitle("Message")
                            ad.setMessage("Account not register")
                            ad.setPositiveButton("Ok", null)
                            ad.show()
                        }
                    }else{
                        var ad = AlertDialog.Builder(this)
                        ad.setTitle("Message")
                        ad.setMessage("Account already exist")
                        ad.setPositiveButton("Ok", null)
                        ad.show()
                    }
                }
            }else if(binding.img.drawable ==null){
                Toast.makeText(this,"Image is required",Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this,"All fields required",Toast.LENGTH_SHORT).show()
            }
        }
        binding.loginLink.setOnClickListener {
            val intent=Intent(this,login_form::class.java)
            startActivity(intent)
        }
    }

    private fun pickimagefromgallery() {
        val intent=Intent()
        intent.type="image/*"
        intent.action=Intent.ACTION_GET_CONTENT
        startActivityForResult(intent,100)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 100 && resultCode== RESULT_OK){
            imguri=data?.data!!
            binding.img.setImageURI(imguri)
        }
    }
    fun uploadimageprofile(){
        sdb=FirebaseStorage.getInstance().getReference("Users/"+user)
        sdb.putFile(imguri)
    }
}