package at.ingameengine.entities.inventory.button;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.javatuples.Pair;

public class PlaceholderInvButton extends AInventoryButton {

    public PlaceholderInvButton(Pair<Integer, Integer> position, String itemName, ItemStack itemStack) {
        super(position, itemName, itemStack);
    }

    @Override
    public void Execute(InventoryClickEvent event) {
    }
}
