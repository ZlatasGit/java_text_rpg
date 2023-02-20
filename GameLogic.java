import java.util.Scanner;

public class GameLogic {
    static Scanner scanner = new Scanner (System.in);
    private GameCharacter mainCharacter = new GameCharacter();
    private boolean isCurrentOpponentMonster;
    private Monster currentMonster;
    private GameCharacter currentWitch;

    /*methods for interacting with the console*/
    public static int readUserInput(String actionPrompt, int optionsAmount){
        int input;
        do {
            System.out.println(actionPrompt);
            try {
                input = Integer.parseInt (scanner. next ());
            } catch (Exception e){
                input = 0;
                System.out.println("Please enter a number!");
            }
            if (input>optionsAmount){
                System.out.println("There's no such option! Choose again:");
            }
        } while (input<1||input>optionsAmount);

        return input;
    }           //reads user input, if the input is not correct asks for a new input
    public static void printSeparator(int length){
        System.out.println();
        for (int i = 0; i < length; i++) {
            System.out.print('-');
        }
        System.out.println();
    }           //prints a line of dashes(-)
    public static void printHeading(String heading){
        printSeparator(30);
        System.out.print(heading);
        printSeparator(30);
    }           //prints given text between two separators
    public static void printLongHeading(String heading){
        printSeparator(95);
        System.out.print(heading);
        printSeparator(95);
    }           //prints given text between two long separators
    public static void clearConsole(){
        for (int i = 0; i < 100; i++) {
            System.out.println();
        }
    }           //prints 100 empty lines
    public static void continueTheGame(){
        System.out.println("\n*Enter anything to continue*" +
                "\n->");
        scanner.next();
    }           //continues to the next line of code after user's input
    public void printAttributes(){
        if (isCurrentOpponentMonster){
            printHeading("Your attributes:" +
                    "\n🤍: "+mainCharacter.getHealthPoints()+
                    ", 🔮: "+mainCharacter.getManaPoints()+
                    ", 💪🏻: "+mainCharacter.getStaminaPoints()+
                    "\n~~~~~~~~~~~~~~~~~~~"+
                    "\nOpponents left: " + mainCharacter.getOpponents().size()+
                    "\n~~~~~~~~~~~~~~~~~~~"+
                    "\n"+currentMonster.getMonsterType()+"'s attributes:" +
                    "\n🤍: "+currentMonster.getHealthPoints()
            );
            System.out.println();
        } else {
            printHeading("Your attributes:" +
                    "\n🤍: "+mainCharacter.getHealthPoints()+
                    ", 🔮: "+mainCharacter.getManaPoints()+
                    ", 💪🏻: "+mainCharacter.getStaminaPoints()+
                    "\n~~~~~~~~~~~~~~~~~~~"+
                    "\nOpponents left: " + mainCharacter.getOpponents().size()+
                    "\n~~~~~~~~~~~~~~~~~~~"+
                    "\n"+currentWitch.getName()+"'s attributes:" +
                    "\n🤍: "+currentWitch.getHealthPoints()
            );
            System.out.println();
        }
    }               //prints player's and opponent's attributes + the amount of opponents left

