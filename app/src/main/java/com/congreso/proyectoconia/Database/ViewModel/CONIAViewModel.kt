package com.congreso.proyectoconia.Database.ViewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.congreso.proyectoconia.Database.Entities.*
import com.congreso.proyectoconia.Database.Repository.CONIARepository
import com.congreso.proyectoconia.Database.RoomDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class CONIAViewModel(var app : Application) : AndroidViewModel(app) {
    private var repository : CONIARepository


    init {
        val anotacionDao = RoomDB.getDataBase(app).anotacionDao()
        val asistenciaDao = RoomDB.getDataBase(app).asistenciaDao()
        val carreraDao = RoomDB.getDataBase(app).carreraDao()
        val comentarioDao = RoomDB.getDataBase(app).comentarioDao()
        val contactoDao = RoomDB.getDataBase(app).contactoDao()
        val cursoDao = RoomDB.getDataBase(app).cursoDao()
        val fechaImportanteDao = RoomDB.getDataBase(app).fechasImportantesDao()
        val galeriaDao = RoomDB.getDataBase(app).galeriaDao()
        val generoDao = RoomDB.getDataBase(app).generoDao()
        val informacionDao = RoomDB.getDataBase(app).informacionDao()
        val nivelDao = RoomDB.getDataBase(app).nivelDao()
        val paisDao = RoomDB.getDataBase(app).paisDao()
        val patrocinioDao = RoomDB.getDataBase(app).patrocionoDao()
        val ponenteDao = RoomDB.getDataBase(app).ponenteDao()
        val ponenteXprogramacionDao = RoomDB.getDataBase(app).ponenteXprogramacionDao()
        val programacionDao = RoomDB.getDataBase(app).programacionDao()
        val tematicaDao = RoomDB.getDataBase(app).tematicaDao()
        val tipoDao = RoomDB.getDataBase(app).tipoDao()
        val usuarioDao = RoomDB.getDataBase(app).usuarioDao()

        repository = CONIARepository(anotacionDao, asistenciaDao, carreraDao, comentarioDao, contactoDao, cursoDao, fechaImportanteDao, galeriaDao,
            generoDao, informacionDao, nivelDao, paisDao, patrocinioDao, ponenteDao, ponenteXprogramacionDao, programacionDao, tematicaDao,
            tipoDao, usuarioDao)
    }


    //----------------------------------------------TEMATICA----------------------------------------------------

    fun insertTematica(tematica: tematica) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertTematica(tematica)
    }

    fun deleteAllTematica() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAllTematica()
    }

    fun getAllTematica() = repository.getAllTematica()

    fun sincronizarTematica() = viewModelScope.launch{(Dispatchers.IO)
        deleteAllTematica()
        val response = repository.tematicaAsync().await()
        if (response!!.isSuccessful) with(response.body()?.Search){
            this?.forEach{
                insertTematica(it)
            }
        }
    }




    //----------------------------------------------ANOTACION----------------------------------------------------

    fun insertAnotacion(anotacion:anotacion) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertAnotacion(anotacion)
    }

    fun getAllAnotacion(correo : String)= repository.getAllAnotacion(correo)

    fun deleteAllAnotacion()= viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAllAnotacion()
    }

    fun sincronizarAnotacion() = viewModelScope.launch(Dispatchers.IO) {
        deleteAllAnotacion()
        val response = repository.anotacionAsync().await()
        if(response!!.isSuccessful) with (response.body()){
            this?.forEach{
                insertAnotacion(anotacion(it._id, it.titulo,it.fecha,it.archivo,it.usuario[0]._id,it.programacion[0]._id))
            }
        }
    }

    fun setAnotacionApi(titulo:String,fecha:String,archivo:String,usuario:String,programacion:String) =

        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.setAnotacionApi(titulo,fecha,archivo,
                getOneUsuario(usuario)._id,getOneProgramacion(programacion)._id)
            if(response.execute().isSuccessful){
                Log.d("insertar","Inserto la anotacion")
            }
            sincronizarAnotacion()
        }

    fun updateAnotacionApi(id:String,titulo:String,fecha:String,archivo:String,usuario:String,programacion: String)=
        viewModelScope.launch(Dispatchers.IO){
            val response = repository.updateAnotacionApi(id,titulo,fecha,archivo,
                getOneUsuario(usuario)._id,getOneProgramacion(programacion)._id)
            if(response.execute().isSuccessful){
                Log.d("update","Actualizo")
            }
            sincronizarAnotacion()
        }

    fun deleteAnotacionApi(id : String) = viewModelScope.launch(Dispatchers.IO) {
        val response = repository.deleteAnotacionApi(id)
        if (response.execute().isSuccessful){
            Log.d("Hola", "SOY LA OSTIA POR QUE YA LO ELIMINA")
        }
        sincronizarAnotacion()
    }





    //----------------------------------------------INFORMACION--------------------------------------------------
    fun insertInfo(info : informacion) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertInformacion(info)
    }

    fun getAllInformacion() = repository.getAllInformacion()

    fun deleteAllInformacion() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAllInformacion()
    }

    fun sincronizarInformacion() = viewModelScope.launch{(Dispatchers.IO)
        deleteAllInformacion()
        val response = repository.infoAsync().await()
        if (response!!.isSuccessful) with(response.body()?.Search){
            this?.forEach{
                insertInfo(it)
            }
        }
    }

    //---------------------------------------------GENERO---------------------------------------------------------
    fun insertGenero(genero : genero) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertGenero(genero)
    }

    fun sincronizarGenero() = viewModelScope.launch{(Dispatchers.IO)
        val response = repository.generoAsync().await()
        if (response!!.isSuccessful) with(response.body()?.Search){
            this?.forEach{
                insertGenero(it)
            }
        }
    }

    fun getAllGenero() = repository.getAllGenero()

    fun getOneGenero(nombre : String) = repository.getUnaGenero(nombre)


    //---------------------------------------------PAIS---------------------------------------------------------
    fun insertPais(pais : pais) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertPais(pais)
    }

    fun getAllPais() = repository.getAllPais()

    fun sincronizarPais() = viewModelScope.launch{(Dispatchers.IO)
        val response = repository.paisAsync().await()
        if (response!!.isSuccessful) with(response.body()?.Search){
            this?.forEach{
                insertPais(it)
            }
        }
    }

    fun getOnePais(nombre : String) = repository.getUnaPais(nombre)

    //---------------------------------------------CARRERA---------------------------------------------------------
    fun insertCarrera(carrera : carrera) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertCarrera(carrera)
    }

    fun getAllCarrera() = repository.getAllCarrera()

    fun sincronizarCarrera() = viewModelScope.launch{(Dispatchers.IO)
        val response = repository.carreraAsync().await()
        if (response!!.isSuccessful) with(response.body()?.Search){
            this?.forEach{
                insertCarrera(it)
            }
        }
    }

    fun getOneCarrera(nombre : String) = repository.getUnaCarrera(nombre)


    //---------------------------------------------NIVEL---------------------------------------------------------
    fun insertNivel(nivel : nivel) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertNivel(nivel)
    }

    fun getAllNivel() = repository.getAllNivel()

    fun sincronizarNivel() = viewModelScope.launch{(Dispatchers.IO)
        val response = repository.nivelAsync().await()
        if (response!!.isSuccessful) with(response.body()?.Search){
            this?.forEach{
                insertNivel(it)
            }
        }
    }

    fun getOneNivel(nombre : String) = repository.getUnaNivel(nombre)


    //---------------------------------------------TIPO---------------------------------------------------------
    fun insertTipo(tipo : tipo) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertTipo(tipo)
    }

    fun getAllTipo() = repository.getAllTipo()

    fun sincronizarTipo() = viewModelScope.launch{(Dispatchers.IO)
        val response = repository.tipoAsync().await()
        if (response!!.isSuccessful) with(response.body()?.Search){
            this?.forEach{
                insertTipo(it)
            }
        }
    }

    fun getOneTipo(nombre : String) = repository.getUnaTipo(nombre)


    //---------------------------------------------USUARIO---------------------------------------------------------
    fun insertUsuario(usuario : usuario) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertUsuario(usuario)
    }

    fun getAllUsuario() = repository.getAllUsuario()

    fun getOneUsuario(correo : String) = repository.getUnaUsuario(correo)

    fun deleteAllUsuario() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAllUsuario()
    }

    fun sincronizarUsuario() = viewModelScope.launch{(Dispatchers.IO)
        deleteAllUsuario()
        val response = repository.usuarioAsync().await()
        if (response!!.isSuccessful) with(response.body()){
            this?.forEach{
                insertUsuario(usuario(it._id, it.nombre, it.apellido, it.correo, it.pass, it.empresa, it.formacion, it.institutoEmpresa,
                    it.genero[0]._id, it.tipo[0]._id, it.carrera[0]._id, it.pais[0]._id, it.nivel[0].nombre))
            }
        }
    }

    fun setUsuarioApi(nombre : String, apellido : String, pass : String, correo : String, genero : String, pais : String, carrera : String, nivel : String,
                      empresa : String, formacion : String, institutoEmpresa : String, tipo : String) = viewModelScope.launch(Dispatchers.IO) {
        val response = repository.setUsuarioApi(nombre, apellido, pass, correo, getOneGenero(genero)._id, getOnePais(pais)._id, getOneCarrera(carrera)._id, getOneNivel(nivel)._id, empresa, formacion, institutoEmpresa, getOneTipo(tipo)._id)
        if (response.execute().isSuccessful){
            Log.d("Hola", "SOY LA OSTIA POR QUE YA LO INGRESA")
        }
    }

    //---------------------------------------------FECHAS---------------------------------------------------------
    fun insertFecha(fecha : fechaImportante) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertFechasImportantes(fecha)
    }

    fun getAllFechas() = repository.getAllFechasImportantes()

    fun deleteAllFechas() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAllFechasImportantes()
    }

    fun sincronizarFecha() = viewModelScope.launch{(Dispatchers.IO)
        deleteAllFechas()
        val response = repository.fechaAsync().await()
        if (response!!.isSuccessful) with(response.body()?.Search){
            this?.forEach{
                insertFecha(it)
            }
        }
    }


    //---------------------------------------------PATROCINADORES---------------------------------------------------------
    fun insertPatrocinio(patrocinio : patrocinio) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertPatrocinio(patrocinio)
    }

    fun getAllPatrocinio() = repository.getAllPatrocinio()

    fun deleteAllPatrocinio() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAllPatrocinio()
    }

    fun sincronizarPatrocinio() = viewModelScope.launch{(Dispatchers.IO)
        deleteAllPatrocinio()
        val response = repository.patrocinioAsync().await()
        if (response!!.isSuccessful) with(response.body()?.Search){
            this?.forEach{
                insertPatrocinio(it)
            }
        }
    }

    //---------------------------------------------CURSOS---------------------------------------------------------
    fun insertCurso(curso : curso) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertCurso(curso)
    }

    fun deleteAllCursos() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAllCurso()
    }

    fun getAllCurso() = repository.getAllCurso()

    fun sincronizarCurso() = viewModelScope.launch{(Dispatchers.IO)
        deleteAllCursos()
        val response = repository.cursoAsync().await()
        if (response!!.isSuccessful) with(response.body()?.Search){
            this?.forEach{
                insertCurso(it)
            }
        }
    }

    //---------------------------------------------CURSOS---------------------------------------------------------
    fun insertGaleria(galeria : galeria) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertGaleria(galeria)
    }

    fun deleteAllGaleria() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAllGaleria()
    }

    fun getAllGaleria() = repository.getAllGaleria()

    fun sincronizarGaleria() = viewModelScope.launch{(Dispatchers.IO)
        deleteAllGaleria()
        val response = repository.galeriaAsync().await()
        if (response!!.isSuccessful) with(response.body()?.Search){
            this?.forEach{
                insertGaleria(it)
            }
        }
    }

    //---------------------------------------------CONTACTO---------------------------------------------------------
    fun insertContacto(contacto : contacto) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertContacto(contacto)
    }

    fun deleteAllContacto() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAllContacto()
    }

    fun getAllContacto() = repository.getAllContacto()

    fun sincronizarContacto() = viewModelScope.launch{(Dispatchers.IO)
        deleteAllContacto()
        val response = repository.contactoAsync().await()
        if (response!!.isSuccessful) with(response.body()?.Search){
            this?.forEach{
                insertContacto(it)
            }
        }
    }

    //---------------------------------------------PONENTE---------------------------------------------------------
    fun insertPonente(ponente : ponente) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertPonente(ponente)
    }

    fun deleteAllPonente() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAllPonente()
    }

    fun getAllPonente() = repository.getAllPonente()

    fun sincronizarPonente() = viewModelScope.launch{(Dispatchers.IO)
        deleteAllPonente()
        val response = repository.ponenteAsync().await()
        if (response!!.isSuccessful) with(response.body()?.Search){
            this?.forEach{
                insertPonente(it)
            }
        }
    }

    //---------------------------------------------PROGRAMACIÓN---------------------------------------------------------
    fun insertProgramacion(programacion : programacion) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertProgramacion(programacion)
    }

    fun deleteAllProgramacion() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAllProgramacion()
    }

    fun getAllProgramacion(dia : String) = repository.getAllProgramacion(dia)

    fun getAllProgramacion2() = repository.getAllProgramacion2()

    fun getOneProgramacion(nombre: String) = repository.getUnaProgramacion(nombre)

    fun getOneProgramacion2(id : String) = repository.getOneProgramacion2(id)

    fun insertProgramacionxPonente(ponenteXprogramacion: ponenteXprogramacion) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertPonentxProgra(ponenteXprogramacion)
    }

    fun deleteProgramacionxPonente() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAllPonentxProgra()
    }

    fun getPonentes(id : String) = repository.getUnaPonentxProgra(id)

    fun sincronizarProgramacion() = viewModelScope.launch{(Dispatchers.IO)
        deleteAllProgramacion()
        deleteProgramacionxPonente()
        val response = repository.programacionAsync().await()
        if (response!!.isSuccessful) with(response.body()){
            this?.forEach{
                insertProgramacion(programacion(it._id, it.numeroDia, it.fecha, it.lugar, it.hora_inicio, it.hora_fin, it.descripcion))
                it.ponente.forEach { ponente ->
                    insertProgramacionxPonente(ponenteXprogramacion(ponente._id, it._id))
                }
            }
        }
    }

    //---------------------------------------------Asistencia---------------------------------------------------------

    fun getAllAsistencia() = repository.getAllAsistencia()

    fun insertAsistencia(asistencia: asistencia) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertAsistencia(asistencia)
    }

    fun deleteAllAsistencia() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAllAsistencia()
    }

    fun sincronizarAsistencia() = viewModelScope.launch(Dispatchers.IO) {
        deleteAllAsistencia()
        val response = repository.asistenciaAsync().await()
        if (response!!.isSuccessful) with(response.body()){
            this?.forEach {
                it.programacion?.forEach { programacion ->
                    insertAsistencia(asistencia(it._id, it.calificacion, it.usuario[0]._id, programacion._id))
                }
            }
        }
    }

    fun getOneAsistencia(id : String) = repository.getUnaAsistencia(id)

    /*fun deleteAsistenciaApi(usuario : String) = viewModelScope.launch(Dispatchers.IO) {
        var id_usuario = getOneUsuario(usuario)._id
        val response = repository.deleteAsistenciaApi(getOneAsistencia(id_usuario)._id)
        if (response.execute().isSuccessful){
            Log.d("Hola", "SOY LA OSTIA POR QUE YA LO ELIMINA")
        }
    }*/

    fun getOneAsistenciaUsuario(id : String) : LiveData<List<asistencia>> = repository.getOneAsistenciaUsuario(id)

    fun getOneAsistenciaUsuario2(id : String) : List<asistencia> = repository.getOneAsistenciaUsuario2(id)

    fun getContAsistencia(id : String) = repository.getContAsistencia(id)

    fun getUsuarioPonencia(correo : String, id_programacion : String) = repository.getOneAsistenciaUsuarioPonencia(correo, id_programacion)

    fun updateAsistenciaCalifiacion(usuario: String, programacion: String, calificacion: Float) = viewModelScope.launch (Dispatchers.IO){
        var response = repository.updateAsistenciaApi(getUsuarioPonencia(usuario, programacion)._id, getOneUsuario(usuario)._id, programacion, calificacion)
        if (response.execute().isSuccessful){
            Log.d("Hola", "Si funciona!!")
        }
        sincronizarAsistencia()
    }

    fun updateoinsertAsistenciaApi(usuario : String, programa: ArrayList<String>, calificacion: Float) = viewModelScope.launch(Dispatchers.IO) {
        var id_usuario = getOneUsuario(usuario)._id

        getOneAsistencia(id_usuario).forEach {
            val response = repository.deleteAsistenciaApi(it._id)
            if (response.execute().isSuccessful){
                Log.d("Hola", "SOY LA OSTIA POR QUE YA LO ELIMINA")
            }
        }

        programa.forEach {
            var bandera = 0
            try {
                getUsuarioPonencia(usuario, it).calificacion
            } catch (exception : Exception){
                bandera = 1
            }

            if (bandera == 1){
                val response = repository.setAsistenciaApi(id_usuario, it, calificacion)
                if (response.execute().isSuccessful){
                    Log.d("Hola", "SOY LA OSTIA POR QUE YA LO INGRESA")
                }
            } else{
                val response = repository.setAsistenciaApi(id_usuario, it, getUsuarioPonencia(usuario, it).calificacion)
                if (response.execute().isSuccessful){
                    Log.d("Hola", "SOY LA OSTIA POR QUE YA LO INGRESA")
                }
            }
        }
        sincronizarAsistencia()
    }

    fun getProgramaAsistencia(id: String) : LiveData<List<programacion>> {
        return repository.getProgramaAsistencia(id)
    }


    //-----------------------------------------------------COMENTARIOS----------------------------------------------

    fun insertComentario(comentario: comentario) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertComentario(comentario)
    }

    fun comentarioAsync() = viewModelScope.launch (Dispatchers.IO){
        var response = repository.comentarioAsync().await()
        if (response!!.isSuccessful) with(response.body()){
            this?.forEach {
                insertComentario(comentario(it._id, it.comentario, it.fecha, it.hora, it.asistencia[0]._id))
            }
        }
    }

    fun getAllComentario(id : String) = repository.getUnaComentario(id)

    fun insertComentarioApi(usuario: String, id_programacion: String, comentario : String, fecha : String, hora : String) = viewModelScope.launch (Dispatchers.IO) {

        var id_asistencia = getUsuarioPonencia(usuario, id_programacion)

        var response = repository.setComentarioApi(id_asistencia._id, comentario, fecha, hora)

        if (response.execute().isSuccessful){
            Log.d("Hola", "Suuuuu")
        }
        comentarioAsync()
    }
}