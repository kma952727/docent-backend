package com.mydocent.model.art.dto

import com.mydocent.model.art.entity.PlayList
import io.swagger.v3.oas.annotations.media.Schema

class ApiFindPlayListDto {

    @Schema(description = "플레이 리스트 정보")
    data class Response(
        @Schema(description = "플레이 리스트 pk", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        val id: Int,

        @Schema(description = "ai 플랫폼의 데이터와 연결시켜주는 FK", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        val aiServiceKey: String? = null,

        @Schema(description = "검색 키워드", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        val keyword: String? = null,

        @Schema(description = "작가명", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        val author: String? = null,

        @Schema(description = "작품명", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        val workTitle: String? = null,

        @Schema(description = "위치", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        val location: String? = null,

        @Schema(description = "???", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        val workIntro: String? = null,

        @Schema(description = "???", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        val authorIntro: String? = null,

        @Schema(description = "???", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        val workBackground: String? = null,

        @Schema(description = "???", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        val appreciationPoint: String? = null,

        @Schema(description = "역사", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        val history: String? = null,

        @Schema(description = "출처", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        val source: String? = null
    ) {
        companion object {
            fun fromEntity(playList: PlayList): Response {
                return Response(
                    id = playList.id!!,
                    aiServiceKey = playList.aiServiceKey,
                    keyword = playList.keyword,
                    author = playList.author,
                    workTitle = playList.workTitle,
                    location = playList.location,
                    workIntro = playList.workIntro,
                    authorIntro = playList.authorIntro,
                    workBackground = playList.workBackground,
                    appreciationPoint = playList.appreciationPoint,
                    history = playList.history,
                    source = playList.source
                )
            }
        }
    }
}