    /*methods for character manipulation*/
    public void createMainCharacter(){
        int attributePointsLeft = 15;
        int characterHealth;
        int characterStamina;
        int characterMagic;
        String name;
        printHeading("""
                Welcome to the game!
                Let's start by creating your Character!
                What will be your character's name?""");
        System.out.println("->");
        name = scanner.nextLine();
        clearConsole();
        printLongHeading("Hello, "+name+"!"
                +"\nNow let's choose your strengths and weaknesses:"
                +"\nYou are given 15 points to distribute between 3 character attributes."
                +"\nChoose wisely between Health, Magic Powers and Stamina.");
        System.out.println("\nType in the Health score you want to start with:");
        characterHealth = readUserInput("->",15);
        while (characterHealth>attributePointsLeft){
            clearConsole();
            printLongHeading("Oops, You can give up to 15 points to this attribute! Try again:");
            characterHealth = readUserInput("->",15);
        }
        clearConsole();
        attributePointsLeft-=characterHealth;
        printLongHeading("You have "+attributePointsLeft+" points left!");
        System.out.println("\nNow do the same for your Magic Powers:");
        characterMagic = readUserInput("->",15);
        while (characterMagic>attributePointsLeft){
            clearConsole();
            printLongHeading("Oops, You can give up to "+attributePointsLeft+" points to this attribute! Try again:");
            characterMagic = readUserInput("->",15);
        }
        clearConsole();
        attributePointsLeft-=characterMagic;
        printLongHeading("You have "+attributePointsLeft+" points left! "+" These will be your Stamina points.");
        characterStamina = attributePointsLeft;
        this.mainCharacter = new GameCharacter(characterHealth,characterMagic,characterStamina);
        this.mainCharacter.setName(name);
        continueTheGame();
        clearConsole();
    }           //creates main character for current game
    public void increaseAttributes(){
        int input = 0;
        while (input!=4) {
            printLongHeading("You have " + mainCharacter.getExperiencePoints() + " experience points available." +
                    "\nHere are the attributes boost options:"
            );
            System.out.println(
                    "\n(1) +5 health points [-5 experience points]" +
                    "\n(2) +5 magic points [-5 experience points]" +
                    "\n(3) +5 stamina points [-5 experience points]" +
                    "\n(4) Exit the store" + "\n");
            input = readUserInput("->", 4);
            switch (input) {
                case 1:
                    if (mainCharacter.getExperiencePoints() > 4) {
                        mainCharacter.setHealthPoints(mainCharacter.getHealthPoints() + 5);
                        mainCharacter.setExperiencePoints(mainCharacter.getExperiencePoints() - 5);
                        System.out.println("You got +5 health points");
                    } else {
                        clearConsole();
                        printLongHeading("You don't have enough experience points!");
                        continueTheGame();
                        clearConsole();
                        input = 4;
                    }
                    break;
                case 2:
                    if (mainCharacter.getExperiencePoints() > 4) {
                        mainCharacter.setManaPoints(mainCharacter.getManaPoints() + 5);
                        mainCharacter.setExperiencePoints(mainCharacter.getExperiencePoints() - 5);
                        System.out.println("You got +5 mana points");
                    } else {
                        clearConsole();
                        printLongHeading("You don't have enough experience points!");
                        continueTheGame();
                        clearConsole();
                        input = 4;
                    }
                    break;
                case 3:
                    if (mainCharacter.getExperiencePoints() > 4) {
                        mainCharacter.setStaminaPoints(mainCharacter.getStaminaPoints() + 5);
                        mainCharacter.setExperiencePoints(mainCharacter.getExperiencePoints() - 5);
                        System.out.println("You got +5 stamina points");
                    } else {
                        clearConsole();
                        printLongHeading("You don't have enough experience points!");
                        continueTheGame();
                        clearConsole();
                        input = 4;
                    }
                    break;
                case 4:
                    clearConsole();
                    break;
            }
        }
    }           //increases the chosen attributes if user has enough experience points
    public void getCurrentOpponentClass(){
        if (!mainCharacter.getOpponents().isEmpty()) {
            mainCharacter.setCurrentOpponent(mainCharacter.getOpponents().get(0));
            isCurrentOpponentMonster = mainCharacter.getCurrentOpponent().getClass() == Monster.class;
        }
    }           //sets isCurrentOpponentMonster=true if current opponent's class is Monster
    public void setCurrentOpponents(){
        getCurrentOpponentClass();
        if (isCurrentOpponentMonster){
            this.currentMonster = (Monster)(mainCharacter.getCurrentOpponent());
            this.currentWitch = new GameCharacter();
        }
        if (!isCurrentOpponentMonster){
            this.currentWitch = (GameCharacter)(mainCharacter.getCurrentOpponent());
            this.currentMonster = new Monster();
        }
    }           //depending on the class sets current opponent to currentWitch or currentMonster
    public void generateOpponents(int monstersAmount){
        int monstersCount = 0;
        while (monstersCount<monstersAmount){
            int opponentType = (int)(Math.random()*2 + 1);
            if (opponentType==1){
                Monster monster = new Monster();
                mainCharacter.addOpponent(monster);
                monstersCount++;
            } else {
                GameCharacter opponent = new GameCharacter();
                if (opponent.getName().equals("Witch")){
                    mainCharacter.addOpponent(opponent);
                } else {
                    mainCharacter.addAlly(opponent);
                }
            }
        }
        mainCharacter.setCurrentOpponent(mainCharacter.getOpponents().get(0));
        setCurrentOpponents();
    }           //generates an ArrayList of 5 monsters and a random amount of Witches + a list of random amount of allies

