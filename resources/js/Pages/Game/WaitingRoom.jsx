import { useState, useEffect } from 'react';
import AuthenticatedLayout from '@/Layouts/AuthenticatedLayout';
import { Head } from '@inertiajs/react';
import { router } from '@inertiajs/react';

export default function WaitingRoom() {
    const [loadingText, setLoadingText] = useState('Searching for an open game...');
    const messages = [
        'Searching for an open game...',
        'Looking for opponents...',
        'Waiting for players to join...',
        'Almost there...'
    ];
    
    useEffect(() => {
        const interval = setInterval(() => {
            setLoadingText(messages[Math.floor(Math.random() * messages.length)]);
        }, 2000);
        
        return () => clearInterval(interval);
    }, []);

    const cancelSearch = () => {
        router.visit('/dashboard');
    };

    return (
        <AuthenticatedLayout>
            <Head title="Waiting Room" />

            <div className="py-12">
                <div className="max-w-7xl mx-auto sm:px-6 lg:px-8">
                    <div className="bg-white dark:bg-gray-800 overflow-hidden shadow-sm sm:rounded-lg p-6">
                        <div className="flex flex-col items-center justify-center space-y-6">
                            {/* Loader */}
                            <div className="animate-spin rounded-full h-16 w-16 border-t-2 border-b-2 border-indigo-500"></div>
                            
                            {/* Loading Text */}
                            <div className="text-lg text-gray-600 dark:text-gray-300">
                                {loadingText}
                            </div>

                            {/* Cancel Button */}
                            <button
                                onClick={cancelSearch}
                                className="inline-flex items-center px-4 py-2 bg-red-600 border border-transparent rounded-md font-semibold text-xs text-white uppercase tracking-widest hover:bg-red-700 focus:bg-red-700 active:bg-red-900 focus:outline-none focus:ring-2 focus:ring-red-500 focus:ring-offset-2 transition ease-in-out duration-150"
                            >
                                Cancel Search
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </AuthenticatedLayout>
    );
}
