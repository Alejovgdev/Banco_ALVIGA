package com.alejodev.banco_alviga

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.alejodev.banco_alviga.bd.CajeroDatabase
import com.alejodev.banco_alviga.pojo.CajeroEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CajeroApplication : Application() {

    companion object {
        lateinit var database: CajeroDatabase
    }

    override fun onCreate() {
        super.onCreate()

        val callback = object : RoomDatabase.Callback() {
            override fun onCreate(db: androidx.sqlite.db.SupportSQLiteDatabase) {
                super.onCreate(db)
                // Inserción inicial de cajeros
                CoroutineScope(Dispatchers.IO).launch {
                    val cajerosEntityLists: List<CajeroEntity> = listOf(
                        CajeroEntity(1, "Carrer del Clariano, 1, 46021 Valencia, España", 39.4760077, -0.3524475, ""),
                        CajeroEntity(2, "Avinguda del Cardenal Benlloch, 65, 46021 València, España", 39.4710366, -0.3547525, ""),
                        CajeroEntity(3, "Av. del Port, 237, 46011 València, España", 39.46162, -0.33763, ""),
                        CajeroEntity(4, "Carrer del Batxiller, 6, 46010 València, España", 39.4826729, -0.3639119, ""),
                        CajeroEntity(5, "Av. del Regne de València, 2, 46005 València, España", 39.4647669, -0.373276, "")
                    )
                    database.cajeroDao().insertAll(cajerosEntityLists)
                }
            }
        }

        database = Room.databaseBuilder(this, CajeroDatabase::class.java, "CajeroDatabase")
            .addCallback(callback)
            .build()
    }
}
