package com.example.coderem.profiles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.coderem.ProfileViewModel
import com.example.coderem.ProfileViewModelFactory
import com.example.coderem.database.database1.User
import com.example.coderem.database.database1.UserViewModel
import com.example.coderem.databinding.FragmentCodechefBinding
import retrofit2.HttpException


class codechef : Fragment() {

    private lateinit var vm: UserViewModel
    lateinit var pvm:ProfileViewModel
    lateinit var viewModelFactory: ProfileViewModelFactory
    lateinit var binding: FragmentCodechefBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentCodechefBinding.inflate(inflater,container,false)
        vm = ViewModelProvider(this)[UserViewModel::class.java]



        binding.ccbtn.setOnClickListener {
           if(binding.cctext.text.toString().trim().isEmpty()) {


           }
            else{
               viewModelFactory = ProfileViewModelFactory(binding.cctext.text.toString().trim())
               pvm = ViewModelProvider(this, viewModelFactory)[ProfileViewModel::class.java]
               try {
                   pvm.getCcStatus(binding.cctext.text.toString().trim())
                   pvm.ccResponse.observe(viewLifecycleOwner, Observer {
                       if (it.status.toString() == "Success") {

                           Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_LONG).show()
                           val user = User(0, "CodeChef", binding.cctext.text.toString().trim())

                           vm.addUser(user)
                       } else {
                          // binding.cclayout.error = "Invalid ID"
                       }
                   })
               }
               catch (e:HttpException){
                   Toast.makeText(context,e.toString(),Toast.LENGTH_LONG).show()
               }

           }

        }
        return binding.root
    }
}