    /*methods for storytelling and game flow*/

    public void startStory(){
        printSeparator(130);
        System.out.print("Now that all of this is set, let me tell you more about your mission."
                +"\nThe Magic World is in great danger! Relentless monsters and dreadful witches bring terror to the people of the world."
                +"\nThe few protectors who survived cannot destroy evil powers on their own, and ask for your help."
                +"\nTo overcome the evil forces the Enhancing Stone has to be brought to the Merlin's Tower to cast a protection spell!"
                +"\nYour task is to deliver the stone straight to Merlin, and defeat every monster on your way to the Tower"
                +"\n"
                +"\nDuring your mission you'll encounter allies, and Witches."
                +"\nWitches have valuable Items, and fighting them is the only way to add new Items to your Inventory."
                +"\nHowever, you can choose not to fight the Witches, it is totally up to you."
                +"\nAllies will become your friends, and will help you fight your battles, you just need to call them!"
                +"\n" +
                "\nAt the beginning of your path you'll get one Magic Item in your inventory, that you can use at any time." +
                "\nDefeating a monster will give you experience points, that you can use to boost your health, mana or stamina." +
                "\n" +
                "\nNow you know everything about your mission! Farewell, "+mainCharacter.getName()+", and good luck!"
        );
        printSeparator(130);
    }           //prints the narrative of the game
    public void startGameLoop(){
        clearConsole();
        while (!mainCharacter.getOpponents().isEmpty()) {
            if (mainCharacter.getIsDefeated()){
                clearConsole();
                setCurrentOpponents();
            }
            if (mainCharacter.getIsFirstEncounter()) {
                if (isCurrentOpponentMonster) {
                    printLongHeading("🧟‍️ You encounter a " + currentMonster.getMonsterType() + " on your way, you have to defeat it! What would you do?");
                } else {
                    printLongHeading("🧙🏻 You encounter a " + currentWitch.getName() + " on your way. You can fight her to get new Items! What would you do?");
                }
            }
            chooseAction();
        }
    }           //starts the attack game loop
    public void startGame(){
        startStory();
        continueTheGame();
        clearConsole();
        generateOpponents(5);
        startGameLoop();
    }           //calls startStory and startGameLoop

