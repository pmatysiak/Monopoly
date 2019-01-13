package pl.kpierczyk.monopoly.model.utilities.settings;

import pl.kpierczyk.monopoly.model.*;
import pl.kpierczyk.monopoly.model.utilities.Util;
import pl.kpierczyk.monopoly.model.utilities.settings.settingUtilities.SettingsWarnings;
import pl.kpierczyk.monopoly.model.utilities.settings.settingsKinds.BooleanSetting;
import pl.kpierczyk.monopoly.model.utilities.settings.settingsKinds.InRangeSetting;
import pl.kpierczyk.monopoly.model.utilities.settings.settingsKinds.SelectSetting;

import java.io.*;

// *******************************************//
// Setting class contains all setting
// which can be set during app is runinng.
// Instance of this class is preserved in
// theinstance of Model.
// *******************************************//

public class Settings {

    /*****************************************/
    /* Class Fields */
    /*****************************************/

    private final int settingsNumber = 4;

    private SelectSetting language;
    private SelectSetting resolution;
    private BooleanSetting fullscreen;
    private InRangeSetting soundLevel;

    private SettingsWarnings registeredWarnings;

    /*****************************************/
    /* Constructor */
    /*****************************************/

    // default constructor
    public Settings() {
        this.language =
            new SelectSetting(0, new String[] { "en", "pl" });
        this.resolution =
            new SelectSetting(0, new String[] { "1920x1080", "800x600" });
        this.fullscreen =
            new BooleanSetting(false);
        this.soundLevel =
            new InRangeSetting(50, new int[] { 0, 100 });

        this.registeredWarnings =
            new SettingsWarnings();
    }

    // full specified constructor
    public Settings(String gameHome, Integer language, Integer resolution, Boolean fullscreen, Integer soundLevel) {

        SettingsWarnings warnings = new SettingsWarnings();

        this.language =
            new SelectSetting(0, new String[] { "en", "pl" });
        this.resolution =
            new SelectSetting(0, new String[] { "1920x1080", "800x600" });
        this.fullscreen =
            new BooleanSetting(false);
                
        this.soundLevel =
            new InRangeSetting(50, new int[] { 0, 100 });

        if (!this.language.setValue(language)) {
            warnings.setLanguageWarning(true);
            this.language.setValue(0);
        }

        if (!this.resolution.setValue(resolution)) {
            warnings.setResolutionWarning(true);
            this.resolution.setValue(0);
        }

        this.fullscreen.setValue(fullscreen);

        if (!this.soundLevel.setValue(soundLevel)) {
            warnings.setLanguageWarning(true);
            this.soundLevel.setValue(50);
        }

        this.registeredWarnings = warnings;
    }


    //Copy constructor
    public Settings(Settings settings){
        this.language =
            new SelectSetting(settings.getLanguageSetting());
        this.resolution =
            new SelectSetting(settings.getResolutionSetting());
        this.fullscreen =
            new BooleanSetting(settings.getFullscreenSetting());
        this.soundLevel =
            new InRangeSetting(settings.getSoundSetting());
        
        this.registeredWarnings = new SettingsWarnings(settings.getRegisteredWarnings());
    }

    /*****************************************/
    /* Getters & setters */
    /*****************************************/


    public String getLanguage() {
        return language.getValue();
    }
    public Integer[] getResolution() {
        String width = "";
        String height = "";

        if (this.resolution.getValue().indexOf("x") == 3) {
            width = this.resolution.getValue().substring(0, 3);
            height = this.resolution.getValue().substring(4, 6);
        } else if (this.resolution.getValue().indexOf("x") == 4) {
            width = this.resolution.getValue().substring(0, 4);
            height = this.resolution.getValue().substring(5, 9);
        }

        return new Integer[] { Integer.parseInt(width), Integer.parseInt(height) };
    }
    public boolean isFullscreen() {
        return this.fullscreen.getValue();
    }
    public Integer getSoundLevel() {
        return soundLevel.getValue();
    }


    public SelectSetting getLanguageSetting(){
        return this.language;
    }
    public SelectSetting getResolutionSetting(){
        return this.resolution;
    }
    public BooleanSetting getFullscreenSetting(){
        return this.fullscreen;
    }
    public InRangeSetting getSoundSetting(){
        return this.soundLevel;
    }

    public boolean setLanguage(Integer language) {
        return this.language.setValue(language);
    }
    public boolean setResolution(Integer resolution) {
        return this.resolution.setValue(resolution);
    }
    public boolean setFullscreen(Boolean fullscreen) {
        return this.fullscreen.setValue(fullscreen);
    }
    public Boolean setSoundLevel(Integer soundLevel) {
        return this.soundLevel.setValue(soundLevel);
    }



    public SettingsWarnings getRegisteredWarnings() {
        return registeredWarnings;
    }
    public void setRegisteredWarnings(SettingsWarnings registeredWarnings) {
        this.registeredWarnings = registeredWarnings;
    }


    public int getSettingsNumber() {
        return settingsNumber;
    }
    public String getValueOf(int settingNumber){
        if(settingNumber <= 0)
            return this.language.toString();
        else if(settingNumber == 1)
            return this.resolution.toString();
        else if(settingNumber == 2)
            return this.fullscreen.toString();
        else
            return this.soundLevel.toString();
    }



    /*****************************************/
    /* Utilities */
    /*****************************************/

    public void reset() {
        this.language.setValue(0);
        this.resolution.setValue(0);
        this.fullscreen.setValue(false);
        this.soundLevel.setValue(50);
        this.registeredWarnings.clearFlags();
    }

    public boolean readFromFile(String configPath) {

        try {
            FileReader fileReader =
                new FileReader(configPath);
            BufferedReader bufferedReader =
                new BufferedReader(fileReader);


            // tmp string for reading language version from config.txt
            String line = null;

            // language initialization
            if ((line = bufferedReader.readLine()) != null) {
                switch (line) {
                case "en":
                    this.setLanguage(0);
                    break;
                case "pl":
                    this.setLanguage(1);
                    break;
                }
            }

            // resolution initialization
            if ((line = bufferedReader.readLine()) != null) {
                switch (line) {
                case "1920x1080":
                    this.setResolution(0);
                    break;
                case "800x600":
                    this.setResolution(1);
                    break;
                }
            }

            // fullscreen option initialization
            if ((line = bufferedReader.readLine()) != null) {
                switch (line) {
                case "on":
                    this.setFullscreen(true);
                    break;
                case "off":
                    this.setFullscreen(false);
                    break;
                }
            }

            // sound level initialization
            if ((line = bufferedReader.readLine()) != null) {
                this.setSoundLevel(Integer.parseInt(line));
            }

            bufferedReader.close();
        } 
        catch (FileNotFoundException ex) {
            System.out.println("Unable to find " + configPath);
            // default settings
            this.reset();
            return false;
        } 
        catch (IOException ex) {
            System.out.println("Unable to load settings from " + configPath);
            // default settings
            this.reset();
            return false;
        }

        return true;
    }


    public boolean writeToFile(String configPath) {

        try {
            FileWriter fileWriter =
                new FileWriter(configPath);
            PrintWriter printWriter =
                new PrintWriter(fileWriter);

            printWriter.print(this.getLanguageSetting().toString());
            printWriter.print(this.getResolutionSetting().toString());
            printWriter.print(this.getFullscreenSetting().toString());
            printWriter.print(this.getSoundSetting().toString());

            printWriter.close();
        } 
        catch (FileNotFoundException ex) {
            return false;
        } 
        catch (IOException ex) {
            return false;
        }

        return true;
    }
}