package com.chocolatecake.marvel.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.chocolatecake.marvel.data.local.entities.SeriesEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface SeriesDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSeries(series : List<SeriesEntity>) : Completable

    @Query("SELECT * FROM SeriesEntity")
    fun getAllSeries(): Single<List<SeriesEntity>>

    @Query("DELETE FROM SeriesEntity")
    fun deleteAllSeries() : Completable

}
