package com.example.proyectoconia.Database.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.proyectoconia.Database.Entities.pais
import com.example.proyectoconia.Database.Entities.patrocinio
@Dao
interface patrocinioDao {

    @Query("SELECT * FROM patrocinio_table ORDER BY nombre ASC")
    fun getAllPatrocinio(): LiveData<List<patrocinio>>

    @Query("SELECT _id, imagen, nombre, url FROM patrocinio_table WHERE  _id= :id")
    fun getOnePatrocinio(id: String): LiveData<List<patrocinio>>

    @Query("SELECT COUNT(*) FROM patrocinio_table")
    fun getContPatrocinio(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPatrocinio(new: patrocinio)

    @Query("DELETE FROM patrocinio_table")
    fun deleteAllPatrocinio()

    @Query("DELETE FROM patrocinio_table WHERE _id= :id")
    fun deleteOnePatrocinio(id: String)
}