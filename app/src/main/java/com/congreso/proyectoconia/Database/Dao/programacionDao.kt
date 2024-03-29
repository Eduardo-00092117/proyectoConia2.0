package com.congreso.proyectoconia.Database.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.congreso.proyectoconia.Database.Entities.ponente
import com.congreso.proyectoconia.Database.Entities.programacion

@Dao
interface programacionDao {

    @Query("SELECT * FROM programacion_table WHERE numeroDia = :dia ORDER BY fecha ASC, hora_inicio ASC")
    fun getAllProgramacion(dia : String): LiveData<List<programacion>>

    @Query("SELECT * FROM programacion_table ORDER BY fecha ASC, hora_inicio ASC")
    fun getAllProgramacion2(): LiveData<List<programacion>>

    @Query("SELECT * FROM programacion_table WHERE  descripcion= :id")
    fun getOneProgramacion(id: String): programacion

    @Query("SELECT * FROM programacion_table WHERE  _id= :id")
    fun getOneProgramacion2(id: String): LiveData<programacion>

    @Query("SELECT COUNT(*) FROM programacion_table")
    fun getContProgramacion(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProgramacion(new: programacion)

    @Query("DELETE FROM programacion_table")
    fun deleteAllProgramacion()

    @Query("DELETE FROM programacion_table WHERE _id= :id")
    fun deleteOneProgramacion(id: String)
}