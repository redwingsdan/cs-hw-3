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
 * This class creates a new object of type CodeBlock. This object contains two objects of type Complexity:
 * blockComplexity, which is the complexity of a particular block of code within the file, and highestSubComplexity,
 * which is the highest level of complexity within a nested block of code. This class also has two String variables:
 * name, which tracks the name of the current block of code being traced, and loopVariable, which tracks the variable
 * in a 'while' loop which is being altered. There is also a constant array which contains the keywords from the program
 * which will affect the complexity. 
 */
public class CodeBlock {

	public static final String[] BLOCK_TYPES = {"def", "for", "while", "if", "elif", "else"};
	public static final int DEF = 0;
	public static final int FOR = 1;
	public static final int WHILE = 2;
	public static final int IF = 3;
	public static final int ELIF = 4;
	public static final int ELSE = 5;
	private Complexity blockComplexity;
	private Complexity highestSubComplexity;
	String name;
	String loopVariable;	
	
	/**
	 * Creates an instance of <code>CodeBlock</code> - Constructor
	 * @param blockComplexity = The complexity of a particular block of code - Complexity
	 * @param highestSubComplexity = The largest complexity value in a nested block of code - Complexity
	 * @param name = The name of the current block of code being traced - String
	 * @param loopVariable = The variable which is being updated within a while loop - String
	 */
	public CodeBlock()
	{
		blockComplexity = null;
		highestSubComplexity = null;
		name = null;
		loopVariable = null;
	}
	
	/**
	 * Sets the value of <code>blockComplexity</code> to the Complexity value passed in.
	 * @param blockComplexity = The complexity of a particular block of code - Complexity
	 */
	public void setBlockComplexity(Complexity blockComplexity)
	{
		this.blockComplexity = blockComplexity;
	}
	
	/**
	 * Sets the value of <code>blockComplexity</code> to the Complexity value passed in.
	 * @param highestSubComplexity = The largest complexity value in a nested block of code - Complexity
	 */
	public void setHighestSubComplexity(Complexity highestSubComplexity)
	{
		this.highestSubComplexity = highestSubComplexity;
	}
	
	/**
	 * Sets the value of <code>name</code> to the String value passed in.
	 * @param name = The name of the current block of code being traced - String
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	
	/**
	 * Sets the value of <code>loopVariable</code> to the String value passed in.
	 * @param loopVariable = The variable which is being updated within a while loop - String
	 */
	public void setLoopVariable(String loopVariable)
	{
		this.loopVariable = loopVariable;
	}
	
	/**
	 * Returns the value of <code>blockComplexity</code> - Complexity
	 * @return blockComplexity - Complexity
	 */
	public Complexity getBlockComplexity()
	{
		return blockComplexity;
	}
	
	/**
	 * Returns the value of <code>highestSubComplexity</code> - Complexity
	 * @return highestSubComplexity - Complexity
	 */
	public Complexity getHighestSubComplexity()
	{
		return highestSubComplexity;
	}
	
	/**
	 * Returns the value of <code>name</code> - String
	 * @return name - String
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Returns the value of <code>loopVariable</code> - String
	 * @return loopVariable - String
	 */
	public String getLoopVariable()
	{
		return loopVariable;
	}
	
}
