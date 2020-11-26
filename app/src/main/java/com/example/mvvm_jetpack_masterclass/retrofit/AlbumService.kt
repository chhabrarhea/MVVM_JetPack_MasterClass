package com.example.mvvm_jetpack_masterclass.retrofit

import retrofit2.Response
import retrofit2.http.*

interface  AlbumService {
    @GET("/albums")
    suspend fun getAlbums(): Response<Albums>

    @GET("/albums")
    suspend fun getSortedAlbums(@Query("userId") userId: Int): Response<Albums>

    @GET("/albums/{id}")
    suspend fun getAlbum(@Path(value = "id") albumId: Int): Response<Album>

    @POST("/albums")
    suspend fun uploadAlbum(@Body album: Album): Response<Album>


}

