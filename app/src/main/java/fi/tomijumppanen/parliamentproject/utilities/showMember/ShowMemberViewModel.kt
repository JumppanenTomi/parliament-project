/**
Name:  Tomi Jumppanen
ID:    2113590
Last update:  9.10.2022
Description:
This class idea is to handle all things that happens in ShowMember-fragments viewModel
 */

package fi.tomijumppanen.parliamentproject.utilities.showMember

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import fi.tomijumppanen.parliamentproject.data.ParliamentMember
import fi.tomijumppanen.parliamentproject.data.ParliamentMemberExtras
import fi.tomijumppanen.parliamentproject.data.ParliamentMemberRepository

class ShowMemberViewModel: ViewModel() {
    fun getData(hetekaId: Int): LiveData<ParliamentMember> {
        return ParliamentMemberRepository.getSingleParliamentMember(hetekaId)
    }
    fun getExtraData(hetekaId: Int): LiveData<ParliamentMemberExtras> {
        return ParliamentMemberRepository.getSingleParliamentMemberExtras(hetekaId)
    }
}