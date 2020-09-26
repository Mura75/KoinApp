package com.test.koinapp.data.network

import com.google.gson.JsonObject
import com.test.koinapp.data.model.MovieData
import com.test.koinapp.data.model.MoviesResponse
import io.reactivex.Single
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.Path

interface MovieApi {

    @GET("authentication/token/new")
    suspend fun createRequestToken(): Response<JsonObject>

    @POST("authentication/token/validate_with_login")
    suspend fun login(@Body body: JsonObject): Response<JsonObject>

    @POST("authentication/session/new")
    suspend fun createSession(@Body body: JsonObject) : Response<JsonObject>

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("page") page: Int) : Response<MoviesResponse>

    @GET("movie/{movie_id}")
    suspend fun getMovie(@Path("movie_id") movieId: Int) : Response<MovieData>

    @GET("account/{account_id}/favorite/movies")
    suspend fun getFavoriteMovies(
        @Path("account_id") accountId: Int,
        @Query("session_id") sessionId: String,
        @Query("page") page: Int
    ) : Response<MoviesResponse>
}
