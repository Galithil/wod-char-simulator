
public class Goule extends Creature {

	public Goule(String name,Levels lev){
		super(name,5,7,4,6,6,1, lev);
		
	}
         public String toString(){
         return (this.name+"   Goule   "+this.lev.toString());
         }
}
