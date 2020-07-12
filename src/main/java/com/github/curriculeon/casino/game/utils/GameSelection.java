package com.github.curriculeon.casino.game.utils;


import com.github.curriculeon.casino.game.cardgame.blackjack.BlackJackGameEngine;
import com.github.curriculeon.casino.game.cardgame.highlow.HighLowGameEngine;
import com.github.curriculeon.casino.utils.DecisionInterface;

import java.util.function.Supplier;

/**
 * Created by leon on 2/25/18.
 * @ATTENTION_TO_STUDENTS - You are advised against modifying this class
 */
public enum GameSelection implements DecisionInterface {
    HIGH_LOW(HighLowGameEngine::new),
    BLACKJACK(()-> (GameEngineInterface<?,?>)new BlackJackGameEngine());

    private final Supplier<GameEngineInterface<?,?>> gameConstructor;

    GameSelection(Supplier<GameEngineInterface<?,?>> gameConstructor) {
        this.gameConstructor = gameConstructor;
    }

    public void perform() {
        GameEngineInterface<?, ?> gameEngine = create();
        gameEngine.setUp();
        gameEngine.run();
    }

    public GameEngineInterface<?,?> create() {
        return gameConstructor.get();
    }
}
