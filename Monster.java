public class Monster extends GameCharacter {
    private final String[] monsters = {"Werewolf", "Mutant", "Skeleton"};
    private final String monsterType;

    /*getters*/
    public String getMonsterType(){
        return monsterType;
    }
    @Override
    public int getExperiencePoints() {
        return super.getExperiencePoints();
    }

    /*constructor*/
    public Monster(){
        setHealthPoints((int)(1 + Math.random() * 5));
        monsterType = generateMonsterType();
        setExperiencePoints((int) (1 + Math.random() * 7));
    }

    /*methods*/
    public String generateMonsterType(){
        int randomItemIndex = (int) (Math.random()*monsters.length);
        setName(monsters[randomItemIndex]);
        return monsters[randomItemIndex];
    }           //sets monsterType with a random String from monsters[]
}
