package com.example.proyectoconia.Database.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.proyectoconia.Database.Entities.programacion
import com.example.proyectoconia.Database.Entities.tematica
@Dao
interface tematicaDao {
    @Query("SELECT * FROM tematica_table")
    fun getAllTematica(): LiveData<List<tematica>>

    @Query("SELECT _id, titulo, imagen, descripcion  FROM tematica_table WHERE  _id= :id")
    fun getOneTematica(id: String): LiveData<List<tematica>>

    @Query("SELECT COUNT(*) FROM tematica_table")
    fun getContTematica(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTematica(new: tematica)

    @Query("DELETE FROM tematica_table")
    fun deleteAllTematica()

    @Query("DELETE FROM tematica_table WHERE _id= :id")
    fun deleteOneTematica(id: String)
}