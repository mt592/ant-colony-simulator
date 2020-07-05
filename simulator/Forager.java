import java.util.ArrayList;
import java.util.Iterator;

public class Forager extends Ant {
		
	
	/*FORAGE MODE
	 * a. Foragers are considered to be in forage mode whenever they are not carrying food.
	   b. In Forage Mode, foragers should always move to the adjacent square containing the highest level of pheromone,
		except:
			i. If more than one adjacent square has the same level of pheromone they should randomly pick one of those
				squares.
			ii. When following a pheromone trail a forager should never move into the square it just came from unless it has
				no other choice.
			iii. Depending on how you implement your movement algorithm, it is possible for a forager to get stuck in a loop,
				traveling round and round the same squares without getting anywhere. Try to detect when this happens, and
				prevent the endless looping.
		c. Foragers should maintain a history of their movement, to be used when they need to return to the nest.
		d. When a forager enters a square containing food, it should pick up 1 unit of food, unless it is already carrying food.
		e. When a forager picks up a unit of food, it enters return-to-nest mode.
		f. Foragers should never pick up food from the square containing the queen.
		g. After a forager has picked up 1 unit of food, it should not move again until the next turn.



		RETURN TO NEST MODE
		a. When a forager is carrying food, it should retrace its steps exactly back to the colony entrance; i.e., it should
		backtrack whatever path it took to get to the food.
		b.Foragers should ignore pheromone in this mode; i.e., they should not move to the adjacent square containing
		the highest level of pheromone.
		c. Foragers should not move randomly in this mode.
		d.Foragers should deposit 10 units of pheromone in each square along the way back to the colony entrance,
		including the square in which the food was found, but excluding the colony entrance (the queen's square).
		e.Foragers should only deposit pheromone in a given square if the current pheromone total in the square is <
		1000.
		f. A forager may deposit pheromone in one square, and move to a new square in the same turn.
		g. When a forager reaches the colony entrance, it should add the food it is carrying to the food supply in that
		square, in the same turn in which it entered the colony entrance.
		h. Foragers should not move out of the colony entrance on the same turn they deliver food there.
		i. If a forager dies while carrying food, the food it was carrying should remain in the square in which the forager
		died.
		j.When a forager has deposited food at the nest, the forager re-enters forage mode, and its movement history
		should be reset.

	 */

	String antMode;
    ArrayList<Node> trail;
    int goBack;
    int turn;
	int age;
	Node currentNode;
	Node lastNode;
	Node def;
	ColonyNodeView nodeView;

    public Forager(Node node, int antID, String antType, int X, int Y) {
    	this.currentNode=node;
    	this.antType=antType;
    	this.antID=antID;
    	this.X=X;
    	this.Y=Y;

        trail = new ArrayList<Node>();
        setMode("FORAGE");
        setTypeAnt(antType);
        trail.add(currentNode);

    }
    
    public void setMode(String modeSet) {
    	this.antMode=modeSet;
    }
    
    public String getMode() {
    	return this.antMode;
    }
    
    public void setAge(int turn) {
    	age=turn;
    }
    
    public int getAge() {
    	return age;
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
    

    public void goTo(Node node) {
        
    }


    public void advanceSim(int nextTurn) {
    	if(this.getAge()==0) {
    		def= new Node(nodeView, 13, 13);
    		trail.add(def);
    	}
    	
    	trail.add(currentNode);
    	turn++;
        this.setAge(turn);        
        
        if (this.getAge()>3650) {
            currentNode.antLeaves(this);
	    }
     
       
        if (this.getMode()=="NEST") {
            returnNestMode();
        }
        else {
        	forageMode();
        }

    }
    

	public void forageMode() {
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

        for (int b=0; b<adjacentList.size(); b++){
    		Node next=adjacentList.get(b);

	        	if (trail.contains(next)){
	        		adjacentList.remove(next);
	        	}

	        	
        }
        
        if (adjacentList.size()== 0) {
            adjacentList = copy;
        }
   

        int highestLevel=0;
        int tempHighest;
        int highestKey=-1;
        for (int i = 0; i < adjacentList.size(); i++) {
        	tempHighest=adjacentList.get(i).getPheromone();
        	if (tempHighest>highestLevel) {
        		highestLevel=tempHighest;
        		highestKey=i;
        	}
        }

        
        if (highestKey==-1) {
        	highestKey=NextRand.randomNumber(adjacentList.size());	
        }
        
        jumpToNode=(adjacentList.get(highestKey));

        currentNode.antLeaves(this);
        currentNode= jumpToNode;
        
        
        trail.add(currentNode);
        currentNode.antEnters(this);
        if (currentNode.getX()!=13 && currentNode.getY()!=13) {
        	if(currentNode.getFood()>1) {
        		int foodCount;
        		foodCount=currentNode.getFood()-1;
        		currentNode.setFood(foodCount);
        		this.setMode("NEST");
        		return;
        		
        	}
        }
        
	}
	
	public void returnNestMode() {
    	for (int i=trail.size()-1; i>=0; i--) {

            if (trail.get(i).getX()!=13 && trail.get(i).getY()!=13) {
            	int pherLevel=trail.get(i).getPheromone();
                if (pherLevel < 1000) {
                	int newPherLevel=trail.get(i).getPheromone();
                	newPherLevel=newPherLevel+10;
                	trail.get(i).setPheromone(newPherLevel);
                }
            } 
            goTo(trail.get(i));
                 
    	}
        trail.clear();
        def= new Node(nodeView, 13, 13);
		trail.add(def);
    	this.setMode("FORAGE");
	}

}





