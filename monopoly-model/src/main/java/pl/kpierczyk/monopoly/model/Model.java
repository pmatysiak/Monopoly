package pl.kpierczyk.monopoly.model;

import pl.kpierczyk.monopoly.model.mainMenuController.*;
import pl.kpierczyk.monopoly.model.gameController.*;
import pl.kpierczyk.monopoly.model.utilities.settings.*;
import java.net.*;
import java.io.*;

//*******************************************//
//
//
//
//
//
//
//
//*******************************************//

public class Model {

    public enum AppState {
        intro, mainMenu, inGame
    }

    /*****************************************/
    /* Class Fields */
    /*****************************************/

    private AppState state; // state of the whole app

    private IntroController introController;
    private MainMenuController mainMenuController; // controller responsible for main menu
    private GameController gameController; // controller responsible for in-game simulation

    final private Settings settings;




    /*****************************************/
    /* Constructor */
    /*****************************************/

    public Model() {

        /* initializing app's state */
        this.state = AppState.intro;

        /* initializing settings */
        this.settings = new Settings();
        this.settings.readFromFile("config.txt");

        /* initializing begginingController */
        String introMoviePath = "/lang/" + this.settings.getLanguage() + "/movies/begginingMovie.mp4";
        String introOmmitButtron = "/lang/ommitIntroButton.txt";
        introController = new IntroController(introMoviePath, introOmmitButtron);


        /* initializing mainMenuController */
        this.mainMenuController = new MainMenuController(settings);

        /* initializing mainMenuController */
        gameController = new GameController();

    }






    /*****************************************/
    /* Getters & setters */
    /*****************************************/

    public AppState getState() {
        return state;
    }
    private void setState(AppState state) {
        this.state = state;
    }

    public MainMenuController getMainMenuController() {
        return mainMenuController;
    }
    public GameController getGameController() {
        return gameController;
    }
    public IntroController getIntroController() {
        return introController;
    }

    public Settings getSettings() {
        return settings;
    }







    /*****************************************/
    /* Utilities */
    /*****************************************/

    public boolean finishBeggining() {
        if(getState() == AppState.intro){
            setState(AppState.mainMenu);
            this.introController = null;
            this.mainMenuController = new MainMenuController(settings);
            return true;
        }
        else
            return false;
    }


    public boolean quitGame() {
        if(getState() == AppState.inGame){
            setState(AppState.mainMenu);
            this.gameController = null;
            this.mainMenuController = new MainMenuController(settings);
            return true;
        }
        else
            return false;
    }


    public boolean startGame() {
        if(getState() == AppState.mainMenu){
            setState(AppState.inGame);
            this.mainMenuController = null;
            this.gameController = new GameController();
            return true;
        }
        else
            return false;
    }


    public boolean loadGame(GameController gameController) {
        if(getState() == AppState.mainMenu){
            setState(AppState.inGame);
            this.mainMenuController = null;
            this.gameController = gameController;
            return true;
        }
        else
            return false;
    }
}