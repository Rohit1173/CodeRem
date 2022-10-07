package com.example.coderem.profiles

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.coderem.ProfileViewModel
import com.example.coderem.ProfileViewModelFactory
import com.example.coderem.R
import com.example.coderem.database.User
import com.example.coderem.database.UserViewModel


class UserLeetCode : Fragment() {

    lateinit var vm: UserViewModel
    lateinit var user: User
    lateinit var pvm: ProfileViewModel
    lateinit var viewModelFactory: ProfileViewModelFactory
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v= inflater.inflate(R.layout.fragment_user_leetcode, container, false)
        vm = ViewModelProvider(this)[UserViewModel::class.java]
        val ulcId: TextView =v.findViewById(R.id.ulc_id)
        val lclogout: Button =v.findViewById(R.id.lclogout)
        val easySolved:TextView=v.findViewById(R.id.lc_easySolved)
        val mediumSolved:TextView=v.findViewById(R.id.lc_mediumSolved)
        val hardSolved:TextView=v.findViewById(R.id.lc_hardSolved)

        val lcranking:TextView=v.findViewById(R.id.lc_ranking)
        val lcPoints:TextView=v.findViewById(R.id.lc_points)
        val lcProblemsContributed:TextView=v.findViewById(R.id.lc_problemsContributed)
        val lcTestCasesContributed:TextView=v.findViewById(R.id.lc_tcContributed)
        val lcReputation:TextView=v.findViewById(R.id.lc_reputation)
        vm.readlcdata.observe(viewLifecycleOwner, Observer { mUser ->
            if(mUser!=null) {
                ulcId.text = mUser.profile
                user = mUser
                viewModelFactory = ProfileViewModelFactory(user.profile)
                pvm = ViewModelProvider(this, viewModelFactory)[ProfileViewModel::class.java]
                pvm.getLcData(user.profile)
                pvm.lcdataResponse.observe(viewLifecycleOwner, Observer {
                    lcranking.text="Ranking : "+it.ranking
                    easySolved.text="Easy Solved : "+ it.easy_questions_solved + " / " + it.total_easy_questions
                    mediumSolved.text="Medium Solved : "+ it.medium_questions_solved + " / " + it.total_medium_questions
                    hardSolved.text="Hard Solved : "+ it.hard_questions_solved + " / " + it.total_hard_questions
                    lcPoints.text="Leetcode coins : "+it.contribution_points
                    lcProblemsContributed.text="Problems Contributed : "+it.contribution_problems
                    lcTestCasesContributed.text="Test Cases Contributed : "+it.contribution_testcases
                    lcReputation.text="Reputation : "+it.reputation


                })
            }
        })
        lclogout.setOnClickListener {
            vm.deleteUser(user)
            Toast.makeText(requireContext(),"GOOD", Toast.LENGTH_LONG).show()
        }
        return v
    }

}