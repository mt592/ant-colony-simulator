
import java.util.ArrayList;
import java.util.Random;

import java.util.*;

public class Node {

    boolean activated=false;
    ArrayList<Ant> iterateAnts= new ArrayList<Ant>();
    int pheromone=0;
    ArrayList<Ant> centerList;
    ColonyNodeView nodeView;
    Colony colony;
    int x;
    int y;
    ArrayList<Ant> leaving= new ArrayList<Ant>();;
    ArrayList<Ant> joining= new ArrayList<Ant>();;
    int food;

    Node(ColonyNodeView colNodeV, int x, int y) {
        this.x = x;
        this.y = y;
        this.nodeView = colNodeV;
    }

    public void antEnters(Ant ant) {
        iterateAnts.add(ant);
        resetCounts();
    }

    public void antLeaves(Ant newAnt) {
        iterateAnts.remove(newAnt);
        resetCounts();
    }

    public void activateNode() {

        nodeView.showNode();
        int randFood;
        int randFoodAmt;
        randFood= NextRand.randomNumber(4)+1;
        randFoodAmt= NextRand.randomNumber(500)+500;
        if(randFood==1) {
            nodeView.setFoodAmount(randFoodAmt);
            this.setFood(randFoodAmt);
        }
        nodeView.setFoodAmount(randFoodAmt);

    }


    public void resetCounts() {
    
        nodeView.setFoodAmount(food);
        nodeView.setPheromoneLevel(pheromone);
        
        int numbSold= 0;
        int numbBala=0;
        int numbScout=0;
        int numbFor=0;

        for (int i = 0; i < iterateAnts.size(); i++) {
            if (iterateAnts.get(i).getTypeAnt()== "Soldier") {
                numbSold++;
            }
            else if (iterateAnts.get(i).getTypeAnt()== "Bala") {
                numbBala++;
            }
            else if (iterateAnts.get(i).getTypeAnt()== "Scout") {
                numbScout++;
            }
            else if (iterateAnts.get(i).getTypeAnt()== "Forager") {
                numbFor++;
            }
        }
        
        if (this.getX()==13 && this.getY()==13) {
            nodeView.setQueen(true);
        }
    
        nodeView.setSoldierCount(numbSold);
        nodeView.setBalaCount(numbBala);
        nodeView.setScoutCount(numbScout);
        nodeView.setForagerCount(numbFor);
       
        
    }

    public void antLeft() {
        for (int i=0; i<leaving.size(); i++) {
        	Ant nextAnt=leaving.get(i);
            iterateAnts.remove(nextAnt);
        }
      
        leaving.clear();
    }

    
    public void antJoined() {
    for (int j=0; j<joining.size(); j++) {
    	Ant nextAnt=joining.get(j);
        iterateAnts.add(nextAnt);
	    }
	    joining.clear();
    }
    
    
    
    public void centerList() {
        for (int j=0; j<centerList.size(); j++) {
        	Ant nextAnt=centerList.get(j);
        	centerList.add(nextAnt);
        }
        centerList.clear();
        }
    
    
    public void nodeAction(int nextTurn) {
        
        try {
  
        
	        for(int i=0; i<iterateAnts.size(); i++){
	            iterateAnts.get(i).advanceSim(nextTurn);
	
	        	}
        }
        catch (Exception e) {
        	
        }
        if (nextTurn > 0) {
        	if (nextTurn % 10 == 0) {
        		double pherLevel=getPheromone();
        		pherLevel=pherLevel/2;
                this.setPheromone((int) pherLevel);
        	}
        }
        
        antLeft();
        antJoined();
        resetCounts();

    }

    
    public ArrayList<Node> getNeighbors() {
    	ArrayList<Node> neigh = colony.getNeighboring(this);
        return neigh;
    }

    public void setPheromone(int pherAmount) {
        pheromone = pherAmount;
    }

    public int getPheromone() {
        return pheromone;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setColony(Colony colony) {
        this.colony = colony;
    }

    public void setFood(int foodAmt) {
        food = foodAmt;
    }

    public int getFood() {
        return food;
    }


    public boolean nodeActivated() {
        return activated;
    }

    public ArrayList<Ant> otherAnts() {
        ArrayList<Ant> otherAnt = new ArrayList<Ant>();
        for (int i = 0; i < iterateAnts.size(); i++) {
        	String typeInList=iterateAnts.get(i).getAntType();
            if (typeInList != "Bala") {
                otherAnt.add(iterateAnts.get(i));
            }
        }
        return otherAnt;
    }
    
    public boolean balaPresent() {
    	if (isBalaPresent().size()>0) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
    public ArrayList<Ant> isBalaPresent() {
        ArrayList<Ant> otherAnt = new ArrayList<Ant>();
        for (int i = 0; i < iterateAnts.size(); i++) {
        	String typeInList=iterateAnts.get(i).getAntType();
            if (typeInList == "Bala") {
                otherAnt.add(iterateAnts.get(i));
            }
        }
        return otherAnt;
    }

        
    public ArrayList<Ant> isBalaPresentCenter() {
        ArrayList<Ant> otherAnt = new ArrayList<Ant>();
        for (int i = 0; i < centerList.size(); i++) {
        	String typeInList=centerList.get(i).getAntType();
            if (typeInList == "Bala") {
                otherAnt.add(centerList.get(i));
            }
        }
        return otherAnt;
    }

}


