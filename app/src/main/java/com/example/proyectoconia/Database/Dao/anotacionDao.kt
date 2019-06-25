package com.example.proyectoconia.Database.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.proyectoconia.Database.Entities.anotacion
@Dao
interface anotacionDao {


    @Query("SELECT anota._id, anota.titulo, anota.fecha, anota.archivo, anota.fk_usuario_anotacion, anota.fk_programacion_anotacion FROM anotaciones_table anota, usuario_table usu, programacion_table prog WHERE anota.fk_usuario_anotacion = usu._id and anota.fk_programacion_anotacion = prog._id and usu.correo = :correo ORDER BY anota.fecha ASC")
    fun getAllAnotacione(correo : String): LiveData<List<anotacion>>

    @Query("SELECT * FROM anotaciones_table anota, usuario_table usu, programacion_table prog WHERE anota.fk_usuario_anotacion = usu._id and anota.fk_programacion_anotacion = prog._id and anota._id = :id")
    fun getOneAnotacion(id: String): anotacion

    @Query("SELECT COUNT(*) FROM anotaciones_table")
    fun getContAnotacion(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnotacion(new: anotacion)

    @Query("DELETE FROM anotaciones_table")
    fun deleteAllAnotacion()

    @Query("DELETE FROM anotaciones_table WHERE _id = :id")
    fun deleteOneAnotacion(id: String)

}