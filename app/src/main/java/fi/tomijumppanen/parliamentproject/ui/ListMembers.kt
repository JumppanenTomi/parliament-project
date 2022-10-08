package fi.tomijumppanen.parliamentproject.ui

import android.annotation.SuppressLint
import android.content.Context
import android.media.CamcorderProfile.getAll
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RestrictTo.Scope
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fi.tomijumppanen.parliamentproject.MainActivity
import fi.tomijumppanen.parliamentproject.R
import fi.tomijumppanen.parliamentproject.data.ParliamentMember
import fi.tomijumppanen.parliamentproject.data.ParliamentMemberRepository
import fi.tomijumppanen.parliamentproject.databinding.FragmentListMembersBinding
import fi.tomijumppanen.parliamentproject.databinding.ItemJasenetListausBinding
import fi.tomijumppanen.parliamentproject.utilities.AssignData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class ListMembers : Fragment() {
    private lateinit var viewModel: ListFragmentViewModel
    private lateinit var binding: FragmentListMembersBinding
    val scope = CoroutineScope(Job() + Dispatchers.Main)


    override fun onResume() {
        (activity as MainActivity).actionBarSettings("Parliament application", false)
        super.onResume()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_members, container,false)

        viewModel = ViewModelProvider(this)[ListFragmentViewModel::class.java]

        scope.launch{
            testi()
        }

        viewModel.members.observe(this){
            val lista = it
            binding.jasenetRecyclerView.layoutManager = GridLayoutManager(activity,1)
            binding.jasenetRecyclerView.adapter = context?.let { MyRecyclerViewAdapter(lista, it) }
        }

        return binding.root
    }

    suspend fun testi(){
        ParliamentMemberRepository.addMember(414, 0,"Vanhanen","Matti", "kesk", false, "attachment/member/pictures/Vanhanen-Matti-web-v7828-414.JPG")
    }
}

class MyRecyclerViewAdapter(private val list: List<ParliamentMember>, private val context: Context): RecyclerView.Adapter<MyViewHolder>() {
    private lateinit var binding: ItemJasenetListausBinding

    override fun onCreateViewHolder(parent: ViewGroup, vt: Int): MyViewHolder {
        binding = ItemJasenetListausBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(vh: MyViewHolder, pos: Int) {
        val bundle = bundleOf("hetekaId" to list[pos].hetekaId)

        AssignData.setImage(context, "https://avoindata.eduskunta.fi/"+list[pos].pictureUrl, binding.imageView)

        binding.itemLayout.setOnClickListener{
            binding.root.findNavController().navigate(R.id.action_ListMember_to_ShowMember, bundle)
        }
    }
}

class MyViewHolder(binding: ItemJasenetListausBinding): RecyclerView.ViewHolder(binding.root)

class ListFragmentViewModel: ViewModel() {
    val members = ParliamentMemberRepository.getParliamentMembers()
}