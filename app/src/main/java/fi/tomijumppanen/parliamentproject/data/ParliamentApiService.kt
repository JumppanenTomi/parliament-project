package fi.tomijumppanen.parliamentproject.data

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

    private const val baseUrl = "https://webomi.fi/wp-content/uploads/school/"

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(baseUrl)
        .build()

    interface ParliamentApiService {
        @GET("seating.json")
        suspend fun getParliamentList(): List<ParliamentMember>
    }

    object ParliamentApi {
        val retrofitService : ParliamentApiService by lazy {
            retrofit.create(ParliamentApiService::class.java) }
    }