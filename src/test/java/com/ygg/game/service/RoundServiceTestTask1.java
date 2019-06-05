package com.ygg.game.service;

import com.ygg.game.models.BonusRoundEnum;
import com.ygg.game.models.MainRoundEnum;
import com.ygg.game.models.Round;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

@RunWith(MockitoJUnitRunner.class)
public class RoundServiceTestTask1 {

    @InjectMocks
    private RoundServiceTask1 sut;
    @InjectMocks
    private Round round;

    private static final int TENMILLION = 10000000;

    public List<MainRoundEnum> mainRoundRandomize() {
        List<MainRoundEnum> stringList = Arrays.asList(round.mainRound.clone());
        Collections.shuffle(stringList);
        return stringList;
    }

    public BonusRoundEnum randomPickBonusRound(final boolean firstTime) {
        final Random random = new Random();
        if (firstTime) {
            return round.bonusRound[random.nextInt(round.bonusRound.length)];
        } else {
            return round.secondBonusRound[random.nextInt(round.secondBonusRound.length)];
        }
    }

    //Task 1
    @Test
    public void getFullGamePlaythroughExpectedValue() {
        final NumberFormat formatter = new DecimalFormat("#0.0");
        List<Integer> rewards = new ArrayList();
        for (int i = 0; i < TENMILLION; i++) {
            rewards.add(sut.gameLogic(mainRoundRandomize(), randomPickBonusRound(true), mainRoundRandomize(), randomPickBonusRound(false)));
        }
        final OptionalDouble average = rewards.stream().mapToDouble(a -> a).average();

        System.out.println("Average of: " + formatter.format(average.getAsDouble()) + " with " + rewards.size() + " entries");
    }

}
