package at.ingameengine.commands.implementations;

import at.ingameengine.gamestates.GameStateManager;
import at.ingameengine.gamestates.IGameStateVisitor;
import at.ingameengine.werewolf.Werewolf;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public abstract class ACommand implements CommandExecutor, IGameStateVisitor {

    Werewolf plugin;
    GameStateManager gameStateManager;

    CommandSender sender;
    Command command;
    String label;
    String[] args;

    public ACommand(Werewolf plugin) {
        this.plugin = plugin;
        gameStateManager = plugin.getGameStateManager();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        this.sender = sender;
        this.command = command;
        this.label = label;
        this.args = args;
        gameStateManager.getGameState().accept(this);
        return true;
    }
}
