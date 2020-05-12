package javaWorkout;

public class Day1_Palindrome_Logic2 {

	public static void main(String[] args) {

		String input = "1221";
		StringBuilder builder = new StringBuilder();
		builder.append(input);
		String reverse = builder.reverse().toString();
		if(input.equalsIgnoreCase(reverse))
		{
			System.out.println("Give String "+input+" is a palindrome");
		}
		else
		{
			System.out.println("Give String "+input+" is not a palindrome");
		}
	}

}
