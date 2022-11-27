package at.ingameengine.gamestates;

import at.ingameengine.entities.WerewolfPlayer;
import at.ingameengine.entities.roles.ERole;
import at.ingameengine.utils.VotingManager;
import at.ingameengine.werewolf.Werewolf;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class AGameState {
    public static final int
            SETUP_STATE = 0,
            LOBBY_STATE = 1,
            FIRST_DAY_STATE = 2,
            FIRST_NIGHT_STATE = 3,
            DISCUSSION_STATE = 4,
            DAY_STATE = 5,
            NIGHT_STATE = 6,
            ENDING_STATE = 7;
    protected HashMap<Integer, ArrayList<VotingManager>> votingSteps;
    protected Integer stepId = 0;
    protected Integer runningTaskId;
    protected Werewolf plugin;

    public AGameState(Werewolf plugin) {
        this.plugin = plugin;
        votingSteps = new HashMap<>();
    }

    public abstract void start();

    public abstract void stop();

    public abstract void accept(IGameStateVisitor commandInspector);

    protected abstract void runnable();

    public void addStep(Integer stepId, ArrayList<ERole> roles) {
        ArrayList<VotingManager> votingManagers = new ArrayList<>();
        for (ERole role : roles) {
            votingManagers.add(new VotingManager(plugin, role));
        }
        votingSteps.put(stepId, votingManagers);
    }

    public boolean nextStep() {
        if (stepId < votingSteps.size()) {
            stepId++;
        }
        return stepId < votingSteps.size();
    }

    public void addVote(WerewolfPlayer player, WerewolfPlayer target) {
        VotingManager votingManager = getVotingStep(player);
        if (votingManager == null) return;
        votingManager.addVote(player, target);
    }

    private VotingManager getVotingStep(WerewolfPlayer player) {
        for (VotingManager votingManager : votingSteps.get(stepId)) {
            if (votingManager.hasRole(player)) {
                return votingManager;
            }
        }
        return null;
    }
}
