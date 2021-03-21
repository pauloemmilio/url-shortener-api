package com.paulo.urlshortner.repository

import com.paulo.urlshortner.domain.model.Url
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UrlRepository : JpaRepository<Url, String> {
    fun findAllByOrderByAccessesDesc(): List<Url>
}