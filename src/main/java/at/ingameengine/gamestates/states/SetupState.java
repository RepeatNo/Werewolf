package at.ingameengine.gamestates.states;

import at.ingameengine.gamestates.AGameState;
import at.ingameengine.gamestates.IGameStateVisitor;
import at.ingameengine.utils.ActionbarManager;
import at.ingameengine.werewolf.Werewolf;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

public class SetupState extends AGameState {

    int runnableTaskId; // Store the BukkitTask reference

    public SetupState(Werewolf plugin) {
        super(plugin);
    }

    @Override
    public void start() {
        startRunnable();
        plugin.getServer().getWorlds().get(0).setTime(6000);

        plugin.getActionbarManager().startActionBar(Werewolf.prefix + "§7Setup required!", plugin.getConfigManager().readString("permissions.admin"));
    }

    @Override
    public void stop() {
        if (runnableTaskId != -1) {
            Bukkit.getScheduler().cancelTask(runnableTaskId); // Cancel the runnable task
        }

        plugin.getActionbarManager().stopActionBar();
        plugin.getPlayerHandler().resetPlayer();
    }

    @Override
    public void accept(IGameStateVisitor commandInspector) {
        commandInspector.visit(this);
    }

    private void startRunnable() {
        runnableTaskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            @Override
            public void run() {
                if (checkSetupCompletion()) {
                    plugin.getGameStateManager().setGameState(LOBBY_WAITING_STATE);
                }
            }
        }, 1, 20L);
    }

    private boolean checkSetupCompletion() {
        if (plugin.getLocationsManager().getLocation("lobby-spawn") == null) return false;
        if (plugin.getLocationsManager().getLocation("game-spawn") == null) return false;

        return true;
    }
}
