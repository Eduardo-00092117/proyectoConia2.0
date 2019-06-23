package com.example.proyectoconia.Database.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.proyectoconia.Database.Entities.anotacion
import com.example.proyectoconia.Database.Entities.contacto
@Dao
interface contactoDao {
    @Query("SELECT * FROM contacto_table")
    fun getAllContacto(): LiveData<List<contacto>>

    @Query("SELECT con._id, con.titulo , con.descripcion, con.imagen FROM contacto_table con WHERE  _id = :id")
    fun getOneContacto(id: String): LiveData<List<contacto>>

    @Query("SELECT COUNT(*) FROM contacto_table")
    fun getContContacto(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContacto(new: contacto)

    @Query("DELETE FROM contacto_table")
    fun deleteAllContacto()

    @Query("DELETE FROM contacto_table WHERE _id= :id")
    fun deleteOneContacto(id: String)
}