package com.example.roompatronesdiseo.db

import android.content.Context
import androidx.room.Room
import com.example.roompatronesdiseo.dao.NotaDao
import androidx.room.RoomDatabase
import com.example.roompatronesdiseo.model.NotaEntity
import androidx.room.Database

@Database(entities = [NotaEntity::class], version = 1)
abstract class NotaRoomDatabase : RoomDatabase() {

    abstract fun notaDao(): NotaDao

    companion object {

        @Volatile
        private var INSTANCE: NotaRoomDatabase? = null

        fun getDatabase(context: Context): NotaRoomDatabase? {
            if (INSTANCE == null) {
                synchronized(NotaRoomDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            NotaRoomDatabase::class.java, "notas_database").build()
                    }
                }

            }

            return INSTANCE

        }
    }

}