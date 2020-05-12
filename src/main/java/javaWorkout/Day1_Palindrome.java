package javaWorkout;

public class Day1_Palindrome {

	public static void main(String[] args) {
		
		String input = "testleaf";
		boolean palindrome = false;
		char[] inputArray = input.toCharArray();
		for (int i = 0; i < inputArray.length; i++)
		{
			if(inputArray[i] == inputArray[inputArray.length-(i+1)])
			{
				palindrome = true;
			}
			else
			{
				palindrome = false;
			}
		}
		if(palindrome == true)
		{
			System.out.println("The given string "+input+" is a palindrome");
		}
		else
		{
			System.out.println("The given string "+input+" is not a palindrome");

		}
	}

}
