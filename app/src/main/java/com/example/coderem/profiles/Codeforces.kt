package com.example.coderem.profiles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.coderem.ProfileViewModel
import com.example.coderem.ProfileViewModelFactory
import com.example.coderem.R
import com.example.coderem.database.database1.User
import com.example.coderem.database.database1.UserViewModel
import com.example.coderem.databinding.FragmentCodeforcesBinding


class codeforces : Fragment() {

    private lateinit var vm: UserViewModel
    lateinit var pvm: ProfileViewModel
    lateinit var viewModelFactory: ProfileViewModelFactory
    lateinit var binding: FragmentCodeforcesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentCodeforcesBinding.inflate(inflater,container,false)
        vm = ViewModelProvider(this)[UserViewModel::class.java]
        binding.cftext.doOnTextChanged { text, start, before, count ->
            if (text.toString().isNotEmpty()) {
                binding.cftext.background= ContextCompat.getDrawable(requireContext(),R.drawable.border)
                binding.cfErr.text = ""
            }
        }
        binding.cfbtn.setOnClickListener {
            if(binding.cftext.text.toString().trim().isEmpty()) {
                binding.cftext.background= ContextCompat.getDrawable(requireContext(), R.drawable.error_border)
                binding.cfErr.text="ID cannot be empty"

            }
            else {

                viewModelFactory = ProfileViewModelFactory(binding.cftext.text.toString().trim())
                pvm = ViewModelProvider(this, viewModelFactory)[ProfileViewModel::class.java]
                pvm.getCfStatus(binding.cftext.text.toString().trim())
                pvm.cfResponse.observe(viewLifecycleOwner, Observer {
                    if(it.status =="Success") {
                        Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_LONG).show()
                        val user = User(0, "CodeForces", binding.cftext.text.toString())
                        vm.addUser(user)
                    }
                    else{
                        binding.cftext.background= ContextCompat.getDrawable(requireContext(),R.drawable.error_border)
                        binding.cfErr.text="Invalid ID"
                    }
                })
            }
        } 
        return binding.root
    }


}