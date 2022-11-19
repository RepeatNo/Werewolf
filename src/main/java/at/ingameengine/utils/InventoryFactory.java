package at.ingameengine.utils;

import at.ingameengine.entities.InventoryNode;
import at.ingameengine.werewolf.Werewolf;

import java.util.ArrayList;
import java.util.HashMap;

public class InventoryFactory {

    Werewolf plugin;
    InventoryBuilder inventoryManager;

    public InventoryFactory(Werewolf plugin) {
        this.plugin = plugin;
        this.inventoryManager = new InventoryBuilder(plugin);
    }


    public InventoryNode getSetup(){
        HashMap<String, InventoryNode> setupChildren = new HashMap<String, InventoryNode>();
        setupChildren.put("§eGame Profiles",new InventoryNode(inventoryManager.GameProfileInventory(), null, null));

        InventoryNode setup = new InventoryNode(inventoryManager.setupInventory(),null, setupChildren);
        return setup;
    }

}
