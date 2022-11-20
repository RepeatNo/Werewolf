package at.ingameengine.entities.inventory;

public abstract class AInventoryButton {
    private final String itemName;
    private final Integer row;
    private final Integer column;

    InventoryNode rootNode;

    public AInventoryButton(String itemName, Integer row, Integer column) {
        this.itemName = itemName;
        this.row = row;
        this.column = column;
    }

    public abstract void Execute();

    public abstract void Execute(InventoryNode rootNode);
}
