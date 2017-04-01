package edu.chemeketa.zombieDice;

import java.util.ArrayList;
import java.util.Vector;

/**
 * File name: ZombieDiceGame.java
 *
 * Programmer: Alexander Molodyh Chemeketa Community College Class CIS234J
 * Created: May 20, 2015 9:39:37 PM Assignment: CIS234J Final Project
 */
/**
 * Class Name: ZombieDiceGame.java
 *
 * Description: This class is a zombie dice game. It sets all the dice in the
 * proper places. It sets the booleans for the player class. It finds out which
 * dice is currently in the list and rolls that dice. then it gets the dice
 * image and sets it in the ZombieGameFinal class. It also determines who is the
 * winner and displays the appropriate screen. If a Player is shotgunned then a
 * screen is displayed telling the player that he/she is shotgunned.
 *
 * Example:
 *
 * <pre> ZombieDiceGame zGame = new ZombieDiceGame();
 * 		zGame.beggin();<pre>
 *
 * This will start the ZombieDiceGame game.
 *
 * An example of using it with a javaFX GUI is:
 *
 * <pre>ZombieDiceGame zGame = new ZombieDiceGame(this);
 * 		zGame.beggin();
 *
 */
/**
 *
 */
public class ZombieDiceGame
{

    /*
     * These are the green dice.
     */
    private GreenDie zombieDie_1_1;
    private GreenDie zombieDie_1_2;
    private GreenDie zombieDie_1_3;
    private GreenDie zombieDie_1_4;
    private GreenDie zombieDie_1_5;
    private GreenDie zombieDie_1_6;

    /*
     * A dice list for the green dice.
     */
    private ArrayList<String> greenList;

    /*
     * These are the yellow dice.
     */
    private YellowDie zombieDie_2_1;
    private YellowDie zombieDie_2_2;
    private YellowDie zombieDie_2_3;
    private YellowDie zombieDie_2_4;

    /*
     * A dice list for the yellow dice.
     */
    private ArrayList<String> yellowList;

    /*
     * These are the red dice.
     */
    private RedDie zombieDie_3_1;
    private RedDie zombieDie_3_2;
    private RedDie zombieDie_3_3;

    /*
     * A dice list for the red dice.
     */
    private ArrayList<String> redList;

    /*
     * These are the objects of the green, yellow, and red dice.
     */
    private GreenDie gDie;
    private YellowDie yDie;
    private RedDie rDie;

    /*
     * These are the trackers for the brain and shotgun dice. They determine
     * which ImageView to place the die.
     */
    private int currentBrainSpot = 0;
    private int currentShotSpot = 0;

    /*
     * This is the limit of how much shot the game can take before being
     * shotgunned.
     */
    private final int MAX_SHOTS = 3;

    /*
     * The dieScore takes in the current die face value.
     */
    private int dieScore;

    /*
     * The dieColor takes in the die color number.
     */
    private int dieColor = 0;

    /*
     * These are the Players in the game.
     */
    Player firstPlayer;
    Player currentPlayer;
    Player secondPlayer;

    /*
     * An object of the ZombieCup class.
     */
    ZombieCup zombieCup;

    /*
     * An object of the ZombieSounds class.
     */
    ZombieSounds zSounds;

    /*
     * An ArrayList for the three dice from the ZombieCup class.
     */
    ArrayList<String> threeDice;

    /*
     * An object of the ZombieDiceFinal class.
     */
    ZombieDiceFinal zombieGUI;

    /**
     * An empty constructor for the ZombieDiceGame.
     */
    public ZombieDiceGame()
    {

    }

    /**
     * The second constructor that takes in an instance of the ZombieDiceFianl
     * class. It passes in zFinal in to the zombieGUI object. The players are
     * instantiated, then the currentPlayer takes in the firstPlayer. threeDice
     * ArrayList is instantiated. zombieCup is instantiated and it calls the
     * loadDice method.
     *
     * @param zFinal takes in an instance of the ZombieDiceFinal class. This way
     * we can use its Nodes in this class.
     */
    public ZombieDiceGame(ZombieDiceFinal zFinal)
    {
        /*
         * Create an object of ZombieDiceFinal.
         */
        zombieGUI = zFinal;

        /*
         * Instantiate Players.
         */
        firstPlayer = new Player();
        currentPlayer = new Player();
        secondPlayer = new Player();

        /*
         * CurrentPlayer now equals firstPlayer.
         */
        currentPlayer = firstPlayer;

        threeDice = new ArrayList();

        zombieCup = new ZombieCup();
        loadDice();// Load dice
    }

