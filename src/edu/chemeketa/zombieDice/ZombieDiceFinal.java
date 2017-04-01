package edu.chemeketa.zombieDice;

/**
 * File name: ZombieDiceFinal.java
 *
 * Programmer: Alexander Molodyh Chemeketa Community College Class CIS233J
 * Created: Apr 3, 2015 10:57:38 PM Assignment: CIS234J Final Project
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Class Name: ZombieDiceFinal.java
 *
 * Description: This class is a javaFX GUI. It is a ZombieDice game that has a
 * banner across the top that seas Zombie Dice. The zombie face in the top left
 * corner makes sounds when ever you make a move or get a brain die or shotgun
 * die. Brain dice are placed on the left side of the screen on a TilePane. The
 * shotgun dice are placed on the right side of the screen on a TilePane. A
 * zombie cup is placed in the middle of the screen on a Pane for explicit
 * placing. The three dice that are picked from the cup are placed in the middle
 * of the screen for explicit placing. The Start button is changed to the Next
 * button after the first click. The Next button is clicked for every move that
 * the player wants to make.
 */
public class ZombieDiceFinal extends Application
{

    /*
     * These width and height for the Game GUI.
     */
    private final int WIDTH = 1390;
    private final int HEIGHT = 800;

    /*
     * This TilePane holds the brain dice ImageViews.
     */
    private TilePane leftTile;

    /*
     * This TilePane holds the shot dice ImageViews.
     */
    private TilePane rightTile;

    /*
     * This GridPane holds the zombie cupView.
     */
    private GridPane cupPane;

    /*
     * This ImageView holds the zombie cup image.
     */
    private ImageView cupView;

    /*
     * These are ImageView Arrays that display the dice images.
     */
    protected ImageView[] threeDieArray;
    protected ImageView[] brainViewArray;
    protected ImageView[] shotViewArray;

    /*
     * This ImageView holds the Next button in the bottom right side of the
     * screen.
     */
    private ImageView zNext;

    /*
     * These are the Start, Next, Brains die, Shots die Images.
     */
    private Image zombieStart;
    private Image zombieNext;
    private Image zombieBrains;
    private Image zombieShots;

    /*
     * The moveTracker keeps track of the Next button clicks.
     */
    private int moveTracker = 0;

    /*
     * This String holds the introduction to the game.
     */
    private String introFile = "";

    /*
     * This Thread is used to pass in an instance of the sounds Object so it can
     * play in the background without interrupting the program.
     */
    Thread thread;
    ZombieSounds sounds;

    /*
     * This is an Object of the ZombieDiceGame class.
     */
    ZombieDiceGame zGame;

    /*
     * This Image is set to the dice ImageViews in the beginning.
     */
    private Image zDiceOff = new Image("images/zDiceOff.gif");

    /*
     * This String holds the url for the zombie cup. This sound plays when the
     * player drops the dice in to the cup.
     */
    private String dropDice = "src/sounds/zombieCupDrop.wav";

    File file = new File("src/resources/text/zombieRules.txt");

    /*
     * An image to use for the top left corner of the frame.
     */
    Image frameImage = new Image("images/zombieFace.gif");

    /*
     * These are the dice images that are initially displayed in the main game
     * at the beginning before rolling the dice.
     */
    Image threeDie = new Image("images/zThreeDie.gif");
    Image cupImage = new Image("images/zombieCup.gif");

