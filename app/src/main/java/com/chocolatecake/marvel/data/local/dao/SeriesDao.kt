package com.chocolatecake.marvel.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.chocolatecake.marvel.data.local.entities.CharacterEntity
import com.chocolatecake.marvel.data.local.entities.SeriesEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

@Dao
interface SeriesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSeries(series: List<SeriesEntity>): Completable

    @Query("select * from TABLE_SERIES order by random() limit(:limit)")
    fun getSeriesWithLimit(limit: Int = 10): Observable<List<SeriesEntity>>

    @Query("delete from TABLE_SERIES")
    fun deleteAllSeries(): Completable

    @Query("SELECT * FROM TABLE_SERIES WHERE title LIKE :seriesTitle limit(:limit)")
    fun getFilteredSeries(seriesTitle: String, limit: Int = 5): Observable<List<SeriesEntity>>
}