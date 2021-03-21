package com.paulo.urlshortner.domain.model

import javax.persistence.*

@Entity
@Table(name = "url")
class Url(
    @Id
    val shortenUrl: String,
    val originalUrl: String,
    var accesses: Long = 0
)