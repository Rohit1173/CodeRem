package com.example.coderem.profiles

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.coderem.ProfileViewModel
import com.example.coderem.ProfileViewModelFactory
import com.example.coderem.R
import com.example.coderem.database.database1.User
import com.example.coderem.database.database1.UserViewModel
import com.example.coderem.databinding.FragmentLeetcodeBinding

class leetcode : Fragment() {

    private lateinit var vm: UserViewModel
    lateinit var pvm: ProfileViewModel
    lateinit var viewModelFactory: ProfileViewModelFactory
    lateinit var binding: FragmentLeetcodeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentLeetcodeBinding.inflate(inflater,container,false)
        vm = ViewModelProvider(this)[UserViewModel::class.java]
        binding.lctext.doOnTextChanged { text, start, before, count ->
            if (text.toString().isNotEmpty()) {
                binding.lctext.background= ContextCompat.getDrawable(requireContext(),R.drawable.border)
                binding.lcErr.text = ""
            }
        }
        binding.lcbtn.setOnClickListener {
            if(binding.lctext.text.toString().trim().isEmpty()) {
                binding.lctext.background= ContextCompat.getDrawable(requireContext(), R.drawable.error_border)
                binding.lcErr.text="ID cannot be empty"

            }
            else {

                viewModelFactory = ProfileViewModelFactory(binding.lctext.text.toString().trim())
                pvm = ViewModelProvider(this, viewModelFactory)[ProfileViewModel::class.java]
                pvm.getLcStatus(binding.lctext.text.toString().trim())
                pvm.lcResponse.observe(viewLifecycleOwner, Observer {

                    if(it!=null) {
                        if (it.status == "Success") {
                            Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_LONG)
                                .show()
                            val user = User(0, "LeetCode", binding.lctext.text.toString())
                            vm.addUser(user)
                        } else {
                            binding.lctext.background= ContextCompat.getDrawable(requireContext(),R.drawable.error_border)
                            binding.lcErr.text = "Invalid ID"
                        }
                    }
                    else{

                        Toast.makeText(requireContext(), "Try Again", Toast.LENGTH_LONG)
                            .show()
                    }
                })
            }
        }
        return binding.root
    }
}