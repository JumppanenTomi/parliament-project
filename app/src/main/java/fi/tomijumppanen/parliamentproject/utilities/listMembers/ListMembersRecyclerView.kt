/**
Name:  Tomi Jumppanen
ID:    2113590
Last update:  9.10.2022
Description:
This class idea is to handle all things that happens in ListMembers-fragments RecyclerView
 */

package fi.tomijumppanen.parliamentproject.utilities.listMembers

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import fi.tomijumppanen.parliamentproject.R
import fi.tomijumppanen.parliamentproject.data.ParliamentMember
import fi.tomijumppanen.parliamentproject.databinding.ItemMembersListingBinding
import fi.tomijumppanen.parliamentproject.utilities.AssignData

class ListMembersRecyclerView(private val list: List<ParliamentMember>, private val context: Context): RecyclerView.Adapter<ListMembersViewHolder>() {
    private lateinit var binding: ItemMembersListingBinding

    override fun onCreateViewHolder(parent: ViewGroup, vt: Int): ListMembersViewHolder {
        binding = ItemMembersListingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListMembersViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(vh: ListMembersViewHolder, pos: Int) {
        val bundle = bundleOf("hetekaId" to list[pos].hetekaId)
        AssignData.setImage(context, "https://avoindata.eduskunta.fi/"+list[pos].pictureUrl, binding.memberImage)
        AssignData.setText(list[pos].firstname+" "+list[pos].lastname, binding.nameText)
        AssignData.setPartyText("Puolue:", binding.partyText, list[pos].party)

        binding.itemLayout.setOnClickListener{
            it.findNavController().navigate(R.id.action_ListMember_to_ShowMember, bundle)
        }
    }
}

class ListMembersViewHolder(binding: ItemMembersListingBinding): RecyclerView.ViewHolder(binding.root)