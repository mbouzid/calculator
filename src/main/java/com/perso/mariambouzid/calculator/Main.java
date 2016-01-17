package com.perso.mariambouzid.calculator;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import java.util.Stack; /** used for the class Calculator **/

public class Debut extends ActionBarActivity 
{
    
    public class Calculator
    /**
     *  This class is dedicated to calculate the user's inputed operations.
     *  @class @public
     * */
    {
        /**
         *  This is a description of the members of the class Calculator.
         *  @member @private {Stack<String>} m_operators - the operators input.
         *  @member @private {Stack<int>} m_figures - the figures input.
         * */
         private Stack<String> m_operators ;
         private Stack<int> m_figures ;
        
        public Calculator()
        /**
         *  Public constructor. 
         *  It creates two empty stacks : one for the figures and the other for the operands.
         *  @constructor @public 
         * */
        {
            m_operators= new Stack<String>();
            m_figures= new Stack<int>();
        }
        
        public boolean isOperator(char c) 
        /**
         *  Return true if the char is an operator (among + and -) , else return false.
         *  @method @public @readonly
         *  @param {char} c - a character.
         *  @return {boolean} 
         * */
        {
            return ((c=='+')||(c=='-'));
        }
        
        public int calculate( String expression )
        /**
         *  Return the result of the calculation of the expression. 
         *  It parses the expression and pushes the differents tokens in their respective stacks.
         *  @method @public
         *  @param {String} expression - an arithmetic expression.
         *  @return {int} - the result.
         * */
        {
            int result = 0 ;
            
        /**
         * I'll continue this part later.. 
         * */
           while ( expression != null )
           {
               if (!isOperator(expression[i]))
               {
                  m_figures.push((expression[i]).getNumericValue());
            
               }
               else
               {
                   m_operators.push(expression[i]);
               }
               i++;
           }
            
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
