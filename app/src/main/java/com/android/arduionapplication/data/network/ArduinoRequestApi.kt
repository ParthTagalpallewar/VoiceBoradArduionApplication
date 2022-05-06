package com.android.arduionapplication.data.network

import com.android.arduionapplication.utils.BASEURL
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


interface ArduinoRequestApi {

    @GET("speak")
    suspend fun sendData(
        @Query("query") query: String,
    ): Response<String>

    @GET("clear")
    suspend fun clearData(): Response<String>

    //api request for login of all employees of all roles
    @FormUrlEncoded
    @POST("hello")
    suspend fun employeeLogin(
        @Field("email") email: String,
    ): Response<String>

    companion object {
        operator fun invoke(
        ): ArduinoRequestApi {

            return Retrofit.Builder()
//                .client(provideHttpClint(provideInterceptor()))
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ArduinoRequestApi::class.java)
        }

/*        fun provideInterceptor(): HttpLoggingInterceptor =
            HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

        fun provideHttpClint(
          interceptor: HttpLoggingInterceptor
        ): OkHttpClient =
            OkHttpClient.Builder().also {
                it.addInterceptor(interceptor)
            }.build()*/
    }

}