    /**
     * This is the Overridden start method to start the JavaFX Application. The
     * Application first starts the Stage that introduces the Zombie Dice game
     * rules. from this Stage the player can choose to Quit the game or click
     * the Start button to create the actual game frame and play the game.
     *
     * @param stage Takes in the main stage. The scene will be written on to
     * this stage.
     */
    @Override
    public void start(Stage stageInfo)
    {
        final int WIDTH = 900;
        final int HEIGHT = 600;
        /*
         * A Pane for explicit Node placing. pane is given an id so it can be
         * styled explicitly.
         */
        Pane pane = new Pane();
        pane.setId("pane");

        /*
         * The loadFile method is called to create the "introFile" String with
         * the game rules.
         */
        loadFile();

        /*
         * The Quit and Start Images are created.
         */
        Image zombieQuit = new Image("images/zombieQuit.gif");
        Image zombieStart = new Image("images/zombieStart.gif");

        /*
         * Create the introduction Text. Relocate it to coordinates. Give it an
         * ID.
         */
        Text introText = new Text(introFile);
        introText.setId("text-intro-file");
        introText.relocate(20, 20);

        /*
         * Create the ImageView that holds the Start Image. Give it an ID for
         * CSS styling.
         */
        ImageView goView = new ImageView();
        goView.setImage(zombieStart);
        goView.relocate(680, 430);
        goView.setId("go-view");

        /*
         * A MouseListener is set for the goView ImageView so it can be used as
         * a button. When the ImageView is clicked the frame closes and the main
         * game frame is started.
         */
        goView.setOnMouseClicked((MouseEvent e) ->
        {
            stageInfo.close();

            /*
             * Start the main game.
             */
            startFX();
        });

        /*
         * Create the Quit ImageView that holds the Quit Image.
         */
        ImageView stopView = new ImageView();
        stopView.setImage(zombieQuit);
        stopView.relocate(50, 430);

        /*
         * A MouseListener is set for the stopView so it can be used as a
         * button. When the ImageView is clicked the stage closes and we use
         * Platform.exit() to close the Application.
         */
        stopView.setOnMouseClicked((MouseEvent e) ->
        {
            stageInfo.close();
            Platform.exit();
        });

        pane.getChildren().addAll(introText, stopView, goView);

        /*
         * create scene with set width, height and color. Get the style sheets
         * to style the Nodes.
         */
        Scene scene2 = new Scene(pane, WIDTH, HEIGHT);
        scene2.getStylesheets().add("css/intro.css");

        stageInfo.setScene(scene2);

        stageInfo.centerOnScreen();

        /*
         * Set the icon for the top left side of the frame.
         */
        stageInfo.getIcons().add(frameImage);
        stageInfo.setResizable(false);
        stageInfo.show();
    }

