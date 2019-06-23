package com.example.proyectoconia.Database.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.proyectoconia.Database.Entities.usuario

@Dao
interface usuarioDao {
    @Query("SELECT * FROM usuario_table")
    fun getAllUsuario(): LiveData<List<usuario>>

    @Query("SELECT * FROM usuario_table usuario, genero_table genero, tipo_table tipo, nivel_table nivel, pais_table pais, carrera_table carrera WHERE genero._id= usuario.fk_genero_usuario and tipo._id= usuario.fk_tipo_usuario and carrera._id= usuario.fk_carrera_usuario and pais._id= usuario.fk_pais_usuario and nivel._id = usuario.fk_usuario_nivel and usuario._id= :id")
    fun getOneUsuario(id: String): LiveData<List<usuario>>

    @Query("SELECT COUNT(*) FROM usuario_table")
    fun getContUsuario(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsuario(new: usuario)

    @Query("DELETE FROM tipo_table")
    fun deleteUsuario()

    @Query("DELETE FROM usuario_table WHERE _id= :id")
    fun deleteOneUsuario(id: String)
}