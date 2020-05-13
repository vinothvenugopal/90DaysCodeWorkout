package javaWorkout;

public class Day2_Reverse_Logic1 {

	// program to reverse the even numbered words of a sentence
	public static void main(String[] args) {

		String input = "When the world realise its own mistake, corona will dissolve automatically";
		String[] splitInput = input.split(" ");
		for (int i = 0; i < splitInput.length; i++) 
		{
			if(i%2 != 0)
			{
				StringBuffer buffer = new StringBuffer(splitInput[i]);
				splitInput[i] = buffer.reverse().toString();
			}
		}
		for (int i = 0; i < splitInput.length; i++) 
		{
			System.out.print(splitInput[i]+" ");
		}
	}

}
