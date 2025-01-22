package com.rejeq.ktobs.request

import com.rejeq.ktobs.ObsTest
import com.rejeq.ktobs.request.outputs.*
import kotlinx.coroutines.test.runTest
import kotlin.test.*

class OutputsTest : ObsTest() {
    @Test
    fun testOutputs() =
        runTest {
            val outputs = session.getOutputList()
            println("Available outputs: $outputs")

            var camActive = session.getVirtualCamStatus()
            println("Initial virtual cam status: $camActive")

            if (!camActive) {
                session.startVirtualCam()
            }

            session.toggleVirtualCam()

            camActive = session.getVirtualCamStatus()
            if (!camActive) {
                session.startVirtualCam()
            }

            outputs.first()?.let { output ->
                println("Testing output: ${output.name}")

                val outputStatus = session.getOutputStatus(output.name)
                println("Output status: $outputStatus")

                val outputSettings =
                    session.getOutputSettings(
                        output.name,
                    )
                println("Output settings: $outputSettings")

                if (!outputStatus.active) {
                    session.setOutputSettings(
                        name = output.name,
                        settings = outputSettings,
                    )

                    session.startOutput(output.name)

                    session.toggleOutput(output.name)
                }
            }

            camActive = session.getVirtualCamStatus()
            if (camActive) {
                session.stopVirtualCam()
            }
        }

    @AfterTest
    fun cleanup() =
        runTest {
            tryObsRequest {
                session.stopVirtualCam()
            }

            tryObsRequest {
                session.stopReplayBuffer()
            }

            tryObsRequest {
                val outputs = session.getOutputList()
                outputs.forEach { output ->
                    if (session.getOutputStatus(output.name).active) {
                        session.stopOutput(output.name)
                    }
                }
            }
        }
}
