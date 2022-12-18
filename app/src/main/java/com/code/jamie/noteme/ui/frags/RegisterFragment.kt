package com.code.jamie.noteme.ui.frags

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.code.jamie.noteme.databinding.FragmentRegisterBinding
import com.code.jamie.noteme.models.vo.RegisterRequestWrapper
import com.code.jamie.noteme.ui.viewmodels.MainViewModel
import com.code.jamie.noteme.utils.Utils
import com.google.firebase.storage.FirebaseStorage
import dagger.hilt.android.AndroidEntryPoint

@Suppress("DEPRECATION")
@AndroidEntryPoint
class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private var selectedImageUri: Uri? = null
    private val viewModel: MainViewModel by viewModels()
    private val requestPermissions =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            if (it) {
                val intent =
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI).apply {
                        type = "image/*"
                    }
                getResults.launch(intent)
            } else {
                Utils.snackBar(binding.root, "This permission is required")
            }
        }
    private val getResults =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK && it.data != null) {
                selectedImageUri = it.data?.data!!
                try {
                    val bm = MediaStore.Images.Media.getBitmap(
                        requireActivity().contentResolver,
                        selectedImageUri
                    )
                    binding.profileIv.setImageBitmap(bm)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "onCreate: $TAG started successFully")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.profileIv.setOnClickListener {
            if (checkPermissions()) {
                val intent =
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI).apply {
                        type = "image/*"
                    }
                getResults.launch(intent)
            } else {
                requestPermissions.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
            }
        }
        binding.registerBtn.setOnClickListener {
            binding.progressBarRegister.visibility = VISIBLE
            binding.registerBtn.isEnabled = false
            val username = binding.usernameEt.text.toString().trim()
            val email = binding.emailEt.text.toString().trim()
            val password = binding.passwordEt.text.toString().trim()
            if (username.isBlank()) {
                binding.usernameEt.error = "Username is required"
                binding.progressBarRegister.visibility = INVISIBLE
                binding.registerBtn.isEnabled = true
                return@setOnClickListener
            }
            if (email.isBlank()) {
                binding.emailEt.error = "Email is required"
                binding.progressBarRegister.visibility = INVISIBLE
                binding.registerBtn.isEnabled = true
                return@setOnClickListener
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.emailEt.error = "Invalid email address"
                binding.progressBarRegister.visibility = INVISIBLE
                binding.registerBtn.isEnabled = true
                return@setOnClickListener
            }
            if (password.isBlank()) {
                binding.passwordEt.error = "Password is required"
                binding.progressBarRegister.visibility = INVISIBLE
                binding.registerBtn.isEnabled = true
                return@setOnClickListener
            }
            if (password.length < 8) {
                binding.passwordEt.error = "Password is short,try 8 characters"
                binding.progressBarRegister.visibility = INVISIBLE
                binding.registerBtn.isEnabled = true
                return@setOnClickListener
            }
            if (selectedImageUri == null) {
                Utils.snackBar(binding.root, "Select profile image")
                return@setOnClickListener
            }
            uploadImageToFB(selectedImageUri!!, username, email, password)
        }
    }

    private fun uploadImageToFB(
        selectedImageUri: Uri,
        username: String,
        email: String,
        password: String
    ) {
        val ref = FirebaseStorage.getInstance().getReference("/images")
        ref.putFile(selectedImageUri)
            .addOnSuccessListener {
                ref.downloadUrl.addOnCompleteListener { task ->
                    val downloadUrl = task.toString()
                    val register = RegisterRequestWrapper(email, downloadUrl, password, username)
                    viewModel.register(register).observe(viewLifecycleOwner) {
                        if (it?.message == "User created successfully") {
                            Utils.snackBar(binding.root, "${it.message}\nLogin")
                        } else {
                            Utils.snackBar(binding.root, "Error occurred. Try again")
                        }
                    }
                }
            }.addOnCompleteListener {
                Log.i(TAG, "uploadIMageToFB: Image upload complete")
            }.addOnFailureListener {
                Log.e(TAG, "uploadIMageToFB: ${it.message}")
                Utils.snackBar(binding.root, "Error uploading image")
            }
    }


    private fun checkPermissions(): Boolean =
        ActivityCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        private val TAG = RegisterFragment::class.java.simpleName
    }
}