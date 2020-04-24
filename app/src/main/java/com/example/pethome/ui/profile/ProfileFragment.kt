package com.example.pethome.ui.profile

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.pethome.Activity.FavorActivity
import com.example.pethome.Data.User
import com.example.pethome.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import kotlinx.android.synthetic.main.activity_petinfo.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlin.concurrent.fixedRateTimer

public var user: FirebaseUser? = null

class ProfileFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var userDB: FirebaseFirestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()

        userDB = FirebaseFirestore.getInstance()
        val settings = FirebaseFirestoreSettings.Builder()
            .setTimestampsInSnapshotsEnabled(true)
            .build()
        userDB.firestoreSettings = settings

        updateUI(user)

        signupBtn.setOnClickListener {
            val username = inputUser.text.toString()
            val email = emailAdrReg.text.toString()
            val password = passwordReg.text.toString()

            if (username!=""&&email!=""&&password!="") {
                createAccount(username,email,password)
            } else {
                Toast.makeText(this.context, "Invalid Value", Toast.LENGTH_SHORT).show()
            }
        }

        loginBtn.setOnClickListener {
            val username = inputUser.text.toString()
            val email = emailAdrReg.text.toString()
            val password = passwordReg.text.toString()

            if (email=="") {
                Toast.makeText(this.context,"Invalid Email", Toast.LENGTH_SHORT).show()
            } else if (password=="") {
                Toast.makeText(this.context,"Invalid Password", Toast.LENGTH_SHORT).show()
            } else {
                logIn(email,password)
            }
        }

        logoutBtn.setOnClickListener {
            auth.signOut()
            user=null
            updateUI(user)
        }

        viewFavorBtn.setOnClickListener {
            val intent = Intent(this.context, FavorActivity::class.java)
            startActivity(intent)
        }
    }

    // login function using firebase authentication
    private fun logIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("success", "login success")
                    user = auth.currentUser

                    updateUI(user)
                } else {
                    Toast.makeText(this.context, "Authentication failed.", Toast.LENGTH_SHORT).show()
                }
            }
    }

    // create account using firebase authentication and insert the data into database
    private fun createAccount(username: String, email: String, password: String) {

        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("success", "createUserSuccess")
                    user = auth.currentUser
                    if (user != null) {
                        addAccount(username, email, user!!.uid)
                    }
                    updateUI(user)
                } else {
                    Toast.makeText(this.context, "Authentication failed.", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun addAccount(username: String, email: String, uid: String) {
        val account = User(username, email, uid)

        val accountMap: MutableMap<String, Any> = HashMap()

        accountMap["username"] = account.username
        accountMap["email"] = account.email
        accountMap["uid"] = account.uid

        userDB.collection("accounts").document(account.uid)
            .set(accountMap)
            .addOnSuccessListener { doucument ->
                Toast.makeText(this.context, "Account Created", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e->
                Toast.makeText(this.context,"Fail to Create Account", Toast.LENGTH_SHORT).show()
            }
    }

    private fun updateUI(user: FirebaseUser?) {
        var username = ""

        if (user!=null) {
            userDB.collection("accounts")
                .whereEqualTo("uid", user.uid)
                .get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        for (document in task.result!!) {
                            username = document.get("username").toString()
                        }
                        showUser(username)
                        Log.e("username", username)

                    }
                }
        } else {
            showProfile()
        }
    }

    private fun showUser(username: String) {
        inputUser.visibility = ViewGroup.INVISIBLE
        emailAdrReg.visibility = ViewGroup.INVISIBLE
        passwordReg.visibility = ViewGroup.INVISIBLE
        loginBtn.visibility = ViewGroup.INVISIBLE
        signupBtn.visibility = ViewGroup.INVISIBLE

        userTitle.visibility = ViewGroup.VISIBLE
        userTitle.text = "Hello "+ username + "!"
        userImage.visibility = ViewGroup.VISIBLE
        logoutBtn.visibility = ViewGroup.VISIBLE
        viewFavorBtn.visibility = ViewGroup.VISIBLE
    }

    private fun showProfile() {
        inputUser.visibility = ViewGroup.VISIBLE
        inputUser.setText("")
        emailAdrReg.visibility = ViewGroup.VISIBLE
        emailAdrReg.setText("")
        passwordReg.visibility = ViewGroup.VISIBLE
        passwordReg.setText("")
        loginBtn.visibility = ViewGroup.VISIBLE
        signupBtn.visibility = ViewGroup.VISIBLE

        userTitle.visibility = ViewGroup.INVISIBLE
        userImage.visibility = ViewGroup.INVISIBLE
        logoutBtn.visibility = ViewGroup.INVISIBLE
        viewFavorBtn.visibility = ViewGroup.INVISIBLE
    }

}