package edu.chemeketa.zombieDice;

import java.util.ArrayList;

/**
 * File name: Player.java
 *
 * Programmer: Alexander Molodyh Chemeketa Community College Class CIS234J
 * Created: May 20, 2015 9:37:46 PM Assignment: CIS234J Final Project
 */
/**
 * Class Name: Player.java
 *
 * Description: This class represents a Player in a game. The class consists of
 * many getters and setters to keep track of scores and winners.
 *
 */
public class Player
{
    /*
     * These keep track of the colored dice.
     */

    private int greenDie;
    private int yellowDie;
    private int redDie;

    /*
     * These keep track of the scores.
     */
    private int shotGun;
    private int brains;
    private int feet;

    /*
     * These keep track if the lists have been depleted or not.
     */
    private boolean redDepleted;
    private boolean yellowDepleted;
    private boolean greenDepleted;

    /*
     * Keeps track of shotgunned or not.
     */
    private boolean shotgunned;

    /*
     * These keep let the game know if the player is a winner, done with the
     * turn or if there is a tie.
     */
    private boolean winn;
    private boolean done;
    private boolean tie;

    /*
     * These are constant variables that keep track of the dice limit.
     */
    private final int RED_DIE_LIMIT = 3;
    private final int YELLOW_DIE_LIMIT = 4;
    private final int GREEN_DIE_LIMIT = 6;

    /**
     * Constructor initializes the variables needed for a new Player.
     */
    public Player()
    {
        redDepleted = false;
        yellowDepleted = false;
        greenDepleted = false;
        shotgunned = false;
        winn = false;
        greenDie = 0;
        yellowDie = 0;
        redDie = 0;
    }

    /**
     * Sets the red dice depleted.
     *
     * @param rTemp in a true or false to set depleted or not.
     */
    public void setRedDepleted(boolean rTemp)
    {
        redDepleted = rTemp;
    }

    /**
     * Sets the yellow dice depleted.
     *
     * @param yTemp in a true or false to set depleted or not.
     */
    public void setYellowDepleted(boolean yTemp)
    {
        yellowDepleted = yTemp;
    }

    /**
     * Sets the green dice depleted.
     *
     * @param gTemp in a true or false to set depleted or not.
     */
    public void setGreenDepleted(boolean gTemp)
    {
        greenDepleted = gTemp;
    }

    /**
     * Gets the red dice depleted.
     *
     * @return The depleted dice if true or false.
     */
    public boolean getRedDepleted()
    {
        return redDepleted;
    }

    /**
     * Gets the yellow dice depleted.
     *
     * @return The depleted dice if true or false.
     */
    public boolean getYellowDepleted()
    {
        return yellowDepleted;
    }

    /**
     * Gets the green dice depleted.
     *
     * @return The depleted dice if true or false.
     */
    public boolean getGreenDepleted()
    {
        return greenDepleted;
    }

    /**
     * Gets the shotgunned state of the player.
     *
     * @param sTemp takes in a true or false boolean.
     */
    public void setShotgunned(boolean sTemp)
    {
        shotgunned = sTemp;
    }

    /**
     * Sets the shotgunned state of the player.
     *
     * @return The shotgunned player if true or false.
     */
    public boolean getShotgunned()
    {
        return shotgunned;
    }

    /**
     * Adds a brain score to brains.
     *
     * @param bTemp takes in an integer to add to brains.
     */
    public void setBrains(int bTemp)
    {
        brains += bTemp;
    }

    /**
     * Adds a feet score to feet.
     *
     * @param fTemp takes in an integer to add to feet.
     */
    public void setFeet(int fTemp)
    {
        feet += fTemp;
    }

    /**
     * Adds a shotgun score to shotGun. If shotgun reaches 3 then shotgunned is
     * set to true.
     *
     * @param sTemp takes in an integer to add to shotGun.
     */
    public void setShots(int sTemp)
    {
        shotGun += sTemp;
        if (shotGun >= 3)
        {
            setShotgunned(true);
        }
    }

    /**
     * Gets the brains score.
     *
     * @return The brain score for the player.
     */
    public int getBrains()
    {
        return brains;
    }

    /**
     * Gets the feet score.
     *
     * @return The feet score for the player.
     */
    public int getFeet()
    {
        return feet;
    }

    /**
     * Gets the shotGun score.
     *
     * @return The shotGun score for the player.
     */
    public int getShot()
    {
        return shotGun;
    }

    /**
     * Sets the winner to true or false.
     *
     * @param bool Takes in a boolean of true or false.
     */
    public void setWinner(boolean bool)
    {
        winn = bool;
    }

    /**
     * Gets the winner for this player.
     *
     * @return The winner of this player if true or false.
     */
    public boolean getWinner()
    {
        return winn;
    }

    /**
     * Sets the player to done or not.
     *
     * @param dBool Takes in a boolean of true or false to set the player to
     * done or not.
     */
    public void setDone(boolean dBool)
    {
        done = dBool;
    }

    /**
     * Gets done to see if the players turn is done or not.
     *
     * @return The done boolean to see if the player's turn is done or not.
     */
    public boolean getDone()
    {
        return done;
    }

    /**
     * Sets the player to a game tie.
     *
     * @param tBool takes in a boolean value to set the tie to true or false.
     */
    public void setTie(boolean tBool)
    {
        tie = tBool;
    }

    /**
     * Gets the tie of the player to see if there is a tie between the two
     * players.
     *
     * @return The tie boolean between the players to see if there is a tie or
     * not.
     */
    public boolean getTie()
    {
        return tie;
    }
}
