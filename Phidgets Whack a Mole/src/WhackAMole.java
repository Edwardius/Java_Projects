import java.io.IOException;
import java.sql.Timestamp;
import com.phidget22.DigitalInput;
import com.phidget22.DigitalInputStateChangeEvent;
import com.phidget22.DigitalInputStateChangeListener;
import com.phidget22.DigitalOutput;
import com.phidget22.PhidgetException;
import java.util.Random;

public class WhackAMole {
	
	static boolean redButtonPressed = false;
	static boolean greenButtonPressed = false;

	public static void main(String[] args) throws PhidgetException, InterruptedException, IOException {
		long counter = 0;
		long avg = 0;
		long timeOn = 0;
		long timeOff = 0;
		long difference = 0;
		int random = 0;
		int referenceRed = 3;
		int referenceGreen = 45;
		Random rand = new Random();
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
		            redButtonPressed = true;
		        }
		    }
		});
		greenButton.addStateChangeListener(new DigitalInputStateChangeListener() {
		    public void onStateChange(DigitalInputStateChangeEvent e) {
		        if (e.getState() == true) {
		            greenButtonPressed = true;
		        }
		    }
		});
		redLED.open(1000);										//opens objects
		greenLED.open(1000);
		redButton.open(1000);
		greenButton.open(1000);
		while(programIsRunning){
			while(referenceRed != random && referenceGreen != random) {
				random = rand.nextInt(50);
				Thread.sleep(150);
				Timestamp on = new Timestamp(System.currentTimeMillis());
				timeOn = on.getTime();
			}
			if(referenceRed == random) {
				redLED.setState(true);
			}
			if(referenceGreen == random) {
				greenLED.setState(true);
			}
				if(redButtonPressed && referenceRed == random){				//if correct button pressed
			    	redLED.setState(false);
			    	Timestamp off = new Timestamp(System.currentTimeMillis());
			    	timeOff = off.getTime();
			    	redButtonPressed = false;//reset variable
			    }
			    if(greenButtonPressed && referenceGreen == random){			//if correct button pressed
			    	greenLED.setState(false);
			    	Timestamp off = new Timestamp(System.currentTimeMillis());
			    	timeOff = off.getTime();
			    	greenButtonPressed = false;//reset variable
			    }
			if(timeOff != 0 && counter != 10) {
				difference = timeOff - timeOn;
				avg = avg*counter;
				counter++;
				avg = avg + difference;
				avg = avg/counter;
				System.out.println("It took you " + difference + "ms to whack the mole.");
				random = 0;
				timeOff = 0;
				timeOn = 0;
				redLED.setState(false);
				greenLED.setState(false);
			}
			if(counter == 10) {
				break;
			}
		}
		System.out.println("You averaged a time of " + avg + "ms.");
		//close objects
		redLED.close();
		greenLED.close();
		redButton.close();
		greenButton.close();
	}

}
