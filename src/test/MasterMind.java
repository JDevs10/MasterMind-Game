package test;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class MasterMind {
	private int[] randomCode = new int[4];
	private Scanner userInput;
	Scanner scAnswer;
	private int cpt = 8;
	private int y = 0;
	private String userAnswer;

	public void MasterGameMind() {
		/* Generate a random code
		 * get the user input
		 * compare the user input with the random generate code
		 * if the user does not find the code before the number of attempts expire, he/she have the option to play again*/
		do {
			this.randomCode();
			do {
				if (y == 4) {
					System.out.println("	Code confirm !!!\n\n");
					break;
				}
				//System.out.println("\nThe random code : "+randomCode[0]+randomCode[1]+randomCode[2]+randomCode[3]);
				int[] userInputCode = getUserInput();
				validation(userInputCode);
			}while(this.cpt >= 1);
			System.out.println("\n\nGame over");
			cpt=8;
			y=0;

			System.out.println("The generated code was "+randomCode[0]+randomCode[1]+randomCode[2]+randomCode[3]+"\n\n"
					+ "Would you like to play again ? yes/no");
			scAnswer = new Scanner(System.in);
			userAnswer = scAnswer.nextLine();
		}while(userAnswer.trim().equals("yes"));
		
		/* if the user wants to close the game,
		 * close all the Scanner,
		 * the user will be notify and the games shut down*/
		scAnswer.close();
		userInput.close();
		System.out.println("\n\n\nSee you soon !!!");
		System.exit(0);
	}

	private void randomCode() {
		//generate a random code of 4 digits and store them
		for (int i = 0; i < randomCode.length; i++) {
			Random rand = new Random();
			randomCode[i] = rand.nextInt((9-1)+1)+1;
			
		}
	}
	
	private int[] getUserInput() {
		/*- get user input and store it
		 *- get each digits from the user input */
		userInput = new Scanner(System.in);
		System.out.println("Number of attempt(s) left: "+cpt+"\nEnter your code:");
		int userCode = userInput.nextInt();
		
		int userCodePart1 = 0;
		int userCodePart2 = 0;
		int userCodePart3 = 0;
		int userCodePart4 = 0;
		
		userCodePart1 = (userCode % 10);
		userCodePart2 = (userCode % 100) / 10;
		userCodePart3 = (userCode % 1000) / 100;
		userCodePart4 = (userCode % 10000) / 1000;
		
		int [] userInputCode = {userCodePart4, userCodePart3, userCodePart2, userCodePart1};
		return userInputCode;
	}
	
	private void validation(int[] userInputCode) {
		/*- compare the generated code and user code
		 *- if both codes are exact... notify the user "code confirmed
		 *- else if there are digits from the user input that exist but not in the correct order... notify the user
		 *-			else the digits from user does not exist... notify the user"*/
		
		System.out.println("\nSymbol reference:\n"
				+ "-'.' means the number exist but the badly place\n"
				+ "-'X' means The number does not exist.\n"
				+ "Results...:  ");
		for (int i = 0; i < userInputCode.length; i++) {
			if (randomCode[i] == userInputCode[i]) {
				System.out.print(randomCode[i]);
				y++;
			}else if(Arrays.toString(randomCode).contains( String.valueOf(userInputCode[i]) )) {
				System.out.print(".");
			}else {
				System.out.print("X");
			}
		}
		
		//if the code is not confirmed. the user will try again with a limited number of attempts and he will be notify
		cpt--;
		if (y != 4) {
			y=0;
			System.out.println("\nThe code you entered is incorrect, please try again.\n\n");
		}
	}
	
	
}
