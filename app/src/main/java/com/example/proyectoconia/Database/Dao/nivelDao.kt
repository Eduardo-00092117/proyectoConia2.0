package com.example.proyectoconia.Database.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.proyectoconia.Database.Entities.nivel

@Dao
interface nivelDao {
    @Query("SELECT * FROM nivel_table ORDER BY nombre DESC")
    fun getAllNivel(): LiveData<List<nivel>>

    @Query("SELECT _id, nombre FROM nivel_table WHERE  nombre= :id")
    fun getOneNivel(id: String): nivel

    @Query("SELECT COUNT(*) FROM nivel_table")
    fun getContNivel(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNivel(new: nivel)

    @Query("DELETE FROM nivel_table")
    fun deleteAllNivel()

    @Query("DELETE FROM nivel_table WHERE _id= :id")
    fun deleteOneNivel(id: String)
}