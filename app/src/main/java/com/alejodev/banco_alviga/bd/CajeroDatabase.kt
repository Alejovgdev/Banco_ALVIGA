package com.alejodev.banco_alviga.bd

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alejodev.banco_alviga.dao.CajeroDAO
import com.alejodev.banco_alviga.pojo.CajeroEntity


@Database(entities = arrayOf(CajeroEntity::class), version = 1)
abstract class CajeroDatabase : RoomDatabase(){

    abstract fun cajeroDao(): CajeroDAO
}