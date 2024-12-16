import React from 'react';

export default function Card({ suit, value, faceDown = false, onClick }) {
    // Suit symbols and colors
    const suits = {
        hearts: { symbol: '♥', color: 'text-red-600' },
        diamonds: { symbol: '♦', color: 'text-red-600' },
        clubs: { symbol: '♣', color: 'text-gray-900' },
        spades: { symbol: '♠', color: 'text-gray-900' }
    };

    // Value display mapping (can be extended for face cards)
    const displayValue = {
        '1': 'A',
        '11': 'J',
        '12': 'Q',
        '13': 'K',
    }[value] || value;

    // If card is face down, show back of card
    if (faceDown) {
        return (
            <div className="w-24 h-32 bg-red-900 rounded-lg border-2 border-white/20 shadow-lg">
                <div className="w-full h-full rounded-lg bg-[repeating-linear-gradient(45deg,#9b2c2c,#9b2c2c_10px,#822727_10px,#822727_20px)]">
                </div>
            </div>
        );
    }

    // Get suit info
    const suitInfo = suits[suit] || suits.spades;

    return (
        <div 
            className={`w-24 h-32 bg-white rounded-lg shadow-lg relative select-none 
                ${onClick ? 'cursor-pointer transform transition-transform hover:scale-105 hover:shadow-xl active:scale-95' : ''}`}
            onClick={onClick}
        >
            {/* Top left value and suit */}
            <div className="absolute top-2 left-2">
                <div className={`text-lg font-bold ${suitInfo.color}`}>
                    {displayValue}
                </div>
                <div className={`text-lg leading-none ${suitInfo.color}`}>
                    {suitInfo.symbol}
                </div>
            </div>

            {/* Center suit */}
            <div className={`absolute inset-0 flex items-center justify-center ${suitInfo.color}`}>
                <span className="text-4xl">{suitInfo.symbol}</span>
            </div>

            {/* Bottom right value and suit (inverted) */}
            <div className="absolute bottom-2 right-2 rotate-180">
                <div className={`text-lg font-bold ${suitInfo.color}`}>
                    {displayValue}
                </div>
                <div className={`text-lg leading-none ${suitInfo.color}`}>
                    {suitInfo.symbol}
                </div>
            </div>
        </div>
    );
}
