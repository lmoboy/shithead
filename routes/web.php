<?php

use App\Http\Controllers\ProfileController;
use Illuminate\Foundation\Application;
use Illuminate\Support\Facades\Route;
use Inertia\Inertia;

Route::get('/', function () {
    return Inertia::render('Welcome', [
        'canLogin' => Route::has('login'),
        'canRegister' => Route::has('register'),
        'laravelVersion' => Application::VERSION,
        'phpVersion' => PHP_VERSION,
    ]);
});

Route::get('/dashboard', function () {
    return Inertia::render('Dashboard');
})->middleware(['auth', 'verified'])->name('dashboard');

Route::middleware('auth')->group(function () {
    Route::get('/profile', [ProfileController::class, 'edit'])->name('profile.edit');
    Route::patch('/profile', [ProfileController::class, 'update'])->name('profile.update');
    Route::delete('/profile', [ProfileController::class, 'destroy'])->name('profile.destroy');

    // Game routes
    Route::get('/create-game', function() {
        return Inertia::render('Game/CreateGame');
    })->name('game.create');

    Route::get('/join-game', function() {
        return Inertia::render('Game/JoinGame');
    })->name('game.join');

    Route::get('/waiting-room', function() {
        return Inertia::render('Game/WaitingRoom');
    })->name('game.waiting');

    Route::get('/test', function() {
        return Inertia::render('Game/GameBoard');
    })->name('game.test');
});

require __DIR__.'/auth.php';
