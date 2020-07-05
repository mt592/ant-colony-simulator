import java.util.ArrayList;
import java.util.Random;

public class Scout extends Ant {
	

	/*
	 * 1. Scouts should always randomly pick one of the eight possible directions of movement when it is their turn to do something.
			a. If the chosen square is open, the scout should simply move into that square.
			b. If the chosen square is closed, the scout should move into that square and the contents of that square should be
		revealed.
	
	  2. Whenever a closed square is revealed, there is a chance of there being food in the square, according to the following
		frequency:
			a. There is a 25% chance that the square will contain a random amount of food between 500 and 1000 units.
			b. The other 75% of the time the square is empty.
			c. You can predetermine the contents of all the squares at the beginning of the simulation, or you can dynamically
			determine the contents of each square as it is opened.

	 */

	Node currentNode;
	
	Scout(Node node, int antID, String antType, int X, int Y) {
    	this.currentNode=node;
    	this.antType=antType;
    	this.antID=antID;
    	this.X=X;
    	this.Y=Y;
    }

   


    public void advanceSim(int curTurn) {
 
    	setAge(getAge()+1); 
    	if (getAge()>3650) {
            currentNode.antLeaves(this);

    	}
    	else {
            
            int moveGen;
            moveGen=NextRand.randomNumber(8)+1;
            ArrayList<Node> adjacentList;
            adjacentList= currentNode.getNeighbors();
            Node destination;
            destination= adjacentList.get(moveGen);
            currentNode.antLeaves(this);
            currentNode= destination;
            this.setX(currentNode.getX());
            this.setY(currentNode.getY());

            currentNode.antEnters(this);
            currentNode.activateNode();
            
    	}
       
        

    }

  

}
