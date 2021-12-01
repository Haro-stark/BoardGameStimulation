package com.company;

import java.util.*;

public class Game implements Trap, Bonus {
    //    int[] trapIndex;
//    int[] bonusIndex;
    HashMap<String, List<Integer>> specialIndexHashMap;
    Player[] players;

    public Game() {
//        trapIndex = new int[GlobalVariables.trapIndex];
//        bonusIndex = new int[GlobalVariables.bonusIndex];
        specialIndexHashMap = new HashMap<String, List<Integer>>();
        players = new Player[askNumberOfPlayers()];
    }

    public void generateGame() {
        setSpecialIndex();
        String playerName;
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < players.length; i++) {
            System.out.print("Name of player " + i + ": ");
            playerName = sc.next();
            players[i] = new Player(i, playerName);
        }

    }

    public void playGame() {
        int playerNumber = 0, stepJump = 0;
        boolean playerWon = false;

        while (!playerWon) {
            // keeps the players turn in circle
            if (playerNumber >= players.length) playerNumber = 0;

            System.out.println("\n\n" + players[playerNumber].name + "'s Turn:");

            if (!players[playerNumber].skipRound) {
                // dice has been rolled
                stepJump = rollDice();

                players[playerNumber].movePlayer(stepJump);
                System.out.println("Player index => " + players[playerNumber].currentIndex);

                if (players[playerNumber].currentIndex > 30) {
                    playerWon = true;
                    System.out.println("\n\n\t\t"+players[playerNumber].name + " has won the game!!");
                } else {

                    // if the skipRound is not false only then the player can take the turn
                    if (numberIsAnySpecialNumber(specialIndexHashMap, players[playerNumber].currentIndex)) {
                        System.out.println("The player has landed on special field");


                            String specialIndexKey = getSpecialIndexKey(specialIndexHashMap, players[playerNumber].currentIndex);

                            if (specialIndexKey != null && specialIndexKey.equalsIgnoreCase("trap")) {
                                // joker should be false only then a trap can be activated for that person
                                if (!players[playerNumber].joker) {
                                    switch (randomFieldSelection()) {
                                        case 1:
                                            movePlayerTwoFieldsBack(players[playerNumber]);
                                            break;
                                        case 2:
                                            moveOtherPLayersTwoFieldsForward(players, players[playerNumber].id);
                                            break;
                                        case 3:
                                            skipTheRound(players[playerNumber]);
                                            break;
                                        default:
                                            return;
                                    }
                                } else {
                                    System.out.println("The player has utilized joker on the following trap");
                                    // the joker bonus has been used. Now set it back to false
                                    players[playerNumber].joker = false;
                                }
                            } else if (specialIndexKey != null && specialIndexKey.equalsIgnoreCase("bonus")) {
                                switch (randomFieldSelection()) {
                                    case 1:
                                        movePlayerTwoFieldsForward(players[playerNumber]);
                                        break;
                                    case 2:
                                        moveOtherPLayersTwoFieldsBackward(players, players[playerNumber].id);
                                        break;
                                    case 3:
                                        utilizeJoker(players[playerNumber]);
                                        break;
                                    default:
                                        return;
                                }
                            } else if (specialIndexKey == null) {
                                System.out.println("Game crash error. No key could be found by the specified value... Ending game");
                            }


                    } else {
                        // condition when player lads on a standard field
                        System.out.println("The player has landed on standard normal field");
                    }
                }
            } else {
                System.out.println(players[playerNumber].name + "'s turn has been skipped due to skip round trap");
                // the turn of the player has been skipped and now it is set back to false
                players[playerNumber].skipRound = false;
            }

            playerNumber++;
        }
    }

    private String getSpecialIndexKey(HashMap<String, List<Integer>> hashMap, Integer value) {

        // if the given value is equal to value from entry
        // return the corresponding key
        for (Map.Entry<String, List<Integer>> entry : hashMap.entrySet()) {
            ArrayList<Integer> list = (ArrayList<Integer>) entry.getValue();
            for (Integer i : list) {
                if (i == value)
                    return entry.getKey();
            }
        }


//        Iterator iterator = hashMap.entrySet().iterator();
//
//        while (iterator.hasNext()) {
//            Map.Entry mapValue = (Map.Entry) iterator.next();
//            if (numberIsAnySpecialNumber(mapValue, value)) {
//                return (String) mapValue.getKey();
//            }
//        }
        return null;
    }

    private int rollDice() {
        Random random = new Random();
        int number = random.nextInt(6 - 1 + 1) + 1;
        System.out.println("Dice Outcome : " + number);

        return number;
    }

    private void setSpecialIndex() {
        Integer indexNumber = 0;
        ArrayList<Integer> trapIndex = new ArrayList<>();
        ArrayList<Integer> bonusIndex = new ArrayList<>();

        specialIndexHashMap.put("Trap", trapIndex);
        specialIndexHashMap.put("Bonus", bonusIndex);

        while (trapIndex.size() < 5) {
            indexNumber = generateRandom1To30();
            if (!numberIsAnySpecialNumber(specialIndexHashMap, indexNumber)) {
                specialIndexHashMap.get("Trap").add(indexNumber);
            }
        }


        while (bonusIndex.size() < 5) {
            indexNumber = generateRandom1To30();
            if (!numberIsAnySpecialNumber(specialIndexHashMap, indexNumber)) {
                specialIndexHashMap.get("Bonus").add(indexNumber);
            }
        }

        Iterator iterator = specialIndexHashMap.entrySet().iterator();

        // printing the special field index
        System.out.println("\tThe Special Fields Indices");
        while (iterator.hasNext()) {
            Map.Entry mapValue = (Map.Entry) iterator.next();
            System.out.print(mapValue.getValue() + "\t");
        }
        System.out.println();
    }

    private int randomFieldSelection() {
        Random random = new Random();
        return random.nextInt(3 - 1 + 1) + 1;
    }

    private boolean numberIsAnySpecialNumber(HashMap<String, List<Integer>> hashMap, Integer toCheckValue) {
        Iterator iterator = hashMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry mapValue = (Map.Entry) iterator.next();
            ArrayList<Integer> list = (ArrayList<Integer>) mapValue.getValue();
            for (Integer i : list) {
                if (i == toCheckValue)
                    return true;
            }
        }
        return false;
    }

    private int generateRandom1To30() {
        Random random = new Random();
        return random.nextInt(30 - 1 + 1) + 1;
    }

    private int askNumberOfPlayers() {
        System.out.println("Please enter number of players between 2 & 4 :");
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();
        while (number < GlobalVariables.minimumPlayers || number > GlobalVariables.maximumPlayers) {
            System.out.println("Please enter valid number of players in between 2 & 4 :");
            number = sc.nextInt();
        }
        return number;
    }


    /*----- Bonus Fields----*/
    @Override
    public void movePlayerTwoFieldsForward(Player player) {
        System.out.println("Bonus : movePlayerTwoFieldsForward");
        player.movePlayer(2);
    }

    @Override
    public void moveOtherPLayersTwoFieldsBackward(Player[] players, int id) {
        System.out.println("Bonus : moveOtherPLayersTwoFieldsBackward");
        for (int i = 0; i < players.length; i++) {
            if (players[i].id != id) {
                players[i].movePlayer(-2);
            }
        }
    }

    @Override
    public void utilizeJoker(Player player) {
        System.out.println("Bonus : utilizeJoker");
        player.joker = true;
    }

    /*----- Trap Fields----*/
    @Override
    public void movePlayerTwoFieldsBack(Player player) {
        System.out.println("Trap : movePlayerTwoFieldsBack");
        player.movePlayer(-2);
    }

    @Override
    public void moveOtherPLayersTwoFieldsForward(Player[] players, int id) {
        System.out.println("Trap : moveOtherPLayersTwoFieldsForward");
        for (int i = 0; i < players.length; i++) {
            if (players[i].id != id) {
                players[i].movePlayer(2);
            }
        }
    }

    @Override
    public void skipTheRound(Player player) {
        System.out.println("Trap : skipTheRound");
        player.skipRound = true;
    }
}
