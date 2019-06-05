package com.ygg.game.service;

import com.ygg.game.models.BonusRoundEnum;
import com.ygg.game.models.MainRoundEnum;

import java.util.List;

import static com.ygg.game.models.MainRoundEnum.*;

public class RoundServiceTask1 {

    public int gameLogic(final List<MainRoundEnum> mainRound, final BonusRoundEnum firstBonusRound, final List<MainRoundEnum> secondMainRound, final BonusRoundEnum secondBonusRound) {
        int reward = calculateMainRoundReward(mainRound);

        if (firstBonusRound.equals(BonusRoundEnum.SECONDCHANCE)) {
            reward += calculateMainRoundReward(secondMainRound);
            reward += secondBonusRound.getValue();
        } else {
            reward += firstBonusRound.getValue();
        }

        return reward;
    }

    private int calculateMainRoundReward(final List<MainRoundEnum> mainRound) {
        int reward = 0;
        boolean extraLife = false;

        for (final MainRoundEnum pick : mainRound) {
            switch (pick) {
                case ONEHUNDRED:
                    reward += ONEHUNDRED.getValue();
                    break;
                case TWENTY:
                    reward += TWENTY.getValue();
                    break;
                case FIVE:
                    reward += FIVE.getValue();
                    break;
                case EXTRALIFE:
                    extraLife = true;
                    break;
                case GAMEOVER:
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
