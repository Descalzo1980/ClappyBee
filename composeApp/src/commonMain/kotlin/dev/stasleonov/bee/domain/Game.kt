package dev.stasleonov.bee.domain

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlin.random.Random

data class Game(
    val screenWidth: Int = 0,
    val screenHeight: Int = 0,
    val gravity: Float = 0.5f,
    val beeRadius: Float = 30f,
    val beeJumpImpulse: Float = -12f,
    val beeMaxVelocity: Float = 25f,
    val pipeWidth: Float = 150f,
    val pipeVelocity: Float = 5f,
    val pipeGapSize: Float = 250f
) {

    var status by mutableStateOf(GameStatus.Idle)
        private set
    var beeVelocity by mutableStateOf(0f)
        private set
    var bee by mutableStateOf(
        Bee(
            x = (screenWidth / 4).toFloat(),
            y = (screenHeight / 2).toFloat(),
            radius = beeRadius
        )
    )
        private set


    var pipePairs = mutableStateListOf<PipePair>()
    fun start() {
        status = GameStatus.Started
    }

    fun gameOver() {
        status = GameStatus.Over
    }

    fun jump() {
        beeVelocity = beeJumpImpulse
    }

    fun restartGame() {
        resetBeePosition()
        removePipe()
        start()
    }

    private fun resetBeePosition() {
        bee =  bee.copy(y = (screenHeight / 2).toFloat())
        beeVelocity = 0f
    }

    fun updateGameProgress() {
        if (bee.y < 0) {
            stopTheBee()
            return
        } else if (bee.y > screenHeight) {
            gameOver()
            return
        }
        beeVelocity = (beeVelocity + gravity)
            .coerceIn(-beeMaxVelocity, beeMaxVelocity)
        bee = bee.copy(y = bee.y + beeVelocity)

        spawnPipe()
    }

    private fun spawnPipe() {
        pipePairs.forEach { it.x -=pipeVelocity }
        pipePairs.removeAll { it.x + pipeWidth < 0 }

        if (pipePairs.isEmpty() || pipePairs.last().x < screenWidth / 2) {
            val initialPipeX = screenWidth.toFloat() + pipeWidth
            val topHeight = Random.nextFloat() * (screenHeight / 2)
            val bottomHeight = screenHeight - topHeight - pipeGapSize
            val newPipePair = PipePair(
                x = initialPipeX,
                y = topHeight + pipeGapSize / 2,
                topHeight = topHeight,
                bottomHeight = bottomHeight
            )
            pipePairs.add(newPipePair)
        }
    }

    private fun removePipe() {
        pipePairs.clear()
    }

    fun stopTheBee() {
        beeVelocity = 0f
        bee = bee.copy(y = 0f)
    }
}