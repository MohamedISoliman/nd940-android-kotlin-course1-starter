package com.udacity.shoestore.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.LoginFragmentBinding

class LoginFragment : Fragment() {

    lateinit var binding: LoginFragmentBinding

    companion object {
        fun newInstance() = LoginFragment()
    }

    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = LoginFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        setupObservers()
    }

    private fun setupObservers() {
        viewModel.isValidCred().observe(viewLifecycleOwner, {
            if (it) {
                navigateToWelcomeScreen()
            } else {
                Toast.makeText(context, "Something wrong happened!", Toast.LENGTH_SHORT).show()
            }
        })

        binding.signIn.setOnClickListener {
            viewModel.signIn(
                binding.email.text.toString(), binding.password.text.toString()
            )
        }

        binding.newUser.setOnClickListener {
            viewModel.signUp(
                binding.email.text.toString(), binding.password.text.toString()
            )
        }
    }

    private fun navigateToWelcomeScreen() {
        findNavController().navigate(R.id.action_loginFragment_to_welcomeFragment)
    }


}