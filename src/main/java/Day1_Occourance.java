package javaWorkout;

public class Day1_Occourance {

	public static void main(String[] args) {

		String input = "You have no choice other than following me!";
		int count = 0;
		char[] inputArray = input.toCharArray();
		for (int i = 0; i < inputArray.length; i++) 
		{
			if(inputArray[i] == 'o')
			{
				count = count+1;
			}
		}
		System.out.println("Occourance of o is: "+count);
		
	}

}
