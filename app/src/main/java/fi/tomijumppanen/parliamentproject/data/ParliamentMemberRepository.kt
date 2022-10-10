/**
Name:  Tomi Jumppanen
ID:    2113590
Last update:  9.10.2022
Description:
This class idea is to create "bridge"- between liveModel, Api-service and database so it handles all data trasfer
 */

package fi.tomijumppanen.parliamentproject.data

import androidx.lifecycle.LiveData

object ParliamentMemberRepository {
    //below is line(s) to add basic data
    suspend fun addMember() {
        ParliamentMemberDB.getInstance()
            .parliamentMemberDAO
            .insertOrUpdate(ParliamentApi.retrofitService.getParliamentList(), ParliamentApi.retrofitService.getParliamentExtrasList())
    }//to bulk add from Api service

    //below are line(s) to insert new comment
    suspend fun insertComment(hetekaId: Int, grade: Int, comment: String){
        ParliamentMemberDB.getInstance()
            .parliamentMemberDAO
            .insertComment(hetekaId, grade, comment)
    }

    //below are line(s) for getting basic data
    fun getParliamentMembers(): LiveData<List<ParliamentMember>> = ParliamentMemberDB.getInstance().parliamentMemberDAO.getAll()//to get all
    fun getSingleParliamentMember(hetekaId: Int): LiveData<ParliamentMember> = ParliamentMemberDB.getInstance().parliamentMemberDAO.getByHetekaId(hetekaId)//to get single member
    //below are line(s) to get extra data
    fun getSingleParliamentMemberExtras(hetekaId: Int): LiveData<ParliamentMemberExtras> = ParliamentMemberDB.getInstance().parliamentMemberDAO.getExtrasByHetekaId(hetekaId)//to get single member
    //below are line(s) to get comment data
    fun getSinleParliamentMemberComments(hetekaId: Int): LiveData<List<ParliamentMemberComments>> = ParliamentMemberDB.getInstance().parliamentMemberDAO.getCommentsByHetekaId(hetekaId)//to get single member
}
