public class MonopolyBoard{
    
    private Space[] _board;
    
    public MonopolyBoard(){
	//[ --> {
	// .... Seriously Jalen
	_board = new Space[40];
	//Properties:  new Property(_price,name, house price, int[] rents)
	_board[1] = new Property(1,60,"Mediterranean Avenue",[10,30,90,160,250]);
	_board[3] = new Property(1,60,"Baltic Avenue",[20,60,180,320,450]);
	_board[6] = new Property(2,100,"Oriental Avenue",[30,90,270,400,550]);
	_board[8] = new Property(2,100,"Vermont Avenue",[30,90,270,400,550]);
	_board[10] = new Property(2,120,"Connecticut Avenue",[40,100,300,450,600]);
	_board[11] = new Property(3,140,"St. Charles Place",[50,150,450,625,750]);
	_board[13] = new Property(3,140,"States Avenue",[50,150,450,625,750]);
	_board[14] = new Property(3,160,"Virginia Avenue",[60,180,500,700,900]);
	_board[16] = new Property(4,180,"St. James Place",[70,200,550,750,950]);
	_board[18] = new Property(4,180,"Tennessee Avenue",[70,200,550,750,950]);
	_board[19] = new Property(4,200,"New York Avenue",[80,220,600,800,1000]);
	_board[21] = new Property(5,220,"Kentucky Avenue",[90,250,700,875,1050]);
	_board[23] = new Property(5,220,"Indiana Avenue",[90,250,700,875,1050]);
	_board[24] = new Property(5,240,"Illinois Avenue",[100,300,750,925,1100]);
	_board[26] = new Property(6,260,"Atlantic Avenue",[110,330,800,975,1150]);
	_board[27] = new Property(6,260,"Ventnor Avenue",[110,330,800,975,1150]);
	_board[29] = new Property(6,280,"Marvin Gardens",[120,360,850,1025,1200]);
	_board[31] = new Property(7,300,"Pacific Avenue",[130,390,900,1100,1275]);
	_board[32] = new Property(7,300,"North Carolina Avenue",[130,390,900,1100,1275]);
	_board[34] = new Property(7,320,"Pennsylvania Avenue",[150,450,1000,1200,1400]);
	_board[37] = new Property(8,350,"Park Place",[175,500,1100,1300,1500]);
	_board[39] = new Property(8,400,"Boardwalk",[200,600,1400,1700,2000]);
	
	//Railroads: 

	_board[5] = new Railroad("Reading Railroad");
	_board[15] = new Railroad("Pennsylvania Railroad");
   
