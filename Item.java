import java.util.Random;

public class Item {
    private String itemName;
    enum ItemType{
        MagicPotion,
        SpellBook,
        StrengthPotion,
        StrengthSpell,
        HealingSpell,
        FirstAidPotion
    }
    private ItemType itemType = ItemType.HealingSpell;

    /*constructor*/
    public Item(){
        generateItemType();
        switch (itemType) {
            case MagicPotion -> itemName = "Magic Potion (+1 mana point)";
            case SpellBook -> itemName = "Spell Book (+2 mana points)";
            case StrengthPotion -> itemName = "Strength Potion (+1 stamina point)";
            case StrengthSpell -> itemName = "Strength Spell (+2 stamina points)";
            case FirstAidPotion -> itemName = "First Aid Potion (+1 health point)";
            case HealingSpell -> itemName = "Healing Spell (+2 health points)";
            default -> {
            }
        }
    }

    /*getters*/
    public String getItemName(){
        return this.itemName;
    }
    public Item.ItemType getItemType(){
        return this.itemType;
    }

    /*methods*/
    public int increaseHealth(int healthPoints){
        healthPoints++;
        return healthPoints;
    }           //increases player's health by 1
    public int increaseStamina(int staminaPoints){
        staminaPoints++;
        return staminaPoints;
    }           //increases player's stamina by 1
    public int increaseMana(int manaPoints){
        manaPoints++;
        return manaPoints;
    }           //increases player's mana by 1

    /*random Item  generator:*/
    public void generateItemType(){
        ItemType[] itemTypes = ItemType.values();
        int itemTypesCount = itemTypes.length;
        int randomItemIndex = new Random().nextInt(itemTypesCount);
        this.itemType = itemTypes[randomItemIndex];
    }           //generates random Item type from ItemType enum
}
