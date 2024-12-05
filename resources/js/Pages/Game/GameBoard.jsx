import { useState } from 'react';
import AuthenticatedLayout from '@/Layouts/AuthenticatedLayout';
import { Head } from '@inertiajs/react';
import Card from '@/Components/Game/Card';

export default function GameBoard() {
    const [cardsLeft, setCardsLeft] = useState(52);

    return (
        <AuthenticatedLayout>
            <Head title="Game Board" />

            <div className="py-6">
                <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
                    {/* Game Container */}
                    <div className="bg-green-800 rounded-lg shadow-xl p-4 min-h-[800px] relative">
                        {/* Deck */}
                        <div className="absolute bottom-4 left-4">
                            <div className="relative">
                                <Card faceDown={true} />
                                {/* Card counter */}
                                <div className="absolute -bottom-2 -right-2 bg-gray-800 text-white px-2 py-1 rounded-full text-sm font-bold">
                                    {cardsLeft}
                                </div>
                            </div>
                        </div>

                        {/* Example Cards */}
                        <div className="flex space-x-4 p-4">
                            <Card suit="hearts" value="1" />
                            <Card suit="diamonds" value="13" />
                            <Card suit="clubs" value="7" />
                            <Card suit="spades" value="12" />
                        </div>
                    </div>
                </div>
            </div>
        </AuthenticatedLayout>
    );
}
