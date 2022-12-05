package com.example.coderem.profiles

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
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
    ): View {
        // Inflate the layout for this fragment
        binding= FragmentUserLeetcodeBinding.inflate(inflater,container,false)
        vm = ViewModelProvider(this)[UserViewModel::class.java]
        vm.readlcdata.observe(viewLifecycleOwner) { mUser ->
            if (mUser != null) {
                binding.ulcId.text = mUser.profile
                user = mUser
                viewModelFactory = ProfileViewModelFactory(user.profile)
                pvm = ViewModelProvider(this, viewModelFactory)[ProfileViewModel::class.java]
                pvm.getLcData(user.profile)
                pvm.lcdataResponse.observe(viewLifecycleOwner, Observer {
                    binding.lcRanking.text = "Ranking : " + it.ranking
                    val text1: String = "Easy : " + it.easy_questions_solved + " / " + it.total_easy_questions

                    val spannable1: Spannable = SpannableString(text1)

                    spannable1.setSpan(
                        ForegroundColorSpan(Color.parseColor("#11BA22")),
                        0,
                        4,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )

                    binding.lcEasySolved.setText(spannable1, TextView.BufferType.SPANNABLE)

                    val text2: String = "Medium : " + it.medium_questions_solved + " / " + it.total_medium_questions

                    val spannable2: Spannable = SpannableString(text2)

                    spannable2.setSpan(
                        ForegroundColorSpan(Color.parseColor("#FF8A00")),
                        0,
                        6,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )

                    binding.lcMediumSolved.setText(spannable2, TextView.BufferType.SPANNABLE)

                    val text3: String = "Hard : " + it.hard_questions_solved + " / " + it.total_hard_questions

                    val spannable3: Spannable = SpannableString(text3)

                    spannable3.setSpan(
                        ForegroundColorSpan(Color.parseColor("#FF0000")),
                        0,
                        4,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )

                    binding.lcHardSolved.setText(spannable3, TextView.BufferType.SPANNABLE)


//                    binding.lcEasySolved.text =
//                        "Easy Solved : " + it.easy_questions_solved + " / " + it.total_easy_questions
//                    binding.lcMediumSolved.text =
//                        "Medium Solved : " + it.medium_questions_solved + " / " + it.total_medium_questions
//                    binding.lcHardSolved.text =
//                        "Hard Solved : " + it.hard_questions_solved + " / " + it.total_hard_questions
                    binding.lcPoints.text = "Leetcode coins : " + it.contribution_points
                    binding.lcProblemsContributed.text =
                        "Problems Contributed : " + it.contribution_problems
                    binding.lcTcContributed.text =
                        "Test Cases Contributed : " + it.contribution_testcases
                    binding.lcReputation.text = "Reputation : " + it.reputation


                })
            }
        }
        binding.lclogout.setOnClickListener {
            vm.deleteUser(user)
            Toast.makeText(requireContext(),"GOOD", Toast.LENGTH_LONG).show()
        }
        return binding.root
    }

}