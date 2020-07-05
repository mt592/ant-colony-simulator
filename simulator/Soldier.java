
import java.util.ArrayList;
import java.util.Random;

public class Soldier extends Ant {
	
	/*
	 * Scout Mode
			1. A soldier is in scout mode when it is in a square that does not contain any Bala ants.
			2. While in scout mode:
				1. If there are one or more Bala ants in one or more of the squares adjacent to the square the soldier is in, the
				soldier should move into any one of the squares containing a Bala ant.
				2. If there are no Bala ants in any of the adjacent squares, the soldier should move randomly.
	 * 
	 * 
	 * 
	 * Attack Mode
		1. A soldier is in attack mode when it is in a square that contains one or more Bala ants. Attack mode takes precedence
		over scout mode.
		2. While in attack mode, a soldier should attack any Bala ants present.
		3. If there are multiple Bala ants present, only one of them should be attacked.
		4. During an attack, there is a 50% chance the soldier kills the enemy ant; otherwise, the soldier misses and the enemy
		ant survives.
	

	 */
	
    ArrayList<Ant> antList;
	Node currentNode;
	int id;
	String antType;
	String antMode;
    ArrayList<Ant> antBala;
    Node jumpToNode; 
    int turn;
	
	 Soldier(Node node, int antID, String antType, int X, int Y) {
	    	this.currentNode=node;
	    	this.antType=antType;
	    	this.antID=antID;
	    	this.X=X;
	    	this.Y=Y;
	    	this.setMode("SCOUT");
			setTypeAnt(antType);

	    }


 

    
    public void setMode(String modeSet) {
    	this.antMode=modeSet;
    }
    
    public String getMode() {
    	return this.antMode;
    }

    public void attackMode() {

    	ArrayList<Ant> balaList=antBala;
    	int kill=NextRand.randomNumber(50)+1;
        if (kill<=25) {
        	int numbBala=balaList.size();
        	int randBala=NextRand.randomNumber(numbBala);
            currentNode.antLeaves(balaList.get(randBala));

            this.setMode("SCOUT");
        }
        else {

        }
    }
    
    public void advanceSim(int nextTurn) {
    	turn=this.getAge()+1;
        this.setAge(turn);

        antBala=currentNode.isBalaPresent();
        if (antBala.size() > 0) {
        	this.setMode("ATTACK");
        	attackMode();
        } 
        else {
        	currentNode.antLeaves(this);
        	Node jumpToNode;
            jumpToNode=scoutMode();
            currentNode=jumpToNode;
            currentNode.antEnters(this);

        }
        if (this.getAge() > 3650) {
        	currentNode.antLeaves(this);
     
        }
    }
    
    public Node scoutMode() {
    	Node jumpToNode;
		ArrayList<Node> adjacentList = currentNode.getNeighbors();
        ArrayList<Node> copy;

        for (int a=0; a<adjacentList.size(); a++){
        		Node next=adjacentList.get(a);
        		
        		if (nodeActivated(next)=="NO"){
	        		adjacentList.remove(next);
	        	}
        }
        
        copy=adjacentList; 
             
        int randNode= NextRand.randomNumber(adjacentList.size());
    	jumpToNode=copy.get(randNode);
        for (int b=0; b<adjacentList.size(); b++){
    		Node next=adjacentList.get(b);
    			
	        	if (next.balaPresent()){
	        		jumpToNode=next;
	 
	        	}
        }
 
        return jumpToNode;
    }
    
    public String nodeActivated(Node node) {
    	String active;
    	if(node.nodeActivated()) {
    		active="YES";
    	}
    	else {
    		active="NO";
    	}
    	return active;
    }

 

}
