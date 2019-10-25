package com.example.roompatronesdiseo.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.roompatronesdiseo.model.NotaEntity

@Dao
interface NotaDao {

    @Insert
    fun insert(nota: NotaEntity)

    @Update
    fun update(nota: NotaEntity)

    @Query("DELETE FROM notas")
    fun deleteAll()

    @Query("DELETE FROM notas WHERE id = :id ")
    fun deleteById(id: Int)

    @Query("SELECT * FROM notas ORDER BY id ASC")
    fun getAll(): LiveData<List<NotaEntity>>

    @Query("SELECT * FROM notas WHERE isFavorita LIKE 1 ")
    fun getAllFavoritas(): LiveData<List<NotaEntity>>

}