package at.ingameengine.entities.roles;

public abstract class ARole {

    public abstract void executeSetup();

    public abstract void executeNight();

    public void executeDay() {
    }

    public void onDeath() {

    }
}
