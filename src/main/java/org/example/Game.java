package org.example;

import org.example.entity.JPAUtil;
import org.example.entity.Score;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Game {
    static Scanner stdin = new Scanner(System.in);
    private Board gameBoard;
    private Player player;

    public void resetGame() {
        player = new Player();
    }

    public void resetGameWithCurrentStats(Player player) {
    }

    public void newGame() {

        //main game loop
        while(true){

            System.out.println("What would you like to do?");
            String answer = stdin.nextLine();

            if(!evaluateInput(answer)){
                System.out.println("No progress was saved, thanks for playing.");
                break;
            }  else if (player.getHealthValue() <= 0){
                System.out.println("You died.");
                break;
            }
        }
        // Save highscore
        System.out.println("Would you like to save your score to the leaderboard? (Y/N)");
        String choice = stdin.nextLine().toUpperCase();
        if(choice.equals("Y")) {
            System.out.println("Enter your name:");
            String name = stdin.nextLine();
            // Save
            JPAUtil.setScore(name, player.getGoldCarried());
            // TODO: Tell user what rank they were placed at
            showHighscores();
        }
        resetGameWithCurrentStats(player);
    }

    /**
     * Function to evaluate the user inputted string.
     * @param userInput
     * @return integer depending on user choice
     */
    public boolean evaluateInput(String userInput){
        if(userInput.equals("move east")){
            player.move(Direction.E);
            describeRoom();
        } else if (userInput.equals("move north")){
            player.move(Direction.N);
            describeRoom();
        } else if (userInput.equals("move west")){
            player.move(Direction.W);
            describeRoom();
        } else if (userInput.equals("move south")){
            player.move(Direction.S);
            describeRoom();
        } else if (userInput.equals("examine room")){
            describeRoom();
        } else if (userInput.equals("quit")){
            return false;
        } else if (userInput.equals("examine npc")) {
            //TODO: Loop over npc array for current room and get user to select which npc to describe
        } else if (userInput.equals("examine chest")) {
            //TODO: Loop over chest array for current room and get user to select which npc to describe
        }
        return true;
    }

    public void describeRoom(){
        // print out the room description
        System.out.println("=============================================================================================================================================");
        gameBoard.getRoomAtIndex(player.getCurrentRoom()).describeRoom(player.getCurrentRoom());
        System.out.println("=============================================================================================================================================");
    }

    public Game() {
        gameBoard = new Board();

    }

    public void help() {

        String userInput;

        System.out.println("Welcome to Devious MUD, the aim of the game is to collect as much gold as possible, and to escape with your life.\n");
        System.out.println("In order to play the game you will need to use these commands");
        System.out.println("To EXAMINE an object or a room, type 'examine' before the name of the object or room you wish to examine");
        System.out.println("To MOVE to a room, type 'move' before the direction (N,S,E,W) of the room you wish to move to");
        System.out.println("To ATTACK a NPC, type 'attack' before the name of the NPC you wish to attack");
        System.out.println("To GRAB an item, type 'grab' before the name of the item you wish to grab\n");

        System.out.println("You can test these commands here, and then type 'exit' when you're done");
        System.out.println("Object: box, NPC: man, Item: sword");

        do {
            userInput = stdin.nextLine();
            if (userInput == "examine box") {
                System.out.println("The box is damp and full of cobwebs, the outside is covered in mold and gives off a putrid stench");
            } else if (userInput == "examine man"){
                System.out.println("The man stands hunched over in the corner of the room, you can barely make out his face in the shadows");
            } else if (userInput == "examine sword"){
                System.out.println("This sword shines with the light from the room, despite the little amount there is.");
            } else if (userInput == "move N") {

            }
        } while (!userInput.equals("exit") );


    }

    /**
     * Highscore function
     */
    public void showHighscores() {
        ArrayList<Score> scores = JPAUtil.getScore();
        String leftAlignFormat = "| %-5d | %-15s | %-10s |%n";
        System.out.format("+--------------------------------------+%n");
        System.out.format("｜ Rank ｜ Player Name    ｜ Gold Score ｜%n");
        System.out.format("+--------------------------------------+%n");

        int counter = 1;
        for (Score score : scores) {
            System.out.format(leftAlignFormat, counter, score.getPlayerName(), score.getScore());
            counter++;
        }
        System.out.format("+-------+-----------------+------------+%n");
        System.out.println("Press enter to continue...");
        try
        {
            System.in.read();
        }
        catch(Exception ignored)
        {}
    }

    /**
     * Menu function
     */
    public void menu() {
        boolean inGame = true;
        asciiArt();
        while (inGame) {

            System.out.println("Please select an option: ");
            System.out.println("[1] Start new game");
            System.out.println("[2] Help");
            System.out.println("[3] Highscores");
            System.out.println("[4] Quit");

            int answer;

            while (true) {
                System.out.println("Please enter 1, 2, 3, 4: ");
                answer = Integer.parseInt(stdin.nextLine());
                if (verifyIntegers(answer, 1, 2, 3, 4)) {
                    break;
                }
            }

            if (answer == 1) {
                resetGame();
                newGame();
            } else if (answer == 2) {
                help();
            } else if (answer == 3) {
                showHighscores();
            } else if (answer == 4) {
                inGame = false;
            }
        }
    }

    /**
     * The following function verifies if a number is contained within a list of integers
     *
     * @return true or false
     */
    boolean verifyIntegers(int number, int... numbers) {
        for (int element : numbers) {
            if (number == element) {
                return true;
            }
        }
        return false;
    }


    public void asciiArt() {

        System.out.println("88888888ba,                             88                                          88b           d88  88        88  88888888ba,                            _,.-------.,_                      ");
        System.out.println("88      `\"8b                            \"\"                                          888b         d888  88        88  88      `\"8b                       ,;~'             '~;,                      ");
        System.out.println("88        `8b                                                                       88`8b       d8'88  88        88  88        `8b                    ,;                     ;,                                         ");
        System.out.println("88         88   ,adPPYba,  8b       d8  88   ,adPPYba,   88       88  ,adPPYba,     88 `8b     d8' 88  88        88  88         88                   ;                         ;                                             ");
        System.out.println("88         88  a8P_____88  `8b     d8'  88  a8\"     \"8a  88       88  I8[    \"\"     88  `8b   d8'  88  88        88  88         88                  ,'                         ',                                            ");
        System.out.println("88         8P  8PP\"\"\"\"\"\"\"   `8b   d8'   88  8b       d8  88       88   `\"Y8ba,      88   `8b d8'   88  88        88  88         8P                 ,;                           ;,                                            ");
        System.out.println("88      .a8P   \"8b,   ,aa    `8b,d8'    88  \"8a,   ,a8\"  \"8a,   ,a88  aa    ]8I     88    `888'    88  Y8a.    .a8P  88      .a8P                  ; ;      .           .      ; ;                                         ");
        System.out.println("88888888Y\"'     `\"Ybbd8\"'      \"8\"      88   `\"YbbdP\"'    `\"YbbdP'Y8  `\"YbbdP\"'     88     `8'     88   `\"Y8888Y\"'   88888888Y\"'                   | ;   ______       ______   ; |                                             ");
        System.out.println("                                                                                                                                                   |  `/~\\\"     ~\" . \"~     \"~\\' |                                                                          ");
        System.out.println("                                                                                                                                                   |  ~  ,-~~~^~, | ,~^~~~-,  ~  |                                                                            ");
        System.out.println("                                                                                                                                                    |   |        }:{        |   |                                                                       ");
        System.out.println("                                                                                                                                                    |   l       / | \\       !   |                                                                       ");
        System.out.println("                                                                                                                                                     .~  (__,.--\" .^. \"--.,__)  ~.                                                                   ");
        System.out.println("                                                                                                                                                    |     ---;' / | \\ `;---     |                                                                  ");
        System.out.println("                                                                                                                                                    \\__.      \\/^\\/ \\/^\\     .__/                                                                      ");
        System.out.println("                                                                                                                                                      V| \\                 / |V                                                                       ");
        System.out.println("                                                                                                                                                        | |T~\\___!___!___/~T| |                                                                    ");
        System.out.println("                                                                                                                                                        | |`IIII_I_I_I_IIII'| |                                                                 ");
        System.out.println("                                                                                                                                                        |  \\,III I I I III,/  |                                                                     ");
        System.out.println("                                                                                                                                                         \\   `~~~~~~~~~~'    /                                                                 ");
        System.out.println("                                                                                                                                                          \\   .       .     /                                                                   ");
        System.out.println("                                                                                                                                                             \\.    ^    . /                                                                ");
        System.out.println("                                                                                                                                                                ^~~~^~~~^                                                             ");


    }
}
