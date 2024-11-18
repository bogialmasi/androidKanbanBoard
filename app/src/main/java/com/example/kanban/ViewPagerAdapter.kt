package com.example.kanban

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragment: MainActivity) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(pos: Int): Fragment {
            return when (pos) {
                0 -> TodoFragment()
                1 -> DoingFragment()
                2 -> DoneFragment()
                else -> TodoFragment()
            }
        }
    }
