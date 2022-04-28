package com.hassanmohammed.formvalidationcleanarchitecture.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.lifecycleScope
import com.hassanmohammed.formvalidationcleanarchitecture.R
import com.hassanmohammed.formvalidationcleanarchitecture.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        lifecycleScope.launchWhenCreated {
            viewModel.formValidationState.collect {
                binding.emailTextField.isErrorEnabled = it.emailError != null
                binding.emailTextField.error = it.emailError
                binding.passwordTextField.isErrorEnabled = it.passwordError != null
                binding.passwordTextField.error = it.passwordError
                binding.repeatedPasswordTextField.isErrorEnabled = it.repeatedPasswordError != null
                binding.repeatedPasswordTextField.error = it.repeatedPasswordError
            }
        }

        binding.emailEt.doOnTextChanged { text, _, _, _ ->
            viewModel.onEvent(RegistrationFormEvent.EmailChanged(text.toString()))
        }

        binding.passwordEt.doOnTextChanged { text, start, before, count ->
            viewModel.onEvent(RegistrationFormEvent.PasswordChanged(text.toString()))
        }

        binding.repeatedPasswordEt.doOnTextChanged { text, start, before, count ->
            viewModel.onEvent(RegistrationFormEvent.RepeatedPasswordChanged(text.toString()))
        }

        binding.submitBtn.setOnClickListener {
            viewModel.onEvent(RegistrationFormEvent.Submit)
        }

        lifecycleScope.launchWhenCreated {
            viewModel.responseFlow.collect {
                Toast.makeText(this@MainActivity, it, Toast.LENGTH_SHORT).show()
            }
        }


    }
}