import java.util.ArrayList;
import java.util.Random;

public class GameCharacter {
    private String name;
    private int healthPoints;
    private int manaPoints;
    private int staminaPoints;
    private int experiencePoints = 0;
    private boolean isAlly = true;
    private boolean isFirstEncounter = true;
    private boolean isDefeated = false;
    private Item[] inventory = new Item[1];
    private final ArrayList<GameCharacter> allies = new ArrayList<>();
    private final ArrayList<Object> opponents = new ArrayList<>();
    private Object currentOpponent;
    private GameCharacter currentAlly;

    /*getters*/
    public ArrayList<Object> getOpponents() {
        return opponents;
    }
    public int getHealthPoints() {
        return healthPoints;
    }
    public int getManaPoints() {
        return manaPoints;
    }
    public int getStaminaPoints() {
        return staminaPoints;
    }
    public int getExperiencePoints() {
        return experiencePoints;
    }
    public String getName() {
        return name;
    }
    public Object getCurrentOpponent() {
        return currentOpponent;
    }
    public Item[] getInventory() {
        return inventory;
    }
    public ArrayList<GameCharacter> getAllies() {
        return allies;
    }
    public boolean getIsFirstEncounter(){
        return isFirstEncounter;
    }
    public boolean getIsDefeated() {
        return isDefeated;
    }

    /*setters*/
    public void setExperiencePoints(int experiencePoints) {
        this.experiencePoints = experiencePoints;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }
    public void setManaPoints(int manaPoints) {
        this.manaPoints = manaPoints;
    }
    public void setStaminaPoints(int staminaPoints) {
        this.staminaPoints = staminaPoints;
    }
    public void setCurrentOpponent(Object currentOpponent) {
        this.currentOpponent = currentOpponent;
    }
    public void setCurrentAlly(){
        if (!allies.isEmpty()) {
            this.currentAlly = allies.get(0);
        } else {
            System.out.println("You don't have anny allies.");
        }
    }
    public void setIsDefeatedTrue() {
        isDefeated = true;
    }

    public void addOpponent(Object opponent) {
        this.opponents.add(opponent);
    }
    public void addAlly(GameCharacter ally){
        this.allies.add(ally);
    }
    public void removeAlly(){
        this.allies.remove(0);
    }

    /*constructors*/
    public GameCharacter(int healthPoints, int manaPoints, int staminaPoints){
        this.healthPoints = healthPoints;
        this.manaPoints = manaPoints;
        this.staminaPoints = staminaPoints;
        inventory[0] = new Item();
    }
    public GameCharacter(){
        this.staminaPoints = (int) (1 + Math.random() * 15);
        this.manaPoints = (int) (1 + Math.random() * (15-staminaPoints));
        this.healthPoints = (int) (1 + Math.random() * (15-staminaPoints-manaPoints));
        int inventorySize = (int) (1 + Math.random() * 5);
        this.inventory = new Item[inventorySize];
        for (int i = 0; i < inventorySize; i++) {
            this.inventory[i] = new Item();
        }
        Random random = new Random();
        this.isAlly = random.nextBoolean();
        if (isAlly){
            this.name = "Ally";
        } else {
            this.name = "Witch";
        }
    }

