package com.mydocent.api.controller

import com.mydocent.art.service.ArtService
import com.mydocent.model.art.dto.ApiFindPlayListDto
import com.mydocent.utils.api.ApiResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RequestMapping("/api")
@Tag(name = "PlayListController - 플레이 리스트 관련 기능")
@RestController
class PlayListController(private val artService: ArtService) {

    @Operation(summary = "플레이 리스트 1개 조회")
    @GetMapping("/play-list/ai-service-key/{aiServiceKey}")
    @ResponseStatus(HttpStatus.OK)
    fun findPlayListByAiServiceKey(
        @Parameter(description = "플레이 리스트와 연결된 ai 서비스 번호", required = true)
        @PathVariable aiServiceKey: String
    ): ApiResponse<ApiFindPlayListDto.Response> {
        return ApiResponse(data = artService.findPlayListByAiServiceKey(aiServiceKey = aiServiceKey))
    }
}