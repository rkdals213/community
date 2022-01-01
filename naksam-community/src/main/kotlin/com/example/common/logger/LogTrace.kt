package com.example.common.logger

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

interface LogTrace {
    fun begin(message: String): TraceStatus
    fun end(status: TraceStatus)
    fun exception(status: TraceStatus, e: Exception)
}

@Component
class LogTraceImpl : LogTrace {
    companion object {
        private const val START_PREFIX = "-->"
        private const val COMPLETE_PREFIX = "<--"
        private const val EX_PREFIX = "<X-"
    }

    private val log = LoggerFactory.getLogger(this::class.java)

    private val traceIdHolder = ThreadLocal<TraceId>()

    override fun begin(message: String): TraceStatus {
        syncTraceId()
        val traceId = traceIdHolder.get()
        val startTimeMs = System.currentTimeMillis()
        log.info(
            "[{}] {}{}",
            traceId.id,
            addSpace(START_PREFIX, traceId.level),
            message
        )
        return TraceStatus(traceId, startTimeMs, message)
    }

    override fun end(status: TraceStatus) {
        complete(status, null)
    }

    override fun exception(status: TraceStatus, e: Exception) {
        complete(status, e)
    }

    private fun complete(status: TraceStatus, e: Exception?) {
        val stopTimeMs = System.currentTimeMillis()
        val resultTimeMs: Long = stopTimeMs - status.startTimeMs
        val traceId: TraceId = status.traceId
        completeLog(e, traceId, status, resultTimeMs)
        releaseTraceId()
    }

    private fun completeLog(e: Exception?, traceId: TraceId, status: TraceStatus, resultTimeMs: Long) {
        if (e == null) {
            log.info(
                "[{}] {}{} time={}ms",
                traceId.id,
                addSpace(COMPLETE_PREFIX, traceId.level),
                status.message,
                resultTimeMs
            )
            return
        }

        log.info(
            "[{}] {}{} time={}ms ex={}",
            traceId.id,
            addSpace(EX_PREFIX, traceId.level),
            status.message,
            resultTimeMs,
            e.toString()
        )
    }

    private fun syncTraceId() {
        val traceId = traceIdHolder.get()
        if (traceId == null) {
            traceIdHolder.set(TraceId())
            return
        }

        traceIdHolder.set(traceId.createNextId())
    }

    private fun releaseTraceId() {
        val traceId = traceIdHolder.get()
        if (traceId.isFirstLevel()) {
            traceIdHolder.remove() //destroy
            return
        }

        traceIdHolder.set(traceId.createPreviousId())
    }

    private fun addSpace(prefix: String, level: Int): String {
        val sb = StringBuilder()
        for (i in 0 until level) {
            sb.append(if (i == level - 1) "|$prefix" else "|   ")
        }
        return sb.toString()
    }
}
