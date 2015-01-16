import java.util.Random;

public class Player_cig24 implements Player
{
	private char[][] board=new char[10][10];
	private char[][] hitBoard=new char[10][10]; //Use hitboard for player firing upon this player
	
	private boolean hunting=true;
	private boolean narrowing=false;
	private boolean destroying=false;
	private char shipHit='x';
	private int tries=0;
	private int locX=-1;
	private int locY=-1;

	private int destX=-1;
	private int destY=-1;

	private boolean lastChance=false;
	
	public Player_cig24()
	{
		for (int i=0;i<board[0].length;i++)
		{
			for (int j=0;j<board.length;j++)
				board[j][i]='';
		}
		
		for (int i=0;i<hitBoard[0].length;i++)
		{
			for (int j=0;j<hitBoard.length;j++)
				hitBoard[j][i]='';
		}
	}
	
	
	public char fireUpon(Coordinate x)
	{
		//This method updates the boards and the hitboard with the proper values from fire()
		//-----------------------------------------------
		
		
		/*char temp; //good ole' temp	
		
		
		if (board[x.getX()][x.getY()]!='') //If it's a hit it returns the ship name
		{
			hitBoard[x.getX()][x.getY()]=board[x.getX()][x.getY()];
			temp= board[x.getX()][x.getY()];
			board[x.getX()][x.getY()]='X'; //replaces ship in board with X if it gets hit. Basically used for a loss condition
		}
		else //if a miss it returns M
		{
			hitBoard[x.getX()][x.getY()]='M';
			temp= 'M';
		}
		
		
		fireResult(temp);
		System.out.println("Here is your hit board as of now");
		printHitBoard();
		return temp;*/
		
		
		
		char temp; //good ole' temp	
		
		
		if (board[x.getX()][x.getY()]!='') //If it's a hit it returns the ship name
		{
			hitBoard[x.getX()][x.getY()]=board[x.getX()][x.getY()];
			temp= board[x.getX()][x.getY()];
			board[x.getX()][x.getY()]='X'; //replaces ship in board with X if it gets hit. Basically used for a loss condition
			
			if (hunting==true)
			{
				shipHit=temp;
				hunting=false;
				narrowing=true;
				destroying=false;
				locX=x.getX();
				locY=x.getY();
			}
			else if (narrowing==true && shipHit==temp && temp!='P')
			{
				hunting=false;
				narrowing=false;
				destroying=true;
				destY=x.getY();
				destX=x.getX();
				tries=0;
			}
			else if (narrowing==true && shipHit==temp && temp=='P')
			{
				hunting=true;
				narrowing=false;
				destroying=false;
				locX=-1;
				locY=-1;
				destX=-1;
				destY=-1;
				tries=0;
				
			}
			else if (destroying==true && shipHit==temp)
			{
				destY=x.getY();
				destX=x.getX();
			}
			else if (destroying==true && shipHit==temp && lastChance==true)
			{
				destY=x.getY();
				destX=x.getX();
				lastChance=false;
			}
			else
			{
				hunting=true;
				narrowing=false;
				destroying=false;
				lastChance=false;
				locX=-1;
				locY=-1;
				destX=-1;
				destY=-1;
				tries=0;
			}
			
		
			
		}
		else //if a miss it returns M
		{
			hitBoard[x.getX()][x.getY()]='M';
			temp= 'M';
			
			if (narrowing==true)
			{
				if (tries>=3)
					tries=0;
				else
					tries++;	
			}
				
			else if  (destroying==true)
			{
				tries=0;
				lastChance=true;
			}
			else if (lastChance==true)
			{
				hunting=true;
				narrowing=false;
				destroying=false;
				lastChance=false;
				locX=-1;
				locY=-1;
				destX=-1;
				destY=-1;
				tries=0;
				
			}
			else
			{
				hunting=true;
				narrowing=false;
				destroying=false;
				lastChance=false;
				locX=-1;
				locY=-1;
				destX=-1;
				destY=-1;
				tries=0;
			}
				
				
		}
		fireResult(temp);
		printHitBoard();
		return temp;

		

		
	}

