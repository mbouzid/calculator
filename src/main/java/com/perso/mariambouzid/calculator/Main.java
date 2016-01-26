package com.univangers.l3info.mbouzid.calculator;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.view.View;
import java.util.Stack;
import android.widget.Button;
import android.util.Log;

public class MainActivity extends ActionBarActivity
/**
 *  This class is the main activity.
 *  @class
 *  @author Mariam Bouzid
 *
 * */
{

    public class Calculator
    /**
    *  This class is dedicated to calculate the user's inputed operations.
    *  @class
    * */
    {

        public class CalculatorException extends Exception
        /**
         * This class manages exception related to the calculator's input.
         *  @class
         *  */
        {
            private String m_message_error ;

            public CalculatorException(String message_error)
            /**
             *  Public constructor.
             *  @constructor public
             *  @param {String} message_error - brief description of the error.
             */
            {
                m_message_error=message_error;
            }

            public String what()
            /**
             *  Return the error message.
             *  @method public
             *  @return {String}
             */
            {
                return m_message_error;
            }

        }

        private Stack<Integer> m_operators ;
        private Stack<Double> m_figures ;

        public Calculator()
        /**
         *  Public constructor.
         *  It creates two empty stacks : one for the figures and the other for the operators.
         *  @constructor public
         * */
        {
            m_operators= new Stack<Integer>();
            m_figures= new Stack<Double>();
        }

        public boolean isOperator(char c)
        /**
         *  Return true if the character is an operator (among + , - and =) , else return false.
         *  @method public
         *  @param {char} c - a character.
         *  @return {boolean}
         * */
        {
            return ((c=='+')||(c=='-')||(c=='='));
        }



        public double calculate( String expression ) throws CalculatorException
        /**
         *  Return the result of the calculation of the expression.
         *  It parses the expression and pushes the  tokens in their respective stacks.
         *  @method public
         *  @param {String} expression - an arithmetic expression.
         *  @return {double} - the result.
         *  @throws {CalculatorException} - an exception is thrown if the input is incorrect.
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
                    if (token.equals(""))
                    {
                        throw new CalculatorException("ERR");
                    }
                    else {
                        Double t = Double.parseDouble(token);

                        m_figures.push(t);

                        token = "";                                     //   reset token

                        if (!m_operators.isEmpty()) {

                            double a = m_figures.pop();
                            double b = m_figures.pop();
                            Integer c = m_operators.pop();

                            switch (c) {
                                case (int) '+': {
                                    m_figures.push(a + b);
                                    break;
                                }

                                case (int) '-': {
                                    m_figures.push(b - a);
                                    break;
                                }

                            }

                        }

                        m_operators.push((int) expression.charAt(i));
                    }
                }
                i++;
            }

            result=m_figures.firstElement();
            return result ;
        }

        public String printDebug()
        /**
         *  Print the state of the stacks.
         *  @method public
         *  @return {String}
         */
        {
            return "Stacks Figures : " +m_figures.toString()+"\nStacks Operators : " + m_operators.toString();
        }
    }


    private TextView m_output;

    private Calculator m_calculator;

    private static final String s_tag ="MainActivity";

    private static final String STATE_OUTPUT = "m_output";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    /**
     *  Start activity.
     *  @method protected
     *  @param {Bundle} savedInstanceState
     */
    {
        Log.v(s_tag, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    /**
     *  Save the output.
     *  @method protected
     *  @param {Bundle} outState
     */
    {
        Log.v(s_tag,"onSaveInstanceState");

        super.onSaveInstanceState(outState);

        m_output=(TextView)findViewById(R.id.m_display_output);

        outState.putString(STATE_OUTPUT,m_output.getText().toString());


    }

    @Override
    protected void onRestoreInstanceState (Bundle savedInstanceState)
    /**
     *  Restore the output.
     *  @method protected
     *  @param {Bundle} savedInstanceState
     */
    {
        Log.v(s_tag,"onRestoreInstanceState");

        super.onRestoreInstanceState(savedInstanceState);

        m_output=(TextView)findViewById(R.id.m_display_output);

        m_output.setText(savedInstanceState.getString(STATE_OUTPUT));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    /**
     * Inflate the menu; this adds items to the action bar if it is present
     *  @method public
     *  @param {Menu} menu
     *  @return {boolean}
     */
    {
        Log.v(s_tag, "onCreateOptionsMenu");

        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    /**
     *  Handle action bar item clicks here. The action bar will
     *  automatically handle clicks on the Home/Up button, so long
     *  as you specify a parent activity in AndroidManifest.xml.
     *  @method public
     *  @param {MenuItem} item
     *  @return {boolean}
     */
    {
        Log.v(s_tag,"onOptionsItemSelected");

        int id = item.getItemId();

        switch (id)
        {
            case R.id.action_settings :
            {
                return true;
            }

        }

        return super.onOptionsItemSelected(item);
    }

    public void display(View v)
    /**
     *  Display the input.
     *  @method public
     *  @param {View} v - a view
     */
    {
        Log.v(s_tag,"display");

        Button buttonClicked = (Button)findViewById(v.getId());

        m_output=(TextView)findViewById(R.id.m_display_output);


        m_output.setText(m_output.getText() + buttonClicked.getText().toString());

    }

    public void result(View v) throws Calculator.CalculatorException
    /**
     *  Give the result of the calculation.
     *  @method public
     *  @param {View} v - a view
     *  @throws {Calculator.CalculatorException} - an exception is thrown if the input is incorrect,
     *  it will be shown in the output.
     **/
    {
        Log.v(s_tag,"result");

        m_calculator= new Calculator();
        Log.v(s_tag, m_calculator.printDebug());

        try
        {

            double result = m_calculator.calculate(m_output.getText()+"=");
            m_output.setText(Double.toString(result));

        }
        catch(Calculator.CalculatorException e)
        {
            m_output.setText(e.what());
        }

    }

    public void clear(View v)
    /**
     *  Clear the output.
     *  @method public
     *  @param {View} v - a view
     **/
    {
        Log.v(s_tag,"clear");
        m_output.setText("");
    }

}
