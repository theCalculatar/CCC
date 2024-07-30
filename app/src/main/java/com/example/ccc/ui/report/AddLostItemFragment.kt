package com.example.ccc.ui.report

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.example.ccc.R
import com.example.ccc.login.LoggedInUserView
import com.example.ccc.login.User
import com.example.ccc.model.ReportModel
import com.google.android.gms.tasks.Task
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import org.json.JSONStringer
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class AddLostItemFragment: BottomSheetDialogFragment(),AdapterView.OnItemSelectedListener {

    private var isPictureSelected = false
    private lateinit var imageUri: Uri
    private lateinit var imageView:ImageView
    private lateinit var userName:String
    private var dropOff = ""
    private lateinit var email:String
    private lateinit var type:String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root =  inflater.inflate(R.layout.fragment_add_lost_item, container, false)
        val submitButton = root.findViewById<Button>(R.id.submit)
        val spinner = root.findViewById<Spinner>(R.id.type_spinner)
        val dropOffEditText = root.findViewById<EditText>(R.id.drop_off)
        imageView = root.findViewById(R.id.lost_item_pic)
        val userId = FirebaseAuth.getInstance().currentUser!!.uid

        Firebase.firestore.collection("Users")
            .document(userId).addSnapshotListener { value, error ->
                if (value != null) {
                    Log.d("lee", value.data?.values.toString())
                }
                val userProfile = value?.toObject(User::class.java)
                if (userProfile != null) {
                    userName = userProfile.firstName
                    email = userProfile.email
                }
            }
        
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.lost_type_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }
        spinner.onItemSelectedListener  = this
        //get current user

        imageView.setOnClickListener {
            selectImage()
        }

        submitButton.setOnClickListener {

            if (isPictureSelected){
                uploadLostItem(userId)
                dropOff = dropOffEditText.text.toString()

                Toast.makeText(requireContext(),"Uploading", Toast.LENGTH_SHORT)
                    .show()
                dismiss()
            }else {
                Toast.makeText(requireContext(), "Select a picture", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        return root
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && data != null && resultCode == AppCompatActivity.RESULT_OK) {
            //TODO: action
            isPictureSelected = true
            imageUri = data.data!!

            Glide.with(this)
                .load(imageUri)
                .placeholder(R.drawable.baseline_image_24)
                .into(imageView)
        }
    }
    private fun selectImage (){
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1)
    }
    private fun uploadLostItem(userId:String){
        val dbRef = Firebase.firestore.collection("reports")
        val storageRef = FirebaseStorage.getInstance()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        val current = LocalDateTime.now().format(formatter)
        var imageUrl = ""
        storageRef.reference.child("pictures")
            .child(current)
            .putFile(imageUri)
            .addOnSuccessListener { taskSnapshot ->
                val uriTask: Task<Uri> = taskSnapshot.storage.downloadUrl
                while (!uriTask.isComplete);
                val urlSong: Uri = uriTask.result
                imageUrl = urlSong.toString()
            }
            .addOnCompleteListener {
                dbRef.add(ReportModel(imageUrl, type, userId, dropOff, userName,email))
            }
    }
    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        type = p0?.getItemAtPosition(p2) as String
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}