	@Override
	public Coordinate fire()
	{
		//This method does the whole firing sequence here
		//-----------------------------------------------
		/*int x=-1; 
		int y=-1;
		Coordinate fireCoord=new Coordinate(x,y);
		printHitBoard();
		while (true)
		{
			while (!(x>=0 && x<=10))
			{
				System.out.println("Enter Col Coord");
				x=IO.readInt()-1;	
			}
			while (!(y>=0 && y<=10))
			{
				System.out.println("Enter Row Coord");
				y=IO.readInt()-1;	
			}
			if (hitBoard[x][y]=='')
			{
				Coordinate temp=new Coordinate(x,y);
				fireCoord=temp;
				break;
			}
			System.out.println("You've fired here before");
			x=-1;
			y=-1;
		}
		
		return fireCoord;*/
		
		
		
		
		
		if (hunting==true)  
			return hunter();
		else if (narrowing==true)
			return narrowing();
		else 
			return destroying();
		
		
	}

	@Override
	public void fireResult(char result)
	{
		System.out.println("THE AI RESULT IS " +result);
		
	}

	@Override
	public void placeShips()
	{

		int[] shipsize={5,4,3,3,2};
		String[] shipnames=
			{
				"Aircraft Carrier", "Battleship",
				"Submarine","Destroyer","Patrol Boat"
			};
//Variables
//------------------------------------------------------------
		
		
		//Basically fills in the board with squares
		

			
		
		
		//--------------------------------------------
		//Logic for placing ships on the board

		for (int i=0;i<shipsize.length;i++)
		{	
			
			
			
			
			//This while loop basically repeats if something goes wrong such as a ship being in the way
			//Label is to allow breaking this loop way down inside
			alabeltobreak: while(true)
			{
				
				int x=-1; //init here fixes a bug
				int y=-1; //init here fixes a bug
				char dir='x'; //same here
				
				
				System.out.println("Type Coordinate for nose of the " +shipnames[i]);
				System.out.println("Ship will be placed vertically, with rest of ship going downwards or");
				System.out.println("Ships placed horizontally will be placed with nose at your coordinate, and rest of ship to the right.");
				System.out.println("In even of no room, (bottom of the board for example), will return an error");
				System.out.println("To place ship horizontally, type H afterwards. To Replace, press R. To keep vertical press V");
			

			
			
				
				//Takes the info for the ship and the direction
				while (!(x>=0 && x<=10))
				{
					System.out.println("Enter Col Coord");
					x=IO.readInt()-1;	
				}
				while (!(y>=0 && y<=10))
				{
					System.out.println("Enter Row Coord");
					y=IO.readInt()-1;	
				}
				while (!(dir=='V' || dir=='v' || dir=='R' || dir=='r' || dir=='H' || dir=='h'))
				{
					System.out.println("Enter Ship Direction");
					dir=IO.readChar();	
				}
				
				
				
				//Does stuff when the info is taken
				
				if (dir=='V' || dir=='v')
				{
					fixbugtwo: for (int j=0;j<shipsize[i]; j++) //doesn't really fix a bug, but it gives me peace of mind
					{
						if (y+j>9 ||x>9|| board[x][y+j]!='')
						{
							System.out.println("Can't place ship here");
							System.out.println("-------------------------------------------");
							break fixbugtwo;
						}
						else if(j==shipsize[i]-1 && board[x][y+j]=='')
						{
							for (int k=0;k<shipsize[i];k++)
							{
								board[x][y+k]=shipnames[i].charAt(0);
							}
							break alabeltobreak;
						}
							
					}
				}
				else if (dir=='H' || dir=='h')
				{
					fixbug: for (int j=0;j<shipsize[i]; j++) //doesn't really fix a bug, but it gives me peace of mind
					{
						if (x+j>9 || y>9 || board[x+j][y]!='')
						{
							System.out.println("Can't place ship here");
							System.out.println("-------------------------------------------");
							break fixbug;
						}
						else if(j==shipsize[i]-1 && board[x+j][y]=='')
						{
							for (int k=0;k<shipsize[i]; k++)
							{
								board[x+k][y]=shipnames[i].charAt(0);
							}
							break alabeltobreak;
						}
							
					}
				}
		
			}
			System.out.println(" ");
			printBoard();
			
		}
			
		
		
		
	}

	
	public boolean lost()
	{
		
		for (int i=0;i<board[0].length;i++)
		{
			for (int j=0;j<board.length;j++)
			{
				if (board[j][i]=='A' || board[j][i]=='B'||board[j][i]=='D'||board[j][i]=='S'||board[j][i]=='P'    )
				{
					return false;
						
				}
			}
		}
		return true;
	}

