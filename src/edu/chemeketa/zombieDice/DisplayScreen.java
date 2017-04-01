package edu.chemeketa.zombieDice;

/**
 * File name: DisplayScreen.java
 *
 * Programmer: Alexander Molodyh Chemeketa Community College Class CIS234J
 * Created: May 30, 2015 12:38:43 PM Assignment: CIS234J Final Project
 */

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Class Name DisplayScreen.java
 *
 * Description: This class is a JavaFX frame that displays the winning players
 * scores, or when the player has been shotgunned. The class can be used in
 * several different ways by passing in different parameters.
 * <pre><b> Be aware not to use more than one true boolean at a time.
 * It will not work.</b></pre> Some example ways to use the class.
 *
 * <pre> This will create a screen that displays the winning player
 * and both the players scores. The reason for that is because the
 * third boolean has been set to true</Pre>
 *
 * <pre>DisplayScreen screen = new DisplayScreen(false, false, true,
 * firstPlayer, secondPlayer);</pre>
 *
 * To use the screen to display a second turn dialog or a shotgunned dialog, you
 * just simply pass in true for the first or second parameter.
 */
public class DisplayScreen extends Stage
{
    /*
     * These objects are globally set because the 
     * class uses them in different methods.
     */

    private Pane root;
    private Player firstPlayer;
    private Player secondPlayer;
    private Scene scene;
    private Button okButton;
    private Stage primaryStage;

