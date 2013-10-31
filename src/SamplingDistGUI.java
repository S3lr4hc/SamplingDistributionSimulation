import javax.swing.JPanel;
import java.awt.Color;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;


@SuppressWarnings("serial")
public class SamplingDistGUI extends JPanel {
	
	private JButton computeButton;
	private JTextField NtextField;
	private JTextField UppertextField;
	private JTextField LowertextField;
	
	//Returns N from Input
	public int getNInput() {
		return Integer.parseInt(NtextField.getText());
	}

	//Returns Upper Bound from input
	public double getUpperBound() {
		return Integer.parseInt(UppertextField.getText());
	}	
	
	//Returns Upper Bound from input
	public double getLowerBound() {
		return Integer.parseInt(LowertextField.getText());
	}	

	//Initializes Panel with components
	public SamplingDistGUI() 
	{
		setBackground(Color.WHITE);
		this.setSize(640 , 400);
		setLayout(null);
		
		JLabel lblTitle = new JLabel("Sampling Distribution Simulation\r\n");
		lblTitle.setFont(new Font("Arial", Font.BOLD, 26));
		lblTitle.setBounds(25, -2, 557, 92);
		add(lblTitle);
		
		JLabel lblLowerBound = new JLabel("Lower Bound:");
		lblLowerBound.setFont(new Font("Tekton Pro Cond", Font.BOLD, 25));
		lblLowerBound.setBounds(25, 78, 171, 32);
		add(lblLowerBound);
		
		JLabel lblUpperBound = new JLabel("Upper Bound:");
		lblUpperBound.setFont(new Font("Dialog", Font.BOLD, 25));
		lblUpperBound.setBounds(25, 146, 171, 32);
		add(lblUpperBound);
		
		JLabel lblPopN = new JLabel("Population N:");
		lblPopN.setFont(new Font("Tekton Pro Cond", Font.BOLD, 25));
		lblPopN.setBounds(25, 219, 171, 32);
		add(lblPopN);
		
		computeButton = new JButton("Generate Graph");
		computeButton.setFont(new Font("Letter Gothic Std", Font.BOLD, 19));
		computeButton.setBounds(114, 330, 436, 32);
		add(computeButton);
		
		LowertextField = new JTextField();
		LowertextField.setFont(new Font("Dialog", Font.PLAIN, 18));
		LowertextField.setColumns(10);
		LowertextField.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		LowertextField.setBounds(218, 65, 67, 45);
		add(LowertextField);
		
		UppertextField = new JTextField();
		UppertextField.setFont(new Font("Dialog", Font.PLAIN, 18));
		UppertextField.setColumns(10);
		UppertextField.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		UppertextField.setBounds(218, 143, 67, 45);
		add(UppertextField);
		
		NtextField = new JTextField();
		NtextField.setFont(new Font("Letter Gothic Std", Font.PLAIN, 18));
		NtextField.setColumns(10);
		NtextField.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		NtextField.setBounds(218, 216, 67, 45);
		add(NtextField);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Uniform", "Skewed", "Bimodal", "Normal", "Random "}));
		comboBox.setBounds(395, 90, 158, 20);
		add(comboBox);

	}
	//Just for the action listener of the button to compute
	public void addListener(ActionListener calculationListener){
		computeButton.addActionListener(calculationListener);
	}
	
	//Display the error message
	public void displayError(String message){
		JOptionPane.showMessageDialog(this, message);
	}
	
	//Clear the fields
	public void clearAll(){
		UppertextField.setText("");
		LowertextField.setText("");
		NtextField.setText("");
	}
}
