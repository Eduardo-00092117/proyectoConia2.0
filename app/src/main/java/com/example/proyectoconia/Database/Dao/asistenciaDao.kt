package com.example.proyectoconia.Database.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.proyectoconia.Database.Entities.anotacion
import com.example.proyectoconia.Database.Entities.asistencia
import com.example.proyectoconia.Database.Entities.programacion

@Dao
interface asistenciaDao {

    @Query("SELECT * FROM asistencia_table")
    fun getAllAnsistencia(): LiveData<List<asistencia>>

    @Query("SELECT * FROM asistencia_table asis WHERE asis.fk_asistencia_usuario = :id")
    fun getOneAsistencia(id: String): asistencia

    @Query("SELECT COUNT(*) FROM asistencia_table WHERE fk_asistencia_usuario = :id")
    fun getContAsistencia(id : String): Int

    @Query("SELECT progra._id, progra.numeroDia, progra.fecha, progra.lugar, progra.hora_inicio, progra.hora_fin, progra.descripcion FROM asistencia_table asis, programacion_table progra, usuario_table user WHERE asis.fk_asistencia_usuario = user._id and fk_programacion_asistencia = progra._id and user.correo = :id")
    fun getProgramaAsistencia(id : String): LiveData<List<programacion>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAsistencia(new: asistencia)

    @Query("DELETE FROM asistencia_table")
    fun deleteAllAsistencia()

    @Query("DELETE FROM asistencia_table WHERE _id = :id")
    fun deleteOneAsistencia(id: String)
}