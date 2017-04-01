package edu.chemeketa.zombieDice;

/**
 * File name: GreenDie.java
 *
 * Programmer: Alexander Molodyh
 * Chemeketa Community College
 * Class CIS234J
 * Created: May 20, 2015 9:36:40 PM
 * Assignment: CIS234 Final Project
 */

/**
 * Class Name: GreenDie.java
 *
 * Description: This class represents a green Zombie die. This class simply
 * rolls the die and then a player can retrieve the die face value and the die
 * face value image.
 */
public class GreenDie
{
	/*
	 * The face values of the die.
	 */
	private int faceValue;
	private String faceImage;

	/*
	 * This takes in the name of the die.
	 */
	private String dieName;

	/*
	 * An instance of the Die class.
	 */
	Die die;

	/**
	 * Constructor instantiates the Die object.
	 */
	public GreenDie()
	{
		die = new Die();
	}

	/**
	 * This constructor sets up the name of the die.
	 * 
	 * @param dieName
	 *            takes in the name of the die.
	 */
	public GreenDie(String dieName)
	{
		this.dieName = dieName;
	}

	/**
	 * This method sets the die name.
	 * 
	 * @param dieName
	 *            takes in the die name.
	 */
	public void setDieName(String dieName)
	{
		this.dieName = dieName;
	}

	/**
	 * This method gets the die name.
	 * 
	 * @return The name of the die.
	 */
	public String getDieName()
	{
		return dieName;
	}

	/**
	 * This method gets the Image URL of the current face value.
	 * 
	 * @return the URL or the current Image face value.
	 */
	public String getImageFaceValue()
	{
		return faceImage;
	}

	/**
	 * This method sets the Image URL of the face value.
	 * 
	 * @param temp
	 *            takes in an integer that represents the image of the die.
	 */
	public void setDieImage(int temp)
	{
		faceImage = "images/zombieDie_1_" + temp + ".png";
	}

	/**
	 * This method sets the face value of the rolled die.
	 * 
	 * @param dieNum
	 *            takes in an integer to set to faceValue.
	 */
	private void setDie(int dieNum)
	{
		faceValue = dieNum;
	}

	/**
	 * This method get the current face value of the die.
	 * 
	 * @return The current face value of the die.
	 */
	protected int getDieValue()
	{
		return faceValue;
	}

	/**
	 * This method rolls the die and sets both the Integer face value and the
	 * image face value.
	 */
	public void rollDie()
	{
		die.roll();
		setDie(die.getFaceValue());
		setDieImage(die.getFaceValue());
	}
}
