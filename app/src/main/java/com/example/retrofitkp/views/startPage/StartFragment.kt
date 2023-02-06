package com.example.retrofitkp.views.startPage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.retrofitkp.databinding.FragmentStartBinding
import com.example.retrofitkp.views.loginPage.LoginFragment
import kotlinx.android.synthetic.main.fragment_root.view.*
import com.example.retrofitkp.ViewModel as ViewModel

class StartFragment : Fragment() {

    private val ViewModel by activityViewModels<ViewModel>()
    private lateinit var binding: FragmentStartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStartBinding.inflate(inflater)
        return binding.root
    }

    companion object {

        fun newInstance() =
            StartFragment().apply {
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.login.setOnClickListener(){
            ViewModel.goToNextFragment(this, LoginFragment.newInstance())
        }


    }
}