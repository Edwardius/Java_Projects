import com.phidget22.DigitalOutput;
import com.phidget22.PhidgetException;
import java.util.*;

public class PhigetsBlinkingLight {
	
	public static int errorTrap (int min, int max) {               //errorTrap
		Scanner input = new Scanner(System.in);
		int value = 0;
		boolean valid = true;
		do {
			valid = true;
			try {
				value = input.nextInt();
			} catch (Exception e) {                           //catches invalid input
				valid = false;
				String garbage = input.nextLine();
				System.out.println("Invalid Input. Please try again");
			}
			if (valid == true && (value < min || value > max)) {        //catches value which exceeds the range
				valid = false;
				String garbage = input.nextLine();
				System.out.println("Invalid Input. Please try again");
			}
		}while(!valid);
		return(value);
	}

	public static void main(String[] args) throws PhidgetException, InterruptedException {
		/*
		DigitalOutput redLED = new DigitalOutput();		//creates object
		redLED.setHubPort(1);							//address object
		redLED.setIsHubPortDevice(true);
		redLED.open(1000);								//open object
		redLED.setState(true);							//turn LED on
		redLED.setDutyCycle(0.1); 						//set output to percentage
		Thread.sleep(1000);								//lets light stay on for longer
		redLED.close();*/								//turn LED off
		
		int menuChoice = 1;
		Scanner input = new Scanner(System.in);
		DigitalOutput redLED = new DigitalOutput();
		DigitalOutput greenLED = new DigitalOutput();
		redLED.setHubPort(1);							//address object
		redLED.setIsHubPortDevice(true);
		greenLED.setHubPort(4);
		greenLED.setIsHubPortDevice(true);
		do{
			System.out.println("What would you like to do with the red light?\n0. Exit Code\n1. "
					+ "Make the light blink constantly\n2. Make light fluctuate in brightness");
			menuChoice = errorTrap(0,2);
			if(menuChoice == 1) {								//make light blink
				int onInterval = 0;
				int offInterval = 0;
				System.out.println("How long do you want the light to stay on? 1000 to 10000ms.");
				onInterval = errorTrap(1000, 10000);
				System.out.println("How long do you want the light to stay off? 1000 to 10000ms.");
				offInterval = errorTrap(1000, 10000);
				int loop = 0;
				System.out.println("Look at Phidget....");
				while(loop < 10) {
					redLED.open(1000);								//open object
					greenLED.open(1000);
					redLED.setDutyCycle(1); 						//set output to percentage
					greenLED.setDutyCycle(1); 
					Thread.sleep(onInterval);								//lets light stay on for longer
					redLED.close();	
					greenLED.close();
					Thread.sleep(offInterval);
					loop++;
				}
			}
			if(menuChoice == 2) {								//make light fluctuate
				redLED.open(1000);								//open object
				greenLED.open(1000);
				for(double x = 0; x < 100; x = x + 0.01) {
					redLED.setDutyCycle(Math.abs(Math.sin(x))); 						//set output to percentage
					greenLED.setDutyCycle(Math.abs(Math.cos(x)));
				}
				redLED.close();	
				greenLED.close();
			}
		}while(menuChoice !=0);
		
	}

}
