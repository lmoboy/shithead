import React from 'react';
import Card from "./Card";

export default function CardPile({ cards, onClick }) {
    return (
        <div className="relative w-24">
            {/* Stacked card effect */}
            <div className="absolute -top-1 left-0.5 w-24">
                <Card faceDown={true} />
            </div>
            <div className="absolute -top-0.5 left-0.25 w-24">
                <Card faceDown={true} />
            </div>
            {/* Top card */}
            <div className="relative">
                <Card faceDown={true} onClick={onClick} />
                {/* Card counter */}
                <div className="absolute -bottom-2 -right-2 bg-gray-800 text-white px-2 py-1 rounded-full text-sm font-bold">
                    {cards.length}
                </div>
            </div>
        </div>
    );
}

CardPile.propTypes = {
    cards: Array,
    onClick: Function
};