package Tests;


import Implementation.Machine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;



class MachineTest {
    private Machine machine;

    @BeforeEach
    public void init() {
        machine = new Machine();
    }

    @Test
    public void new_machine_not_broken(){
        assertFalse(machine.isBroken());
    }

    @Test
    public void set_machine_broken(){
        machine.setBroken();
        assertTrue(machine.isBroken());
    }

    @Test
    public void repair_machine_not_broken(){
        machine.repair();
        assertFalse(machine.isBroken());
    }

    @Test
    public void set_machine_broken_twice(){
        machine.setBroken();
        machine.setBroken();
        assertTrue(machine.isBroken());
    }

    @Test
    public void repair_broken_machine(){
        machine.setBroken();
        machine.repair();
        assertFalse(machine.isBroken());
    }
}