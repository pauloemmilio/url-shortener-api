package com.paulo.urlshortner.domain.dto

import javax.validation.constraints.NotBlank

class UrlDto(
    @NotBlank(message = "Url is mandatory")
    var url: String
)