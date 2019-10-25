package com.example.roompatronesdiseo

import com.example.roompatronesdiseo.model.NotaEntity
import com.example.roompatronesdiseo.repository.NotaRepository
import androidx.lifecycle.LiveData
import android.app.Application
import androidx.lifecycle.AndroidViewModel

class NuevaNotaDialogViewModel(application: Application) : AndroidViewModel(application) {

    var allNotas: LiveData<List<NotaEntity>>
    var allFavoritasNotas: LiveData<List<NotaEntity>>
    var notaRepository: NotaRepository

    init {
        notaRepository = NotaRepository(application)
        allNotas = notaRepository.all
        allFavoritasNotas = notaRepository.allFavs
    }

    fun insertarNota(nuevaNotaEntity: NotaEntity) {
        notaRepository.insert(nuevaNotaEntity)
    }

    fun updateNota(nuevaNotaEntity: NotaEntity) {
        notaRepository.update(nuevaNotaEntity)
    }

}
