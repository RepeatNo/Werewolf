package at.ingameengine.utils;

import at.ingameengine.entities.WerewolfPlayer;
import at.ingameengine.entities.inventory.InventoryNode;
import at.ingameengine.entities.inventory.button.*;
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
        Pair<Integer, Integer> size = new Pair<>(6, 9);
        ArrayList<AInventoryButton> invButtons = new ArrayList<AInventoryButton>() {
            {
                add(new PlaceholderInvButton(new Pair<>(1, 1), "§eProfile §6» §c" + plugin.getGameProfile().getName(),
                        new ItemManager().getHead("§eProfile §6» §c" + plugin.getGameProfile().getName(),
                                plugin.getSkullManager().readString("setup.profile"), null)));
                add(new OpenInvButton(new Pair<>(3, 3), "§eBasics",
                        new ItemManager().getItem(Material.COMPASS, 1, "§eBasics")));
                add(new OpenInvButton(new Pair<>(3, 5), "§eSpawns",
                        new ItemManager().getItem(Material.MAGMA_CREAM, 1, "§eSpawns")));
                add(new OpenInvButton(new Pair<>(3, 7), "§eSetup",
                        new ItemManager().getItem(Material.BOOK, 1, "§eGame Profiles")));
            }
        };

        return inventoryBuilder.buildInventoryNode("§eSetup", size, invButtons);
    }

    public InventoryNode gameProfileInventoryNode() {
        Pair<Integer, Integer> size = new Pair<>(4, 9);

        ArrayList<AInventoryButton> invButtons = new ArrayList<AInventoryButton>() {
            {
                add(new OpenParentInvButton(size));
                add(new OpenInvButton(new Pair<>(1, 1), "§eProfile §6» §c" + plugin.getGameProfile().getName(),
                        new ItemManager().getHead("§eProfile §6» §c" + plugin.getGameProfile().getName(),
                                plugin.getSkullManager().readString("setup.profile"),
                                null)));
                add(new OpenInvButton(new Pair<>(3, 3), "§eBasics",
                        new ItemManager().getItem(Material.COMPASS, 1, "§eBasics")));

                int number = 5;
                for (int row = 2; row < 4; row++) {
                    for (int column = 2; column < 9; column++) {
                        add(new OpenInvButton(new Pair<>(row, column), "§6#" + number,
                                new ItemManager().getItem(Material.BOOK, number, "§6#" + number)));
                        number++;
                    }
                }
            }
        };

        return inventoryBuilder.buildInventoryNode("§eGame Profiles", size, invButtons);
    }

    public InventoryNode spawnsInventoryNode() {
        Pair<Integer, Integer> size = new Pair<>(3, 9);

        ArrayList<AInventoryButton> invButtons = new ArrayList<AInventoryButton>() {
            {
                add(new OpenInvButton(new Pair<>(2, 3), "§eSet Spawn",
                        new ItemManager().getItem(Material.NETHER_STAR, 1, "§eSet Spawn")));
                add(new OpenInvButton(new Pair<>(2, 5), "§eSet Deine Mum",
                        new ItemManager().getItem(Material.SPAWNER, 1, "§eSet Deine Mum")));
            }
        };

        return inventoryBuilder.buildInventoryNode("§eSpawns", size, invButtons);
    }

    public InventoryNode votingInventoryNode() {
        Pair<Integer, Integer> size = new Pair<>(5, 9);

        ArrayList<AInventoryButton> invButtons = new ArrayList<AInventoryButton>() {
            {
                Integer row = 1;
                for (int i = 0; i < plugin.getPlayers().size(); i++) {
                    WerewolfPlayer player = plugin.getPlayers().get(i);
                    add(new VotePlayerInvButton(new Pair<>(row, i + 1 - ((row - 1) * 7)),
                            "§eVote for " + player.getPlayer().getName(),
                            new ItemManager().getHead(player.getPlayer(), "§eVote for " + player.getPlayer().getName())));
                    if (i % 7 == 0) {
                        row++;
                    }
                }
            }
        };

        return inventoryBuilder.buildInventoryNode("§eVoting Menu", size, invButtons);
    }

    //endregion

    //region Inventory Nodes

    public InventoryNode getSetupRootNode() {
        InventoryNode setupRoot = setupInventoryNode();

        InventoryNode gameProfiles = gameProfileInventoryNode();
        setupRoot.addChild(gameProfiles);
        InventoryNode spawns = spawnsInventoryNode();
        setupRoot.addChild(spawns);
        InventoryNode basics = gameProfileInventoryNode();
        setupRoot.addChild(basics);

        return setupRoot;
    }

    //endregion
}
