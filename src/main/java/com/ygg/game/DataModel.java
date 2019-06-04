package com.ygg.game;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class DataModel {

    List<String> choiceCombination;
    int reward;
    double probability;

}
