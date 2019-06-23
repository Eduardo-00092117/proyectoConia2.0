package com.example.proyectoconia.Database.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.proyectoconia.Database.Entities.comentario
import com.example.proyectoconia.Database.Entities.fechaImportante
@Dao
interface fechaImportanteDao {

    @Query("SELECT * FROM fechasImportantes_table ORDER BY titulo ASC")
    fun getAllFechasImportantes(): LiveData<List<fechaImportante>>

    @Query("SELECT _id, titulo, fecha FROM fechasImportantes_table WHERE _id= :id")
    fun getOneFechasImportantes(id: String): LiveData<List<fechaImportante>>

    @Query("SELECT COUNT(*) FROM fechasImportantes_table")
    fun getContFechasImportantes(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFechasImportantes(new: fechaImportante)

    @Query("DELETE FROM fechasImportantes_table")
    fun deleteAllFechasImportantes()

    @Query("DELETE FROM fechasImportantes_table WHERE _id = :id")
    fun deleteOneFechasImportantes(id: String)
}
