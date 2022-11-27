package at.ingameengine.entities.inventory.button;

import at.ingameengine.entities.Vote;
import at.ingameengine.utils.VotingManager;
import at.ingameengine.werewolf.Werewolf;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.javatuples.Pair;

public class VotePlayerInvButton extends AInventoryButton {
    public VotePlayerInvButton(Pair<Integer, Integer> position, String itemName, ItemStack itemStack) {
        super(position, itemName, itemStack);
    }

    @Override
    public void Execute(Werewolf plugin, InventoryClickEvent event) {
        VotingManager votingManager = plugin.getVotingManager();
        votingManager.addVote(plugin.getPlayer(event.getWhoClicked().getName()), plugin.getPlayer(event.getCurrentItem().getItemMeta().getDisplayName()));
        Bukkit.broadcastMessage("Vote added");
        for (Vote vote : votingManager.getVotes()) {
            Bukkit.broadcastMessage(vote.getVotesCount() + " " + vote.getTarget().getPlayer().getName());
        }
        event.getWhoClicked().closeInventory();
    }
}
