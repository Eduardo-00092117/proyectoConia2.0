package com.example.proyectoconia.Database.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.proyectoconia.Database.Entities.anotacion
import com.example.proyectoconia.Database.Entities.asistencia
@Dao
interface asistenciaDao {

    @Query("SELECT * FROM asistencia_table")
    fun getAllAnsistencia(): LiveData<List<asistencia>>

    @Query("SELECT asis._id , asis.calificacion , asis.fk_asistencia_usuario, asis.fk_programacion_asistencia FROM asistencia_table asis, usuario_table usu, programacion_table prog WHERE asis.fk_asistencia_usuario = usu._id and asis.fk_programacion_asistencia = prog._id and asis._id = :id")
    fun getOneAsistencia(id: String): LiveData<List<asistencia>>

    @Query("SELECT COUNT(*) FROM asistencia_table")
    fun getContAsistencia(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAsistencia(new: asistencia)

    @Query("DELETE FROM asistencia_table")
    fun deleteAllAsistencia()

    @Query("DELETE FROM asistencia_table WHERE _id = :id")
    fun deleteOneAsistencia(id: String)
}