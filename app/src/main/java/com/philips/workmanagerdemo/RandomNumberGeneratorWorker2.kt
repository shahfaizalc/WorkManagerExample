package com.philips.workmanagerdemo

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.lang.Exception

class RandomNumberGeneratorWorker2(
    internal val context: Context,
    internal val workerParams: WorkerParameters
) : Worker(
    context,
    workerParams
) {

    val TAG = "RandomNumberGeneratorWorker2"
    var randomNumber = 0;
    fun startRandomNumeberGenerator2() {
        var i = 0;

        while (i < 10 && !isStopped) {
            try {
                Thread.sleep(1000)
                randomNumber = (0..10).random()
                Log.d(TAG,"Allfine $i "+randomNumber);
            } catch (e: Exception) {
                Log.d(TAG,"Failed Exception:"+e);
            }
            i++;
        }
    }

    override fun doWork(): Result {
        startRandomNumeberGenerator2()
        return Result.success()
    }
}