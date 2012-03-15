import java.util.ArrayList;
import java.util.Random;


public class DiceManager {

	//throws nbDices dices from 1 to diceTypes. ex : 4d10 -> roll(4,10)
	//returns throws in a Arraylist
	public ArrayList<Integer> roll(int nbDices,int diceTypes){
		
		ArrayList<Integer> results=new ArrayList<Integer>();
		int i;
		Random r=new Random();
		for(i=0;i<nbDices;i++){
			results.add(r.nextInt((diceTypes))+1);
		}
		
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
	
	public int rollSuccesses(int nbDices,int diceTypes, int difficulty, boolean rerolltens){
		int result=0;
		int bonus=0;
		
		ArrayList<Integer> rolls=roll(nbDices,diceTypes);
		
		for(Integer oneRoll:rolls){
			
			
			if (oneRoll>=difficulty) {
				result++;
			}
			if (oneRoll==10){
				bonus++;
			}
		}
		ArrayList<Integer> bonusRolls=roll(bonus,diceTypes);	
		for(Integer oneRoll:bonusRolls){
			
			
			if (oneRoll>=difficulty) {
				result++;
			}
			
		}
		
		
		return result;
	}
	
	public int rollSuccesses(int nbDices,int diceTypes, int difficulty, boolean rerolltens, boolean fumbles){
		int result=0;
		
		ArrayList<Integer> rolls=roll(nbDices,diceTypes);
		
		for(Integer oneRoll:rolls){
			
			if (oneRoll>=difficulty) {
				result++;
			}
			if (oneRoll==1 && fumbles) {
				result--;
			}
		}
		
		
		return result;
	}
	
	public static void main(String[] argv){
		
		ArrayList<Integer> test=new ArrayList<Integer>();
		DiceManager dm=new DiceManager();
		test=dm.roll(5,10);
		System.out.println(test);
		
	}
	
}
