/**
 Name:  Tomi Jumppanen
 ID:    2113590
 Last update:  9.10.2022
 Description:
 This class idea is to gather data from network using retrofit-library after that it uses Moshi-library to convert it to Kotlin
 */

package fi.tomijumppanen.parliamentproject.data

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

    private const val baseUrl = "https://users.metropolia.fi/~peterh/"

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
        @GET("extras.json")
        suspend fun getParliamentExtrasList(): List<ParliamentMemberExtras>
    }

    object ParliamentApi {
        val retrofitService : ParliamentApiService by lazy {
            retrofit.create(ParliamentApiService::class.java) }
    }