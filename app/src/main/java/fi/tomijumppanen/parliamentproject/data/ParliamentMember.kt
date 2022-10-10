/**
Name:  Tomi Jumppanen
ID:    2113590
Last update:  9.10.2022
Description:
This class idea is to create 3 data entitys for database to create suitable tables of them. After that it creates Dao interface that makes it possible to interact with Room database
 */

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
    val pictureUrl: String = "",
)

@Entity
data class ParliamentMemberExtras(
    @PrimaryKey val hetekaId: Int,
    val twitter: String?,
    val bornYear: Int?,
    val constituency: String?
)

@Entity
data class ParliamentMemberComments(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val hetekaId: Int,
    val grade: Int,
    val comment: String
)

@Dao
interface ParliamentMemberDao{
    //insert basic data of members
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(parliamentMember: List<ParliamentMember>, parliamentMemberExtras: List<ParliamentMemberExtras>)

    //insert extra data of members
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addExtraData(ParliamentMember: List<ParliamentMember>)

    //get all users
    @Query("select * from ParliamentMember order by party")
    fun getAll(): LiveData<List<ParliamentMember>>

    //get single user by its hetekaId
    @Query("select * from ParliamentMember where hetekaId like :hetekaId")
    fun getByHetekaId(hetekaId: Int):LiveData<ParliamentMember>

    //get extra data from database
    @Query("select * from ParliamentMemberExtras where hetekaId like :hetekaId")
    fun getExtrasByHetekaId(hetekaId: Int):LiveData<ParliamentMemberExtras>

    //get comments by hetekaId from database
    @Query("select * from ParliamentMemberComments where hetekaId like :hetekaId order by id DESC")
    fun getCommentsByHetekaId(hetekaId: Int):LiveData<List<ParliamentMemberComments>>

    //insert comment to database
    @Query("insert into ParliamentMemberComments values(null, :hetekaId,:grade,:comment)")
    suspend fun insertComment(hetekaId: Int, grade: Int, comment: String)
}
