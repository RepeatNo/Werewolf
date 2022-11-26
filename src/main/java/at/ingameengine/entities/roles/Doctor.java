package at.ingameengine.entities.roles;

import at.ingameengine.entities.WerewolfPlayer;

public class Doctor extends ARole {

    WerewolfPlayer protectedPlayer;

    @Override
    public void executeSetup() {

    }

    @Override
    public void executeNight() {
        // set protected player
    }

    @Override
    public void onDeath() {

    }
}
