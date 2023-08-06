package at.ingameengine.entities.inventory.button;

import at.ingameengine.werewolf.Werewolf;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.javatuples.Pair;

public class VotePlayerInvButton extends AInventoryButton {
    public VotePlayerInvButton(Pair<Integer, Integer> position, String itemName, ItemStack itemStack, Werewolf plugin) {
        super(position, itemName, itemStack, plugin);
    }

    @Override
    public void Execute(InventoryClickEvent event) {
    }
}