	@Override
	public void printBoard()
	{
		for (int i=0;i<board[0].length;i++)
		{
			for (int j=0;j<board.length;j++)
			{
				if (j==board.length-1)
					System.out.println(" "+board[j][i]+" ");
				else 
					System.out.print(" "+board[j][i]+" ");
			}
		}
		
	}
	public void printHitBoard()
	{
		for (int i=0;i<hitBoard[0].length;i++)
		{
			for (int j=0;j<hitBoard.length;j++)
			{
				if (j==hitBoard.length-1)
					System.out.println(" "+hitBoard[j][i]+" ");
				else 
					System.out.print(" "+hitBoard[j][i]+" ");
			}
		}
		
	}
	
	private Coordinate hunter()
	{
		int x=-1; 
		int y=-1;
		Random ran = new Random();
		
		Coordinate fireCoord=new Coordinate(x,y);

		while (true)
		{
				y=ran.nextInt(10);
				
				if ((y& 1)==0) //even
				{
					do
					{
						x=ran.nextInt(10);
					}
					while ((x&1)==0);
				}
				else //odd
				{
					do
					{
						x=ran.nextInt(10);
					}
					while ((x&1)==1);
				}
			
			if (hitBoard[x][y]=='')
			{
				Coordinate temp=new Coordinate(x,y);
				fireCoord=temp;
				break;
			}
		}
		return fireCoord;
	}
	
	
	private Coordinate narrowing()
	{
		moose: while (true)
		{
			
			Random ran = new Random();
			Coordinate ret = new Coordinate(1,1);
			if (tries==0 && locX+1<=9)
			{
				ret=new Coordinate(locX+1,locY);
				
			} 
			else if (locX+1>9)
			{
				tries++;
			}
				
			
			if (tries==1 && locX-1>=0)
			{
				ret=new Coordinate(locX-1,locY);
				
			} 
			else if (locX-1>=0)
			{
				tries++;
			}
			
			
			if (tries==2 && locY+1<=9)
			{
				ret=new Coordinate(locX-1,locY);
				
			} 
			else if (locY+1<=9)
			{
				tries++;
			}
			
			
			if (tries==3 && locY-1>=0)
			{
				ret=new Coordinate(locX-1,locY);
				
			} 
			else if (locY-1>=0)
			{
				tries=0;
			}
			
			if (hitBoard[ret.getX()][ret.getY()]=='')
			{
				return ret;
			
			}
			else
			{
				while (true)
				{
					ret=new Coordinate(ran.nextInt(10),ran.nextInt(10));
					if (hitBoard[ret.getX()][ret.getY()]=='')
					{
						return ret;
					}
						
				}
				
			}
		}
		
		
	}
	private Coordinate destroying()
	{
		int x=-1;
		int y=-1;
		
		if (lastChance==true)
		{
			if (locX-destX==0)
				x=locX;
			else if (locX-destX<0)	
				x=locX+1;
			else if (locX-destX>0)
				x=locX-1;
			
			if (locY-destY==0)
				y=locY;
			else if (locY-destY<0)	
				y=locY+1;
			else if (locY-destY>0)
				y=locY-1;
		}
		
		if (locX-destX==0)
			x=locX;
		else if (locX-destX<0)	
			x=destX+1;
		else if (locX-destX>0)
			x=destX-1;
		
		
		if (locY-destY==0)
			y=locY;
		else if (locY-destY<0)	
			y=destY+1;
		else if (locY-destY>0)
			y=destY-1;
		
		if (x>9)
		{
			x=locX-1;
		}
		if (x<0)
		{
			x=locX+1;
		}
		
		if (y<0)
		{
			y=locY+1;
		}
		if (y>9)
		{
			y=locY-1;
		}
		
		
		Random ran = new Random();
		if (hitBoard[x][y]=='')
		{
			Coordinate temp=new Coordinate(x,y);
			return temp;
			
		}
		else
		{
			while (true)
			{
				Coordinate temp=new Coordinate(ran.nextInt(10),ran.nextInt(10));
				if (hitBoard[temp.getX()][temp.getY()]=='')
				{
					return temp;
				}
			}
		}

	}


	
	
	
	//Implement your methods here:
	
	
	
}