package at.ingameengine.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class CommanderParams {
    private final CommandSender sender;
    private final Command command;
    private final String label;
    private final String[] args;

    public CommanderParams(CommandSender sender, Command command, String label, String[] args) {
        this.sender = sender;
        this.command = command;
        this.label = label;
        this.args = args;
    }

    public CommandSender getSender() {
        return sender;
    }

    public Command getCommand() {
        return command;
    }

    public String getLabel() {
        return label;
    }

    public String[] getArgs() {
        return args;
    }
}
