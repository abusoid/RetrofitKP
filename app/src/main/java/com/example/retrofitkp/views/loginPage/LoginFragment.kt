package com.example.retrofitkp.views.loginPage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.retrofitkp.R
import com.example.retrofitkp.ViewModel
import com.example.retrofitkp.data.db.data.UserEntity
import com.example.retrofitkp.databinding.FragmentLoginBinding
import com.example.retrofitkp.databinding.FragmentStartBinding
import com.example.retrofitkp.views.moviesPage.MoviesFragment
import com.example.retrofitkp.views.registration.RegistrationFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class LoginFragment : Fragment() {

    private val ViewModel by activityViewModels<ViewModel>()
    private lateinit var binding: FragmentLoginBinding
    private val scope =  CoroutineScope(Dispatchers.Main)
    private lateinit var userEntity: UserEntity
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
        val fragment = this
        binding.login.setOnClickListener {
            scope.launch {
                try {
                    //var users: List<UserEntity> = ViewModel.getAllUsers()
                    println("email text: " + binding.editTextTextEmailAddress.text.toString())
                    println("password text: " + binding.editTextTextPassword.text.toString())
                    userEntity = ViewModel.authorizationUser(
                    binding.editTextTextEmailAddress.text.toString(),
                    binding.editTextTextPassword.text.toString())
                    /*for(user in users) {
                        println("uid="+user.uid)
                        println("email="+user.email)
                        println("password="+user.password)
                        println("lastName="+user.lastName)
                        println("firstName="+user.firstName)
                        println("activeFlag="+user.activeFlag)
                    }*/
                    println("end entity")
                    //println(userEntity)
                    println(userEntity.email)
                    println(userEntity.password)
                    println("end try")
                    ViewModel.goToNextFragment(fragment, MoviesFragment.newInstance())
                }catch(e:Exception) {
                    println("start catch")
                    println(e)
                    Toast.makeText(context, "Пользователь не найден", Toast.LENGTH_LONG)
                }
            }


            //
        }
        binding.registration.setOnClickListener(){
            ViewModel.goToNextFragment(this, RegistrationFragment.newInstance())
        }
    }
}