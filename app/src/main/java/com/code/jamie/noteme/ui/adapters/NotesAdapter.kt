package com.code.jamie.noteme.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.code.jamie.noteme.R
import com.code.jamie.noteme.databinding.NoteItemBinding
import com.code.jamie.noteme.models.vo.Note

class NotesAdapter(private val list: List<Note>):RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {
    inner class NotesViewHolder(view: View):RecyclerView.ViewHolder(view){
        private val binding = NoteItemBinding.bind(view)
        fun bind(note: Note){
            binding.noteTitleTv.text = note.title
            binding.noteContentTv.text = note.note
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.note_item,parent,false)
        )
    }

    override fun getItemCount(): Int =list.size

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.bind(list[position])
    }
}