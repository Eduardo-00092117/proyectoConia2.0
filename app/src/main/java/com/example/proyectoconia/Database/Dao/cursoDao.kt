package com.example.proyectoconia.Database.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.proyectoconia.Database.Entities.comentario
import com.example.proyectoconia.Database.Entities.curso
@Dao
interface cursoDao {
    @Query("SELECT * FROM curso_table ORDER BY titulo ASC")
    fun getAllCurso(): LiveData<List<curso>>

    @Query("SELECT _id, titulo, imagen FROM curso_table WHERE  _id = :id")
    fun getOneCurso(id: String): LiveData<List<curso>>

    @Query("SELECT COUNT(*) FROM curso_table")
    fun getContCurso(): Int
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurso(new: curso)

    @Query("DELETE FROM curso_table")
    fun deleteAllCurso()

    @Query("DELETE FROM curso_table WHERE _id = :id")
    fun deleteOneCurso(id: String)
}
