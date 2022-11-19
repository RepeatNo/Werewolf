package at.ingameengine.entities;

import org.bukkit.inventory.Inventory;

import java.util.ArrayList;

public class InventoryNode {

    private Inventory inventory;
    private int size;
    private InventoryNode parent;
    private ArrayList<InventoryNode> children;

    public InventoryNode(Inventory inventory, int size, InventoryNode parent, ArrayList<InventoryNode> children) {
        this.inventory = inventory;
        this.size = size;
        this.parent = parent;
        this.children = children;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public int getSize() {
        return size;
    }

    public InventoryNode getParent() {
        return parent;
    }

    public ArrayList<InventoryNode> getChildren() {
        return children;
    }

    public void addChild(InventoryNode child) {
        children.add(child);
    }
}
