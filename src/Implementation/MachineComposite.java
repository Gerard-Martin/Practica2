package Implementation;

import java.util.ArrayList;
import java.util.List;

public class MachineComposite extends MachineComponent{
    private List<MachineComponent> components = new ArrayList<>();

    public void addComponent(MachineComponent mc) {
        components.add(mc);
    }

    @Override
    public boolean isBroken() {
        if (broken) { return true; }
        for(MachineComponent mc: components) {
            if (mc.isBroken()) { return true; }
        }
        return false;
    }

    @Override
    public void setBroken(){
        boolean wasBroken = isBroken();
        broken = true;
        if(!wasBroken){
            setChanged();
            notifyObservers(broken);
        }
    }

    @Override
    public void repair(){
        boolean wasBroken = isBroken();
        broken = false;
        if(wasBroken){
            setChanged();
            notifyObservers(broken);
        }
    }


}
