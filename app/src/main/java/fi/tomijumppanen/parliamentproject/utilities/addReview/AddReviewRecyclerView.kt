/**
Name:  Tomi Jumppanen
ID:    2113590
Last update:  9.10.2022
Description:
This class idea is to handle all things that happens in addReview-fragments RecyclerView
 */

package fi.tomijumppanen.parliamentproject.utilities.addReview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fi.tomijumppanen.parliamentproject.data.ParliamentMemberComments
import fi.tomijumppanen.parliamentproject.databinding.ItemCommentsListingBinding
import fi.tomijumppanen.parliamentproject.utilities.AssignData

class AddReviewRecyclerView(private val list: List<ParliamentMemberComments>): RecyclerView.Adapter<AddReviewViewHolder>() {
    private lateinit var binding: ItemCommentsListingBinding

    override fun onCreateViewHolder(parent: ViewGroup, vt: Int): AddReviewViewHolder {
        binding = ItemCommentsListingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AddReviewViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(vh: AddReviewViewHolder, pos: Int) {
        AssignData.setText(list[pos].comment, binding.partyText)
        AssignData.setText(list[pos].grade.toString(), binding.gradeNum)
    }
}

class AddReviewViewHolder(binding: ItemCommentsListingBinding): RecyclerView.ViewHolder(binding.root)