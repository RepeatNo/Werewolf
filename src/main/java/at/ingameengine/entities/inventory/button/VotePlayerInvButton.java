package at.ingameengine.entities.inventory.button;

import at.ingameengine.werewolf.Werewolf;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.javatuples.Pair;

public class VotePlayerInvButton extends AInventoryButton {
    public VotePlayerInvButton(Pair<Integer, Integer> position, String itemName, ItemStack itemStack) {
        super(position, itemName, itemStack);
    }

    @Override
    public void Execute(Werewolf plugin, InventoryClickEvent event) {
        plugin.getGameStateManager().addVote(plugin.getPlayer(event.getWhoClicked().getName()), plugin.getPlayer(event.getCurrentItem().getItemMeta().getDisplayName()));
        event.getWhoClicked().closeInventory();
    }
}
