import { useState, useEffect } from 'react';
import AuthenticatedLayout from '@/Layouts/AuthenticatedLayout';
import { Head } from '@inertiajs/react';
import Card from '@/Components/Game/Card';
import CardPile from '@/Components/Game/CardPile';

export default function GameBoard() {
    // Game state
    const [deck, setDeck] = useState([]);
    const [discardPile, setDiscardPile] = useState([]);
    const [tableCard, setTableCard] = useState(null);
    const [currentValue, setCurrentValue] = useState(0);
    const [playerCount, setPlayerCount] = useState(2);
    const [playerTurn, setPlayerTurn] = useState(0);
    const [players, setPlayers] = useState([]);
    const [cardsLeft, setCardsLeft] = useState(52);

    // Initialize game
    useEffect(() => {
        initializeGame();
    }, []);

    const initializeGame = () => {
        const suits = ['hearts', 'diamonds', 'clubs', 'spades'];
        const values = ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13'];
        const newDeck = [];

        // Create and shuffle deck
        for (let suit of suits) {
            for (let value of values) {
                newDeck.push({suit, value});
            }
        }
        shuffleDeck(newDeck);
        setDeck(newDeck);

        // Initialize players
        const initialPlayers = [];
        for (let i = 0; i < playerCount; i++) {
            initialPlayers.push({
                id: i,
                hand: [],
                faceUpCards: [],
                faceDownCards: []
            });
        }
        setPlayers(initialPlayers);
        dealInitialCards(newDeck, initialPlayers);
    };

    const shuffleDeck = (deck) => {
        for (let i = deck.length - 1; i > 0; i--) {
            const j = Math.floor(Math.random() * (i + 1));
            [deck[i], deck[j]] = [deck[j], deck[i]];
        }
    };

    const dealInitialCards = (deck, players) => {
        const updatedPlayers = [...players];
        let currentDeck = [...deck];

        // Deal 3 cards to each player's hand, face-up cards, and face-down cards
        for (let player of updatedPlayers) {
            player.hand = currentDeck.splice(0, 3);
            player.faceUpCards = currentDeck.splice(0, 3);
            player.faceDownCards = currentDeck.splice(0, 3);
        }

        setPlayers(updatedPlayers);
        setDeck(currentDeck);
        setCardsLeft(currentDeck.length);
    };

    const playCard = (card, playerId) => {
        if (playerId !== playerTurn) return false;
        
        // Check if the play is valid
        if (!isValidPlay(card)) return false;

        // Handle special cards
        if (card.value === '10') {
            discardPile.push(tableCard);
            setDiscardPile([...discardPile]);
            setTableCard(null);
            setCurrentValue(0);
            return true;
        }

        if (card.value === '6') {
            setCurrentValue(0);
        } else {
            setCurrentValue(card.value);
        }

        setTableCard(card);
        return true;
    };

    const isValidPlay = (card) => {
        // First play of the game
        if (!tableCard) return true;

        // Check for special cards
        if (card.value === '10') return true;
        if (card.value === '6') return true;

        // Check for four of a kind
        const lastFourCards = discardPile.slice(-3).concat(tableCard);
        if (lastFourCards.length === 4 && 
            lastFourCards.every(c => c.value === card.value)) {
            return true;
        }

        // Normal play - card value must be greater than or equal to current value
        return parseInt(card.value) >= parseInt(currentValue);
    };

    const nextTurn = () => {
        setPlayerTurn((prevTurn) => (prevTurn + 1) % playerCount);
    };

    return (
        <AuthenticatedLayout>
            <Head title="Game Board" />
            <div className="py-6">
                <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
                    <div className="bg-green-800 rounded-lg shadow-xl p-4 min-h-[800px] relative">
                        {/* Current player info */}
                        <div className="absolute top-4 left-4 text-white">
                            Player {playerTurn + 1}'s turn
                        </div>

                        {/* Table card */}
                        <div className="absolute top-1/2 left-1/2 transform -translate-x-1/2 -translate-y-1/2">
                            {tableCard && <Card {...tableCard} />}
                        </div>

                        {/* Deck */}
                        <div className="absolute bottom-4 left-4">
                            <CardPile cards={deck} />
                            <div className="absolute -bottom-2 -right-2 bg-gray-800 text-white px-2 py-1 rounded-full text-sm font-bold">
                                {cardsLeft}
                            </div>
                        </div>

                        {/* Current player's hand */}
                        {players[playerTurn] && (
                            <div className="absolute bottom-4 left-1/2 transform -translate-x-1/2">
                                <div className="flex space-x-4">
                                    {players[playerTurn].hand.map((card, index) => (
                                        <Card 
                                            key={index} 
                                            {...card} 
                                            onClick={() => playCard(card, playerTurn) && nextTurn()}
                                        />
                                    ))}
                                </div>
                            </div>
                        )}
                    </div>
                </div>
            </div>
        </AuthenticatedLayout>
    );
}
