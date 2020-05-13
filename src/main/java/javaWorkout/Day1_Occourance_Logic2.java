package javaWorkout;

import java.util.HashMap;
import java.util.Map;

public class Day1_Occourance_Logic2 {

	public static void main(String[] args) {

		String input = "You have no choice other than following me!";
		char[] inputArray = input.toCharArray();
		Map<Character, Integer> count = new HashMap<Character, Integer>();
		for (int i = 0; i < inputArray.length; i++) 
		{
			if(count.containsKey(inputArray[i]))
			{
				count.put(inputArray[i], count.get(inputArray[i])+1);
			}
			else
			{
				count.put(inputArray[i], 1);
			}
		}

		System.out.println("Occurance of o in the given input String is "+count.get('o'));
		}
	}


