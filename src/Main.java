
public class Main 
{


	public static void main(String[] args) throws InterruptedException 
	{
		
		System.out.println("Choose Your Options for the game by typing in the corresponding number");
		System.out.println("Option 1: Play Against Easy AI");
		System.out.println("Option 2: Play Against Hard AI");
		System.out.println("Option 3: Play Against Player");
		
		int z;
		while (true)
		{
			z=IO.readInt();
			if (z>=1 && z<=3)
				break;
			
		}
		
		Player playerOne;
		Player playerTwo;
		
		if (z==1)
		{
			playerOne=new AiHard();
			playerTwo=new AI();
			
			
		}
		else if (z==2)
		{
			playerOne=new AiHard();
			playerTwo=new Player_cig24();
		}
		else  if (z==3)
		{
			playerOne=new playervsPlayerOne();
			playerTwo=new playervsPlayerTwo();
			
		}
		else
		{
			playerOne=new playervsPlayerOne();
			playerTwo=new playervsPlayerTwo();
		}
		
		
		if (z==3)
		{
			System.out.println(" ");
			System.out.println("PLAYER ONE PLACEMENT TURN");
			System.out.println("------------------------------------");
			System.out.println(" ");
			
			playerOne.placeShips();
			
			for (int x=0;x<100;x++)
				System.out.println(" ");
			
			System.out.println(" ");
			System.out.println("PLAYER TWO PLACEMENT TURN");
			System.out.println("------------------------------------");
			System.out.println(" ");
			
			playerTwo.placeShips();
			
		}
		else
		{
			playerOne.placeShips();
			
			for (int x=0;x<100;x++)
				System.out.println(" ");
			
			
			
			System.out.println(" ");
			System.out.println("PLAYER PLACEMENT TURN");
			System.out.println("------------------------------------");
			System.out.println(" ");
			
			playerTwo.placeShips();
			
		}
		
		
	
		
		
		
		
		
		while (true)
		{
			for (int x=0;x<100;x++)
				System.out.println(" ");
			
			
			if (z==3)
			{
				
				System.out.println("PLAYER ONE TURN");
				System.out.println("------------------------------------");
				System.out.println(" ");
				
			}
			else
			{
				
				System.out.println("AI FIRING ON YOUR SHIPS");
				System.out.println("------------------------------------");
				System.out.println(" ");
				

			}
			
			playerTwo.fireUpon(playerTwo.fire());
			
			if (playerTwo.lost()==true)
			{
				System.out.println("All of player Two's ship have been destroyed. Player Two Loses");
				break;
			}
			
			
			
			
			Thread.sleep(3000);
			
			
			
			for (int x=0;x<100;x++)
				System.out.println(" ");
			if (z==3)
			{
				
				System.out.println("PLAYER TWO TURN");
				System.out.println("------------------------------------");
				System.out.println(" ");
				
				
			}
			else
			{
				System.out.println("YOUR TURN");
				System.out.println("------------------------------------");
				System.out.println(" ");
				
				
			}

			playerOne.fireUpon(playerOne.fire());
			
			
			if (playerOne.lost()==true)
			{
				System.out.println("All of player One's ship have been destroyed. Player One Loses");
				break;
			}
			Thread.sleep(3000);
		}
		
		
		
		
	}

}
