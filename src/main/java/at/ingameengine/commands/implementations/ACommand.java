package at.ingameengine.commands.implementations;

import at.ingameengine.commands.CommanderParams;
import at.ingameengine.gamestates.GameStateManager;
import at.ingameengine.gamestates.IGameStateVisitor;
import at.ingameengine.werewolf.Werewolf;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class ACommand implements CommandExecutor, IGameStateVisitor {

    Werewolf plugin;
    GameStateManager gameStateManager;
    CommanderParams params;

    public ACommand(Werewolf plugin) {
        this.plugin = plugin;
        gameStateManager = plugin.getGameStateManager();
    }
}
