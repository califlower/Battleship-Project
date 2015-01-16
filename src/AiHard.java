
import java.util.Random;


public class AiHard implements Player
{

private char[][] board=new char[10][10];
private char[][] hitBoard=new char[10][10]; //Use hitboard for player firing upon this player

/*private boolean hunting=true;
private boolean narrowing=false;
private boolean destroying=false;
private char shipHit='x';
private int tries=0;
private int locX=-1;
private int locY=-1;

private int destX=-1;
private int destY=-1;

private boolean lastChance=false;*/


	public AiHard()
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
		
		
		char temp; //good ole' temp	
		
		
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
		return temp;

		
	}

	
	public Coordinate fire()
	{
		//This method does the whole firing sequence here
		//-----------------------------------------------
		int x=-1; 
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
			if (x<10 && x>=0 && y<10 && y>=0)
			{
				if (hitBoard[x][y]=='')
				{
					Coordinate temp=new Coordinate(x,y);
					fireCoord=temp;
					break;
					
				}
				
			}
			System.out.println("You've fired here before or invalid location");
			x=-1;
			y=-1;
		}
		
		return fireCoord;
		
		
	}

	@Override
	public void fireResult(char result)
	{
		System.out.println("Your result is " +result);
		
	}

	@Override
	public void placeShips()
	{
		Random ran=new Random();
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
						
			
				//Takes the info for the ship and the direction
				while (!(x>=0 && x<=10))
				{
					x=ran.nextInt(10);	
				}
				while (!(y>=0 && y<=10))
				{
					y=ran.nextInt(10);	
				}
				while (!(dir=='V' || dir=='v' || dir=='R' || dir=='r' || dir=='H' || dir=='h'))
				{
					int temp=ran.nextInt(2);
					if (temp==1)
						dir='h';
					if (temp==0)
						dir='v';
				}
				
				
				
				//Does stuff when the info is taken
				
				if (dir=='V' || dir=='v')
				{
					fixbugtwo: for (int j=0;j<shipsize[i]; j++) //doesn't really fix a bug, but it gives me peace of mind
					{
						if (y+j>9 ||x>9|| board[x][y+j]!='')
						{
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
						if (x+j>9 ||y>9|| board[x+j][y]!='')
						{
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

	
	
}
