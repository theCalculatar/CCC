package com.example.ccc.ui.crime

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ccc.adapter.LostItemAdapter
import com.example.ccc.databinding.FragmentCrimeBinding
import com.example.ccc.databinding.FragmentLostandfoundBinding
import com.example.ccc.model.Crime
import com.example.ccc.model.ReportModel
import com.example.ccc.ui.crime.adapter.CrimeAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject

class CrimeFragment: Fragment() {

    private var _binding: FragmentCrimeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCrimeBinding.inflate(inflater, container, false);
        val root = binding.root

        val lFRecycler = binding.lostProductsRecycler
        val userId = FirebaseAuth.getInstance().currentUser!!.uid

        binding.addLostItem.setOnClickListener {
            val onClick = ReportCrimeFragment()
            onClick.show(childFragmentManager,"onClickFrag")
        }

        lFRecycler.layoutManager = LinearLayoutManager(this.requireContext())

        getCrimes().observe(viewLifecycleOwner){

            val adapter = CrimeAdapter(this.requireContext(),it)
            lFRecycler.adapter = adapter

            adapter.onClick = { model->
                val onClick = ViewCrimeFragment(model,userId)
                onClick.show(childFragmentManager,"onClickFrag")
            }
        }
        return root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null;

    }
    private fun getCrimes(): LiveData<ArrayList<Crime>> {

        val dbRef = FirebaseFirestore.getInstance()
        val blogs: MutableLiveData<ArrayList<Crime>> = MutableLiveData()

        dbRef.collection("crime")
            .addSnapshotListener { result, _ ->
                val arrayList = ArrayList<Crime>()
                if (result != null) {
                    for (document in result) {
                        val obj = document.toObject<Crime>()
                        obj.postId = document.id
                        arrayList.add(obj)
                    }
                }
                blogs.postValue(arrayList)
            }
        return blogs
    }
}