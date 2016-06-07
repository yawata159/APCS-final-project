import java.util.List;
import java.util.ArrayList;

public class MonopolyGame{

    public ArrayList<Player> _players;
    public MonopolyBoard _board;
    /*
      ...
    */
    
    public MonopolyGame(int players){
	for (int i=0;i<players;i++){
	    String name="AAA";//Get name from user here
	    _players.add(new Player(name, 1500, this));
	}
	_board=new MonopolyBoard();
    }

    public MonopolyBoard getBoard(){
	return _board;
    }

}
	
