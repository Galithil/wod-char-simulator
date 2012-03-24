
import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;


public abstract class Creature {

	public String name;
	public int initD;
	public int attackD;
	public int damageD;
	public int dodgeD;
	public int resistanceD;
	public int rAggD;
	public Levels lev;
	
	private int bonusfromattack=0;
	
	public Creature(String name, int initD, int attackD, int damageD, int dodgeD,
			int resistanceD, int rAggD, Levels lev) {
		super();
		this.name=name;
		this.initD = initD;
		this.attackD = attackD;
		this.damageD = damageD;
		this.dodgeD = dodgeD;
		this.resistanceD = resistanceD;
		this.rAggD=rAggD;
		this.lev=lev;
	}
	
	public int initRoll(){
		
		DiceManager dm=new DiceManager();
		return(dm.realHandler(initD+lev.initM, 10, 4, lev.rerolltens, true));
	}
	public int attackRoll(){
		
		DiceManager dm=new DiceManager();
		int successes=dm.realHandler(attackD+lev.attackM, 10, 7, lev.rerolltens, true);
		if(successes>=0){
			bonusfromattack=successes;
		}else{
			
			bonusfromattack=0;
		}
		return(successes);
	}
	public int damageRoll(){
		
		DiceManager dm=new DiceManager();
		return(dm.realHandler(damageD+lev.damageM+bonusfromattack, 10, 7, false, false));
	}
	
	public int dodgeRoll(){
		
		DiceManager dm=new DiceManager();
		return(dm.realHandler(dodgeD+lev.dodgeM, 10, 7, lev.rerolltens, true));
	}
	public int resistanceRoll(){
		
		DiceManager dm=new DiceManager();
		return(dm.realHandler(resistanceD+lev.resistanceM, 10, 7, lev.rerolltens, true));
		
	}
	public int aggravatedRoll(){
		
		DiceManager dm=new DiceManager();
		return(dm.realHandler(rAggD+lev.rAggM, 10, 7, lev.rerolltens, true));
		
	}
        public String toString(){

        return (this.name+this.lev.toString());
            
        }
}