    /**
     * This method instantiates the dice. Then it adds all the dice in to the
     * appropriate dice lists.
     */
    public void loadDice()
    {
        /*
         * Instantiate the dice lists.
         */
        greenList = new ArrayList<String>();
        yellowList = new ArrayList<String>();
        redList = new ArrayList<String>();

        /*
         * These dice are used in the placeDice method.
         */
        gDie = new GreenDie();
        yDie = new YellowDie();
        rDie = new RedDie();

        /*
         * Create 6 green dice.
         */
        zombieDie_1_1 = new GreenDie();
        zombieDie_1_2 = new GreenDie();
        zombieDie_1_3 = new GreenDie();
        zombieDie_1_4 = new GreenDie();
        zombieDie_1_5 = new GreenDie();
        zombieDie_1_6 = new GreenDie();

        /*
         * Create 4 yellow dice.
         */
        zombieDie_2_1 = new YellowDie();
        zombieDie_2_2 = new YellowDie();
        zombieDie_2_3 = new YellowDie();
        zombieDie_2_4 = new YellowDie();

        /*
         * Create 3 red dice.
         */
        zombieDie_3_1 = new RedDie();
        zombieDie_3_2 = new RedDie();
        zombieDie_3_3 = new RedDie();

        /*
         * Load the green dice list.
         */
        for (int g = 1; g < 7; g++)
        {
            greenList.add("zombieDie_1_" + g);
        }

        /*
         * Load the yellow dice list.
         */
        for (int y = 1; y < 5; y++)
        {
            yellowList.add("zombieDie_2_" + y);
        }

        /*
         * Load the red dice list.
         */
        for (int r = 1; r < 4; r++)
        {
            redList.add("zombieDie_3_" + r);
        }
    }

    /**
     * This method get the green die list.
     *
     * @return The green dice ArrayList.
     */
    public ArrayList<String> getGreenList()
    {
        return greenList;
    }

    /**
     * This method starts the Zombie Dice game. First the cup rolls the dice
     * then the play method is called.
     */
    public void beggin()
    {
        zombieCup.rollCup();
        play();
    }

    /**
     * This method sets the three dice from the zombie cup in to the threeDice
     * ArrayList. Then the three dice are passed in to the placeDice method to
     * be placed in to the ImageViews in the ZombieDiceFinal GUI.
     */
    public void play()
    {
        zombieGUI.shakeNode(zombieGUI.getCup());
        threeDice = zombieCup.getDieList();
        placeDice(threeDice);
    }

    /**
     * This method checks the String passed in as a parameter. What it is
     * looking for is the 17th character. The character should be a number and
     * then it tries to create an integer from that character. the integer is
     * then returned and it represents a color of a die.
     *
     * @param aList takes in a String that is a dice face value.
     *
     * @return The dieColor in an integer which represents the die color.
     */
    public int checkDieColor(String aList)
    {
        String stringIndex = aList;
        int dieColor = Integer.parseInt(stringIndex.substring(17, 18));

        return dieColor;
    }

    /**
     * This method checks the String passed in as a parameter. What it is
     * looking for is the 19th character. The character should be a number and
     * then it tries to create an integer from that character. the integer is
     * then returned and it represents a type of a die, like a brain, feet, or
     * shotgun.
     *
     * @param cList takes in a String that is a dice face value.
     *
     * @return The die type in an integer form that represents the die as a
     * brain, feet, or shotgun.
     */
    public int checkDieType(String cList)
    {
        String stringIndex = cList;
        int dieType = Integer.parseInt(stringIndex.substring(19, 20));

        return dieType;
    }

