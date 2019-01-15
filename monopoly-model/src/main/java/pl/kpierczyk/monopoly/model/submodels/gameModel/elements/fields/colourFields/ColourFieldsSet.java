package pl.kpierczyk.monopoly.model.submodels.gameModel.elements.fields.colourFields;

import pl.kpierczyk.monopoly.model.submodels.gameModel.elements.Player;

/**
 * Class representing set of ColourFields with the same colour. This class make
 * it easier to manipulate captive fields, delivering information about
 * monopolization, and possibility of building next apartment/hotel.
 * 
 * @author Krzysztof Pierczyk
 * @version 1.0
 * @since 1.0
 * @see ColourField
 */
public class ColourFieldsSet {

    /** Colour of the fields in set. */
    private final int colour;

    /** Set of CoolourField */
    private ColourField first = null;
    private ColourField second = null;
    private ColourField third = null;

    /** Statement about if group is monopolized. */
    private boolean captive;

    /** Owner of the group. Null if no monopolist. */
    private Player owner;

    /**
     * Initializes sets of ColourFields with the same colour.
     * 
     * @param colour
     * @see ColourField
     */
    public ColourFieldsSet(int colour) {
        this.colour = colour;
    }

    /**
     * @return colour
     */
    public int getColour() {
        return colour;
    }

    /**
     * Returns first field from the group.
     * 
     * @return first
     */
    public ColourField getFirst() {
        return first;
    }

    /**
     * Returns second field from the group.
     * 
     * @return second
     */
    public ColourField getSecond() {
        return second;
    }

    /**
     * Returns third field from the group.
     * 
     * @return third
     */
    public ColourField getThird() {
        return third;
    }

    /**
     * States if the set is captive by one player.
     * 
     * @return captive
     */
    public boolean isCaptive() {
        return captive;
    }

    /********************************/
    /* Utilities */
    /********************************/

    /**
     * Return owner of the whole set. Null returned if not owner exists.
     * 
     * @return owner
     */
    public Player getMonopolist() {
        if (isCaptive())
            return owner;
        else
            return null;
    }

    /**
     * Updates information about monopolisation and sets values of owner and
     * captive.
     * 
     * @return updated information about monopolisation of the set.
     * @see Player
     */
    public boolean updateOwner() {
        Player firstOwner = first.getOwner();
        Player secondOwner = second.getOwner();
        Player thirdOwner = third.getOwner();

        if (firstOwner != null && secondOwner != null && thirdOwner != null) {
            if (firstOwner == secondOwner && secondOwner == thirdOwner && firstOwner == thirdOwner) {
                this.captive = true;
                this.owner = firstOwner;
                return true;
            } else {
                this.captive = false;
                this.owner = null;
                return false;
            }

        } else {
            this.captive = false;
            this.owner = null;
            return false;
        }

    }

    /**
     * Check if pointed field can be upbuilt. Throws an exception
     * if pointed field is not i the set.
     * 
     * @param   field
     * @return  statement if field can be downbuilt
     * @throws  Exception
     * @see     ColourField
     */
    public boolean canUpbuild(ColourField field) throws Exception {
        if(this.captive){
            if (field == first) {
                if(!first.isPledged() && !second.isPledged() && !third.isPledged()){
                    if (field.isHotel())
                        return false;
                    else {
                        int apartmentsNumber = field.getApartmentsNumber();
                        if (apartmentsNumber <= second.getApartmentsNumber()
                                && apartmentsNumber <= third.getApartmentsNumber())
                            return true;
                        else return false;
                    }
                } else return false;
            }
            else if (field == second) {
                if(!first.isPledged() && !second.isPledged() && !third.isPledged()){
                    if (field.isHotel())
                        return false;
                    else {
                        int apartmentsNumber = field.getApartmentsNumber();
                        if (apartmentsNumber <= first.getApartmentsNumber()
                                && apartmentsNumber <= third.getApartmentsNumber())
                            return true;
                        else return false;
                    }
                } else return false;
            }
            else if (field == third) {
                if(!first.isPledged() && !second.isPledged() && !third.isPledged()){
                    if (field.isHotel())
                        return false;
                    else {
                        int apartmentsNumber = field.getApartmentsNumber();
                        if (apartmentsNumber <= first.getApartmentsNumber()
                                && apartmentsNumber <= second.getApartmentsNumber())
                            return true;
                        else return false;
                    }
                }else return false;
            } 
            else throw new Exception();
        }else return false;
    }

/**
     * Check if pointed field can be downbuilt. Throws an exception
     * if pointed field is not i the set.
     * 
     * @param   field
     * @return  statement if field can be downbuilt
     * @throws  Exception
     * @see     ColourField
     */
    public boolean canDownbuild(ColourField field) throws Exception {
        if (field == first) {
            if(field.isBuilt()){
                if(!first.isPledged() && !second.isPledged() && !third.isPledged()){
                    if (field.isHotel())
                        return true;
                    else {
                        int apartmentsNumber = field.getApartmentsNumber();
                        if (apartmentsNumber >= second.getApartmentsNumber()
                                && apartmentsNumber >= third.getApartmentsNumber())
                            return true;
                        else 
                            return false;
                    }
                } else return true;
            } else return false;

        }
        else if (field == second) {
            if(field.isBuilt()){
                if(!first.isPledged() && !second.isPledged() && !third.isPledged()){
                    if (field.isHotel())
                        return true;
                    else {
                        int apartmentsNumber = field.getApartmentsNumber();
                        if (apartmentsNumber >= first.getApartmentsNumber()
                                && apartmentsNumber >= third.getApartmentsNumber())
                            return true;
                        else 
                            return false;
                    }
                } else return true;
            } else return false;
        }
        else if (field == third) {
            if(field.isBuilt()){
                if(!first.isPledged() && !second.isPledged() && !third.isPledged()){
                    if (field.isHotel())
                        return true;
                    else {
                        int apartmentsNumber = field.getApartmentsNumber();
                        if (apartmentsNumber >= first.getApartmentsNumber()
                                && apartmentsNumber >= second.getApartmentsNumber())
                            return true;
                        else 
                            return false;
                    }
                } else return true;
            } else return false;
        } 
        else throw new Exception();
    }





    /**
     * 
     * @return statement about if something is built on fields from the set.
     */
    public boolean isBuilt(){
        if(first.isBuilt() || second.isBuilt() || third.isBuilt())
            return true;
        else return false;
    }



}