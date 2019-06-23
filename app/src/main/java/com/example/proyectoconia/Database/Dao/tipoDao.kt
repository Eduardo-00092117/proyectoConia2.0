package com.example.proyectoconia.Database.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.proyectoconia.Database.Entities.tipo

@Dao
interface tipoDao {

    @Query("SELECT * FROM tipo_table ORDER BY _id DESC")
    fun getAllTipo(): LiveData<List<tipo>>

    @Query("SELECT _id, nombre FROM tipo_table WHERE  nombre= :id")
    fun getOneTipo(id: String): tipo

    @Query("SELECT COUNT(*) FROM tipo_table")
    fun getContTipo(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTipo(new: tipo)

    @Query("DELETE FROM tipo_table")
    fun deleteTipo()

    @Query("DELETE FROM tipo_table WHERE _id= :id")
    fun deleteOneTipo(id: String)
}