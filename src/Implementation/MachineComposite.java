package Implementation;
import java.util.*;

public class MachineComposite extends MachineComponent implements Observer{

    private Map<MachineComponent, Boolean> components = new HashMap<>();

    public void addComponent(MachineComponent mc) {
        mc.addObserver(this);

        if(mc.isBroken()){
            if(!broken && !checkBrokenComponents()){
                setChanged();
                notifyObservers(true);
            }
            components.put(mc, true);
        } else {
            components.put(mc, false);
        }
    }

    private boolean checkBrokenComponents() {
        for(Boolean isBroken: components.values()){
            if(isBroken) return true;
        }
        return false;
    }

    public boolean isBroken() {
        return broken || checkBrokenComponents();
    }

    private void breakComponent(MachineComponent mc) {
        boolean wasBroken = isBroken();
        components.put(mc, true);
        if (!wasBroken){
            setChanged();
            notifyObservers(true);
        }
    }

    private void repairComponent(MachineComponent mc){
        components.put(mc, false);
        if (!isBroken()){
            setChanged();
            notifyObservers(false);
        }
    }


    @Override
    public void update(Observable o, Object arg) {
        if((Boolean) arg){
            breakComponent((MachineComponent) o);
        } else {
            repairComponent((MachineComponent) o);
        }
    }
}
