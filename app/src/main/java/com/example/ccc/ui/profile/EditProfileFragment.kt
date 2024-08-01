package com.example.ccc.ui.profile

import android.app.Activity.RESULT_OK
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.ccc.R
import com.google.android.gms.tasks.Task
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.storage.FirebaseStorage
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class EditProfileFragment : BottomSheetDialogFragment() {

    private var isPictureSelected = true
    private lateinit var imageUri : Uri
    private lateinit var profile :ImageView

    private val profileViewModel: ProfileViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_edit_profile, container, false)
        profile = root.findViewById(R.id.profile_pic)
        val firstName = root.findViewById<EditText>(R.id.firstName)
        val phone = root.findViewById<EditText>(R.id.phone_number)

        //selecting image
        root.findViewById<ImageView>(R.id.select_pic).
            setOnClickListener {
                selectImage()
            }

        profileViewModel.profile.observe(viewLifecycleOwner){
            Glide.with(requireContext())
                .load(it?.picture)
                .into(profile)
            firstName.setText(it?.firstName,TextView.BufferType.EDITABLE)
            phone.setText(it?.phoneNumber,TextView.BufferType.EDITABLE)
        }

        firstName.afterTextChanged {
            if (it.isNotEmpty()) {
                profileViewModel.updateFirstName(it)
            }
        }
        phone.afterTextChanged {
            if (it.isNotEmpty()) {
                profileViewModel.updatePhone(it)
            }
        }

        return root
    }
    private fun selectImage (){
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1)
    }
    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1 && data != null && resultCode == RESULT_OK) {
            isPictureSelected = true
            imageUri = data.data!!

            uploadPicture()

            Glide.with(this)
                .load(imageUri)
                .placeholder(R.drawable.ic_baseline_person_24)
                .into(profile)
        }

        }

    private fun uploadPicture(){
            val formatter = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault())
            val now = Date()
            val fileName = formatter.format(now)

            val storageReference = FirebaseStorage.getInstance().getReference("images/$fileName")

            if (isPictureSelected){
                storageReference.putFile(imageUri).addOnSuccessListener{ taskSnapshot->
                    val uriTask: Task<Uri> = taskSnapshot.storage.downloadUrl

                    while (!uriTask.isComplete);
                    val urlSong: Uri = uriTask.result
                    val picReferenceFromDb = urlSong.toString()
                    profileViewModel.updatePictureRef(picReferenceFromDb)
                }
        }

    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        profileViewModel.updateProfile()
    }

//    override fun getTheme(): Int {
//        return R.style.RoundedCornersBottomDialog
//    }
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