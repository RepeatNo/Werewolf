package at.ingameengine.entities.inventory.button;

import at.ingameengine.entities.inventory.InventoryNode;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.javatuples.Pair;

public abstract class AInventoryButton {
    protected final String itemName;
    protected final ItemStack itemStack;
    protected final Pair<Integer, Integer> position;

    protected InventoryNode node;

    public AInventoryButton(Pair<Integer, Integer> position, String itemName, ItemStack itemStack) {
        this.itemName = itemName;
        this.itemStack = itemStack;
        this.position = position;
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
