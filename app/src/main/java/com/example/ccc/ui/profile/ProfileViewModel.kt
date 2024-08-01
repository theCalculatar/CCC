package com.example.ccc.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ccc.login.User
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class ProfileViewModel : ViewModel() {
    private val dbRef = Firebase.firestore
    private val userId = FirebaseAuth.getInstance().currentUser?.uid
    private val _profile: MutableLiveData<User> = MutableLiveData()
    val profile:LiveData<User> = _profile

    fun updateProfile(){
        dbRef.collection("Users")
            .document(userId!!)
            .get().addOnCompleteListener {
                if (it.isSuccessful) _profile.postValue(it.result.toObject())
            }
    }

    fun signOut() {
        FirebaseAuth.getInstance().signOut()
    }
    fun updatePictureRef(picReferenceFromDb: String): Task<Void> {
        return dbRef.collection("Users").document(userId!!)
            .set(hashMapOf("picture" to picReferenceFromDb), SetOptions.merge())

    }
    fun updateFirstName(name:String) {
        dbRef.collection("Users").document(userId!!)
            .set(hashMapOf("firstName" to name), SetOptions.merge())
    }

    fun updatePhone(name:String) {
        dbRef.collection("Users").document(userId!!)
            .set(hashMapOf("phoneNumber" to name), SetOptions.merge())
    }
}
