package com.ygg.game.models;

public class Round {

    public MainRoundEnum[] mainRound = {
            MainRoundEnum.ONEHUNDRED,
            MainRoundEnum.TWENTY,
            MainRoundEnum.TWENTY,
            MainRoundEnum.FIVE,
            MainRoundEnum.FIVE,
            MainRoundEnum.FIVE,
            MainRoundEnum.FIVE,
            MainRoundEnum.FIVE,
            MainRoundEnum.EXTRALIFE,
            MainRoundEnum.GAMEOVER,
            MainRoundEnum.GAMEOVER,
            MainRoundEnum.GAMEOVER
    };

    public BonusRoundEnum[] bonusRound = {
            BonusRoundEnum.TWENTY,
            BonusRoundEnum.TEN,
            BonusRoundEnum.FIVE,
            BonusRoundEnum.SECONDCHANCE
    };

    public BonusRoundEnum[] secondBonusRound = {
            BonusRoundEnum.TWENTY,
            BonusRoundEnum.TEN,
            BonusRoundEnum.FIVE
    };
}