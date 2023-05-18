package com.chocolatecake.marvel.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.chocolatecake.marvel.data.local.entities.EventEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

@Dao
interface EventDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEvent(events: List<EventEntity>): Completable

    @Query("DELETE FROM TABLE_EVENT")
    fun deleteAllEvents(): Completable

    @Query("SELECT * FROM TABLE_EVENT ORDER BY random() limit(:limit)")
    fun getEventsWithLimit(limit: Int = 10): Observable<List<EventEntity>>

}
