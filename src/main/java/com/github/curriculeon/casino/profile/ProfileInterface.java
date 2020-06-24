package com.github.curriculeon.casino.profile;

public interface ProfileInterface {
    void printInformation();

    void increaseBalance(double amount);

    void decreaseBalance(double amount);

    double getBalance();

    String getName();

    Long getProfileId();
}
