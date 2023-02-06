package com.koar.projectbeta;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
//import static org.junit.Assert.*;


// Working with TDD , I will build an XO game using cmd line
// TODO : tasks
// 1. create a board
// 2. create two players
// 3. create start a game
// 4. check which turn is
// 5. calculate winner after each move
// 6. end game



@SpringBootTest
class XoGameTests {


    @Test
    void create_board_and_board_is_not_null() {
        System.out.println("create_board_and_board_is_not_null");
        Board board = new Board( );
        assertNotNull(board);
    }

    @Test
    void create_player_and_is_not_null_and_name_is_valid() {
        System.out.println("create_player_and_is_not_null_and_name_is_valid");
        Player player = new Player("koar", "X");
        String name = "koar";
        String symbol = "X";
        assertNotNull(player);
        assertEquals(player.getName( ),name);
        assertEquals( player.getSymbol( ),symbol);

    }


    @Test
    void check_if_board_is_3_x_3() {
        System.out.println("check_if_board_is_3_x_3");
        Board board = new Board( );
        assert board.getBoard( ).length == 3;
        assert board.getBoard( )[0].length == 3;
        assert board.getBoard( )[1].length == 3;
        assert board.getBoard( )[2].length == 3;
    }

    @Test
    void check_if_board_is_empty() {
        System.out.println("check_if_board_is_empty");
        Board board = new Board( );
        assert board != null;
        for (int[] ints : board.getBoard( )) {
            for (int anInt : ints) {
                assertEquals(anInt,0);
            }
        }
    }

    @Test
    void displayBoard_() {
        System.out.println("displayBoard_");
        Board board = new Board( );
        board.displayBoard( );
    }

    @Test
    void start_game_game_is_not_null_and_players() {
        System.out.println("start_game_game_is_not_null_and_players");
        Game game = new Game(new Player("koar", "X"), new Player("player2", "O"));
        Player player1 = new Player("koar", "X");
        Player player2 = new Player("player2", "O");
        game.getBoard( ).displayBoard( );
        assert game.getPlayer1( ) != null;
        assert game.getPlayer2( ) != null;
        assert game.getPlayer1( ).getName( ).equals(player1.getName( ));
        assert game.getPlayer2( ).getSymbol( ).equals(player2.getSymbol( ));

    }

    @Test
    void check_if_board_updates_when_playing_and_board_equals_squares() {
        System.out.println("check_if_board_updates_when_playing_and_board_equals_squares");
        Game game = new Game(new Player("koar", "X"), new Player("player2", "O"));
        Player player1 = new Player("koar", "X");
        Player player2 = new Player("player2", "O");
        int[][] squares = {
            {1,2,1},
            {2,0,0},
            {0,0,0}
        };
        game.startGame( );
        game.move(0, 0);
        game.move(0, 1);
        game.move(0, 2);
        game.move(1, 0);


        assertEquals(Arrays.deepToString(squares),Arrays.deepToString(game.getBoard( ).getBoard( )));


    }

    @Test
    void check_if_calculate_winner_works_and_returns_1() {
        System.out.println("check_if_calculate_winner_works_and_returns_1");
        Game game = new Game(new Player("koar", "X"), new Player("player2", "O"));
        game.startGame( );
        int[][] squares = game.getBoard( ).getBoard( );
        squares[0][0] = 1;
        squares[0][1] = 1;
        squares[0][2] = 1;
        game.getBoard( ).setBoard(squares);
        game.getBoard( ).displayBoard( );
        assert game.calculateWinner( ) == 1;

    }

    @Test
    void check_if_calculate_winner_works_and_returns_2() {
        System.out.println("check_if_calculate_winner_works_and_returns_2");
        Game game = new Game(new Player("koar", "X"), new Player("player2", "O"));
        game.startGame( );
        int[][] squares = game.getBoard( ).getBoard( );
        squares[0][0] = 2;
        squares[0][1] = 2;
        squares[0][2] = 2;
        game.getBoard( ).setBoard(squares);
        game.getBoard( ).displayBoard( );
        assert game.calculateWinner( ) == 2;

    }

