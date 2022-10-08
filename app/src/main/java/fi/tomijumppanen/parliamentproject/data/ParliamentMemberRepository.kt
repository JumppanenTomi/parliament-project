package fi.tomijumppanen.parliamentproject.data

import androidx.lifecycle.LiveData

object ParliamentMemberRepository {
    suspend fun addMember(hetekaid: Int, seatNumber: Int, lastname: String, firstname: String, party: String, minister: Boolean, pictureUrl: String) {
        ParliamentMemberDB.getInstance()
            .parliamentMemberDAO
            .insertOrUpdate(hetekaid, seatNumber, lastname, firstname, party, minister, pictureUrl)
    }

    fun getParliamentMembers(): LiveData<List<ParliamentMember>> = ParliamentMemberDB.getInstance().parliamentMemberDAO.getAll()

    /**fun readMembers() {
    viewModelScope.launch {
    try {
    members.value = ParliamentApi.retrofitService.getParliamentList()
    } catch (e: Exception) {
    Log.d("XXX", e.toString())
    }

    }
    }**/
}