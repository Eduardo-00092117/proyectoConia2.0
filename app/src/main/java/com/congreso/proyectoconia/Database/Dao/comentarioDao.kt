package com.congreso.proyectoconia.Database.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.congreso.proyectoconia.Database.Entities.anotacion
import com.congreso.proyectoconia.Database.Entities.comentario
@Dao
interface comentarioDao {
    @Query("SELECT * FROM comentario_table")
    fun getAllComentario(): LiveData<List<comentario>>

    @Query("SELECT comen._id, comen.comentario , comen.fecha, comen.hora , comen.fk_asistencia_comentario FROM comentario_table comen, asistencia_table asis WHERE comen.fk_asistencia_comentario = asis._id and  comen._id= :id")
    fun getOneComentario(id: String): LiveData<List<comentario>>

    @Query("SELECT COUNT(*) FROM comentario_table")
    fun getContComentario(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComentario(new: comentario)

    @Query("DELETE FROM comentario_table")
    fun deleteAllComentario()

    @Query("DELETE FROM comentario_table WHERE _id= :id")
    fun deleteOneComentario(id: String)
}