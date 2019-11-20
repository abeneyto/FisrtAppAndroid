package com.example.hello.data.local

import android.content.Context
import androidx.room.*
import com.example.hello.data.remote.Movie

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie")
    suspend fun getMovies(): List<Movie>

    @Query("SELECT * FROM movie ORDER BY title")
    suspend fun getOrderByTitle(): List<Movie>

    @Query("SELECT * FROM movie ORDER BY vote_average")
    suspend fun getOrderByOverage(): List<Movie>

    @Query("DELETE from movie where id = :id")
    suspend fun deleteFeavorite(id:Int)

    @Query("SELECT * from movie where id = :id")
    suspend fun isFavorite(id:Int): List<Movie>

    @Insert
    suspend fun insertMovie(movie: Movie)

}

@Database(entities = arrayOf(Movie::class), version = 3)
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