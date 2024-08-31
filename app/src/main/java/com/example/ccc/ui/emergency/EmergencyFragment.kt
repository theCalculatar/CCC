package com.example.ccc.ui.emergency

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ccc.R
import com.example.ccc.model.EmergencyModel
import com.example.ccc.ui.emergency.adapter.EmergencyAdapter


class EmergencyFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_emergency, container, false)

        val recycler = view.findViewById<RecyclerView>(R.id.emergency_recycler)
        recycler.layoutManager = LinearLayoutManager(requireContext())

        val emergencyContacts = ArrayList<EmergencyModel>().apply {
            add(EmergencyModel("Campus Control","0152682823"))
            add(EmergencyModel("Mankweng Police Station","0152682468"))
            add(EmergencyModel("Health Centre","0153683502"))
        }

        val adapter = EmergencyAdapter(requireContext(),emergencyContacts)
        recycler.adapter = adapter

        adapter.onClick = { number->
            val intent = Intent(
                Intent.ACTION_CALL,
                Uri.parse("tel:$number")
            )
            startActivity(intent)
        }

        return view
    }

}