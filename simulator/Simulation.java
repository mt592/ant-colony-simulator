

public class Simulation implements SimulationEventListener{

	AntSimGUI guiComp;
    int nextTurns= 0;
    int day= 1;
    int turnOfDay=0;
    Thread thread=null;
    int queenAlive=1;
    int clickedNorm=0;
    int clickedRun=0;
    int clickedSteps=0;
    Colony startColony;
     
    
    Simulation(AntSimGUI guiComp) {
        this.guiComp = guiComp;
        startColony = new Colony(new ColonyView(27, 27), this);
        guiComp.initGUI(startColony.showColonyView());
    }
    
   

    public void simulationEventOccurred(SimulationEvent simEvent) {
        if (simEvent.getEventType() == SimulationEvent.NORMAL_SETUP_EVENT && clickedNorm==0) {
            startColony.colonyDefault();
            clickedNorm=1;
        }
        if (simEvent.getEventType() == SimulationEvent.STEP_EVENT && clickedSteps==0) {
        	clickedSteps=1;
            advanceSim();
        }
        if (simEvent.getEventType() == SimulationEvent.RUN_EVENT && clickedRun==0 ) {
        	clickedRun=1;
            thread = new Thread() {
                public void run() {
                    advanceSim();
                }
            };
            thread.start();
        }
       
    }
  
    
    
    public void advanceSim() {
    	if(clickedRun==1) {
    		for(int i=0; i<=73000; i++) {
    			if(clickedSteps==1) {
    				clickedRun=0;
    				break;
    			}
    		if(queenAlive==1) {
    	    	   nextTurns++;
    	       	if(nextTurns%10==0) {
    	       		day++;
    	       	}
    	   		turnOfDay=nextTurns%10+1;

    	       	guiComp.setTime("Day "+ day + " Turn " + turnOfDay);
    	           startColony.advanceSimulation(nextTurns);
    	           try {
    	               Thread.sleep(300);
    	           } catch (InterruptedException ex) {
    	               Thread.currentThread().interrupt();
    	           }
    	    	   
    	       }
    	}
    	}
       
    		if(clickedSteps==1) {
    			if(queenAlive==1) {
     	    	   nextTurns++;
     	       	if(nextTurns%10==0) {
     	       		day++;
     	       	}
     	   		turnOfDay=nextTurns%10+1;

     	       	guiComp.setTime("Day "+ day + " Turn " + turnOfDay);
     	           startColony.advanceSimulation(nextTurns);
     	           try {
     	               Thread.sleep(300);
     	           } catch (InterruptedException ex) {
     	               Thread.currentThread().interrupt();
     	           }
    			}
    		
    		
    		clickedSteps=0;
    		
    	}
    	
    }

    
    public void gameOver() {
        System.out.println("Game Over");
        System.exit(0);

    }
}
