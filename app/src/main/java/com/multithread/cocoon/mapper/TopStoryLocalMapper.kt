package com.multithread.cocoon.mapper

import com.multithread.cocoon.base.LocalMapper
import com.multithread.cocoon.data.model.localEntity.TopStoryLocalEntity
import com.multithread.cocoon.domain.model.TopStoryDomainEntity
import javax.inject.Inject

class TopStoryLocalMapper @Inject constructor() :
    LocalMapper<List<TopStoryLocalEntity>, TopStoryDomainEntity> {

    override fun mapToLocal(items: TopStoryDomainEntity): List<TopStoryLocalEntity> =
        mutableListOf<TopStoryLocalEntity>().apply {
            items.results.forEach { result ->
                add(
                    TopStoryLocalEntity(
                        storyAbstract = result.abstract,
                        byline = result.abstract,
                        createdDate = result.abstract,
                        itemType = result.itemType,
                        kicker = result.kicker,
                        materialTypeFacet = result.abstract,
                        imageUrl = result.imageUrl,
                        publishedDate = result.publishedDate,
                        section = result.section,
                        shortUrl = result.shortUrl,
                        subsection = result.subsection,
                        title = result.title,
                        updatedDate = result.updatedDate,
                        uri = result.uri,
                        url = result.url,
                        favorite = result.favorite
                    )
                )
            }
        }

    override fun mapFromLocal(items: List<TopStoryLocalEntity>): TopStoryDomainEntity =
        TopStoryDomainEntity(
            results = createDomainResult(items)
        )

    private fun createDomainResult(items: List<TopStoryLocalEntity>): List<TopStoryDomainEntity.Result> =
        mutableListOf<TopStoryDomainEntity.Result>().apply {
            items.forEach { result ->
                add(
                    TopStoryDomainEntity.Result(
                        abstract = result.storyAbstract,
                        byline = result.byline,
                        createdDate = result.createdDate,
                        itemType = result.itemType,
                        kicker = result.kicker,
                        materialTypeFacet = result.materialTypeFacet,
                        imageUrl = result.imageUrl,
                        publishedDate = result.publishedDate,
                        section = result.section,
                        shortUrl = result.shortUrl,
                        subsection = result.subsection,
                        title = result.title,
                        updatedDate = result.updatedDate,
                        uri = result.uri,
                        url = result.url,
                        favorite = result.favorite
                    )
                )
            }
        }

}