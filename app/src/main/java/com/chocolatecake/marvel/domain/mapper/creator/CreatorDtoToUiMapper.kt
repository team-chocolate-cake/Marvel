package com.chocolatecake.marvel.domain.mapper.creator

import com.chocolatecake.marvel.data.remote.model.dto.ProfileDto
import com.chocolatecake.marvel.domain.mapper.Mapper
import com.chocolatecake.marvel.domain.model.Creator
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class CreatorDtoToUiMapper @Inject constructor(): Mapper<ProfileDto, Creator> {
    override fun map(input: ProfileDto): Creator {
        return Creator(
            id= input.id ?: 0,
            name = input.fullName ?: "",
            imageURL = input.thumbnail?.toUrl() ?: ""
        )
    }
}