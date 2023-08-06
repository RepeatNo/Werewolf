package at.ingameengine.utils;

import at.ingameengine.entities.WerewolfPlayer;
import at.ingameengine.entities.roles.ARole;
import at.ingameengine.werewolf.Werewolf;

import java.util.ArrayList;
import java.util.HashMap;

public class RoleManager {
    Werewolf plugin;

    public RoleManager(Werewolf plugin) {
        this.plugin = plugin;
    }

    public void allocateRoles(HashMap<ARole, Integer> roleAmount) {
        ArrayList<ARole> roles = new ArrayList<>();

        for (ARole role : roleAmount.keySet()) {
            for (int i = 0; i < roleAmount.get(role); i++) {
                roles.add(role);
            }
        }

        for (WerewolfPlayer player : plugin.getWerewolfPlayers()) {
            int random = (int) (Math.random() * roles.size());
            player.setRole(roles.get(random));
            player.sendMessage("Du bist " + roles.get(random).getClass().getSimpleName());
            roles.remove(random);
        }
    }


}
