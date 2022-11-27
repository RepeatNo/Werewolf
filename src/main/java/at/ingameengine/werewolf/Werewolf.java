package at.ingameengine.werewolf;

import at.ingameengine.commands.implementations.TestCommand;
import at.ingameengine.commands.implementations.VoteCommand;
import at.ingameengine.entities.GameProfile;
import at.ingameengine.entities.WerewolfPlayer;
import at.ingameengine.gamestates.AGameState;
import at.ingameengine.gamestates.GameStateManager;
import at.ingameengine.listeners.*;
import at.ingameengine.role.RoleManager;
import at.ingameengine.utils.*;
import org.bukkit.GameRule;
import org.bukkit.entity.Item;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Objects;

public class Werewolf extends JavaPlugin {

    public static String prefix;
    private ArrayList<WerewolfPlayer> players;

    private GameProfile gameProfile;
    private InventoryFactory inventoryFactory;
    private InventoryBuilder inventoryBuilder;

    private VotingManager votingManager;
    private GameStateManager gameStateManager;
    private RoleManager roleManager;
    private FileManager configManager;
    private FileManager messageManager;
    private FileManager gameProfileManager;
    private FileManager skullManager;

    @Override
    public void onEnable() {
        players = new ArrayList<>();

        gameStateManager = new GameStateManager(this);
        gameStateManager.setGameState(AGameState.SETUP_STATE);
        roleManager = new RoleManager(this);
        votingManager = new VotingManager(this);

        //region Configs
        configManager = new FileManager(this, "config.yml");
        messageManager = new FileManager(this, "messages.yml");
        gameProfileManager = new FileManager(this, "game-profiles.yml");
        skullManager = new FileManager(this, "skulls.yml");

        //endregion

        inventoryBuilder = new InventoryBuilder(this);
        inventoryFactory = new InventoryFactory(this);
        gameProfile = new GameProfile(this, configManager.readString("game-profile"));

        //region ConfigElements

        prefix = messageManager.readString("prefix");

        //endregion

        //region Commands

        Objects.requireNonNull(this.getCommand("test")).setExecutor(new TestCommand(this));
        Objects.requireNonNull(this.getCommand("vote")).setExecutor(new VoteCommand(this));

        //endregion

        //region Listeners

        getServer().getPluginManager().registerEvents(new JoinListener(this), this);
        getServer().getPluginManager().registerEvents(new QuitListener(this), this);
        getServer().getPluginManager().registerEvents(new BlockBreakListener(this), this);
        getServer().getPluginManager().registerEvents(new InventoryClickListener(this), this);
        getServer().getPluginManager().registerEvents(new DropItemListener(this), this);
        getServer().getPluginManager().registerEvents(new InteractListener(this), this);
        getServer().getPluginManager().registerEvents(new CancelledListeners(this), this);

        //endregion

        //region GameRules

        getServer().getWorlds().get(0).setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false);

        //endregion

        //region ItemManager
        ItemManager itemManager = new ItemManager().getInstance();
        itemManager.setPlugin(this);
        //endregion
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public GameStateManager getGameStateManager() {
        return gameStateManager;
    }

    public RoleManager getRoleManager() {
        return roleManager;
    }

    public FileManager getConfigManager() {
        return configManager;
    }

    public FileManager getSkullManager() {
        return skullManager;
    }

    public FileManager getGameProfileManager() {
        return gameProfileManager;
    }

    public FileManager getMessageManager() {
        return messageManager;
    }

    public ArrayList<WerewolfPlayer> getPlayers() {
        return players;
    }

    public WerewolfPlayer getPlayer(String name) {
        for (WerewolfPlayer player : players) {
            if (player.getPlayer().getName().equals(name)) {
                return player;
            }
        }
        return null;
    }

    public void addPlayer(WerewolfPlayer player) {
        this.players.add(player);
    }

    public void removePlayer(WerewolfPlayer player) {
        this.players.remove(player);
    }

    public GameProfile getGameProfile() {
        return gameProfile;
    }

    public InventoryFactory getInventoryFactory() {
        return inventoryFactory;
    }

    public InventoryBuilder getInventoryBuilder() {
        return inventoryBuilder;
    }

    public VotingManager getVotingManager() {
        return votingManager;
    }
}
