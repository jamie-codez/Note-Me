package com.code.jamie.noteme.ui.frags

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.code.jamie.noteme.databinding.FragmentNewNoteBinding

class NewNoteFragment : Fragment() {
    private var binding: FragmentNewNoteBinding? = null
    private val _binding get() = binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewNoteBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding
    }

    companion object {
        private val TAG = NewNoteFragment::class.java.simpleName
    }
}