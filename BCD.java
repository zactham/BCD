//Zac Thamer
//Period 3
// 11/5/15
//Allows a user to do a variety of functions, including adding and multiplying, to a number whose digits are stored in a dynamic array (BCD)
public class BCD 
{
	private int [] digit;//Class variable

	// DESC: Default constructor
	// INPUT: none
	// OUTPUT: none
	public BCD()
	{
		digit = null;
	}

	//DESC: Constructor assigns digit to a new digit
	// INPUT: bcdDigits (integer array)
	// OUTPUT: none
	public BCD(int bcdDigits [])
	{
		digit = bcdDigits;
	}

	//DESC: Constructor creates a BCD equivalent to the integer 
	// INPUT: num (value to be stored inside the BCD)
	// OUTPUT: none
	public BCD(int num)
	{
		int DIGIT = num % 10;
		num /= 10;

		digit = new int [1];
		digit [0] = DIGIT;

		while (num>0)
		{
			DIGIT = num % 10;
			addADigit(DIGIT);
			num /= 10;
		}
	}

	// DESC: Multiplies a number by ten and prints the equation
	// INPUT: none
	// OUTPUT: result (the final result of the BCD multiplied by 10)
	public BCD multiplyByTen()
	{
		System.out.print("10 * " + toString() + " = ");
		BCD result = multiplyByTenNoPrint();//calls multiplyByTenNoPrint() so if it is used in other equations it doesn't print	
		result.print();
		System.out.println();
		return result;
	}

	// DESC: Multiplies a number by ten but doesn't print the equation, so if it is used in other equations it doesn't print them	
	// INPUT: none
	// OUTPUT: result (the final result of the BCD multiplied by 10)
	private BCD multiplyByTenNoPrint()
	{
		BCD bcd1 = new BCD (0);
		BCD result = new BCD (0);
		int[] intArray = new int[digit.length+1];

		if (digit.length == 1 && digit[0] == 0)
		{
			return bcd1;
		}
		else
		{
			intArray = new int[digit.length+1];
			intArray [0] = 0;

			for (int counter = 0; counter<digit.length; counter++)
			{
				intArray[counter+1] = digit[counter];
			}

			result = new BCD (intArray);
			return result;
		}
	}

	// DESC: Multiplies the BCD by a number and doesn't print the equation, so if it is used in other equations it doesn't print them
	// INPUT: num (the number multiplying the BCD's number)
	// OUTPUT: the resulting BCD that was multiplied
	private BCD multiplyByNoPrint(int num)
	{
		if(num == 0)//if multiplying by 0 it returns 0
		{
			return new BCD(0);
		}

		if(num == 1)//if multiplying by 1 it returns the b1
		{
			BCD b1 = new BCD(digit);
			return b1;
		}

		if(num == 10)//if multiplying by 10 it multiplies it by 10
		{
			return multiplyByTenNoPrint();
		}

		else//multiplies the numbers 
		{
			BCD result = new BCD();
			int carry = 0;

			for (int counter = 0; counter<digit.length; counter++)
			{
				int digitMult = digit[counter]*num + carry;
				int remainder = digitMult % 10;
				result.addADigit(remainder);
				carry = digitMult / 10;		
			}

			// handle final carry value
			if (carry != 0)
			{
				do 
				{
					int remainder = carry % 10;
					result.addADigit(remainder);
					carry /= 10;	
				}
				while (carry > 9);

				if (carry > 0)
				{
					result.addADigit(carry);
				}
			}
			return result;
		}
	}

	// DESC: Multiplies the BCD by a number and prints the equation
	// INPUT: num (the number multiplying the BCD's number)
	// OUTPUT: result (the resulting BCD that was multiplied)
	public BCD multiplyBy(int num)
	{
		System.out.print(num  + " * " + toString() + " = ");
		BCD result = multiplyByNoPrint(num);//calls multiplyByNoPrint() so if it is used in other equations it doesn't print	
		result.print();
		System.out.println();
		return result;
	}

	// DESC: Multiplies two BCDs and doesn't print the equation, so if it is used in other equations it doesn't print them
	// INPUT: other (the multiplying BCD)
	// OUTPUT: b1 (the result of two BCDs multiplying)
	private BCD multiplyBCDsNoPrint(BCD other)
	{
		BCD b1 = new BCD(0);
		int DIGIT = 0;

		do
		{
			b1 = b1.addBCDsNoPrint (other.multiplyByNoPrint(digit[DIGIT]));
			other = other.multiplyByTenNoPrint();//calls multiplyByTenNoPrint() so if it is used in other equations it doesn't print	
			DIGIT++;
		}	

		while (DIGIT < digit.length);
		return b1;
	}

