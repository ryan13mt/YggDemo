package com.ygg.game.service;

import com.ygg.game.DataModel;

import java.util.List;

public class RoundServiceTask2 {

    public DataModel gameLogic(final List<String> mainRound) {

        return calculateMainRoundReward(mainRound);

    }

    public DataModel calculateMainRoundReward(List<String> mainRound) {
        int reward = 0;
        boolean extraLife = false;
        double probability = 1.0;
        double numberof100Left = 1.0;
        double numberof20Left = 2.0;
        double numberof5Left = 5.0;
        double numberofextraLifeLeft = 1.0;
        double numberofGameOverLeft = 3.0;

        for (int i = 0; i < mainRound.size(); i++) {
            switch (mainRound.get(i)) {
                case "100":
                    reward += 100;
                    probability = probability*(numberof100Left/(12-i));
                    numberof100Left -= 1;
                    break;
                case "20":
                    reward += 20;
                    probability = probability*(numberof20Left/(12-i));
                    numberof20Left -= 1;
                    break;
                case "5":
                    reward += 5;
                    probability = probability*(numberof5Left/(12-i));
                    numberof5Left -= 1;
                    break;
                case "extraLife":
                    extraLife = true;
                    probability = probability*(numberofextraLifeLeft/(12-i));
                    numberofextraLifeLeft -= 1;
                    break;
                case "gameOver":
                    if (extraLife) {
                        extraLife = false;
                        probability = probability*(numberofGameOverLeft/(12-i));
                        numberofGameOverLeft -= 1;
                        break;
                    } else {
                        probability = probability*(numberofGameOverLeft/(12-i));
                        return new DataModel(mainRound.subList(0, i+1), reward, probability);
                    }
                default:
                    throw new IllegalStateException("Not possible!!!");
            }
        }
        throw new IllegalStateException("Not possible!!!");
    }

}