    @Test
    void check_if_check_turn_works_and_returns_player1_then_player2() {
        System.out.println("check_if_check_turn_works_and_returns_player1_then_player2");
        Player player1 = new Player("koar", "X");
        Player player2 = new Player("player2", "O");
        Game game = new Game(player1, player2);

        game.startGame( );
        assert game.getCurrentPlayer( ) == player1;
        game.checkTurn( );
        assert game.getCurrentPlayer( ) == player2;

    }

    @Test
    void check_if_end_game_meessage_fails_if_winner_is_null() {
        System.out.println("check_if_end_game_meessage_works");
        Player player1 = new Player("koar", "X");
        Player player2 = new Player("player2", "O");
        Game game = new Game(player1, player2);

        game.startGame( );
        game.setWon(true);

        Throwable exception = assertThrows(NullPointerException.class, game::endGame);
        assertEquals("Cannot invoke \"com.koar.projectbeta.Player.getName()\" because \"this.winner\" is null", exception.getMessage( ));


    }

    @Test
    void check_winner_method_winner_is_player_1_and_game_is_over_and_won() {
        System.out.println("check_winner_method_winner_is_player_1_and_game_is_over_and_won");
        Player player1 = new Player("koar", "X");
        Player player2 = new Player("player2", "O");
        Game game = new Game(player1, player2);
        game.startGame( );
        int[][] squares = game.getBoard( ).getBoard( );
        squares[0][0] = 1;
        squares[0][1] = 1;
        squares[0][2] = 1;
        game.getBoard( ).setBoard(squares);
        game.getBoard( ).displayBoard( );
        game.checkWinner();
        assertEquals(player1,game.getWinner());
        assertTrue(game.isOver( ));
        assertTrue(game.isWon());
        assertFalse(game.isDraw());

    }

    @Test
    void check_winner_method_no_winner_game_over_and_draw() {
        System.out.println("check_winner_method_no_winner_game_over_and_draw");
        Player player1 = new Player("koar", "X");
        Player player2 = new Player("player2", "O");
        Game game = new Game(player1, player2);
        game.startGame( );
        int[][] squares = game.getBoard( ).getBoard( );
        squares[0][0] = 1;
        squares[0][1] = 1;
        squares[0][2] = 1;
        game.getBoard( ).setBoard(squares);
        game.getBoard( ).displayBoard( );
        game.checkWinner();
        assertEquals(player1,game.getWinner());
        assertTrue(game.isOver( ));
        assertTrue(game.isWon());
        assertFalse(game.isDraw());

    }

    @Test
    void check_if_square_is_filled_same_board_is_retained() {
        System.out.println( "check_if_square_is_filled_same_board_is_retained");

        Player player1 = new Player("koar", "X");
        Player player2 = new Player("player2", "O");
        Game game = new Game(player1, player2);
        game.startGame( );

        int[][] squares = {
                {1,2,1},
                {2,0,0},
                {0,0,0}
        };
        game.startGame( );
        game.move(0, 0);
        game.move(0, 1);
        game.move(0, 2);
        game.move(1, 0);
        game.move(1, 0);

        assertEquals(Arrays.deepToString(squares),Arrays.deepToString(game.getBoard( ).getBoard( )));

    }


    @Test
    void game_entire_test_winner_is_player_2() {

        System.out.println( "game_entire_test_winner_is_player_2");

        Player player1 = new Player("koar", "X");
        Player player2 = new Player("player2", "O");
        Game game = new Game(player1, player2);
        game.startGame( );

        game.move(0, 0);
        game.move(0, 1);
        game.move(0, 2);
        game.move(1, 0);
        game.move(2, 0);
        game.move(1, 1);
        game.move(2, 2);
        game.move(2, 1);
        game.move(1, 2);

        assertEquals(game.getWinner(),player2);
        assertTrue(game.isWon());
        assertFalse(game.isDraw());
        assertTrue(game.isOver());



    }


}



class Board {
    // create a matrix 3x3
    // create a method to print the board
    // create a method to check if the board is full
    // create a method to check if the board is empty

    private int[][] board;

