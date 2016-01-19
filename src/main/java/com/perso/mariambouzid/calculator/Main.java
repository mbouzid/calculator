package com.perso.mariambouzid.calculator;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import java.util.Stack; /** used for the class Calculator **/

public class Main extends ActionBarActivity 
{
    
    public class Calculator
    /**
     *  This class is dedicated to calculate the user's inputed operations.
     *  @class @public
     * */
    {
        /**
         *  This is a description of the members of the class Calculator.
         *  @member @private {Stack<String>} m_operators - the operators' input.
         *  @member @private {Stack<Integer>} m_figures - the figures' input.
         * */
         private Stack<String> m_operators ;
         private Stack<Integer> m_figures ;
        
        public Calculator()
        /**
         *  Public constructor. 
         *  It creates two empty stacks : one for the figures and the other for the operands.
         *  @constructor @public 
         * */
        {
            m_operators= new Stack<String>();
            m_figures= new Stack<Integer>();
        }
        
        public boolean isOperator(String str) 
        /**
         *  Return true if the string is an operator (among + and -) , else return false.
         *  @method @public
         *  @param {String} str - a string.
         *  @return {boolean} 
         * */
        {
            return ((str.equals("+"))||(str.equals("-")));
        }
        
        public int calculate( String expression )
        /**
         *  Return the result of the calculation of the expression. 
         *  It parses the expression and pushes the different tokens in their respective stacks.
         *  @method @public
         *  @param {String} expression - an arithmetic expression.
         *  @return {int} - the result.
         * */
        {
            int result = 0 , i =0;
            String [] figures = expression.split("([^0-9])");
           
            String [] operators = expression.split("([0-9])");
            
            int n = figures.length;
            int m = operators.length;
            
            for (int j =0 ; j<n ; ++j)
            {
            
            	m_figures.push(Integer.parseInt(figures[j]));
            }
            for (int j =0 ; j<m ; ++j)
            {
       
            	m_operators.push(operators[j]);
            }
            
            while ( i!= n)
            {
            	if (!m_operators.isEmpty())
            	{
            		String operator = m_operators.pop();
            		if (operator.equals("+"))
            		{
            			m_figures.push(m_figures.pop()+m_figures.pop());
            		}
            		else if (operator.equals("-"))
            		{
            			int a = m_figures.pop();
            			int b = m_figures.pop();
            			m_figures.push(b-a);
            		}
            	}
            	i++;
            }
         
            result = m_figures.pop();
            return result ;
        }
    }
    
    /**
     * @member @private {TextView} m_output - the display output 
     * */
    private TextView m_output ;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debut);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) 
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_debut, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) 
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) 
        {
            /*TextView t = (TextView)findViewById(R.id.m_display_output);
            t.setText(id.getText());*/
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
