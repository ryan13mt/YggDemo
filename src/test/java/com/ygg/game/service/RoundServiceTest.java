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
