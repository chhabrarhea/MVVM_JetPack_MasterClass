package com.example.mvvm_jetpack_masterclass.workmanager

import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.mvvm_jetpack_masterclass.room.MainActivity
import com.example.mvvm_jetpack_masterclass.workmanager.HomeActivity.Companion.KEY_COUNT_VALUE
import java.text.SimpleDateFormat
import java.util.*

class UploadWorker(context: Context, params: WorkerParameters) : Worker(context,params) {

    companion object{
        const val KEY_WORKER = "key_worker"
    }

    override fun doWork(): Result {
        try {
            val count = inputData.getInt(HomeActivity.KEY_COUNT_VALUE,0)
            for (i in 0 until count) {
                Log.i("MYTAG", "Uploading $i")
            }

            val time = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
            val currentDate = time.format(Date())

            val outPutData = Data.Builder()
                .putString(KEY_WORKER,currentDate)
                .build()

            return Result.success(outPutData)
        } catch (e:Exception){
            return Result.failure()
        }
    }
}