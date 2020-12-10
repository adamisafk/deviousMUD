package org.example;

import org.example.entity.*;

import javax.swing.*;
import java.sql.SQLOutput;
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
    // these are used for keeping track of combat stats
    private int selectedRoomNpc;
    private int selectedNPChealth;
    private int selectedRoomChest;

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
        System.out.println("\n\n\n\n\n\n");

        skull();

        System.out.println("You open your eyes, and struggle to remember where you were before...");
        System.out.println("[Press enter to continue]");
        try
        {
            System.in.read();
        }
        catch(Exception ignored)
        {}
        System.out.println("... looking around you, you realise you are in a room....");
        System.out.println("[Press enter to continue]");
        try
        {
            System.in.read();
        }
        catch(Exception ignored)
        {}
        System.out.println("... as you stand up, a long wooden staff falls from you body... you pick this up, it might be useful.");
        System.out.println("[Press enter to continue]");
        try
        {
            System.in.read();
        }
        catch(Exception ignored)
        {}
        System.out.println("Reaching into your pocket, you find a compass...");
        System.out.println("[Press enter to continue]");
        try
        {
            System.in.read();
        }
        catch(Exception ignored)
        {}
        System.out.println("Examining the compass and the room, you realise that there are four doors in this room, each \naligned with the four cardinal directions on the compass, north, south east and west.\n");
        System.out.println("[Press enter to continue]\n");
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
        switch(userInput) {
            case "move east":
                printDoor();
                player.move(Direction.E);
                currentRoomName();
                if(player.getCurrentRoom() == 5){
                    skull();
                    System.out.println("You have entered a boss room, kill all the NPCs to win the game...");
                    System.out.println("... type 'leave' when you have finished.");
                    System.out.println("You may want to explore the rest of the dungeon before leaving.");
                }
                break;
            case "move west":
                printDoor();
                player.move(Direction.W);
                currentRoomName();
                break;
            case "move north":
                printDoor();
                player.move(Direction.N);
                currentRoomName();
                break;
            case "move south":
                printDoor();
                player.move(Direction.S);
                currentRoomName();
                break;
            case "examine room":
                describeRoom();
                break;
            case "quit":
                return false;
            case "inventory":
                player.equipItem();
                break;
            case "attack":
                NPC npcToAttack = selectNPC();
                if (npcToAttack != null) {
                    return playerBattle(npcToAttack);
                }
                break;
            case "help":
                help();
                break;
            case "examine npc":
                describeNPC();
                break;
            case "examine chest":
                describeChests();
                break;
            case "talk":
                NPC npcToTalkTo = selectNPC();
                if (npcToTalkTo != null) {
                    if (talkToNPC(npcToTalkTo)) {
                        return playerBattle(npcToTalkTo);
                    }
                }
                break;
            case "take item":
                Chest chest = selectChest();
                if (chest != null) {
                    pickupItem(chest);
                    gameBoard.destroyChestAtIndex(player.getCurrentRoom(), selectedRoomChest - 1);
                }
                break;
            case "leave":
                // see if the player is in the boss room
                if(player.getCurrentRoom() == 5){
                    if(gameBoard.allNpcsInRoomAreDead(5)){
                        System.out.println("You managed to leave the dungeon....");
                        return false;
                    } else {
                        skull();
                        System.out.println("Defeat all the NPCs to win.");
                    }
                } else {
                    skull();
                    System.out.println("You cannot leave.");
                }
                break;
            default:
                System.out.println("I have no idea what you just said, are you a big dumb dumb or something?");
        }
        return true;
    }

    /**
     * Function to describe the room that the player is in.
     */
    public void describeRoom(){
        // print out the room description
        System.out.println("=============================================================================================================================================");
        gameBoard.getRoomAtIndex(player.getCurrentRoom()-1).describeRoom();
        System.out.println("=============================================================================================================================================");
    }

    public void describeNPC() {
        // print out the NPC description
        System.out.println("=============================================================================================================================================");
        gameBoard.getRoomAtIndex(player.getCurrentRoom()-1).describeNPCs(gameBoard.getRoomNpcIds() ,player.getCurrentRoom()-1);
        System.out.println("=============================================================================================================================================");
    }

    public void describeChests() {
        System.out.println("=============================================================================================================================================");
        gameBoard.getRoomAtIndex(player.getCurrentRoom()-1).describeChests(gameBoard.getRoomChestIds(), player.getCurrentRoom()-1);
        System.out.println("=============================================================================================================================================");
    }

    public void currentRoomName(){
        System.out.println("You examine the room around you... its name is....");
        System.out.println(gameBoard.getRoomAtIndex(player.getCurrentRoom()-1).getName());
    }

    public void pickupItem(NPC npc) {
        Item item = JPAUtil.getItemByNPCId(npc.getId());
        player.pickupItem(item);
    }
    public void pickupItem(Chest chest) {
        Item item = JPAUtil.getItemByChestId(chest.getId());
        player.pickupItem(item);
        System.out.printf("...as you grasp at the item contained within, the %s disintegrates \n", chest.getName());
    }

    /**
     * Helper function to select an NPC in the player's current room
     * @return the selected NPC
     */
    public NPC selectNPC(){
        while(true) {
            System.out.println("Which npc [q to quit]?");
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
     * Helper function to select an chest in the player's current room
     * @return the selected chest
     */
    public Chest selectChest(){
        while(true) {
            System.out.println("Which chest [q to quit]?");
            listChests();
            String answer = stdin.nextLine();
            if (isIntInRange(answer, gameBoard.getRoomChestIds().get(player.getCurrentRoom()-1).size()) > 0) {

                selectedRoomChest = isIntInRange(answer, gameBoard.getRoomChestIds().get(player.getCurrentRoom()-1).size());
                return JPAUtil.getChest(gameBoard.getRoomChestIds().get(player.getCurrentRoom()-1).get(selectedRoomChest - 1));
            } else if (answer.equals("q") || answer.equals("exit")) {
                return null;
            } else {
                System.out.println("Please select a valid chest");
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
     * Function to list the chests in the current room of the player
     */
    public void listChests(){

        for (int i = 0; i < gameBoard.getRoomChestIds().get(player.getCurrentRoom()-1).size(); i++) {
            String chestName = JPAUtil.getChest(gameBoard.getRoomChestIds().get(player.getCurrentRoom()-1).get(i)).getName();
            Item item = JPAUtil.getItemByChestId(gameBoard.getRoomChestIds().get(player.getCurrentRoom()-1).get(i));
            System.out.printf("[%d] ---  %s \n", i + 1, chestName + " (" + item.getName() + ")");
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
        while(player.getHealthValue() > 0 && gameBoard.getCorrespondingNPCHealth().get(player.getCurrentRoom()-1).get(selectedRoomNpc-1) > 0){
            if(isPlayerTurn) {
                for (int i = 0; i < 40; i++) {
                    System.out.println();
                }
                int damageDealt = player.hit(npc);
                selectedNPChealth = selectedNPChealth - damageDealt;
                gameBoard.setElementCorrespondingNPCHealth(selectedNPChealth, selectedRoomNpc - 1, player.getCurrentRoom());
                isPlayerTurn = false;
            } else{
                for (int i = 0; i < 40; i++) {
                    System.out.println();
                }
                player.recieveHit(npc);
                isPlayerTurn = true;
            }
            printSword();
            health("Your", player.getHealthValue());
            System.out.println("----------------------------------------------------------------------------------------------------------");
            health(npc.getName(), gameBoard.getCorrespondingNPCHealth().get(player.getCurrentRoom()-1).get(selectedRoomNpc-1));
            printSword2();
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
            // give the player the npc's gold
            int currentPlayerGold = player.getGoldCarried();
            int npcGold = npc.getGoldCarried();
            player.setGoldCarried(currentPlayerGold + npcGold);
            System.out.printf("You gained %d gold. Your gold is now: %d \n", npcGold, player.getGoldCarried());
            // option to give the player the NPC's item
            Item item = JPAUtil.getItemByNPCId(npc.getId());
            System.out.println(npc.getName() + " has dropped a " + item.getName() + ". " + item.getDescription() + "\n");
            System.out.println("Do you want to pickup and equip the " + item.getName() + "? (Y/N)");
            String choice = stdin.nextLine().toUpperCase();
            if(choice.equals("Y")) {
                pickupItem(npc);
            }
            return true; // player wins
        }
    }



    public void help() {
        for (int i = 0; i < 20; i++) {
            System.out.println();
        }
        System.out.println("  d888888b   d8b   db  .d8888.     ()     d8888b.  db    db   .o88b.     ()     d888888b   .d88b.   d8b   db  .d8888.                                                            ");
        System.out.println("     `88'    888o  88  88'  YP  o~~88~~o  88  `8D  88    88  d8P  Y8  o~~88~~o    `88'    .8P  Y8.  888o  88  88'  YP                                  ,-.                              ");
        System.out.println("      88     88V8o 88  `8bo.       ||     88oobY'  88    88  8P          ||        88     88    88  88V8o 88  `8bo                ___,---.__          /'|`\\          __,---,___         ");
        System.out.println("      88     88 V8o88    `Y8b.     ||     88`8b    88    88  8b          ||        88     88    88  88 V8o88    `Y8b.          ,-'    \\`    `-.____,-'  |  `-.____,-'     /    `-.                  ");
        System.out.println("     .88.    88  V888  db   8D     ||     88 `88.  88b  d88  Y8b  d8     ||       .88.    `8b  d8'  88  V888  db   8D        ,'        |           ~'\\     /`~           |        `.                 ");
        System.out.println("   Y888888P  VP   V8P  `8888Y'     \\/     88   YD  ~Y8888P'   `Y88P'     \\/     Y888888P   `Y88P'   VP   V8P  `8888Y'        /      ___//              `. ,'          ,  , \\___      \\                ");
        System.out.println("                                                                                                                            |    ,-'   `-.__   _         |        ,    __,-'   `-.    |                 ");
        System.out.println("                                                                                                                            |   /          /\\_  `   .    |    ,      _ /          \\   |                                                             ");
        System.out.println("+-------------------------------------------------------------------------------------------------------------------------+ \\  |           \\ \\`-.___ \\   |   / ___,-'/ /           |  /                                                              ");
        System.out.println("|X| Welcome to Devious MUD, the aim of the game is to collect as much gold as possible, and to escape with your life.   |X|  \\  \\           | `._   `\\\\  |  //'   _,' |           /  /                                                           ");
        System.out.println("|X|                                                                                                                     |X|    `-.\\         /'  _ `---'' , . ``---' _ `\\         /,-'                                                                    ");
        System.out.println("|X| In order to play the game you will need to use these commands                                                       |X|                /      \\  ,='/ \\`=.    /     \\                                                                        ");
        System.out.println("|X| To EXAMINE a Chest, NPC or room, type 'examine' before Chest, NPC or Room  e.g. 'examine room' or 'examine npc'     |X|                |__   /|\\_,--.,-.--,--._/|\\ __|          ");
        System.out.println("|X| To MOVE room, type 'move' before north, south, east or west e.g. 'move east' to move the east room                  |X|               /  `./  \\\\`\\ |  |  | /,//' \\,'  \\       ");
        System.out.println("|X| To ATTACK an NPC, type 'attack', you'll then be asked which npc you would like to attack e.g. [1] goblin [2] witch  |X|              /   /     ||--+--|--+-/-|     \\   \\         ");
        System.out.println("|X| To TAKE an item, type 'take item' to be shown list of chests you can take items from, you will be shown [1] axe etc |X|             |   |     /'\\_\\_\\ | /_/_/`\\     |  |       ");
        System.out.println("|X| To VIEW INVENTORY/Equip different weapon, type 'inventory' this will display your gold which is your score and also |X|              \\   \\__, \\_     `~'     _/ .__/   /        ");
        System.out.println("|X| show weapons you can equip                                                                                          |X|                '-._,-'   `-._______,-'   `-._,-'                   ");
        System.out.println("|X| For HELP and to view this instructions screen again type 'help'                                                     |X|                          ");
        System.out.println("|X| To TALK to an NPC, type 'talk' you'll then be asked which npc to talk to, be weary, not everyone is friendly!       |X|");
        System.out.println("|X| To LEAVE the dungeon after completing the boss room by killing all NPCs type 'leave'                                |X|");
        System.out.println("|X| To QUIT the game type 'quit'                                                                                        |X|");
        System.out.println("|X| If you would like to exit EXAMINING a chest or TALKING or ATTACKING an NPC, type 'q'                                |X|");
        System.out.println("|X| To TAKE an item from a chest, type 'take item', you'll then be asked which chest to take from.                      |X|");
        System.out.println("|X|                                                                                                                     |X|");
        System.out.println("|X| You must navigate to the final room and kill all the NPCs in the boss room to win, good luck!                       |X|");
        System.out.println("+-------------------------------------------------------------------------------------------------------------------------+");
        System.out.println("Press enter to continue...");
        try
        {
            System.in.read();
        }
        catch(Exception ignored)
        {}
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

        while (inGame) {

            asciiArt();

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

    public boolean talkToNPC(NPC npc) {

        while (true) {
            System.out.println("What would you like to say to the character?");
            System.out.println("[1] What are you?");
            System.out.println("[2] What's in this room?");
            System.out.println("[3] I have nothing more to say");
            System.out.println("[4] Attack");
            int selectAnOption;
            String answer = stdin.nextLine();
            int integerAnswer = isIntInRange(answer, 4);
            if (integerAnswer > 0) {
                return npc.converseWithAndAttack(gameBoard.getRoomAtIndex(player.getCurrentRoom()-1), integerAnswer);
            } else if (answer.equals("q")) {
                return false;
            } else {
                System.out.println("Please enter a valid option or type q to quit");
            }
        }
    }


    public void asciiArt() {
        for (int i = 0; i < 20; i++) {
            System.out.println();
        }
        System.out.println(" ");
        System.out.println("88888888ba,                             88                                          88b           d88  88        88  88888888ba,                            _,.-------.,_                      ");
        System.out.println("88      `\"8b                            \"\"                                          888b         d888  88        88  88      `\"8b                       ,;~'             '~;,                      ");
        System.out.println("88        `8b                                                                       88`8b       d8'88  88        88  88        `8b                    ,;                     ;,                                         ");
        System.out.println("88         88   ,adPPYba,  8b       d8  88   ,adPPYba,   88       88  ,adPPYba,     88 `8b     d8' 88  88        88  88         88                   ;                         ;                                             ");
        System.out.println("88         88  a8P_____88  `8b     d8'  88  a8\"     \"8a  88       88  I8[    \"\"     88  `8b   d8'  88  88        88  88         88                  ,'                         ',                                            ");
        System.out.println("88         8P  8PP\"\"\"\"\"\"\"   `8b   d8'   88  8b       d8  88       88   `\"Y8ba,      88   `8b d8'   88  88        88  88         8P                 ,;                           ;,                                            ");
        System.out.println("88      .a8P   \"8b,   ,aa    `8b,d8'    88  \"8a,   ,a8\"  \"8a,   ,a88  aa    ]8I     88    `888'    88  Y8a.    .a8P  88      .a8P                  ; ;      .           .      ; ;                                         ");
        System.out.println("88888888Y\"'     `\"Ybbd8\"'      \"8\"      88   `\"YbbdP\"'    `\"YbbdP'Y8  `\"YbbdP\"'     88     `8'     88   `\"Y8888Y\"'   88888888Y\"'                   | ;   ______       ______   ; |                                             ");
        System.out.println("                                                                                                                                                   |  `/~\\\"     ~\" . \"~     \"~\\' |                                                                          ");
        System.out.println("   ________________________________                                                                                                                |  ~  ,-~~~^~, | ,~^~~~-,  ~  |                                                                            ");
        System.out.println("   |X| Please select an option: |X|                                                                                                                 |   |        }:{        |     |                                                                       ");
        System.out.println("   |X|    [1] Start new game    |X|    >>------>                                                                                                   |   l       / | \\       !     |                                                                       ");
        System.out.println("   |X|    [2] Help              |X|    >>------>          ,_._._._._._._._._|__________________________________________________________,           .~  (__,.--\" .^. \"--.,__)   ~.                                                                   ");
        System.out.println("   |X|    [3] Highscores        |X|    >>------>          |_|_|_|_|_|_|_|_|_|_________________________________________________________/            |     ---;' / | \\ `;---       |                                                                  ");
        System.out.println("   |X|    [4] Quit              |X|    >>------>                            !                                                                       \\__.      \\/^\\/ \\/^\\     .__/                                                                      ");
        System.out.println("   |X|                          |X|                                                                                                                   V| \\                 / |V                                                                       ");
        System.out.println("   |X|--------------------------|X|                                                                                                                   | |T~\\___!___!___/~T| |                                                                    ");
        System.out.println("   |X|                          |X|                                                                                                                   | |`IIII_I_I_I_IIII'| |                                                                 ");
        System.out.println("   |X|--------------------------|X|                                                                                                                   |  \\,III I I I III,/  |                                                                     ");
        System.out.println("                                                                                                                                                       \\    `~~~~~~~~~~'    /                                                                 ");
        System.out.println("                                                                                                                                                         \\   .       .     /                                                                   ");
        System.out.println("                                                                                                                                                           \\.    ^     . /                                                                ");
        System.out.println("                                                                                                                                                              ^~~~^~~~^                                                             ");


    }

    public void printSword(){

        System.out.println("                                                                                                                                                           ");
        System.out.println("                                                                                                                                     ");
        System.out.println("       ,_._._._._._._._._|__________________________________________________________,                                                               ");
        System.out.println("       |_|_|_|_|_|_|_|_|_|_________________________________________________________/                                                                    ");
        System.out.println("                         !                                                                                                                                  ");
        System.out.println("                                                                                                                                                   ");
    }

    public void printSword2(){

        System.out.println("                                                                                                                                                           ");
        System.out.println("                                                                                                                                     ");
        System.out.println("        ,________________________________________________________|_._._._._._._._._,                                                               ");
        System.out.println("       /_________________________________________________________|_|_|_|_|_|_|_|_|_|                                                                    ");
        System.out.println("                                                                 !                                                                       ");
        System.out.println("                                                                                                                                                   ");
    }

    public void printDoor(){
        for (int i = 0; i < 40; i++) {
            System.out.println();
        }
        System.out.println("========================================================================================================================");
        System.out.println("                                               ______                                       ");
        System.out.println("                                            ,-' ;  ! `-.                                       ");
        System.out.println("                                           } :  !  :  . {                                    ");
        System.out.println("                                          |_ ;   __:  ;  |                                    ");
        System.out.println("                                          )| .  :)(.  !  |                                    ");
        System.out.println("                                          |\"    (##)  _  |                                    ");
        System.out.println("                                          |  :  ;`'  (_) (                                    ");
        System.out.println("                                          |  :  :  .     |                                    ");
        System.out.println("                                          )_ !  ,  ;  ;  |                                    ");
        System.out.println("                                          || .  .  :  :  |                                    ");
        System.out.println("                                          |\" .  |  :  .  |                                    ");
        System.out.println("                                          |mt-2_;----.___|                                    ");
        System.out.println("========================================================================================================================");
    }

    public void health(String name, int healthValue){
        System.out.printf("                                   ,d88b.d88b,\n");
        System.out.printf("                                   88888888888 \n");
        System.out.printf("                                   `Y8888888Y'    %s  health : %d\n",name,healthValue);
        System.out.printf("                                     `Y888Y'  \n");
        System.out.printf("                                       `Y'    \n");
    }

    public void skull(){
        System.out.println(" ");
        System.out.println("                                                                         _,.-------.,_                      ");
        System.out.println("                                                                     ,;~'             '~;,                      ");
        System.out.println("                                                                   ,;                     ;,                                         ");
        System.out.println("                                                                  ;                         ;                                             ");
        System.out.println("                                                                 ,'                         ',                                            ");
        System.out.println("                                                                ,;                           ;,                                            ");
        System.out.println("                                                                ; ;      .           .      ; ;                                         ");
        System.out.println("                                                                | ;   ______       ______   ; |                                             ");
        System.out.println("                                                                |  `/~\\\"     ~\" . \"~     \"~\\' |                                                                          ");
        System.out.println("                                                                |  ~  ,-~~~^~, | ,~^~~~-,  ~  |                                                                            ");
        System.out.println("                                                                |   |        }:{        |     |                                                                       ");
        System.out.println("                                                                |   l       / | \\       !     |                                                                       ");
        System.out.println("                                                                .~  (__,.--\" .^. \"--.,__)   ~.                                                                   ");
        System.out.println("                                                                |     ---;' / | \\ `;---       |                                                                  ");
        System.out.println("                                                                 \\__.      \\/^\\/ \\/^\\     .__/                                                                      ");
        System.out.println("                                                                   V| \\                 / |V                                                                       ");
        System.out.println("                                                                    | |T~\\___!___!___/~T| |                                                                    ");
        System.out.println("                                                                    | |`IIII_I_I_I_IIII'| |                                                                 ");
        System.out.println("                                                                    |  \\,III I I I III,/  |                                                                     ");
        System.out.println("                                                                    \\    `~~~~~~~~~~'    /                                                                 ");
        System.out.println("                                                                      \\   .       .     /                                                                   ");
        System.out.println("                                                                        \\.    ^     . /                                                                ");
        System.out.println("                                                                           ^~~~^~~~^                                                             ");

    }
}
