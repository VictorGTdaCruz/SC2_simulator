package victorcruz.sc2_simulator.Requisites;


import java.util.HashSet;

public class RequisitesHandler {

    private HashSet reqControl;


    public RequisitesHandler(){

        reqControl = new HashSet(35);

//      TESTED!
//        printElemets();
//        System.out.println(containsInControl(new String[]{}));
//        removeFromControl("40");
//        System.out.println(containsInControl(new String[]{"30", "40"}));
//        printElemets();

    }

    public void addToControl(String req) {
        if (!reqControl.contains(req))
            reqControl.add(req);
    }

    public void removeFromControl(String req){
        reqControl.remove(req);
    }

    public boolean containsInControl(String[] req){
        int aux= 0 ;
        for (; aux < req.length; aux++) {
            if (!reqControl.contains(req[aux]))
                return false;
        }

        return true;
    }

    public void printElemets(){
        System.out.println("Elements: " + reqControl.toString());
    }

}
