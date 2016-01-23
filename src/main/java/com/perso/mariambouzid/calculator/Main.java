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
         *  @member @private {Stack<Integer>} m_operators - the operators input.
         *  @member @private {Stack<Double>} m_figures - the figures input.
         * */
         private Stack<Integer> m_operators ;
         private Stack<Double> m_figures ;
        
        public Calculator()
        /**
         *  Public constructor. 
         *  It creates two empty stacks : one for the figures and the other for the operands.
         *  @constructor @public 
         * */
        {
            m_operators= new Stack<Integer>();
            m_figures= new Stack<Double>();
        }
        
        public boolean isOperator(char c) 
        /**
         *  Return true if the character is an operator (among + , - and =) , else return false.
         *  @method @public 
         *  @param {char} c - a character.
         *  @return {boolean} 
         * */
        {
            return ((c=='+')||(c=='-')||(c=='='));
        }
        
        
        
        public double calculate( String expression )
        /**
         *  Return the result of the calculation of the expression. 
         *  It parses the expression and pushes the  tokens in their respective stacks.
         *  @method @public
         *  @param {String} expression - an arithmetic expression.
         *  @return {double} - the result.
         * */
        {
            double result=0;
            int n= expression.length();
            String token ="";
            int i=0;
            while(i<n)
            {
            	if(!isOperator(expression.charAt(i)))
            	{
            		token+=expression.charAt(i);
            	}
            	else
            	{
            		Double t=Double.parseDouble(token);
                    
            		m_figures.push(t);

            		token="";	//reset token
            		
            		if (!m_operators.isEmpty())
            		{
            			
            			double a = m_figures.pop();
            			double b= m_figures.pop();
            			Integer c = m_operators.pop();
            			
            			switch(c)
            			{
            				case (int) '+':
            				{
            					m_figures.push(a+b);
            					break;
            				}
            			
            				case (int) '-':
            				{
            					m_figures.push(b-a);
            					break;
            				}
            				
            			}
            			
            		}
            		
            		
            	
            		m_operators.push((int) expression.charAt(i));
            	}
            	i++;
            }
          
            result=m_figures.pop();
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
           
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    
    public void display(View v)
    /**
     * Display the input 
     * @param {View} v - a view
     * */
     {
         int id = v.getId();
         m_output=(TextView)findViewById(R.id.m_display_output);
         
         switch(id)
         {
            case R.id.m_zero :
                {
                    m_output.setText(m_output.getText()+"0");
                    break;
                }
                
            case R.id.m_one :
                {
                    m_output.setText(m_output.getText()+"1");
                    break;
                }
            
            case R.id.m_two :
                {
                     m_output.setText(m_output.getText()+"2");
                    break;
                }
                
            case R.id.m_three :
                {
                     m_output.setText(m_output.getText()+"3");
                    break;
                }
            
            case R.id.m_four :
                {
                    m_output.setText(m_output.getText()+"4");
                    break;
                }
                
            case R.id.m_five :
                {
                    m_output.setText(m_output.getText()+"5");
                    break;
                }
            
            case R.id.m_six :
                {
                    m_output.setText(m_output.getText()+"6");
                    break;
                }
            
            case R.id.m_seven :
                {
                    m_output.setText(m_output.getText()+"7");
                    break;
                }
            case R.id.m_eight :
                {
                    m_output.setText(m_output.getText()+"8");
                    break;
                }
            case R.id.m_nine:
                {
                    m_output.setText(m_output.getText()+"9");
                    break;
                }
            
            case R.id.m_decimal_point:
                {
                    m_output.setText(m_output.getText()+".");
                    break;
                }
            
            case R.id.m_addition :
                {
                    m_output.setText(m_output.getText()+"+");
                    break;
                }
            
            case R.id.m_substraction :
                {
                    m_output.setText(m_output.getText()+"-");
                    break;
                }
            
         }
     }
    
    public void result(View v)
    /**
     * Display the result on screen
     * @param {View} v - a view
     **/
    {
        int id = v.getId();
       
        if (id==R.id.m_result)
        {
            Calculator c = new Calculator();
            double result = c.calculate(m_output.getText().toString()+"=");
            m_output.setText(result);
        }
    }
    
    public void clear(View v)
    /**
     * Clear the output.
     * @param {View} v - a view
     * */
     {
        int id=v.getId();
        if (id==R.id.m_all_clear)
        {
            m_output.setText("");
        }
     }
}
