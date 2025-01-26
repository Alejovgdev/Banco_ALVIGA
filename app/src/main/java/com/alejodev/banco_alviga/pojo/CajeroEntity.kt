package com.alejodev.banco_alviga.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "CajeroEntity")
data class CajeroEntity(@PrimaryKey(autoGenerate = true)  var id: Int,
                         var direccion: String,
                         var latitud: Double,
                         var longitud: Double,
                         var zoom: String): Serializable
