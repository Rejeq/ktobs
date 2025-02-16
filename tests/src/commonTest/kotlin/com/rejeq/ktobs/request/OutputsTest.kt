package com.rejeq.ktobs.request

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.RequestCode
import com.rejeq.ktobs.request.outputs.*
import com.rejeq.ktobs.requestCanFailWith
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

            requestCanFailWith(RequestCode.InvalidResourceState) {
                var camActive = getVirtualCamStatus()
                println("Initial virtual cam status: $camActive")

                requestCanFailWith(RequestCode.OutputRunning) {
                    startVirtualCam()
                }

                toggleVirtualCam()
            }


            outputs.first().let { output ->
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

            requestCanFailWith(RequestCode.InvalidResourceState) {
                val camActive = getVirtualCamStatus()
                if (camActive) {
                    stopVirtualCam()
                }
            }
        }
}