    /**
     * This method takes in the ArrayList of threeDice. Then it check each die
     * for its color and then rolls the appropriate color of die. Then it sets
     * the value of the of that die to the ImageView in the ZombieGameFinal GUI.
     * At the end the method checks if the player has been shotgunned.
     *
     * @param list takes in the list of three dice rolled by the zombie cup.
     */
    private void placeDice(ArrayList<String> list)
    {
        /*
         * Check the dice list with a for loop.
         */
        for (int i = 0; i < list.size(); i++)
        {

            /*
             * This String is used to get the current die name.
             */
            String returningDie = "zombieDie_" + checkDieColor(list.get(i))
                    + "_" + checkDieType(list.get(i));

            /*
             * If the current die is green. Then zombieCup sets the green die
             * list. Then it sets the name of the die, it then rolls the die.
             * dieScore then takes in the die face value, dieColor is set to 1
             * to represent it self as a green die. The score is then placed
             * with the dieColor and dieScore passed in to it as parameters.
             * zombieGUI then sets the dice to the appropriate ImageViews.
             */
            if (checkDieColor(list.get(i)) == 1)
            {
                zombieCup.setGreenDieList(greenList);
                gDie.setDieName(returningDie);
                gDie.rollDie();
                dieScore = gDie.getDieValue();
                dieColor = 1;
                placeScore(dieScore, gDie.getImageFaceValue());
                zombieGUI.setADie(gDie.getImageFaceValue(), i);
            }

            /*
             * If the current die is yellow. Then zombieCup sets the green die
             * list. Then it sets the name of the die, it then rolls the die.
             * dieScore then takes in the die face value, dieColor is set to 2
             * to represent it self as a yellow die. The score is then placed
             * with the dieColor and dieScore passed in to it as parameters.
             * zombieGUI then sets the dice to the appropriate ImageViews.
             */
            else if (checkDieColor(list.get(i)) == 2)
            {
                zombieCup.setYellowDieList(yellowList);
                yDie.setDieName(returningDie);
                yDie.rollDie();
                dieScore = yDie.getDieValue();
                dieColor = 2;
                placeScore(dieScore, yDie.getImageFaceValue());
                zombieGUI.setADie(yDie.getImageFaceValue(), i);
            }

            /*
             * If the current die is red. Then zombieCup sets the green die
             * list. Then it sets the name of the die, it then rolls the die.
             * dieScore then takes in the die face value, dieColor is set to 3
             * to represent it self as a red die. The score is then placed with
             * the dieColor and dieScore passed in to it as parameters.
             * zombieGUI then sets the dice to the appropriate ImageViews.
             */
            else if (checkDieColor(list.get(i)) == 3)
            {
                zombieCup.setRedDieList(redList);
                rDie.setDieName(returningDie);
                rDie.rollDie();
                dieScore = rDie.getDieValue();
                dieColor = 3;
                placeScore(dieScore, rDie.getImageFaceValue());
                zombieGUI.setADie(rDie.getImageFaceValue(), i);
            }
        }

        /*
         * This checks if the player has bin shotgunned.
         */
        checkIfShotgunned();
    }

    /**
     * This method removes a die from the ArrayList parameter remover . The die
     * is removed by the name, which is the String parameter dieName.
     *
     * @param remover takes in a die list, like the greenList, yellowList, or
     * redList.
     *
     * @param dieName takes in the name of the die to remove.
     */
    public void removeDie(ArrayList<String> remover, String dieName)
    {
        /*
         * if the ArrayList equals to the green list.
         */
        if (remover == greenList)
        {
            remover.remove(dieName);
            zombieCup.setGreenDieList(remover);
        }

        /*
         * if the ArrayList equals to the yellow list.
         */
        else if (remover == yellowList)
        {
            remover.remove(dieName);
            zombieCup.setYellowDieList(remover);
        }

        /*
         * if the ArrayList equals to the red list.
         */
        else if (remover == redList)
        {
            remover.remove(dieName);
            zombieCup.setRedDieList(remover);
        }
    }

