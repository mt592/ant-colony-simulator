import java.util.Random;

public class NextRand {
	
	static int randNumb;
	
	public static int randomNumber(int numbers) {
	    Random rand= new Random();
	    randNumb = rand.nextInt(numbers);
		
		return randNumb;
	}
	}
