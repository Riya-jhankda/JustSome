package com.example.justsome

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        //fetching data
        val name=intent.getStringExtra(signIn.KEY1)
        val mail=intent.getStringExtra(signIn.KEY2)
        val id=intent.getStringExtra(signIn.KEY3)

        //gettig the id
        val welcometext=findViewById<TextView>(R.id.tv1)
        val maile=findViewById<TextView>(R.id.mail)
        val iduser=findViewById<TextView>(R.id.id)

        //setting up the text for text views
        welcometext.text="Welcome $name"
        maile.text="Mail: $mail"
        iduser.text="User Id: $id"
    }
}