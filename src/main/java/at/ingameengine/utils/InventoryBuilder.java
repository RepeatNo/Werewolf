package at.ingameengine.utils;

import at.ingameengine.entities.inventory.button.AInventoryButton;
import at.ingameengine.werewolf.Werewolf;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;

public class InventoryBuilder {

    private final Werewolf plugin;

    public InventoryBuilder(Werewolf plugin) {
        this.plugin = plugin;
    }

    private Integer getPosition(Integer row, Integer column) {
        return (row - 1) * 9 + (column - 1);
    }

    public Inventory buildInventory(Integer size, String title, ArrayList<AInventoryButton> invButtons) {
        Inventory inventory = plugin.getServer().createInventory(null, size, title);

        for (int i = 0; i < inventory.getSize(); i++) {
            inventory.setItem(i, new ItemManager().getItem(Material.GRAY_STAINED_GLASS_PANE, 1, "§a"));
        }

        for (AInventoryButton invButton : invButtons) {
            inventory.setItem(getPosition(invButton.getPosition().getValue0(), invButton.getPosition().getValue1()), invButton.getItemStack());
        }

        return inventory;
    }
}
