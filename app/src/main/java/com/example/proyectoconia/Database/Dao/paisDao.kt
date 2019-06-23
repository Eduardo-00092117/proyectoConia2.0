package com.example.proyectoconia.Database.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.proyectoconia.Database.Entities.pais

@Dao
interface paisDao {
    @Query("SELECT * FROM pais_table ORDER BY nombre DESC")
    fun getAllPais(): LiveData<List<pais>>

    @Query("SELECT _id, nombre FROM pais_table WHERE  nombre= :id")
    fun getOnePais(id: String): pais

    @Query("SELECT COUNT(*) FROM pais_table")
    fun getContPais(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPais(new: pais)

    @Query("DELETE FROM pais_table")
    fun deleteAllPais()

    @Query("DELETE FROM pais_table WHERE _id= :id")
    fun deleteOnePais(id: String)
}