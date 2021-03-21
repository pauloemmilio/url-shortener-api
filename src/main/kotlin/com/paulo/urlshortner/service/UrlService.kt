package com.paulo.urlshortner.service

import com.paulo.urlshortner.common.util.RandomAlphanumericUtils.generate
import com.paulo.urlshortner.domain.dto.UrlDto
import com.paulo.urlshortner.domain.model.Url
import com.paulo.urlshortner.repository.UrlRepository
import org.apache.commons.validator.routines.UrlValidator
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class UrlService(
    private val urlRepository: UrlRepository
) {

    fun short(urlDto: UrlDto): UrlDto {
        var shortenUrl = generate()
        while (urlRepository.findById(shortenUrl).isPresent) {
            shortenUrl = generate()
        }
        save(urlDto.url, shortenUrl)
        urlDto.url = shortenUrl
        return urlDto
    }

    private fun save(originalUrl: String, shortenUrl: String): Url {
        val url = Url(originalUrl = originalUrl, shortenUrl = shortenUrl)
        urlRepository.save(url)
        return url
    }

    fun validate(urlDto: UrlDto) {
        val urlValidator = UrlValidator()
        val isValid = urlValidator.isValid(urlDto.url)
        if (isValid.not())
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid URL")
    }

    fun retrieve(shortenUrl: String): String {
        val url: Url = urlRepository.findById(shortenUrl).orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND)
        }
        countAccess(url)
        return url.originalUrl
    }

    private fun countAccess(url: Url) {
        url.accesses += 1
        urlRepository.save(url)
    }

    fun stats(): List<Url> {
        return urlRepository.findAllByOrderByAccessesDesc()
    }
}