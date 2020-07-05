
import java.util.*;
public class Bala extends Ant {
	int turn;
	int age; 
	int numberBalas;
	Node currentNode;
	
	/*
	Each turn there is a 3% chance one Bala ant will appear in one of the squares at the boundary of the colony. You may choose
	to have Bala ants always enter at the same square (e.g., upper left corner), or you may have them enter randomly at any of
	the 106 squares on the edge of the colony.
	1.
	2. Once a Bala appears, it should remain in the environment until it is killed, or dies of old age.
	3. Bala ants should always move randomly.
	4. Bala ants may move into squares that have not yet been revealed by scout ants.
	If a Bala ant is in a square containing one or more friendly ants (scout, forager, soldier, queen), the Bala should attack one of
	those ants. The ant that is attacked can be selected at random, or you can pick which ant gets attacked.
	5.
	During an attack, there is a 50% chance a Bala kills the ant it attacks; otherwise, the Bala misses and the ant that is attacked
	survives.
	*/
	
    Bala(Node node, int antID, String antType, int X, int Y) {
    	this.currentNode=node;
    	this.antType=antType;
    	this.antID=antID;
    	this.X=X;
    	this.Y=Y;
    	 
    }

    public int antOccupied() {
    	int occupied;
    	if (currentNode.otherAnts().size()>0){
    		occupied=1;
    	}
    	else {
    		occupied=0;
    	}
    	return occupied;
    }

    public void advanceSim(int nextTurn) {
        turn++;
        int newAge=this.getAge()+1;
        this.setAge(newAge);
        
        if (this.getAge() < 3650) {
            if (antOccupied()==0) {
            	ArrayList<Node> neighboringNodes = currentNode.getNeighbors();
                Node jumpToNode;
         
                int arraySize=neighboringNodes.size();
                int whichNode=NextRand.randomNumber(arraySize);
                jumpToNode = neighboringNodes.get(whichNode);
                goTo(jumpToNode);
                if(jumpToNode.getX()==13 && jumpToNode.getY()==13) {
            		System.out.println("The queen was attacked.");
                    currentNode.colony.colonySimulation.gameOver();
                }

            }
             else {
            	 ArrayList<Ant> antToAttack = currentNode.otherAnts();
                 int won;
                 int kill=NextRand.randomNumber(10);
        
                 if (kill<5) {
                 	won=1;
                    currentNode.antLeaves(antToAttack.get(0));
                 }
                 else {
                 	won=0;
                 }
                
              
            }
        }
        else {
            currentNode.antLeaves(this);
        }
    }
    

    public void goTo(Node node) {
        currentNode.antLeaves(this);
        node.antEnters(this);
        currentNode= node;

    }
    
    public void setAge(int turn) {
    	age=turn;
    }
    
    public int getAge() {
    	return age;
    }
    
 
  

   
   
}