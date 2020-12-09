package org.example;

import org.example.entity.JPAUtil;
import org.example.entity.NPC;
import org.example.entity.Score;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

/**
 * Class to represent the game.
 */
public class Game {
    // utility attributes
    static Scanner stdin = new Scanner(System.in);
    // other classes
    private Board gameBoard;
    private Player player;
    // these are used for combat
    private int selectedRoomNpc;
    private int selectedNPChealth;

    public Game() {
        gameBoard = new Board();
        selectedRoomNpc = 0;
    }

    /**
     * Function to reset the game, resetting player stats too
     */
    public void resetGame() {
        player = new Player();
        gameBoard = new Board();
    }

    /**
     * Function to reset the game but maintain player's stats/ equipment
     * @param player
     */
    public void resetGameWithCurrentStats(Player player) {
        gameBoard = new Board();
        this.player = player;
    }

    /**
     * The main game loop
     */
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
        } else if (userInput.equals("inventory")){
            player.equipItem();
        } else if (userInput.equals("attack")){
            NPC npcToAttack = selectNPC();
            if (npcToAttack != null) {
                return playerBattle(npcToAttack);
            }
        } else if (userInput.equals("quit")){
            return false;
        }
        return true;
    }

    /**
     * Function to describe the room that the player is in.
     */
    public void describeRoom(){
        // print out the room description
        System.out.println();
        System.out.println("The room you are in:");
        gameBoard.getRoomAtIndex(player.getCurrentRoom()-1).describeRoom(gameBoard.getRoomNpcIds(), gameBoard.getRoomChestIds(), player.getCurrentRoom()-1);
        System.out.println();
    }

    /**
     * Helper function to select an NPC in the player's current room
     * @return the selected NPC
     */
    public NPC selectNPC(){
        while(true) {
            System.out.println("Which npc?");
            listNPCs();
            int selectedNPC;
            String answer = stdin.nextLine();
            if (isIntInRange(answer, gameBoard.getRoomNpcIds().get(player.getCurrentRoom()-1).size()) > 0) {

                selectedNPC = isIntInRange(answer, gameBoard.getRoomNpcIds().get(player.getCurrentRoom()-1).size());
                selectedNPChealth = gameBoard.getCorrespondingNPCHealth().get(player.getCurrentRoom()-1).get(selectedNPC - 1);
                if(selectedNPChealth > 0) {
                    selectedRoomNpc = selectedNPC;
                    return JPAUtil.getNPC(gameBoard.getRoomNpcIds().get(player.getCurrentRoom()-1).get(selectedNPC - 1));
                } else{
                    System.out.println("Selected NPC is dead.[q/exit to quit]");
                }
            } else if (answer.equals("q") || answer.equals("exit")) {
                return null;
            } else {
                System.out.println("Please select a valid npc");
            }
        }


    }

    /**
     * Function to list the npcs in the current room of the player
     */
    public void listNPCs(){

        for (int i = 0; i < gameBoard.getRoomNpcIds().get(player.getCurrentRoom()-1).size(); i++) {
            int npcHealth = gameBoard.getCorrespondingNPCHealth().get(player.getCurrentRoom()-1).get(i);
            String npcName = JPAUtil.getNPC(gameBoard.getRoomNpcIds().get(player.getCurrentRoom()-1).get(i)).getName();
            if(npcHealth > 0) {
                System.out.printf("[%d] ---  %s \n", i + 1, npcName);
            } else {
                System.out.printf("[%d] ---  %s [DEAD] \n", i + 1, npcName);
            }
        }
    }

    /**
     * Combat function
     * @param npc
     * @return true or false depending on whether the player wins or dies.
     */
    public boolean playerBattle(NPC npc){
        System.out.println("In combat...");
        boolean isPlayerTurn = true;
        while(player.getHealthValue() > 0 && npc.getHealthValue() > 0){
            if(isPlayerTurn) {
                int damageDealt = player.hit(npc);
                selectedNPChealth = selectedNPChealth - damageDealt;
                gameBoard.setElementCorrespondingNPCHealth(selectedNPChealth, selectedRoomNpc - 1, player.getCurrentRoom());
                isPlayerTurn = false;
            } else{
                player.recieveHit(npc);
                isPlayerTurn = true;
            }
            System.out.println("***********************");
            System.out.printf("Your health: %d \n", player.getHealthValue());
            System.out.printf("%s health: %d \n", npc.getName(), gameBoard.getCorrespondingNPCHealth().get(player.getCurrentRoom()-1).get(selectedRoomNpc-1));
            System.out.println("***********************");
            System.out.println("Press enter to continue...");
            try
            {
                System.in.read();
            }
            catch(Exception ignored)
            {}
        }

        if(player.getHealthValue() <= 0){
            return false; // player lost
        } else {
            return true; // player wins
        }
    }



    public void help() {
        System.out.println("Welcome to Devious MUD, the aim of the game is to collect as much gold as possible, and to escape with your life.\n");
        System.out.println("In order to play the game you will need to use these commands");
        System.out.println("To EXAMINE an object or a room, type 'examine' before the name of the object or room you wish to examine");
        System.out.println("To MOVE to a room, type 'move' before the direction (north, south, east, west) of the room you wish to move to");
        System.out.println("To ATTACK a NPC, type 'attack', you'll then be asked which npc you would like to attack");
        System.out.println("To GRAB an item, type 'grab' before the name of the item you wish to grab");
        System.out.println("To VIEW INVENTORY, type 'inventory'");
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

            String input;

            while (true) {
                System.out.println("Please enter 1, 2, 3, 4: ");
                input = stdin.nextLine();
                if (isIntInRange(input, 4)>0) {
                    break;
                }
            }
            int answer = isIntInRange(input, 4);

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

    /**
     * Functiopn returns the number if it is a valid inventory slot. otherwise returns negative number
     * @param answer
     * @param numberOfItemsInInventory
     * @return number if it is positive and valid inventory slot, otherwise negative number
     */
    public int isIntInRange(String answer, int numberOfItemsInInventory){
        int result;
        try{
            result = Integer.parseInt(answer);
            if(result > 0 && result <= numberOfItemsInInventory){
                return result;
            } else{
                return -1;
            }
        } catch (Exception e){
            return -1;
        }
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
