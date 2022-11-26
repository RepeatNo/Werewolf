package at.ingameengine.entities;

import at.ingameengine.werewolf.Werewolf;

public class GameProfile {

    Werewolf plugin;

    String name;

    public GameProfile(Werewolf plugin, String name) {
        this.plugin = plugin;
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
