package com.example.proyectoconia.Database.Repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.proyectoconia.Database.Dao.*
import com.example.proyectoconia.Database.Entities.*
import com.example.proyectoconia.Database.ModelsRetrofit.*
import com.example.proyectoconia.Database.Retrofit.retroFitServices
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response

class CONIARepository(
    private val Anotacion: anotacionDao,
    private val Asistenia: asistenciaDao,
    private val Carrera: carreraDao,
    private val Comentario: comentarioDao,
    private val Contacto: contactoDao,
    private val Curso: cursoDao,
    private val FechaImportante: fechaImportanteDao,
    private val Galeria: galeriaDao,
    private val Genero: generoDao,
    private val Informacion: informacionDao,
    private val Nivel: nivelDao,
    private val Pais: paisDao,
    private val Patrocinio: patrocinioDao,
    private val Ponente: ponenteDao,
    private val PonenteXProgramacion: ponenteXprogramacionDao,
    private val Programacion: programacionDao,
    private val Tematica: tematicaDao,
    private val Tipo: tipoDao,
    private val Usuario: usuarioDao
) {

    //  0-------------------------------------RETROFIT------------------------------

    fun infoAsync() : Deferred<Response<retroInformacion>>{
        return retroFitServices.getRetrofit().getInformacion()
    }

    fun generoAsync() : Deferred<Response<retroGenero>>{
        return retroFitServices.getRetrofit().getGenero()
    }

    fun paisAsync() : Deferred<Response<retroPais>>{
        return retroFitServices.getRetrofit().getPais()
    }

    fun carreraAsync() : Deferred<Response<retroCarrera>>{
        return retroFitServices.getRetrofit().getCarrera()
    }

    fun nivelAsync() : Deferred<Response<retroNivel>>{
        return retroFitServices.getRetrofit().getNivel()
    }

    fun tipoAsync() : Deferred<Response<retroTipo>>{
        return retroFitServices.getRetrofit().getTipo()
    }

    fun usuarioAsync() : Deferred<Response<List<modeloUsuario>>>{
        return retroFitServices.getRetrofit().getUsuario()
    }

    fun setUsuarioApi(nombre : String, apellido : String, pass : String, correo : String, genero : String, pais : String, carrera : String, nivel : String,
                      empresa : String, formacion : String, institutoEmpresa : String, tipo : String) : Call<usuario> {
        return retroFitServices.getRetrofit().setUsuario(nombre, apellido, pass, correo, genero, pais, carrera, nivel, empresa, formacion, institutoEmpresa, tipo)
    }

    fun fechaAsync() : Deferred<Response<retroFecha>>{
        return retroFitServices.getRetrofit().getFecha()
    }

    fun patrocinioAsync() : Deferred<Response<retroPatrocinio>>{
        return retroFitServices.getRetrofit().getPatrocinio()
    }

    fun cursoAsync() : Deferred<Response<retroCurso>>{
        return retroFitServices.getRetrofit().getCurso()
    }

    fun galeriaAsync() : Deferred<Response<retroGaleria>>{
        return retroFitServices.getRetrofit().getGaleria()
    }

    fun contactoAsync() : Deferred<Response<retroContacto>>{
        return retroFitServices.getRetrofit().getContacto()
    }

    fun ponenteAsync() : Deferred<Response<retroPonente>>{
        return retroFitServices.getRetrofit().getPonente()
    }

    fun programacionAsync() : Deferred<Response<List<modeloProgramacion>>>{
        return retroFitServices.getRetrofit().getProgramacion()
    }

    fun asistenciaAsync() : Deferred<Response<List<modeloAsistencia>>>{
        return retroFitServices.getRetrofit().getAsistencia()
    }

    fun setAsistenciaApi(usuario : String, programacion : String, calificacion : Float) : Call<asistencia> {
        return retroFitServices.getRetrofit().setAsistencia(usuario, programacion, calificacion)
    }

    fun deleteAsistenciaApi(id : String) : Call<asistencia> {
        return retroFitServices.getRetrofit().deleteAsistencia(id)
    }

    fun updateAsistenciaApi(id : String, usuario : String, programacion: String, calificacion: Float) : Call<asistencia> {
        return retroFitServices.getRetrofit().updateAsistencia(id, usuario, programacion, calificacion)
    }

    //  1-------------------------------------ANOTACION------------------------------

    fun getAllAnotacion(): LiveData<List<anotacion>> = Anotacion.getAllAnotacione()
    @WorkerThread
    suspend fun insertAnotacion(anot: anotacion) = Anotacion.insertAnotacion(anot)

    fun getUnaAnotacion(id: String): LiveData<List<anotacion>> = Anotacion.getOneAnotacion(id)
    fun getContAnotacion() = Anotacion.getContAnotacion()
    fun deleteAllAnotacion() = Anotacion.deleteAllAnotacion()
    fun deleteOneAnotacion(id: String) = Anotacion.deleteOneAnotacion(id)


    //  2-------------------------------------ASISTENCIA------------------------------
    fun getAllAsistencia(): LiveData<List<asistencia>> = Asistenia.getAllAnsistencia()

    @WorkerThread
    suspend fun insertAsistencia(asis: asistencia) = Asistenia.insertAsistencia(asis)

    fun getUnaAsistencia(id: String): asistencia = Asistenia.getOneAsistencia(id)
    fun getContAsistencia(id : String) = Asistenia.getContAsistencia(id)
    fun deleteAllAsistencia() = Asistenia.deleteAllAsistencia()
    fun deleteOneAsistencia(id: String) = Asistenia.deleteOneAsistencia(id)

    fun getProgramaAsistencia(id : String): LiveData<List<programacion>> = Asistenia.getProgramaAsistencia(id)

    //  3-------------------------------------CARRERA---------------------------------
    fun getAllCarrera(): LiveData<List<carrera>> = Carrera.getAllCarrera()

    @WorkerThread
    suspend fun insertCarrera(carr: carrera) = Carrera.insertCarrera(carr)

    fun getUnaCarrera(id: String): carrera = Carrera.getOneCarrea(id)
    fun getContCarrera() = Carrera.getContCarrera()
    fun deleteAllCarrera() = Carrera.deleteAllCarrera()
    fun deleteOneCarrera(id: String) = Carrera.deleteOneCarrera(id)
    //  4-------------------------------------COMENTARIO------------------------------

    fun getAllComentario(): LiveData<List<comentario>> = Comentario.getAllComentario()

        @WorkerThread
        suspend fun insertComentario(carr: comentario) = Comentario.insertComentario(carr)

    fun getUnaComentario(id: String): LiveData<List<comentario>> = Comentario.getOneComentario(id)
    fun getContComentario() = Comentario.getContComentario()
    fun deleteAllComentario() = Comentario.deleteAllComentario()
    fun deleteOneComentario(id: String) = Comentario.deleteOneComentario(id)
    //  5-------------------------------------CONTACTO------------------------------
    fun getAllContacto(): LiveData<List<contacto>> = Contacto.getAllContacto()

        @WorkerThread
        suspend fun insertContacto(carr: contacto) = Contacto.insertContacto(carr)

    fun getUnaContacto(id: String): LiveData<List<contacto>> = Contacto.getOneContacto(id)
    fun getContContacto() = Contacto.getContContacto()
    fun deleteAllContacto() = Contacto.deleteAllContacto()
    fun deleteOneContacto(id: String) = Contacto.deleteOneContacto(id)
    //  6-------------------------------------CURSO------------------------------
    fun getAllCurso(): LiveData<List<curso>> = Curso.getAllCurso()

        @WorkerThread
        suspend fun insertCurso(carr: curso) = Curso.insertCurso(carr)

    fun getUnaCurso(id: String): LiveData<List<curso>> = Curso.getOneCurso(id)
    fun getContCurso() = Curso.getContCurso()
    fun deleteAllCurso() = Curso.deleteAllCurso()
    fun deleteOneCurso(id: String) = Curso.deleteOneCurso(id)
    //  7-------------------------------------FECHASIMPORTANTES------------------------------
    fun getAllFechasImportantes(): LiveData<List<fechaImportante>> = FechaImportante.getAllFechasImportantes()

        @WorkerThread
        suspend fun insertFechasImportantes(carr: fechaImportante) = FechaImportante.insertFechasImportantes(carr)

    fun getUnaFechasImportantes(id: String): LiveData<List<fechaImportante>> = FechaImportante.getOneFechasImportantes(id)
    fun getContFechasImportantes() =FechaImportante.getContFechasImportantes()
    fun deleteAllFechasImportantes() = FechaImportante.deleteAllFechasImportantes()
    fun deleteOneFechasImportantes(id: String) = FechaImportante.deleteOneFechasImportantes(id)
    //  8-------------------------------------GALERIA------------------------------
    fun getAllGaleria(): LiveData<List<galeria>> = Galeria.getAllGaleria()

        @WorkerThread
        suspend fun insertGaleria(carr: galeria) = Galeria.insertGaleria(carr)

    fun getUnaGaleria(id: String): LiveData<List<galeria>> = Galeria.getOneGaleria(id)
    fun getContGaleria() = Galeria.getContGaleria()
    fun deleteAllGaleria() = Galeria.deleteAllGaleria()
    fun deleteOneGaleria(id: String) = Galeria.deleteOneGaleria(id)
    //  9-------------------------------------GENERO------------------------------
    fun getAllGenero(): LiveData<List<genero>> = Genero.getAllGenero()

        @WorkerThread
        suspend fun insertGenero(carr: genero) = Genero.insertGenero(carr)

    fun getUnaGenero(id: String): genero = Genero.getOneGenero(id)
    fun getContGenero() = Genero.getContGenero()
    fun deleteAllGenero() = Genero.deleteAllGenero()
    fun deleteOneGenero(id: String) = Genero.deleteOneGenero(id)
    //  10-------------------------------------INFORMACION------------------------------
    fun getAllInformacion(): LiveData<List<informacion>> = Informacion.getAllInformacion()

        @WorkerThread
        suspend fun insertInformacion(carr: informacion) = Informacion.insertInformacion(carr)

    fun getUnaInformacion(id: String): LiveData<List<informacion>> = Informacion.getOneInformacion(id)
    fun getContInformacion() = Informacion.getContInformacion()
    fun deleteAllInformacion() = Informacion.deleteAllInformacion()
    fun deleteOneInformacion(id: String) = Informacion.deleteOneInformacion(id)
    //  11-------------------------------------NIVEL------------------------------
    fun getAllNivel(): LiveData<List<nivel>> = Nivel.getAllNivel()

        @WorkerThread
        suspend fun insertNivel(carr: nivel) = Nivel.insertNivel(carr)

    fun getUnaNivel(id: String): nivel = Nivel.getOneNivel(id)
    fun getContNivel() = Nivel.getContNivel()
    fun deleteAllNivel() = Nivel.deleteAllNivel()
    fun deleteOneNivel(id: String) = Nivel.deleteOneNivel(id)
    //  12-------------------------------------PAIS------------------------------
    fun getAllPais(): LiveData<List<pais>> = Pais.getAllPais()

        @WorkerThread
        suspend fun insertPais(carr:pais) = Pais.insertPais(carr)

    fun getUnaPais(id: String): pais = Pais.getOnePais(id)
    fun getContPais() = Pais.getContPais()
    fun deleteAllPais() = Pais.deleteAllPais()
    fun deleteOnePais(id: String) = Pais.deleteOnePais(id)
    //  13-------------------------------------PATROCINIO------------------------------
    fun getAllPatrocinio(): LiveData<List<patrocinio>> = Patrocinio.getAllPatrocinio()

        @WorkerThread
        suspend fun insertPatrocinio(carr:patrocinio) = Patrocinio.insertPatrocinio(carr)

    fun getUnaPatrocinio(id: String): LiveData<List<patrocinio>> = Patrocinio.getOnePatrocinio(id)
    fun getContPatrocinio() = Patrocinio.getContPatrocinio()
    fun deleteAllPatrocinio() = Patrocinio.deleteAllPatrocinio()
    fun deleteOnePatrocinio(id: String) = Patrocinio.deleteOnePatrocinio(id)
    //  14-------------------------------------PONENTE------------------------------
    fun getAllPonente(): LiveData<List<ponente>> = Ponente.getAllPonente()

        @WorkerThread
        suspend fun insertPonente(carr:ponente) = Ponente.insertPonente(carr)

    fun getUnaPonente(id: String): LiveData<List<ponente>> = Ponente.getOnePonente(id)
    fun getContPonente() = Ponente.getContPonente()
    fun deleteAllPonente() = Ponente.deleteAllPonente()
    fun deleteOnePonente(id: String) = Ponente.deleteOnePonente(id)
    //  15-------------------------------------PONENTEXPROGRAMACION------------------------------

        @WorkerThread
        suspend fun insertPonentxProgra(carr: ponenteXprogramacion) = PonenteXProgramacion.insertPonenteXProgramacion(carr)

    fun getUnaPonentxProgra(id: String): LiveData<List<ponente>> = PonenteXProgramacion.getOnePonenteXProgramacion(id)
    fun getContPonentxProgra() = PonenteXProgramacion.getContPonenteXProgramacion()
    fun deleteAllPonentxProgra() = PonenteXProgramacion.deleteAllPonenteXProgramacion()
    fun deleteOnePonentxProgra(id: String) = PonenteXProgramacion.deleteOnePonenteXProgramacion(id)
    //  16-------------------------------------PROGRAMACION------------------------------
    fun getAllProgramacion(dia : String): LiveData<List<programacion>> = Programacion.getAllProgramacion(dia)

    @WorkerThread
    suspend fun insertProgramacion(carr: programacion) = Programacion.insertProgramacion(carr)

    fun getUnaProgramacion(id: String): LiveData<List<programacion>> = Programacion.getOneProgramacion(id)
    fun getContProgramacion() = Programacion.getContProgramacion()
    fun deleteAllProgramacion() = Programacion.deleteAllProgramacion()
    fun deleteOneProgramacion(id: String) = Programacion.deleteOneProgramacion(id)

    //  17-------------------------------------TEMATICA------------------------------
    fun getAllTematica(): LiveData<List<tematica>> = Tematica.getAllTematica()

        @WorkerThread
        suspend fun insertTematica(carr: tematica) = Tematica.insertTematica(carr)

    fun getUnaTematica(id: String): LiveData<List<tematica>> = Tematica.getOneTematica(id)
    fun getContTematica() = Tematica.getContTematica()
    fun deleteAllTematica() = Tematica.deleteAllTematica()
    fun deleteOneTematica(id: String) = Tematica.deleteOneTematica(id)
    //  18-------------------------------------TIPO------------------------------
    fun getAllTipo(): LiveData<List<tipo>> = Tipo.getAllTipo()

        @WorkerThread
        suspend fun insertTipo(carr: tipo) = Tipo.insertTipo(carr)

    fun getUnaTipo(id: String): tipo = Tipo.getOneTipo(id)
    fun getContTipo() = Tematica.getContTematica()
    fun deleteAllTipo() = Tematica.deleteAllTematica()
    fun deleteOneTipo(id: String) = Tematica.deleteOneTematica(id)
    //  19-------------------------------------USUARIO------------------------------
    fun getAllUsuario(): LiveData<List<usuario>> = Usuario.getAllUsuario()

        @WorkerThread
        suspend fun insertUsuario(carr: usuario) = Usuario.insertUsuario(carr)

    fun getUnaUsuario(id: String): usuario= Usuario.getOneUsuario(id)
    fun getContUsuario() =  Usuario.getContUsuario()
    fun deleteAllUsuario() = Usuario.deleteUsuario()
    fun deleteOneUsuario(id: String) = Usuario.deleteOneUsuario(id)
    fun getAllUsuarui(): LiveData<List<usuario>> = Usuario.getAllUsuario()
}