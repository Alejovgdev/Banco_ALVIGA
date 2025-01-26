package com.alejodev.banco_alviga.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.alejodev.banco_alviga.pojo.CajeroEntity


@Dao
interface CajeroDAO {

    @Query("Select * from CajeroEntity")
    fun getAllCajeros(): MutableList<CajeroEntity>


    @Insert
    fun insertAll(listaCajeros: List<CajeroEntity>)


    @Insert
    fun addCajero(cajero: CajeroEntity)

    @Update
    fun updateCajero(cajero: CajeroEntity)

    @Delete
    fun deleteCajero(cajero: CajeroEntity)
}