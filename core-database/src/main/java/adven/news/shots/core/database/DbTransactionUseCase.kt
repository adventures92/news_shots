package adven.news.shots.core.database

import androidx.room.withTransaction
import javax.inject.Inject

class DbTransactionUseCase @Inject constructor(private val database: NewsDb) {

    suspend operator fun <R> invoke(block: suspend () -> R): R {
        return database.withTransaction(block)
    }

}