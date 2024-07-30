package com.example.ccc.ui.report;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ccc.adapter.LostItemAdapter
import com.example.ccc.databinding.FragmentLostandfoundBinding
import com.example.ccc.model.ReportModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject

class ReportFragment: Fragment() {

    private var _binding: FragmentLostandfoundBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLostandfoundBinding.inflate(inflater, container, false);
        val root = binding.root

        val lFRecycler = binding.lostProductsRecycler
        val userId = FirebaseAuth.getInstance().currentUser!!.uid

        binding.addLostItem.setOnClickListener {
            val onClick = AddLostItemFragment()
            onClick.show(childFragmentManager,"onClickFrag")
        }

        lFRecycler.layoutManager = LinearLayoutManager(this.requireContext())

        getLostItems().observe(viewLifecycleOwner){

            val adapter = LostItemAdapter(this.requireContext(),it)
            lFRecycler.adapter = adapter

            adapter.onClick = { model->
                val onClick = ViewLostItemCardFragment(model,userId)
                onClick.show(childFragmentManager,"onClickFrag")
            }
        }
        return root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null;

    }
    private fun getLostItems(): LiveData<ArrayList<ReportModel>> {

        val dbRef = FirebaseFirestore.getInstance()
        val blogs: MutableLiveData<ArrayList<ReportModel>> = MutableLiveData()

        dbRef.collection("reports")
            .addSnapshotListener { result ,err ->
                val arrayList = ArrayList<ReportModel>()
                if (result != null) {
                    for (document in result) {
                        val obj = document.toObject<ReportModel>()
                        obj.postId = document.id
                        arrayList.add(obj)
                    }
                }
                blogs.postValue(arrayList)
            }
        return blogs
    }
}