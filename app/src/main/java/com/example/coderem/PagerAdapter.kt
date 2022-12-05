package com.example.coderem

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import org.jetbrains.annotations.NotNull

class PagerAdapter(fm:FragmentManager): FragmentPagerAdapter(fm) {
    private val fragList:MutableList<Fragment> = ArrayList()
    private val titleList:MutableList<String> = ArrayList()

    override fun getCount(): Int {
        TODO("Not yet implemented")
    }

    override fun getItem(position: Int): Fragment {
        TODO("Not yet implemented")
    }
    fun addfrag(fragment: Fragment,title:String){
        fragList.add(fragment)
        titleList.add(title)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titleList[position]
    }

}

