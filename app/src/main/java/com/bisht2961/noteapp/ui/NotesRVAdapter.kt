package com.bisht2961.noteapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bisht2961.noteapp.Database.Note

class NotesRVAdapter(private val context: Context, private val listener:INotesRVAdapter) : RecyclerView.Adapter<NotesRVAdapter.NoteViewHolder>(){

    val allNotes = ArrayList<Note>()
    inner class NoteViewHolder(itemview: View): RecyclerView.ViewHolder(itemview){

        val title = itemview.findViewById<TextView>(R.id.note_title)
        val description = itemview.findViewById<TextView>(R.id.note_description)
        val delete = itemview.findViewById<ImageView>(R.id.note_delete)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val viewHolder = NoteViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_view,parent,false))

        viewHolder.delete.setOnClickListener{
            listener.onItemClicked(allNotes[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = allNotes[position]
        holder.title.text = currentNote.title
        holder.description.text = currentNote.description

    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    fun updateList(newList:ArrayList<Note>){
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }
}
interface INotesRVAdapter{
    fun onItemClicked(note:Note)
}