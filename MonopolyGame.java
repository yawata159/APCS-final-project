import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class MonopolyGame{

    public ArrayList<Player> _players;
    public MonopolyBoard _board;
    //public MapText _map;
    
    /*
      ...
    */
    
    public MonopolyGame(int players){
	Scanner s=new Scanner(System.in);
	for (int i=0;i<players;i++){
	    System.out.println("Type name of this player");
	    String name=s.next();
	    _players.add(new Player(name, 1500, this));
	}
	s.close();
	_board=new MonopolyBoard();
	//_map=new MapText();
    }

    public MonopolyBoard getBoard(){
	return _board;
    }

}
	