    /*choice methods*/
    public Item chooseItem(){
        int index;
        printLongHeading("Select one of the Items from your Inventory:");
        System.out.println();
        for (int i = 0; i < mainCharacter.getInventory().length; i++) {
            int option = i+1;
            System.out.println("("+option+") "+mainCharacter.getInventory()[i].getItemName()+"");
        }
        index = readUserInput("->",mainCharacter.getInventory().length)-1;
        return mainCharacter.getInventory()[index];
    }           //returns the chosen item from the inventory
    public void chooseAction(){
        int input;
        printAttributes();
        System.out.println("""
                Choose your move:
                (1) Attack
                (2) Ignore the Witch
                (3) Use item from Inventory
                (4) Boost attributes
                (5) Exit the game"""
        );
        input = readUserInput("->",5);
        switch (input){
            case 1:
                clearConsole();
                attack();
                break;
            case 2:
                if (!isCurrentOpponentMonster){
                    clearConsole();
                    mainCharacter.getOpponents().remove(currentWitch);
                    mainCharacter.setIsDefeatedTrue();
                    setCurrentOpponents();
                    printHeading("The Witch was left behind.");
                } else {
                    clearConsole();
                    printLongHeading("You cannot run from a fight with a monster! Choose another action!");
                    continueTheGame();
                    clearConsole();
                }
                break;
            case 3:
                clearConsole();
                mainCharacter.useItem(chooseItem());
                continueTheGame();
                clearConsole();
                break;
            case 4:
                clearConsole();
                increaseAttributes(); break;
            case 5:
                clearConsole();
                printHeading("See you next time!");
                System.exit(0);
            default: break;
        }
    }           //calls action functions depending on user input
    public void attack(){
        setCurrentOpponents();
        clearConsole();
        printAttributes();
        System.out.println("""
                Choose the type of attack:
                (1) Physical attack [-1 stamina point]
                (2) Magical attack [-1 mana point]
                (3) Ask for Help. (You have"""+" "+mainCharacter.getAllies().size()+" allies)"
        );
        int input = readUserInput("->", 3);
        clearConsole();
        switch (input){
            case 1:
                if(mainCharacter.getStaminaPoints()>0){
                    printSeparator(40);
                    physicalAttack();
                    opponentsAttack();
                    continueTheGame();
                    clearConsole();
                } else if(mainCharacter.getStaminaPoints()==0&&mainCharacter.getManaPoints()==0) {
                    System.out.println("You don't have any mana or stamina left! You are being attacked by your opponent.");
                    continueTheGame();
                    opponentsAttack();
                } else if(mainCharacter.getStaminaPoints()==0&&mainCharacter.getManaPoints()>0){
                    System.out.println("You don't have enough Stamina! Choose another attack option!");
                    continueTheGame();
                }
                break;
            case 2:
                if(mainCharacter.getManaPoints()>0){
                    printSeparator(40);
                    magicalAttack();
                    opponentsAttack();
                    continueTheGame();
                    clearConsole();
                } else if(mainCharacter.getStaminaPoints()==0&&mainCharacter.getManaPoints()==0){
                    System.out.println("You don't have any mana or stamina left! You are being attacked by your opponent.");
                    continueTheGame();
                    opponentsAttack();
                } else if(mainCharacter.getStaminaPoints()>0&&mainCharacter.getManaPoints()==0){
                    System.out.println("You don't have enough Mana! Choose another attack option!");
                    continueTheGame();
                }
                break;
            case 3:
                if (mainCharacter.getAllies().isEmpty()){
                    System.out.println("""
                            You don't have any allies! You'll have to attack on your own.
                            Choose attack type:
                            (1) Physical attack [-1 stamina point]
                            (2) Magical attack [-1 mana point]"""
                    );
                    input = readUserInput("->", 2);
                    switch (input) {
                        case 1:
                            if (mainCharacter.getStaminaPoints() > 0) {
                                physicalAttack();
                                opponentsAttack();
                                continueTheGame();
                                clearConsole();
                                chooseAction();
                            } else {
                                System.out.println("You don't have enough Stamina!");
                                attack();
                            }
                            break;
                        case 2:
                            if (mainCharacter.getManaPoints() > 0) {
                                magicalAttack();
                                opponentsAttack();
                                chooseAction();
                            } else {
                                System.out.println("You don't have enough Mana!");
                                attack();
                            }
                            break;
                    }
                } else {
                    callForHelp();
                    break;
                }
            default: break;
        }
    }           //calls attack functions depending on user input
    public void physicalAttack(){
        if (mainCharacter.getHealthPoints()>0) {
            if (isCurrentOpponentMonster) {
                mainCharacter.physicalAttackMonster(currentMonster);
            } else {
                mainCharacter.physicalAttackWitch(currentWitch);
            }
        } else if (mainCharacter.getHealthPoints()==0){
            System.out.println("You were defeated. Try again!");
            System.exit(0);
        }
    }           //calls physical attack functions from GameCharacter depending on opponent's class
    public void magicalAttack(){
        setCurrentOpponents();
        if (mainCharacter.getHealthPoints()>0) {
            if (isCurrentOpponentMonster) {
                mainCharacter.magicalAttackMonster(currentMonster);
            } else {
                mainCharacter.magicalAttackWitch(currentWitch);
            }
        } else if (mainCharacter.getHealthPoints()==0){
            System.out.println("You were defeated. Try again!");
            System.exit(0);
        }
    }           //calls magical attack functions from GameCharacter depending on opponent's class
    public void opponentsAttack(){
        if (isCurrentOpponentMonster){
            monsterAttack();
        } else {
            witchAttack();
        }
    }           //calls opponent attack functions depending on opponent's class
    public void monsterAttack(){
        if (currentMonster.getHealthPoints()>0) {
            if (mainCharacter.getHealthPoints()>0) {
                mainCharacter.setHealthPoints(mainCharacter.getHealthPoints() - 1);
                System.out.println("\nYou were attacked by " + currentMonster.getName() + "!");
                printSeparator(40);
                printAttributes();
            } else {
                System.out.println("""

                                You were defeated💔
                                Game over""");
                System.exit(0);
            }
        } else {
            printSeparator(40);
        }
    }           //decreases player's health by one, if health = 0 ends the game
    public void witchAttack(){
        if (currentWitch.getHealthPoints()>0) {
            if (mainCharacter.getHealthPoints()>0) {
                mainCharacter.setHealthPoints(mainCharacter.getHealthPoints() - 1);
                System.out.println("\nYou were attacked by " + currentWitch.getName() + "!");
                printSeparator(40);
                printAttributes();
            } else {
                System.out.println("""

                        You were defeated💔
                        Game over""");
                System.exit(0);
            }
        } else {
            printSeparator(40);
        }
    }           //decreases player's health by one, if health = 0 ends the game
    public void allyMagicAttack(){
        GameCharacter ally = mainCharacter.getAllies().get(0);
        if((ally.getHealthPoints()>0)&&(ally.getManaPoints()>0)){
            if (isCurrentOpponentMonster) {
                System.out.println("""

                        *Ally Attack!*
                        """);
                mainCharacter.setManaPoints(mainCharacter.getManaPoints()+1);
                mainCharacter.magicalAttackMonster(currentMonster);
                ally.setManaPoints(ally.getManaPoints()-1);
                continueTheGame();
                clearConsole();
            } else {
                System.out.println("""

                        *Ally Attack!*
                        """);
                mainCharacter.setManaPoints(mainCharacter.getManaPoints()+1);
                mainCharacter.magicalAttackWitch(currentWitch);
                ally.setManaPoints(ally.getManaPoints()-1);
                continueTheGame();
                clearConsole();
            }
        } else {
            System.out.println("Your ally has been defeated.");
            mainCharacter.removeAlly();
            mainCharacter.setCurrentAlly();
        }
    }           //calls magic attack functions depending on opponent's class, and decreases ally's mana by 1
    public void allyPhysicalAttack(){
        GameCharacter ally = mainCharacter.getAllies().get(0);
        if((ally.getHealthPoints()>0)&&(ally.getStaminaPoints()>0)){
            if (isCurrentOpponentMonster) {
                printHeading("Ally Attack!");
                mainCharacter.setStaminaPoints(mainCharacter.getStaminaPoints()+1);
                mainCharacter.physicalAttackMonster(currentMonster);
                ally.setStaminaPoints(ally.getStaminaPoints()-1);
                continueTheGame();
                clearConsole();
            } else {
                printHeading("Ally Attack!");
                mainCharacter.setStaminaPoints(mainCharacter.getStaminaPoints()+1);
                mainCharacter.physicalAttackWitch(currentWitch);
                ally.setStaminaPoints(ally.getStaminaPoints()-1);
                continueTheGame();
                clearConsole();
            }
        } else {
            System.out.println("Your ally has been defeated.");
            mainCharacter.removeAlly();
            mainCharacter.setCurrentAlly();
        }
    }           //calls physical attack functions depending on opponent's class, and decreases ally's stamina by 1
    public void callForHelp(){
        GameCharacter ally = mainCharacter.getAllies().get(0);
        if (ally.getStaminaPoints()>0){
            allyPhysicalAttack();
        } else if (ally.getManaPoints()>0){
            allyMagicAttack();
        } else {
            printLongHeading("Your ally has been defeated.");
            mainCharacter.removeAlly();
            mainCharacter.setCurrentAlly();
        }
    }           //checks if there are allies left and calls ally attack functions

    public GameLogic(){
        clearConsole();
        createMainCharacter();
        setCurrentOpponents();
        startGame();
    }
    public static void main(String[] args) {
        GameLogic gl = new GameLogic();
    }
}