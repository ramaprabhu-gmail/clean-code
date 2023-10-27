package com.d.tdd;

public class MyGame {

    int[] rolls = new int[21];
    int rollIndex = 0;
    public void roll(int pinsKnockedDown) {
        rolls[rollIndex++] = pinsKnockedDown;
    }

    public int score() {
        int sum = 0;
        int rollsIndex = 0;

        for (int frame = 0; frame < 10; frame++) {
            if(isStrike(rollsIndex)){
                sum += 10 + getBonusForStrike(rollsIndex);
                rollsIndex++;
            } else if(isSpare(rollsIndex)) {
                sum += 10 + getBonusForSpare(rollsIndex);
                rollsIndex += 2;
            } else {
                sum += rolls[rollsIndex] + rolls[rollsIndex + 1];
                rollsIndex += 2;
            }

        }
        return sum;
    }

    private int getBonusForSpare(int rollsIndex) {
        return rolls[rollsIndex + 2];
    }

    private int getBonusForStrike(int rollsIndex) {
        return rolls[rollsIndex + 1] + rolls[rollsIndex + 2];
    }

    private boolean isStrike(int rollsIndex) {
        return rolls[rollsIndex] == 10;
    }

    private boolean isSpare(int rollsIndex) {
        return rolls[rollsIndex] + rolls[rollsIndex + 1] == 10;
    }
}
