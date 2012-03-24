
public class Garou extends Creature {
	
	public Garou(String name, Levels lev){
		super(name,8,9,8,7,7,4, lev);
		
	}
         public String toString(){

        return (this.name+" Garou "+this.lev.toString());

        }

}
