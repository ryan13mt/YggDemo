package com.ygg.game.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class DataModel {

    private List<MainRoundEnum> choiceCombination;
    private int reward;
    private double probability;

}
