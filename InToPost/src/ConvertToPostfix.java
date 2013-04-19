import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ConvertToPostfix{
   
    static StackOperand operandTop = null;
	static StackOperator operatorTop = null;
	static int x;
	static int y;
	static char c;
	
	public static void convertToPostFix(String infixFileName) throws IOException{
        
        String line = null;
		BufferedWriter bufferWriter = null;
		ConvertToPostfix obj = new ConvertToPostfix();
	    BufferedReader bufferReader = new BufferedReader( new FileReader( new File( "C:/Users/AMEYA/Labs/InToPost/src/"+infixFileName+"" ) ) );
	    bufferWriter = new BufferedWriter(new FileWriter("C:/Users/AMEYA/Labs/InToPost/src/Output.txt"));
	    bufferWriter.write("");
	    bufferWriter.close();
	    while( (line = bufferReader.readLine()) != null )
	    {
	    	
	        for(int i=0;i<line.length();i++)
	        {
	        	if(line.charAt(i) >= '0' && line.charAt(i) <= '9')
	        	{
	        		String operand = Character.toString(line.charAt(i));
	        		{
	        			StackOperand stackoperand = new StackOperand(Integer.parseInt(operand));
	        			stackoperand.link = operandTop;
	        			operandTop = stackoperand;
	        		}
	        		
             	}
	        	else if(line.charAt(i) == '+' || line.charAt(i) == '-' || line.charAt(i) == '*' || line.charAt(i) == '/')
	        	{
	        		StackOperator stackoperator = new StackOperator(line.charAt(i));
	        		stackoperator.link = operatorTop;
	        		operatorTop = stackoperator;
             	}
	        }
	        int result = evaluate(line);
	        bufferWriter = new BufferedWriter(new FileWriter("C:/Users/AMEYA/Labs/InToPost/src/Output.txt",true));
	        bufferWriter.write("Input expression --- Postfix expression --– evaluation");
		    bufferWriter.newLine();
	        bufferWriter.write(line);
		    bufferWriter.write("------");
	        bufferWriter.write(Integer.toString(obj.popOperand()));
	        bufferWriter.write(Integer.toString(obj.popOperand()));
	        bufferWriter.write(obj.popOperator());
	        bufferWriter.write("------");
	        bufferWriter.write(Integer.toString(result));
	        bufferWriter.newLine();
	        bufferWriter.flush();
	        bufferWriter.close();
	    }
	    
	    
        
	}
	
	int popOperand()
	{
		if(operandTop == null)
		{
			System.out.println("List empty");
		}
		else
		{
			int ret;
			ret = operandTop.operand;
			operandTop = operandTop.link;	
			return ret;
		}
		return 0;
	}
	
	char popOperator()
	{
		if(operatorTop != null)
		{
			char operator = operatorTop.operator;
			operatorTop = operatorTop.link;
			return operator;
		}
		return 'n';
	}
    
	public static int evaluate(String postfix)
	{
		int operandArray[] = new int[5];
		int index = 0;
		char c = 0;
		for(int i=0;i<postfix.length();i++)
		{
			if(postfix.charAt(i) >= '0' && postfix.charAt(i) <= '9')
			{
				operandArray[index] = Integer.parseInt(Character.toString(postfix.charAt(i)));
				index++;
			}
			else if(postfix.charAt(i) == '+' || postfix.charAt(i) == '-' || postfix.charAt(i) == '*' || postfix.charAt(i) == '/')
			{
				c = postfix.charAt(i);
			}
		}
		
		switch (c)
		{
			case '+' : return(operandArray[0]+operandArray[1]);
			case '-' : return(operandArray[0]-operandArray[1]);
			case '*' : return(operandArray[0]*operandArray[1]);
			case '/' : return(operandArray[0]/operandArray[1]);
		}
		return 0;        		
	}
    
	public static void main(String[] args) throws IOException {
        
        convertToPostFix("Input.txt");
        
        
	}

}