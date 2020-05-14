package javaWorkout;

public class Day2_Count_Logic2 {

	//Write a java program to find the number of Uppercase, lowercase, numbers and spaces in the following String.
	public static void main(String[] args) {
		
		String input = "1. It is Work from Home  not Work for Home";
		System.out.println("Total number of digits: "+input.chars().filter((c)->Character.isDigit(c)).count());
		System.out.println("Total number of lower case letters :"+input.chars().filter((c)->Character.isLowerCase(c)).count());
		System.out.println("Total number of UPPER case letters :"+input.chars().filter((c)->Character.isUpperCase(c)).count());
		System.out.println("Total number of Blank Spaces :"+input.chars().filter((c)->Character.isSpaceChar(c)).count());
	}
}
