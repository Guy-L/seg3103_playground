import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
class CalCFrame extends JFrame implements ActionListener
{
  
  private JButton[]   buttons = new JButton[19];   
  private String[]    buttonText = { " 1 ", " 2 ", " 3 ", " + ", " - ",
                                     " 4 ", " 5 ", " 6 ", " x ", " / ",
                                     " 7 ", " 8 ", " 9 ", "^ ", "sqrt",
                                     " C ", " 0 ", " . ", "    =    "};
                         
  private JTextField   result;       // Calculator display screen
  private String      input = "";   // stores user input
  private JLabel       label;        
  private Color       forecolor,    // Calculator foreground color
                      backcolor,    // Calculator background color
                      fieldcolor;   // the display screen's color
  private Font        font, 
                      buttonfont;
  private int         oper = 0,     // stores the integer constants representing
                      oldoper = 0,  // the operators
                      newoper = 0;
  private double      answer,      
                      num1 = 0.0, 
                      num2 = 0.0, 
                      num3 = 0.0;
  private static final int ADD=1,        // integer constants representing operators
                           SUB = 2,      // fixing bugs #P-5 to #P-10
                           MULT = 3, 
                           DIVI = 4, 
                           POW = 5, 
                           SQRT = 6;
  private boolean     firstpress = true,  //determines first button press
                      morenums = false,   //"" if more numbers are being pressed
                      equals = false,     //"" if equal button has been pressed
                      clearscreen = false, //clears screen
                      decnumber = false,  //"" if a user entered a float
                      doubleclick = false; //"" if mouse was doubleclicked
 
 
 private static class CustomWindowAdapter extends WindowAdapter {
 	public void windowClosing(WindowEvent e) {
        System.exit(0);
    }
 }

public CalCFrame(String title) {
     
    super(title);
	setVisible(true); //fixing bug #BP-2 (moved from Main.java)
    addWindowListener(new CustomWindowAdapter()); //fixing bug #P-1 

    buttonfont = new Font( "Courier", Font.PLAIN, 12 );
    setBackground( Color.lightGray );
    
    //initialize colors
    
    result = new JTextField( 25 );
    getContentPane().setLayout( new FlowLayout() );        
       
    getContentPane().add( result );
    
  //initialize and add buttons  
  for ( int i = 0; i < 19; i++ ) {
    buttons[i] = new JButton( buttonText[i] );   
    buttons[i].setFont( buttonfont );
    buttons[i].addActionListener( this );
    
    getContentPane().add( buttons[i] ); //fixing bug #DC-1 & #DC-3 to #DC-5
       
	if (i == 2 || i == 7 || i == 12 || i == 17)   
		getContentPane().add(new JLabel("  "));
    }     
   buttons[15].setForeground( Color.red ); 
   result.setBackground( Color.white );          
}

//==============================================================================
// Interface method that determines which button was pressed then determines
// the appropriate action.
//==============================================================================
public void actionPerformed( ActionEvent e )
{ 
   
  // "if" block is not entered if the button is an operator
  if (    e.getSource() != buttons[3] && e.getSource() != buttons[4] 
       && e.getSource() != buttons[8] && e.getSource() != buttons[9]
       && e.getSource() != buttons[13] && e.getSource() != buttons[14]
       && e.getSource() != buttons[15] && e.getSource() != buttons[18] ) {
     
    if ( clearscreen ) {       // clears screen if user enters number before an
        clearScreen();         // operator after pressing equals
        clearscreen = false;
      }
             
     if ( e.getSource() == buttons[0] ) { 
        input += "1";                     // concoctenate "1" to input
        result.setText( input );
        showAnswer( input );       
      } // end else if
      
      else if ( e.getSource() == buttons[1] ) {   
        input += "2";                     // concoctenate "2" to input
        showAnswer( input ); 
      } // end else if
      
      else if ( e.getSource() == buttons[2] ) {      
        input += "3";                     // concoctenate "3" to input
        showAnswer( input ); 
      }  // end else if
      
      else if ( e.getSource() == buttons[5] ) {      
        input += "4";                     // concoctenate "4" to input
       showAnswer( input ); 
      } // end else if
      
      else if ( e.getSource() == buttons[6] ) {   
        input += "5";                      // concoctenate "5" to input
        showAnswer( input ); 
      }  // end if
      
      else if ( e.getSource() == buttons[7] ) {     
        input += "6";                      // concoctenate "6" to input
        showAnswer( input ); 
      }  // end else if
      
      else if ( e.getSource() == buttons[10] ) {   
        input += "7";                      // concoctenate "7" to input
        showAnswer( input ); 
      }  // end else if
      
      else if ( e.getSource() == buttons[11] ) {    
        input += "8";                      // concoctenate "8" to input
        showAnswer( input ); 
      }  // end else if
      
      else if ( e.getSource() == buttons[12] ) {    
        input += "9";                      // concoctenate "9" to input
        showAnswer( input ); 
      }  // end else if
      
      else if ( e.getSource() == buttons[16] ) {
        input += "0";                      // concoctenate "0" to input
        showAnswer( input ); 
      }  // end else if 
     else if ( e.getSource() == buttons[17] ) {
       if ( decnumber == false ) {
	   decnumber = true;    
	   input += ".0";                         // concoctenate "." to input
	   showAnswer( input );
       }
     }
  }  // end if
  
  // check if user entered the addition operator
  if ( e.getSource() == buttons[3] ) {
        clearscreen = false;
        decnumber = false;
        oper = ADD;                   // oper is set to addition
        clickCheck( input );         // checks if user doubleclicked
      if ( doubleclick == false )
        processNumbers();            // if no double click continue to process
        input = "";                  // clear variable to store new input 
      }  // end  if
  
  // check if user entered the subtraction operator    
  else if (e.getSource() == buttons[4] ) {
      clearscreen = false;
      decnumber = false;
      oper = SUB;                    // oper is set to subtraction
      clickCheck( input );           // check if user doubleclicked
      if ( doubleclick == false ) 
        processNumbers();            // if no double click continue to process
        input = "";                  // clear variable to store new input  
      } // end else if
  
  // check if user entered the multiplication operator     
  else if (e.getSource() == buttons[8] ) {
      clearscreen = false;
      decnumber = false;
      oper = MULT;                   // oper is set to multiplication
      clickCheck( input );           // check if user doubleclicked
      if ( doubleclick == false )
        processNumbers();            // if no double click continue to process
        input = "";                  // clear variable to store new input
      } //end else if 
  
  // check if user entered the divide operator    
  else if (e.getSource() == buttons[9] ) {
      clearscreen = false;
      decnumber = false;
      oper = DIVI;                   // oper is set to divide
      clickCheck( input );           // check if user doubleclicked
      if ( doubleclick == false )
        processNumbers();            // if no double click continue to process
        input = "";                  // clear variable to store new input
      }  // end else if
  
  // check if user entered the exponential operator      
  else if ( e.getSource() == buttons[13] ) {
      clearscreen = false;
      decnumber = false;
      oper = POW;                    // oper is set to exponential
      clickCheck( input );           // check if user doubleclicked
      if ( doubleclick == false )
        processNumbers();            // if no double click continue to process
        input = "";                  // clear variable to store new input
      }  // end else if
  
  // check if user entered the square root operator    
  else if ( e.getSource() == buttons[14] ) {
      clearscreen = false;
      oper = SQRT;                    // oper is set to square root
      clickCheck( input );            // check if user doubleclicked
      if ( doubleclick == false )
        processNumbers();             // if no double click continue to process
        input = "";                   // clear variable to store new input
      }  // end else if
  
  // check if user entered the clear operator      
  if (e.getSource() == buttons[15] ) {
       clearScreen();
      }  // end if
  
  // check if user entered the equal operator    
  if (e.getSource() == buttons[18] ) {
       equals = true; 
       clearscreen = true;
       clickCheck( input );             //check if user double-clicked
     if ( doubleclick == false )
        processNumbers();                   //continue to process numbers if                                        
        input = Double.toString( answer );  //if no double-click
    } // end if
  
   }  // end actionPerformed()

//==============================================================================
//Method processNumbers is where processes the numbers inputed by the user
//==============================================================================  
public void processNumbers() {
  
  // the program enters this "if" block when an operator is pressed for the
  // first time
  if ( firstpress ) {
      
    if ( equals ) {
      num1 = answer;    //answer is stored in num1 if user enters equal operator
      equals = false;   // equals is set to false to allow additional input    
  } // end if    
    else 
      num1 = Double.parseDouble(input); // converts a string number to double; fixing bug #P-3
     
      oldoper =  oper;                  // store current operator to oldoper
      
    // if operator is square root, calculation and output is done immediately  
    if ( oper == SQRT ) { 
      answer = calculate( oldoper, num1, 0.0 );  
      showAnswer( Double.toString( answer ) );
      morenums = true;             
    }                             
      firstpress = false;          // no longer the first operator
}  // end if
    
    // "if" block is entered if now more than two numbers are being entered to
    // be calculated
    else if ( !morenums ) {      
     
      num2 = Double.parseDouble(input);			 //converts second num to double; fixing bug #P-4.3
      answer = calculate( oldoper, num1, num2 ); //calculate num1 and num2 with   
      showAnswer( Double.toString( answer) );   //the past operator
      newoper = oper;                            //store current operator to
                                                 //new oper
      if ( !equals )
        morenums = true;        //tells program that more than two numbers have             
      else {                    //entered
        morenums = false;       //if equal operator is pressed, firstpress
        firstpress = true;      //returns to true
    } // end else
    } // end if
  
    // if more than two numbers are being inputted to calculate, this "if" block
    // is accessed
    else { //fixing bug #DC-6
      
      if ( equals ) {
       
        newoper = oper;
        morenums = false;
        firstpress = true;  // if equals is pressed set firstpress to false
    } // end if             
    
      num3 = Double.parseDouble(input); //fixing bug #P-4.7
      answer = calculate( newoper, answer, num3 );      
      showAnswer( Double.toString(answer) );
      
      newoper = oper;             
   }  // end else if  
}  // end processNumbers()  

//==============================================================================
//Method calculate determines which operator was entered and calculates
//two numbers depending on the operator pressed
//==============================================================================
public double calculate( int oper, double number1, double number2 )
{    
   double answer = 0.0;
  
        switch( oper ) {
          case ADD:            
            answer = number1 + number2; 
            break;
          case SUB:            
            answer = number1 - number2;
            break;
          case MULT:
            answer = number1 * number2;
            break;
          case DIVI:
            answer = number1 / number2;
            break;
          case POW:
            answer = Math.pow( number1, number2 );
            break;
          case SQRT:
            answer = Math.sqrt( number1 );
            break; 
          default: //should never happen; fixing bug #DC-2
			clearScreen();
			System.out.println("Calculate called without operator -> investigate!");
			break;
      } // end switch  
      
     return answer;     
  }  // end calculate()

//==============================================================================
//Method showAnswer outputs the results in the calculators displays screen
//==============================================================================
public void showAnswer( String s )
{
    double answer;
    
    answer = Double.parseDouble(s); //fixing bug #P-4
    if ( decnumber )    
    result.setText( Double.toString(answer) );
    else
    result.setText( s );        //all output are displayed as integers at start
        
} // end showAnswer

//==============================================================================
//Method clickCheck determines if the user double clicked and returns a boolean
//value.  If doubleclick is true, the program ignores the input
//==============================================================================
public boolean clickCheck( String s ) {
  if (s.isEmpty()) //fixing bug #BP-1
    doubleclick = true;
  else 
    doubleclick = false;
    
  return doubleclick;
}

//==============================================================================
//Method clearScreen clears calculator display screen and sets variables to
//default.
//==============================================================================
public void clearScreen()
{
        oper = 0;                    // reinitialize variables to default
        input = "";
        answer = 0;        
        decnumber = false;
        morenums = false;
        firstpress = true;
        equals = false;
        showAnswer( Integer.toString( (int)answer) ); 
}

}