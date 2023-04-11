import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.CardLayout;
import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.TextField;

import javax.swing.SpringLayout;
import java.awt.BorderLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import net.miginfocom.swing.MigLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;

public class app {

	private JFrame frame;
	private JTextField txtName;
	private JTextField txtType;
	private JTextField txtLowerb_1;
	private JTextField txtUpperb;
	public JPanel varsScrollPanel;
	public JPanel fuzzySetsContainer;
	public JPanel inputList;
	String currentVariableName = "";
	String rulesInput = "";
	
	ArrayList<Variable> varList;
	HashMap<String, Variable> varMap;
	HashMap<String, Integer> crispVarMap;
	FuzzyRule fuzzyRule;
	Defuzzifier defuzz;
	
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField varCrispVal;
	private JTextField txtInputtxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					app window = new app();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public app() {
		initialize();
		varList = new ArrayList<>();
		varMap = new HashMap<>();
		crispVarMap = new HashMap<>();
		fuzzyRule = new FuzzyRule();
		defuzz = new Defuzzifier();
	}

	private JPanel generateSubPanel() {
		JPanel panel_1 = new JPanel();
		varsScrollPanel.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 4, 0, 0));

		txtName = new JTextField();
		txtName.setText("Name");
		panel_1.add(txtName);
		txtName.setColumns(10);

		txtType = new JTextField();
		txtType.setText("Type");
		panel_1.add(txtType);
		txtType.setColumns(10);

		txtLowerb_1 = new JTextField();
		txtLowerb_1.setText("0");
		panel_1.add(txtLowerb_1);
		txtLowerb_1.setColumns(10);

		txtUpperb = new JTextField();
		txtUpperb.setText("0");
		panel_1.add(txtUpperb);
		txtUpperb.setColumns(10);

		return panel_1;
	}
	
	private JPanel generateSubClassPanel() {
		JPanel panel_1_1 = new JPanel();
		fuzzySetsContainer.add(panel_1_1);
		panel_1_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		textField = new JTextField();
		textField.setText("className");
		textField.setColumns(10);
		panel_1_1.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setText("Type");
		textField_1.setColumns(10);
		panel_1_1.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setText("0");
		textField_2.setColumns(10);
		panel_1_1.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setText("0");
		textField_3.setColumns(10);
		panel_1_1.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setText("0");
		textField_4.setColumns(10);
		panel_1_1.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setText("0");
		textField_5.setColumns(10);
		panel_1_1.add(textField_5);

		
		return panel_1_1;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 462, 322);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 446, 272);
		frame.getContentPane().add(tabbedPane);

		JPanel variablesPanel = new JPanel();
		tabbedPane.addTab("variables", null, variablesPanel, null);
		variablesPanel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 5, 421, 194);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		variablesPanel.add(scrollPane);

		varsScrollPanel = new JPanel();
		scrollPane.add(varsScrollPanel);
		scrollPane.setViewportView(varsScrollPanel);
		varsScrollPanel.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panel_1 = new JPanel();
		varsScrollPanel.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 4, 0, 0));

		txtName = new JTextField();
		txtName.setText("Name");
		panel_1.add(txtName);
		txtName.setColumns(10);

		txtType = new JTextField();
		txtType.setText("Type");
		panel_1.add(txtType);
		txtType.setColumns(10);

		txtLowerb_1 = new JTextField();
		txtLowerb_1.setText("0");
		panel_1.add(txtLowerb_1);
		txtLowerb_1.setColumns(10);

		txtUpperb = new JTextField();
		txtUpperb.setText("0");
		panel_1.add(txtUpperb);
		txtUpperb.setColumns(10);

		JButton btnAddAnotherV = new JButton("add another variable");
		btnAddAnotherV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				varsScrollPanel.add(generateSubPanel());
				varsScrollPanel.revalidate();
			}
		});
		btnAddAnotherV.setBounds(32, 210, 133, 23);
		variablesPanel.add(btnAddAnotherV);

		

		JPanel FuzzySetsPanel = new JPanel();
		tabbedPane.addTab("FuzzySets", null, FuzzySetsPanel, null);
		FuzzySetsPanel.setLayout(new CardLayout(0, 0));
		
		
		
		
		JPanel varClassesPanel = new JPanel();
		FuzzySetsPanel.add(varClassesPanel, "name_9838177275700");
		varClassesPanel.setLayout(null);
		
		/*
		 * JScrollPane scrollPane_1 = new JScrollPane(); scrollPane_1.setBounds(10, 11,
		 * 431, 183); varClassesPanel.add(scrollPane_1);
		 * 
		 * fuzzySetsContainer = new JPanel();
		 * scrollPane_1.setViewportView(fuzzySetsContainer);
		 * fuzzySetsContainer.setLayout(new GridLayout(0, 1, 0, 0));
		 * 
		 * JPanel panel_1_1 = new JPanel(); fuzzySetsContainer.add(panel_1_1);
		 * panel_1_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		 * 
		 * textField = new JTextField(); textField.setText("className");
		 * textField.setColumns(10); panel_1_1.add(textField);
		 * 
		 * textField_1 = new JTextField(); textField_1.setText("Type");
		 * textField_1.setColumns(10); panel_1_1.add(textField_1);
		 * 
		 * textField_2 = new JTextField(); textField_2.setText("0");
		 * textField_2.setColumns(10); panel_1_1.add(textField_2);
		 * 
		 * textField_3 = new JTextField(); textField_3.setText("0");
		 * textField_3.setColumns(10); panel_1_1.add(textField_3);
		 * 
		 * textField_4 = new JTextField(); textField_4.setText("0");
		 * textField_4.setColumns(10); panel_1_1.add(textField_4);
		 * 
		 * textField_5 = new JTextField(); textField_5.setText("0");
		 * textField_5.setColumns(10); panel_1_1.add(textField_5);
		 * 
		 * JButton btnAddAnotherClass = new JButton("add another class");
		 * btnAddAnotherClass.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent e) {
		 * fuzzySetsContainer.add(generateSubClassPanel());
		 * fuzzySetsContainer.revalidate(); } });
		 * 
		 * btnAddAnotherClass.setBounds(35, 210, 133, 23);
		 * varClassesPanel.add(btnAddAnotherClass);
		 */
		
		// to assign fuzzySets to its variable
		/////////////////////////////////////////////////////////////////////
		
		/*
		 * JButton btnSubmit_1 = new JButton("Submit");
		 * btnSubmit_1.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent e) { for (Component c :
		 * fuzzySetsContainer.getComponents()) { if (c instanceof JPanel) { JPanel panel
		 * = ((JPanel) c); int cnt = ((JPanel) c).getComponentCount();
		 * 
		 * String txtName = ((JTextField) panel.getComponent(0)).getText(); String
		 * txtType = ((JTextField) panel.getComponent(1)).getText(); Integer aVal =
		 * Integer.parseInt(((JTextField) panel.getComponent(2)).getText()); Integer
		 * bVal = Integer.parseInt(((JTextField) panel.getComponent(3)).getText());
		 * Integer cVal = Integer.parseInt(((JTextField)
		 * panel.getComponent(4)).getText()); Integer dVal =
		 * Integer.parseInt(((JTextField) panel.getComponent(5)).getText());
		 * 
		 * FuzzySet newSet = new FuzzySet(txtName, txtType, new
		 * ArrayList<>(Arrays.asList(aVal, bVal, cVal, dVal)) );
		 * varMap.get(currentVariableName).fuzzySets.add(newSet);
		 * 
		 * }
		 * 
		 * } varClassesPanel.setVisible(false); varButtonsPanel.setVisible(true);
		 * System.out.println("*******************");
		 * System.out.println(varMap.toString());
		 * System.out.println("*******************");
		 * 
		 * } }); btnSubmit_1.setBounds(206, 210, 65, 23);
		 * varClassesPanel.add(btnSubmit_1);
		 */
		
		JButton btnSubmit_1 = new JButton("Submit");
		btnSubmit_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (Component c : varClassesPanel.getComponents()) {
					if (c instanceof JTextField) {
						String filePath = ((JTextField) c).getText();
						readClassesInfo(filePath, varMap);

					}

				}
				varClassesPanel.setVisible(false);
				//varButtonsPanel.setVisible(true);
				System.out.println("*******************");
				System.out.println(varMap);
				System.out.println("*******************");
				
			}
		});
		btnSubmit_1.setBounds(355, 74, 65, 23);
		varClassesPanel.add(btnSubmit_1);
		
		JLabel lblNewLabel_1 = new JLabel("Classes info file path:");
		lblNewLabel_1.setBounds(10, 70, 128, 30);
		varClassesPanel.add(lblNewLabel_1);
		
		txtInputtxt = new JTextField();
		txtInputtxt.setText("input.txt");
		txtInputtxt.setBounds(153, 75, 176, 20);
		varClassesPanel.add(txtInputtxt);
		txtInputtxt.setColumns(10);

		
		  
		  
		  
		  JButton btnSubmit = new JButton("Submit");
			btnSubmit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					for (Component c : varsScrollPanel.getComponents()) {
						if (c instanceof JPanel) {
							JPanel panel = ((JPanel) c);
							int cnt = ((JPanel) c).getComponentCount();

							String txtName = ((JTextField) panel.getComponent(0)).getText();
							String txtType = ((JTextField) panel.getComponent(1)).getText();
							Integer lowerB = Integer.parseInt(((JTextField) panel.getComponent(2)).getText());
							Integer upperB = Integer.parseInt(((JTextField) panel.getComponent(3)).getText());

							Variable newVar = new Variable(txtName, txtType, lowerB, upperB);
							varMap.put(txtName, newVar);

						}

					}

					
					/*
					 * // add variables' buttons to fuzzySetsLayout for (Map.Entry<String, Variable>
					 * set : varMap.entrySet()) {
					 * 
					 * System.out.println(set.getKey() + " = " +set.getValue()); JButton
					 * btnNewButton = new JButton(set.getKey()); btnNewButton.addActionListener(new
					 * ActionListener() { public void actionPerformed(ActionEvent e) {
					 * currentVariableName = set.getKey(); varClassesPanel.setVisible(true);
					 * varButtonsPanel.setVisible(false); } }); varButtonsPanel.add(btnNewButton); }
					 */
					
					
					// add input variables to simulation layout
					for (Map.Entry<String, Variable> set : varMap.entrySet()) {
						
						if(set.getValue().type.equals("in"))
						{
							JPanel subInputVar = new JPanel();
							inputList.add(subInputVar);
							
							JLabel lblVarName = new JLabel(set.getKey());
							subInputVar.add(lblVarName);
							
							varCrispVal = new JTextField();
							varCrispVal.setText("0");
							subInputVar.add(varCrispVal);
							varCrispVal.setColumns(10);
							
						}
					}
					
					
				}
			});

			btnSubmit.setBounds(254, 210, 65, 23);
			variablesPanel.add(btnSubmit);
			
			JPanel RulesTab = new JPanel();
			tabbedPane.addTab("Rules tab", null, RulesTab, null);
			RulesTab.setLayout(null);
			
			JScrollPane scrollPane_2 = new JScrollPane();
			scrollPane_2.setBounds(10, 11, 421, 203);
			RulesTab.add(scrollPane_2);
			
			JTextArea textRulesInput = new JTextArea();
			scrollPane_2.setViewportView(textRulesInput);
			
			JButton btnSumbitRules = new JButton("submit Rules");
			btnSumbitRules.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rulesInput = textRulesInput.getText();
				
				}
			});
			btnSumbitRules.setBounds(158, 221, 124, 23);
			RulesTab.add(btnSumbitRules);
			
			JPanel resultsPanel = new JPanel();
			tabbedPane.addTab("Simulation", null, resultsPanel, null);
			resultsPanel.setLayout(null);
			
			JPanel panel_2 = new JPanel();
			panel_2.setBounds(233, 30, 198, 178);
			resultsPanel.add(panel_2);
			panel_2.setLayout(new GridLayout(0, 1, 0, 0));
			
			JTextArea txtFiledPredictRes = new JTextArea();
			txtFiledPredictRes.setLineWrap(true);
			panel_2.add(txtFiledPredictRes);
			
			JScrollPane scrollPane_3 = new JScrollPane();
			scrollPane_3.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane_3.setBounds(10, 22, 213, 197);
			resultsPanel.add(scrollPane_3);
			
			inputList = new JPanel();
			scrollPane_3.setViewportView(inputList);
			inputList.setLayout(new GridLayout(0, 1, 0, 0));
			
			/*
			 * JPanel subInputVar = new JPanel(); inputList.add(subInputVar);
			 * 
			 * JLabel lblVarName = new JLabel("varName"); subInputVar.add(lblVarName);
			 * 
			 * varCrispVal = new JTextField(); varCrispVal.setText("0");
			 * subInputVar.add(varCrispVal); varCrispVal.setColumns(10);
			 */
			
			JLabel lblNewLabel = new JLabel("Enter the crisp values:");
			lblNewLabel.setBounds(10, 0, 150, 25);
			resultsPanel.add(lblNewLabel);
			
			JButton btnNewButton_1 = new JButton("Run");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					for (Component c : inputList.getComponents()) {
						if (c instanceof JPanel) {
							JPanel panel = ((JPanel) c);
							int cnt = ((JPanel) c).getComponentCount();

							String varName = ((JLabel) panel.getComponent(0)).getText();
							Integer crispVal = Integer.parseInt(((JTextField) panel.getComponent(1)).getText());
							

							crispVarMap.put(varName, crispVal);

						}

					}
					
					// apply fuzzification phase
					calcAllMemInputs();

					// Apply Inference.
					fuzzyRule.InputScan(rulesInput, varMap);

					// apply defuzzification
					for (Map.Entry<String, Variable> set : varMap.entrySet()) {
						
						if(set.getValue().type.equals("out"))
						{
							System.out.println("in defuzz condition");
							
							defuzz = new Defuzzifier(set.getValue());
							String predictRes = defuzz.getPrediction();
							
							txtFiledPredictRes.setText(predictRes);
							
						}
					}
					
					System.out.println("******* crispVarMap **********");
					System.out.println(crispVarMap.toString());
					
					System.out.println("******* AllVarMap **********");
					System.out.println(varMap.toString());
				}
			});
			btnNewButton_1.setBounds(280, 219, 89, 23);
			resultsPanel.add(btnNewButton_1);
			
			JLabel lblPredRes = new JLabel("prediction result");
			lblPredRes.setBounds(233, 5, 102, 14);
			resultsPanel.add(lblPredRes);
			
			
			/*
			 * for (Component component : varButtonsPanel.getComponents()) { if (component
			 * instanceof JButton) { ((JButton) component).addActionListener(new
			 * ActionListener() { public void actionPerformed(ActionEvent e) {
			 * varClassesPanel.setVisible(true); varButtonsPanel.setVisible(false);
			 * 
			 * System.out.println("heree?????");
			 * 
			 * } }); } }
			 */
			 
		
	}
	
	public void calcAllMemInputs()
	{
		for (Map.Entry<String, Variable> set : varMap.entrySet()) {
			
			if(set.getValue().type.equals("in"))
			{
				for(FuzzySet fSet: set.getValue().fuzzySets)
				{
					Integer crisp = crispVarMap.get(set.getKey());
					fSet.memValue = fSet.calcMemValue(crisp);
				}
				
			}
			
		}
	}
	
void readClassesInfo(String filePath, HashMap<String, Variable> map) {
		
		String input = "";
		try {
			File myObj = new File(filePath);
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				input += data + "\n";
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		
		String[] lines = input.split("\n");
		for (String line : lines) {

			String[] tokens = line.split(" ");

			String variableName = tokens[0];
			String className = tokens[1];
			String classType = tokens[2];
			ArrayList<Integer> points = new ArrayList<>();

			for (int i = 3; i < tokens.length; i++) {
				points.add(Integer.parseInt(tokens[i]));
			}

			FuzzySet newSet = new FuzzySet(className, classType, points);
			map.get(variableName).addFuzzySet(newSet);
		}
		 
        
		
	}
}
