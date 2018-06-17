package calculator;

import java.util.Random;
import java.awt.*;
import java.awt.event.*;

public class CalculatorUI extends Panel{
	Label showLabel;
	TextArea tf;
	Button clearButton;
	Button[] numButton;
	String[] buttonStr = 
		{"e","(",")","AC","Del",
		 "π","7","8","9","÷",
		 "^","4","5","6","×",
		 "±","1","2","3","-",
		 " ","0",".","=","+"};
	CalculatorUI(){
		super();
		GridLayout g = new GridLayout(5,5);
		g.setVgap(20);	g.setHgap(20);
		setLayout(g);
		showLabel = new Label("0",Label.RIGHT);
		showLabel.setFont(new Font("標楷體", Font.BOLD, 40));
		showLabel.setBackground(Color.decode("0xffffff"));
		tf = new TextArea("",20,20,TextArea.SCROLLBARS_BOTH);
		tf.setFont(new Font("標楷體", Font.PLAIN, 30));
		tf.setBackground(Color.decode("0xfff5cb"));
		tf.setFocusable(false);
		tf.setEditable(false);
		numButton = new Button[buttonStr.length];
		for(int i=0;i<buttonStr.length;i++) {
			numButton[i] = new Button(buttonStr[i]);
			numButton[i].setFont(new Font("標楷體", Font.PLAIN, 50));
			numButton[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {CalculatorUI.this.showNum(e);}});
			add(numButton[i]);
		}
		
		clearButton = new Button("Clear");
		clearButton.setFont(new Font("標楷體", Font.PLAIN, 30));
		clearButton.setBackground(Color.gray);
		clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {CalculatorUI.this.tf.setText("");}});
		
		tf.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(tf.getText().length()==0)	return;
				int index = tf.getCaretPosition();
				String s = tf.getText();
				String line = s.substring(s.substring(0,index).lastIndexOf('\n')+1,s.indexOf('\n', index));
		        if(line.indexOf('~')!=-1)	return;
		        else if(line.indexOf('=')!=-1)	showLabel.setText(line.substring(2));
		        else showLabel.setText(line);
		    }
		});
		setColor(1);
	}
	void showNum(ActionEvent e) {
		Button b = (Button)(e.getSource());
    	String s = b.getLabel(),
    		   t = showLabel.getText(),
    		   temp;
    	if(s.equals("=")) {
    		t = MyMath.check(t);
    		temp = MyMath.count(t);
    		if(!t.equals(temp)) {
    			tf.setText(tf.getText()+t+"\n= "+temp+"\n~~~~~~~~~~~~~~~\n");
    			tf.setCaretPosition(tf.getText().length());
    		}
    	}
    	else if(s.equals(" ")) {
    		setColor(0);
    		return;
    	}
    	else if(s.equals("±")) {
    		t = MyMath.check(t);
    		temp = MyMath.sign(MyMath.count(t));
    		if(!t.equals(temp)) {
    			tf.setText(tf.getText()+"-("+t+")\n= "+temp+"\n~~~~~~~~~~~~~~~\n");
    			tf.setCaretPosition(tf.getText().length());
    		}
    	}
    	else if(s.equals("Del")) {
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
		String[] buttonColor = {"0x1e90ff","0xed9121","0xff0000","0xf0e68c","0xffffff","0x808069"};
		Random ran = new Random();
		if(mode!=1) {
			for(int i=0;i<6;i++) {
				String c="0x";
				for(int x=6;x!=0;x--) c+=Integer.toHexString(ran.nextInt(16));
				buttonColor[i]=c;
			}
		}
		for(Button b: numButton) {
			int i;
			char c = b.getLabel().charAt(0);
			if((c>='0'&&c<='9')||c=='.') i=0;
			else if(c=='=') i=1;
			else if(c=='A'||c=='D') i=2;
			else if(c=='+'||c=='-'||c=='÷'||c=='×') i=3;
			else if(c==' ') i=4;
			else i=5;
			b.setBackground(Color.decode(buttonColor[i]));
		}
		
	}
}