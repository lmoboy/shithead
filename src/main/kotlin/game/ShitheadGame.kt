package game

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class ShitheadGame {
    var northHand by mutableStateOf(emptyList<Int>())
        private set
    var southHand by mutableStateOf(emptyList<Int>())
        private set
    var eastHand by mutableStateOf(emptyList<Int>())
        private set
    var westHand by mutableStateOf(emptyList<Int>())
        private set
    var centerPile by mutableStateOf(emptyList<Int>())
        private set
    var currentPlayer by mutableStateOf(0) // 0: South, 1: West, 2: North, 3: East

    private var deck = mutableListOf<Int>()

    init {
        startNewGame()
    }

    private fun startNewGame() {
        // Create and shuffle deck (1-13 for each suit)
        deck = (1..13).flatMap { num -> List(4) { num } }.toMutableList()
        deck.shuffle()

        // Deal cards
        southHand = deck.take(5).toList()
        westHand = deck.subList(5, 10).toList()
        northHand = deck.subList(10, 15).toList()
        eastHand = deck.subList(15, 20).toList()

        // Remove dealt cards from deck
        deck = deck.subList(20, deck.size).toMutableList()

        // Start with one card in center
        centerPile = listOf(deck.removeFirst())

        // Start with South player
        currentPlayer = 0
    }

    fun onCardClicked(value: Int) {
        val currentHand = when (currentPlayer) {
            0 -> southHand
            1 -> westHand
            2 -> northHand
            3 -> eastHand
            else -> return
        }

        if (isValidPlay(value, currentHand)) {
            playCard(value)
            nextPlayer()
        }
    }

    private fun isValidPlay(value: Int, hand: List<Int>): Boolean {
        if (!hand.contains(value)) return false

        val topCard = centerPile.lastOrNull() ?: return true

        return when (value) {
            2 -> true  // Can play on anything
            10 -> true // Burns the pile
            3 -> true  // Resets to 3
            else -> value >= topCard
        }
    }

    private fun playCard(value: Int) {
        when (currentPlayer) {
            0 -> southHand = southHand - value
            1 -> westHand = westHand - value
            2 -> northHand = northHand - value
            3 -> eastHand = eastHand - value
        }

        when (value) {
            10 -> centerPile = emptyList() // Burn the pile
            else -> centerPile = centerPile + value
        }

        // Draw a card if deck isn't empty
        if (deck.isNotEmpty()) {
            val newCard = deck.removeFirst()
            when (currentPlayer) {
                0 -> southHand = southHand + newCard
                1 -> westHand = westHand + newCard
                2 -> northHand = northHand + newCard
                3 -> eastHand = eastHand + newCard
            }
        }
    }

    private fun nextPlayer() {
        currentPlayer = (currentPlayer + 1) % 4
    }

    fun pickUpPile() {
        when (currentPlayer) {
            0 -> southHand = southHand + centerPile
            1 -> westHand = westHand + centerPile
            2 -> northHand = northHand + centerPile
            3 -> eastHand = eastHand + centerPile
        }
        centerPile = emptyList()
        nextPlayer()
    }
}
