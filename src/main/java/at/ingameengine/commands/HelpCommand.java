package at.ingameengine.commands;

import at.ingameengine.gamestates.*;
import at.ingameengine.werewolf.Werewolf;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class HelpCommand implements CommandExecutor, GameStateVisitor {

    Werewolf plugin;
    GameStateManager gameStateManager;
    CommanderParams params;

    public HelpCommand(Werewolf plugin) {
        this.plugin = plugin;
        gameStateManager = plugin.getGameStateManager();
    }

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        params = new CommanderParams(sender, command, label, args);
        gameStateManager.getGameState().accept(this);
        return true;
    }

    @Override
    public void visit(SetupState state) {
        params.getSender().sendMessage("Seas");
        Bukkit.broadcastMessage(params.getSender().getName() + " has executed the command " + params.getLabel());
        Bukkit.broadcastMessage("SetupState");
    }

    @Override
    public void visit(LobbyState state) {
        params.getSender().sendMessage("Seas");
        Bukkit.broadcastMessage(params.getSender().getName() + " has executed the command " + params.getLabel());
        Bukkit.broadcastMessage("LobbyState");
    }

    @Override
    public void visit(DayState state) {
        Bukkit.broadcastMessage("DayState");
    }

    @Override
    public void visit(NightState state) {
        Bukkit.broadcastMessage("NightState");
    }

    @Override
    public void visit(EndingState state) {
        Bukkit.broadcastMessage("EndingState");
    }
}
