package Implementation;

import java.util.Observable;

public abstract class MachineComponent extends Observable {
    protected boolean broken = false;

    public void setBroken(){
        boolean wasBroken = isBroken();
        broken = true;
        if(!wasBroken){
            setChanged();
            notifyObservers(broken);
        }
    }

    public void repair(){
        boolean wasBroken = isBroken();
        broken = false;
        if(wasBroken){
            setChanged();
            notifyObservers(broken);
        }
    }
    public abstract boolean isBroken();
}
