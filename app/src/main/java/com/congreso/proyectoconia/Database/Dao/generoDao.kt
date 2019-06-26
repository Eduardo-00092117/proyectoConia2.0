package com.congreso.proyectoconia.Database.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.congreso.proyectoconia.Database.Entities.genero

@Dao
interface generoDao {
    @Query("SELECT * FROM genero_table ORDER BY nombre DESC")
    fun getAllGenero(): LiveData<List<genero>>

    @Query("SELECT * FROM genero_table WHERE  nombre= :id")
    fun getOneGenero(id: String): genero

    @Query("SELECT COUNT(*) FROM genero_table")
    fun getContGenero(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenero(new: genero)

    @Query("DELETE FROM genero_table")
    fun deleteAllGenero()

    @Query("DELETE FROM genero_table WHERE _id= :id")
    fun deleteOneGenero(id: String)
}