    /**
     * This method places the scores in to the Player class. It also places the
     * brains and shotgun Images in to the appropriate ImageViews in the
     * zombieGUI class.
     *
     * @param list takes in an integer that represents the die face value.
     *
     * @param dieColorScore takes in the name of the current die.
     */
    public void placeScore(int list, String dieColorScore)
    {
        int one = 1;

        /*
         * If the currentPlayer hasn't been shotgunned yet then it keeps going.
         */
        if (currentPlayer.getShotgunned() == false)
        {
            /*
             * If the current die is a brain.
             */
            if (list == 1 || list == 4)
            {
                /*
                 * If die color is green
                 */
                if (checkDieColor(dieColorScore) == 1)
                {
                    currentPlayer.setBrains(one);// Add brains to Player
                    removeDie(greenList, dieColorScore);
                    zombieGUI.setZombieBrains("images/zombieDie_1_1.png",
                            currentBrainSpot);

                    /*
                     * Increment spot to place next brain in the ImageViews.
                     */
                    currentBrainSpot++;
                }

                /*
                 * If die color is yellow.
                 */
                if (checkDieColor(dieColorScore) == 2)
                {
                    currentPlayer.setBrains(one);// Add brains to Player
                    removeDie(yellowList, dieColorScore);
                    zombieGUI.setZombieBrains("images/zombieDie_2_1.png",
                            currentBrainSpot);

                    /*
                     * Increment spot to place next brain in the ImageViews.
                     */
                    currentBrainSpot++;
                }

                /*
                 * If die color is red.
                 */
                if (checkDieColor(dieColorScore) == 3)
                {
                    currentPlayer.setBrains(one);// Add brains to Player
                    removeDie(redList, dieColorScore);
                    zombieGUI.setZombieBrains("images/zombieDie_3_1.png",
                            currentBrainSpot);

                    /*
                     * Increment spot to place next brain in the ImageViews.
                     */
                    currentBrainSpot++;
                }
            }

            /*
             * Else if the die is a shotgun.
             */
            else if (list == 3 || list == 6
                    && currentPlayer.getShot() < MAX_SHOTS)
            {
                /*
                 * If die color is green
                 */
                if (checkDieColor(dieColorScore) == 1)
                {
                    currentPlayer.setShots(one);
                    removeDie(greenList, dieColorScore);
                    zombieGUI.setShot("images/zombieDie_1_3.png",
                            currentShotSpot);

                    /*
                     * Increment spot to place next shotgun in the ImageViews.
                     */
                    currentShotSpot++;
                }

                /*
                 * If die color is yellow.
                 */
                if (checkDieColor(dieColorScore) == 2)
                {
                    currentPlayer.setShots(one);
                    removeDie(yellowList, dieColorScore);
                    zombieGUI.setShot("images/zombieDie_2_3.png",
                            currentShotSpot);

                    /*
                     * Increment spot to place next shotgun in the ImageViews.
                     */
                    currentShotSpot++;
                }

                /*
                 * If die color is red.
                 */
                if (checkDieColor(dieColorScore) == 3)
                {
                    currentPlayer.setShots(one);
                    removeDie(redList, dieColorScore);
                    zombieGUI.setShot("images/zombieDie_3_3.png",
                            currentShotSpot);

                    /*
                     * Increment spot to place next shotgun in the ImageViews.
                     */
                    currentShotSpot++;
                }
            }
        }

        /*
         * Else if the firstPlayer has been shotgunned, then switchPlayers
         * method is called.
         */
        else if (firstPlayer.getShotgunned() == true)
        {
            switchPlayers();
        }
    }

    /**
     * This method checks if the first or second player have been shotgunned.
     */
    public void checkIfShotgunned()
    {
        /*
         * If firstPlayer shotgunned.
         */
        if (getFirstPlayer().getShotgunned() == true)
        {
            switchPlayers();
        }

        /*
         * If secondPlayer shotgunned.
         */
        else if (getSecondPlayer().getShotgunned() == true)
        {
            determineWinner();
        }
    }

