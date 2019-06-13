package Tests;

import java.util.Observable;
import java.util.Observer;

public class TestObserver implements Observer {
    private boolean notified = false;

    @Override
    public void update(Observable o, Object arg) {
        notified = true;
    }

    public boolean wasNotified() {
        return notified;
    }

}
