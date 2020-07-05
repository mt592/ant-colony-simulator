public class Ant {
	
	
	/*
	 * 	1. Each ant should be identified by a unique integer ID. The queen ant should have an ID value of 0. Other ants should be numbered in
		ascending order as they are hatched.
		2. All ant types (except for the queen) have a maximum life span of 1 year.
		3. Dead ants should be removed from the simulation.
		4. All ants are limited to one action per turn, with some exceptions that will be discussed later.
		5. All ants except Bala ants may only move in squares that have been revealed by scout ants; Bala ants may also move into squares
		that have not been revealed by scout ants.
		6. When moving, all ant types should move no more than 1 square per turn.

	 */
	
	public Ant() {}
	
	int squaresVisited;
	Node currentNode;
	Node node;
	int antID;
	int maxAge;
    boolean isAlive;
    int X;
    int Y;
    int lastTurn;
    int firstTurn;
	String antType;
    int age;
     

    public Ant(Node currentNode,  int antID, String antType, int X, int Y) {
    	this.currentNode=currentNode;
    	this.antType=antType;
    	this.antID=antID;
    	this.X=X;
    	this.Y=Y;
    }

    public void setSquaresVisited(int visits) {
    	this.squaresVisited=visits;
    }
    
    public int getSquaresVisited() {
    	return squaresVisited;
    }

    public void goTo(Node nextNode) {
        currentNode.antLeaves(this);
        nextNode.antEnters(this);
        currentNode= nextNode;
    }

    public void setAlive(boolean alive) {
		this.isAlive = alive;
	}

	public boolean getAlive() {
		return isAlive;
	}

    public void setFirstTurn(int curTurn) {
        firstTurn= curTurn;
    }

    public void setID(int id) {
        this.antID= id;
    } 
    
    public void setAge(int antAge) {
        age= antAge;
    } 
    
    public int getAge() {
    	return age;
    }
    
    public void setAntType(String antTy) {
    	antType=antTy;
    }
    
    public String getAntType() {
    	return antType;
    }
    
    public int getID() {
    	return antID;
    }
    
    public void setTypeAnt(String antType)	{
		this.antType = antType;
	}
	
	public String getTypeAnt()	{
		return antType;
	}
		
	public void setX(int gridX) {
		X = gridX;
	}
	
	public int getX() {
		return X;
	}

	public void advanceSim(int takeTurn) {
		
	}
	
	public void setY(int gridY) {
		Y = gridY;
	}
	
	public int getY() {
		return Y;
	}
	
	
}
