package com.example.ezpt

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ezpt.ui.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_register.*
import java.util.regex.Pattern

class RegisterActivity : AppCompatActivity() {

    // 이메일과 비밀번호
    //private var editTextEmail: EditText? = null
    //private var editTextPassword: EditText? = null

    //private var email = ""
    //private var password = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        findViewById<Button>(R.id.register_btn).setOnClickListener {
            createUser()
        }
//        firebaseAuth = FirebaseAuth.getInstance()
        //editTextEmail = findViewById(R.id.email_edittext)
        //editTextPassword = findViewById(R.id.password_edittext)

    }

    fun createUser() {
        var email = email_edittext.text.toString()
        var password = password_edittext.text.toString()

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener{ task ->
            if(task.isSuccessful) { // 회원가입 성공
                Toast.makeText(
                    this@RegisterActivity,
                    R.string.register_succeeded,
                    Toast.LENGTH_SHORT
                ).show()
                val nextIntent = Intent(this, ProfileSetActivity::class.java)
                startActivity(nextIntent)
            } else {
                // 회원가입 실패
                Toast.makeText(
                    this@RegisterActivity,
                    R.string.register_failed,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}