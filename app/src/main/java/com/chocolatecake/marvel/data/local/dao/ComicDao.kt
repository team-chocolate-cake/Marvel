package com.chocolatecake.marvel.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.chocolatecake.marvel.data.local.entities.CharacterEntity
import com.chocolatecake.marvel.data.local.entities.ComicsEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

@Dao
interface ComicDao {
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertComics(comics: List<ComicsEntity>): Completable
    
    @Query("DELETE FROM TABLE_COMIC")
    fun deleteAllComics(): Completable
    
    @Query("SELECT * FROM TABLE_COMIC ORDER BY random() limit(:limit)")
    fun getComicsWithLimit(limit: Int = 10): Observable<List<ComicsEntity>>

    @Query("SELECT * FROM TABLE_COMIC WHERE title LIKE :comicTitle limit(:limit)")
    fun getFilteredComics(comicTitle: String, limit: Int = 5): Observable<List<ComicsEntity>>
}