package inlupp2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class PopUp extends JFrame {

	private JButton okButton = new JButton("OK");
	private JButton cancelButton = new JButton("Cancel");
	
	public PopUp(int i){
		super("Platsinfo:");
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		setSize(300, 100);
		
	}
	
	public PopUp(char c){
		super("Koordinat input:");
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		setSize(300, 100);
		
		cancelFunction();
		add(okButton);
		add(cancelButton);

		
		setVisible(true);
		
	}
	
	class CancelListener implements ActionListener{
		public void actionPerformed(ActionEvent ave){
			dispose();
		}
	}
	
	public void cancelFunction(){
		CancelListener canceler = new CancelListener();
		cancelButton.addActionListener(canceler);
	}
	
}
