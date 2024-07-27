package com.example.ccc.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.Window
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.ccc.R
import com.google.android.gms.tasks.Task
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions


class Registering : AppCompatActivity() {
    private var mAuth: FirebaseAuth? = null
    private lateinit var progressBar: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_registering)

        val loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        mAuth = FirebaseAuth.getInstance()

        val editTextFirstName: EditText = findViewById(R.id.firstName)
        val editTextEmail: EditText = findViewById(R.id.email)
        val editTextPassword: EditText = findViewById(R.id.password)
        progressBar = findViewById(R.id.progress_bar)


        val registerUser = findViewById<MaterialButton>(R.id.sign_in_button)
        val signInUser = findViewById<TextView>(R.id.login)

        loginViewModel.registrationForm.observe(this) {
            val loginState = it

            // disable login button unless both username / password is valid
            registerUser.isEnabled = loginState.isDataValid

            if (loginState.firstNameError != null) {
                editTextFirstName.error = getString(loginState.firstNameError)
            }

            if (loginState.emailError != null) {
            editTextEmail.error = getString(loginState.emailError)
        }
            if (loginState.passwordError != null) {
                editTextPassword.error = getString(loginState.passwordError)
            }
        }
        editTextFirstName.afterTextChanged {
            loginViewModel.registrationDataChanged(
                it,
                editTextEmail.text.toString(),
                editTextPassword.text.toString(),
                editTextPassword.text.toString()
            )
        }
        editTextEmail.afterTextChanged {
            loginViewModel.registrationDataChanged(
                editTextFirstName.text.toString(),
                it,
                editTextPassword.text.toString(),
                editTextPassword.text.toString())
        }
        editTextPassword.afterTextChanged {
            loginViewModel.registrationDataChanged(
                editTextFirstName.text.toString(),
                editTextEmail.text.toString(),
                it,
                it)
        }

        registerUser.setOnClickListener {
            val firstName = editTextFirstName.text.toString().trim()
            val email = editTextEmail.text.toString().trim()
            val password = editTextPassword.text.toString().trim()
            val studentNumber = email.split("@")[0]

            it.visibility = View.GONE
            progressBar.isVisible = true

            mAuth!!.createUserWithEmailAndPassword(editTextEmail.text.toString().trim(), password)
                .addOnCompleteListener { task: Task<AuthResult?> ->
                    if (task.isSuccessful) {
                        val additional: MutableMap<String, Any> =
                            HashMap()
                        additional["dateOfBirth"] = "01 jan 2023"
                        additional["picture"] = ""
                        additional["studentNumber"] = studentNumber
                        val user =
                            User(firstName, email, password)

                        val db = FirebaseFirestore.getInstance()

                        db.collection("Users/")
                            .document(
                                    FirebaseAuth.getInstance().currentUser!!.uid
                            )
                            .set(user)
                            .addOnCompleteListener { task1: Task<Void?> ->
                                if (task1.isSuccessful) {
                                    mAuth!!.currentUser?.sendEmailVerification();
                                    db.collection("Users/")
                                        .document(mAuth!!.uid!!)[additional] = SetOptions.merge()
                                    Toast.makeText(
                                        this@Registering,
                                        "Verify your email!",
                                        Toast.LENGTH_SHORT
                                    )
                                        .show()
                                    progressBar.visibility = View.GONE
                                    val intent = Intent(this@Registering, Login::class.java)
                                    startActivity(intent)
                                    finish()
                                } else {
                                    Toast.makeText(
                                        this@Registering,
                                        task1.exception!!.message,
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    progressBar.visibility = View.GONE
                                    it.visibility = View.VISIBLE
                                }
                            }
                    } else {
                        Toast.makeText(
                            this@Registering,
                            task.exception!!.message,
                            Toast.LENGTH_SHORT
                        )
                            .show()
                        progressBar.visibility = View.GONE
                        it.visibility = View.VISIBLE
                    }
                }
        }

        signInUser.setOnClickListener {
            val intent = Intent(this@Registering, Login::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this@Registering, Login::class.java)
        startActivity(intent)
    }


    /**
     * Extension function to simplify setting an afterTextChanged action to EditText components.
     */
    private fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
        this.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(editable: Editable?) {
                afterTextChanged.invoke(editable.toString())
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })
    }
}