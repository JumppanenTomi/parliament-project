/**
Name:  Tomi Jumppanen
ID:    2113590
Last update:  9.10.2022
Description:
This class idea is to handle all things that happens in ListMembers-fragments view
 */

package fi.tomijumppanen.parliamentproject.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import fi.tomijumppanen.parliamentproject.MainActivity
import fi.tomijumppanen.parliamentproject.R
import fi.tomijumppanen.parliamentproject.databinding.FragmentListMembersBinding
import fi.tomijumppanen.parliamentproject.utilities.listMembers.ListMembersRecyclerView
import fi.tomijumppanen.parliamentproject.utilities.listMembers.ListMembersViewModel


class ListMembers : Fragment() {
    private lateinit var viewModel: ListMembersViewModel
    private lateinit var binding: FragmentListMembersBinding

    override fun onResume() {
        super.onResume()
        viewModel = ViewModelProvider(this)[ListMembersViewModel::class.java]
        viewModel.members.observe(viewLifecycleOwner){
            (activity as MainActivity).actionBarSettings("Parliament application", false)
            binding.membersRecyclerView.setItemViewCacheSize(200)//i don't know what this line does but it fixed problem with duplicate members in list and switching child content when scrolling
            binding.membersRecyclerView.layoutManager = GridLayoutManager(activity,1)
            binding.membersRecyclerView.adapter = context?.let { context -> ListMembersRecyclerView(it, context) }
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_members, container,false)
        return binding.root
    }
}