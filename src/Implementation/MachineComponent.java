package Implementation;

import java.util.Observable;

public abstract class MachineComponent extends Observable {
    protected boolean broken = false;

    public abstract void setBroken();
    public abstract void repair();
    public abstract boolean isBroken();
}
