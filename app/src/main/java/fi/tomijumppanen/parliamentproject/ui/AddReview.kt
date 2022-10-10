/**
Name:  Tomi Jumppanen
ID:    2113590
Last update:  9.10.2022
Description:
This class idea is to handle all things that happens in addReview-fragments view
 */

package fi.tomijumppanen.parliamentproject.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import fi.tomijumppanen.parliamentproject.MainActivity
import fi.tomijumppanen.parliamentproject.R
import fi.tomijumppanen.parliamentproject.data.ParliamentMemberRepository
import fi.tomijumppanen.parliamentproject.databinding.FragmentAddReviewBinding
import fi.tomijumppanen.parliamentproject.utilities.addReview.AddReviewRecyclerView
import fi.tomijumppanen.parliamentproject.utilities.addReview.AddReviewViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class AddReview : Fragment() {
    private lateinit var viewModel: AddReviewViewModel
    private lateinit var binding: FragmentAddReviewBinding
    private var selectedMemberHetekaId: Int = 0
    private val scope = CoroutineScope(Job() + Dispatchers.Main)

    override fun onResume() {
        super.onResume()
        selectedMemberHetekaId = arguments?.getInt("hetekaId")!!
        viewModel = ViewModelProvider(this)[AddReviewViewModel::class.java]

        viewModel.getComments(selectedMemberHetekaId).observe(viewLifecycleOwner){
            binding.commentsRecyclerView.setItemViewCacheSize(200)//i don't know what this line does but it fixed problem with duplicate members in list and switching child content when scrolling
            binding.commentsRecyclerView.layoutManager = GridLayoutManager(activity,1)
            binding.commentsRecyclerView.adapter = AddReviewRecyclerView(it)
        }

        binding.submitComment.setOnClickListener{
            val comment = binding.commentInput.text.toString()
            val grade = Integer.parseInt(binding.ratingInput.text.toString())
            if(grade in 1..10){
                scope.launch {
                    ParliamentMemberRepository.insertComment(selectedMemberHetekaId, grade, comment)
                }
            } else{
                Toast.makeText(MainActivity.appContext, "Syöttämäsi arvosana ei ollut sallittu.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_review, container,false)
        return binding.root
    }
}