package com.chocolatecake.marvel.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.chocolatecake.marvel.data.local.entities.CharacterEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacters(characters: List<CharacterEntity>): Completable

    @Query("DELETE FROM CharacterEntity")
    fun deleteAllCharacters(): Completable

    @Query("SELECT * FROM CharacterEntity")
    fun getAllCharacters(): Observable<List<CharacterEntity>>

    @Query("SELECT * FROM CharacterEntity WHERE name LIKE :characterName")
    fun getFilteredCharacters(characterName: String): Observable<List<CharacterEntity>>
}