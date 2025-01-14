package dev.stasleonov.bee.domain

import org.w3c.dom.Audio

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class AudioPlayer {

    private val audioElement = mutableMapOf<String, Audio>()

    actual fun playGameOverSound() {
        stopFallingSound()
        playSound(fileName = "game_over.wav")
    }

    actual fun playJumpSound() {
        stopFallingSound()
        playSound(fileName = "jump.wav")
    }

    actual fun playFallingSound() {
        playSound(fileName = "falling.wav")
    }

    actual fun stopFallingSound() {
        stopSound(fileName = "falling.wav")
    }

    actual fun playGameSoundInLoop() {
        playSound(fileName = "game_sound.wav",loop = true)
    }

    actual fun stopGameSound() {
        playGameOverSound()
        stopSound(fileName = "game_sound.wav")
    }

    actual fun release() {
        stopAllSounds()
        audioElement.clear()
    }

    private fun stopSound(fileName: String) {
        audioElement[fileName]?.let { audio ->
            audio.pause()
            audio.currentTime = 0.0
        }
    }

    private fun stopAllSounds() {
        audioElement.values.forEach { audio ->
            audio.pause()
            audio.currentTime = 0.0
        }
    }

    private fun playSound(
        fileName: String,
        loop: Boolean = false
    ) {
        val audio = audioElement[fileName] ?: createAudioElement(fileName).also {
            audioElement[fileName] = it
        }
        audio.loop = loop
        audio.play().catch {
            println("Error playing file: $fileName")
            it
        }
    }

    private fun createAudioElement(fileName: String): Audio {
        val path = "src/commonMain/composeResources/files/$fileName"
        return Audio(path).apply {
            onerror = { _, _, _, _, _ ->
                println("Error loading file: $path")
                null
            }
        }
    }
}