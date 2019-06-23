package com.example.proyectoconia.Database.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.proyectoconia.Database.Entities.galeria

@Dao
interface galeriaDao {
    @Query("SELECT * FROM galeria_table ORDER BY _id")
    fun getAllGaleria(): LiveData<List<galeria>>

    @Query("SELECT _id, imagen FROM galeria_table WHERE  _id= :id")
    fun getOneGaleria(id: String): LiveData<List<galeria>>

    @Query("SELECT COUNT(*) FROM galeria_table")
    fun getContGaleria(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGaleria(new: galeria)

    @Query("DELETE FROM galeria_table")
    fun deleteAllGaleria()

    @Query("DELETE FROM galeria_table WHERE _id= :id")
    fun deleteOneGaleria(id: String)
}