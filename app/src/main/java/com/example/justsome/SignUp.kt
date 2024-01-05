package com.example.justsome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.Firebase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUp : AppCompatActivity() {

    lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val btn_signup = findViewById<Button>(R.id.signup)
        val et_name = findViewById<TextInputEditText>(R.id.name)
        val et_mail = findViewById<TextInputEditText>(R.id.mail)
        val et_pass = findViewById<TextInputEditText>(R.id.pass)
        val et_id = findViewById<TextInputEditText>(R.id.uniid)

        btn_signup.setOnClickListener {
            val name = et_name.text.toString()
            val mail = et_mail.text.toString()
            val pass = et_pass.text.toString()
            val id = et_id.text.toString()

            val user=Users(name,id,mail,pass)
            database= FirebaseDatabase.getInstance().getReference("User")
            database.child(id).setValue(user).addOnSuccessListener {
                et_name.text?.clear()
                Toast.makeText(this,"User Registered Sucessfully",Toast.LENGTH_SHORT).show()
                val intenttohomeact=Intent(this,HomeActivity::class.java)
                startActivity(intenttohomeact)
            }.addOnFailureListener {
                Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
            }


        }

        val signintext=findViewById<TextView>(R.id.signintv)
        signintext.setOnClickListener{
            val opensignin=Intent(this,signIn::class.java)
            startActivity(opensignin)
        }
    }
}