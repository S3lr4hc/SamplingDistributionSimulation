import javax.swing.DefaultListModel;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JList;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JTextPane;

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.*;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.JTextField;


@SuppressWarnings("serial")
public class resultFrame extends JFrame{
	private JFreeChart chart;
	private ChartPanel chartPanel;	
	private JPanel PopGraphpanel;
	
	private JSlider Nslider;
	private JSlider nslider;
	
	private int nFinal;
	private double pFinal;
	private Integer[] xInputs;
	private JTextField ntextField;
	
	@SuppressWarnings({ "deprecation", "static-access" })
	public resultFrame(){//inside parameters are the values required for simulation
		
		this.setSize(949 , 700);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		getContentPane().setBackground(Color.LIGHT_GRAY);
		setTitle("Result");
		getContentPane().setLayout(null);
		
		JPanel Resultpanel = new JPanel();
		Resultpanel.setBackground(Color.BLACK);
		Resultpanel.setBounds(55, 496, 836, 178);
		getContentPane().add(Resultpanel);
		Resultpanel.setLayout(null);
		
		
		JLabel labelCumulative = new JLabel("Population Mean");
		labelCumulative.setForeground(Color.WHITE);
		labelCumulative.setFont(new Font("Letter Gothic Std", Font.BOLD, 9));
		labelCumulative.setBounds(25, 11, 194, 14);
		Resultpanel.add(labelCumulative);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(25, 36, 189, 54);
		Resultpanel.add(scrollPane_1);
		
		JTextPane popMeantextPane = new JTextPane();
		scrollPane_1.setViewportView(popMeantextPane);
		//popMeantextPane.setText(String.valueOf(cumulativeResult));
		popMeantextPane.setFont(new Font("Tahoma", Font.PLAIN, 22));
		
		JLabel lblPopulationVariance = new JLabel("Population Variance");
		lblPopulationVariance.setForeground(Color.WHITE);
		lblPopulationVariance.setFont(new Font("Dialog", Font.BOLD, 9));
		lblPopulationVariance.setBounds(224, 11, 194, 14);
		Resultpanel.add(lblPopulationVariance);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(224, 36, 189, 54);
		Resultpanel.add(scrollPane);
		
		JTextPane popVartextPane = new JTextPane();
		scrollPane.setViewportView(popVartextPane);
		popVartextPane.setText("0.0");
		popVartextPane.setFont(new Font("Tahoma", Font.PLAIN, 22));
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(423, 36, 189, 54);
		Resultpanel.add(scrollPane_2);
		
		JTextPane samMeantextPane = new JTextPane();
		scrollPane_2.setViewportView(samMeantextPane);
		samMeantextPane.setText("0.0");
		samMeantextPane.setFont(new Font("Tahoma", Font.PLAIN, 22));
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(622, 36, 189, 54);
		Resultpanel.add(scrollPane_3);
		
		JTextPane samVartextPane = new JTextPane();
		scrollPane_3.setViewportView(samVartextPane);
		samVartextPane.setText("0.0");
		samVartextPane.setFont(new Font("Tahoma", Font.PLAIN, 22));
		
		JLabel lblSampleMean = new JLabel("Sample Mean");
		lblSampleMean.setForeground(Color.WHITE);
		lblSampleMean.setFont(new Font("Dialog", Font.BOLD, 9));
		lblSampleMean.setBounds(428, 11, 194, 14);
		Resultpanel.add(lblSampleMean);
		
		JLabel lblSampleVariance = new JLabel("Sample Variance");
		lblSampleVariance.setForeground(Color.WHITE);
		lblSampleVariance.setFont(new Font("Dialog", Font.BOLD, 9));
		lblSampleVariance.setBounds(622, 11, 194, 14);
		Resultpanel.add(lblSampleVariance);
		
		JLabel lblSampleSizeN = new JLabel("Input Sample Size n:");
		lblSampleSizeN.setForeground(Color.WHITE);
		lblSampleSizeN.setFont(new Font("Dialog", Font.BOLD, 9));
		lblSampleSizeN.setBounds(10, 101, 194, 14);
		Resultpanel.add(lblSampleSizeN);
		
		ntextField = new JTextField();
		ntextField.setBounds(10, 126, 204, 41);
		Resultpanel.add(ntextField);
		ntextField.setColumns(10);
		Nslider = new JSlider();				
		Nslider.setValue(0);
		Nslider.setBounds(285, 126, 224, 41);
		Resultpanel.add(Nslider);
		
		Nslider.setFont(new Font("Tahoma", Font.PLAIN, 16));		
		Nslider.setBackground(Color.GRAY);
		
		
		JLabel lblN = new JLabel("N");
		lblN.setBounds(284, 97, 21, 14);
		Resultpanel.add(lblN);
		lblN.setForeground(Color.WHITE);
		lblN.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		nslider = new JSlider();
		nslider.setBounds(570, 126, 223, 41);
		Resultpanel.add(nslider);
		nslider.setValue((int) pFinal*100);
		nslider.setMaximum(100);
		nslider.setMinimum(0);
		nslider.setBackground(Color.DARK_GRAY);
		
		JLabel lbln = new JLabel("n");
		lbln.setBounds(570, 101, 21, 14);
		Resultpanel.add(lbln);
		lbln.setForeground(Color.WHITE);
		lbln.setFont(new Font("Tahoma", Font.BOLD, 18));
		nslider.addChangeListener(new ChangeListener(){

			@Override
			public void stateChanged(ChangeEvent e) {
				
				
			}
			
		});
		Nslider.addChangeListener(new ChangeListener(){

			@Override
			public void stateChanged(ChangeEvent arg0) {
				
				
			}});


			
		JLabel lblComputationResults = new JLabel("Computation Results");
		lblComputationResults.setFont(new Font("Letter Gothic Std", Font.BOLD, 16));
		lblComputationResults.setBounds(10, 11, 194, 21);
		getContentPane().add(lblComputationResults);
		
		PopGraphpanel = new JPanel();
		PopGraphpanel.setBounds(10, 36, 458, 460);
		getContentPane().add(PopGraphpanel);
		PopGraphpanel.setLayout(null);
		
		JPanel SamGraphpanel = new JPanel();
		SamGraphpanel.setLayout(null);
		SamGraphpanel.setBounds(475, 36, 458, 460);
		getContentPane().add(SamGraphpanel);
		
		
	}
}
