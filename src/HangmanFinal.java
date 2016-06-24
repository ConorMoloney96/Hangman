import javax.swing.JOptionPane;

public class HangmanFinal 
{ 
	public static void main (String [] args)
	{ 
		boolean weArePlaying=true;
		while (weArePlaying)
		{
		
		String [] listOfWords;
		listOfWords=menuSelection();
		int wordIndex=(int)(Math.random()*listOfWords.length);
		String wordGenerated = listOfWords[wordIndex];
		String [] wordToGuess = wordGenerated.split("");
		String [] playerGuess = new String [wordToGuess.length];
		for (int i=0;i<wordToGuess.length;i++)
			playerGuess[i]="-";
		game(wordToGuess, playerGuess);
		
		
		String playAgain=JOptionPane.showInputDialog(null, "Would you like to play again? (yes/no)");
		if (playAgain.equalsIgnoreCase("no"))
			weArePlaying=false;
		
		}
		
	}
	
	public static String Drawing(int Lives)
	{
		String image="";
		switch (Lives)
		{
		case 6: image=("\t+--+\n\t|  |\n\t|\n\t|\n\t|\n\t|\n\t|\n\t+--\n");					break;
		case 5: image=("\t+--+\n\t|  |\n\t|  @\n\t|\n\t|\n\t|\n\t|\n\t+--\n");				break;
		case 4: image=("\t+--+\n\t|  |\n\t|  @\n\t| /\n\t|\n\t|\n\t|\n\t+--\n");			break;
		case 3: image=("\t+--+\n\t|  |\n\t|  @\n\t| / \\\n\t|\n\t|\n\t|\n\t+--\n");			break;
		case 2: image=("\t+--+\n\t|  |\n\t|  @\n\t| /|\\\n\t|  |\n\t|\n\t|\n\t+--\n");		break;
		case 1: image=("\t+--+\n\t|  |\n\t|  @\n\t| /|\\\n\t|  |\n\t| /\n\t|\n\t+--\n");	break;
		case 0: image=("\t+--+\n\t|  |\n\t|  @\n\t| /|\\\n\t|  |\n\t| / \\\n\t|\n\t+--\n");	break;
		}
		return(image);
	}


	public static void game(String [] wordArray, String [] hiddenWord)
	 {
	    int lives = 7;
		String guess;
		String guessedLetters = "";
		boolean correctGuess = false;
		String word = "";
		
		for (int i=0;i<wordArray.length;i++)
			word+=wordArray[i];
		
		
		for( ; lives > 0 && !correctGuess ; )
		{
		                                                                  //hangmanArt = (method for hangman ascii)(Pass through present number of lives)
			String image=Drawing(lives);
			System.out.println("\nHangman game");                            //replace with hangmanArt
		  System.out.print("Word : ");
		  for(int i = 0; i < hiddenWord.length; i++)                      //loop for prinitng out the hidden word
		   {
		    System.out.print(hiddenWord[i] + " ");  
		   }
		  System.out.println();
		  System.out.println("Lives : " + lives);
		  System.out.println(image);
		  guess = JOptionPane.showInputDialog(null, "Enter your guess: ");                                       //input
	      
	     
	      if(guess.isEmpty())                                          //if the input is empty
	      {
			 System.out.println("No entry\n");
			  }	  
	      else
			  {
			
		    if(guess.length() > 1 )                                      //User takes a final guess; if the length is greater than 1.
			  {
			   lives = 0;
			   if(word.equals(guess))
			   {
			    System.out.println("Congrats you guessed the word");
					correctGuess = true;
			   }
			  else
			   {
			    System.out.println("Game Over");    
			   }
			  
			  }
			else
			{
			
			  if(guessedLetters.indexOf(guess) == -1)                       //searches the already guessed characters to check if it exists
			  {
			    guessedLetters = guessedLetters.concat(guess);              //if it doesnt exist, it combines your 
				  boolean ifCorrect = false;                                  // guess with the already guessed characters.
				  int index = word.indexOf(guess);  
				
				  if(index != -1)                                               //check if your guess is in the String "word"
				  {
					 for(int z = 0; z < hiddenWord.length; z++)                  //Check each indexin the Array for your guess
						 {                                                           // and replaces it with yur guess in the hidden 
							 if(guess.indexOf(wordArray[z]) != -1)                     // Array
							 {
								hiddenWord[z] = guess;
							 }
							
						 }
						
					 
				      if (letterFullyGuessed(hiddenWord) )
				      { 
					  System.out.println("Congratulations, you have correctly guessed the word. The word in question was: " + word);
					  correctGuess=true;
				      }
				  }
				   else
				  {
				   lives--;
				  }
			  }
				
			  else
			  {
				System.out.println("Letter already guessed, Lives remaining : " + lives + "\n");
			  }
			}
		  	
	      }	  
		  
		  
		  
		  
		  
		}
			if((lives == 0) && (correctGuess == false))
			{
				System.out.println("Game Over, Lives = " + lives);
				System.out.println("\t+--+\n\t|  |\n\t|  @\n\t| /|\\\n\t|  |\n\t| / \\\n\t|\n\t+--\n");
			}
		}
	
	public static boolean letterFullyGuessed(String [] hiddenWord)
	{ 
		boolean fullyGuessed=true; 
		for (int i=0;i<hiddenWord.length;i++)
		{ 
			if(hiddenWord[i]=="-" ) fullyGuessed=false;
		}
		return fullyGuessed;
	}
	
	public static String [] menuSelection()
	{ 
		Object [] options = { "Sports", "Animals", "School", "Music"};
		String [] sports = {"football", "rugby", "soccer", "tennis"};
		String [] animals = {"snake", "mouse", "cat", "dog"};
		String [] school = {"class", "desk", "pen", "notepad"};
		String [] music = {"guitar", "piano", "drums"};
		
		String selection = (String) JOptionPane.showInputDialog(null, "Enter the topic you would like your game to deal with. ", 
				                                                  "Categories", 1, null, options,options[0]);
		
		String [] words ={""};
		switch (selection)
		{ 
		  
		case "Sports": words=sports; break;
		case "Animals": words=animals; break;
		case "School": words=school; break; 
		case "Music": words=music; break;
		}
		return words;
	}

}