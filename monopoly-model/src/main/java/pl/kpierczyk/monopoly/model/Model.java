package pl.kpierczyk.monopoly.model;
import pl.kpierczyk.monopoly.model.mainMenuController.*;
import pl.kpierczyk.monopoly.model.gameController.*;
import pl.kpierczyk.monopoly.model.utilities.settings.*;









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
        beggining, mainMenu, inGame, quitting
    }

    



    /*****************************************/
    /*            Class Fields               */
    /*****************************************/

    private AppState state; // state of the whole app

    private final MainMenuController mainMenuController; // controller responsible for main menu
    private final GameController gameController; // controller responsible for in-game simulation

    private Settings settings;





    /*****************************************/
    /*             Constructor               */
    /*****************************************/

    public Model(String gameHome) {
        
        settings = new Settings();
        settings.readFromFile(gameHome);

        /* initializing app's state */
        state = AppState.beggining;

        /* initializing mainMenuController */
        mainMenuController = new MainMenuController(settings.getGameHome() + "\\lang\\" +
                                                    settings.getLanguage() +
                                                    "\\mainMenuFields.txt");

        /* initializing mainMenuController */
        gameController = new GameController();
    }





    /*****************************************/
    /*          Getters & setters            */
    /*****************************************/

    public AppState getState() {
        return state;
    }
    public void setState(AppState t_state) {
        state = t_state;
    }

    public MainMenuController getMainMenuController() {
        return mainMenuController;
    }
    public GameController getGameController() {
        return gameController;
    }

    public Settings getSettings() {
        return settings;
    }
    public void setSettings(Settings settings) {
        this.settings = settings;
    }




    
    /*****************************************/
    /*              Utilities                */
    /*****************************************/
}