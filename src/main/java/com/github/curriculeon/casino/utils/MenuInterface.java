package com.github.curriculeon.casino.utils;

import com.github.curriculeon.utils.InputOutputConsoleInterface;
import com.github.curriculeon.utils.StringUtils;

/**
 * Created by leon on 6/25/2020.
 * @ATTENTION_TO_STUDENTS - You are advised against modifying this class
 */
public interface MenuInterface
        <SomeDecision extends Enum<SomeDecision> & DecisionInterface> {

    InputOutputConsoleInterface getConsole();

    SomeDecision[] getDecisions();

    default SomeDecision getInput() {
        String userInput = null;
        try {
            userInput = getInputFromUser();
            return getValueOf(userInput);
        } catch(IllegalArgumentException iae) {
            getConsole().println("[ %s ] is not a valid user-input.\nPlease try again.", userInput);
            return getInput();
        }
    }

    default void display() {
        getConsole().println(toString());
    }

    default String getInputFromUser() {
        String border = StringUtils.repeatString("-", 50);
        getConsole().println(border);
        display();
        return getConsole()
                .getStringInput("Select an option.")
                .toUpperCase();
    }

    @SuppressWarnings("all")
    default SomeDecision getValueOf(String userInput) {
        return (SomeDecision) SomeDecision.valueOf(getDecisions()[0].getClass(), userInput
                .replace(" ", "_")
                .toUpperCase());
    }

    default void handleIllegalInput(String input) {
        getConsole().println("Invalid input! %s is not valid input!", input);
        display();
    }
}
