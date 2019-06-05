package com.ygg.game.models;


public enum BonusRoundEnum {

    TWENTY(20),
    TEN(10),
    FIVE(5),
    SECONDCHANCE(null);

    private Integer value;

    BonusRoundEnum(final Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

}

