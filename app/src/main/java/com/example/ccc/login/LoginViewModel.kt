package com.example.ccc.login
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ccc.R
import com.example.medinow.login.RegisteringFormState


class LoginViewModel:ViewModel() {


    private val _registrationForm = MutableLiveData<RegisteringFormState>()
    val registrationForm: LiveData<RegisteringFormState> = _registrationForm


//    private val _loginResult = MutableLiveData<LoginResult>()
//
//    val loginResult: LiveData<LoginResult> = _loginResult

//    private val _userDetails = MutableLiveData<User>()
//
//    val userDetails: LiveData<User> = _userDetails
//    suspend fun register(username: String, password: String, name: String):Result<User>{
//        // TODO: handle registration
//        return try {
//            val db = Firebase.firestore
//            val mAuth = FirebaseAuth.getInstance()
//            mAuth.createUserWithEmailAndPassword(username, password)
//                .addOnCompleteListener { task ->
//                    val user = mAuth.currentUser
//                    if (task.isSuccessful) {
//                        realUser = LoggedInUser(user.uid, name)
//                    }
//                    else {
//                        // If sign in fails, display a message to the user.
//                        Log.d("lee", "createUserWithEmail:failure", task.exception)
//                    }
//                }.await()
//            Result.Success(realUser!!)
//
//        } catch (e: Throwable){
//            Result.Error(IOException("Error logging in", e))
//        }
//    }
//    suspend fun register(username: String, password: String,name: String,mAuth: FirebaseAuth) {
//        // can be launched in a separate asynchronous job
//        val result = loginRepository.register(username, password,name,mAuth)
//        if (result is Result.Success) {
//            _loginResult.value =
//                LoginResult(success = LoggedInUserView(displayName = result.data.displayName!!))
//            // LoginDataSource().userDetails?.let { appDatabase.userDao().insertNew(it) }
//        } else {
//            _loginResult.value = LoginResult(error = R.string.registration_failed)
//        }
//    }

    fun registrationDataChanged(
        username: String,
        email: String,
        password: String,
        confirmationPassword: String
    ) {
        if (!isNameValid(username)) {
            _registrationForm.value =
                RegisteringFormState(firstNameError = R.string.invalid_name)
        } else if (!isUserNameValid(email)) {
            _registrationForm.value =
                RegisteringFormState(emailError = R.string.invalid_email)
        } else if (!isPasswordValid(password)) {
            _registrationForm.value =
                RegisteringFormState(passwordError = R.string.invalid_password)
        } else {
            _registrationForm.value = RegisteringFormState(isDataValid = true)
        }

        Log.d("confirm", "${doesPasswordMatch(password, confirmationPassword)}")
    }

    // A placeholder name validation check
    private fun isNameValid(name: String): Boolean {
        return if (name.contains("[0-9!\"#$%&'()*+,-./:;\\\\<=>?@\\[\\]^_`{|}~]".toRegex())) {
            false
        } else !(name.isEmpty() || name.isBlank()) && name.length>3
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            emailValidation(username)
        } else {
            false
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 6 &&
                password.contains("[\"#$%&'()*+,-./:;\\\\<=>?@\\[\\]^_`{|}~]".toRegex())
    }

    // A placeholder password validation check
    private fun doesPasswordMatch(password: String, confirmationPassword: String): Boolean {
        return confirmationPassword == password
    }

    private fun emailValidation(email: String): Boolean {
        val emailParts = email.split("@")
        val domain = "keyaka.ul.ac.za"
        return if (email.isEmpty()) {
            false
        } else if (emailParts[1] != domain) {
            false
        }else !(emailParts[0].contains("[\"![A-Za-z]#$%&'()*+,-./:;\\\\<=>?@\\[\\]^_`{|}~]".toRegex()))
                && emailParts[0].length == 9
    }
}

