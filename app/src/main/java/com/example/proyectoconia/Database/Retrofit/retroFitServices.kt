package com.example.proyectoconia.Database.Retrofit

import com.example.proyectoconia.Database.Entities.anotacion
import com.example.proyectoconia.Database.Entities.asistencia
import com.example.proyectoconia.Database.Entities.usuario
import com.example.proyectoconia.Database.ModelsRetrofit.*
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

const val HEROKU_API = "https://conia.herokuapp.com/"

interface retroFitServices {

    @GET("/informacion")
    fun getInformacion() : Deferred<Response<retroInformacion>>

    @GET("/tematica")
    fun getTematica():Deferred<Response<retroTematica>>

    @GET("/anotacion")
    fun getAnotacion() : Deferred<Response<List<modeloAnotacion>>>

    @GET("/genero")
    fun getGenero() : Deferred<Response<retroGenero>>

    @GET("/pais")
    fun getPais() : Deferred<Response<retroPais>>

    @GET("/carrera")
    fun getCarrera() : Deferred<Response<retroCarrera>>

    @GET("/nivel")
    fun getNivel() : Deferred<Response<retroNivel>>

    @GET("/tipo")
    fun getTipo() : Deferred<Response<retroTipo>>

    @GET("/usuario")
    fun getUsuario() : Deferred<Response<List<modeloUsuario>>>

    @GET("/fechasimportante")
    fun getFecha() : Deferred<Response<retroFecha>>

    @GET("/patrocinio")
    fun getPatrocinio() : Deferred<Response<retroPatrocinio>>

    @GET("/curso")
    fun getCurso() : Deferred<Response<retroCurso>>

    @GET("/galeria")
    fun getGaleria() : Deferred<Response<retroGaleria>>

    @GET("/contacto")
    fun getContacto() : Deferred<Response<retroContacto>>

    @GET("/ponente")
    fun getPonente() : Deferred<Response<retroPonente>>

    @GET("/programacion")
    fun getProgramacion() : Deferred<Response<List<modeloProgramacion>>>

    @GET("/asistencia")
    fun getAsistencia() : Deferred<Response<List<modeloAsistencia>>>

    @FormUrlEncoded
    @POST("/usuario")
    fun setUsuario(@Field("nombre") nombre : String,
                   @Field("apellido") apellido : String,
                   @Field("pass") pass : String,
                   @Field("correo") correo : String,
                   @Field("genero") genero : String,
                   @Field("pais") pais : String,
                   @Field("carrera") carrera : String,
                   @Field("nivel") nivel : String,
                   @Field("empresa") empresa : String,
                   @Field("formacion") formacion : String,
                   @Field("institutoEmpresa") institutoEmpresa : String,
                   @Field("tipo") tipo : String) : Call<usuario>

    @FormUrlEncoded
    @POST("/anotacion")
    fun setAnotacion(@Field("titulo") titulo:String,
                     @Field("fecha") fecha:String,
                     @Field("archivo") archivo:String,
                     @Field("usuario") usuario:String,
                     @Field("programacion") programacion:String) :Call<anotacion>

    @FormUrlEncoded
    @PUT("/anotacion/{id}")
    fun updateAnotacion(@Path("id")id:String,
                        @Field("titulo")titulo:String,
                        @Field("fecha")fecha:String,
                        @Field("archivo") archivo: String,
                        @Field("usuario")usuario: String,
                        @Field("programacion")programacion: String):Call<anotacion>

    @DELETE("/anotacion/{id}")
    fun deleteAnotacion(@Path("id") id : String) : Call<anotacion>

    @FormUrlEncoded
    @POST("/asistencia")
    fun setAsistencia(@Field("usuario") usuario : String,
                   @Field("programacion") programacion : String,
                   @Field("calificacion") calificacion : Float) : Call<asistencia>

    @DELETE("/asistencia/{id}")
    fun deleteAsistencia(@Path("id") id : String) : Call<asistencia>

    @FormUrlEncoded
    @PUT("/asistencia/{id}")
    fun updateAsistencia(@Path("id") id : String,
                         @Field("usuario") usuario : String,
                         @Field("programacion") programacion : String,
                         @Field("calificacion") calificacion : Float) : Call<asistencia>

    companion object {
        fun getRetrofit():retroFitServices{
            return Retrofit.Builder()
                .baseUrl(HEROKU_API)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build().create(retroFitServices::class.java)
        }
    }
}