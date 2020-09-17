package codes;

public class HumanClass {

	private String name;
	private int age;
	private char gender;
	
	public HumanClass() {
		name = "Thomas";
		age = 0;
		gender = 'm';
	}
	
	public HumanClass(String name, int age, char gender) {
		this.name = name;
		this.age = age;
		this.gender = gender;
	}
	
	public String getName() {			//getters and setters
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void showName() {
		System.out.println("The name is " + name);
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
		if(age > 30) {						//if age is greater than 30, 30% chance of output -5
			int randomNumber = (int) (10*Math.random());
			if(randomNumber < 3)
				this.age = -5;
			
			else
				this.age = age;
		}
	}
	
	public void showAge() {
		System.out.println("Their age is " + age);
	}
	
	public char getGender() {				//Gender Related methods
		return gender;
	}
	
	public void setGender(char gender) {
		this.gender = gender;
	}
	
	public void showGender() {
		System.out.println("Their gender is " + gender);
	}
	
	public int add(int a, int b) {					//adding ints
		int sum = a+b;
		return sum;
	}
	
	public int add(String a, String b) {			//adding strings
		int inta = 0;
		int intb = 0;
		if(a == "one")				//first number
			inta = 1;
		if(a == "two")
			inta = 2;
		if(a == "three")
			inta = 3;
		if(a == "four")
			inta = 4;
		if(a == "five")
			inta = 5;
		if(a == "six")
			inta = 6;
		if(a == "seven")
			inta = 7;
		if(a == "eight")
			inta = 8;
		if(a == "nine")
			inta = 9;
		if(a == "ten")
			inta = 10;
		if(b == "one")				//second number
			intb = 1;
		if(b == "two")
			intb = 2;
		if(b == "three")
			intb = 3;
		if(b == "four")
			intb = 4;
		if(b == "five")
			intb = 5;
		if(b == "six")
			intb = 6;
		if(b == "seven")
			intb = 7;
		if(b == "eight")
			intb = 8;
		if(b == "nine")
			intb = 9;
		if(b == "ten")
			intb = 10;
		int sum = inta + intb;     //calculate string sum
		if(inta == 0 || intb == 0)
			return -1;
			
		return sum;
	
	}
}

