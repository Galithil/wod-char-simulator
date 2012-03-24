import java.util.ArrayList;


public class CharManager {
	public ArrayList<Creature> creaList;
	
	public CharManager(){
		creaList=new ArrayList<Creature>();
		
	}
	
	public void addCrea(Creature crea){
		creaList.add(crea);
		
	}
	public void removeCrea(Creature crea){
		creaList.remove(crea);
		
	}
}
