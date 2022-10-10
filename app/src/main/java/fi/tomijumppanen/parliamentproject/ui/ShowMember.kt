/**
Name:  Tomi Jumppanen
ID:    2113590
Last update:  9.10.2022
Description:
This class idea is to handle all things that happens in ShowMember-fragments view
 */

package fi.tomijumppanen.parliamentproject.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import fi.tomijumppanen.parliamentproject.MainActivity
import fi.tomijumppanen.parliamentproject.R
import fi.tomijumppanen.parliamentproject.databinding.FragmentShowMemberBinding
import fi.tomijumppanen.parliamentproject.utilities.AssignData
import fi.tomijumppanen.parliamentproject.utilities.showMember.ShowMemberViewModel

class ShowMember : Fragment() {
    private lateinit var viewModel: ShowMemberViewModel
    private lateinit var binding: FragmentShowMemberBinding
    private var selectedMemberHetekaId: Int = 0

    override fun onResume() {
        super.onResume()
        selectedMemberHetekaId = arguments?.getInt("hetekaId")!!//getting selected hetekaid from Navigator

        viewModel = ViewModelProvider(this)[ShowMemberViewModel::class.java]
        viewModel.getData(selectedMemberHetekaId).observe(viewLifecycleOwner){
            (activity as MainActivity).actionBarSettings(it.firstname+" "+it.lastname, true)//here we set action bar title to members name and disable backbutton
            AssignData.setImage((activity as MainActivity), "https://avoindata.eduskunta.fi/"+it.pictureUrl, binding.memberPicture)
            AssignData.setText(it.firstname+" "+it.lastname, binding.nameText)
            AssignData.setText("Edustajan istumapaikka on nro: "+it.seatNumber.toString(), binding.seatText)
            AssignData.setPartyText("Puolue:", binding.partyText, it.party)
            AssignData.setConditionalText(it.minister, "Edustaja on nykyinen ministeri", "Edustaja ei ole tällä hetkellä ministeri", binding.ministerText)
        }

        viewModel.getExtraData(selectedMemberHetekaId).observe(viewLifecycleOwner){
            if (it.twitter != ""){
                binding.twitterButton.isVisible = true
                it.twitter?.let { twitter -> AssignData.setTwitterButton(context, twitter, binding.twitterButton) }
            }
            if (it.constituency != ""){
                binding.areaText.isVisible = true
                it.constituency?.let { constituency -> AssignData.setText("Vaalipiiri: $constituency", binding.areaText) }
            }
            if (it.bornYear != null){
                binding.birthYeartext.isVisible = true
                it.bornYear.let { born -> AssignData.setText("Syntynyt vuonna $born", binding.birthYeartext)}
            }
        }

        binding.addreviewButton.setOnClickListener{//if rating button is clicked then we navigate to review fragment
            val bundle = bundleOf("hetekaId" to selectedMemberHetekaId)//creating bundle to pass selected hetekaId to next fragment
            it.findNavController().navigate(R.id.action_ShowMember_to_AddReview, bundle)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_show_member, container,false)
        return binding.root
    }
}