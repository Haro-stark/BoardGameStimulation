package com.company;

public interface Bonus {
    void movePlayerTwoFieldsForward(Player player);
    void moveOtherPLayersTwoFieldsBackward(Player[] players, int id);
    void utilizeJoker(Player player);
}
