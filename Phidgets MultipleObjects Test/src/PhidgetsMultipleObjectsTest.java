import java.io.IOException;
import java.sql.Timestamp;
import com.phidget22.*;

public class PhidgetsMultipleObjectsTest {
	
	static boolean turnLEDOn = false;
	static int counter = 0;

	public static void main(String[] args) throws PhidgetException, InterruptedException, IOException {
		long timeOn = 0;
		long timeOff = 0;
		long difference = 0;
		boolean programIsRunning = true;
		DigitalInput redButton = new DigitalInput();			//creates objects
		DigitalOutput redLED = new DigitalOutput();
		redButton.setHubPort(0);								//address objects
		redButton.setIsHubPortDevice(true);
		redLED.setHubPort(1); 
		redLED.setIsHubPortDevice(true);
		redButton.addStateChangeListener(new DigitalInputStateChangeListener(){			//state change listener
		    public void onStateChange(DigitalInputStateChangeEvent e){
		    	if (e.getState() == true) {
		            turnLEDOn = true;											//LED boolean
		            counter++;
		        } 
		    }
		});
		redLED.open(1000);										//opens object
		redButton.open(1000);
		while(programIsRunning){
			if (turnLEDOn && counter%2 != 0) {
				Timestamp on = new Timestamp(System.currentTimeMillis());
		        turnLEDOn = false; 
		        redLED.setState(true);							//turns on LED
		        timeOn = on.getTime();
		    }
			if (turnLEDOn && counter%2 == 0) {
				Timestamp off = new Timestamp(System.currentTimeMillis());
				turnLEDOn = false; 
		        redLED.setState(false);							//turns it off
		        timeOff = off.getTime();
		        difference = timeOff - timeOn;
		        System.out.println("The Difference in time is " + difference + "ms.");
			}
		    if (System.in.available() > 0) {
		        System.out.println("Ending Program");
		        programIsRunning = false;
		    }

		    Thread.sleep(150);
		}

		redLED.close();
		redButton.close();
	}

}
