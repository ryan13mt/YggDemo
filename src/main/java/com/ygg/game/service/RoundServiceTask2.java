package com.ygg.game.service;

import com.ygg.game.models.DataModel;
import com.ygg.game.models.MainRoundEnum;

import java.util.List;

import static com.ygg.game.models.MainRoundEnum.*;

public class RoundServiceTask2 {

    public DataModel calculateMainRoundReward(final List<MainRoundEnum> choices) {
        int reward = 0;
        boolean extraLife = false;
        double probability = 1.0;
        double numberOf100Left = 1.0;
        double numberOf20Left = 2.0;
        double numberOf5Left = 5.0;
        double numberOfExtraLifeLeft = 1.0;
        double numberOfGameOverLeft = 3.0;

        for (int i = 0; i < choices.size(); i++) {
            switch (choices.get(i)) {
                case ONEHUNDRED:
                    reward += ONEHUNDRED.getValue();
                    probability = probability * (numberOf100Left / (choices.size() - i));
                    numberOf100Left -= 1;
                    break;
                case TWENTY:
                    reward += TWENTY.getValue();
                    probability = probability * (numberOf20Left / (choices.size() - i));
                    numberOf20Left -= 1;
                    break;
                case FIVE:
                    reward += FIVE.getValue();
                    probability = probability * (numberOf5Left / (choices.size() - i));
                    numberOf5Left -= 1;
                    break;
                case EXTRALIFE:
                    extraLife = true;
                    probability = probability * (numberOfExtraLifeLeft / (choices.size() - i));
                    numberOfExtraLifeLeft -= 1;
                    break;
                case GAMEOVER:
                    if (extraLife) {
                        extraLife = false;
                        probability = probability * (numberOfGameOverLeft / (choices.size() - i));
                        numberOfGameOverLeft -= 1;
                        break;
                    } else {
                        probability = probability * (numberOfGameOverLeft / (choices.size() - i));
                        return new DataModel(choices.subList(0, i + 1), reward, probability);
                    }
                default:
                    throw new IllegalStateException("Not possible!!!");
            }
        }
        throw new IllegalStateException("Not possible!!!");
    }

}
