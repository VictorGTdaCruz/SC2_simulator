package victorcruz.sc2_simulator.Requisites;


import android.widget.Button;

import java.util.HashSet;

public class TechHandler {

    private HashSet unlockedTechControl;

    private Button[] buttons;

    public TechHandler(Button[] buttons){

        this.buttons = buttons;

        unlockedTechControl = new HashSet(35);

        resetButtonAlpha();
        addToControl(new String[]{"Hatchery", "Extractor", "EvolutionChamber", "SpawningPool", "Drone", "Overlord"});


//      TESTED!
//        printElemets();
//        System.out.println(containsInControl(new String[]{}));
//        removeFromControl("40");
//        System.out.println(containsInControl(new String[]{"30", "40"}));
//        printElemets();

    }

    public void addToControl(String[] tech) {
        for (String tec : tech) {
            if (!unlockedTechControl.contains(tec)) {
                unlockedTechControl.add(tec);
                setButtonAlpha(tec);
            }
        }
    }

    public void removeFromControl(String tech){
        unlockedTechControl.remove(tech);
    }

    public boolean containsInControl(String tech){
        //int aux= 0 ;
        //for (; aux < tech.length; aux++) {
            if (!unlockedTechControl.contains(tech))
                return false;
        //}

        return true;
    }

    public void printElemets(){
        System.out.println("Elements: " + unlockedTechControl.toString());
    }

    public void setButtonAlpha(String tech){

        //for (String tec : tech) {
            for (Button button : buttons) {
                if (tech.equals(button.getTag())){
                    button.setAlpha(1);
                    break;
                }
            }
        //}
    }

    public void resetButtonAlpha(){

        for (Button button : buttons){
            button.setAlpha(0.3f);
        }

    }

}
