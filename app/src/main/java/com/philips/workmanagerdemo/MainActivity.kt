package com.philips.workmanagerdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.work.*
import com.philips.workmanagerdemo.ProgressWorker.Companion.Progress
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    var workManager: WorkManager? = null
    var workRequest: WorkRequest? = null

    var workRequest1: OneTimeWorkRequest? = null
    var workRequest2: OneTimeWorkRequest? = null
    var workRequest3: OneTimeWorkRequest? = null
    var workRequest4: OneTimeWorkRequest? = null
    var notifiationRequest5: WorkRequest? = null
    var notifiationProgressRequest5: WorkRequest? = null
    var progressRequest5: WorkRequest? = null
    lateinit var stopWork: Button;
    lateinit var startWork: Button;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        stopWork = findViewById(R.id.button_stopwork);
        startWork = findViewById(R.id.button_startwork);
        workManager = WorkManager.getInstance(applicationContext)

        //commment and uncomment. to run the specific example
       // periodicWorkerfunction()
//        oneTimeWorkerFunction()
//        progressWorkerFunction()
//        notificationWorkerFunction()
        notificationProgressWorkerFunction()

    }

    private fun periodicWorkerfunction() {
        workRequest = PeriodicWorkRequest.Builder(
            RandomNumberGeneratorWorker::class.java,
            2,
            TimeUnit.MINUTES
        ).build()
        startWork.setOnClickListener { workManager?.enqueue(workRequest!!) }
        stopWork.setOnClickListener { workManager?.cancelWorkById(workRequest!!.id) }

    }

    private fun oneTimeWorkerFunction() {
        workRequest1 =
            OneTimeWorkRequestBuilder<RandomNumberGeneratorWorker1>().addTag("RandomNumberGeneratorWorker1")
                .build()
        workRequest2 =
            OneTimeWorkRequestBuilder<RandomNumberGeneratorWorker2>().addTag("RandomNumberGeneratorWorker2")
                .build()
        workRequest3 =
            OneTimeWorkRequestBuilder<RandomNumberGeneratorWorker3>().addTag("RandomNumberGeneratorWorker3")
                .build()
        workRequest4 =
            OneTimeWorkRequestBuilder<RandomNumberGeneratorWorker4>().addTag("RandomNumberGeneratorWorker4")
                .build()
        startWork.setOnClickListener {
            // workManager?.beginWith(workRequest1!!)?.then(workRequest2!!)?.then(workRequest3!!)?.then(workRequest4!!)?.enqueue()
            workManager?.beginWith(listOf(workRequest1!!, workRequest2!!))?.then(workRequest3!!)
                ?.enqueue()
            //  workManager?.beginWith(listOf(workRequest1!!,workRequest2!!))?.then(workRequest3!!)?.enqueue()

        }
        stopWork.setOnClickListener {
            workManager?.cancelWorkById(workRequest1!!.id)
        }
    }

    private fun notificationWorkerFunction() {
        notifiationRequest5 = OneTimeWorkRequest.Builder(NotificationWorker::class.java)
            .build()
        startWork.setOnClickListener { workManager?.enqueue(notifiationRequest5!!) }
        stopWork.setOnClickListener { workManager?.cancelWorkById(notifiationRequest5!!.id) }


        WorkManager.getInstance(this)
            .beginUniqueWork(
                "NotificationWorker", ExistingWorkPolicy.APPEND_OR_REPLACE,
                notifiationRequest5 as OneTimeWorkRequest
            ).enqueue().state
            .observe(this) { state ->
                Log.d("task", "NotificationWorker: $state")
            }
    }

    private fun notificationProgressWorkerFunction() {
        notifiationProgressRequest5 = OneTimeWorkRequest.Builder(NotificationProgressWorker::class.java)
            .build()
        startWork.setOnClickListener { workManager?.enqueue(notifiationProgressRequest5!!) }
        stopWork.setOnClickListener { workManager?.cancelWorkById(notifiationProgressRequest5!!.id) }


        WorkManager.getInstance(this)
            .beginUniqueWork(
                "NotificationWorker", ExistingWorkPolicy.APPEND_OR_REPLACE,
                notifiationProgressRequest5 as OneTimeWorkRequest
            ).enqueue().state
            .observe(this) { state ->
                Log.d("task", "NotificationWorker: $state")
            }
    }

    private fun progressWorkerFunction() {
        progressRequest5 = OneTimeWorkRequest.Builder(ProgressWorker::class.java)
            .build()
        startWork.setOnClickListener { workManager?.enqueue(progressRequest5!!) }
        stopWork.setOnClickListener { workManager?.cancelWorkById(progressRequest5!!.id) }


        WorkManager.getInstance(applicationContext)
            .getWorkInfoByIdLiveData(progressRequest5!!.id)
            .observe(this) { workInfo ->
                if (workInfo != null) {
                    val progress = workInfo.progress
                    val value = progress.getInt(Progress, 0)
                    Log.d("task", "ProgressWorker - $value")
                    // Do something with progress information
                }
            }
    }
}