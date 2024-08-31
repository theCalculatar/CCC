package com.example.ccc.ui.crime

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.example.ccc.R
import com.example.ccc.login.User
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class ReportCrimeFragment: BottomSheetDialogFragment(),AdapterView.OnItemSelectedListener {

    private lateinit var userName:String
    private var description = ""
    private lateinit var type:String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root =  inflater.inflate(R.layout.fragment_report_crime, container, false)
        val submitButton = root.findViewById<MaterialButton>(R.id.report_theft)
        val spinner = root.findViewById<Spinner>(R.id.type_spinner)
        val dropOffEditText = root.findViewById<EditText>(R.id.drop_off)
        val userId = FirebaseAuth.getInstance().currentUser!!.uid

        Firebase.firestore.collection("Users")
            .document(userId).addSnapshotListener { value, error ->
                if (value != null) {
                    Log.d("lee", value.data?.values.toString())
                }
                val userProfile = value?.toObject(User::class.java)
                if (userProfile != null) {
                    userName = userProfile.firstName
                }
            }

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.crime_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }
        spinner.onItemSelectedListener  = this

        submitButton.setOnClickListener {
            val dbRef = Firebase.firestore.collection("crime")
            description = dropOffEditText.text.toString()
            dbRef.add(hashMapOf(
                "type" to type,
                "userId" to userId,
                "userName" to userName,
                "description" to description
            ))
            Toast.makeText(requireContext(),"Reporting...", Toast.LENGTH_SHORT)
                .show()
            dismiss()
        }
        return root
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        type = p0?.getItemAtPosition(p2) as String
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}