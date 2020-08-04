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
 * This class contains the trace function, which determines the order of complexity of the file, and the main function,
 * which provides a menu for the user. The user can enter a filename at this menu or can type 'quit' to exit the program.
 * After the user enters a filename, if it does not exist, then the user receives an error message and can enter another
 * filename. If the file does exist, then the user is shown a trace of the file as it parses the code and then the final
 * complexity of the file is displayed for the user to see.
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.ClassCastException;
import java.util.EmptyStackException;
import java.util.Stack;
import java.util.Scanner;

public class PythonTracer {

	public static final int SPACE_COUNT = 4;
	static Stack s1 = new Stack();
	
	/**
	 * Takes a String input entered by the user and checks to see if a file exists with that name. If it does, the file is
	 * parsed and its order of complexity is determined in Big-O notation. 
	 * @param filename = Name of the file to be read and traced - String
	 * @return test = The Complexity object which holds the overall complexity of the traced file - Complexity
	 * @throws IllegalArgumentException = Indicates that an illegal file or filename has been entered
	 * @throws FileNotFoundException = Indicates that the indicated file does not exist and cannot be traced
	 * @throws ClassCastException = Indicates that the current object cannot be cast as a particular type
	 */
	public static Complexity traceFile(String filename)throws IllegalArgumentException, FileNotFoundException, ClassCastException
	{
		try
		{
			FileInputStream fis = new FileInputStream(filename);
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Specified file was not found.");
		}
		Scanner reader = new Scanner(new File(filename));
		String data = "";
		int indents = 0;
		int lineSpaces = 0;
		int count = 0;
		int blocks = 0;
		int flagcount = 1;
		boolean flag = false;
		String backup = null;
		CodeBlock oldTop = new CodeBlock();
		Complexity oldTopComplexity = new Complexity();
		while(reader.hasNextLine())
		{
			data = reader.nextLine();
			if(data == null)
			{
				throw new IllegalArgumentException("File is null. Please choose a valid file.");
			}
			if(data.startsWith("#"))
			{
				//This line is a comment and nothing happens
			}
			if(data.startsWith(" "))
			{
				lineSpaces = data.indexOf(data.trim());
			}
			indents = lineSpaces / SPACE_COUNT;
			while(indents < s1.size())
			{
				if(indents == 0)
				{
					break;
					//close file
				}
				else
				{
					oldTop = (CodeBlock) s1.pop();
					//if(oldTopComplexity.getLog_Power() < oldTop.getHighestSubComplexity().getLog_Power())
					//{
					//	oldTopComplexity = oldTop.getHighestSubComplexity();
					//}
					//if(oldTopComplexity.getN_Power() < oldTop.getHighestSubComplexity().getN_Power())
					//{
					//	oldTopComplexity = oldTop.getHighestSubComplexity();
					//}
					oldTopComplexity = oldTop.getHighestSubComplexity();
					try
					{
						if(oldTopComplexity.getN_Power() == 1)
						{
							count++;
						}
						oldTopComplexity.setN_Power(count);
						System.out.println("Complexity update: new complexity = " + oldTopComplexity.toString());
						//System.out.print("The complexity of block " + blocks);
						//for(int i = 0; i < indents; i++)
						//{
						//	System.out.print("." + flagcount);
						//}
						//System.out.println(" is " + oldTopComplexity.toString());
						backup = oldTopComplexity.toString();
					}
					catch(NullPointerException e)
					{
						
					}					
				}
			}
			if(data.contains("def "))
			{
				CodeBlock c1 = new CodeBlock();
				Complexity c2 = new Complexity();
				blocks++;
				c2.setN_Power(0);
				c2.setLog_Power(0);
				c1.setBlockComplexity(c2);
				c1.setHighestSubComplexity(c2);
				s1.push(c2);
			}
			if(data.contains("for "))
			{
				if(!data.trim().startsWith("#") && !data.trim().startsWith("print"))
				{
					CodeBlock c1 = new CodeBlock();	
					Complexity c2 = new Complexity();
					if(data.contains("log"))
					{
						c2.setN_Power(0);
						c2.setLog_Power(1);
						c1.setBlockComplexity(c2);
						c1.setHighestSubComplexity(c2);
						s1.push(c1);
					}
					else
					{
						c2.setN_Power(1);
						c2.setLog_Power(0);
						c1.setBlockComplexity(c2);
						c1.setHighestSubComplexity(c2);
						s1.push(c1);
					}
				}
			}
			if(data.contains("while "))
			{
				if(!data.trim().startsWith("#") && !data.trim().startsWith("print"))
				{
					CodeBlock c1 = new CodeBlock();
					Complexity c2 = new Complexity();
					c2.setN_Power(0);
					c2.setLog_Power(1);
					c1.setBlockComplexity(c2);
					c1.setHighestSubComplexity(c2);
					s1.push(c1);
				}
			}
			if(data.contains("if "))
			{
				if(!data.trim().startsWith("#") && !data.trim().startsWith("print"))
				{
					CodeBlock c1 = new CodeBlock();
					Complexity c2 = new Complexity();
					c2.setN_Power(0);
					c2.setLog_Power(0);
					c1.setBlockComplexity(c2);
					try
					{
						if(oldTopComplexity.getLog_Power() < oldTop.getHighestSubComplexity().getLog_Power())
						{	
							c1.setHighestSubComplexity(c2);
						}
						else
						{
							try
							{
								CodeBlock ctest = (CodeBlock) s1.peek();
								c1.setHighestSubComplexity(ctest.getHighestSubComplexity());
							}
							catch(ClassCastException e)
							{
							
							}
						}
					}
					catch(NullPointerException e)
					{
						
					}
					s1.push(c1);
				}
			}
			if(data.contains("elif "))
			{
				CodeBlock c1 = new CodeBlock();
				Complexity c2 = new Complexity();
				c2.setN_Power(1);
				c2.setLog_Power(1);
				c1.setBlockComplexity(c2);
			}
			if(data.contains("else "))
			{
				if(!data.trim().startsWith("#") && !data.trim().startsWith("print"))
				{
					CodeBlock c1 = new CodeBlock();
					Complexity c2 = new Complexity();
					c2.setN_Power(1);
					c2.setLog_Power(0);
					c1.setBlockComplexity(c2);
					c1.setHighestSubComplexity(c2);
					s1.push(c1);
				}
			}
			
			else
			{
				//System.out.print("This line is being ignored.");
			}
		}
		try
		{
			Complexity test = new Complexity();
			test = (Complexity) s1.pop();
			if(oldTopComplexity.getLog_Power() > test.getLog_Power())
			{
				test.setLog_Power(oldTopComplexity.getLog_Power());
			}			
			if(oldTopComplexity.getN_Power() > test.getN_Power())
			{
				test.setN_Power(oldTopComplexity.getN_Power());
			}
			return test;
		}
		catch(NullPointerException e)
		{
			System.out.println("Trace complete.\n");
			throw new NullPointerException(("The overall complexity of " + filename + " is " + backup + "\n"));
		}
		catch(EmptyStackException e)
		{
			throw new EmptyStackException();
		}
		catch(ClassCastException e)
		{
			throw new ClassCastException("");
		}
	}
	
	public static void main(String[] args) throws IllegalArgumentException, FileNotFoundException, ClassCastException
	{
		Scanner input = new Scanner(System.in);
		String file = "";
		Complexity c1 = new Complexity();
		boolean flag = false;
		
		while(flag == false)
		{
			System.out.println("Please enter a file name (or 'quit' to quit): ");			
			file = input.next();
			if(!(file.toLowerCase().equals("quit")))
			{
				try
				{
				c1 = traceFile(file);
				System.out.println("Trace complete.\n");
				System.out.println("The overall complexity of " + file + " is " + c1.toString() + "\n");
				}
				catch(FileNotFoundException e)
				{
					System.out.print("");
				}
				catch(EmptyStackException e)
				{
					System.out.println("Stack is empty. Could not pop value.");
				}
				catch(ClassCastException e)
				{
					System.out.println("Trace complete.\n");
					System.out.println("The overall complexity of " + file + " is " + c1.toString() + "\n");
				}
				catch(NullPointerException e)
				{
					System.out.println(e.getMessage());
				}
			}
			else
			{
				flag = true;
				break;	
			}			
		}		
		System.out.println("Program terminating successfully...");
	}
	
}
