package codes;


public class Main {
	
	public static void checkname(HumanClass human) {
		System.out.println("The person's name is: " +human.getName());
	}
	
	public static void Birthday(HumanClass human) {
		human.setAge(human.getAge()+1);
	}

	public static void main(String[] args) {
		HumanClass human1 = new HumanClass();
		HumanClass human2 = new HumanClass("Jack", 13, 'm');		
		human1.showName();
		human2.showName();	
		System.out.println(human1.getName() + " says the sum is " + human1.add("nine", "three"));	 
		human1.setAge(13); 		
		checkname(human1);
		human1.showName();
		System.out.println(human1.getName() + " is " + human1.getAge() +" years old");
		Birthday(human1);
		System.out.println(human1.getName() + " is " + human1.getAge() +" years old");
	}
	
	
}


