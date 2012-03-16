import java.util.ArrayList;
import java.util.Random;


public class DiceManager {

	//DiceManager simulates dice rolls.
	//If you simply want a dice roll, use roll()
	//If you want something that brings you back successes based on a given difficulty, use realHandler()

	//throws nbDices dices from 1 to diceTypes. ex : 4d10 -> roll(4,10)
	//returns throws in a Arraylist
	public ArrayList<Integer> roll(int nbDices,int diceTypes){

		ArrayList<Integer> results=new ArrayList<Integer>();
		int i;
		Random r=new Random();
		for(i=0;i<nbDices;i++){
			results.add(r.nextInt((diceTypes))+1);
		}
		System.out.println(results);
		return results;

	}
	//same thing as roll but is able to reroll tens if the parameter rerollten is true
	//	public ArrayList<Integer> roll(int nbDices,int diceTypes, boolean rerolltens){
	//		
	//		ArrayList<Integer> results=new ArrayList<Integer>();
	//		int i, thisroll;
	//		Random r=new Random();
	//		for(i=0;i<nbDices;i++){
	//			thisroll=r.nextInt((diceTypes))+1;
	//			if (rerolltens && (thisroll==10)){
	//				nbDices++;
	//			}
	//			results.add(thisroll);
	//		}
	//		
	//		return results;
	//		
	//	}
	//throws nbDices and returns the number of successes for a given difficulty
	public int rollSuccesses(int nbDices,int diceTypes, int difficulty){
		int result=0;

		ArrayList<Integer> rolls=roll(nbDices,diceTypes);

		for(Integer oneRoll:rolls){

			if (oneRoll>=difficulty) {
				result++;
			}
		}




		return result;
	}
	//same thing, but rerolls tens
	public int rollSuccesses(int nbDices,int diceTypes, int difficulty, boolean rerolltens){
		int result=0;
		int bonus=0;

		ArrayList<Integer> rolls=roll(nbDices,diceTypes);

		for(Integer oneRoll:rolls){

			if (oneRoll==10){
				bonus++;
			}
		}
		if (bonus!=0){
			rolls.addAll(tenagain(bonus,diceTypes,difficulty, false));
		}
		for(Integer oneRoll:rolls){
			if (oneRoll>=difficulty) {
				result++;
			}
		}

		return result;
	}
	//same thing, but can reroll tens and count the fumbles
	public int rollSuccesses(int nbDices,int diceTypes, int difficulty, boolean rerolltens, boolean fumbles){
		ArrayList<Integer> rolls=new ArrayList<Integer>();
		int result=0;
			rolls.addAll(tenagain(nbDices,diceTypes,difficulty, true));
		
		
		for(Integer oneRoll:rolls){
			if (oneRoll>=difficulty) {
				result++;
			}
		}
		
		return result;
	}
	public ArrayList<Integer> tenagain(int nbdices, int diceType, int difficulty, boolean fumbles){
		int bonus=0;
		int malus=0;
		ArrayList<Integer> bonusRolls=roll(nbdices,diceType);
		for(int oneRoll:bonusRolls){
			if(oneRoll==10){
				bonus++;
			}
			if (fumbles && oneRoll==1){
				malus++;
				
			}
			
		}
		if (fumbles && malus!=0){
			if(bonus>=malus){
				bonus-=malus;
				malus=0;
				
			}else{
				bonus=0;
				malus-=bonus;
				
			}
			
		}
		if (bonus!=0){
			bonusRolls.addAll(tenagain(bonus, diceType, difficulty, fumbles));
			
		}
		return bonusRolls;

	}
	public int realHandler(int nbdices, int diceType, int difficulty, boolean rerolltens, boolean ones){
		ArrayList<Integer> rolls=roll(nbdices, diceType);
		
		int fumbles=0;
		int fails=0;
		int successes=0;
		int criticals=0;
		
		for(Integer oneRoll:rolls){
			if (oneRoll ==1){
				fumbles++;
			}else if(oneRoll==10){
				criticals++;
				
			}else if(oneRoll>=difficulty){
				successes++;
				
			}else{
				fails++;
				
			}
			
			
		}
		//System.out.println(fumbles+" "+fails+" "+successes+" "+criticals);
		if(!rerolltens && !ones){
			return successes+criticals;
			
		}else if(rerolltens && !ones){
			int newsuccesses=0;
			if (criticals!=0){
				newsuccesses=realHandler(criticals, diceType, difficulty, rerolltens, ones);
			}
			return criticals+successes+newsuccesses;
			
		}else if(!rerolltens && ones){
			return (criticals+successes-fumbles);
			
		}else{
			
			//reroll tens AND count fumbles. fumbles kill tens first
			if (criticals>fumbles){
				
				
				int rerolls=criticals-fumbles;
				fumbles=0; //useless, except for understanding the logic
				
				int newsuccesses=0;
				if (rerolls!=0){
					realHandler(rerolls, diceType, difficulty, rerolltens, ones);
				}
				return criticals+successes+newsuccesses;
				
			}else if(criticals==fumbles){
				//System.out.println("TWO");
				return successes;
			}else{
				//System.out.println("THREE");
				return (successes+criticals-fumbles);
			}
			
			
			
		}
		
		
		
		
	}
	public static void main(String[] argv){

		
		DiceManager dm=new DiceManager();
		int test2;
		//test2=dm.rollSuccesses(5, 10, 7);
		//System.out.println(test2);
		//test2=dm.rollSuccesses(10, 10, 5, true);
		//System.out.println(test2);
		test2=dm.realHandler(10, 10, 7, true, false);
		System.out.println(test2);

	}

}
