package com.d.tdd;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyGameTest {
    MyGame myGame = new MyGame();
    @Test
    void testAllZeros(){
        rollMultipleTimes(0, 20);
        assertEquals(0,myGame.score());
    }

    @Test
    void testAllOnes(){
        rollMultipleTimes( 1, 20);
        assertEquals(20,myGame.score());
    }

    @Test
    void testAllHalfOnesHalfTwos(){
        rollMultipleTimes( 1, 10);
        rollMultipleTimes( 2, 10);
        assertEquals(30,myGame.score());
    }

    @Test
    void testSpare(){
        rollASpare();
        rollMultipleTimes( 1, 18);
        assertEquals(29,myGame.score());
    }

    @Test
    void testTwoSpare(){
        rollASpare();
        rollASpare();
        rollMultipleTimes( 1, 16);
        assertEquals(42,myGame.score());
    }

    @Test
    void testStrike(){
        myGame.roll(10);
        rollMultipleTimes( 1, 18);
        assertEquals(30,myGame.score());
    }

    private void rollASpare() {
        rollMultipleTimes( 5, 2);
    }

    private void rollMultipleTimes(int pinsKnockedDown, int noOfRolls) {
        for (int i = 0; i < noOfRolls; i++) {
            myGame.roll(pinsKnockedDown);
        }
    }
}
