package com.example.retrofitkp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.retrofitkp.views.detailMoviePage.DetailMovieFragment
import com.example.retrofitkp.views.loginPage.LoginFragment
import com.example.retrofitkp.views.moviesPage.MoviesAdapter
import com.example.retrofitkp.views.moviesPage.MoviesFragment
import com.example.retrofitkp.views.startPage.StartFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> StartFragment()
            1 -> LoginFragment()
            2 -> MoviesFragment()
            else -> DetailMovieFragment()
        }
    }
}