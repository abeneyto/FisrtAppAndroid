package com.example.hello.data.local

import android.content.Context
import androidx.room.*
import com.example.hello.Model.Favorite

@Dao
interface MovieDao {
    @Query("SELECT * FROM favorite")
    suspend fun getMovies(): List<Favorite>

    @Query("SELECT * FROM favorite ORDER BY title")
    suspend fun getOrderByTitle(): List<Favorite>

    @Query("SELECT * FROM favorite ORDER BY created")
    suspend fun getOrderByCreated(): List<Favorite>

    @Query("DELETE from favorite where id = :id")
    suspend fun deleteFavorite(id: Int)

    @Query("SELECT * from favorite where id = :id")
    suspend fun isFavorite(id: Int): List<Favorite>

    @Query("DELETE from favorite")
    suspend fun deleteAll()

    @Insert
    suspend fun insertMovie(favorite: Favorite)

}

@Database(entities = arrayOf(Favorite::class), version = 5)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}

object DatabaseFactory {
    fun get(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "movieDB")
            .fallbackToDestructiveMigration()
            .build()
    }
}