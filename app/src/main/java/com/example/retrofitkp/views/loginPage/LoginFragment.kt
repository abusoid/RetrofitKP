package com.example.retrofitkp.views.loginPage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.retrofitkp.R
import com.example.retrofitkp.ViewModel
import com.example.retrofitkp.databinding.FragmentLoginBinding
import com.example.retrofitkp.databinding.FragmentStartBinding
import com.example.retrofitkp.views.moviesPage.MoviesFragment


class LoginFragment : Fragment() {

    private val ViewModel by activityViewModels<ViewModel>()
    private lateinit var binding: FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater)
        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            LoginFragment().apply {
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.login.setOnClickListener {
            ViewModel.goToNextFragment(this, MoviesFragment.newInstance())
        }
    }
}