    public Board() {
        this.board = new int[3][3];
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public boolean isFull() {
        for (int[] ints : this.board) {
            for (int anInt : ints) {
                if ( anInt == 0 ) return false;
            }
        }
        return true;
    }

    public void displayBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if ( this.board[i][j] == 0 ) System.out.print("[ ]");
                if ( this.board[i][j] == 1 ) System.out.print("[X]");
                if ( this.board[i][j] == 2 ) System.out.print("[O]");
//                if ( j < 2 ) System.out.print(" | ");
            }
            System.out.println( );
//            if ( i < 2 ) System.out.println("---------");
        }
        System.out.println( );
    }

}

class Player {
    // create a name
    // create a symbol


    private String name;
    private String symbol;

    public Player(String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}

class Game {
    // create a method to start the game
    // create a method to check which turn is
    // create a method to check if the game is over
    // create a method to check if the game is draw
    // create a method to check if the game is won
    // create a method to end the game
    // create a method to check if the player is winner

    private Board board;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Player winner;
    private boolean isOver;
    private boolean isDraw;
    private boolean isWon;

    public Game(Player player1, Player player2) {
        this.board = new Board( );
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;
        this.winner = null;
        this.isOver = false;
        this.isDraw = false;
        this.isWon = false;
    }

    public Board getBoard() {
        return board;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Player getWinner() {
        return winner;
    }

    public boolean isOver() {
        return isOver;
    }

    public boolean isDraw() {
        return isDraw;
    }

    public boolean isWon() {
        return isWon;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public void setOver(boolean over) {
        isOver = over;
    }

    public void setDraw(boolean draw) {
        isDraw = draw;
    }

    public void setWon(boolean won) {
        isWon = won;
    }

    public void startGame() {
        System.out.println("Welcome to XO game");
        System.out.println("Player 1 is " + this.player1.getName( ) + " and his symbol is " + this.player1.getSymbol( ));
        System.out.println("Player 2 is " + this.player2.getName( ) + " and his symbol is " + this.player2.getSymbol( ));
        System.out.println("Let's start the game");
        this.board.displayBoard( );
    }


    public void checkTurn() {
        if ( this.currentPlayer == this.player1 ) this.currentPlayer = this.player2;
        else this.currentPlayer = this.player1;
    }



    public int calculateWinner() {

        int[][] board = this.board.getBoard( );
        int[][] ref =
                {
                        {1, 2, 3},
                        {4, 5, 6},
                        {7, 8, 9},
                        {1, 4, 7},
                        {2, 5, 8},
                        {3, 6, 9},
                        {1, 5, 9},
                        {3, 5, 7}
                };

        for (int i = 0; i < ref.length; i++) {

            int a = ref[i][0] - 1;
            int b = ref[i][1] - 1;
            int c = ref[i][2] - 1;

            if ( board[a / 3][a % 3] == board[b / 3][b % 3] && board[b / 3][b % 3] == board[c / 3][c % 3] ) {
                return board[a / 3][a % 3];
            }

        }

        return 0;

    }

    public void move(int x, int y) {

        if ( this.isOver ) {
            this.endGame( );
            return;
        }

        if(isFilled(x,y))
        {
            return;
        }

        int[][] squares = this.board.getBoard( );
        if ( this.currentPlayer == this.player1 ) squares[x][y] = 1;
        else squares[x][y] = 2;

        board.setBoard(squares);
        this.board.displayBoard( );
        this.checkWinner( );

        //update current player
        this.checkTurn( );
    }

    public void checkWinner() {
        int winner = this.calculateWinner( );
        if ( winner == 0 ) {
            if ( this.board.isFull( ) ) {
                this.isDraw = true;
                this.isOver = true;
            }
        } else {
            this.isWon = true;
            this.isOver = true;
            if ( winner == 1 ) this.winner = this.player1;
            else if ( winner == 2 ) this.winner = this.player2;
        }

    }

    public void endGame() {
        if ( this.isWon ) System.out.println("The winner is " + this.winner.getName( ));
        else System.out.println("The game is draw");
    }

    public boolean isFilled(int x, int y)
    {
        return this.board.getBoard()[x][y]!=0;
    }


}









