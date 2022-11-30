package com.code.jamie.noteme.ui.frags

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "onCreate: $TAG started successFully")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.profileIv.setOnClickListener {
            if (checkPermissions()) {
                goToGallery()
            } else {
                requestPermission()
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
                        if (it.message == "User created successfully") {
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

    private fun requestPermission() =
        ActivityCompat.requestPermissions(
            requireActivity(), arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            PERMISSION_CODE
        )

    private fun goToGallery() {
        Intent(Intent.ACTION_PICK).apply {
            type = "image/*"
            action = Intent.ACTION_GET_CONTENT
            startActivityForResult(this, GALLERY_CODE)
        }
    }

    private fun checkPermissions(): Boolean =
        ActivityCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED

    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_CODE &&
            permissions.contentEquals(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)) &&
            grantResults[0] == PackageManager.PERMISSION_GRANTED
        ) {
            goToGallery()
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GALLERY_CODE && resultCode == Activity.RESULT_OK && data != null) {
            selectedImageUri = data.data
            try {
//                val contentUri = MediaStore.Images.Media.getContentUri(selectedImageUri.toString())
//                val bm = MediaStore.Images.Media.getBitmap(
//                    requireActivity().contentResolver,
//                    selectedImageUri
//                )
                binding.profileIv.setImageURI(selectedImageUri)
            } catch (e: Exception) {
                e.printStackTrace()
                Log.i(TAG, "onActivityResult: ${e.message}")
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        private val TAG = RegisterFragment::class.java.simpleName
        private const val PERMISSION_CODE = 200
        private const val GALLERY_CODE = 300
    }
}