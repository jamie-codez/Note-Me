package com.code.jamie.noteme.ui.frags

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.code.jamie.noteme.R
import com.code.jamie.noteme.databinding.FragmentNotesBinding
import com.code.jamie.noteme.ui.adapters.NotesAdapter
import com.code.jamie.noteme.ui.viewmodels.MainViewModel
import com.code.jamie.noteme.utils.Utils


class NotesFragment : Fragment() {
    private lateinit var _binding: FragmentNotesBinding
    private val binding get() = _binding
    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var jwtToken: String
    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prefs = Utils.getPrefs(requireContext())
        jwtToken = Utils.getJWT(Utils.jwt_pref, prefs)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addNoteFab.setOnClickListener {
            it.findNavController().navigate(R.id.action_notesFragment_to_newNoteFragment)
        }
        mainViewModel.getNotes(jwtToken).observe(viewLifecycleOwner) {
            if (it!!.notes.isEmpty()) {
                binding.notesRecycler.visibility = GONE
                binding.emptyTrayIv.visibility = VISIBLE
                binding.emptyTrayTv.visibility = VISIBLE
            } else {
                binding.notesRecycler.visibility = VISIBLE
                binding.emptyTrayIv.visibility = GONE
                binding.emptyTrayTv.visibility = GONE
                val notesAdapter = NotesAdapter(it.notes)
                binding.notesRecycler.apply {
                    adapter = notesAdapter
                    layoutManager = GridLayoutManager(requireContext(), 2)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotesBinding.inflate(inflater, container, false)
        setUpMenu()
        return binding.root
    }

    fun setUpMenu() {
        requireActivity().addMenuProvider(
            object : MenuProvider {
                override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                    TODO("Not yet implemented")
                }

                override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                    TODO("Not yet implemented")
                }
            },
            viewLifecycleOwner,
            Lifecycle.State.RESUMED
        )
    }

    override fun onDestroy() {
        super.onDestroy()

    }

    companion object {
        private val TAG = NotesFragment::class.java.simpleName
    }
}