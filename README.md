# Shithead Card Game

A modern web-based implementation of the classic card game "Shithead" (also known as Palace or Karma) built with Laravel, Inertia.js, and React.

## About the Game

Shithead is a multiplayer card game where players aim to get rid of all their cards by playing them in ascending order. Each player starts with face-down cards, face-up cards, and hand cards. The game combines strategy and luck, making it both challenging and entertaining.

## Features

- Real-time multiplayer gameplay
- User authentication system
- Modern, responsive UI with Tailwind CSS
- Fast, SPA-like experience with Inertia.js
- Beautiful card animations and interactions
- Private and public game rooms
- Mobile-friendly design

## Tech Stack

- **Backend:** Laravel 10
- **Frontend:** React with Inertia.js
- **Styling:** Tailwind CSS
- **Real-time:** Laravel WebSockets (coming soon)
- **Database:** MySQL

## Getting Started

1. Clone the repository
```bash
git clone https://github.com/yourusername/shithead.git
cd shithead
```

2. Install dependencies
```bash
composer install
npm install
```

3. Set up environment
```bash
cp .env.example .env
php artisan key:generate
```

4. Configure database in `.env`

5. Run migrations and seed
```bash
php artisan migrate --seed
```

6. Start development servers
```bash
php artisan serve
npm run dev
```

## Development Status

This project is currently in active development. Check out our [Changelog](CHANGELOG.md) to see recent updates and upcoming features.

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgments

- Card game rules inspired by the traditional Shithead card game
- Built with Laravel's excellent ecosystem
- UI components powered by React and Tailwind CSS
