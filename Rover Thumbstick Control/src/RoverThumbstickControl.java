import java.io.IOException;
import com.phidget22.*;


public class RoverThumbstickControl {
	
	
	static double VerticalRatio = 0;
	static double HorizontalRatio = 0;
	static boolean ObjectDetect = false;

	public static void main(String[] args) throws PhidgetException, InterruptedException, IOException {
		Net.addServer("", "192.168.100.1", 5661, "", 0);
		boolean programIsRunning = true;
		VoltageRatioInput VerticalInput = new VoltageRatioInput();
		VoltageRatioInput HorizontalInput = new VoltageRatioInput();
		DistanceSensor SonarSensor = new DistanceSensor();
		DCMotor LeftMotors = new DCMotor();
		DCMotor RightMotors = new DCMotor();
		
		LeftMotors.setHubPort(2);
		LeftMotors.setChannel(0);
		LeftMotors.setIsHubPortDevice(false);
		RightMotors.setHubPort(2);
		RightMotors.setChannel(1);
		RightMotors.setIsHubPortDevice(false);
		VerticalInput.setHubPort(0);
		VerticalInput.setChannel(0);
		VerticalInput.setIsHubPortDevice(false);
		HorizontalInput.setHubPort(0);
		HorizontalInput.setChannel(1);
		HorizontalInput.setIsHubPortDevice(false);
		SonarSensor.setHubPort(5);
		SonarSensor.setIsHubPortDevice(false);
		
		Thread.sleep(100);
		VerticalInput.addVoltageRatioChangeListener(new VoltageRatioInputVoltageRatioChangeListener() {
			public void onVoltageRatioChange(VoltageRatioInputVoltageRatioChangeEvent e) {
				VerticalRatio = e.getVoltageRatio();
			}
		});
		HorizontalInput.addVoltageRatioChangeListener(new VoltageRatioInputVoltageRatioChangeListener() {
			public void onVoltageRatioChange(VoltageRatioInputVoltageRatioChangeEvent e) {
				HorizontalRatio = e.getVoltageRatio();
			}
		});
		SonarSensor.addDistanceChangeListener(new DistanceSensorDistanceChangeListener() {
			public void onDistanceChange(DistanceSensorDistanceChangeEvent e) {
				if(e.getDistance() > 200) {
					ObjectDetect = false;
				}
				else {
					ObjectDetect = true;
				}
			}
		});
		
		LeftMotors.open(1000);
		RightMotors.open(1000);
		VerticalInput.open(1000);
		HorizontalInput.open(1000);
		SonarSensor.open(5000);
		LeftMotors.setAcceleration(LeftMotors.getMaxAcceleration()/2);
		RightMotors.setAcceleration(RightMotors.getMaxAcceleration()/2);
		double LeftSpeed = 0;
		double RightSpeed = 0;
		
		while(programIsRunning) {
			if(ObjectDetect == true) {
				LeftMotors.setTargetVelocity(0);
				RightMotors.setTargetVelocity(0);
			}
			else {
				if(VerticalRatio >= 0) {
					if(HorizontalRatio == 0) {
						LeftSpeed = 1;
						RightSpeed = 1;
					}
					else {
						LeftSpeed = VerticalRatio + HorizontalRatio;
						RightSpeed = VerticalRatio - HorizontalRatio;
						if(LeftSpeed > 1) {
							LeftSpeed = 1;
						}
						if(RightSpeed > 1) {
							RightSpeed = 1;
						}
					}
				}
				else {
					if(HorizontalRatio == 0) {
						LeftSpeed = 1;
						RightSpeed = 1;
					}
					else {
						LeftSpeed = VerticalRatio - HorizontalRatio;
						RightSpeed = VerticalRatio + HorizontalRatio;
						if(LeftSpeed < -1) {
							LeftSpeed = -1;
						}
						if(RightSpeed < -1) {
							RightSpeed = -1;
						}
					}
				}
				LeftMotors.setTargetVelocity(LeftSpeed);
				RightMotors.setTargetVelocity(RightSpeed);
			}
			if (System.in.available() > 0) {
		        System.out.println("Ending Program");
		        programIsRunning = false;
		    }
		}
		LeftMotors.close();
		RightMotors.close();
		VerticalInput.close();
		HorizontalInput.close();
		SonarSensor.close();
	}

}
