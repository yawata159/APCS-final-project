import java.util.List;
import java.util.ArrayList;
public class MonopolyBoard{
    
    private Space[] _board;
    private final int[] _PROPERTY_POSITIONS={1,3,6,8,9,11,13,14,16,18,19,21,23,24,26,27,29,31,32,34,37,39};
    private final int[] _RAILROAD_POSITIONS={5,15,25,35};
    private final int[] _UTILITY_POSITIONS={12,28};
    private ArrayList<Property> _properties;
    private ArrayList<Railroad> _railroads;
    private ArrayList<Utility> _utilities;
    
    public MonopolyBoard(){
	_board = new Space[40];

	//Properties:  new Property(int type,int price,String name,int[] rents)
	_board[1] = new Property(1,60,"Mediterranean Avenue", new int[]{10,30,90,160,250});
	_board[3] = new Property(1,60,"Baltic Avenue",new int[]{20,60,180,320,450});
	_board[6] = new Property(2,100,"Oriental Avenue",new int[]{30,90,270,400,550});
	_board[8] = new Property(2,100,"Vermont Avenue",new int[]{30,90,270,400,550});
	_board[9] = new Property(2,120,"Connecticut Avenue",new int[]{40,100,300,450,600});
	_board[11] = new Property(3,140,"St. Charles Place",new int[]{50,150,450,625,750});
	_board[13] = new Property(3,140,"States Avenue", new int[]{50,150,450,625,750});
	_board[14] = new Property(3,160,"Virginia Avenue",new int[]{60,180,500,700,900});
	_board[16] = new Property(4,180,"St. James Place",new int[]{70,200,550,750,950});
	_board[18] = new Property(4,180,"Tennessee Avenue",new int[]{70,200,550,750,950});
	_board[19] = new Property(4,200,"New York Avenue",new int[]{80,220,600,800,1000});
	_board[21] = new Property(5,220,"Kentucky Avenue",new int[]{90,250,700,875,1050});
	_board[23] = new Property(5,220,"Indiana Avenue",new int[]{90,250,700,875,1050});
	_board[24] = new Property(5,240,"Illinois Avenue",new int[]{100,300,750,925,1100});
	_board[26] = new Property(6,260,"Atlantic Avenue",new int[]{110,330,800,975,1150});
	_board[27] = new Property(6,260,"Ventnor Avenue",new int[]{110,330,800,975,1150});
	_board[29] = new Property(6,280,"Marvin Gardens",new int[]{120,360,850,1025,1200});
	_board[31] = new Property(7,300,"Pacific Avenue",new int[]{130,390,900,1100,1275});
	_board[32] = new Property(7,300,"North Carolina Avenue",new int[]{130,390,900,1100,1275});
	_board[34] = new Property(7,320,"Pennsylvania Avenue",new int[]{150,450,1000,1200,1400});
	_board[37] = new Property(8,350,"Park Place",new int[]{175,500,1100,1300,1500});
	_board[39] = new Property(8,400,"Boardwalk",new int[]{200,600,1400,1700,2000});	

	//Railroads: 
	_board[5] = new Railroad("Reading Railroad");
	_board[15] = new Railroad("Pennsylvania Railroad");
   	_board[25] = new Railroad("B & O Railroad");
   	_board[35] = new Railroad("Short Line");

	//Utilities:
	_board[12]=new Utility("Electric Company");
	_board[28]=new Utility("Water Works");

	//CommunityChest and Chance
	_board[2]=new CommunityChest();
	_board[33]=new CommunityChest();
	_board[7]=new Chance();
	_board[22]=new Chance();

	//Others:
	_board[4]=new IncomeTax();
	_board[10]=new Jail();
	_board[20]=new FreeParking();
	_board[30]=new GoToJail();
	_board[38]=new LuxuryTax();

	setPositions();
	setProperties();
	setRailroads();
	setUtilities();
    }

    public Space getSpace(int pos){
	return _board[pos];
    }

    public void setPositions(){
	for (int i=0;i<_board.length;i++)
	    _board[i].setPos(i);
    }

    public void setProperties(){
	for (int x: _PROPERTY_POSITIONS)
	    _properties.add((Property)(_board[x]));
    }

    public void setRailroads(){
	for (int x: _RAILROAD_POSITIONS)
	    _railroads.add((Railroad)(_board[x]));
    }

    public void setUtilities(){
	for (int x: _UTILITY_POSITIONS)
	    _utilities.add((Utility)(_board[x]));
    }

    public ArrayList<Property> getProperties(){
	return _properties;
    }

    public ArrayList<Railroad> getRailroads(){
        return _railroads;
    }

    public ArrayList<Utility> getUtilities(){
        return _utilities;
    }

}
