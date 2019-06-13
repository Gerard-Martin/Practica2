package Tests;

import Implementation.Machine;
import Implementation.MachineComposite;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MachineCompositeTest {

    private MachineComposite root, mc1;
    private Machine m1;
    private TestObserver observer;

    @BeforeEach
    public void init(){
        root = new MachineComposite();
        mc1 = new MachineComposite();
        m1 = new Machine();
        observer = new TestObserver();
    }

    @Test
    public void mc_initial_not_broken(){
        assertFalse(root.isBroken());
    }

    @Test
    public void mc_set_broken(){
        root.setBroken();
        assertTrue(root.isBroken());
    }

    @Test
    public void notify_if_breaks(){
        root.addObserver(observer);
        root.setBroken();
        assertTrue(observer.wasNotified());
        assertTrue(root.isBroken());
    }

    @Test
    public void not_notified_if_already_broken(){
        root.setBroken();
        root.addObserver(observer);
        root.setBroken();
        assertFalse(observer.wasNotified());
        assertTrue(root.isBroken());
    }


    @Test
    public void notify_repair(){
        root.setBroken();
        root.addObserver(observer);
        root.repair();
        assertTrue(observer.wasNotified());
        assertFalse(root.isBroken());
    }


    @Test
    public void not_notified_if_repair_non_broken(){
        root.addObserver(observer);
        root.repair();
        assertFalse(observer.wasNotified());
        assertFalse(root.isBroken());
    }

    @Test
    public void not_notify_when_adding_non_broken_machines() {
        mc1.addComponent(m1);
        root.addComponent(mc1);
        root.addObserver(observer);
        assertFalse(observer.wasNotified());
        assertFalse(root.isBroken());
    }

    @Test
    public void notify_when_adding_broken() {
        mc1.setBroken();
        root.addObserver(observer);
        root.addComponent(mc1);
        assertTrue(observer.wasNotified());
        assertTrue(root.isBroken());
    }

    @Test
    public void notify_broken_tree(){
        mc1.addComponent(m1);
        root.addComponent(mc1);
        root.addObserver(observer);
        m1.setBroken();
        assertTrue(root.isBroken());
        assertTrue(mc1.isBroken());
        assertTrue(m1.isBroken());
        assertTrue(observer.wasNotified());

    }

    @Test
    public void notify_repair_tree(){
        mc1.addComponent(m1);
        root.addComponent(mc1);
        root.addObserver(observer);
        m1.setBroken();
        m1.repair();
        assertTrue(observer.wasNotified());
        assertFalse(root.isBroken());
        assertFalse(mc1.isBroken());
        assertFalse(m1.isBroken());
    }

    @Test
    public void machine_broken_still_broken_if_component_fixed(){
        mc1.addComponent(m1);
        root.addComponent(mc1);
        root.setBroken();
        mc1.setBroken();
        root.addObserver(observer);
        mc1.repair();
        assertFalse(observer.wasNotified());
        assertTrue(root.isBroken());
    }

    @Test
    public void break_subcomponent_already_broken_machine(){
        mc1.addComponent(m1);
        root.addComponent(mc1);
        root.setBroken();
        root.addObserver(observer);
        mc1.setBroken();
        assertFalse(observer.wasNotified());
    }
}