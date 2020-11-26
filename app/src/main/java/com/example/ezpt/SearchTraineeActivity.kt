package com.example.ezpt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ezpt.data.Users
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_search_trainee.*
import kotlinx.android.synthetic.main.item_recycler.view.*

class SearchTraineeActivity : AppCompatActivity() {
    var firestore : FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_trainee)
        firestore = FirebaseFirestore.getInstance()

        dataRW()

        findViewById<Button>(R.id.back_btn).setOnClickListener {
            val nextIntent = Intent(this, MainActivity::class.java)
            startActivity(nextIntent)
        }
        findViewById<Button>(R.id.contact_btn).setOnClickListener {
            val nextIntent = Intent(this, ChatActivity::class.java)
            startActivity(nextIntent)
        }
    }

    fun dataRW() {
        var data: MutableList<user>
        val adapter = RecyclerAdapter()
        var dat: MutableList<user> = mutableListOf()
        firestore?.collection("Users")?.whereEqualTo("userType","Trainee")?.get()?.addOnSuccessListener { result ->
            for (document in result) {
                var oneData = document.toObject(user::class.java)
                dat.add(oneData)
            }
            data = dat
            adapter?.listData = data
            trainee_recycler?.adapter = adapter
            trainee_recycler?.layoutManager = LinearLayoutManager(this)
        }
    }
}