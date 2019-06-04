package com.ygg.game.service;

import java.util.*;

public class RoundService {

    public String[] mainRound = {"100", "20", "20", "5", "5", "5", "5", "5", "extraLife", "gameOver", "gameOver", "gameOver"};
    public String[] bonusRound = {"secondChance", "20", "10", "5"};
    public String[] secondBonusRound = {"20", "10", "5"};

    public int gameLogic(final List<String> mainRound, final String firstBonusRound, final List<String> secondMainRound, final String secondBonusRound) {
        int reward = 0;

        reward += calculateMainRoundReward(mainRound);

        if (firstBonusRound.equals("secondChance")) {
            reward += calculateMainRoundReward(secondMainRound);
            reward += Integer.valueOf(secondBonusRound);
        } else {
            reward += Integer.valueOf(firstBonusRound);
        }

        return reward;
    }

    public int calculateMainRoundReward(List<String> mainRound) {
        int reward = 0;
        boolean extraLife = false;

        for (String pick : mainRound) {
            switch (pick) {
                case "100":
                    reward += 100;
                    break;
                case "20":
                    reward += 20;
                    break;
                case "5":
                    reward += 5;
                    break;
                case "extraLife":
                    extraLife = true;
                    break;
                case "gameOver":
                    if (extraLife) {
                        extraLife = false;
                        break;
                    } else {
                        return reward;
                    }
                default:
                    throw new IllegalStateException("Not possible!!!");
            }
        }
        throw new IllegalStateException("Not possible!!!");
    }

}
