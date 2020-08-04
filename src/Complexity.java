/**
 * Dan Peterson
 * 109091561
 * daniel.peterson@stonybrook.edu
 * Homework #3
 * CSE 214 Recitation #5
 * Sun Lin
 * @author Dan
 */

/**
 * This class creates an object of the type Complexity. This object represents the complexity of something
 * in this case, it is the complexity of a block of code within a Python program. The complexity has two parts:
 * an n part and a log part. The n part represents the complexity of 'for' loops and the log part represents the
 * complexity of 'while' loops. With each successive loop, the exponent value of each of these two parts increases.
 * The n_power and log_power values within this class are used to store these exponent values. Finally there is a 
 * method which outputs the complexity of the current object to a string. 
 */
public class Complexity {

	private int n_power;
	private int log_power;
	
	/**
	 * Creates an instance of <code>Complexity</code> - Constructor
	 * @param n_power = The exponent value for the n component of the complexity - int
	 * @param log_power = The exponent value for the log component of the complexity - int
	 */	
	public Complexity()
	{
		n_power = 0;
		log_power = 0;
	}
	
	/**
	 * Sets the value of <code>n_power</code> to the int value passed in.
	 * @param n_power = The exponent value for the n component of the complexity - int
	 */
	public void setN_Power(int n_power)
	{
		this.n_power = n_power;
	}
	
	/**
	 * Sets the value of <code>log_power</code> to the int value passed in.
	 * @param log_power = The exponent value for the log component of the complexity - int
	 */
	public void setLog_Power(int log_power)
	{
		this.log_power = log_power;
	}
	
	/**
	 * Returns the value of <code>n_power</code> - int
	 * @return n_power - int
	 */
	public int getN_Power()
	{
		return n_power;
	}
	
	/**
	 * Returns the value of <code>log_power</code> - int
	 * @return log_power - int
	 */
	public int getLog_Power()
	{
		return log_power;
	}
	
	/**
	 * Returns the string representation of the current Complexity - String
	 * @return msg - String
	 */
	public String toString()
	{
		String msg = "";
		boolean flag = false;
		if(this.n_power > 1)
		{
			msg = "O(n ^ " + n_power;
			if(this.log_power != 0)
			{
				flag = true;
			}
		}
		else if (this.n_power == 1)
		{
			msg = "O(n";
			if(this.log_power != 0)
			{
				flag = true;
			}
		}
		if(flag == true)
		{
			msg = msg + " * ";
		}
		if(flag == false)
		{
			msg = msg + ").";
		}
		if(this.n_power == 0)
		{
			msg = "O(";
		}
		if(this.log_power > 1)
		{
			msg = msg + "log (n) ^ " + log_power + ").";
		}
		else if (this.log_power == 1)
		{
			msg = msg + "log (n)" +  ").";
		}
		if((this.n_power == 0) && (this.log_power == 0))
		{
			msg = "O(1)";
		}
		return msg;
	}
}
