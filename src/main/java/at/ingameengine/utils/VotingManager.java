package at.ingameengine.utils;

import at.ingameengine.entities.Vote;
import at.ingameengine.entities.WerewolfPlayer;
import at.ingameengine.entities.roles.ERole;
import at.ingameengine.werewolf.Werewolf;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.Optional;

public class VotingManager {
    private final Werewolf plugin;
    private final ArrayList<Vote> votes;
    private final ERole role;

    public VotingManager(Werewolf plugin, ERole role) {
        this.plugin = plugin;
        this.role = role;
        votes = new ArrayList<>();
    }

    public void addVote(WerewolfPlayer player, WerewolfPlayer target) {
        for (Vote vote : votes) {
            if (vote.ContainsPlayer(player)) {
                vote.removeVote(player);
            }
            if (vote.getTarget().compare(target)) {
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

    public boolean hasRole(WerewolfPlayer player) {
        if (role == ERole.NONE) return true;
        if (player.getRole().toString().equalsIgnoreCase(role.toString())) return true;
        return player.getIsMajor() && role.toString().equalsIgnoreCase(ERole.MAYOR.toString());
    }
}
