package com.congreso.proyectoconia.Database.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.congreso.proyectoconia.Database.Entities.usuario

@Dao
interface usuarioDao {
    @Query("SELECT * FROM usuario_table")
    fun getAllUsuario(): LiveData<List<usuario>>

    @Query("SELECT * FROM usuario_table usuario WHERE  usuario.correo= :correo")
    fun getOneUsuario(correo: String): usuario

    @Query("SELECT COUNT(*) FROM usuario_table")
    fun getContUsuario(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsuario(new: usuario)

    @Query("DELETE FROM tipo_table")
    fun deleteUsuario()

    @Query("DELETE FROM usuario_table WHERE _id= :id")
    fun deleteOneUsuario(id: String)
}