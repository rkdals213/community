package com.example.common.logger

import java.util.*

class TraceStatus(
    val traceId: TraceId,
    val startTimeMs: Long,
    val message: String
)

class TraceId(
    val id: String = createId(),
    val level: Int = 0
) {
    fun createNextId(): TraceId = TraceId(id, level + 1)

    fun createPreviousId(): TraceId = TraceId(id, level - 1)

    fun isFirstLevel() = level == 0
}

private fun createId(): String = UUID.randomUUID().toString().substring(0, 8)