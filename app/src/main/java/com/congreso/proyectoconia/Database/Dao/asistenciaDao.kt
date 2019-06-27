package com.congreso.proyectoconia.Database.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.congreso.proyectoconia.Database.Entities.anotacion
import com.congreso.proyectoconia.Database.Entities.asistencia
import com.congreso.proyectoconia.Database.Entities.programacion
import com.congreso.proyectoconia.Database.Entities.usuario

@Dao
interface asistenciaDao {

    @Query("SELECT * FROM asistencia_table")
    fun getAllAnsistencia(): LiveData<List<asistencia>>

    @Query("SELECT * FROM asistencia_table asis WHERE asis.fk_asistencia_usuario = :id")
    fun getOneAsistencia(id: String): List<asistencia>

    @Query("SELECT asis._id, asis.calificacion ,asis.fk_asistencia_usuario, asis.fk_programacion_asistencia FROM asistencia_table asis, usuario_table usu WHERE asis.fk_asistencia_usuario = usu._id and usu.correo = :id")
    fun getOneAsistenciaUsuario(id: String): LiveData<List<asistencia>>

    @Query("SELECT asis._id, asis.calificacion ,asis.fk_asistencia_usuario, asis.fk_programacion_asistencia FROM asistencia_table asis, usuario_table usu WHERE asis.fk_asistencia_usuario = usu._id and usu.correo = :id")
    fun getOneAsistenciaUsuario2(id: String): List<asistencia>

    @Query("SELECT asis._id, asis.calificacion ,asis.fk_asistencia_usuario, asis.fk_programacion_asistencia FROM asistencia_table asis, usuario_table usu, programacion_table progra WHERE asis.fk_asistencia_usuario = usu._id and asis.fk_programacion_asistencia = progra._id and usu.correo = :correo and progra._id = :id_programa")
    fun getOneAsistenciaUsuarioPonencia(correo: String, id_programa : String): asistencia

    @Query("SELECT COUNT(*) FROM asistencia_table WHERE fk_asistencia_usuario = :id")
    fun getContAsistencia(id : String): Int

    @Query("SELECT progra._id, progra.numeroDia, progra.fecha, progra.lugar, progra.hora_inicio, progra.hora_fin, progra.descripcion FROM asistencia_table asis, programacion_table progra, usuario_table user WHERE asis.fk_asistencia_usuario = user._id and fk_programacion_asistencia = progra._id and user.correo = :id")
    fun getProgramaAsistencia(id : String): LiveData<List<programacion>>

    @Query("SELECT * FROM asistencia_table asis, usuario_table user WHERE asis.fk_asistencia_usuario = user._id and asis._id = :id")
    fun getUsuarioAsistencia(id : String): LiveData<usuario>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAsistencia(new: asistencia)

    @Query("DELETE FROM asistencia_table")
    fun deleteAllAsistencia()

    @Query("DELETE FROM asistencia_table WHERE _id = :id")
    fun deleteOneAsistencia(id: String)
}