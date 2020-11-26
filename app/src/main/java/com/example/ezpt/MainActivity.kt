package com.example.ezpt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.ezpt.ui.login.LoginActivity
import com.example.ezpt.ui.login.SearchTrainerActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.search_trainee_btn).setOnClickListener {
            val nextIntent = Intent(this, SearchTraineeActivity::class.java)
            startActivity(nextIntent)
        }
        findViewById<Button>(R.id.search_trainer_btn).setOnClickListener {
            val nextIntent = Intent(this, SearchTrainerActivity::class.java)
            startActivity(nextIntent)
        }
        findViewById<Button>(R.id.mypage_btn).setOnClickListener {
            val nextIntent = Intent(this, MypageActivity::class.java)
            startActivity(nextIntent)
        }
        findViewById<Button>(R.id.chat_btn).setOnClickListener {
            val nextIntent = Intent(this, ChatActivity::class.java)
            startActivity(nextIntent)
        }
        findViewById<Button>(R.id.logout_btn).setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val nextIntent = Intent(this, LoginActivity::class.java)
            startActivity(nextIntent)
        }
    }
}