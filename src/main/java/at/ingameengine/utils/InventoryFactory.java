package at.ingameengine.utils;

import at.ingameengine.entities.InventoryNode;
import at.ingameengine.werewolf.Werewolf;

public class InventoryFactory {

    Werewolf plugin;
    InventoryBuilder inventoryManager;

    public InventoryFactory(Werewolf plugin) {
        this.plugin = plugin;
        this.inventoryManager = new InventoryBuilder(plugin);
    }


    public InventoryNode getSetup() {
        InventoryNode setupRoot = new InventoryNode("§eSetup", inventoryManager.setupInventory());

        InventoryNode gameProfiles = new InventoryNode("§eGame Profiles", inventoryManager.GameProfileInventory());
        setupRoot.addChild(gameProfiles);
        InventoryNode spawns = new InventoryNode("§eSpawns", inventoryManager.spawnsInventory());
        setupRoot.addChild(spawns);
        InventoryNode basics = new InventoryNode("§eBasics", inventoryManager.GameProfileInventory());
        setupRoot.addChild(basics);

        return setupRoot;
    }

}
