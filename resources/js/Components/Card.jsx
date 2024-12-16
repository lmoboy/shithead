import React from 'react';

export default function Card({ suit, value, style }) {
    const suits = {
        hearts: '♥',
        diamonds: '♦',
        clubs: '♣',
        spades: '♠'
    };

    const wonkyValues = {
        1: 'A',
        2: 2,
        3: 3,
        4: 4,
        5: 5,
        106: 6,
        7: 7,
        8: 8,
        9: 9,
        110: 10,
        11: 'J',
        12: 'Q',
        13: 'K'

    };

    const getColor = (suit) => {
        return ['hearts', 'diamonds'].includes(suit) ? 'text-red-600' : 'text-gray-900';
    };

    return (
        <div 
            className={`absolute ${getColor(suit)} bg-white rounded-lg shadow-lg p-4 w-24 h-36 flex flex-col justify-between transform transition-transform hover:scale-110`}
            style={style}
        >
            <div className="text-xl font-bold">{wonkyValues[value]}</div>
            <div className="text-4xl self-center">{suits[suit]}</div>
            <div className="text-xl font-bold self-end rotate-180">{wonkyValues[value]}</div>
        </div>
    );
}
