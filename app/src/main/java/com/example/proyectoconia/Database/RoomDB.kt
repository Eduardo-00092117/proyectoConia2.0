package com.example.proyectoconia.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.proyectoconia.Database.Dao.*
import com.example.proyectoconia.Database.Entities.*

@Database(
    entities = [anotacion::class,
                asistencia::class,
                carrera::class,
                comentario::class,
                genero::class,
                nivel::class,
                pais::class,
                ponenteXprogramacion::class,
                ponente::class,
                programacion::class,
                tipo::class,
                usuario::class,
                contacto::class,
                patrocinio::class,
                curso::class,
                tematica::class,
                galeria::class,
                fechaImportante::class,
                informacion::class],version = 10, exportSchema = false)
abstract class RoomDB : RoomDatabase(){
    abstract fun anotacionDao() : anotacionDao
    abstract fun asistenciaDao() : asistenciaDao
    abstract fun carreraDao() : carreraDao
    abstract fun comentarioDao() : comentarioDao
    abstract fun generoDao() : generoDao
    abstract fun nivelDao() : nivelDao
    abstract fun paisDao() : paisDao
    abstract fun ponenteXprogramacionDao() : ponenteXprogramacionDao
    abstract fun ponenteDao() : ponenteDao
    abstract fun programacionDao() : programacionDao
    abstract fun tipoDao() : tipoDao


    abstract  fun usuarioDao(): usuarioDao
    abstract  fun contactoDao(): contactoDao
    abstract  fun patrocionoDao(): patrocinioDao
    abstract  fun tematicaDao(): tematicaDao
    abstract  fun cursoDao(): cursoDao
    abstract  fun galeriaDao(): galeriaDao
    abstract  fun fechasImportantesDao(): fechaImportanteDao
    abstract  fun informacionDao(): informacionDao

    companion object{
        @Volatile
        private var INSTANCE: RoomDB?=null

        fun getDataBase(
            context: Context
        ):RoomDB{
            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RoomDB::class.java,
                    "usuarios_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE=instance
                instance
            }
        }
    }
}