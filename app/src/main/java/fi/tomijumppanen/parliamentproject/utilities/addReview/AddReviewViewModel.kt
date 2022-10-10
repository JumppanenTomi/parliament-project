/**
Name:  Tomi Jumppanen
ID:    2113590
Last update:  9.10.2022
Description:
This class idea is to handle all things that happens in addReview-fragments ViewModel
 */

package fi.tomijumppanen.parliamentproject.utilities.addReview

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import fi.tomijumppanen.parliamentproject.data.ParliamentMemberComments
import fi.tomijumppanen.parliamentproject.data.ParliamentMemberRepository

class AddReviewViewModel: ViewModel() {
    fun getComments(hetekaId: Int): LiveData<List<ParliamentMemberComments>>{
        return ParliamentMemberRepository.getSinleParliamentMemberComments(hetekaId)
    }
}