package com.example.retrofitkp.views.registration

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.retrofitkp.App.Companion.ctx
import com.example.retrofitkp.ViewModel
import com.example.retrofitkp.databinding.FragmentRegistrationBinding
import com.example.retrofitkp.views.moviesPage.MoviesFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegistrationFragment : Fragment() {
    private lateinit var binding: FragmentRegistrationBinding
    private val scope =  CoroutineScope(Dispatchers.Main)

    private val ViewModel by activityViewModels<ViewModel>()
    private lateinit var email: String
    private lateinit var password: String
    private lateinit var firstName: String
    private lateinit var lastName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegistrationBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.registration.setOnClickListener {
            email = binding.email.text.toString()
            password = binding.password.text.toString()
            firstName = binding.password.text.toString()
            lastName = binding.lastName.text.toString()
            println("Создаем пользователя $email , $password , $firstName , $lastName")
            scope.launch {
                ViewModel.createUser(email = email, password = password, firstName = firstName, lastName = lastName, activeFlag = 0)
            }
            ViewModel.goToNextFragment(this, MoviesFragment.newInstance())

        }
    }
    companion object {

        @JvmStatic
        fun newInstance() =
            RegistrationFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}



