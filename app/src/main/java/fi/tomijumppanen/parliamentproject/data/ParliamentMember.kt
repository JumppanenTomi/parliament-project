package fi.tomijumppanen.parliamentproject.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Entity
data class ParliamentMember (
    @PrimaryKey val hetekaId: Int,
    val seatNumber: Int = 0,
    val lastname: String,
    val firstname: String,
    val party: String,
    val minister: Boolean = false,
    val pictureUrl: String = ""
)

@Dao
interface ParliamentMemberDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate()

    @Query("select * from ParliamentMember order by party")
    fun getAll(): LiveData<List<ParliamentMember>>
}
