package com.example.ccc.login

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import com.example.ccc.R
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException


class ForgotPassword:AppCompatActivity() {

    private lateinit var userEmail: EditText
    private lateinit var progressBar: ProgressBar
    private lateinit var send: MaterialButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.forgort_password)

        send = findViewById(R.id.send)
        progressBar = findViewById(R.id.progress_bar)
        userEmail = findViewById(R.id.email)

        send.setOnClickListener {
            if(emailValidation()){
                genPasswordLink(email = userEmail.text.toString())
            }
        }
    }
    //checks if user is a students
    private fun emailValidation(): Boolean {
        val email: String = userEmail.text.toString()
        return if (email.isEmpty()) {
            userEmail.error = "Email Required*"
            false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            userEmail.error = "Please provide valid email*"
            false
        } else {
            userEmail.error = null
            true
        }
    }

    private fun genPasswordLink(email:String){
        progressBar.isVisible = true
        send.isVisible = false

        try {
            val mAuth = FirebaseAuth.getInstance()

            mAuth.sendPasswordResetEmail(email).addOnSuccessListener {
                progressBar.isVisible = false
                send.isVisible = true
                val fragSuccess = RestPasswordSuccess()

                fragSuccess.show(supportFragmentManager,"success")
            }
            // Construct email verification template, embed the link and send
            // using custom SMTP server.
        } catch (e: FirebaseAuthException) {
            progressBar.isVisible = true
            send.isVisible = false
            println("Error generating email link: " + e.message)
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this.applicationContext, Login::class.java))
        finish()
    }

}

class RestPasswordSuccess: DialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =inflater.inflate(R.layout.rest_password_success, container, false)
        view.findViewById<MaterialButton>(R.id.login).
            setOnClickListener {
                dismiss()
            }
        return view
    }
    override fun getTheme() = R.style.RoundedCornersDialog
    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        startActivity(Intent(this.context,Login::class.java))
        dismiss()
        activity?.finish()
    }
    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.80).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.45).toInt()
        dialog!!.window?.setLayout(width,height)
    }

}