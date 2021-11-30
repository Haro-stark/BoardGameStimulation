package com.company;

import java.util.*;

public class Game implements Trap,Bonus {
//    int[] trapIndex;
//    int[] bonusIndex;
    HashMap<String, Integer> specialIndexHashMap;
    Player[] players;

    public Game(){
//        trapIndex = new int[GlobalVariables.trapIndex];
//        bonusIndex = new int[GlobalVariables.bonusIndex];
        specialIndexHashMap = new HashMap<String, Integer>();
        players = new Player[askNumberOfPlayers()];
    }

    public void generateGame(){
//        setBonusIndex();
//        setTrapIndex();
        setSpecialIndex();
        String playerName;
        Scanner sc = new Scanner(System.in);
        for (int i=0; i<players.length; i++){
            System.out.print("Name of player "+ i +": ");
            playerName = sc.next();
            players[i] = new Player(i, playerName);
        }

    }

    public void playGame(){
        int playerNumber=0, stepJump=0;
        boolean playerWon = false;

        while(!playerWon){
            // keeps the players turn in circle
            if(playerNumber>players.length) playerNumber=0;
            stepJump = rollDice();
            if (numberIsAnySpecialNumber(specialIndexHashMap, players[playerNumber].currentIndex + stepJump)) {
                String specialIndexKey = getSpecialIndexKey(specialIndexHashMap, players[playerNumber].currentIndex + stepJump);
                if(specialIndexKey!= null && specialIndexKey.toLowerCase().equals("trap")){

                }else if(specialIndexKey!= null && specialIndexKey.toLowerCase().equals("bonus")){

                }else if(specialIndexKey == null){

                }
            }else{
                if(players[playerNumber].skipRound == false)
                    players[playerNumber].movePlayer(stepJump);
                else{
                    players[playerNumber].skipRound = false;
                }
            }

            playerNumber++;



            playerWon=true;
        }

    }

    private String getSpecialIndexKey(HashMap<String, Integer> hashMap, Integer value){

        Iterator iterator = hashMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry mapValue = (Map.Entry) iterator.next();
            if(mapValue.getValue() == value){
                return (String) mapValue.getKey();
            }
        }
        return null;
    }

    private int rollDice(){
        Random random = new Random();
        return random.nextInt(6 - 1 + 1) + 1;

    }

    private void setSpecialIndex(){
        Integer indexNumber=0;
        int countTrapIndex = 0, countBonusIndex = 0;

        while(countTrapIndex<5){
            indexNumber = generateRandom1To30();
            if(!numberIsAnySpecialNumber(specialIndexHashMap, indexNumber)){
                countTrapIndex++;
                specialIndexHashMap.put("Trap", indexNumber);
            }
        }
        while(countBonusIndex<5){
            indexNumber = generateRandom1To30();
            if(!numberIsAnySpecialNumber(specialIndexHashMap, indexNumber)){
                countBonusIndex++;
                specialIndexHashMap.put("Bonus", indexNumber);
            }
        }


    }
//    private void setTrapIndex(){
//        int countTrapIndex = 0, number=0;
//        int index=0;
//        while(countTrapIndex<5){
//            number = generateRandom1To30();
//            if(numberExists(trapIndex, number) && numberExists(bonusIndex, number)){
//                countTrapIndex++;
//                trapIndex[index] = number;
//                index++;
//            }
//        }
//
//    }

//    private void setBonusIndex(){
//        int countBonusIndex = 0, number=0;
//        int index=0;
//        while(countBonusIndex<5){
//            number = generateRandom1To30();
//            if(numberExists(trapIndex, number) && numberExists(bonusIndex, number)){
//                countBonusIndex++;
//                bonusIndex[index] = number;
//                index++;
//            }
//        }
//
//    }


    private boolean numberIsAnySpecialNumber(HashMap<String, Integer> hashMap, Integer toCheckValue){
        Iterator iterator = hashMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry mapValue = (Map.Entry) iterator.next();
            if(mapValue.getValue() == toCheckValue){
                return true;
            }
        }
        return false;
    }

    private int generateRandom1To30(){
        Random random = new Random();
        return random.nextInt(30 - 1 + 1) + 1;
    }

    private int askNumberOfPlayers(){
        System.out.println("Please enter number of players between 2 & 4 :");
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();
        while(number<GlobalVariables.minimumPlayers || number>GlobalVariables.maximumPlayers){
            System.out.println("Please enter valid number of players in between 2 & 4 :");
            number = sc.nextInt();
        }
        return number;
    }




    @Override
    public void movePlayerTwoFieldsForward(Player player) {

    }

    @Override
    public void moveOtherPLayersTwoFieldsBackward(Player[] players) {

    }

    @Override
    public void utilizeJoker(Player player) {

    }

    @Override
    public void movePlayerTwoFieldsBack(Player player) {

    }

    @Override
    public void moveOtherPLayersTwoFieldsForward(Player[] players) {

    }

    @Override
    public void skipTheRound(Player player) {

    }
}
