package calculator;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

public class Calculator extends Frame{
	Calculator(){
		super();
		
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		this.setBackground(Color.black);
		setSize(1000, 600);
		setLocation((d.width-getWidth())/2, (d.height-getHeight())/2);
		setTitle("CBB105028's calculator!!");
		setLayout(new BorderLayout());
		CalculatorUI ui = new CalculatorUI();
		
		BorderLayout b = new BorderLayout();
		b.setVgap(20);
		JPanel mainP = new JPanel();
		mainP.setLayout(b);
		mainP.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		mainP.setBackground(Color.black);
		mainP.add(ui.showLabel,BorderLayout.NORTH);
		mainP.add(ui,BorderLayout.CENTER);
		add(mainP,BorderLayout.CENTER);
		
		Panel rightP = new Panel();
		rightP.setLayout(new BorderLayout());
		Label topic= new Label("Record",Label.CENTER);
		topic.setBackground(Color.BLACK);
		topic.setForeground(Color.WHITE);
		topic.setFont(new Font("標楷體", Font.BOLD, 40));
		rightP.add(ui.clearButton, BorderLayout.SOUTH);
		rightP.add(topic,BorderLayout.NORTH);
		rightP.add(ui.tf,BorderLayout.CENTER);
		add(rightP,BorderLayout.EAST);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) 
			{System.exit(0);}
		});
		
		setVisible(true);
	}
}