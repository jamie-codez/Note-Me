package com.code.jamie.noteme.ui.frags

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.code.jamie.noteme.R
import com.code.jamie.noteme.databinding.FragmentNotesBinding


class NotesFragment : Fragment() {
    private lateinit var _binding:FragmentNotesBinding
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addNoteFab.setOnClickListener {
            it.findNavController().navigate(R.id.action_notesFragment_to_newNoteFragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotesBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()

    }

    companion object {
    private val TAG = NotesFragment::class.java.simpleName
    }
}