    /*attack methods*/
    //attack functions decrease player's mana/stamina and opponent's health by 1. if opponent's health = 0 remove opponent from opponents ArrayList
    public void physicalAttackMonster(Monster opponent){
        if (opponent.getHealthPoints() > 1) {
            isFirstEncounter = false;
            this.staminaPoints -= 1;
            opponent.setHealthPoints(opponent.getHealthPoints() - 1);
            System.out.println("\n"+opponent.getName() + " was physically attacked. 💔");
        } else {
            opponent.setHealthPoints(opponent.getHealthPoints()-1);
            System.out.println("🔥The "+opponent.getName()+" was defeated!🔥");
            experiencePoints += opponent.getExperiencePoints();
            opponents.remove(opponent);
            isFirstEncounter = true;
            isDefeated = true;
            if (opponents.isEmpty()){
                System.out.println("""
                        Congratulations! You've managed to bring the stone to the tower!
                        Merlin has cast a protection spell, and all of the enemies were defeated
                        Game Over!"""
                );
                System.exit(0);
            } else {
                currentOpponent = opponents.get(0);
                System.out.println("Next opponent!");
            }
        }
    }
    public void magicalAttackMonster(Monster opponent){
        if (opponent.getHealthPoints() > 1) {
            isFirstEncounter = false;
            this.manaPoints-=1;
            opponent.setHealthPoints(opponent.getHealthPoints()-1);
            System.out.println("\nAn attack spell was cast on "+opponent.getName()+"🔥.");
        } else {
            opponent.setHealthPoints(opponent.getHealthPoints()-1);
            System.out.println("🔥The "+opponent.getName()+" was defeated!🔥");
            experiencePoints += opponent.getExperiencePoints();
            opponents.remove(opponent);
            isFirstEncounter = true;
            isDefeated = true;
            if (opponents.isEmpty()){
                System.out.println();
                System.out.println("""
                        ***Congratulations! You've managed to bring the stone to the tower!***
                        Merlin has cast a protection spell, and all of the enemies were defeated
                        Game Over!"""
                );
                System.exit(0);
            } else {
                currentOpponent = opponents.get(0);
                System.out.println("Next opponent!");
            }
        }
    }
    public void physicalAttackWitch(GameCharacter opponent){
        if (opponent.getHealthPoints() > 1) {
            isFirstEncounter = false;
            this.staminaPoints-=1;
            opponent.setHealthPoints(opponent.getHealthPoints()-1);
            System.out.println("\n"+opponent.getName()+" was physically attacked.💔");
        } else {
            opponent.setHealthPoints(opponent.getHealthPoints()-1);
            System.out.println("The "+opponent.getName()+" was defeated!🔥" );
            isFirstEncounter = true;
            isDefeated = true;
            if (opponent.getInventory().length>0) {
                System.out.println();
                for (int i = 0; i < 100; i++) {
                    System.out.print('-');
                }
                System.out.println("\nHere are the items you got: ");
                for (int i = 0; i < opponent.getInventory().length; i++) {
                    addItem(opponent.getInventory()[i]);
                    System.out.print(opponent.getInventory()[i].getItemName()+", ");
                }
                System.out.println();
                for (int i = 0; i < 100; i++) {
                    System.out.print('-');
                }
                System.out.println();
            } else {
                System.out.println("The Witch's inventory was empty, you didn't get any new Items.");
            }
            opponents.remove(opponent);
            if (opponents.isEmpty()) {
                System.out.println();
                System.out.println("""
                        ***Congratulations! You've managed to bring the stone to the tower!***
                        Merlin has cast a protection spell, and all of the enemies were defeated
                        Game Over!"""
                );
                System.exit(0);
            } else {
                currentOpponent = opponents.get(0);
                System.out.println("Next opponent!");
            }
        }
    }
    public void magicalAttackWitch(GameCharacter opponent){
        if (opponent.getHealthPoints() > 1) {
            isFirstEncounter = false;
            this.manaPoints-=1;
            opponent.setHealthPoints(opponent.getHealthPoints()-1);
            System.out.println("\nAn attack spell was cast on "+opponent.getName()+"🔥.");
        } else {
            opponent.setHealthPoints(opponent.getHealthPoints()-1);
            System.out.println("The "+opponent.getName()+" was defeated!🔥");
            isFirstEncounter = true;
            isDefeated = true;
            if (opponent.getInventory().length>0) {
                System.out.println();
                for (int i = 0; i < 100; i++) {
                    System.out.print('-');
                }
                System.out.println("\nHere are the items you got: ");
                for (int i = 0; i < opponent.getInventory().length; i++) {
                    addItem(opponent.getInventory()[i]);
                    System.out.print(opponent.getInventory()[i].getItemName()+", ");
                }
                System.out.println();
                for (int i = 0; i < 100; i++) {
                    System.out.print('-');
                }
                System.out.println();
            } else {
                System.out.println("The Witch's inventory was empty, you didn't get any new Items.");
            }
            opponents.remove(opponent);
            if (opponents.isEmpty()) {
                System.out.println();
                System.out.println("""
                        ***Congratulations! You've managed to bring the stone to the tower!***
                        Merlin has cast a protection spell, and all of the enemies were defeated
                        Game Over!"""
                );
                System.exit(0);
            } else {
                currentOpponent = opponents.get(0);
                System.out.println("Next opponent!");
            }
        }
    }

    /*interactions with the inventory*/
    public void removeItem(Item item){
        Item[] newInventory = new Item[inventory.length-1];
        int newInventoryIndex = 0;
        boolean isFound = false;
        for (Item value : inventory) {
            if (item == value && !isFound) {
                isFound = true;
                continue;
            }
            if (item != value || (item == value && isFound)) {
                newInventory[newInventoryIndex] = value;
                newInventoryIndex++;
            }
        }
        inventory = newInventory;
    }           //removes given item from the inventory
    public void addItem(Item item){
        Item[] newInventory = new Item[inventory.length+1];
        if (inventory.length - 1 >= 0) System.arraycopy(inventory, 0, newInventory, 0, inventory.length);
        newInventory[inventory.length] = item;
        inventory = newInventory;
    }           //adds given item to the inventory
    public void useItem(Item item){
        if (item == null){
            System.out.println("no item passed to use item");
        } else {
            switch (item.getItemType()) {
                case MagicPotion:
                    System.out.println("+1 mana point");
                    this.manaPoints = item.increaseMana(this.manaPoints);

                    break;
                case SpellBook:
                    System.out.println("+2 mana points");
                    this.manaPoints = item.increaseMana(this.manaPoints);
                    this.manaPoints = item.increaseMana(this.manaPoints);
                    break;
                case StrengthPotion:
                    System.out.println("+1 stamina point");
                    this.staminaPoints = item.increaseStamina(this.staminaPoints);
                    break;
                case StrengthSpell:
                    System.out.println("+2 stamina points");
                    this.staminaPoints = item.increaseStamina(this.staminaPoints);
                    this.staminaPoints = item.increaseStamina(this.staminaPoints);
                    break;
                case FirstAidPotion:
                    System.out.println("+1 health point");
                    this.healthPoints = item.increaseHealth(this.healthPoints);
                    break;
                case HealingSpell:
                    System.out.println("+2 health points");
                    this.healthPoints = item.increaseHealth(this.healthPoints);
                    this.healthPoints = item.increaseHealth(this.healthPoints);
                    break;
                default:
                    break;
            }
            removeItem(item);
        }
    }          //calls functions depending on the type of item used, and removes used item from the inventory
}
