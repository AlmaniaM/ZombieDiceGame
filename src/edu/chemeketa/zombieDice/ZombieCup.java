package edu.chemeketa.zombieDice;

import java.util.ArrayList;
import java.util.Vector;

/**
 * File name: ZombieCup.java
 *
 * Programmer: Alexander Molodyh
 * Chemeketa Community College
 * Class CIS234J
 * Created: May 22, 2015 8:33:29 AM
 * Assignment: CIS234J Final Project
 */

/**
 * Class Name: ZombieCup.java
 *
 * Description: This class is a cup of dice. When the cup shakes it makes a
 * sound like a cup with dice in it. After the cup finishes shaking then it
 * throws out three dice. The three dice then can be retrieved by another class.
 * An Example of how to use it.
 * 
 * <pre>
 * ZombieCup zCup = new ZombieCup();
 * zCup.rollCup();
 * ArrayList zombieList = zCup.getDieList();
 * </pre>
 * 
 * Now your zombieList has the three dice that were rolled.
 *
 */
public class ZombieCup
{
	/*
	 * These are ArrayLists that contain the green, yellow, and red dice names.
	 */
	private ArrayList<String> cupGreenList;
	private ArrayList<String> cupYellowList;
	private ArrayList<String> cupRedList;

	/*
	 * This is the total limit of dice the cup can pull out at a time.
	 */
	private final int TOTAL_LIMIT = 3;

	/*
	 * A sound for when the cup shakes.
	 */
	private String shakeSound = "src/sounds/cupRolling.wav";

	/*
	 * The chosen dice are set in to this ArrayList.
	 */
	private ArrayList<String> chosenDice;

	/*
	 * An object of the ZombieSounds class to play the cup shaking sound.
	 */
	ZombieSounds zSounds;

	/*
	 * Three instances of the colored dice.
	 */
	GreenDie cupGreenDie;
	YellowDie cupYellowDie;
	RedDie cupRedDie;

	/**
	 * This constructor instantiates the dice objects and ArrayLists. Then it
	 * loads the ArrayLists with the dice names.
	 */
	public ZombieCup()
	{
		cupGreenDie = new GreenDie();
		cupYellowDie = new YellowDie();
		cupRedDie = new RedDie();

		cupGreenList = new ArrayList<String>();
		cupYellowList = new ArrayList<String>();
		cupRedList = new ArrayList<String>();

		/*
		 * Load the green die list.
		 */
		for (int g = 1; g < 7; g++)
		{
			cupGreenList.add("zombieDie_1_" + g);
		}

		/*
		 * Load the yellow die list.
		 */
		for (int y = 1; y < 5; y++)
		{
			cupYellowList.add("zombieDie_2_" + y);
		}

		/*
		 * Load the red die list.
		 */
		for (int r = 1; r < 4; r++)
		{
			cupRedList.add("zombieDie_3_" + r);
		}
	}

	/**
	 * This method shakes the cup and sets the three dice in to an ArrayList.
	 */
	public void rollCup()
	{
		/*
		 * Create and play the cup sound.
		 */
		zSounds = new ZombieSounds(shakeSound);
		Thread threadSounds = new Thread(zSounds);
		threadSounds.start();

		/*
		 * Set the three dice by calling the setThreeDice method.
		 */
		setThreeDice();
	}

	/**
	 * This method randomly sets three dice from the cup in to an ArrayList.
	 */
	public void setThreeDice()
	{
		/*
		 * This variable holds a number from 1 to 100.
		 */
		int randomGenerator;

		/*
		 * This ArrayList will hold the three chosen dice from the cup.
		 */
		chosenDice = new ArrayList<String>();

		/*
		 * This String takes in the name of the chosen die.
		 */
		String currentDie = "";

		/*
		 * Run the for loop three times
		 */
		for (int i = 0; i < TOTAL_LIMIT; i++)
		{
			/*
			 * Generate a random number from 1 to 100.
			 */
			randomGenerator = (int) (Math.random() * 100) + 1;

			/*
			 * If the number is from 1 to 33 then then the die is green.
			 */
			if (randomGenerator > 0 && randomGenerator <= 33
					&& cupGreenList.isEmpty() == false)
			{
				cupGreenDie.rollDie();
				currentDie = cupGreenDie.getImageFaceValue();
			}

			/*
			 * If the number is from 34 to 66 then the die is yellow.
			 */
			else if (randomGenerator >= 34 && randomGenerator <= 66
					&& cupYellowList.isEmpty() == false)
			{
				cupYellowDie.rollDie();
				currentDie = cupYellowDie.getImageFaceValue();
			}

			/*
			 * If the number is from 67 to 100 then the die is red.
			 */
			else if (randomGenerator >= 67 && randomGenerator <= 100
					&& cupRedList.isEmpty() == false)
			{
				cupRedDie.rollDie();
				currentDie = cupRedDie.getImageFaceValue();
			}

			/*
			 * Add the die to the ArrayList.
			 */
			chosenDice.add(currentDie);
		}

		/*
		 * Set the chosen dice to another ArrayList so other classes can reach
		 * it.
		 */
		setDieList(chosenDice);
	}

	/**
	 * This method sets the greenDie list.
	 * 
	 * @param greenList
	 *            takes in an ArrayList of green dice.
	 */
	public void setGreenDieList(ArrayList<String> greenList)
	{
		cupGreenList = greenList;
	}

	/**
	 * This method sets the yellowDie list.
	 * 
	 * @param yVec
	 *            takes in an ArrayList of yellow dice.
	 */
	public void setYellowDieList(ArrayList<String> yVec)
	{
		cupYellowList = yVec;
	}

	/**
	 * This method sets the redDie list.
	 * 
	 * @param rVec
	 *            takes in an ArrayList of red dice.
	 */
	public void setRedDieList(ArrayList<String> rVec)
	{
		cupRedList = rVec;
	}

	/**
	 * This method gets the greenList of dice.
	 * 
	 * @return The ArrayList of green dice.
	 */
	public ArrayList<String> getGreenDieList()
	{
		return cupGreenList;
	}

	/**
	 * This method gets the yellowList of dice.
	 * 
	 * @return The ArrayList of yellow dice.
	 */
	public ArrayList<String> getGYellowDieList()
	{
		return cupYellowList;
	}

	/**
	 * This method gets the redList of dice.
	 * 
	 * @return The ArrayList of red dice.
	 */
	public ArrayList<String> getYellowDieList()
	{
		return cupYellowList;
	}

	/**
	 * This method sets the three chosen dice from the cup.
	 * 
	 * @param aList
	 *            takes in an ArrayList of three dice.
	 */
	private void setDieList(ArrayList aList)
	{
		chosenDice = aList;
	}

	/**
	 * This method gets the three chosen dice from the cup.
	 * 
	 * @return The three dice that were chosen from the cup.
	 */
	public ArrayList<String> getDieList()
	{
		return chosenDice;
	}
}
