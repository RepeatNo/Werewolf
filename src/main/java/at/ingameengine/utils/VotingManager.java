package at.ingameengine.utils;

import at.ingameengine.entities.Votes;
import at.ingameengine.entities.WerewolfPlayer;
import at.ingameengine.werewolf.Werewolf;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.Optional;

public class VotingManager {
    private final Werewolf plugin;
    private final ArrayList<Votes> votes;

    public VotingManager(Werewolf plugin) {
        this.plugin = plugin;
        votes = new ArrayList<>();
    }

    public void addVote(WerewolfPlayer player, WerewolfPlayer target) {
        for (Votes vote : votes) {
            if (vote.ContainsPlayer(player)) vote.removeVote(player);
            if (vote.getTarget() == target) vote.addVote(player);
        }
    }

    public void getMostVotedPlayer() {
        Optional<Votes> mostVotedCount = votes.stream().max((o1, o2) -> o1.getVotesCount() - o2.getVotesCount());
        Bukkit.broadcastMessage(mostVotedCount.get().getTarget().getPlayer().getName());
    }

    public void openVotingInventory(ArrayList<WerewolfPlayer> players) {
        for (WerewolfPlayer player : players) {
            player.getPlayer().openInventory(plugin.getInventoryFactory().votingInventoryNode().getInventory());
        }
    }
}