	// DESC: Multiplies two BCDs and prints the equation
	// INPUT: other (the multiplying BCD)
	// OUTPUT: b1 (the result of two BCDs multiplying)
	public BCD multiplyBCDs(BCD other)
	{
		System.out.print(other + " * " + this + " = ");//all of the print info
		BCD result = multiplyBCDsNoPrint(other);//calls multiplyBCDsNoPrint() so if it is used in other equations it doesn't print	
		result.print();
		System.out.println();
		return result;
	}

	//DESC: Adds two BCDs together and creates a new BCD
	// INPUT: other (the adding BCD)
	// OUTPUT: none
	public BCD addBCDs (BCD other)
	{
		System.out.print(other  + " + " + toString() + " = ");
		BCD result = addBCDsNoPrint(other);//calls addBCDsNoPrint() so if it is used in other equations it doesn't print	
		result.print();
		System.out.println();
		return result;
	}

	//DESC: Adds two BCDs together and doesn't print the equation, so if it is used in other equations it doesn't print them
	// INPUT: other (the adding BCD)
	// OUTPUT: none
	private BCD addBCDsNoPrint (BCD other)
	{
		BCD result = new BCD();
		int carry = 0;

		// gets the length of the largest BCD 
		int maxLength = numberOfDigits();
		if (other.numberOfDigits() > maxLength)
			maxLength = other.numberOfDigits();

		for (int i=0; i<maxLength; i++)
		{
			// add both digits for this place (ones, tens, hundreds, etc)
			int myDigit = nthDigit(i);
			if (myDigit == -1)
				myDigit = 0;

			int otherDigit = other.nthDigit(i);
			if (otherDigit == -1)
				otherDigit = 0;

			int digitSum = myDigit + otherDigit + carry;
			int remainder = digitSum % 10;
			result.addADigit(remainder);
			carry = digitSum / 10;			
		}

		// handle final carry value
		if (carry != 0)
			result.addADigit(carry);

		return result;
	}

	//DESC: Returns the length of the digits array
	// INPUT: none
	// OUTPUT: the length of digit
	public int numberOfDigits()
	{
		return digit.length;
	}

	//DESC: Returns a certain number in the digits array using their index
	// INPUT: n (the specific index)
	// OUTPUT: a digit at specific index
	public int nthDigit(int n)
	{
		if (n >= digit.length || n < 0)
			return -1;
		else 
			return digit[n];
	}

	//DESC: Prints the digits in reverse order
	// INPUT: none
	// OUTPUT: none
	public void print()
	{
		System.out.print(this);
	}

	//DESC: Prints the digits into a String
	// INPUT: none
	// OUTPUT: digitPrint (the new String containing the digits)
	public String toString()
	{
		String digitPrint="";
		for (int counter = digit.length-1; counter>= 0; counter--)
		{
			digitPrint += digit[counter];

			if (counter % 3 == 0 && counter !=0)
			{
				digitPrint += ",";
			}
		}
		return digitPrint;
	}

	// DESC: Outputs a BCD representing the factorial of a number
	// INPUT: num (the number of the factorial that is wanted)
	// OUTPUT: fact (the factorial as a BCD)
	public static BCD factorial(int num)
	{
		BCD fact = new BCD (1);
		for (int a = 1; a<=num; a++)
		{
			fact = fact.multiplyByNoPrint(a);//Multiplies each number from 1-num to create a factorial
		}
		System.out.println(num + "! = " + fact);
		return fact;//returns the factorial in a BCD
	}

	// DESC: Finds a BCD to a certain power
	// INPUT: num (the power)
	// OUTPUT: bPow (the final result as a BCD)
	public BCD pow (int num)
	{
		BCD bPow = new BCD(digit);
		if (num == 0)
		{
			bPow = new BCD (1);
		}
		else 
			if (num == 1)
			{
				bPow = this;
			}
			else
			{
				for (int counter = 2; counter<=num; counter++)
				{
					bPow =  bPow.multiplyBCDsNoPrint(this);
				}
			}
		System.out.println(this + "^" + num + " = " + bPow);
		return bPow;
	}

	//DESC: Adds a digit to the array
	// INPUT: newdigit (the digit being added)
	// OUTPUT: none
	public void addADigit(int newdigit)
	{
		if (newdigit<0 || newdigit>9)
		{
			System.out.println("ERROR - Invalid digit: "+ newdigit);
			return;
		}
		// get the length of our BCD, or 0 if empty
		int length = 0;
		if (digit != null)
			length = digit.length;

		int [] newDigitArray = new int[length+1];// makes a new array that is 1 index longer than digit

		for (int counter = 0; counter<length; counter++)//stores every int in digit in newDigitArray with the counterpart
		{
			newDigitArray[counter] = digit[counter];
		}

		newDigitArray[length] = newdigit;//stores the new digit in the last index of the new digit array

		digit = newDigitArray;
	}
}
