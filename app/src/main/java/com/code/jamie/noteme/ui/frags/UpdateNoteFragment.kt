package com.code.jamie.noteme.ui.frags

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.code.jamie.noteme.databinding.FragmentUpdateNoteBinding
import com.code.jamie.noteme.models.vo.NoteOpRequestWrapper
import com.code.jamie.noteme.ui.viewmodels.MainViewModel
import com.code.jamie.noteme.utils.Utils
import com.google.firebase.storage.internal.Util
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateNoteFragment : Fragment() {
    private var _binding: FragmentUpdateNoteBinding? = null
    private val binding get() = _binding!!
    private lateinit var prefs:SharedPreferences
    private lateinit var jwt:String
    private lateinit var email:String
    private val mainViewModel:MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "onCreate: Fragment started successfully")
        prefs = Utils.getPrefs(requireContext())
        jwt = Utils.getJWT(Utils.jwt_pref,prefs)
        email = Utils.getEmail(prefs)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fabUpdate.setOnClickListener {
            val noteTitle = binding.updateTitleEt.text.toString()
            val noteText = binding.updateNoteEt.text.toString()
            if (noteTitle.isBlank()){
                Utils.snackBar(binding.root,"Provide a title")
                return@setOnClickListener
            }
            val note = NoteOpRequestWrapper("",noteText,email,noteTitle)
            mainViewModel.updateNote(jwt,note).observe(viewLifecycleOwner){
                Utils.snackBar(binding.root,it!!.message)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpdateNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        private val TAG = UpdateNoteFragment::class.java.simpleName
    }
}