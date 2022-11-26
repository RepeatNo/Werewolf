package at.ingameengine.entities.roles;

public class Witch extends ARole {
    private int healingPotionAmount;
    private int killPotionAmount;

    @Override
    public void executeSetup() {
        healingPotionAmount = 1;
        killPotionAmount = 1;
    }

    @Override
    public void executeNight() {
        // select one player to heal
        // select one player to kill
    }

    @Override
    public void onDeath() {

    }

    public Integer useHealingPotion() {
        if (healingPotionAmount > 0) {
            healingPotionAmount--;
            return 1;
        }
        return 0;
    }

    public Integer useKillPotion() {
        if (killPotionAmount > 0) {
            killPotionAmount--;
            return 1;
        }
        return 0;
    }
}
