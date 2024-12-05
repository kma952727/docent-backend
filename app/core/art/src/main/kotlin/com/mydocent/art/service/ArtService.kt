package com.mydocent.art.service

import com.mydocent.art.repository.PlayListRepository
import com.mydocent.art.repository.findOrThrow
import com.mydocent.model.art.dto.ApiFindPlayListDto
import org.springframework.stereotype.Service

@Service
class ArtService(private val playListRepository: PlayListRepository) {

    fun findPlayListByAiServiceKey(aiServiceKey: String): ApiFindPlayListDto.Response {
        return playListRepository.findOrThrow(aiServiceKey = aiServiceKey).let(ApiFindPlayListDto.Response::fromEntity)
    }

}