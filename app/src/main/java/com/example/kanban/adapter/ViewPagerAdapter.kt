package com.example.kanban.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.kanban.activities.MainActivity
import com.example.kanban.fragments.DoingFragment
import com.example.kanban.fragments.DoneFragment
import com.example.kanban.fragments.TodoFragment

class ViewPagerAdapter(fragment: MainActivity) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 3
    }

    // this is what determines which to do list is shown on main
    override fun createFragment(pos: Int): Fragment {
            return when (pos) {
                0 -> TodoFragment()
                1 -> DoingFragment()
                2 -> DoneFragment()
                else -> TodoFragment()
            }
        }
    }
