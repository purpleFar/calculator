package calculator;

import java.util.Random;
import java.awt.*;
import java.awt.event.*;

public class CalculatorUI extends Panel{
	Label showLabel;
	Button[] numButton;
	public static String[] buttonStr = 
		{"e","(",")","AC","←",
		 "π","7","8","9","÷",
		 "^","4","5","6","×",
		 "±","1","2","3","-",
		 " ","0",".","=","+"};
	CalculatorUI(){
		super();
		setLayout(new GridLayout(5,5));
		showLabel = new Label("0",Label.RIGHT);
		showLabel.setFont(new Font("標楷體", Font.BOLD, 40));
		showLabel.setBackground(Color.decode("0xD0D0D0"));
		numButton = new Button[buttonStr.length];
		for(int i=0;i<buttonStr.length;i++) {
			numButton[i] = new Button(buttonStr[i]);
			numButton[i].setFont(new Font("標楷體", Font.BOLD, 64));
			numButton[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {CalculatorUI.this.showNum(e);}});
			add(numButton[i]);
		}
		setColor(1);
	}
	void showNum(ActionEvent e) {
    	String s = e.getSource().toString(),
    		   t = showLabel.getText(),
    		   temp;
    	s = s.substring(s.lastIndexOf('=')+1,s.length()-1);
    	s = s.equals("")?"=":s;
    	if(s.equals("=")) {
    		temp = MyMath.count(t);
    	}
    	else if(s.equals(" ")) {
    		setColor(0);
    		return;
    	}
    	else if(s.equals("±")) {
    		temp = MyMath.sign(MyMath.count(t));
    	}
    	else if(s.equals("←")) {
    		temp = t.substring(0,t.length()-1);
    		temp = (temp.equals("")||temp.equals("Infinit")||temp.equals("-Infinit"))?"0":temp;
    	}
    	else if(s.equals("AC")) {
    		temp = "0";
    	}
    	else {
    		temp = MyMath.mistakeProofing(t+s);
    	}
    	showLabel.setText(temp);
	}
	void setColor(int mode) {
		String[][] buttonColor = {{"0x1e90ff","0xed9121","0xff0000","0xf0e68c","0x000000","0x808069"}};
		Random ran = new Random();
		if(mode!=1) {
			for(int i=0;i<6;i++) {
				int x = 6;
				String c="0x";
				while(x--!=0) c+=Integer.toHexString(ran.nextInt(16));
				buttonColor[0][i]=c;
			}
		}
		for(int i=0;i<buttonStr.length;i++) {
			char c = buttonStr[i].charAt(0);
			if((c>='0'&&c<='9')||c=='.')
				numButton[i].setBackground(Color.decode(buttonColor[0][0]));
			else if(c=='=')
				numButton[i].setBackground(Color.decode(buttonColor[0][1]));
			else if(c=='A'||c=='←')
				numButton[i].setBackground(Color.decode(buttonColor[0][2]));
			else if(c=='+'||c=='-'||c=='×'||c=='÷')
				numButton[i].setBackground(Color.decode(buttonColor[0][3]));
			else if(c==' ')
				numButton[i].setBackground(Color.decode(buttonColor[0][4]));
			else
				numButton[i].setBackground(Color.decode(buttonColor[0][5]));
		}
	}
}