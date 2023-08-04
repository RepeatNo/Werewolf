package at.ingameengine.werewolf;

import at.ingameengine.commands.implementations.ConfigCommand;
import at.ingameengine.commands.implementations.TestCommand;
import at.ingameengine.entities.GameProfile;
import at.ingameengine.entities.WerewolfPlayer;
import at.ingameengine.gamestates.AGameState;
import at.ingameengine.gamestates.GameStateManager;
import at.ingameengine.listeners.*;
import at.ingameengine.utils.*;
import org.bukkit.GameRule;
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
    private ExperienceBarManager experienceBarManager;
    private ActionbarManager actionbarManager;

    private FileManager configManager;
    private FileManager messageManager;
    private FileManager gameProfileManager;
    private FileManager skullManager;
    private FileManager locationsManager;

    @Override
    public void onEnable() {
        players = new ArrayList<>();

        gameStateManager = new GameStateManager(this);
        gameStateManager.setGameState(AGameState.SETUP_STATE);
        roleManager = new RoleManager(this);
        votingManager = new VotingManager(this);
        experienceBarManager = new ExperienceBarManager(this);
        actionbarManager = new ActionbarManager(this);

        //region Configs
        configManager = new FileManager(this, "config.yml");
        messageManager = new FileManager(this, "messages.yml");
        gameProfileManager = new FileManager(this, "game-profiles.yml");
        skullManager = new FileManager(this, "skulls.yml");
        locationsManager = new FileManager(this, "locations.yml");

        //endregion

        inventoryBuilder = new InventoryBuilder(this);
        inventoryFactory = new InventoryFactory(this);
        gameProfile = new GameProfile(this, configManager.readString("game-profile"));

        //region ConfigElements

        prefix = messageManager.readString("prefix");

        //endregion

        //region Commands

        Objects.requireNonNull(this.getCommand("test")).setExecutor(new TestCommand(this));
        Objects.requireNonNull(this.getCommand("config")).setExecutor(new ConfigCommand(this));

        //endregion

        //region Listeners

        getServer().getPluginManager().registerEvents(new JoinListener(this), this);
        getServer().getPluginManager().registerEvents(new QuitListener(this), this);
        getServer().getPluginManager().registerEvents(new BlockBreakListener(this), this);
        getServer().getPluginManager().registerEvents(new InventoryClickListener(this), this);
        getServer().getPluginManager().registerEvents(new DropItemListener(this), this);
        getServer().getPluginManager().registerEvents(new InteractListener(this), this);
        getServer().getPluginManager().registerEvents(new CancelledListeners(this), this);
        getServer().getPluginManager().registerEvents(new WeatherChangeListener(), this);

        //endregion

        //region GameRules

        getServer().getWorlds().get(0).setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false);
        getServer().getWorlds().get(0).setGameRule(GameRule.COMMAND_BLOCK_OUTPUT, false);
        getServer().getWorlds().get(0).setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);

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

    public ExperienceBarManager getExpBarManager() {
        return experienceBarManager;
    }
    public ActionbarManager getActionbarManager() {
        return actionbarManager;
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

    public FileManager getLocationsManager() {
        return locationsManager;
    }

    public ArrayList<WerewolfPlayer> getPlayers() {
        return players;
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
