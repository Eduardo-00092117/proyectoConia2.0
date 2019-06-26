package com.congreso.proyectoconia.Database.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.congreso.proyectoconia.Database.Entities.ponente

@Dao
interface ponenteDao {
    @Query("SELECT * FROM ponentes_table WHERE destacado = 1")
    fun getAllPonente(): LiveData<List<ponente>>

    @Query("SELECT * FROM ponentes_table WHERE _id = :id")
    fun getOnePonente(id: String): LiveData<List<ponente>>

    @Query("SELECT COUNT(*) FROM ponentes_table")
    fun getContPonente(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPonente(new: ponente)

    @Query("DELETE FROM ponentes_table")
    fun deleteAllPonente()

    @Query("DELETE FROM ponentes_table WHERE _id = :id")
    fun deleteOnePonente(id: String)
}