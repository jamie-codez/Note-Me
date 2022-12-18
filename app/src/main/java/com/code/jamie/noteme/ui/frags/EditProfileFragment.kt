package com.code.jamie.noteme.ui.frags

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore.Images.Media
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.code.jamie.noteme.databinding.FragmentEditProfileBinding
import com.code.jamie.noteme.models.vo.User
import com.code.jamie.noteme.ui.viewmodels.MainViewModel
import com.code.jamie.noteme.utils.Utils
import com.google.firebase.storage.internal.Util
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditProfileFragment : Fragment() {
    private var _binding: FragmentEditProfileBinding? = null
    private val binding get() = _binding!!
    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var sharedPrefs: SharedPreferences
    private lateinit var jwtToken: String
    private lateinit var email: String
    private var imageLoc: Uri?=null
    private var savedUser:User?=null

    private val requestPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            if (it) {
                val intent = Intent(Intent.ACTION_PICK, Media.EXTERNAL_CONTENT_URI).apply {
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
                imageLoc = it.data?.data!!
                try {
                    val bm = Media.getBitmap(requireActivity().contentResolver, imageLoc)
                    binding.profileIv.setImageBitmap(bm)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "onCreate: Fragment started successfully")
        sharedPrefs = Utils.getPrefs(requireContext())
        jwtToken = Utils.getJWT(Utils.jwt_pref, sharedPrefs)
        email = Utils.getEmail(sharedPrefs)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel.getUser(jwtToken, email).observe(viewLifecycleOwner) {
            savedUser = it?.user
            if (it?.user == null) {
                Utils.snackBar(binding.root, "Data is empty")
            } else {
                Picasso.get().load(it.user.imageUrl).into(binding.profileIv)
                binding.usernameEt.setText(it.user.username)
                binding.emailEt.setText(it.user.email)
            }
        }
        binding.updateBtn.setOnClickListener {
            val username = binding.usernameEt.text.toString().trim()
            val email = binding.emailEt.text.toString().trim()
            val password = binding.passwordEt.text.toString().trim()
            if (username.isBlank() || email.isBlank() || password.isBlank()){
                Utils.snackBar(binding.root,"Please fill all fields")
                return@setOnClickListener
            }
            val image = if (imageLoc==null){
                binding.profileIv.tag
            }else{
                imageLoc
            }
            mainViewModel.updateUser(jwtToken,savedUser!!.email,User(savedUser!!.id,email,
                image as String,password,username,
                savedUser!!.verified))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        private val TAG = EditProfileFragment::class.java.simpleName
    }
}