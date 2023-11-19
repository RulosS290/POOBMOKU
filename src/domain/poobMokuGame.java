package domain;

public class poobMokuGame {
    private int players;
    private player player1;
    private player player2;

    public poobMokuGame(String Player1, String Player2){
        player1 = new player(Player1);
        player2 = new player(Player2);

    }
}
