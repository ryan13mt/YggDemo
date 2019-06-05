package com.ygg.game.models;


public enum MainRoundEnum {

    ONEHUNDRED(100),
    TWENTY(20),
    FIVE(5),
    EXTRALIFE(null),
    GAMEOVER(null);

    private Integer value;

    MainRoundEnum(final Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

}

