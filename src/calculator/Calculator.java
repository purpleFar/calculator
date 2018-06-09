package calculator;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends Frame{

	Calculator(){
		super();
		CalculatorUI ui = new CalculatorUI();
		setLayout(new BorderLayout());
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(600, 800);
		setLocation((d.width-getWidth())/2, (d.height-getHeight())/2);
		setTitle("CBB105028's calculator!!");
		add(ui.showLabel,BorderLayout.NORTH);
		add(ui,BorderLayout.CENTER);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) 
			{System.exit(0);}
		});
		setVisible(true);
	}
}