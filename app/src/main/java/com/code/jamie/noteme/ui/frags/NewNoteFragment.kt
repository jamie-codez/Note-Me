package com.code.jamie.noteme.ui.frags

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.code.jamie.noteme.databinding.FragmentNewNoteBinding
import com.code.jamie.noteme.models.vo.NoteRequestWrapper
import com.code.jamie.noteme.ui.viewmodels.MainViewModel
import com.code.jamie.noteme.utils.Utils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewNoteFragment : Fragment() {
    private var binding: FragmentNewNoteBinding? = null
    private val _binding get() = binding!!
    private lateinit var prefs:SharedPreferences
    private lateinit var jwtToken:String
    private lateinit var email:String
    private val mainViewModel:MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "onCreate: Fragment started successfully")
        prefs = Utils.getPrefs(requireContext())
        jwtToken = Utils.getJWT(Utils.jwt_pref,prefs)
        email = Utils.getEmail(prefs)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding.fabAdd.setOnClickListener {
            val titleText = _binding.titleEt.text.toString()
            val noteText = _binding.noteEt.text.toString()
            if (titleText.isBlank()){
                Utils.snackBar(_binding.root,"You need at least a title to save a note")
                return@setOnClickListener
            }
            val note = NoteRequestWrapper(System.currentTimeMillis(),noteText,email,titleText)
            mainViewModel.createNote(jwtToken,note).observe(viewLifecycleOwner){
                Utils.snackBar(_binding.root,it!!.message)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewNoteBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding
    }

    companion object {
        private val TAG = NewNoteFragment::class.java.simpleName
    }
}