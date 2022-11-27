package at.ingameengine.entities.inventory.button.spawns;

import at.ingameengine.entities.inventory.button.AInventoryButton;
import at.ingameengine.werewolf.Werewolf;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.javatuples.Pair;

public class SetNightSpawnInvButton extends AInventoryButton {

    public SetNightSpawnInvButton(Pair<Integer, Integer> position, ItemStack itemStack) {
        super(position, itemStack);
    }

    @Override
    public void Execute(Werewolf plugin, InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        player.sendMessage("Spawnpoint set!");
        player.closeInventory();
        //Method to set the spawn
    }
}
