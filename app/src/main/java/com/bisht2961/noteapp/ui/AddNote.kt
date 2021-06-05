package com.bisht2961.noteapp.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bisht2961.noteapp.databinding.ActivityAddNoteBinding

class AddNote : AppCompatActivity() {

    private lateinit var binding: ActivityAddNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    fun createNote(view: View) {
        val title = binding.title.text.toString()
        val description = binding.description.text.toString()
        val bundle = Bundle()
        bundle.putString("title",title)
        bundle.putString("description",description)
        val replyIntent = Intent()
        if(title.isNotEmpty() && description.isNotEmpty()){
            replyIntent.putExtra(EXTRA_REPLY,bundle)
            setResult(Activity.RESULT_OK,replyIntent)
            finish()
        }else{
            Toast.makeText(this,"Field can't be Empty",Toast.LENGTH_LONG).show()
        }
    }
    companion object{
        const val EXTRA_REPLY = "com.bisht2961.noteapp.ui.REPLY"
    }
}