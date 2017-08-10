package victorcruz.sc2_simulator.Supply;

import android.widget.TextView;


public class SupplyHandler {

    // android components
    private TextView supplyTextView, supplyMaxTextView;


    private int supply, supplyMax;


    public SupplyHandler(TextView supplyTextView, TextView supplyMaxTextView){

        supply = 12;
        supplyMax = 14;

        this.supplyTextView = supplyTextView;
        this.supplyMaxTextView = supplyMaxTextView;

    }

    // used on makebuilding for zerg
    public void decreaseSupply(int amount){
        supply = supply - amount;
        supplyTextView.setText(Integer.toString(supply));
    }

    public void increaseSupply(int amount){
        supply = supply + amount;
        supplyTextView.setText(Integer.toString(supply));
    }

    public void increaseSupplyMax(int amount){
        supplyMax = supplyMax + amount;
        supplyMaxTextView.setText(Integer.toString(supplyMax));

    }

    public int getSupply() {
        return supply;
    }

    public int getSupplyMax() {
        return supplyMax;
    }
}
