/**
Name:  Tomi Jumppanen
ID:    2113590
Last update:  9.10.2022
Description:
This worker class for refreshing data from api-service
 */

package fi.tomijumppanen.parliamentproject.utilities.workers

import android.content.Context
import android.widget.Toast
import androidx.work.Worker
import androidx.work.WorkerParameters
import fi.tomijumppanen.parliamentproject.data.ParliamentMemberRepository
import fi.tomijumppanen.parliamentproject.utilities.AssignData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class WorkerUpdateDatabase(appContext: Context, workerParams: WorkerParameters): Worker(appContext, workerParams) {
    private val scope = CoroutineScope(Job() + Dispatchers.Main)
    override fun doWork(): Result {
        scope.launch{
            AssignData.setToast("Data päivitetään ajan tasalle", Toast.LENGTH_SHORT)
            ParliamentMemberRepository.addMember()
        }
    return Result.success()
    }
}