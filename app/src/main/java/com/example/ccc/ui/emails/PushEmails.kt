package com.example.ccc.ui.emails

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.RadioGroup
import android.widget.RadioGroup.OnCheckedChangeListener
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.ccc.R
import com.example.ccc.network.EmailService
import com.example.ccc.network.model.EmailModal
import com.example.ccc.ui.profile.ProfileFragment
import com.example.ccc.ui.profile.ProfileViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PushEmails:Fragment() {
    private  val  viewModel: ProfileViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val view = inflater.inflate(R.layout.fragment_push_emails,container,false)

        val emailService = EmailService()

        var email = ""
        var name = ""
        var reqType = "student card"
        var phone = ""
        var address = ""
        var isDevice = false

        val requestChecker = view.findViewById<RadioGroup>(R.id.email_type)
        val aditionals = view.findViewById<LinearLayout>(R.id.aditionals)
        val sendEmail = view.findViewById<TextView>(R.id.submit)
        val deviceEdit = view.findViewById<EditText>(R.id.device_name)
        val snEdit = view.findViewById<EditText>(R.id.seria_number)
        val addressEdit = view.findViewById<EditText>(R.id.address_)

        val progressBar = view.findViewById<ProgressBar>(R.id.progress_bar)

        viewModel.updateProfile()
        viewModel.profile.observe(viewLifecycleOwner) {
            it.firstName.also { _name->
                view.findViewById<TextView>(R.id.name_user).text ="Hi, "+ _name
                name = _name
                phone = it.phoneNumber
             }
            email = it.email
        }
        requestChecker.setOnCheckedChangeListener { p0, p1 ->
            when (p0?.checkedRadioButtonId) {
                R.id.student_card -> {
                    aditionals.isVisible = false
                    isDevice = false
                    reqType = "student card"
                }

                else -> {
                    isDevice = true
                    aditionals.isVisible = true
                }
            }
        }

        sendEmail.setOnClickListener {
            Log.d("lee",isDevice.toString())
            if (isDevice){
                if (snEdit.text.toString().trim() == ""){
                    Toast.makeText(requireContext(),"Enter Serial number",Toast.LENGTH_SHORT)
                        .show()
                    return@setOnClickListener
                }
                if (deviceEdit.text.toString().trim() == ""){
                    Toast.makeText(requireContext(),"Enter device name",Toast.LENGTH_SHORT)
                        .show()
                    return@setOnClickListener
                }
                if (addressEdit.text.toString().trim() == ""){
                    Toast.makeText(requireContext(),"Enter address",Toast.LENGTH_SHORT)
                        .show()
                    return@setOnClickListener
                }
                progressBar.isVisible = true
                emailService.emailService()
                    .sendEmail(EmailModal(name,email,"pass out",snEdit.text.toString(),deviceEdit.text.toString(),phone,addressEdit.text.toString()))
                    .enqueue(object :Callback<String>{
                        override fun onResponse(
                            call: Call<String>,
                            response: Response< String>
                        ) {
//                            Log.d("leeSucces",response.message())
                            progressBar.isVisible = false
                            if (response.isSuccessful){
                                Toast.makeText(requireContext(),"Successfully Requested",Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                        override fun onFailure(call: Call< String>, t: Throwable) {
                            progressBar.isVisible = false
                            Toast.makeText(requireContext(),"Successfully Requested",Toast.LENGTH_SHORT)
                                .show()
                            activity?.onBackPressed()

                        }
                    })
            }else{
                progressBar.isVisible = true
                emailService.emailService()
                    .sendEmail(EmailModal(name,email,reqType,null,null,phone))
                    .enqueue(object :Callback<String>{
                        override fun onResponse(
                            call: Call< String>,
                            response: Response< String>
                        ) {
                            Log.d("here","loctaiomn")

                            progressBar.isVisible = false
                            if (response.isSuccessful){
                                Toast.makeText(requireContext(),"Successfully Requested",Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }

                        override fun onFailure(call: Call< String>, t: Throwable) {
                            progressBar.isVisible = false
                            Log.d("hebann",t.message.toString())

                            Toast.makeText(requireContext(),"Successfully Requested",Toast.LENGTH_SHORT)
                                .show()
                            activity?.onBackPressed()
                        }
                    })
            }

        }
        return view
    }
}