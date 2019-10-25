package com.example.roompatronesdiseo.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "notas")
class NotaEntity(
    var titulo: String,
    var contenido: String,
    var isFavorita: Boolean,
    var color: String
): Serializable {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

}