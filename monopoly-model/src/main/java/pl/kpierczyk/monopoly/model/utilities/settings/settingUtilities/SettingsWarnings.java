package pl.kpierczyk.monopoly.model.utilities.settings.settingUtilities;



/**
 * SettingWarnings class is defined for containg boolean statements describing
 * if a prticular setting was asigned correctly or it was asigned in the default
 * way.
 * 
 * @author  Krzysztof Pierczyk
 * @version 1.0
 * @since   1.0
 */
public class SettingsWarnings {

    boolean languageWarning;
    boolean resolutionWarning;
    boolean soundLevelWarning;


    public SettingsWarnings() {
        languageWarning = false;
        resolutionWarning = false;
        soundLevelWarning = false;
    }

    public SettingsWarnings(boolean languageWarning_t, boolean resolutionWarning_t,
            boolean soundLevelWarning_t) {
        languageWarning = languageWarning_t;
        resolutionWarning = resolutionWarning_t;
        soundLevelWarning = soundLevelWarning_t;
    }

    public SettingsWarnings(SettingsWarnings settingsWarnings){
        this.languageWarning = settingsWarnings.languageWarning;
        this.resolutionWarning = settingsWarnings.resolutionWarning;
        this.soundLevelWarning = settingsWarnings.soundLevelWarning;
    }

    public boolean isLanguageWarning() {
        return languageWarning;
    }
    public boolean isResolutionWarning() {
        return resolutionWarning;
    }
    public boolean isSoundLevelWarning() {
        return soundLevelWarning;
    }


    public void setLanguageWarning(boolean languageWarning) {
        this.languageWarning = languageWarning;
    }
    public void setResolutionWarning(boolean resolutionWarning) {
        this.resolutionWarning = resolutionWarning;
    }
    public void setSoundLevelWarning(boolean soundLevelWarning) {
        this.soundLevelWarning = soundLevelWarning;
    }





    /*****************************************/
    /*              Utilities                */
    /*****************************************/

    public void clearFlags() {
        this.languageWarning = false;
        this.resolutionWarning = false;
        this.soundLevelWarning = false;
    }
}