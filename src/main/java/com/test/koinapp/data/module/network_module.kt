package com.test.koinapp.data.module

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mobile.moviedatabase.core.exceptions.NoConnectionException
import com.test.koinapp.R
import com.test.koinapp.data.network.MovieApi
import com.test.koinapp.data.network.NetworkConstants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val TIMEOUT = 60L
private const val CONNECTION_INTERCEPTOR = "connection_interceptor"
private const val AUTH_INTERCEPTOR = "auth_interceptor"
private const val LOGGING_INTERCEPTOR = "logging_interceptor"
private const val API_KEY = "api_key"

val networkModule = module {
    single { createGson() }
    single(named(LOGGING_INTERCEPTOR)) { createLoggingInterceptor() }
    single(named(CONNECTION_INTERCEPTOR)) { createConnectionCheckerInterceptor(get()) }
    single(named(AUTH_INTERCEPTOR)) { createAuthInterceptor() }
    single {
        createHttpClient(
            connectionCheckerInterceptor = get(named(CONNECTION_INTERCEPTOR)),
            authInterceptor = get(named(AUTH_INTERCEPTOR)),
            httpLoggingInterceptor = get(named(LOGGING_INTERCEPTOR))
        )
    }
    single { createRetrofit(okHttpClient = get(), gson = get()) }
    single { createApiService(retrofit = get()) }
}

fun createApiService(retrofit: Retrofit): MovieApi {
    return retrofit.create(MovieApi::class.java)
}

fun createRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
    return Retrofit.Builder()
        .baseUrl(NetworkConstants.MOVIE_API_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
}

fun createHttpClient(
    connectionCheckerInterceptor: Interceptor,
    authInterceptor: Interceptor,
    httpLoggingInterceptor: HttpLoggingInterceptor
): OkHttpClient {
    val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT, TimeUnit.SECONDS)
        .addInterceptor(connectionCheckerInterceptor)
        .addInterceptor(authInterceptor)
    if (BuildConfig.DEBUG) {
        okHttpClient.addInterceptor(httpLoggingInterceptor)
    }
    return okHttpClient.build()
}

fun createGson(): Gson = GsonBuilder()
    .setLenient()
    .create()

fun createLoggingInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor(logger = object : HttpLoggingInterceptor.Logger {
        override fun log(message: String) {
            Log.d("OkHttp", message)
        }
    }).apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
}

fun createConnectionCheckerInterceptor(context: Context): Interceptor {
    return object : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val netInfo = connectivityManager.activeNetworkInfo
            val isConnected = netInfo != null && netInfo.isConnected
            if (!isConnected) {
                throw NoConnectionException(R.string.no_network)
            } else {
                return chain.proceed(chain.request())
            }
        }
    }
}

fun createAuthInterceptor(): Interceptor {
    return  object : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val newUrl = chain.request().url
                .newBuilder()
                .addQueryParameter(API_KEY, NetworkConstants.API_KEY)
                .build()
            val newRequest = chain.request()
                .newBuilder()
                .url(newUrl)
                .build()
            return chain.proceed(newRequest)
        }
    }
}
