package com.github.curriculeon;


import com.github.curriculeon.profile.ProfileInterface;

/**
 * Created by leon on 5/11/17.
 */
public class Player implements PlayerInterface {
    protected final ProfileInterface profile;
    protected final String profileName;

    public Player(ProfileInterface profile) {
        this.profile = profile;
        this.profileName = profile.getName();
    }

    public ProfileInterface getProfile() {
        return profile;
    }

    public String getName() {
        return profileName;
    }
}
