package co.kr.searchvoca.local.dao

import androidx.room.*
import androidx.room.OnConflictStrategy

@Dao
interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(obj: T)

    @Update
    suspend fun update(obj: T)

    @Delete
    suspend fun delete(obj: T)

    @Query("DELETE FROM word WHERE id IN (:id)")
    suspend fun delete(id: Int)
}