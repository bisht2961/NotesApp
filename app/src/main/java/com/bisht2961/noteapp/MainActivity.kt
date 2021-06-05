package com.bisht2961.noteapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bisht2961.noteapp.Application.NoteApplication
import com.bisht2961.noteapp.Database.Note
import com.bisht2961.noteapp.ViewModel.NoteViewModel
import com.bisht2961.noteapp.ViewModel.NoteViewModelFactory
import com.bisht2961.noteapp.databinding.ActivityMainBinding
import com.bisht2961.noteapp.ui.AddNote

class MainActivity : AppCompatActivity(), INotesRVAdapter, androidx.appcompat.widget.SearchView.OnQueryTextListener {

    private val addNoteActivityRequestCode = 1
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter:NotesRVAdapter
    private lateinit var searchView: androidx.appcompat.widget.SearchView
    private val noteViewModel: NoteViewModel by viewModels {
        NoteViewModelFactory((application as NoteApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView( binding.root)

        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        adapter = NotesRVAdapter(this,this)
        binding.recyclerview.adapter = adapter
        noteViewModel.allNotes.observe(this, Observer{ list ->
            list?.let {
                adapter.updateList(ArrayList(list))
            }
        })
        binding.fab.setOnClickListener{
            val intent = Intent(this@MainActivity,AddNote::class.java)
            startActivityForResult(intent,addNoteActivityRequestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == addNoteActivityRequestCode && resultCode == Activity.RESULT_OK){
            data?.getBundleExtra(AddNote.EXTRA_REPLY)?.let { reply ->
                val title = reply.getString("title")
                val description = reply.getString("description")
                val note = Note(title.toString(),description.toString())
                noteViewModel.insert(note)
            }
        }
    }

    override fun onItemClicked(note: Note) {
        noteViewModel.delete(note)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu,menu)
        val searchItem = menu!!.findItem(R.id.search_note)
        searchView = searchItem.actionView as androidx.appcompat.widget.SearchView
        searchView.isSubmitButtonEnabled = true
        searchView.setOnQueryTextListener(this)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.deleteAll ->
                noteViewModel.deleteAll()
        }
        return false
    }

    private fun searchQuery(query:String){
        val searchQuery = "%$query%"
        noteViewModel.getAllByTitle(searchQuery).observe(this, Observer { list ->
            list.let {
                adapter.updateList(ArrayList(list))
            }
        })
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if(query != null )
            searchQuery(query)
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if(newText != null )
            searchQuery(newText)
        return true
    }

}

