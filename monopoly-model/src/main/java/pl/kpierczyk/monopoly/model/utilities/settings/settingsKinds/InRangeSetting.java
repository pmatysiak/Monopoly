package pl.kpierczyk.monopoly.model.utilities.settings.settingsKinds;

import pl.kpierczyk.monopoly.model.utilities.settings.Setting;

/**
 * 
 * 
 * @author  Krzysztof Pierczyk
 * @version 1.0
 * @since   1.0
 */
public class InRangeSetting implements Setting{

    int value;
    final int possibleValues[];

    public InRangeSetting(int value, int possibleValues[]){
        this.value = value;
        this.possibleValues = possibleValues;
    }

    public InRangeSetting(InRangeSetting inRangeSetting){
        this.value = inRangeSetting.value;
        this.possibleValues = inRangeSetting.possibleValues.clone();
    }

    @Override
    public Integer getValue() {
        return value;
    }
    @Override
    public boolean setValue(Object value) {
        if(value instanceof Integer){
            if((Integer) value >= possibleValues[0] &&
               (Integer) value <= possibleValues[1]){
                this.value = (Integer) value;
                return true;
            }
            else
                return false;
        }
        else
            return false;
    }

    @Override
    public Integer[] getPossibleValues() {
        Integer possibleValues[] = new Integer[this.possibleValues.length];
        for(int i = 0; i < possibleValues.length; i++){
            possibleValues[i] = Integer.valueOf(this.possibleValues[i]);
       }
       return possibleValues;
    }


    /*****************************************/
    /*              Utilities                */
    /*****************************************/

    public boolean nextValue(){
        if(this.value < this.possibleValues[1]){
            this.value = this.value + 1;
            return true;
        }
        else
            return false;
    }


    public boolean previousValue(){
        if(this.value > this.possibleValues[0]){
            this.value = this.value - 1;
            return true;
        }
        else
            return false;
    }


    @Override
    public String toString() {
        return Integer.toString(this.value);
    }
}