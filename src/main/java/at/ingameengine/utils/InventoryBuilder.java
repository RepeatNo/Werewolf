package at.ingameengine.utils;

import at.ingameengine.werewolf.Werewolf;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.javatuples.Pair;

import java.util.HashMap;

public class InventoryBuilder {

    private final Werewolf plugin;

    public InventoryBuilder(Werewolf plugin) {
        this.plugin = plugin;
    }

    public static Boolean compareInventory(Inventory inventory, Inventory compareInventory) {
        if (inventory.getSize() != compareInventory.getSize()) {
            return false;
        }

        if (inventory.getType() != compareInventory.getType()) {
            return false;
        }

        for (int i = 0; i < inventory.getSize(); i++) {
            if (!inventory.getItem(i).getItemMeta().getDisplayName().equalsIgnoreCase(compareInventory.getItem(i).getItemMeta().getDisplayName())) {
                return false;
            }
        }

        return true;
    }

    public Inventory setupInventory() {
        HashMap<Pair<Integer, Integer>, ItemStack> items = new HashMap<Pair<Integer, Integer>, ItemStack>();
        items.put(
                new Pair<>(1, 1),
                new ItemManager().getHead("§eProfile §6» §c" + plugin.getGameProfile().getName(),
                        plugin.getSkullManager().readString("setup.profile"), null));
        items.put(new Pair<>(3, 3), new ItemManager().getItem(Material.COMPASS, 1, "§eBasics"));
        items.put(new Pair<>(3, 5), new ItemManager().getItem(Material.MAGMA_CREAM, 1, "§eSpawns"));
        items.put(new Pair<>(3, 7), new ItemManager().getItem(Material.BOOK, 1, "§eGame Profiles"));

        return buildInventory(9 * 5, "§eSetup", items);
    }

    public Inventory GameProfileInventory() {
        HashMap<Pair<Integer, Integer>, ItemStack> items = new HashMap<Pair<Integer, Integer>, ItemStack>();
        items.put(new Pair<>(3, 3), new ItemManager().getItem(Material.COMPASS, 1, "§eBasics"));
        items.put(new Pair<>(1, 1), new ItemManager().getHead(
                "§eProfile §6» §c" + plugin.getGameProfile().getName(),
                plugin.getSkullManager().readString("setup.profile"),
                null));

        int number = 5;
        for (int row = 2; row < 4; row++) {
            for (int column = 2; column < 9; column++) {
                items.put(new Pair<>(row, column),
                        new ItemManager().getItem(Material.BOOK,
                                number,
                                "§6#" + number));
                number++;
            }
        }

        return buildInventory(9 * 4, "§eGame Profiles", items);
    }

    public Inventory spawnsInventory() {
        HashMap<Pair<Integer, Integer>, ItemStack> items = new HashMap<Pair<Integer, Integer>, ItemStack>() {{
            put(new Pair<>(2, 3), new ItemManager().getItem(Material.NETHER_STAR, 1, "§eSet Spawn"));
            put(new Pair<>(2, 5), new ItemManager().getItem(Material.SPAWNER, 1, "§eSet Deine Mum"));
        }};

        return buildInventory(9 * 3, "§eSpawns", items);
    }

    private Integer getPosition(Integer row, Integer column) {
        return (row - 1) * 9 + (column - 1);
    }

    private Inventory buildInventory(Integer size, String title, HashMap<Pair<Integer, Integer>, ItemStack> items) {
        Inventory inventory = plugin.getServer().createInventory(null, size, title);

        for (int i = 0; i < inventory.getSize(); i++) {
            inventory.setItem(i, new ItemManager().getItem(Material.GRAY_STAINED_GLASS_PANE, 1, "§a"));
        }

        for (Pair<Integer, Integer> pair : items.keySet()) {
            inventory.setItem(getPosition(pair.getValue0(), pair.getValue1()), items.get(pair));
        }

        inventory.setItem(size - 1, new ItemManager().getItem(Material.BARRIER, 1, "§cBack"));

        return inventory;
    }
}
