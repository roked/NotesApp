package com.example.plainolnotes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.plainolnotes.data.AppDatabase
import com.example.plainolnotes.data.NoteEntity
import com.example.plainolnotes.data.SampleDataProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(app: Application) : AndroidViewModel(app) {

    private val database = AppDatabase.getInstance(app)
    val noteList = database?.noteDao()?.getAll()

    fun addSampleData() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val sampleNotes = SampleDataProvider.getNotes()
                database?.noteDao()?.insertAll(sampleNotes)
            }
        }
    }

    fun deleteNotes(selectedNotes: List<NoteEntity>) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                database?.noteDao()?.deleteNotes(selectedNotes)
            }
        }
    }

    fun deleteAllNotes() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                database?.noteDao()?.deleteAllNotes()
            }
        }
    }

}