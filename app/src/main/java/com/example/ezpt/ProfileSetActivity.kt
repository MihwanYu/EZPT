package com.example.ezpt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import com.example.ezpt.data.Users
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_profile_set.*
import kotlinx.android.synthetic.main.activity_profile_set.view.*

class ProfileSetActivity : AppCompatActivity() {
    var firestore : FirebaseFirestore? = null
    var auth: FirebaseAuth? = null
    var currentUserEmail : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_set)

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()
        currentUserEmail = auth?.currentUser?.email!!

        findViewById<Button>(R.id.done_btn).setOnClickListener {
            val nextIntent = Intent(this, MainActivity::class.java)
            startActivity(nextIntent)
            updateSetting()
        }
    }

    fun updateSetting() {
        var username = username_edittext.text.toString()
        var phoneNum = phone_edittext.text.toString()
        val radio: RadioButton = findViewById(userType.checkedRadioButtonId)
        val usertype = radio.text.toString()
        val radio2: RadioButton = findViewById(sex.checkedRadioButtonId)
        val sex = radio2.text.toString()
        var region = preferredRegion.text.toString()
        var description = selfDescriptionText.text.toString()

        createData(username,phoneNum,currentUserEmail,usertype,sex,region,description)
    }

     fun createData(username: String, phoneNum: String, email: String, userType: String, sex: String, region: String, description: String) {
        val user = Users(username, phoneNum, email, userType, sex, region, description)
         firestore?.collection("Users")?.document()?.set(user)?.
         addOnCompleteListener{
                 task ->
                 if(task.isSuccessful) {
                    Toast.makeText(
                        this@ProfileSetActivity,
                        "update !",
                        Toast.LENGTH_SHORT
                    ).show()
             }
         }
    }
}