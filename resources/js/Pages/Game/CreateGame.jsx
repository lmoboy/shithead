import { useState } from 'react';
import AuthenticatedLayout from '@/Layouts/AuthenticatedLayout';
import { Head } from '@inertiajs/react';
import InputLabel from '@/Components/InputLabel';
import TextInput from '@/Components/TextInput';
import Checkbox from '@/Components/Checkbox';
import PrimaryButton from '@/Components/PrimaryButton';
import { router } from '@inertiajs/react';

export default function CreateGame() {
    const [formData, setFormData] = useState({
        gameName: '',
        isPrivate: false,
        password: ''
    });

    const handleSubmit = (e) => {
        e.preventDefault();
        // For now, just redirect to waiting room
        router.visit('/waiting-room');
    };

    return (
        <AuthenticatedLayout>
            <Head title="Create Game" />

            <div className="py-12">
                <div className="max-w-7xl mx-auto sm:px-6 lg:px-8">
                    <div className="bg-white dark:bg-gray-800 overflow-hidden shadow-sm sm:rounded-lg p-6">
                        <form onSubmit={handleSubmit} className="space-y-6">
                            <div>
                                <InputLabel htmlFor="gameName" value="Game Name" />
                                <TextInput
                                    id="gameName"
                                    type="text"
                                    className="mt-1 block w-full"
                                    value={formData.gameName}
                                    onChange={(e) => setFormData({ ...formData, gameName: e.target.value })}
                                    required
                                />
                            </div>

                            <div className="block">
                                <label className="flex items-center">
                                    <Checkbox
                                        name="private"
                                        checked={formData.isPrivate}
                                        onChange={(e) => setFormData({ ...formData, isPrivate: e.target.checked })}
                                    />
                                    <span className="ms-2 text-sm text-gray-600 dark:text-gray-400">
                                        Make this game private
                                    </span>
                                </label>
                            </div>

                            {formData.isPrivate && (
                                <div>
                                    <InputLabel htmlFor="password" value="Game Password" />
                                    <TextInput
                                        id="password"
                                        type="text"
                                        className="mt-1 block w-full"
                                        value={formData.password}
                                        onChange={(e) => setFormData({ ...formData, password: e.target.value })}
                                        required
                                    />
                                </div>
                            )}

                            <div className="flex items-center justify-end">
                                <PrimaryButton>
                                    Create Game
                                </PrimaryButton>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </AuthenticatedLayout>
    );
}
