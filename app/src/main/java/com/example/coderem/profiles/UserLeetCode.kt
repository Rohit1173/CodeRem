package com.example.coderem.profiles

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.coderem.ProfileViewModel
import com.example.coderem.ProfileViewModelFactory
import com.example.coderem.database.database1.User
import com.example.coderem.database.database1.UserViewModel
import com.example.coderem.databinding.FragmentUserLeetcodeBinding


class UserLeetCode : Fragment() {

    lateinit var vm: UserViewModel
    lateinit var user: User
    lateinit var pvm: ProfileViewModel
    lateinit var viewModelFactory: ProfileViewModelFactory
    lateinit var binding: FragmentUserLeetcodeBinding
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentUserLeetcodeBinding.inflate(inflater,container,false)
        vm = ViewModelProvider(this)[UserViewModel::class.java]
        vm.readlcdata.observe(viewLifecycleOwner, Observer { mUser ->
            if(mUser!=null) {
                binding.ulcId.text = mUser.profile
                user = mUser
                viewModelFactory = ProfileViewModelFactory(user.profile)
                pvm = ViewModelProvider(this, viewModelFactory)[ProfileViewModel::class.java]
                pvm.getLcData(user.profile)
                pvm.lcdataResponse.observe(viewLifecycleOwner, Observer {
                    binding.lcRanking.text="Ranking : "+it.ranking
                    binding.lcEasySolved.text="Easy Solved : "+ it.easy_questions_solved + " / " + it.total_easy_questions
                    binding.lcMediumSolved.text="Medium Solved : "+ it.medium_questions_solved + " / " + it.total_medium_questions
                    binding.lcHardSolved.text="Hard Solved : "+ it.hard_questions_solved + " / " + it.total_hard_questions
                    binding.lcPoints.text="Leetcode coins : "+it.contribution_points
                    binding.lcProblemsContributed.text="Problems Contributed : "+it.contribution_problems
                    binding.lcTcContributed.text="Test Cases Contributed : "+it.contribution_testcases
                    binding.lcReputation.text="Reputation : "+it.reputation


                })
            }
        })
        binding.lclogout.setOnClickListener {
            vm.deleteUser(user)
            Toast.makeText(requireContext(),"GOOD", Toast.LENGTH_LONG).show()
        }
        return binding.root
    }

}