package dev.stasleonov.bee.domain

import platform.AVFAudio.AVAudioPlayer
import platform.AVFAudio.AVAudioSession
import platform.AVFAudio.AVAudioSessionCategoryPlayback
import platform.AVFAudio.setActive
import platform.Foundation.NSBundle
import platform.Foundation.NSURL
import platform.Foundation.NSURL.Companion.fileURLWithPath

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
actual class AudioPlayer {

    private var audioPlayer: MutableMap<String,AVAudioPlayer?> =
        mutableMapOf()
    private var fallingSoundPlayer: AVAudioPlayer? = null

    init {
        val session = AVAudioSession.sharedInstance()
        session.setCategory(AVAudioSessionCategoryPlayback, error = null)
        session.setActive(true, null)
    }

    actual fun playGameOverSound() {
        stopFallingSound()
        playSound(soundName = "game_over")
    }

    actual fun playJumpSound() {
        stopFallingSound()
        playSound(soundName = "jump")
    }

    actual fun playFallingSound() {
        fallingSoundPlayer = playSound(soundName = "falling")
    }

    actual fun stopFallingSound() {
        fallingSoundPlayer?.stop()
        fallingSoundPlayer = null
    }

    actual fun playGameSoundInLoop() {
        val url = getSoundUrl(resourceName = "game_sound")
        val player = url?.let { AVAudioPlayer(it,null) }
        player?.numberOfLoops = -1
        player?.prepareToPlay()
        player?.play()
        audioPlayer["game_sound"] = player
    }

    actual fun stopGameSound() {
        playGameOverSound()
        audioPlayer["game_sound"]?.stop()
        audioPlayer["game_sound"] = null
    }

    actual fun release() {
        audioPlayer.values.forEach { it?.stop() }
        audioPlayer.clear()
        fallingSoundPlayer?.stop()
        fallingSoundPlayer = null
    }

    private fun playSound(soundName: String): AVAudioPlayer? {
        val url = getSoundUrl(soundName)
        val player = url?.let { AVAudioPlayer(it,null) }
        player?.prepareToPlay()
        player?.play()
        audioPlayer[soundName] = player
        return player
    }

    private fun getSoundUrl(resourceName: String): NSURL? {
        val bundle = NSBundle.mainBundle()
        val path = bundle.pathForResource(resourceName, "wav")
        return path?.let { fileURLWithPath(it) }
    }
}