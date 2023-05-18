package com.chocolatecake.marvel.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.chocolatecake.marvel.data.local.entities.CharacterEntity
import com.chocolatecake.marvel.data.local.entities.SearchHistoryEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface SearchHistoryDao {

    @Insert
    fun insertSearchHistory(search: SearchHistoryEntity): Completable

    @Delete
     fun deleteSearchHistory(search: SearchHistoryEntity): Completable

    @Query("SELECT * FROM SEARCH_HISTORY_TABLE ORDER BY ID DESC")
    fun getAllSearchHistory(): Single<List<SearchHistoryEntity>>

    @Query("SELECT * FROM SEARCH_HISTORY_TABLE WHERE keyword LIKE :keyword AND type == :type ORDER BY ID DESC")
    fun getFilteredSearchHistory(keyword: String , type: String): Single<List<SearchHistoryEntity>>
}