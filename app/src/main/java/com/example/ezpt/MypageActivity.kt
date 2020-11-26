package com.example.ezpt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.ezpt.data.Relationships
import com.example.ezpt.data.Users
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_trainee_mypage.*

class MypageActivity : AppCompatActivity() {
    var firestore: FirebaseFirestore? = null
    var auth: FirebaseAuth? = null
    var currentUserEmail: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trainee_mypage)

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()
        currentUserEmail = auth?.currentUser?.email!!

        profiledataRW()
        partnerdataRW()

        findViewById<Button>(R.id.back_btn).setOnClickListener {
            val nextIntent = Intent(this, MainActivity::class.java)
            startActivity(nextIntent)
        }
    }

    fun profiledataRW() {
        firestore?.collection("Users")?.whereEqualTo("email", currentUserEmail)?.get()
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (dc in task.result!!.documents) {
                        var user = dc.toObject(Users::class.java)

                        profile_email.text = user!!.email.toString()
                        profile_username.text = user!!.username.toString()
                        profile_region.text = user!!.region.toString()
                        profile_description.text = user!!.description.toString()
                    }
                }
            }
    }

    fun partnerdataRW() {

        firestore?.collection("Partners")?.whereEqualTo("trainee", currentUserEmail)?.get()
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (dc in task.result!!.documents) {
                        var partner = dc.toObject(Relationships::class.java)

                        partner_email.text = partner!!.trainer.toString()
                        firestore?.collection("Users")?.whereEqualTo("email", partner!!.trainer.toString())?.get()
                            ?.addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    for (dc in task.result!!.documents) {
                                        var partnerInfo = dc.toObject(Users::class.java)
                                        partner_username.text = partnerInfo!!.username.toString()
                                        partner_phoneNum.text = partnerInfo!!.phoneNum.toString()
                                        partner_region.text = partnerInfo!!.region.toString()
                                        partner_description.text = partnerInfo!!.description.toString()
                                    }
                                }
                            }
                    }
                }
            }

        firestore?.collection("Partners")?.whereEqualTo("trainer", currentUserEmail)?.get()
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (dc in task.result!!.documents) {
                        var partner = dc.toObject(Relationships::class.java)
                        partner_email.text = partner!!.trainee.toString()
                        firestore?.collection("Users")?.whereEqualTo("email", partner!!.trainee.toString())?.get()
                            ?.addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    for (dc in task.result!!.documents) {
                                        var partnerInfo = dc.toObject(Users::class.java)
                                        partner_username.text = partnerInfo!!.username.toString()
                                        partner_phoneNum.text = partnerInfo!!.phoneNum.toString()
                                        partner_region.text = partnerInfo!!.region.toString()
                                        partner_description.text = partnerInfo!!.description.toString()
                                    }
                                } else
                                    partner_email.text = "You don't have a partner yet."
                            }
                    }
                }
            }
    }
}



