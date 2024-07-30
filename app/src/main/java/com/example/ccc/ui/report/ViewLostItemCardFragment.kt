package com.example.ccc.ui.report

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.example.ccc.R
import com.example.ccc.model.ReportModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class ViewLostItemCardFragment(private val lostItemsModel: ReportModel, private val userId: String) : BottomSheetDialogFragment() {

    private lateinit var deleteButton:ImageView

        override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root =  inflater.inflate(R.layout.fragment_view_lost_item_card, container, false)

        deleteButton = root.findViewById(R.id.delete)
        val display = root.findViewById<ImageView>(R.id.lost_item_pic)
        val name = root.findViewById<TextView>(R.id.name)
        val email = root.findViewById<TextView>(R.id.email)
        val dropOff = root.findViewById<TextView>(R.id.drop_off)
        val item = root.findViewById<TextView>(R.id.item)

        name.text = lostItemsModel.userName
        lostItemsModel.number.let {
            email.text = it
            }
        dropOff.text = lostItemsModel.dropOff
        item.text = lostItemsModel.type

        Glide.with(requireContext())
            .load(lostItemsModel.display)
            .into(display)
        initialize(userId)

        deleteButton.setOnClickListener {
            deletePost()
        }
        return root
    }
    private fun deletePost(){
        val dbRef = Firebase.firestore.collection("reports")
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