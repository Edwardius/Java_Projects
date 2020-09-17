import com.phidget22.*;
import java.io.IOException;

public class PhidgetsAllObjectsTest {
	
	static boolean turnRedLEDOn = false;
	static boolean turnGreenLEDOn = false;

	public static void main(String[] args) throws PhidgetException, InterruptedException, IOException {
		boolean programIsRunning = true;
		DigitalInput redButton = new DigitalInput();				//creates objects
		DigitalInput greenButton = new DigitalInput();
		DigitalOutput redLED = new DigitalOutput();
		DigitalOutput greenLED = new DigitalOutput();
		redButton.setHubPort(0);									//addresses objects
		redButton.setIsHubPortDevice(true);
		greenButton.setHubPort(5);
		greenButton.setIsHubPortDevice(true);
		redLED.setHubPort(1);
		redLED.setIsHubPortDevice(true);
		greenLED.setHubPort(4);
		greenLED.setIsHubPortDevice(true);
		redButton.addStateChangeListener(new DigitalInputStateChangeListener() {				//state change listeners
		    public void onStateChange(DigitalInputStateChangeEvent e) {
		        if (e.getState() == true) {
		            turnRedLEDOn = true;
		        } 
		    }
		});
		greenButton.addStateChangeListener(new DigitalInputStateChangeListener() {
		    public void onStateChange(DigitalInputStateChangeEvent e) {
		        if (e.getState() == true) {
		            turnGreenLEDOn = true;
		        } 
		    }
		});
		redLED.open(1000);										//opens objects
		greenLED.open(1000);
		redButton.open(1000);
		greenButton.open(1000);
		double redCounter = 0;
		double greenCounter = 0;
		while(programIsRunning){
		    //turn red LED on based on red button input
		    if(turnRedLEDOn){
		        turnRedLEDOn = false;//reset variable
		        redCounter = redCounter + 0.1;
		        if(redCounter >= 1.0) {
		        	redCounter = 1;
		        	greenLED.setState(false);
			    	for(int x = 0; x < 10; x++) {
			    		Thread.sleep(500);
			    		redLED.setState(true);
			    		Thread.sleep(500);
			    		redLED.setState(false);
			    	}
			    	programIsRunning = false;
			    	break;
			    }
		        redLED.setDutyCycle(redCounter);
		    }
		    //turn green LED on based on green button input
		    if(turnGreenLEDOn){
		        turnGreenLEDOn = false;//reset variable
		        greenCounter = greenCounter + 0.1;
		        if(greenCounter >= 1.0) {
		        	greenCounter = 1;
		        	redLED.setState(false);
			    	for(int x = 0; x < 10; x++) {
			    		Thread.sleep(500);
			    		greenLED.setState(true);
			    		Thread.sleep(500);
			    		greenLED.setState(false);
			    	}
			    	programIsRunning = false;
			    	break;
			    }
		        greenLED.setDutyCycle(greenCounter);
		    }
		    //check if ENTER key has been pressed
		    if (System.in.available() > 0) {
		        System.out.println("Ending Program");
		        programIsRunning = false;
		    }
		}
		//close objects
		redLED.close();
		greenLED.close();
		redButton.close();
		greenButton.close();
	}

}
