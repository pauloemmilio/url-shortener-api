package com.paulo.urlshortner.controller

import com.paulo.urlshortner.domain.dto.UrlDto
import com.paulo.urlshortner.domain.model.Url
import com.paulo.urlshortner.service.UrlService
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse

@RestController
class UrlController(
    private val urlService: UrlService
) {

    @PostMapping("/url/short")
    fun short(@RequestBody urlDto: UrlDto): UrlDto {
        return urlService.short(urlDto)
    }

    @GetMapping("/{shortenUrl}")
    fun retrieve(@PathVariable shortenUrl: String, response: HttpServletResponse) {
        val originalUrl = urlService.retrieve(shortenUrl)
        response.sendRedirect(originalUrl)
    }

    @GetMapping("/url/stats")
    fun stats(): List<Url> {
        return urlService.stats()
    }

}