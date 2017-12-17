//Zac Thamer
//Period 3
// 11/9/15
//Tests BCD
public class BCDDriver 
{
	public static void main (String[]args)
	{


		int []list = {1,9,1,1,5,6,7,8,9,1,8,7,6,5,1,3,2};
		BCD b1 = new BCD(list);
		BCD b2 = new BCD(654321);
		
		//System.out.print ("23,156,781,987,651,191 + 654321 = ");
		BCD answer = b1.addBCDs(b2);
		//answer.print();
		
		System.out.println ();
		
		//System.out.print ("23,156,781,987,651,191 * 654321 = ");
		answer=b2.multiplyBCDs(b1);
		//answer.print();
		
		System.out.println ();
		
		BCD b3 = new BCD (21001);
		//System.out.print ("21001 * 4 = ");
		answer=b3.multiplyBy(4);
		//answer.print();

		BCD b35 = new BCD (55556);
		answer=b35.multiplyBy(55);
		//answer.print();
		
		System.out.println ();

		//System.out.print ("12141 + 9999 = ");
		
		int []list1 = {1,4,1,2,1};
		BCD b5 = new BCD(list1);
		BCD b6 = new BCD(9999);

		answer= b5.addBCDs(b6);
		//answer.print();

		System.out.println ();
		
		//System.out.print ("654321 ^ 2 = ");
		answer=b2.pow(2);
		//answer.print();
		
		System.out.println ();
		
		//System.out.print ("654! = ");
		answer=BCD.factorial(654);
		//answer.print();

		System.out.println ();
		
		//System.out.print ("654 ^ 120 = ");
		b2 = new BCD(654);
		answer=b2.pow(120);
		//answer.print();
		
		System.out.println ();
		
		//System.out.print ("21! = ");
		answer=BCD.factorial(21);
		//answer.print();


	}
}
