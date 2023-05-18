package com.chocolatecake.marvel.domain.mapper

import com.chocolatecake.marvel.data.local.entities.SearchHistoryEntity
import com.chocolatecake.marvel.domain.model.SearchHistory

class SearchHistoryUIMapper : Mapper<SearchHistoryEntity, SearchHistory> {
    override fun map(input: SearchHistoryEntity): SearchHistory {
        return SearchHistory(
            id = input.id,
            keyword = input.keyword,
        )
    }
}