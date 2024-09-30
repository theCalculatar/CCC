package com.example.ccc.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.ccc.R
import com.example.ccc.login.Login

class ProfileFragment : Fragment() {

    private  val  viewModel: ProfileViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<CardView>(R.id.edit_profile)
            .setOnClickListener {
//                dismiss()
                EditProfileFragment().show(childFragmentManager,"edit profile")
            }

        view.findViewById<CardView>(R.id.support)
            .setOnClickListener {
                findNavController().navigate(R.id.navigation_general)
            }

        view.findViewById<CardView>(R.id.terms_of_use)
            .setOnClickListener {
                findNavController().navigate(R.id.navigation_t_o_u)
            }

        view.findViewById<TextView>(R.id.sign_out).setOnClickListener {
            viewModel.signOut()
            activity?.finish()
            startActivity(Intent(requireContext(), Login::class.java))
        }
        viewModel.updateProfile()
        viewModel.profile.observe(viewLifecycleOwner) {
             it.firstName.also { name-> view.findViewById<TextView>(R.id.name_user).text = name }
            view.findViewById<TextView>(R.id.email).text = it.email
            Glide.with(this)
                 .load(it.picture)
                 .into(view.findViewById(R.id.profile_pic))
         }
    }

}