    /**
     * This method switches the players. If the firstPlayer has been shotgunned
     * then it determines the winner.
     */
    public void switchPlayers()
    {
        /*
         * If no one has been shotgunned yet.
         */
        if (secondPlayer.getDone() == false
                && firstPlayer.getShotgunned() == false)
        {
            setPlayer(secondPlayer);
            zombieGUI.resetImageViews();
            zombieGUI.setMoveTracker(false, 0, true, 0);
            resetGame();
            DisplayScreen screen = new DisplayScreen(false, true, false,
                    firstPlayer, secondPlayer);
        }

        /*
         * If the firstPlayer has been shotgunned.
         */
        else if (firstPlayer.getShotgunned() == true)
        {
            determineWinner();
        }
    }

    /**
     * This method checks to see who is the winner of the game, or if someone
     * has been shotgunned. for every situation a specific frame is made to
     * display the information.
     */
    public void determineWinner()
    {
        /*
         * If the firstPlayer has more brains and hasn't been shotgunned.
         */
        if (firstPlayer.getBrains() > secondPlayer.getBrains()
                && firstPlayer.getShotgunned() == false)
        {
            firstPlayer.setWinner(true);
            secondPlayer.setWinner(false);
            resetGame();// Reset game
            zombieGUI.resetImageViews();

            /*
             * Create the screen to display the winning information.
             */
            DisplayScreen screen = new DisplayScreen(false, false, true,
                    firstPlayer, secondPlayer);
        }

        /*
         * If the firstPlayer has been shotgunned.
         */
        else if (firstPlayer.getShotgunned() == true
                && secondPlayer.getShotgunned() == false
                && secondPlayer.getWinner() == false)
        {
            firstPlayer.setWinner(false);
            secondPlayer.setWinner(true);
            resetGame();// Reset game.
            zombieGUI.resetImageViews();

            /*
             * Create a screen to display the shotgunned status.
             */
            DisplayScreen screen = new DisplayScreen(true, false, false,
                    firstPlayer, secondPlayer);
        }

        /*
         * If the secondPlayer has been shotgunned.
         */
        else if (firstPlayer.getShotgunned() == false
                && secondPlayer.getShotgunned() == true)
        {
            firstPlayer.setWinner(true);
            secondPlayer.setWinner(false);
            resetGame();

            /*
             * Create a screen to display the the winner and the scores.
             */
            DisplayScreen screen = new DisplayScreen(false, false, true,
                    firstPlayer, secondPlayer);
        }

        /*
         * If the secondPlayer has more brains and no one has been shotgunned.
         */
        else if (secondPlayer.getBrains() > firstPlayer.getBrains()
                && secondPlayer.getShotgunned() == false)
        {
            firstPlayer.setWinner(false);
            secondPlayer.setWinner(true);

            /*
             * Create a screen to display the secondPlayer winner.
             */
            DisplayScreen screen = new DisplayScreen(false, false, true,
                    firstPlayer, secondPlayer);
        }

        /*
         * If its a tie then it does nothing for now.
         */
        else if (firstPlayer.getBrains() == secondPlayer.getBrains())
        {
            firstPlayer.setTie(true);
            secondPlayer.setTie(true);
        }
    }

    /**
     * Gets the first Player
     *
     * @return the first player Player.
     */
    public Player getFirstPlayer()
    {
        return firstPlayer;
    }

    /**
     * Gets the second player
     *
     * @return the second player Player.
     */
    public Player getSecondPlayer()
    {
        return secondPlayer;
    }

    /**
     * Sets the current player from the parameter.
     *
     * @param p takes in an instance of the Player class.
     */
    public void setPlayer(Player p)
    {
        currentPlayer = p;
    }

    /**
     * Gets the current player of the game.
     *
     * @return the currentPlayer that is playing the game.
     */
    public Player getPlayer()
    {
        return currentPlayer;
    }

    /**
     * Gets the current players brains score.
     *
     * @return The current players brain score.
     */
    public int getBrainScore()
    {
        return currentPlayer.getBrains();
    }

    /**
     * Gets the current players shotgun score.
     *
     * @return The current players shotgun score.
     */
    public int getShotScore()
    {
        return currentPlayer.getShot();
    }

    /**
     * This method resets the currentBrainSpot and the currentShotSpot for the
     * ImageViews.
     */
    public void resetGame()
    {
        currentBrainSpot = 0;
        currentShotSpot = 0;
    }
}
