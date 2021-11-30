package com.company;

public interface Trap {
    void movePlayerTwoFieldsBack(Player player);
    void moveOtherPLayersTwoFieldsForward(Player[] players);
    void skipTheRound(Player player);
}
