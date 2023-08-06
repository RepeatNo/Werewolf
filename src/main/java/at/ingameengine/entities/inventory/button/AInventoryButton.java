package at.ingameengine.entities.inventory.button;

import at.ingameengine.entities.inventory.InventoryNode;
import at.ingameengine.werewolf.Werewolf;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.javatuples.Pair;

public abstract class AInventoryButton {
    protected final String itemName;
    protected final ItemStack itemStack;
    protected final Pair<Integer, Integer> position;

    protected InventoryNode node;
    protected final Werewolf plugin;

    public AInventoryButton(Pair<Integer, Integer> position, String itemName, ItemStack itemStack, Werewolf plugin) {
        this.itemName = itemName;
        this.itemStack = itemStack;
        this.position = position;
        this.plugin = plugin;
    }

    public void SetNode(InventoryNode node) {
        this.node = node;
    }

    public Pair<Integer, Integer> getPosition() {
        return position;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public abstract void Execute(InventoryClickEvent event);
}
