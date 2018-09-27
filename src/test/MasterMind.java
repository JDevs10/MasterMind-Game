package test;

import java.util.Random;
import java.util.Scanner;

public class MasterMind {
	private int[] randomCode = new int[4];
	private int cpt = 0;
	
	public void MasterGameMind() {
		this.randomCode();
		System.out.println("The random code : "+randomCode[0]+randomCode[1]+randomCode[2]+randomCode[3]);
		int[] userInputCode = getUserInput();
		// System.out.println("test : "+userInputCode[3]+userInputCode[2]+userInputCode[1]+userInputCode[0]);
		validation(userInputCode);
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
		Scanner userInput = new Scanner(System.in);
		System.out.println("Entrer votre code : ");
		int userCode = userInput.nextInt();
		userInput.close();
		
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
	
	private void validation(int[] a) {
		/*- compare the generated code and user code
		 *- if both codes are exact... notify the user "code confirmed
		 *- else if there are digits from the user input that exist but not in the correct order... notify the user
		 *-			else the digits from user does not exist... notify the user"*/
		
		int fullRandomCode = randomCode[0]+randomCode[1]+randomCode[2]+randomCode[3];
		int fullUserInputCode = a[3]+a[2]+a[1]+a[0];
		
		if (fullRandomCode == fullUserInputCode) {
			System.out.println("Code confirm ");
		}else  {
			for (int i : randomCode) {
				if (i == randomCode[0]) {
					 System.out.println("index 1 : "+i+" == "+randomCode[0]);
					 
				}else {
					System.out.println("index 1 : "+i+" != "+randomCode[0]);
				}
				
				if (i == randomCode[1]) {
					System.out.println("index 2 : "+i+" == "+randomCode[1]);
					
				}else {
					System.out.println("index 2 : "+i+" != "+randomCode[1]);
				}
				
				if (i == randomCode[2]) {
					System.out.println("index 3 : "+i+" == "+randomCode[2]);
					
				}else {
					System.out.println("index 3 : "+i+" != "+randomCode[2]);
				}
				
				if (i == randomCode[3]) {
					System.out.println("index 4 : "+i+" == "+randomCode[3]);
				}else {
					System.out.println("index 4 : "+i+" != "+randomCode[3]);
				}
				//System.out.println(i);
			}
		}
	}
}
