package test;

import java.util.Random;
import java.util.Scanner;

public class MasterMind {
	private int[] randomCode = new int[4];
	private Scanner userInput;
	private int cpt = 1;
	private boolean gameStatus = false;
	
	public void MasterGameMind() {
		this.randomCode();
		System.out.println("The random code : "+randomCode[0]+randomCode[1]+randomCode[2]+randomCode[3]);
		int[] userInputCode = getUserInput();
		// System.out.println("test : "+userInputCode[3]+userInputCode[2]+userInputCode[1]+userInputCode[0]);
		validation(userInputCode);
	}
	
	private void startAgain() {
		System.out.println("The random code : "+randomCode[0]+randomCode[1]+randomCode[2]+randomCode[3]);
		cpt++;
		int[] userInputCode = getUserInput();
		validation(userInputCode);
	}
	
	private void gameOver() {
		if (gameStatus) {
			cpt=0;
			userInput.close();
		}
	}
	
	private void randomCode() {
		//generate a random code of 4 digits and store them
		for (int i = 0; i < randomCode.length; i++) {
			Random rand = new Random();
			randomCode[i] = rand.nextInt(9);
		}
	}
	
	private int[] getUserInput() {
		/*- get user input and store it
		 *- get each digits from the user input */
		userInput = new Scanner(System.in);
		System.out.println(cpt+" try. Enter your code:");
		int userCode = userInput.nextInt();
		
		int userCodePart1 = 0;
		int userCodePart2 = 0;
		int userCodePart3 = 0;
		int userCodePart4 = 0;
		
		userCodePart1 = (userCode % 10);
		userCodePart2 = (userCode % 100) / 10;
		userCodePart3 = (userCode % 1000) / 100;
		userCodePart4 = (userCode % 10000) / 1000;
		
		int [] userInputCode = {userCodePart1, userCodePart2, userCodePart3, userCodePart4};
		return userInputCode;
	}
	
	private void validation(int[] userInputCode) { 
		/*- compare the generated code and user code
		 *- if both codes are exact... notify the user "code confirmed
		 *- else if there are digits from the user input that exist but not in the correct order... notify the user
		 *-			else the digits from user does not exist... notify the user"*/
		
		int fullRandomCode = randomCode[0]+randomCode[1]+randomCode[2]+randomCode[3];
		int fullUserInputCode[] = new int[4];
		/*
		fullUserInputCode[0] = userInputCode[3];
		fullUserInputCode[1] = userInputCode[2];
		fullUserInputCode[2] = userInputCode[1];
		fullUserInputCode[3] = userInputCode[0];*/
		
		
		//store the user input in the correct order
		for(int i=0, j=3; (i<4) && (j>-1); i++, j--) {
			fullUserInputCode[i] = userInputCode[j];
			//System.out.println("test: "+fullUserInputCode[i]+"___"+userInputCode[j]);
		}
		
		int userCodeId=0;
		
		if (fullRandomCode == (fullUserInputCode[0]+fullUserInputCode[1]+fullUserInputCode[2]+fullUserInputCode[3])) {
			// if the user code matches with the random code
			System.out.println("Code confirm ");
		}else  {
			//check each digits that the user entered
			System.out.println("Results...:\n");
			for (int i : randomCode) {
				if (i == fullUserInputCode[userCodeId]) {
					//if the digit from the random code matches the digit from the user input AT THE SAME INDEX OVER BOTH ARRAYS
					 System.out.print(fullUserInputCode[userCodeId]);
					 
				}else {
					//check the user digit at x index if it exists in other indexes in the random code array
					//not really working
					int checkDigit=0;
					for(int check : randomCode) {
						if(fullUserInputCode[checkDigit] == check) {
							//checkDigit++;
							//if (checkDigit > 2) {
							//System.out.print("("+fullUserInputCode[userCodeId]+")");
							System.out.print(".");
							//}
						}
					}
					//System.out.print("["+fullUserInputCode[userCodeId]+"]");
					System.out.print("X");
				}
				userCodeId++;
			}
			
			if(cpt != 8) {
				System.out.println("\nThe code you entered is incorrect, please try again.\n");
				startAgain();
			}else {
				System.out.println("Game Over !!!");
				gameOver();
			}
		}
	}
}
