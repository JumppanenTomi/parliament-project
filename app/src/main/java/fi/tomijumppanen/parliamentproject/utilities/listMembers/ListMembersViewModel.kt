/**
Name:  Tomi Jumppanen
ID:    2113590
Last update:  9.10.2022
Description:
This class idea is to handle all things that happens in ListMembers-fragments viewModel
 */

package fi.tomijumppanen.parliamentproject.utilities.listMembers

import androidx.lifecycle.ViewModel
import fi.tomijumppanen.parliamentproject.data.ParliamentMemberRepository


class ListMembersViewModel: ViewModel() {
    val members = ParliamentMemberRepository.getParliamentMembers()
}