package com.mydocent.art.repository

import com.mydocent.model.art.entity.PlayList
import com.mydocent.utils.error.DocentException
import com.mydocent.utils.error.ErrorCode
import org.springframework.data.jpa.repository.JpaRepository

interface PlayListRepository: JpaRepository<PlayList, Int> {
    fun findByAiServiceKey(aiServiceKey: String): PlayList?
}

fun PlayListRepository.findOrThrow(aiServiceKey: String): PlayList {
    return findByAiServiceKey(aiServiceKey = aiServiceKey) ?: throw DocentException(ErrorCode.NOT_FOUND_PLAY_LIST)
}