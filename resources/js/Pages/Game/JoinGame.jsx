import AuthenticatedLayout from '@/Layouts/AuthenticatedLayout';
import { Head } from '@inertiajs/react';
import InputLabel from '@/Components/InputLabel';
import TextInput from '@/Components/TextInput';
import PrimaryButton from '@/Components/PrimaryButton';
import { router } from '@inertiajs/react';

export default function JoinGame() {
    const handleSubmit = (e) => {
        e.preventDefault();
        // For now, just redirect to waiting room
        router.visit('/waiting-room');
    };

    return (
        <AuthenticatedLayout>
            <Head title="Join Game" />

            <div className="py-12">
                <div className="max-w-7xl mx-auto sm:px-6 lg:px-8">
                    <div className="bg-white dark:bg-gray-800 overflow-hidden shadow-sm sm:rounded-lg p-6">
                        <form onSubmit={handleSubmit} className="space-y-6">
                            <div>
                                <InputLabel htmlFor="gamePassword" value="Game Password" />
                                <TextInput
                                    id="gamePassword"
                                    type="text"
                                    className="mt-1 block w-full"
                                    required
                                />
                                <p className="mt-2 text-sm text-gray-500 dark:text-gray-400">
                                    Enter the password provided by the game creator
                                </p>
                            </div>

                            <div className="flex items-center justify-end">
                                <PrimaryButton>
                                    Join Game
                                </PrimaryButton>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </AuthenticatedLayout>
    );
}
