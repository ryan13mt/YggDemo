package com.ygg.game.service;

import com.ygg.game.DataModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.paukov.combinatorics3.Generator;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingDouble;

@RunWith(MockitoJUnitRunner.class)
public class RoundServiceTestTask2 {

    @InjectMocks
    private RoundServiceTask2 sut;


    //Task 2
    @Test
    public void getAllPossibleCombinations() {

        List<DataModel> allPossibleCombinations = new ArrayList<>();
        NumberFormat formatter = new DecimalFormat("#0.0");
        double expectedValueOfMainRound = 0.0;

        //get all possible combinations
        Generator.permutation("100", "20", "20", "5", "5", "5", "5", "5", "extraLife", "gameOver", "gameOver", "gameOver")
                .simple()
                .stream()
                .forEach(entry -> allPossibleCombinations.add(sut.gameLogic(entry)));


        //removing duplicate partial combinations
        List<DataModel> allPossibleUniqueCombinations = io.vavr.collection.List.ofAll(allPossibleCombinations).distinctBy(DataModel::getChoiceCombination).toJavaList();

        //collecting combinations and summing the probability of those with the same reward
        Map<Integer, Double> map = allPossibleUniqueCombinations.stream().collect(groupingBy(DataModel::getReward, summingDouble(DataModel::getProbability)));

        for (Map.Entry<Integer, Double> entry : map.entrySet()) {
            expectedValueOfMainRound = expectedValueOfMainRound + entry.getKey()*entry.getValue();
        }
        System.out.println(expectedValueOfMainRound);

        //expected value of bonus round without second chance
        double expectedValueOfBonusRound = (5.0/3)+(10.0/3)+(20.0/3);
        System.out.println(expectedValueOfBonusRound);

        double expectedValueOfSecondChance = expectedValueOfMainRound + expectedValueOfBonusRound;
        System.out.println(expectedValueOfSecondChance);

        //expected value of bonus round with second chance
        double expectedValueOfBonusRoundWithSecondChance = (5.0/4)+(10.0/4)+(20.0/4)+(expectedValueOfSecondChance/4);
        System.out.println(expectedValueOfBonusRoundWithSecondChance);

        System.out.println("Expected value of whole game is " + formatter.format(expectedValueOfMainRound+expectedValueOfBonusRoundWithSecondChance));

    }

}
