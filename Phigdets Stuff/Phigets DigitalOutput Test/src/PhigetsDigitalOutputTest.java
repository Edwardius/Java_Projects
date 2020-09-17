import com.phidget22.*;

public class PhigetsDigitalOutputTest {

	public static void main(String[] args) throws PhidgetException, InterruptedException{
		DigitalOutput redLED = new DigitalOutput();		//creates object
		redLED.setHubPort(1);							//address object
		redLED.setIsHubPortDevice(true);
		redLED.open(1000);								//open object
		/*redLED.setState(true);						//turn LED on*/
		redLED.setDutyCycle(0.1); 						//set output to percentage
		Thread.sleep(1000);								//lets light stay on for longer
		redLED.close();									//turn LED off
	}

}
