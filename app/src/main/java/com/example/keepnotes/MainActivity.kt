package com.example.keepnotes

import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), INotesRVAdapter {
    lateinit var viewModel : NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val layout : ConstraintLayout = findViewById<ConstraintLayout>(R.id.layout)
        val anime  = layout.background as AnimationDrawable
        anime.setEnterFadeDuration(2500)
        anime.setExitFadeDuration(5000)
        anime.start()

        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = NotesRVAdapter(this,this)
        recyclerView.adapter = adapter

        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)
        viewModel.allNotes.observe(this, Observer {list->
            list?.let {
                adapter.updateList(it)
            }
        })
    }

    override fun itemClicked(note: Note) {
        viewModel.deleteNote(note)
        Toast.makeText(this,"${note.text} is Deleted",Toast.LENGTH_LONG).show()
    }

    fun Submit(view: View) {
        val text = edit_text.text.toString()
        if(text.isNotEmpty()){
            viewModel.insertNote(Note(text))
            Toast.makeText(this,"${text} is Added",Toast.LENGTH_LONG).show()
        }
    }

}