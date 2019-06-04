package com.ygg.game.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.paukov.combinatorics3.Generator;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;
import java.util.stream.Collectors;

@RunWith(MockitoJUnitRunner.class)
public class RoundServiceTest {

    @InjectMocks
    private RoundService sut;

    public List<String> mainRoundRandomize() {
        List<String> stringList = Arrays.asList(sut.mainRound.clone());
        Collections.shuffle(stringList);
        return stringList;
    }

    public String randomPickBonusRound(final boolean firstTime) {
        final Random random = new Random();
        if (firstTime) {
            return sut.bonusRound[random.nextInt(sut.bonusRound.length)];
        } else {
            return sut.secondBonusRound[random.nextInt(sut.secondBonusRound.length)];
        }
    }

    @Test
    public void getFullGamePlaythrough() throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter("10Million.txt", "UTF-8");
        ArrayList<Integer> rewards = new ArrayList();
        for (int i = 0; i < 10000000; i++) {

            final List<String> firstMainRound = mainRoundRandomize();
            final String firstBonusPick = randomPickBonusRound(true);
            final List<String> secondMainRound = mainRoundRandomize();
            final String secondBonusPick = randomPickBonusRound(false);

            List<String> randomizedFullGame = new ArrayList<>(firstMainRound);
            randomizedFullGame.add(firstBonusPick);
            randomizedFullGame.addAll(secondMainRound);
            randomizedFullGame.add(secondBonusPick);

            int reward = sut.gameLogic(firstMainRound, firstBonusPick, secondMainRound, secondBonusPick);
            rewards.add(reward);

            writer.println(/*randomizedFullGame + " And winning " + */reward);
        }
        writer.close();
        List<Integer> collect = rewards.stream().distinct().collect(Collectors.toList());
        System.out.println(collect);
    }

    //Task 1
    @Test
    public void getFullGamePlaythroughExpectedValue() {
        NumberFormat formatter = new DecimalFormat("#0.0");
        ArrayList<Integer> rewards = new ArrayList();
        for (int i = 0; i < 10000000; i++) {
            rewards.add(sut.gameLogic(mainRoundRandomize(), randomPickBonusRound(true), mainRoundRandomize(), randomPickBonusRound(false)));
        }
        final OptionalDouble average = rewards.stream().mapToDouble(a -> a).average();

        System.out.println("Average of: " + formatter.format(average.getAsDouble()) + " with " + rewards.size() + " entries");
    }

}
