package com.code.jamie.noteme.ui.frags

import android.content.SharedPreferences.Editor
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.code.jamie.noteme.R
import com.code.jamie.noteme.databinding.FragmentLoginBinding
import com.code.jamie.noteme.models.vo.LoginRequestWrapper
import com.code.jamie.noteme.ui.viewmodels.MainViewModel
import com.code.jamie.noteme.utils.Utils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var editor: Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "onCreate: $TAG started successfully")
        val sharedPreferences = Utils.getPrefs(requireContext())
        editor = Utils.getEditor(sharedPreferences)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navHostFragment = requireActivity().supportFragmentManager.findFragmentById(R.id.main_activity_nav_host) as NavHostFragment
        val navController = navHostFragment.navController
        binding.loginBtn.setOnClickListener {
            binding.progressBarLogin.visibility = VISIBLE
            binding.loginBtn.isEnabled = false
            val email = binding.emailEt.text.toString().trim()
            val password = binding.passwordEt.text.toString().trim()
            if (email.isBlank()) {
                binding.emailEt.error = "Email is required"
                binding.progressBarLogin.visibility = INVISIBLE
                binding.loginBtn.isEnabled = true
                return@setOnClickListener
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.emailEt.error = "Invalid email address"
                binding.progressBarLogin.visibility = INVISIBLE
                binding.loginBtn.isEnabled = true
                return@setOnClickListener
            }
            if (password.isBlank()) {
                binding.passwordEt.error = "Email is required"
                binding.progressBarLogin.visibility = INVISIBLE
                binding.loginBtn.isEnabled = true
                return@setOnClickListener
            }
            val login = LoginRequestWrapper(email, password)
            viewModel.login(login).observe(viewLifecycleOwner) { resp ->
                if (resp.message == "Login successful") {
                    Utils.setJWT(resp.accessToken, editor)
                    navController.navigate(R.id.action_authFragment_to_homeActivity)
                    requireActivity().finish()
                }else{
                    Utils.snackBar(binding.root,"Error occurred. Try again")
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        private val TAG = LoginFragment::class.java.simpleName
    }
}