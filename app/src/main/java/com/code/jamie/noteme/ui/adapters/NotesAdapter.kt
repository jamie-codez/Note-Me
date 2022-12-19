package com.code.jamie.noteme.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.code.jamie.noteme.R
import com.code.jamie.noteme.databinding.NoteItemBinding
import com.code.jamie.noteme.models.vo.Note

class NotesAdapter(private val list: List<Note>):RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {
    private lateinit var listener:OnItemClick
    interface OnItemClick{
        fun onItemClick(position: Int)
    }
    fun setOnClickListener(listener:OnItemClick){
        this.listener = listener
    }
    inner class NotesViewHolder(view: View,listener: OnItemClick):RecyclerView.ViewHolder(view){
        private val binding = NoteItemBinding.bind(view)
        init {
            binding.root.setOnClickListener {
                if (adapterPosition!=RecyclerView.NO_POSITION){
                    listener.onItemClick(adapterPosition)
                }
            }
        }
        fun bind(note: Note){
            binding.noteTitleTv.text = note.title
            binding.noteContentTv.text = note.note
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.note_item,parent,false),
            listener
        )
    }

    override fun getItemCount(): Int =list.size

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.bind(list[position])
    }
}