    /**
     * Constructor takes in 5 parameters but needs to use only 3 of them at a
     * time. It sets up the appropriate screen based on the booleans provided.
     *
     * @param shotgunned Takes in a true or false boolean. If it is true then
     * the constructor sets up the screen to deliver a shotgunned message. If
     * false then it does nothing.
     *
     * @param secondTurn Takes in a true of false boolean. If it is true then
     * the constructor sets the screen up to deliver a message stating that it
     * is the second turn and displays the first players scores. If false it
     * does nothing.
     *
     * @param winner Takes in a true of false boolean. If it is true then the
     * constructor sets the screen up to deliver a message stating that the
     * current Player is the winner. If false it does nothing.
     *
     * @param firstPlayer Takes in the first player of the game so that this
     * class can do some calculations on the players winnings or loses.
     *
     * @param secondPlayer Takes in the first player of the game so that this
     * class can do some calculations on the players winnings or loses.
     *
     */
    public DisplayScreen(boolean shotgunned, boolean secondTurn,
            boolean winner, Player firstPlayer, Player secondPlayer)
    {
        root = new Pane();
        primaryStage = new Stage();

        /*
         * Pass in the first and second players to their designated objects.
         */
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;

        /*
         * The okButton is only created once because every screen
         * has it.
         */
        okButton = new Button("OK");
        okButton.setId("ok-button");
        okButton.setMinWidth(100);
        okButton.setMinHeight(45);
        okButton.setOnAction((ActionEvent e) ->
        {
            primaryStage.close();
        });

        /*
         * If the screen is meant to display the second player
         * turn.
         */
        if (secondTurn)
        {
            scene = new Scene(secondTurn(), 500, 300);
        }

        /*
         * If the screen is meant to display that the player got 
         * shotgunned.
         */
        else if (shotgunned)
        {
            scene = new Scene(shotgunned(), 500, 450);

            sleeper(700);

            /*
             * Start the shotgun blast sound.
             */
            ZombieSounds sound = new ZombieSounds("src/sounds/shotgun.wav");
            sound.start();
        }

        /*
         * If the screen is meant to display the winner of the game.
         */
        else if (winner)
        {
            scene = new Scene(checkWinner(), 550, 400);
        }

        Image okImage = new Image("images/zombieOkButton.gif");

        root.getChildren().addAll(okButton);

        /*
         * Add the style sheets.
         */
        scene.getStylesheets().add("nextPlayerScreen.css");

        primaryStage.getIcons().add(new Image("images/zombieFace.gif"));
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * This method creates the screen which displays the winner of he game and
     * sets the appropriate messages to the winner. Then displays the losing
     * players scores on bottom.
     *
     * @return Pane returns a regular pane with all the Nodes already set up and
     * ready to be put on a Scene.
     */
    private Pane checkWinner()
    {
        /*
         * Text winner displays the winner of the game.
         */
        Text winner;

        /*
         * First players brains and shotgun scores.
         */
        Text firstBrainScore;
        Text firstShotScore;

        /*
         * Second players brains and shotgun scores.
         */
        Text secondBrainScore;
        Text secondShotScore;

        /*
         * If the first player is the winner or the second player gets
         * shotgunned.
         */
        if (firstPlayer.getBrains() > secondPlayer.getBrains()
                || (firstPlayer.getShotgunned() == false && secondPlayer
                .getShotgunned() == true))
        {
            /*
             * Displays the first player as winner.
             */
            winner = new Text("The Winner is First Player");
            winner.relocate(20, 20);
            winner.setId("winner-text");

            /*
             * Displays first players brains score
             */
            firstBrainScore = new Text(20, 20, "First players brain score "
                    + firstPlayer.getBrains());
            firstBrainScore.setId("first-brain-score");
            firstBrainScore.relocate(20, 65);

            /*
             * Displays first players shotgun score.
             */
            firstShotScore = new Text(20, 20, "First players shotgun score "
                    + firstPlayer.getShot());
            firstShotScore.setId("first-shot-score");
            firstShotScore.relocate(20, 110);

            /*
             * Displays second player brain score
             */
            secondBrainScore = new Text(20, 20, "Second players brain score "
                    + secondPlayer.getBrains());
            secondBrainScore.setId("first-brain-score");
            secondBrainScore.relocate(20, 155);

            /*
             * Displays second Player shotgun score.
             */
            secondShotScore = new Text(20, 20, "Second players shotgun score "
                    + secondPlayer.getShot());
            secondShotScore.setId("first-shot-score");
            secondShotScore.relocate(20, 200);

            okButton.relocate(370, 400);

            primaryStage.setTitle("First Player Winner!!!");

            root.getChildren().addAll(winner, firstBrainScore, firstShotScore,
                    secondBrainScore, secondShotScore);
        }

        /*
         * If second player has more brains then the first player or
         * if the first player has been shotgunned and second player not.
         */
        else if (firstPlayer.getBrains() < secondPlayer.getBrains()
                || (firstPlayer.getShotgunned() == true && secondPlayer
                .getShotgunned() == false))
        {
            /*
             * Display second player as winner.
             */
            winner = new Text("The Winner is Second Player");
            winner.relocate(20, 20);
            winner.setId("winner-text");

            /*
             * Displays second player brain score
             */
            secondBrainScore = new Text(20, 20, "Second players brain score "
                    + secondPlayer.getBrains());
            secondBrainScore.setId("first-brain-score");
            secondBrainScore.relocate(20, 65);

            /*
             * Displays second Player shotgun score.
             */
            secondShotScore = new Text(20, 20, "Second players shotgun score "
                    + secondPlayer.getShot());
            secondShotScore.setId("first-shot-score");
            secondShotScore.relocate(20, 110);

            /*
             * Displays first players brains score
             */
            firstBrainScore = new Text(20, 20, "First players brain score "
                    + firstPlayer.getBrains());
            firstBrainScore.setId("first-brain-score");
            firstBrainScore.relocate(20, 155);

            /*
             * Displays first players shotgun score.
             */
            firstShotScore = new Text(20, 20, "First players shotgun score "
                    + firstPlayer.getShot());
            firstShotScore.setId("first-shot-score");
            firstShotScore.relocate(20, 200);

            /*
             * Explicitly place okButton
             */
            okButton.relocate(400, 330);

            primaryStage.setTitle("Second Player Winner!!!");

            root.getChildren().addAll(winner, secondBrainScore,
                    secondShotScore, firstBrainScore, firstShotScore);
        }

        return root;
    }

    /**
     * This method creates the screen which displays the shotgunned player and
     * displays an image saying that you have been shotgunned as well as play a
     * shotgun blast sound.
     *
     * @return Pane returns a regular pane with all the Nodes already set up and
     * ready to be put on a Scene.
     */
    private Pane shotgunned()
    {
        root.setId("root-shotgunned");

        /*
         * Displays that you have been shotgunned.
         */
        Text shotGunText = new Text(20, 20, "You got shotgunned!! \n"
                + "Second player wins!!");
        shotGunText.setId("shot-gun-text");

        /*
         * Explicitly place okButton.
         */
        okButton.relocate(360, 380);

        /*
         * Explicitly place shotGunText.
         */
        shotGunText.relocate(20, 20);

        root.getChildren().add(shotGunText);

        primaryStage.setTitle("Shot Gunned!!!");

        return root;
    }

    /**
     * This method creates the screen which displays the second players turn and
     * displays the first players brain, and shotgun score.
     *
     * @return Pane returns a regular pane with all the Nodes already set up and
     * ready to be put on a Scene.
     */
    private Pane secondTurn()
    {
        /*
         * First players brain score.
         */
        Text firstBrainScore = new Text(20, 20, "First players brain score "
                + firstPlayer.getBrains());
        firstBrainScore.setId("first-brain-score");
        firstBrainScore.relocate(20, 20);

        /*
         * First players shotgun score.
         */
        Text firstShotScore = new Text(20, 20, "First players shotgun score "
                + firstPlayer.getShot());
        firstShotScore.setId("first-shot-score");
        firstShotScore.relocate(20, 65);

        /*
         * Explicitly place okButton.
         */
        okButton.relocate(380, 240);

        root.getChildren().addAll(firstBrainScore, firstShotScore);

        primaryStage.setTitle("Next players Turn!!");

        return root;
    }

    /**
     * A sleeper method to slow down the program in some places.
     *
     * @param milliSeconds takes in an integer that acts as milliseconds to
     * pause.
     */
    public void sleeper(int milliSeconds)
    {
        try
        {
            Thread.sleep(milliSeconds);
        } catch (InterruptedException e)
        {
            System.out.println(e.toString());
        }
    }
}
