

public class Driver {
   
	public static void main(String [] args) {
        AntSimGUI instance;
        instance= new AntSimGUI();
        Simulation startSim= new Simulation(instance);
        instance.addSimulationEventListener(startSim);
    }
}
