package com.adl.fireauth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    private lateinit var  auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        auth= FirebaseAuth.getInstance()
        btnRegUser.setOnClickListener({
           if(txtRegPassword.text.toString().equals(txtRePassword.text.toString())){
               register()
           }else{
               Toast.makeText(applicationContext,"Password tidak sama!",Toast.LENGTH_LONG).show()
           }

        })
    }

    fun register(){
        auth.createUserWithEmailAndPassword(txtEmail.text.toString(),txtRegPassword.text.toString()).addOnCompleteListener { task ->
            if(task.isSuccessful){
                val intent= Intent(this,LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }.addOnFailureListener { exception ->
            Toast.makeText(applicationContext,exception.localizedMessage,Toast.LENGTH_LONG).show()
        }
    }
}