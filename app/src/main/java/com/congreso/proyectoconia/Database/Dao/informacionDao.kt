package com.congreso.proyectoconia.Database.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.congreso.proyectoconia.Database.Entities.genero
import com.congreso.proyectoconia.Database.Entities.informacion
@Dao
interface informacionDao {

    @Query("SELECT * FROM informacion_table")
    fun getAllInformacion(): LiveData<List<informacion>>

    @Query("SELECT _id, icono, titulo, subtitulo, descripcion FROM informacion_table WHERE  _id= :id")
    fun getOneInformacion(id: String): LiveData<List<informacion>>

    @Query("SELECT COUNT(*) FROM informacion_table")
    fun getContInformacion(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInformacion(new: informacion)

    @Query("DELETE FROM informacion_table")
    fun deleteAllInformacion()

    @Query("DELETE FROM informacion_table WHERE _id= :id")
    fun deleteOneInformacion(id: String)
}