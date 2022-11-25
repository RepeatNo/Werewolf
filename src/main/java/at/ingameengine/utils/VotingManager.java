package at.ingameengine.utils;

import at.ingameengine.entities.WerewolfPlayer;
import at.ingameengine.werewolf.Werewolf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class VotingManager {
    private final Werewolf plugin;
    private final HashMap<WerewolfPlayer, Integer> votes;

    public VotingManager(Werewolf plugin) {
        this.plugin = plugin;
        votes = new HashMap<>();
    }

    public void addVote(WerewolfPlayer target) {
        for (WerewolfPlayer player : votes.keySet()) {
            if (player.equals(target)) {
                votes.put(player, votes.get(player) + 1);
                return;
            }
        }
    }

    public void getMostVotedPlayer() {
        int max = 0;
        HashSet<WerewolfPlayer> player = new HashSet<>();
        for (WerewolfPlayer werewolfPlayer : votes.keySet()) {
            if (votes.get(werewolfPlayer) == max) {
                max = votes.get(werewolfPlayer);
                player.add(werewolfPlayer);
            }

            if (votes.get(werewolfPlayer) > max) {
                max = votes.get(werewolfPlayer);
                player.clear();
                player.add(werewolfPlayer);
            }
        }
    }

    public void openVotingInventory() {
        ArrayList<WerewolfPlayer> players = plugin.getPlayers();
        for (WerewolfPlayer player : players) {
            votes.put(player, 0);
        }
    }

    public void openVotingInventory(ArrayList<WerewolfPlayer> players) {
        for (WerewolfPlayer player : players) {
            player.getPlayer().openInventory(plugin.getInventoryFactory().votingInventoryNode().getInventory());
        }
    }
}
