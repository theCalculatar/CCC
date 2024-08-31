package com.example.ccc.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.ccc.R
import com.example.ccc.databinding.FragmentHomeBinding
import com.example.ccc.ui.chat.InMessageView

class
HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val chat = binding.communicate

        val campus_map = binding.campusMap

        val report_theft = binding.reportTheft

        val emergency = binding.emergency

        emergency.setOnClickListener {
            findNavController().navigate(R.id.navigation_emergency)
        }

        campus_map.setOnClickListener {
            Toast.makeText(requireContext(), "Coming soon...",Toast.LENGTH_SHORT).show()
        }
        report_theft.setOnClickListener {
            findNavController().navigate(R.id.navigation_crime)
        }

        chat.setOnClickListener {
            val chatIntent = Intent(this.context, InMessageView::class.java)
            startActivity(chatIntent)
        }

//        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}