package fi.tomijumppanen.parliamentproject.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import fi.tomijumppanen.parliamentproject.MainActivity
import fi.tomijumppanen.parliamentproject.R
import fi.tomijumppanen.parliamentproject.databinding.FragmentShowMemberBinding
import fi.tomijumppanen.parliamentproject.utilities.AssignData


class ShowMember : Fragment() {
    private lateinit var binding: FragmentShowMemberBinding

    override fun onResume() {
        val selectedMemberHetekaId: Int? = arguments?.getInt("hetekaId")

        (activity as MainActivity).actionBarSettings(selectedMemberHetekaId.toString(), true)

        AssignData.setImage(activity as MainActivity, "https://avoindata.eduskunta.fi/attachment/member/pictures/Vanhanen-Matti-web-v7828-414.JPG", binding.edustajaKuva)

        AssignData.setTwitterButton(context,"https://twitter.com/twitter", binding.twitterNappi)

        super.onResume()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_show_member, container,false)
        return binding.root
    }
}