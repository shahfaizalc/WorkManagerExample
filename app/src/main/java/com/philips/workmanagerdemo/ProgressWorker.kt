package com.philips.workmanagerdemo


import android.content.Context
import androidx.work.*
import kotlinx.coroutines.delay


class ProgressWorker(internal val context: Context, parameters: WorkerParameters) :
    CoroutineWorker(context, parameters) {

    companion object {
        val Progress ="progress";
    }

    //Fake long running task for 60 seconds
    private suspend fun delayTask() {
        delay(2000)
    }

    override suspend fun doWork(): Result {
        val firstUpdate = workDataOf(Progress to 10)
        val midUpdate = workDataOf(Progress to 50)
        val lastUpdate = workDataOf(Progress to 90)
        setProgress(firstUpdate)
        delayTask()
        setProgress(midUpdate)
        delayTask()
        setProgress(lastUpdate)
        delayTask()
        return Result.success()
    }

}