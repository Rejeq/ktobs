package com.rejeq.ktobs.request

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.request.record.*
import com.rejeq.ktobs.runObsTest
import com.rejeq.ktobs.tryObsRequest
import kotlinx.coroutines.delay
import kotlin.test.*

class RecordTest {
    companion object {
        const val RECORD_DELAY = 1000L
    }

    suspend fun ObsSession.cleanup() {
        tryObsRequest {
            stopRecord()
            delay(RECORD_DELAY)
        }
    }

    @Test
    fun testRecord() =
        runObsTest(cleanup = { cleanup() }) {
            var recordStatus = getRecordStatus()
            println("Initial record status: $recordStatus")

            if (!recordStatus.outputActive) {
                startRecord()
            }

            pauseRecord()
            delay(RECORD_DELAY)

            toggleRecord()
            delay(RECORD_DELAY)

            toggleRecordPause()
            delay(RECORD_DELAY)

            recordStatus = getRecordStatus()
            if (recordStatus.outputActive) {
                stopRecord()
            }
        }
}
