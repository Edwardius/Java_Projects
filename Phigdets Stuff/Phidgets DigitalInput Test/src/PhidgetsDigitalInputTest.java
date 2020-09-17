import com.phidget22.*;
import java.io.IOException;

public class PhidgetsDigitalInputTest {

	public static void main(String[] args) throws PhidgetException, InterruptedException, IOException {
		boolean programIsRunning = true;
		DigitalInput redButton = new DigitalInput();			//creates object
		redButton.setHubPort(0);								//addresses object
		redButton.setIsHubPortDevice(true);
		redButton.addStateChangeListener(new DigitalInputStateChangeListener(){
		    public void onStateChange(DigitalInputStateChangeEvent e){
		        if(e.getState() == true){
		            System.out.println("Red Button Pressed");
		        }
		        else{
		            System.out.println("Red Button Not Pressed");
		        }
		    }
		});
		redButton.open(1000);									//open object
		Thread.sleep(1000);
		/*if (redButton.getState() == true) {						//read digital input state
		    System.out.println("Red Button Pressed");
		} 
		else {
		    System.out.println("Red Button Not Pressed");
		}*/
		while (programIsRunning) {
		    Thread.sleep(150);									//sleep before another check
		    if (System.in.available() > 0) {					//press enter to exit program
		        System.out.println("Ending Program");
		        programIsRunning = false;
		    }
		}
		redButton.close();										//close object
		
	}

}
