
import java.util.ArrayList;
public class Colony {
	
	
    ColonyNodeView colonyNodeViews;
	ArrayList<Node> trail;
    ColonyView colView;
    Node nodes;
    Simulation colonySimulation;
    Node [][] layout;
    ArrayList<Ant> antList;
    int id= 0;
    Node lastNode;
    
    
    Colony(ColonyView view, Simulation sim) {
    	this.colView = view;
        this.colonySimulation = sim;
        layout= new Node[27][27];
    }
    
    public ArrayList<Node> getNeighboring(Node node) {
        ArrayList<Node> neighboring= new ArrayList<Node>();
    	int StartX= node.getX();
        int StartY= node.getY();

        try {
        //Left upper corner
        neighboring.add(layout[StartX-1][StartY-1]);
        }
        catch(Exception e) {
        	
        }
        //Upper middle
        try {
        neighboring.add(layout[StartX][StartY-1]);
        }
        catch(Exception e) {
        	
        }
        try {
        //Right upper corner
        neighboring.add(layout[StartX+1][StartY-1]);
    	}
        catch(Exception e) {
        	
        }
        try {
        //Right middle
        neighboring.add(layout[StartX+1][StartY]);
        }
        catch(Exception e) {
        	
        }
        //Right bottom corner
        try {
        neighboring.add(layout[StartX+1][StartY+1]);
        }
        catch(Exception e) {
        	
        }
        try {
        //Bottom middle
        neighboring.add(layout[StartX][StartY+1]);
        }
        catch(Exception e) {
        	
        }
        try {
        //Left bottom corner
        neighboring.add(layout[StartX-1][StartY+1]);
        }
        catch(Exception e) {
        	
        }
        try {
        //Left middle
        neighboring.add(layout[StartX-1][StartY]);
        }
        catch(Exception e) {
        	
        }
        return neighboring;
    }

  

    public ColonyView showColonyView() {
        return colView;
    }

    public void advanceSimulation(int nextTurn) {
    	int x;
    	int y;
        for (y= 0; y<=26; y++) {
            for (x = 0; x<=26; x++) {
                layout[x][y].nodeAction(nextTurn);
            }
        }

    }
    
    public void colonyDefault() {
    	int StartX=13;
        int StartY=13;
    	int x;
    	int y;
    	id++;
    	
    	
        for (x= 0; x<= 26; x++) {
            for (y = 0; y<= 26; y++) {
                colonyNodeViews = new ColonyNodeView();
                nodes = new Node(colonyNodeViews, x, y);
                colView.addColonyNodeView(colonyNodeViews, x, y);
                
                nodes.setColony(this);
                layout[x][y]=nodes;

                colonyNodeViews.setID("Node ID: " + x + " - " + y);
                
                if (x== StartX && y== StartY) {
                    Queen initializeQueen= new Queen(nodes, 0,  "Queen" ,13 ,13);
                    initializeQueen.setAntType("Queen");
                    System.out.println(initializeQueen.getAntType());
                    nodes.antEnters(initializeQueen);
                    
                    int antID=0;
                    int numberHatches;
                    for (numberHatches=0; numberHatches< 10; numberHatches++) {
                    	antID++;
                        initializeQueen.originalHatch(new Soldier(nodes, antID, "Soldier", 13, 13));
                    }
                    
                        
                    int numberHatches2;
                    antID=11;
                    for (numberHatches2=0; numberHatches2<50; numberHatches2++) {
                    	antID++;
                    	Forager forager=new Forager(nodes, antID, "Forager", 13, 13);
    					initializeQueen.originalHatch(forager);
    					forager.setMode("FORAGE");
                    }
                
                    antID=50;
                    antID++; initializeQueen.originalHatch(new Scout(nodes, antID, "Scout", 13, 13));
                    antID++; initializeQueen.originalHatch(new Scout(nodes, antID, "Scout", 13, 13));
                    antID++; initializeQueen.originalHatch(new Scout(nodes, antID, "Scout", 13, 13));
                    antID++; initializeQueen.originalHatch(new Scout(nodes, antID, "Scout", 13, 13));

                }
                if (x==(StartX-1) | x==(StartX+1) | x==StartX) {
                	if(y==(StartY-1) | y==(StartY+1) | y==StartY) {
                		nodes.activateNode();
                		if (!(x==StartX && y==StartY)) {
                			nodes.setFood(0);
        					colonyNodeViews.setFoodAmount(0);
        					colonyNodeViews.setBalaCount(0);
        					colonyNodeViews.setForagerCount(0);
        					colonyNodeViews.setScoutCount(0);
        					colonyNodeViews.setSoldierCount(0);
        					colonyNodeViews.setPheromoneLevel(0);;

                		}
                		if(x==StartX && y==StartY) {
                			nodes.setFood(1000);
        					colonyNodeViews.setFoodAmount(1000);
                		}
                	}
                }
            }
        }
    }
    
   

}
