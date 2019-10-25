package com.example.roompatronesdiseo.repository

import com.example.roompatronesdiseo.model.NotaEntity
import com.example.roompatronesdiseo.dao.NotaDao
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.example.roompatronesdiseo.db.NotaRoomDatabase
import android.app.Application

class NotaRepository(application: Application) {

    private val notaDao: NotaDao

    val all: LiveData<List<NotaEntity>>

    val allFavs: LiveData<List<NotaEntity>>


    init {

        var db = NotaRoomDatabase.getDatabase(application)

        notaDao = db!!.notaDao()

        all = notaDao.getAll()

        allFavs = notaDao.getAllFavoritas()

    }


    fun insert(nota: NotaEntity) {

        insertAsyncTask(notaDao).execute(nota)

    }

    fun update(nota: NotaEntity){
        updateAsyncTask(notaDao).execute(nota)
    }

    private class insertAsyncTask internal constructor(private val notaDatoAsyncTask: NotaDao) :
        AsyncTask<NotaEntity, Void, Void>() {

        override fun doInBackground(vararg notaEntities: NotaEntity): Void? {

            notaDatoAsyncTask.insert(notaEntities[0])

            return null

        }

    }

    private class updateAsyncTask internal constructor(private val notaDatoAsyncTask: NotaDao) :
        AsyncTask<NotaEntity, Void, Void>() {

        override fun doInBackground(vararg notaEntities: NotaEntity): Void? {

            notaDatoAsyncTask.update(notaEntities[0])

            return null

        }

    }

}