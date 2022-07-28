package com.example.event_management

import android.R
import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.event_management.databinding.ActivityLoginFormBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_book.*


class login_form : AppCompatActivity() {
    private lateinit var bind : ActivityLoginFormBinding
    private lateinit var db: DatabaseReference
    var username:String=""
    var password:String=""
    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind= ActivityLoginFormBinding.inflate(layoutInflater)
        setContentView(bind.root)
        bind.btnlogin.setOnClickListener {
            startActivity(Intent(this,home::class.java))
//            username=bind.logtxt.text.toString();
//            password=bind.ed3.text.toString()
//            if(username.isNotEmpty() && password.isNotEmpty()){
//                fetch_data(username)
//            }else{
//                Toast.makeText(this,"Please fill all fields",Toast.LENGTH_SHORT).show()
//            }

        }
        bind.regisLink.setOnClickListener {
            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
    private fun fetch_data(username: String) {
        db= FirebaseDatabase.getInstance().getReference("Users")
        val progress= ProgressDialog(this)
        progress.setMessage("Logging...")
        progress.setCancelable(false)
        progress.show()
        db.child(username).get().addOnSuccessListener {
            if(it.exists()){
                var psswrd:String=it.child("pasword").value.toString()
                var name:String=it.child("name").value.toString()
                var email:String=it.child("email").value.toString()
                if(password.equals(psswrd)){
                    progress.dismiss()
                    val intent = Intent(this, home::class.java)
                    val extras = Bundle()
                    extras.putString("username", username)
                    extras.putString("name", name)
                    extras.putString("email", email)
                    intent.putExtras(extras)
                    startActivity(intent)
                }else{
                    progress.dismiss()
                    var ad = AlertDialog.Builder(this)
                    ad.setTitle("Message")
                    ad.setMessage("Failed to login")
                    ad.setPositiveButton("Ok", null)
                    ad.show()
                }
            }else{
                var ad = AlertDialog.Builder(this)
                ad.setTitle("Message")
                ad.setMessage("Failed to login")
                ad.setPositiveButton("Ok", null)
                ad.show()
                progress.dismiss()
            }
        }
    }
}