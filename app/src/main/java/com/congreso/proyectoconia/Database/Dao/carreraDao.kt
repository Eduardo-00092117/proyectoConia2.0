package com.congreso.proyectoconia.Database.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.congreso.proyectoconia.Database.Entities.carrera

@Dao
interface carreraDao {

    @Query("SELECT * FROM carrera_table ORDER BY nombre DESC")
    fun getAllCarrera(): LiveData<List<carrera>>

    @Query("SELECT carre._id, carre.nombre FROM carrera_table carre WHERE  nombre = :id")
    fun getOneCarrea(id: String): carrera

    @Query("SELECT COUNT(*) FROM carrera_table")
    fun getContCarrera(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCarrera(new: carrera)

    @Query("DELETE FROM  carrera_table")
    fun deleteAllCarrera()

    @Query("DELETE FROM carrera_table WHERE _id = :id")
    fun deleteOneCarrera(id: String)
}