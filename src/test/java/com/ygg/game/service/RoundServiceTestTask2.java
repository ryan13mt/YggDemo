package com.ygg.game.service;

import com.ygg.game.models.DataModel;
import com.ygg.game.models.Round;
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

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingDouble;

@RunWith(MockitoJUnitRunner.class)
public class RoundServiceTestTask2 {

    @InjectMocks
    private RoundServiceTask2 sut;

    @InjectMocks
    private Round round;

    //Task 2
    @Test
    public void calculateExpectedValue() {

        List<DataModel> allPossibleCombinations = new ArrayList<>();
        final NumberFormat formatter = new DecimalFormat("#0.0");
        double expectedValueOfMainRound = 0.0;

        //get all possible combinations of the main round and run them through the game logic to get probability and choices till game over
        Generator.permutation(round.mainRound)
                .simple()
                .stream()
                .forEach(entry -> allPossibleCombinations.add(sut.calculateMainRoundReward(entry)));

        //removing duplicate combinations returned by game logic
        final List<DataModel> allPossibleUniqueCombinations = io.vavr.collection.List.ofAll(allPossibleCombinations).distinctBy(DataModel::getChoiceCombination).toJavaList();

        //collecting combinations and summing the probability of those with the same reward
        final Map<Integer, Double> map = allPossibleUniqueCombinations.stream().collect(groupingBy(DataModel::getReward, summingDouble(DataModel::getProbability)));

        //expected value of main round
        for (final Map.Entry<Integer, Double> entry : map.entrySet()) {
            expectedValueOfMainRound = expectedValueOfMainRound + entry.getKey() * entry.getValue();
        }
        System.out.println("Expected value of main round is " + expectedValueOfMainRound);

        //expected value of bonus round without second chance
        final double expectedValueOfBonusRound = (5.0 / 3) + (10.0 / 3) + (20.0 / 3);
        System.out.println("Expected value of bonus round without second chance is " + expectedValueOfBonusRound);

        //expected value of second chance (main round + bonus round without second chance)
        final double expectedValueOfSecondChance = expectedValueOfMainRound + expectedValueOfBonusRound;
        System.out.println("Expected value of second chance (main round + bonus round without second chance) is " + expectedValueOfSecondChance);

        //expected value of bonus round with second chance
        final double expectedValueOfBonusRoundWithSecondChance = (5.0 / 4) + (10.0 / 4) + (20.0 / 4) + (expectedValueOfSecondChance / 4);
        System.out.println("Expected value of bonus round with second chance is " + expectedValueOfBonusRoundWithSecondChance);

        //expected value of whole game
        System.out.println("Expected value of whole game is " + formatter.format(expectedValueOfMainRound + expectedValueOfBonusRoundWithSecondChance));

    }

}
