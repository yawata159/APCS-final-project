import java.util.*;
import java.io.*;

public class MapText {

    private static final String CLEAR = "[0m";

    private static final int RED = 41;
    private static final int YELLOW = 43;
    private static final int GREEN = 42;
    private static final int BLUE = 44;
    private static final int WHITE = 47; //for brown
    private static final int CYAN = 46;
    private static final int MAGENTA = 45;
    private static final int BLACK = 40; //for orange
    
    private static final String RESET = "[2J[1;1H";

    // 6 PLAYERS
    // row,col
    private static final Integer[][] FIRST_PLAYER_POS =
    {
	{63,148},{63,132},{63,118},{63,104},{63,90},{63,76},{63,62},{63,48},{63,34},{63,20},
	{62,7},{56,5},{50,5},{45,5},{39,5},{33,5},{28,5},{22,5},{18,5}, {12,5}, //62,7:jail
	{5,5},{5,20},{5,34},{5,48},{5,62},{5,76},{5,90},{5,104},{5,118},{5,132}, //5,5:parking
	{5,148},{12,148},{18,148},{22,148},{28,148},{33,148},{37,148},{43,148},{50,148},{56,148} //5,148:gotojail
    };
    
    //top row: i=6, values are col numbers
    private static final Integer[] RED_BGRND = {
	17,18,19,20,21,22,23,24,25,26,27,28,29, //kentucky
	45,46,47,48,49,50,51,52,53,54,55,56,57, //indiana
	59,60,61,62,63,64,65,66,67,68,69,70,71 //illinois
    };

    //top row: i=6, values are col numbers    
    private static final Integer[] YELLOW_BGRND = {
	87,88,89,90,91,92,93,94,95,96,97,98,99, //atlantic
	101,102,103,104,105,106,107,108,109,110,111,112,113, //ventinor
	129,130,131,132,133,134,135,136,137,138,139,140,141 //marvin	
    };

    //bottom row: i=58, values are col numbers
    private static final Integer[] CYAN_BGRND = {
	17,18,19,20,21,22,23,24,25,26,27,28,29, //connecticut
	31,32,33,34,35,36,37,38,39,40,41,42,43, //vermont
	59,60,61,62,63,64,65,66,67,68,69,70,71 //oriental
    };

    //bottom row: i=58, values are col numbers
    private static final Integer[] WHITE_BGRND = {
	101,102,103,104,105,106,107,108,109,110,111,112,113, //baltic
	129,130,131,132,133,134,135,136,137,138,139,140,141  //mediterranean
    };
    
    //left column: j=15, values are row numbers
    private static final Integer[] BLACK_BGRND = { 
	8,9,10,11,12, //new york
	14,15,16,17,18, //tennessee
	24,25,26,27,28 // st james
    };
    
    //left column: j=15, values are row numbers
    private static final Integer[] MAGENTA_BGRND = {
	35,36,37,38,39, // virginia
	41,42,43,44,45, //states
	52,53,54,55,56 //st charles
    };

    //right column: j=143, values are row numbers
    private static final Integer[] GREEN_BGRND = {
	8,9,10,11,12, //pacific
	14,15,16,17,18, //north carolina
	24,25,26,27,28 // pennsylvania
    };

    //right column: j=143, values are row numbers
    private static final Integer[] BLUE_BGRND = {
	39,40,41,42,43, //park place
	52,53,54,55,56 //boarwalk
    };


    private ArrayList<ArrayList<Character>> _boardLines;
    private ArrayList<Player> _players;
    private int[][] _playerCoordinates;
    
    public MapText(ArrayList<Player> players) throws FileNotFoundException{
	
	// import map.txt to boardlines
	_boardLines = new ArrayList<ArrayList<Character>>();
	File txt = new File("map.txt");
	try {
	    Scanner sc = new Scanner(txt);
	    while (sc.hasNextLine())
		_boardLines.add(charArrayList(sc.nextLine()));
	    
	} catch (FileNotFoundException ex) {}
	_players = players;
	_playerCoordinates = new int[_players.size()][2];
	updatePlayerCoordinates();
    }

    /*STATIC FUNCTIONS*/
    private static String backgroundColor(int n) {
	return "[" + n + "m";
    }

    private static int binarySearch(Integer[] arr, Integer key) {
	int low = 0;
	int high = arr.length -1;
	while (low <= high) {
	    int mid = low + (high-low)/2;
	    if (key < arr[mid]) high = mid-1;
	    else if (key > arr[mid]) low = mid+1;
	    else return mid;
	}
	return -1;
    }


    /*MODIFIERS*/
    private char changeChar(int row, int col, char c){
	char ans = _boardLines.get(row).get(col);
	_boardLines.get(row).set(col,c);
	return ans;
    }
    


    private void updatePlayerCoordinates() {
	for (int i = 0 ; i < _players.size(); i++) {
	    //add in coordinates to playerCoord
	    _playerCoordinates[i][0] = FIRST_PLAYER_POS[_players.get(i).position().getIntPos()][0];
	    _playerCoordinates[i][1] = FIRST_PLAYER_POS[_players.get(i).position().getIntPos()][1] + i;
	}	
    }

    // returns player number, -1 if not found
    private int playerCoordinates(int i, int j) {
	for (int k = 0 ; k < _playerCoordinates.length; k++) {
	    if (_playerCoordinates[k][0] == i && _playerCoordinates[k][1] == j) return k;
	}
	return -1;
	     
    }

    public void printMap() {
	updatePlayerCoordinates();
	for (int i = 0; i < _boardLines.size(); i++) {
	    for (int j = 0 ; j < _boardLines.get(i).size();j++) {
		// if char is an avatar print color
		int playerNum = playerCoordinates (i,j);
		if (playerNum != -1) {
		    System.out.print(backgroundColor(_players.get(playerNum).getColor()));
		    System.out.print(_players.get(playerNum).getAvatar());
		}
		// else print regular chars
		else {
		    if (i == 6) { //top
			if (binarySearch(RED_BGRND,j) != -1) System.out.print(backgroundColor(RED));
			else if (binarySearch(YELLOW_BGRND,j) != -1) System.out.print(backgroundColor(YELLOW));
		    }
		    if (i == 58) { //bottom
			if (binarySearch(CYAN_BGRND,j) != -1) System.out.print(backgroundColor(CYAN));
			else if (binarySearch(WHITE_BGRND,j) != -1) System.out.print(backgroundColor(WHITE));
		    }
		    
		    if (j == 15) { //left
			if (binarySearch(BLACK_BGRND,i) != -1) System.out.print(backgroundColor(BLACK));
			else if (binarySearch(MAGENTA_BGRND,i) != -1) System.out.print(backgroundColor(MAGENTA));
		    }
		    if (j == 143) { //right
			if (binarySearch(GREEN_BGRND,i) != -1) System.out.print(backgroundColor(GREEN));
			else if (binarySearch(BLUE_BGRND,i) != -1) System.out.print(backgroundColor(BLUE));
		    }
		    
		    System.out.print(_boardLines.get(i).get(j));
		}
		
		System.out.print(CLEAR);
	    }
	    System.out.println();
	    
	}
	
    }
    
    private static ArrayList<Character> charArrayList(String s) {
	ArrayList<Character> chars = new ArrayList<Character>();
	for (char c : s.toCharArray()) chars.add(c);
	return chars;
    }

    public static void main(String[] args) throws FileNotFoundException{
	MapText G = new MapText(new ArrayList<Player>());
	//G.changeChar(0,0,'+');
	System.out.print(RESET);
	G.printMap();
			   
    }

}
