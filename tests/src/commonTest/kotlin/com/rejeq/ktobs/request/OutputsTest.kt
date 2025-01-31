package com.rejeq.ktobs.request

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.request.outputs.*
import com.rejeq.ktobs.runObsTest
import com.rejeq.ktobs.tryObsRequest
import kotlin.test.*

class OutputsTest {
    suspend fun ObsSession.cleanup() {
        tryObsRequest {
            stopVirtualCam()
        }

        tryObsRequest {
            stopReplayBuffer()
        }

        tryObsRequest {
            val outputs = getOutputList()
            outputs.forEach { output ->
                if (getOutputStatus(output.name).active) {
                    stopOutput(output.name)
                }
            }
        }
    }

    @Test
    fun testOutputs() =
        runObsTest(cleanup = { cleanup() }) {
            val outputs = getOutputList()
            println("Available outputs: $outputs")

            var camActive = getVirtualCamStatus()
            println("Initial virtual cam status: $camActive")

            if (!camActive) {
                startVirtualCam()
            }

            toggleVirtualCam()

            camActive = getVirtualCamStatus()
            if (!camActive) {
                startVirtualCam()
            }

            outputs.first()?.let { output ->
                println("Testing output: ${output.name}")

                val outputStatus = getOutputStatus(output.name)
                println("Output status: $outputStatus")

                val outputSettings =
                    getOutputSettings(
                        output.name,
                    )
                println("Output settings: $outputSettings")

                if (!outputStatus.active) {
                    setOutputSettings(
                        name = output.name,
                        settings = outputSettings,
                    )

                    startOutput(output.name)

                    toggleOutput(output.name)
                }
            }

            camActive = getVirtualCamStatus()
            if (camActive) {
                stopVirtualCam()
            }
        }
}
