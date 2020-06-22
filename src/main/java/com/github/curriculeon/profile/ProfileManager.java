package com.github.curriculeon.profile;



import com.github.curriculeon.utils.InputOutputConsole;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leon.hunter on 2/1/2017.
 */
public class ProfileManager implements ProfileManagerInterface {
    public static volatile ProfileManagerInterface DEPRECATED_INSTANCE = new ProfileManager();
    public List<ProfileInterface> profiles = new ArrayList<ProfileInterface>();
    InputOutputConsole console = new InputOutputConsole();
    /**
     * @return array of respective profile objects
     */
    @Override
    public ProfileInterface createProfile() {
        console.println("Creating new player profile...");

        String name = console.getStringInput("Enter player's name");
        double startingBalance = console.getDoubleInput("Enter balance");
        ProfileInterface newPlayer = new Profile(name, startingBalance, (long)profiles.size());
        registerPlayer(newPlayer);

        return newPlayer;
    }

    /**
     * @param numberOfPlayers - number of profiles to create
     * @return array of respective profile objects
     */
    @Override
    public ProfileInterface[] createProfiles(int numberOfPlayers) {
        ProfileInterface[] players = new ProfileInterface[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++) {
            ProfileInterface someNewGuy = createProfile();
            players[i] = someNewGuy;
        }
        return players;
    }

    /**
     * Request each user to make action regarding their profile
     * @param numberOfPlayers - number of players anticipated to generate profiles
     * @return array of respective profile objects
     */
    @Override
    public ProfileInterface[] getProfiles(int numberOfPlayers) {
        ProfileInterface[] profiles = new ProfileInterface[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++) {
            console.println("Requesting action regarding player number %s...", i);
            String userDecision = console.getStringInput("Would you like to [create] a new profile, or [select] a preexisting?");
            if ("create".equalsIgnoreCase(userDecision)) {
                profiles[i] = createProfile();
            } else if("select".equalsIgnoreCase(userDecision)) {
                profiles[i] = selectProfile();
            } else {
                console.println("[ %s ] is not recognized as a valid command.", userDecision);
                profiles[i] = getProfiles(1)[0];
            }
        }
        return profiles;
    }

    /**
     * Select profile from memory, create profile if none exists
     * @return selected profile
     */
    @Override
    public ProfileInterface selectProfile() {
        if (!profiles.isEmpty()) {
            printProfilesInformation();
            long playerId = console.getLongInput("Please enter the desired player-profile's id number: ");
            for (ProfileInterface player : profiles) {
                if (player.getProfileId() == playerId) {
                    return player;
                }
            }
            console.println("[ %s ] is not a profile-id that our records recognize.", playerId);
        } else {
            console.println("There are currently no profiles in our records.");
            console.println("Try creating some player-profiles first.");
        }
        return createProfile();
    }

    /**
     * Print information from each profile in memory
     */
    @Override
    public void printProfilesInformation() {
        console.println("Displaying a list of player-profiles registered at our Casino...");
        for (ProfileInterface profile : profiles) {
            profile.printInformation();
        }
    }


    @Override
    public void registerPlayer(ProfileInterface someDude) {
        String name = someDude.getName();
        console.println("Checking my database to see if I know you like that...");
        if (profiles.contains(someDude)) {
            console.println("Oh, %s! I didn't realize it was you! Welcome back!", name);
            someDude.printInformation();
        } else {
            console.println("The Casino has registered a new profile!");
            console.println("Homie's profile-name is " + name);
            profiles.add(someDude);
        }
    }

    @Override
    public boolean contains(ProfileInterface someDude) {
        return profiles.contains(someDude);
    }
}
