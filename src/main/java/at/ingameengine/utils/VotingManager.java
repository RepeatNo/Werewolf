package at.ingameengine.utils;

import at.ingameengine.entities.Vote;
import at.ingameengine.entities.WerewolfPlayer;
import at.ingameengine.werewolf.Werewolf;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.Optional;

public class VotingManager {
    private final Werewolf plugin;
    private final ArrayList<Vote> votes;

    public VotingManager(Werewolf plugin) {
        this.plugin = plugin;
        votes = new ArrayList<>();
    }

    public void addVote(WerewolfPlayer player, WerewolfPlayer target) {
        for (Vote vote : votes) {
            if (vote.ContainsPlayer(player)) {
                Bukkit.broadcastMessage("Player already voted");
                vote.removeVote(player);
            }
            if (vote.getTarget().compare(target)) {
                Bukkit.broadcastMessage("Player added");
                Bukkit.broadcastMessage(target.getPlayer().getName());
                Bukkit.broadcastMessage(player.getPlayer().getName());
                vote.addVote(player);
            }
        }
    }

    public ArrayList<Vote> getVotes() {
        return votes;
    }

    public void getMostVotedPlayer() {
        Optional<Vote> mostVotedCount = votes.stream().max((o1, o2) -> o1.getVotesCount() - o2.getVotesCount());
        mostVotedCount.ifPresent(vote -> Bukkit.broadcastMessage(vote.getTarget().getPlayer().getName()));
    }

    public void initVotes() {
        for (WerewolfPlayer player : plugin.getPlayers()) {
            votes.add(new Vote(player));
        }
    }
}
