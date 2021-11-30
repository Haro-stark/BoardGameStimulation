package com.company;

public interface Trap {
    void movePlayerTwoFieldsBack(Player player);
    void moveOtherPLayersTwoFieldsForward(Player[] players, int id);
    void skipTheRound(Player player);
}
