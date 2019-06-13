package Implementation;

public class Machine extends MachineComponent {
    @Override
    public boolean isBroken() { return broken; }

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
