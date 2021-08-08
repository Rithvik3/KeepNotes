package com.example.keepnotes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView

class NotesRVAdapter(private val context : Context, private val listner : INotesRVAdapter): RecyclerView.Adapter<NotesRVAdapter.NoteViewHolder>() {
    val allNotes = ArrayList<Note>()
    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val textview = itemView.findViewById<TextView>(R.id.text)
        val deletebutton = itemView.findViewById<ImageView>(R.id.del_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val viewholder = NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.item_note,parent,false))
        viewholder.deletebutton.setOnClickListener {
            listner.itemClicked(allNotes[viewholder.adapterPosition])
        }
        return viewholder
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currNote = allNotes[position]
        holder.textview.text = currNote.text
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    fun updateList(newList: List<Note>){
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }
}

interface INotesRVAdapter{
    fun itemClicked(note : Note)
}