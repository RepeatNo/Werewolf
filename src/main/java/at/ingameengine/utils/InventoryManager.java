package at.ingameengine.utils;

import at.ingameengine.entities.InventoryNode;
import at.ingameengine.werewolf.Werewolf;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.Arrays;

public class InventoryManager {

    private Werewolf plugin;

    public InventoryManager(Werewolf plugin) {
        this.plugin = plugin;
    }

    public Inventory openSetupInventory() {
        Inventory inventory = plugin.getServer().createInventory(null, 9*5, "§eSetup");
        for (int i = 0; i < inventory.getSize(); i++) {
            inventory.setItem(i, new ItemManager().getItem(Material.GRAY_STAINED_GLASS_PANE, 1, "§a"));
        }

        inventory.setItem(getPosition(1,1), new ItemManager().getHead(
                "§eProfile §6» §c" + plugin.getGameProfile().getName(),
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2RjZGVlNmQwNmRmMjM0YjhlNjAzMzI4Yjk2YzU3ZjNhMzEyZTc5YWFiZmMzYmU3MmE4YjQyMTg3OGVkNjhjZiJ9fX0=",
                null));

        inventory.setItem(getPosition(3,3), new ItemManager().getItem(Material.COMPASS, 1, "§eBasics"));
        inventory.setItem(getPosition(3,5), new ItemManager().getItem(Material.MAGMA_CREAM, 1, "§eSpawns"));
        inventory.setItem(getPosition(3,7), new ItemManager().getItem(Material.BOOK, 1, "§eGame Profiles"));

        return inventory;
    }

    public Inventory openGameProfileInventory() {
        Inventory inventory = plugin.getServer().createInventory(null, 9*4, "§eGame Profiles");
        for (int i = 0; i < inventory.getSize(); i++) {
            inventory.setItem(i, new ItemManager().getItem(Material.GRAY_STAINED_GLASS_PANE, 1, "§a"));
        }

        inventory.setItem(getPosition(1,1), new ItemManager().getHead(
                "§eProfile §6» §c" + plugin.getGameProfile().getName(),
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2RjZGVlNmQwNmRmMjM0YjhlNjAzMzI4Yjk2YzU3ZjNhMzEyZTc5YWFiZmMzYmU3MmE4YjQyMTg3OGVkNjhjZiJ9fX0=",
                null));

        int number = 5;
        ArrayList<String> gameLore = new ArrayList<String>() {{
            add("§eRight Click to select");
        }};
        for (int row = 2; row < 4; row++) {
            for (int column = 2; column < 9; column++) {
                inventory.setItem(getPosition(row,column),
                        new ItemManager().getItem(Material.BOOK,
                                number,
                                "§6#" + number,
                                gameLore));
                number++;
            }
        }

        return inventory;
    }

    private Integer getPosition(Integer row, Integer column) {
        return (row-1)*9 + (column-1);
    }
}
