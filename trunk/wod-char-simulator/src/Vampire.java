
public class Vampire extends Creature {
	
	public Vampire(String name,Levels lev){
		super(name,7,8,6,6,6,3, lev);
		
	}
    @Override
         public String toString(){
         return (this.name+" Vampire "+this.lev.toString());
         }
}
