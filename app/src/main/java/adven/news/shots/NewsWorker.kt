package adven.news.shots

import adven.news.shots.core.data.NewsRepository
import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.*
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import java.util.concurrent.TimeUnit

@HiltWorker
class NewsWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParam: WorkerParameters,
    private val newsRepository: NewsRepository
) : CoroutineWorker(context, workerParam) {

    companion object {
        fun initWorker(context: Context) {
            val request = PeriodicWorkRequest.Builder(NewsWorker::class.java, 2, TimeUnit.HOURS)
                .setBackoffCriteria(BackoffPolicy.EXPONENTIAL, 30, TimeUnit.MINUTES)
                .setConstraints(
                    Constraints.Builder()
                        .setRequiresBatteryNotLow(true)
                        .setRequiredNetworkType(NetworkType.CONNECTED)
                        .build()
                )
                .build()

            WorkManager.getInstance(context)
                .enqueueUniquePeriodicWork("NewsWorker", ExistingPeriodicWorkPolicy.UPDATE, request)
        }
    }

    override suspend fun doWork(): Result {
        return if (newsRepository.refreshHeadlines("in"))
            Result.success()
        else
            if (runAttemptCount < 3)
                Result.retry()
            else
                Result.failure()
    }

}