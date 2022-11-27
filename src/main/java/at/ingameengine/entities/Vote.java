package at.ingameengine.entities;

import java.util.HashSet;

public class Vote {
    private final WerewolfPlayer target;
    private final HashSet<WerewolfPlayer> votes;

    public Vote(WerewolfPlayer target) {
        this.target = target;
        this.votes = new HashSet<>();
    }

    public WerewolfPlayer getTarget() {
        return target;
    }

    public int getVotesCount() {
        return votes.size();
    }

    public HashSet<WerewolfPlayer> getVotes() {
        return votes;
    }

    public void addVote(WerewolfPlayer player) {
        if (votes.contains(player)) return;
        votes.add(player);
    }

    public void removeVote(WerewolfPlayer player) {
        if (!votes.contains(player)) return;
        votes.remove(player);
    }

    public boolean ContainsPlayer(WerewolfPlayer player) {
        return votes.contains(player);
    }
}
