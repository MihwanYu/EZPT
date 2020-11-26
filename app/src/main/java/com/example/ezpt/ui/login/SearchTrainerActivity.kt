package com.example.ezpt.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ezpt.MainActivity
import com.example.ezpt.R
import com.example.ezpt.RecyclerAdapter
import com.example.ezpt.user
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_search_trainee.*
import kotlinx.android.synthetic.main.activity_search_trainer.*

class SearchTrainerActivity : AppCompatActivity() {
    var firestore : FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_trainer)

        firestore = FirebaseFirestore.getInstance()

        dataRW()

        findViewById<Button>(R.id.back_btn).setOnClickListener {
            val nextIntent = Intent(this, MainActivity::class.java)
            startActivity(nextIntent)
        }
    }
    fun dataRW() {
        var data: MutableList<user>
        val adapter = RecyclerAdapter()
        var dat: MutableList<user> = mutableListOf()
        firestore?.collection("Users")?.whereEqualTo("userType","Trainer")?.get()?.addOnSuccessListener { result ->
            for (document in result) {
                var oneData = document.toObject(user::class.java)
                dat.add(oneData)
            }
            data = dat
            adapter?.listData = data
            trainer_recycler?.adapter = adapter
            trainer_recycler?.layoutManager = LinearLayoutManager(this)
        }
    }
}