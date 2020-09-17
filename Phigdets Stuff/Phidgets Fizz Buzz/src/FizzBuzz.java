import java.io.IOException;
import com.phidget22.*;

public class FizzBuzz {

	public static void main(String[] args) throws PhidgetException, InterruptedException, IOException {
		boolean programIsRunning = true;
		DigitalInput redButton = new DigitalInput();			//creates object
		redButton.setHubPort(0);								//addresses object
		redButton.setIsHubPortDevice(true);						//event listener
		redButton.addStateChangeListener(new DigitalInputStateChangeListener(){
			int increment = 0;
		    public void onStateChange(DigitalInputStateChangeEvent e){
		        if(e.getState() == true){
		            increment++;
		            if(increment % 3 == 0 && increment % 5 == 0) {
		            	System.out.println("Fizz Buzz");
		            }
		            else if(increment % 3 == 0) {
		            	System.out.println("Fizz");
		            }
		            else if(increment % 5 == 0) {
		            	System.out.println("Buzz");
		            }
		            else {
		            	System.out.println(increment);
		            }
		            
		        }
		    }
		});
		redButton.open(1000);									//open object
		Thread.sleep(1000);
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
