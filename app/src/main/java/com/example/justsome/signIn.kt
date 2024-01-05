package com.example.justsome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class signIn : AppCompatActivity() {

    lateinit var database : DatabaseReference

    companion object{
        const val KEY1="package com.example.justsome.signIn.name"
        const val KEY2="package com.example.justsome.signIn.mail"
        const val KEY3="package com.example.justsome.signIn.id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val btn_signin=findViewById<Button>(R.id.signin)
        val et_signinid=findViewById<TextInputEditText>(R.id.signin_id)

        btn_signin.setOnClickListener {
            val StringUserId=et_signinid.text.toString()
            if(StringUserId.isNotEmpty()){
                readData(StringUserId)
            }
            else{
                Toast.makeText(this,"Enter the Unique User Id",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun readData(stringUserId: String) {

        database= FirebaseDatabase.getInstance().getReference("User")
        database.child(stringUserId).get().addOnSuccessListener {

            //if user does exists or not
            if(it.exists()){
                val email = it.child("mail").value
                val name = it.child("name").value
                val userId = it.child("id").value

                val intenttoHome= Intent(this,HomeActivity::class.java)
                intenttoHome.putExtra(KEY2,email.toString())
                intenttoHome.putExtra(KEY1,name.toString())
                intenttoHome.putExtra(KEY3,userId.toString())
                startActivity(intenttoHome)

            }
            else{
                Toast.makeText(this,"User doesn't exist! Sign Up first.",Toast.LENGTH_SHORT).show()
            }

        }.addOnFailureListener{
            Toast.makeText(this,"Failed!!",Toast.LENGTH_SHORT).show()
        }

    }
}