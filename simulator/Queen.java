
import java.util.*;

public class Queen extends Ant {
	

	/*
	1. The queen never moves from her square (i.e., she remains in the same square for the entire simulation).
	2. The queen's maximum lifespan is 20 years.
	3. The queen hatches new ants at a constant rate of 1 ant/day (i.e., 1 ant every 10 turns).
	4. New ants should always be hatched on the first turn of each day.
	5. The type of ant that is hatched should be determined randomly according to the initial frequencies listed below. You may
	change these frequencies as you see fit — these are simply suggestions for a starting point.
		a. Forager - 50%
		b. Scout - 25%
		c. Soldier - 25%
	6. The queen should consume 1 unit of the food in her chamber on each turn, including the turn in which she hatches a new ant.
	7. If the food level in the queen's square is zero when the queen tries to eat, the queen dies of starvation
   */
	
	
    public Queen(Node currentNode, int antID, String antType, int X, int Y) {
    	this.currentNode=currentNode;
    	this.antType=antType;
    	this.antID=antID;
    	this.X=X;
    	this.Y=Y;
    }
 
    int antID;
    int age;
    int ID;
    int takeTurn;
    int turn;
    String antType;
    boolean attacked;
    boolean tenth;
    ArrayList<Ant> centerList;
    
    public void queenDies(int turn) {
    
    	if(currentNode.getFood()==0) {
    		System.out.println("The queen ant died from starvation.");
            currentNode.colony.colonySimulation.gameOver();
    	}
    		
    	if(turn>73000) {
    		System.out.println("The queen ant died of old age.");
            currentNode.colony.colonySimulation.gameOver();
    	}
    	
    	
    }
    
    public String randomAntType() {
    	String antTypeName="";
    
    	Random antType = new Random();
        int randNum = antType.nextInt(100)+1;
    	
        if(randNum>0 && randNum<25) {
            antTypeName="Scout";
        }
        else if(randNum>24 && randNum<75) {
        	antTypeName="Forager";        
        	}
        else{
        	antTypeName="Soldier";        
        	}
        return antTypeName;
    	
    }
    
   
    
    public void advanceSim(int nextTurn) { 
        turn=this.getAge()+1;
        this.setAge(turn);
        setTenthTurn(turn);
        
        queenDies(turn);
        
        int foodAmt= currentNode.getFood();
        foodAmt--;
                
        int randomAppearance=NextRand.randomNumber(100)+1;
        currentNode.setFood(foodAmt);
        
        if (randomAppearance <=3 ) {
    
        	Node balaLocation = currentNode.colony.layout[0][0];
        	antID++;
            Ant ant = new Bala(balaLocation, antID, "Bala", 0, 0);
            balaLocation.antEnters(ant);
            ant.setAge(1);
        }
        
        if (getTenthTurn()) {
            hatch();
    
        }

    }
    
    public void setTenthTurn(int turnDay) {
    	if (turnDay%10== 0) {
    		tenth=true;
    	}
    }
    
    public boolean getTenthTurn() {
    	return tenth;
    }
    
    public void setAntType(String antTy) {
    	antType=antTy;
    }
    
    public String getAntType() {
    	return antType;
    }
    
    public void originalHatch(Ant ant) {

        antID++;
        Ant hatchedAnt;
        
        if(ant.getTypeAnt()=="Scout") {
            hatchedAnt= new Scout(currentNode, antID, "Scout", 13, 13);
            
        } 
        else if(ant.getTypeAnt()=="Forager") {
            hatchedAnt= new Forager(currentNode, antID, "Forager", 13, 13);
        }
        else {
            hatchedAnt= new Soldier(currentNode, antID, "Soldier", 13 , 13);
        }
        

        hatchedAnt.setAge(1);
        currentNode.antEnters(hatchedAnt);

    }

    public void hatch() {

        antID++;
        Ant hatchedAnt;
        
        antType=randomAntType();
        
        if(antType=="Scout") {
            hatchedAnt= new Scout(currentNode,  antID, "Scout", 13, 13);
        } 
        else if(antType=="Forager") {
            hatchedAnt= new Forager(currentNode,  antID, "Forager", 13, 13);
        }
        else{
            hatchedAnt= new Soldier(currentNode,  antID, "Soldier", 13 , 13);
        }
        
        int nl=isNull(hatchedAnt);
        if (nl==1){
        	return;
        }
     
        hatchedAnt.setAge(1);
        currentNode.antEnters(hatchedAnt);

    }
    
  
    public int isNull(Ant ant) {
    	int nl;
    	if(ant==null) {
    		nl=1;
    	}
    	else {
    		nl=0;
    	}
    	return nl;
    }
    
    public void setAge(int turn) {
    	age=turn;
    }
    
    public int getAge() {
    	return age;
    }
    

}