    /**
     * This method starts the main Zombie Dice game Scene. It adds all the
     * ImageViews and all the Panes. The panes are added as methods to the
     * BorderPane of this Scene.
     */
    private void startFX()
    {

        /*
         * Create a Stage for the game.
         */
        Stage stage = new Stage();

        /*
         * Create an instance of the ZombieDiceGame class.
         */
        zGame = new ZombieDiceGame(this);

        /*
         * Create a BorderPane for the main game frame.
         */
        BorderPane root = new BorderPane();
        root.setId("root");

        /*
         * Create the top header Grid Pane so we can explicitly set how much
         * room a Node will take up.
         */
        GridPane topRow = new GridPane();

        /*
         * Add the topHeader method which is a Pane.
         */
        topRow.add(topHeader(), 1, 0);

        /*
         * Set the top header to the main GridPane.
         */
        root.setTop(topRow);

        /*
         * Set the leftPanel method to the main GridPane.
         */
        root.setLeft(leftPanel());

        /*
         * Set the rightPanel method to the main GridPane.
         */
        root.setRight(rightPanel());
        /*
         * Set the zombieCup method to the main GridPane.
         */
        root.setCenter(zombieCup());

        Scene scene = new Scene(root, WIDTH, HEIGHT);
        scene.getStylesheets().add("styles.css");

        stage.setTitle("Zombie Dice");
        stage.getIcons().add(frameImage);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * This method creates the Zombie Cup ImageView. This method also creates
     * the Three dice we pick from the cup. The cup and the three dice are
     * explicitly set on to a Pane.
     *
     * @return a GridPane with two Panes on it that contain the zombie cup and
     * the three chosen dice.
     */
    public Node zombieCup()
    {
        /*
         * Create the GridPane that holds the cup Pane and the three dice Pane.
         */
        GridPane mainDiceAndCup = new GridPane();

        /*
         * This Pane holds the three dice.
         */
        Pane threeDiePane = new Pane();
        threeDiePane.setPrefSize(1236, 615);

        /*
         * This Pane holds the zombie cup.
         */
        cupPane = new GridPane();

        /*
         * Create the zombie cup ImageView. Give it an ID for styling.
         */
        cupView = new ImageView();
        cupView.setImage(cupImage);
        cupView.setVisible(false);
        cupView.setId("cup-view");

        /*
         * Create and Array of three dice.
         */
        threeDieArray = new ImageView[3];

        /*
         * Set the cup location.
         */
        cupView.relocate(600, 200);

        /*
         * Set the three dice with a for loop. Set the images invisible.
         */
        for (int i = 0; i < 3; i++)
        {
            threeDieArray[i] = new ImageView();
            threeDieArray[i].setImage(threeDie);
            threeDieArray[i].setFitWidth(120);
            threeDieArray[i].setFitHeight(120);
            threeDieArray[i].setVisible(false);
        }

        /*
         * Set the three dice locations.
         */
        threeDieArray[0].relocate(100, 100);
        threeDieArray[1].relocate(280, 100);
        threeDieArray[2].relocate(190, 230);

        threeDiePane.getChildren().addAll(threeDieArray[0], threeDieArray[1],
                threeDieArray[2], cupView);

        mainDiceAndCup.add(threeDiePane, 1, 0);
        mainDiceAndCup.add(cupPane, 1, 1);

        return mainDiceAndCup;
    }

    /**
     * This method creates the top header of the main game. The zombie face is
     * set on the top left side and the banner is set to the right of the zombie
     * face.
     *
     * @return Node returns the GridPane with two ImageViews on it.
     */
    public Node topHeader()
    {
        /*
         * Create the GridPane and the two ImageViews.
         */
        GridPane topHeaderPane = new GridPane();
        ImageView zFace = new ImageView();
        ImageView zBanner = new ImageView();

        /*
         * Zombie face and zombie Banner images.
         */
        Image zombieFaceImage = new Image("images/zombieFace.gif");
        Image zombieBanerImge = new Image("images/zombieBanner.gif");

        /*
         * Set the Images to the ImageViews.
         */
        zFace.setImage(zombieFaceImage);
        zBanner.setImage(zombieBanerImge);

        /*
         * Add the zFace to the first column and the zBanner to the second
         * column.
         */
        topHeaderPane.add(zFace, 1, 0);
        topHeaderPane.add(zBanner, 2, 0);

        return topHeaderPane;
    }

    /**
     * This method creates the 13 Zombie brains ImageViews and the Quit
     * ImageView. The ImageViews are placed on to a TilePane and the Quit
     * ImageView is placed on a BorderPane.
     *
     * @return Node returns the GridPane with two Panes on it that contain the
     * 13 ImageViews and the Quit ImageView.
     */
    private Node leftPanel()
    {
        /*
         * Create the three Panes.
         */
        leftTile = new TilePane();
        BorderPane leftBorderPane = new BorderPane();
        GridPane leftPanel = new GridPane();

        /*
         * Set the top row to take up 75% of the column and the second row 25%.
         */
        RowConstraints row1 = new RowConstraints();
        row1.setPercentHeight(75);
        RowConstraints row2 = new RowConstraints();
        row2.setPercentHeight(25);

        /*
         * Create the Quit ImageView and give it a MouseEvent.
         */
        Image zombieQuit = new Image("images/zombieQuit.gif");
        ImageView zQuit = new ImageView();
        zQuit.setImage(zombieQuit);

        /*
         * Set a MouseEvent for the Quit ImageView so it can be used as a
         * button. When clicked the Application will close.
         */
        zQuit.setOnMouseClicked((MouseEvent e) ->
        {
            Platform.exit();// Exit Application
        });

        /*
         * Create a brain ImageView Array of 13 Objects.
         */
        brainViewArray = new ImageView[13];

        /*
         * Instantiate the default zombie images to place in to the brains
         * ImageViews.
         */
        ImageView zOff = new ImageView();
        zOff.setImage(zDiceOff);

        /*
         * Set the Horizontal and Vertical gaps for the brain ImageViews. Set
         * the amount of culumns for the ImageViews.
         */
        leftTile.setHgap(2);
        leftTile.setPrefColumns(3);
        leftTile.setVgap(2);

        /*
         * Instantiate, set the default images and add the ImageViews to the
         * TilePane through a for loop.
         */
        for (int i = 0; i < brainViewArray.length; i++)
        {
            brainViewArray[i] = new ImageView();
            brainViewArray[i].setImage(zDiceOff);
            leftTile.getChildren().add(brainViewArray[i]);
        }

        leftBorderPane.getChildren().add(zQuit);

        leftPanel.getRowConstraints().addAll(row1, row2);

        leftPanel.add(leftTile, 1, 0);
        leftPanel.add(leftBorderPane, 1, 1);

        return leftPanel;
    }

    /**
     * This method creates three shotgun Imageviews and the      <code>Start/Next<code> ImageViews, which are used
     * as buttons.
     *
     * @return Node returns a GridPane with two other Panes that contain the 3
     *         shotgun and the Start/Next ImageViews.
     */
    private Node rightPanel()
    {
        /*
         * Create the 3 Panes.
         */
        rightTile = new TilePane();
        BorderPane rightBorderPane = new BorderPane();
        GridPane rightPanel = new GridPane();

        /*
         * Set the top row to take up 75% of the column and the second row 25%.
         */
        RowConstraints rightRow1 = new RowConstraints();
        rightRow1.setPercentHeight(75);
        RowConstraints rightRow2 = new RowConstraints();
        rightRow2.setPercentHeight(25);

        /*
         * Create the Start and Next Images.
         */
        zombieStart = new Image("images/zombieStart.gif");
        zombieNext = new Image("images/zombieNext.gif");

        /*
         * Create the Start/Next ImageView.
         */
        zNext = new ImageView();
        zNext.setImage(zombieStart);

        /*
         * Set a MouseEvent for the zNext ImageView so we can use it as a
         * button. The event then increments the moveTracker by one and
         * setCupAndDice method is called.
         */
        zNext.setOnMouseClicked((MouseEvent e) ->
        {
            moveTracker++;
            setCupAndDice();
        });

        /*
         * Create an Array of 3 shotgun ImageViews.
         */
        shotViewArray = new ImageView[3];

        /*
         * Set the columns to 3. The horizontal and vertical gaps for the
         * shotgun ImageViews are set up.
         */
        rightTile.setHgap(2);
        rightTile.setPrefColumns(3);
        rightTile.setVgap(2);

        /*
         * The shotgun ImageViews are set up through a for loop.
         */
        for (int i = 0; i < shotViewArray.length; i++)
        {
            shotViewArray[i] = new ImageView();
            shotViewArray[i].setImage(zDiceOff);
            rightTile.getChildren().add(shotViewArray[i]);
        }

        rightBorderPane.getChildren().add(zNext);

        rightPanel.getRowConstraints().addAll(rightRow1, rightRow2);

        rightPanel.add(rightTile, 1, 0);
        rightPanel.add(rightBorderPane, 1, 1);

        return rightPanel;
    }

    /**
     * This method sets up a frame that pops up after each time a player rolls.
     * The frame displays the current players brains and shotgun scores. A      <code>Stop and Score<code> ImageView is set in the
     * bottom left corner and a <code>Keep Going<code> ImageView is
     * set in the bottom right corner.
     *
     * @return Stage returns a stage with 2 Text Nodes, 2 ImageViews that
     *         display the brains and shotgun scores, and the two <code>Stop and
     *         Score<code> as well as the <code>Keep Going<code> ImageViews.
     */
    private Stage askUserForMove()
    {
        /*
         * Create the Stage.
         */
        final Stage stage = new Stage();

        /*
         * Create the 3 Panes.
         */
        Pane pane = new Pane();
        TilePane brainsPane = new TilePane();
        brainsPane.relocate(510, 90);
        brainsPane.setHgap(2);// Horizontal gap
        brainsPane.setVgap(2);// Vertical gap
        brainsPane.setPrefColumns(7);// 7 Columns

        TilePane shotsPane = new TilePane();
        shotsPane.relocate(510, 190);
        shotsPane.setHgap(2);// Horizontal gap
        shotsPane.setPrefColumns(3);// Vertical gap

        /*
         * Create the brains and shotgun ImageView Arrays. The Arrays are only
         * as big as the player score for each brain and shotgun count is.
         */
        ImageView[] shotsView = new ImageView[zGame.getShotScore()];
        ImageView[] brainsView = new ImageView[zGame.getBrainScore()];

        /*
         * Set the brain image scores through a for loop in a try catch
         * statement. Just in case there is an error that we don't want to
         * terminate our program.
         */
        for (int i = 0; i < zGame.getBrainScore(); i++)
        {
            try
            {
                brainsView[i] = new ImageView(zombieBrains);
                brainsView[i].setFitWidth(40);
                brainsView[i].setFitHeight(40);
                brainsPane.getChildren().add(brainsView[i]);
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        /*
         * Set the shotgun image scores through a for loop in a try catch
         * statement. Just in case there is an error that we don't want to
         * terminate our program.
         */
        for (int i = 0; i < zGame.getShotScore() + 1; i++)
        {
            try
            {
                shotsView[i] = new ImageView(zombieShots);
                shotsView[i].setFitWidth(40);
                shotsView[i].setFitHeight(40);
                shotsPane.getChildren().add(shotsView[i]);
            } catch (Exception e)
            {

            }
        }

        /*
         * Create the stop and score as well as the keep going images.
         */
        Image keepGoind = new Image("images/keepGoing.gif");
        Image stopAndScore = new Image("images/stopAndScore.gif");

        /*
         * First Text Object.
         */
        Text text = new Text(20, 20, "Go again or end your turn?..");
        text.setId("text");
        text.relocate(15, 25);

        /*
         * Create the brain score Text objects.
         */
        Text brainsText = new Text(20, 20, "   " + zGame.getBrainScore());
        brainsText.setId("brains-text");
        Text text2 = new Text(20, 110, "Brains score is: "
                + brainsText.getText());
        text2.setId("text2");
        text2.setFill(Color.WHITESMOKE);
        text2.relocate(15, 110);

        /*
         * Create the shotgun score objects.
         */
        Text shotsText = new Text(20, 20, "   " + zGame.getShotScore());
        shotsText.setId("shot-text");
        Text text3 = new Text(20, 210, "Shot score is: " + shotsText.getText());
        text3.setFill(Color.WHITESMOKE);
        text3.setId("text3");
        text3.relocate(12, 210);

        /*
         * Create the keep going ImageView.
         */
        ImageView goView = new ImageView();
        goView.setImage(keepGoind);
        goView.relocate(600, 400);

        /*
         * Add a MouseEvent to the keep going ImageView so it can be used as a
         * button. When clicked the event closes the frame, sets the moveTracker
         * to 2 and calls the determineMove and passes in the moveTracker as a
         * parameter again.
         */
        goView.setOnMouseClicked((MouseEvent e) ->
        {
            stage.close();
            shakeNode(cupView);
            moveTracker = 2;
            determineMove(moveTracker);
        });

        /*
         * Create the stop and score ImageView.
         */
        ImageView stopView = new ImageView();
        stopView.setImage(stopAndScore);
        stopView.relocate(50, 400);

        /*
         * Add a MouseEvent to the stop and score ImageView so it can be used as
         * a button. When clicked the event closes the frame, and if the first
         * players done boolean is false then the zGame calls the loadDice
         * method to reload the diceList. zGame switches the players and zGame
         * sets the first player to done as true. Or else the winner is
         * determined.
         */
        stopView.setOnMouseClicked((MouseEvent e) ->
        {

            stage.close();

            /*
             * If first player isn't done yet.
             */
            if (zGame.getFirstPlayer().getDone() == false)
            {
                zGame.loadDice();
                zGame.switchPlayers();
                zGame.getFirstPlayer().setDone(true);
            }
            else
            {
                zGame.determineWinner();
            }
        });

        pane.getChildren().addAll(text, text2, brainsPane, shotsPane, text3,
                stopView, goView);

        Scene scene2 = new Scene(pane, 800, 600);
        scene2.getStylesheets().add("nextMoveDialog.css");

        stage.setScene(scene2);

        // center stage on screen
        stage.centerOnScreen();
        stage.getIcons().add(frameImage);// Add frame icon.

        stage.show();

        return stage;
    }

    /**
     * This method calls the determineMove method and passes in the moveTracker
     * variable in to it.
     */
    public void setCupAndDice()
    {
        determineMove(moveTracker);
    }

    /**
     * This method determines what move to make based on the <code>tracker<code>
     * that is passed in to the method. If tracker equals 1 then the three dice
     * and the zombie cup is set to visible. If 2 then zGame calls the beggin
     * method which rolls the dice. If greater then two then the askUserForMove
     * method is called.
     *
     * @param tracker
     *            takes in an integer that determines what move the method
     *            should make.
     */
    private void determineMove(int tracker)
    {
        /*
         * If 1 set ImageViews visible.
         */
        if (tracker == 1)
        {
            cupView.setVisible(true);

            for (int i = 0; i < 3; i++)
            {
                threeDieArray[i].setVisible(true);
            }
            zNext.setImage(zombieNext);
            sleeper(700);
            playCupDropDie();
        }

        /*
         * If 2, zGame calls begin method.
         */
        else if (tracker == 2)
        {
            zGame.beggin();
        }

        /*
         * If greater then 2 then askUserForMove method is called.
         */
        else if (tracker > 2)
        {
            askUserForMove();
        }
    }

    /**
     * This method sets the three dice that are rolled from the cup.
     *
     * @param icon takes in the image url that will be displayed.
     *
     * @param location takes in an integer number that represents which die gets
     * the current image.
     */
    public void setADie(String icon, int location)
    {
        try
        {
            /*
             * Create Image from the icon url.
             */
            Image tempImage = new Image(icon);

            /*
             * Set the image to threeDieArray location
             */
            threeDieArray[location].setImage(tempImage);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * This method sets the three shotgun dice.
     *
     * @param shotImage takes in the shotgun image url that will be displayed.
     *
     * @param location takes in an integer number that represents which die gets
     * the current image.
     */
    public void setShot(String shotImage, int location)
    {
        try
        {
            /*
             * Create Image from the icon url.
             */
            Image tempShotImage = new Image(shotImage);

            /*
             * Set the image to threeDieArray location
             */
            shotViewArray[location].setImage(tempShotImage);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * This method plays a sound when the dice are dropped in to the cup.
     */
    public void playCupDropDie()
    {
        /*
         * Create a ZombieSounds object. Then pass the object in to a Thread and
         * call the start method.
         */
        sounds = new ZombieSounds(dropDice);
        thread = new Thread(sounds);
        thread.start();
    }

    /**
     * A sleeper method to slow down the program in some places.
     *
     * @param milliSeconds takes in the amount of time to pause in milliSeconds.
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

    /**
     * This method sets the brains image to the leftPanel.
     *
     * @param image takes in the brains image url that will be displayed.
     *
     * @param index takes in an integer number that represents which die gets
     * the current image.
     */
    public void setZombieBrains(String image, int index)
    {
        try
        {
            /*
             * Create Image from the icon url.
             */
            Image brainImage = new Image(image);

            /*
             * Set the image to threeDieArray location
             */
            brainViewArray[index].setImage(brainImage);
        } catch (Exception e)
        {

        }
    }

    /**
     * This method resets the ImageViews back to their original state with the
     * default images displaying.
     */
    public void resetImageViews()
    {
        /*
         * Reset the three dice from the cup.
         */
        for (int i = 0; i < threeDieArray.length; i++)
        {
            threeDieArray[i].setImage(zDiceOff);
        }

        /*
         * Reset the brain dice in the left pane.
         */
        for (int i = 0; i < brainViewArray.length; i++)
        {
            brainViewArray[i].setImage(zDiceOff);
        }

        /*
         * Reset the shotgun dice in the right pane.
         */
        for (int i = 0; i < shotViewArray.length; i++)
        {
            shotViewArray[i].setImage(zDiceOff);
        }
    }

    /**
     * This method can add a move to the moveTracker, of reset the moveTracker,
     * or minus a move from the moveTracker.
     *
     * @param add if true then plus is added to the moveTracker.
     *
     * @param plus takes in the amount to add to the moveTracker.
     *
     * @param reset if true then the moveTracker is reset to 0.
     *
     * @param minus takes in the amount to minus from the moveTracker.
     */
    public void setMoveTracker(boolean add, int plus, boolean reset, int minus)
    {
        if (add)
        {
            moveTracker += plus;
        }
        else if (reset)
        {
            moveTracker = 0;
        }
        else
        {
            moveTracker -= minus;
        }
    }

    /**
     * This method loads the text from a text file in to a String. This way the
     * String can be passed in to a Text Node to be displayed in the game
     * introduction screen.
     */
    private void loadFile()
    {
        Scanner scan;

        /*
         * Try to load the file.
         */
        try
        {
            scan = new Scanner(file);

            /*
             * while the file has a next line of text. It adds a line of text to
             * the introFile string at a time.
             */
            while (scan.hasNextLine())
            {
                introFile += scan.nextLine() + "\n";
            }
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * This method shakes the zombie cup. You can also shake any other Node, but
     * you will only get the default side to side shake just because it was only
     * intended for the zombie cup. but it will still work.
     *
     * @param nTemp takes in the Node that you want to shake.
     */
    public void shakeNode(Node nTemp)
    {
        /*
         * This time line takes a keyframe and performs whatever animation
         * that keyframe contains.
         */
        final Timeline timeline = new Timeline();
        timeline.setCycleCount(8);
        timeline.setAutoReverse(true);
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(50),
                new KeyValue(nTemp.translateXProperty(), 20)));
        timeline.play();
    }

    /**
     * This method gets the zombie cup.
     *
     * @return The zombieCup ImageView.
     */
    public ImageView getCup()
    {
        return cupView;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }
}
