package com.example.proyectoconia.Database.Retrofit

import com.example.proyectoconia.Database.Entities.usuario
import com.example.proyectoconia.Database.ModelsRetrofit.*
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

const val HEROKU_API = "https://conia.herokuapp.com/"

interface retroFitServices {

    @GET("/informacion")
    fun getInformacion() : Deferred<Response<retroInformacion>>

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