package edu.chemeketa.zombieDice;

/**
 * File name: Die.java
 *
 * Description: A class that represents a six sided die. You can roll the die
 * and get the current face value.
 *
 * Programmer: Alexander Molodyh Chemeketa Community College Class CIS233J
 * Created: Apr 6, 2015 8:42:52 PM Assignment: CIS234J Final Project.
 */
/**
 * Class Name: Die
 *
 * Description: A class that represents a six sided die. You can roll the die
 * and get the current face value.
 */
public class Die
{

    private final int MIN_FACES = 4;

    /**
     * number of sides on the die
     */
    private int numFaces;

    /**
     * current value showing on the die
     */
    private int faceValue;

    /**
     * This constructor creates a Die object and defaults to a six-sided die,
     * initially with the face showing 1.
     */
    public Die()
    {
        numFaces = 6;
        faceValue = 1;
    }

    /**
     * This constructor explicitly sets the size of the die. Defaults to a size
     * of six if the parameter is invalid. Initial face value is 1.
     *
     * @param faces takes in the amount of faces the die will have.
     *
     */
    public Die(int faces)
    {
        if (faces < MIN_FACES)
        {
            numFaces = 6;
        }
        else
        {
            numFaces = faces;
        }
        faceValue = 1;
    }

    /**
     * Rolls the die and returns the result.
     *
     * @return Returns the face value of the die.
     */
    public int roll()
    {
        faceValue = (int) (Math.random() * numFaces) + 1;
        return faceValue;
    }

    /**
     * Returns the current die value.
     *
     * @return Returns the current die value.
     */
    public int getFaceValue()
    {
        return faceValue;
    }

    /**
     * Typical toString method
     *
     * @return Returns a logical representation of the state of the object
     */
    public String toString()
    {
        return String.valueOf(faceValue);
    }
}
