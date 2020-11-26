package com.example.ezpt.ui.login
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ezpt.MainActivity
import com.example.ezpt.R
import com.example.ezpt.RegisterActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {
    private var mAuth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mAuth = FirebaseAuth.getInstance()

        // SIGN UP 버튼
        findViewById<Button>(R.id.btn_signUp).setOnClickListener {
            val nextIntent = Intent(this, RegisterActivity::class.java)
            startActivity(nextIntent)
        }

        // SIGN IN 버튼
        findViewById<Button>(R.id.btn_signIn).setOnClickListener {
            signIn()
        }

    }

    fun signIn() {
        var email = email_edittext.text.toString()
        var password = password_edittext.text.toString()

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener{ task ->
            if(task.isSuccessful) { // 로그인 성공
                val user: FirebaseUser = mAuth!!.getCurrentUser()!!
                Toast.makeText(
                    this@LoginActivity,
                    R.string.login_succeeded,
                    Toast.LENGTH_SHORT
                ).show()
                moveMainPage()

            } else {
                // 로그인 실패
                Toast.makeText(
                    this@LoginActivity,
                    R.string.login_failed,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    fun moveMainPage() {
        var currentUser = FirebaseAuth.getInstance().currentUser
        if(currentUser != null) {
            startActivity(Intent(this,MainActivity::class.java))
        }
    }
}