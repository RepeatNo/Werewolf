package at.ingameengine.utils;

import at.ingameengine.entities.Vote;
import at.ingameengine.entities.inventory.InventoryNode;
import at.ingameengine.entities.inventory.button.*;
import at.ingameengine.entities.inventory.button.spawns.SetDaySpawnInvButton;
import at.ingameengine.entities.inventory.button.spawns.SetLobbySpawnInvButton;
import at.ingameengine.entities.inventory.button.spawns.SetNightSpawnInvButton;
import at.ingameengine.werewolf.Werewolf;
import org.bukkit.Material;
import org.javatuples.Pair;

import java.util.ArrayList;

public class InventoryFactory {

    Werewolf plugin;
    InventoryBuilder inventoryBuilder;

    public InventoryFactory(Werewolf plugin) {
        this.plugin = plugin;
        this.inventoryBuilder = plugin.getInventoryBuilder();
    }

    //region Inventories

    public InventoryNode setupInventoryNode() {
        Pair<Integer, Integer> size = new Pair<>(5, 9);
        ArrayList<AInventoryButton> invButtons = new ArrayList<AInventoryButton>() {
            {
                add(new PlaceholderInvButton(new Pair<>(1, 1), "§eProfile §6» §c" + plugin.getGameProfile().getName(),
                        new ItemManager().getInstance().getHead("§eProfile §6» §c" + plugin.getGameProfile().getName(),
                                plugin.getSkullManager().readString("setup.profile"), null)));
                add(new OpenInvButton(new Pair<>(3, 3),
                        new ItemManager().getInstance().getItem(Material.COMPASS, 1, "§eBasics")));
                add(new OpenInvButton(new Pair<>(3, 5),
                        new ItemManager().getInstance().getItem(Material.MAGMA_CREAM, 1, "§eSpawns")));
                add(new OpenInvButton(new Pair<>(3, 7),
                        new ItemManager().getInstance().getItem(Material.BOOK, 1, "§eGame Profiles")));
            }
        };

        return inventoryBuilder.buildInventoryNode("§eSetup", size, invButtons);
    }

    public InventoryNode gameProfileInventoryNode() {
        Pair<Integer, Integer> size = new Pair<>(4, 9);

        ArrayList<AInventoryButton> invButtons = new ArrayList<AInventoryButton>() {
            {
                add(new OpenParentInvButton(size));
                add(new OpenInvButton(new Pair<>(1, 1),
                        new ItemManager().getInstance().getHead("§eProfile §6» §c" + plugin.getGameProfile().getName(),
                                plugin.getSkullManager().readString("setup.profile"),
                                null)));
                add(new OpenInvButton(new Pair<>(3, 3),
                        new ItemManager().getInstance().getItem(Material.COMPASS, 1, "§eBasics")));

                int number = 5;
                for (int row = 2; row < 4; row++) {
                    for (int column = 2; column < 9; column++) {
                        add(new OpenInvButton(new Pair<>(row, column),
                                new ItemManager().getInstance().getItem(Material.BOOK, number, "§6#" + number)));
                        number++;
                    }
                }
            }
        };

        return inventoryBuilder.buildInventoryNode("§eGame Profiles", size, invButtons);
    }

    public InventoryNode spawnInventoryNode() {
        Pair<Integer, Integer> size = new Pair<>(3, 9);

        ArrayList<AInventoryButton> invButtons = new ArrayList<AInventoryButton>() {
            {
                add(new OpenInvButton(new Pair<>(2, 3),
                        new ItemManager().getInstance().getItem(Material.COMPASS, 1, "§eGeneral Spawns")));
                add(new OpenInvButton(new Pair<>(2, 7),
                        new ItemManager().getInstance().getSkullConfigHead("§ePlayer Spawns", "setup.wolf", null)));
                add(new OpenParentInvButton(size));
            }
        };

        return inventoryBuilder.buildInventoryNode("§eSpawns", size, invButtons);
    }

    public InventoryNode votingInventoryNode() {
        Pair<Integer, Integer> size = new Pair<>(6, 9);

        ArrayList<AInventoryButton> invButtons = new ArrayList<AInventoryButton>() {
            {
                int row = 1;
                int column = 1;
                for (Vote vote : plugin.getVotingManager().getVotes()) {
                    String playerName = vote.getTarget().getPlayer().getName();
                    add(new VotePlayerInvButton(new Pair<>(row, column), playerName,
                            new ItemManager().getInstance().getHead(vote.getTarget().getPlayer(), vote.getTarget().getPlayer().getName())));
                    add(new PlaceholderInvButton(new Pair<>(row, column + 1), "§eVotes: §6" + vote.getVotesCount(),
                            new ItemManager().getInstance().getHead("§e" + playerName + " - Votes: §6" + vote.getVotesCount(),
                                    plugin.getSkullManager().readString("vote." + vote.getVotesCount()), null)));
                    row++;

                    if (row > size.getValue0()) {
                        row = 1;
                        column += 3;
                    }
                }
            }
        };

        return inventoryBuilder.buildInventoryNode("§eVoting Menu", size, invButtons);
    }

    public InventoryNode generalSpawnInventoryNode() {
        Pair<Integer, Integer> size = new Pair<>(3, 9);

        ArrayList<AInventoryButton> invButtons = new ArrayList<AInventoryButton>() {
            {
                add(new SetLobbySpawnInvButton(new Pair<>(2, 3),
                        new ItemManager().getInstance().getItem(Material.NETHER_STAR, 1, "§cLobby Spawn")));
                add(new SetDaySpawnInvButton(new Pair<>(2, 5),
                        new ItemManager().getInstance().getSkullConfigHead("§cDay Spawn", "setup.sun", null)));
                add(new SetNightSpawnInvButton(new Pair<>(2, 7),
                        new ItemManager().getInstance().getSkullConfigHead("§cNight Spawn", "setup.moon", null)));
                add(new OpenParentInvButton(size));
            }
        };

        return inventoryBuilder.buildInventoryNode("§eGeneral Spawns", size, invButtons);
    }

    public InventoryNode getSetupRootNode() {
        InventoryNode setupRoot = setupInventoryNode();

        InventoryNode gameProfiles = gameProfileInventoryNode();
        setupRoot.addChild(gameProfiles);
        InventoryNode spawns = spawnInventoryNode();
        setupRoot.addChild(spawns);
        InventoryNode basics = gameProfileInventoryNode();
        setupRoot.addChild(basics);

        //region spawn nodes
        InventoryNode generalSpawns = generalSpawnInventoryNode();
        spawns.addChild(generalSpawns);
        //endregion

        return setupRoot;
    }

    //endregion
}
