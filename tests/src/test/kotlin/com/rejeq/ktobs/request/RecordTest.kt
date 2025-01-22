package com.rejeq.ktobs.request

import com.rejeq.ktobs.ObsTest
import com.rejeq.ktobs.request.record.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.test.*

class RecordTest : ObsTest() {
    companion object {
        const val RECORD_DELAY = 1000L
    }

    @Test
    fun testRecord() =
        runBlocking {
            var recordStatus = session.getRecordStatus()
            println("Initial record status: $recordStatus")

            if (!recordStatus.outputActive) {
                session.startRecord()
            }

            session.pauseRecord()
            delay(RECORD_DELAY)

            session.toggleRecord()
            delay(RECORD_DELAY)

            session.toggleRecordPause()
            delay(RECORD_DELAY)

            recordStatus = session.getRecordStatus()
            if (recordStatus.outputActive) {
                session.stopRecord()
            }
        }

    @AfterTest
    fun cleanup() =
        runBlocking {
            tryObsRequest {
                session.stopRecord()
                delay(RECORD_DELAY)
            }
        }
}
