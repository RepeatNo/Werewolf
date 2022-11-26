package at.ingameengine.entities.roles;

import at.ingameengine.entities.WerewolfPlayer;
import org.javatuples.Pair;

public class Cupid extends ARole {
    Pair<WerewolfPlayer, WerewolfPlayer> lovers;

    @Override
    public void executeSetup() {
        //openinventory to select two players
    }

    @Override
    public void executeNight() {/*nothing to do*/}

    @Override
    public void onDeath() {
        // if one of the lovers dies, the other one dies too
    }

    public Pair<WerewolfPlayer, WerewolfPlayer> getLovers() {
        return lovers;
    }

    public void setLovers(Pair<WerewolfPlayer, WerewolfPlayer> lovers) {
        this.lovers = lovers;
    }
}
