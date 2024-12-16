- Players have placed four same cards in a row
function checkRow(deck){
    if(pile.length < 4)
        return false;

    for (let i = 0; i < 4; i++){
        if (pile[pile.length - i].value !== deck[i+1].value)
            return false;
    }
    return true;
}

- Player played a discard card
if(playerCard.value === 110)
{
    discardPile();
}

- Player played a refresh card
if(playerCard.value === 106){
    currentValue = 0;
}

- Played a card and has met criteria
if (playedCard.value >= tableCard.value)
    tableCard = playedCard;
    currentValue = playedCard.value;

- Player has no card to put on the table
if(player.hand.filter((card) => card.value < game.tableCard.value).length <= 0)

- Card structure to follow
struct Card{
    public value = null;
    public suits = "";
}

- Player structure to follow
struct Player{
    public name = "";

    private hand = [];
    private id = null;
}

- Game structure to follow
struct Game{
    public playingPile = [];
    public tableCard = null;
    public playerTurn = 0;
    public players = [];
}


