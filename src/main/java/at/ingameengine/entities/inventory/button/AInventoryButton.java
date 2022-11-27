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

    public AInventoryButton(Pair<Integer, Integer> position, ItemStack itemStack) {
        this.itemName = itemStack.getItemMeta().getDisplayName();
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

    public abstract void Execute(Werewolf plugin, InventoryClickEvent event);
}
