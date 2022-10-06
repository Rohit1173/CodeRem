package com.example.coderem.profiles

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.coderem.ProfileViewModel
import com.example.coderem.ProfileViewModelFactory
import com.example.coderem.R
import com.example.coderem.database.User
import com.example.coderem.database.UserViewModel
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


class codechef : Fragment() {

    private lateinit var vm: UserViewModel
    lateinit var cclayout:TextInputLayout
    lateinit var cctext:TextInputEditText
    lateinit var ccbtn:Button
    lateinit var pvm:ProfileViewModel
    lateinit var viewModelFactory: ProfileViewModelFactory
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v= inflater.inflate(R.layout.fragment_codechef, container, false)
        vm = ViewModelProvider(this)[UserViewModel::class.java]

        cclayout=v.findViewById(R.id.cclayout)
        cctext=v.findViewById(R.id.cctext)
        ccbtn=v.findViewById(R.id.ccbtn)
            cctext.doOnTextChanged { text, start, before, count ->
                if (text.toString().isNotEmpty()) {
                    cclayout.error = null
                }
            }

        ccbtn.setOnClickListener {
           if(cctext.text.toString().trim().isEmpty()) {
              cclayout.error="ID cannot be empty"

           }
            else{
               val user = User(0, "CodeChef", cctext.text.toString().trim())

               //Toast.makeText(requireContext(),"SUCCESS!!!",Toast.LENGTH_LONG).show()
               viewModelFactory = ProfileViewModelFactory(cctext.text.toString().trim())
               pvm = ViewModelProvider(this, viewModelFactory)[ProfileViewModel::class.java]
               pvm.getCcStatus(cctext.text.toString().trim())
               pvm.myResponse.observe(viewLifecycleOwner, Observer {
                   if(it.status.toString()=="Success") {
                       Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_LONG).show()
                       vm.addUser(user)
                   }
                   else{
                       cclayout.error="Invalid ID"
                   }
               })
           }

        }
        return v
    }
}