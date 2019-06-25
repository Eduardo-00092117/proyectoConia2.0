package com.example.proyectoconia.Database.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.proyectoconia.Database.Entities.ponente
import com.example.proyectoconia.Database.Entities.ponenteXprogramacion
@Dao
interface ponenteXprogramacionDao {
    @Query("SELECT pt._id, pt.foto, pt.nombre, pt.descripcion, pt.destacado FROM ponente_x_programacion_table pxp, ponentes_table pt, programacion_table pot WHERE pxp.id_programacionx = pot._id and pxp.id_ponentesx = pt._id and pxp.id_programacionx= :id ORDER BY pt.nombre")
    fun getOnePonenteXProgramacion(id: String): LiveData<List<ponente>>

    @Query("SELECT COUNT(*) FROM ponente_x_programacion_table")
    fun getContPonenteXProgramacion(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPonenteXProgramacion(new: ponenteXprogramacion)

    @Query("DELETE FROM ponente_x_programacion_table")
    fun deleteAllPonenteXProgramacion()

    @Query("DELETE FROM ponente_x_programacion_table WHERE id_ponentesx= :id")
    fun deleteOnePonenteXProgramacion(id: String)
}