import javax.swing.JPanel;
import java.awt.Color;

import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.CategoryDataset;


@SuppressWarnings("serial")
public class SamplingDistGUI extends JPanel {
	
	private JButton computeButton;
	private JTextField NtextField;
	private JTextField UppertextField;
	private JTextField LowertextField;
	private JComboBox comboBox;
	private JFreeChart chart;
	private JPanel pnlChartSimulation;
	private ChartPanel chartPanel;
	private DefaultTableModel tableModel;
	private JTable table;
	
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
		this.setSize(800 , 600);
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
		
		pnlChartSimulation = new JPanel();
		pnlChartSimulation.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Graph and Simulation", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlChartSimulation.setBounds(10, 319, 1262, 330);
		add(pnlChartSimulation);
		pnlChartSimulation.setLayout(null);
		
		//Sliders
		/*NSlider = new JSlider();
		NSlider.setBounds(85, 435, 200, 16);
		pnlChartSimulation.add(NSlider);
		
		PSlider = new JSlider();
		PSlider.setBounds(986, 435, 200, 16);
		pnlChartSimulation.add(PSlider);
		PSlider.setMaximum(100);
		PSlider.setMinimum(0);*/
		
		computeButton = new JButton("Generate Graph");
		computeButton.setFont(new Font("Letter Gothic Std", Font.BOLD, 19));
		computeButton.setBounds(25, 276, 260, 32);
		addListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(Integer.parseInt(UppertextField.getText()) > 0 && Integer.parseInt(LowertextField.getText()) > 0 && Integer.parseInt(NtextField.getText()) > 0) {
			try {
				generateSampleDistribution();
			} catch(NumberFormatException nfe) {
				displayError("Invalid Input Values");
			}
		}
		else displayError("Inputs should be > 0");
	}
		});
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
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Uniform", "Skewed", "Bimodal", "Normal", "Random"}));
		comboBox.setBounds(395, 90, 158, 20);
		add(comboBox);
		
		table = new JTable();
		table.setBounds(311, 122, 427, 122);
		table.setVisible(true);
		add(table);

	}
	//Just for the action listener of the button to compute
	public void addListener(ActionListener calculationListener){
		computeButton.addActionListener(calculationListener);
	}
	
	//Display the error message
	public void displayError(String message){
		JOptionPane.showMessageDialog(this, message);
	}
	private void generateSampleDistribution()
	{
		int N=Integer.parseInt(NtextField.getText());
		int lowerBound=Integer.parseInt(LowertextField.getText());
		int upperBound=Integer.parseInt(UppertextField.getText());
		SamplingComputation calculator=new SamplingComputation();
		//System.out.println(lowerBound+", "+upperBound);
		calculator.setX(lowerBound, upperBound);
		switch(comboBox.getSelectedItem().toString())
		{
		case "Uniform":
			if(N%(upperBound-lowerBound+1)==0)
				calculator.popUniformDist(N);
			else displayError("Uniform distribution not possible for these values");
			break;
		case "Skewed":
			break;
		case "Bimodal":
			break;
		case "Normal":	
			break;
		case "Random":
			calculator.popRandDist(N);
			break;
		default:
			break;
		}
		displayValues(calculator.getValues());
		createChart(calculator.getDataset());
		setTableModel(calculator.getTableModel());
	}
	
	public void setTableModel(DefaultTableModel tm)
	{
		this.tableModel=tm;
		table.setModel(tableModel);
		table.repaint();
		}
	public void displayValues(int[] values)
	{
		//Display values in table/graph
		for(int i :values)
		{
			System.out.println(i);
		}
	}
	//Clear the fields
	public void clearAll(){
		UppertextField.setText("");
		LowertextField.setText("");
		NtextField.setText("");
	}
	public void createChart(CategoryDataset dataset) 
	{
		//creates bar chart
		  chart = ChartFactory.createBarChart(
		  "Population Graph", "X","f(X)" , dataset,
		  PlotOrientation.VERTICAL, false, true, false);
		  
		  //Customization of bar graph
		  chart.getTitle().setFont(new Font("Tahoma",Font.PLAIN,20));
		  
		  final CategoryPlot plot = chart.getCategoryPlot();
	      plot.setBackgroundPaint(Color.white);
	      plot.getDomainAxis().setLabelFont(new Font("Tahoma",Font.PLAIN,15));
	      plot.getRangeAxis().setLabelFont(new Font("Tahoma",Font.PLAIN,15));
	      
	      final BarRenderer renderer = (BarRenderer) plot.getRenderer();
	      
	      renderer.setDrawBarOutline(true);
	      renderer.setShadowVisible(true);
	      renderer.setMaximumBarWidth(0.2);
	      renderer.setBarPainter(new StandardBarPainter());
	      renderer.setSeriesPaint(0, Color.green);
	      
	      if(chartPanel!=null)
	    	  pnlChartSimulation.remove(chartPanel);

		  chartPanel = new ChartPanel(chart);
		  chartPanel.setBounds(12, 18, 600, 250);
		  pnlChartSimulation.add(chartPanel);
		  repaint();
	}
}
