package com.example.ccc.ui.crime

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.example.ccc.R
import com.example.ccc.model.Crime
import com.example.ccc.model.ReportModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class ViewCrimeFragment(private val lostItemsModel: Crime, private val userId: String) : BottomSheetDialogFragment() {

    private lateinit var deleteButton:ImageView

        override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root =  inflater.inflate(R.layout.fragment_view_crimes, container, false)

        deleteButton = root.findViewById(R.id.delete)
        val display = root.findViewById<ImageView>(R.id.lost_item_pic)
        val name = root.findViewById<TextView>(R.id.name)
        val dropOff = root.findViewById<TextView>(R.id.drop_off)
        val item = root.findViewById<TextView>(R.id.item)
        //db ref
        val dbRef = FirebaseFirestore.getInstance()

        name.text = lostItemsModel.userName
        dropOff.text = lostItemsModel.description
        item.text = lostItemsModel.type

        dbRef.collection("Users").document(lostItemsModel.userId).get().addOnSuccessListener { document->
            Glide.with(requireContext())
                .load(document.data?.get("picture"))
                .into(display)
        }
        initialize(userId)

        deleteButton.setOnClickListener {
            deletePost()
        }
        return root
    }
    private fun deletePost(){
        val dbRef = Firebase.firestore.collection("crime")
        dbRef.document(lostItemsModel.postId!!).delete()
            .addOnSuccessListener {
                Toast.makeText(requireContext(),"Deleted!",Toast.LENGTH_SHORT)
                    .show()
                dismissNow()
            }
    }
    private fun initialize(userId:String){
        if (lostItemsModel.userId == userId){
            deleteButton.isVisible = true
        }
    }

}