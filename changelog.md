# Changelog

All notable changes to the Shithead Card Game project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [0.1.0] - 2024-03-18

### Added
- Initial project setup with Laravel, Inertia.js, and React
- Basic game interface components:
  - Card component with suit and value display
  - Game board with deck counter
  - Waiting room with animated loading messages
  - Game creation form
  - Game joining interface
- Authentication system with login and registration
- Protected game routes:
  - `/create-game` for game creation
  - `/join-game` for joining existing games
  - `/waiting-room` for matchmaking
  - `/test` for game board testing
- Dashboard redesign with game navigation buttons
- Basic styling using Tailwind CSS

### Changed
- Updated Vite from version 5.0 to 6.0.2
- Modified default user seeder for easier testing
- Restructured dashboard layout for game navigation

### Technical
- All game routes protected by authentication middleware
- Card component supports:
  - Standard card suits (♥, ♦, ♣, ♠)
  - Values 1-13 with face card mapping
  - Face-down card display
- Inertia.js integration for seamless page transitions
- Responsive design for all screen sizes

### Upcoming
- Game logic implementation
- Real-time multiplayer functionality
- WebSocket integration
- Game state management
- API endpoints for game actions
- Player matchmaking system
- Game history and statistics
