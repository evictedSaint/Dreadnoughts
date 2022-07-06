package UserInterface;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

import ComponentClasses.Component;
import ComponentClasses.DefenseBank;
import GameEngine.Faction;
import GameEngine.Game;
import GameEngine.GameMode;
import GameEngine.MapManager;
import GameEngine.SaveManager;
import GameEngine.Ship;

import javax.swing.ListModel;
import net.miginfocom.swing.MigLayout;

public class GuiNewGameMode extends JFrame {
	

	private JFrame frame;
	private JTextField textFieldDistances;
	private JTextField textFieldFactionName;
	private JTextField textFieldRepairTime;
	private JTextField textFieldFatalityPercentage;
	private JTextField textFieldWeaponType;
	private JTextField textFieldBankDefense;
	private static List<BankDefenseValues> bdValues = new ArrayList<BankDefenseValues>();
	
	static DefaultListModel componentsModel = new DefaultListModel<>();
	private JTextField textFieldSaveName;
	
	public GuiNewGameMode() {
		initialize();
		frame.setVisible(true);
	}

	

	private void initialize() {
		bdValues = new ArrayList<BankDefenseValues>();
		frame = new JFrame();
		frame.setBounds(100, 100, 479, 527);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		
		JPanel panelNewGameMode1 = new JPanel();
		frame.getContentPane().add(panelNewGameMode1, "panelNewGameMode1");
		panelNewGameMode1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panelNewGameMode1.add(panel, BorderLayout.SOUTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{111, 115, 115, 0};
		gbl_panel.rowHeights = new int[]{29, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		});
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.anchor = GridBagConstraints.WEST;
		gbc_btnCancel.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancel.gridx = 1;
		gbc_btnCancel.gridy = 0;
		panel.add(btnCancel, gbc_btnCancel);
		
		JButton btnNext = new JButton("Next");
		
		GridBagConstraints gbc_btnNext = new GridBagConstraints();
		gbc_btnNext.anchor = GridBagConstraints.EAST;
		gbc_btnNext.gridx = 2;
		gbc_btnNext.gridy = 0;
		panel.add(btnNext, gbc_btnNext);
		
		JPanel panel_1 = new JPanel();
		panelNewGameMode1.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Distances (Closest to Furthest):");
		lblNewLabel.setBounds(30, 0, 220, 20);
		panel_1.add(lblNewLabel);
		
		DefaultListModel<String> distancesModel = new DefaultListModel<>();
		JList listDistances = new JList(distancesModel);
		listDistances.setBounds(15, 31, 229, 395);
		panel_1.add(listDistances);
		
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (distancesModel.size() > 0) {
	                CardLayout cl = (CardLayout)(frame.getContentPane().getLayout());
	                cl.show(frame.getContentPane(), "panelNewGameMode2");  					
				}
			}
		});
		
		textFieldDistances = new JTextField();
		textFieldDistances.setBounds(259, 29, 146, 26);
		panel_1.add(textFieldDistances);
		textFieldDistances.setColumns(10);
		
		JButton btnAdd = new JButton("Add"); 
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textFieldDistances.getText().length() > 0) {
					distancesModel.addElement(textFieldDistances.getText());
					textFieldDistances.setText("");
				}
			}
		});
		btnAdd.setBounds(259, 71, 115, 29);
		panel_1.add(btnAdd);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (distancesModel.size() > 0) {
					distancesModel.remove(distancesModel.size() - 1);
				}
			}
		});
		btnRemove.setBounds(259, 116, 115, 29);
		panel_1.add(btnRemove);
		
		//======================================================================================
		JPanel panelNewGameMode2 = new JPanel();
		frame.getContentPane().add(panelNewGameMode2, "panelNewGameMode2");
		panelNewGameMode2.setLayout(new BorderLayout(4, 4));
		
		JPanel panel_2 = new JPanel();
		panelNewGameMode2.add(panel_2, BorderLayout.SOUTH);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{111, 115, 115, 0};
		gbl_panel_2.rowHeights = new int[]{29, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JButton btnCancel_1 = new JButton("Cancel");
		btnCancel_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		});
		GridBagConstraints gbc_btnCancel_1 = new GridBagConstraints();
		gbc_btnCancel_1.anchor = GridBagConstraints.WEST;
		gbc_btnCancel_1.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancel_1.gridx = 1;
		gbc_btnCancel_1.gridy = 0;
		panel_2.add(btnCancel_1, gbc_btnCancel_1);
		
		JPanel panel_5 = new JPanel();
		panelNewGameMode2.add(panel_5, BorderLayout.CENTER);
		GridBagLayout gbl_panel_5 = new GridBagLayout();
		gbl_panel_5.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel_5.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panel_5.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_5.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_5.setLayout(gbl_panel_5);
		
		JLabel lblNewLabel_1 = new JLabel("Faction Name:");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 0;
		panel_5.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JLabel lblNewLabel_3 = new JLabel("Repair Time:");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 1;
		gbc_lblNewLabel_3.gridy = 0;
		panel_5.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		JLabel lblNewLabel_7 = new JLabel("Fatality %:");
		GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
		gbc_lblNewLabel_7.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_7.gridx = 2;
		gbc_lblNewLabel_7.gridy = 0;
		panel_5.add(lblNewLabel_7, gbc_lblNewLabel_7);
		
		DefaultListModel<String> factionNameModel = new DefaultListModel<>();
		JList list_FactionName = new JList(factionNameModel);
		GridBagConstraints gbc_list_FactionName = new GridBagConstraints();
		gbc_list_FactionName.insets = new Insets(0, 0, 5, 5);
		gbc_list_FactionName.fill = GridBagConstraints.BOTH;
		gbc_list_FactionName.gridx = 0;
		gbc_list_FactionName.gridy = 1;
		panel_5.add(list_FactionName, gbc_list_FactionName);
	
		DefaultListModel<String> repairTimeModel = new DefaultListModel<>();
		JList list_RepairTime = new JList(repairTimeModel);
		GridBagConstraints gbc_list_RepairTime = new GridBagConstraints();
		gbc_list_RepairTime.insets = new Insets(0, 0, 5, 5);
		gbc_list_RepairTime.fill = GridBagConstraints.BOTH;
		gbc_list_RepairTime.gridx = 1;
		gbc_list_RepairTime.gridy = 1;
		panel_5.add(list_RepairTime, gbc_list_RepairTime);
	
		DefaultListModel<String> fatalityPercModel = new DefaultListModel<>();
		JList list_FatalityPerc = new JList(fatalityPercModel);
		GridBagConstraints gbc_list_FatalityPerc = new GridBagConstraints();
		gbc_list_FatalityPerc.insets = new Insets(0, 0, 5, 0);
		gbc_list_FatalityPerc.fill = GridBagConstraints.BOTH;
		gbc_list_FatalityPerc.gridx = 2;
		gbc_list_FatalityPerc.gridy = 1;
		panel_5.add(list_FatalityPerc, gbc_list_FatalityPerc);
		
		textFieldFactionName = new JTextField();
		GridBagConstraints gbc_textFieldFactionName = new GridBagConstraints();
		gbc_textFieldFactionName.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldFactionName.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldFactionName.gridx = 0;
		gbc_textFieldFactionName.gridy = 2;
		panel_5.add(textFieldFactionName, gbc_textFieldFactionName);
		textFieldFactionName.setColumns(10);
		
		textFieldRepairTime = new JTextField();
		GridBagConstraints gbc_textFieldRepairTime = new GridBagConstraints();
		gbc_textFieldRepairTime.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldRepairTime.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldRepairTime.gridx = 1;
		gbc_textFieldRepairTime.gridy = 2;
		panel_5.add(textFieldRepairTime, gbc_textFieldRepairTime);
		textFieldRepairTime.setColumns(10);
		
		textFieldFatalityPercentage = new JTextField();
		GridBagConstraints gbc_textFieldFatalityPercentage = new GridBagConstraints();
		gbc_textFieldFatalityPercentage.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldFatalityPercentage.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldFatalityPercentage.gridx = 2;
		gbc_textFieldFatalityPercentage.gridy = 2;
		panel_5.add(textFieldFatalityPercentage, gbc_textFieldFatalityPercentage);
		textFieldFatalityPercentage.setColumns(10);
		
		JButton btnNext_1 = new JButton("Next");
		btnNext_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (factionNameModel.size() > 0 && repairTimeModel.size() > 0 && fatalityPercModel.size() > 0) {
	                CardLayout cl = (CardLayout)(frame.getContentPane().getLayout());
	                cl.show(frame.getContentPane(), "panelNewGameMode3");  					
				}
			}
		});
		GridBagConstraints gbc_btnNext_1 = new GridBagConstraints();
		gbc_btnNext_1.anchor = GridBagConstraints.EAST;
		gbc_btnNext_1.gridx = 2;
		gbc_btnNext_1.gridy = 0;
		panel_2.add(btnNext_1, gbc_btnNext_1);		
		
		JButton btnRemove_1 = new JButton("Remove");
		btnRemove_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (factionNameModel.size() > 0) {
					factionNameModel.remove(factionNameModel.size()-1);					
				}
				if (repairTimeModel.size() > 0) {
					repairTimeModel.remove(repairTimeModel.size()-1);					
				}
				if (fatalityPercModel.size() > 0) {
					fatalityPercModel.remove(fatalityPercModel.size()-1);					
				}
			}
		});
		GridBagConstraints gbc_btnRemove_1 = new GridBagConstraints();
		gbc_btnRemove_1.insets = new Insets(0, 0, 0, 5);
		gbc_btnRemove_1.gridx = 0;
		gbc_btnRemove_1.gridy = 3;
		panel_5.add(btnRemove_1, gbc_btnRemove_1);
		
		//this adds repairtime, fatality percentage, factionname to the new gamemode ui
		JButton btnAdd_1 = new JButton("Add");
		btnAdd_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int repairTime = 0;
				int fatalityPerc = 0;
				if (!textFieldFactionName.getText().isEmpty() && !textFieldRepairTime.getText().isEmpty() && !textFieldFatalityPercentage.getText().isEmpty()) {
					try {
						repairTime = Integer.parseInt(textFieldRepairTime.getText());	
						if (repairTime < 0) {
							throw new NumberFormatException();
						}			
					}
					catch(NumberFormatException err) { 
						JOptionPane.showMessageDialog(frame,
							    "Repair Time must be a positive integer.",
							    "Repair Time",
							    JOptionPane.WARNING_MESSAGE);
						return;
					}
					try {
						fatalityPerc = Integer.parseInt(textFieldFatalityPercentage.getText());		
						if (fatalityPerc < 0 || fatalityPerc > 100) {
							throw new NumberFormatException();
						}
					}
					catch(NumberFormatException err) { 
						JOptionPane.showMessageDialog(frame,
							    "Fatality Percentage must be a positive integer between 0 and 100, inclusive.",
							    "Fatality Percentage",
							    JOptionPane.WARNING_MESSAGE);						
						return;
					}
	
					factionNameModel.addElement(textFieldFactionName.getText());
					textFieldFactionName.setText("");
					repairTimeModel.addElement(textFieldRepairTime.getText());
					textFieldRepairTime.setText("");
					fatalityPercModel.addElement(textFieldFatalityPercentage.getText());
					textFieldFatalityPercentage.setText("");
				}
			}
		});
		GridBagConstraints gbc_btnAdd_1 = new GridBagConstraints();
		gbc_btnAdd_1.gridx = 2;
		gbc_btnAdd_1.gridy = 3;
		panel_5.add(btnAdd_1, gbc_btnAdd_1);
		
		//=====================================================================================
		JPanel panelNewGameMode3 = new JPanel();
		frame.getContentPane().add(panelNewGameMode3, "panelNewGameMode3");
		panelNewGameMode3.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_4 = new JPanel();
		panelNewGameMode3.add(panel_4, BorderLayout.SOUTH);
		GridBagLayout gbl_panel_4 = new GridBagLayout();
		gbl_panel_4.columnWidths = new int[]{111, 115, 115, 0};
		gbl_panel_4.rowHeights = new int[]{29, 0};
		gbl_panel_4.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_4.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_4.setLayout(gbl_panel_4);
		
		JButton btnCancel_3 = new JButton("Cancel");
		btnCancel_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		});
		GridBagConstraints gbc_btnCancel_3 = new GridBagConstraints();
		gbc_btnCancel_3.anchor = GridBagConstraints.WEST;
		gbc_btnCancel_3.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancel_3.gridx = 1;
		gbc_btnCancel_3.gridy = 0;
		panel_4.add(btnCancel_3, gbc_btnCancel_3);
		
		JButton btnNext_3 = new JButton("Next");
		btnNext_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout)(frame.getContentPane().getLayout());
                cl.show(frame.getContentPane(), "panelNGMDefenseBank");  
			}
		});
		GridBagConstraints gbc_btnNext_3 = new GridBagConstraints();
		gbc_btnNext_3.anchor = GridBagConstraints.EAST;
		gbc_btnNext_3.gridx = 2;
		gbc_btnNext_3.gridy = 0;
		panel_4.add(btnNext_3, gbc_btnNext_3);
		
		JPanel panel_6 = new JPanel();
		panelNewGameMode3.add(panel_6, BorderLayout.CENTER);
		GridBagLayout gbl_panel_6 = new GridBagLayout();
		gbl_panel_6.columnWidths = new int[]{0, 0};
		gbl_panel_6.rowHeights = new int[]{0, 353, 0, 0, 0};
		gbl_panel_6.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_6.rowWeights = new double[]{0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		panel_6.setLayout(gbl_panel_6);
		
		JLabel lblWeaponTypes = new JLabel("Weapon Types");
		GridBagConstraints gbc_lblWeaponTypes = new GridBagConstraints();
		gbc_lblWeaponTypes.insets = new Insets(0, 0, 5, 0);
		gbc_lblWeaponTypes.gridx = 0;
		gbc_lblWeaponTypes.gridy = 0;
		panel_6.add(lblWeaponTypes, gbc_lblWeaponTypes);

		DefaultListModel<String> weaponTypesModel = new DefaultListModel<>();
		JList listWeaponTypes = new JList(weaponTypesModel);
		GridBagConstraints gbc_listWeaponTypes = new GridBagConstraints();
		gbc_listWeaponTypes.insets = new Insets(0, 0, 5, 0);
		gbc_listWeaponTypes.fill = GridBagConstraints.BOTH;
		gbc_listWeaponTypes.gridx = 0;
		gbc_listWeaponTypes.gridy = 1;
		panel_6.add(listWeaponTypes, gbc_listWeaponTypes);
		
		textFieldWeaponType = new JTextField();
		GridBagConstraints gbc_textFieldWeaponType = new GridBagConstraints();
		gbc_textFieldWeaponType.anchor = GridBagConstraints.SOUTH;
		gbc_textFieldWeaponType.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldWeaponType.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldWeaponType.gridx = 0;
		gbc_textFieldWeaponType.gridy = 2;
		panel_6.add(textFieldWeaponType, gbc_textFieldWeaponType);
		textFieldWeaponType.setColumns(10);
		
		JPanel panel_7 = new JPanel();
		GridBagConstraints gbc_panel_7 = new GridBagConstraints();
		gbc_panel_7.anchor = GridBagConstraints.SOUTH;
		gbc_panel_7.fill = GridBagConstraints.BOTH;
		gbc_panel_7.gridx = 0;
		gbc_panel_7.gridy = 3;
		panel_6.add(panel_7, gbc_panel_7);
		GridBagLayout gbl_panel_7 = new GridBagLayout();
		gbl_panel_7.columnWidths = new int[]{86, 290, 0};
		gbl_panel_7.rowHeights = new int[]{0, 0};
		gbl_panel_7.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_7.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_7.setLayout(gbl_panel_7);
		
		JButton btnRemoveWT = new JButton("Remove");
		btnRemoveWT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (weaponTypesModel.size() > 0) {
					weaponTypesModel.remove(weaponTypesModel.size()-1);
				}
			}
		});
		GridBagConstraints gbc_btnRemoveWT = new GridBagConstraints();
		gbc_btnRemoveWT.anchor = GridBagConstraints.WEST;
		gbc_btnRemoveWT.insets = new Insets(0, 0, 0, 5);
		gbc_btnRemoveWT.gridx = 0;
		gbc_btnRemoveWT.gridy = 0;
		panel_7.add(btnRemoveWT, gbc_btnRemoveWT);
		
		JButton btnAddWeaponType = new JButton("Add");
		btnAddWeaponType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textFieldWeaponType.getText().trim().length() > 0) {
					weaponTypesModel.addElement(textFieldWeaponType.getText());
					textFieldWeaponType.setText("");
				}
			}
		});
		GridBagConstraints gbc_btnAddWeaponType = new GridBagConstraints();
		gbc_btnAddWeaponType.anchor = GridBagConstraints.EAST;
		gbc_btnAddWeaponType.gridx = 1;
		gbc_btnAddWeaponType.gridy = 0;
		panel_7.add(btnAddWeaponType, gbc_btnAddWeaponType);
		
		//====================================================================================
		JPanel panelNGMDefenseBank = new JPanel();
		frame.getContentPane().add(panelNGMDefenseBank, "panelNGMDefenseBank");
		panelNGMDefenseBank.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_6_1 = new JPanel();
		panelNGMDefenseBank.add(panel_6_1);
		GridBagLayout gbl_panel_6_1 = new GridBagLayout();
		gbl_panel_6_1.columnWidths = new int[]{0, 0};
		gbl_panel_6_1.rowHeights = new int[]{0, 353, 0, 0, 0};
		gbl_panel_6_1.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_6_1.rowWeights = new double[]{0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		panel_6_1.setLayout(gbl_panel_6_1);
		
		JLabel lblBankDefense = new JLabel("Bank Defenses");
		GridBagConstraints gbc_lblBankDefense = new GridBagConstraints();
		gbc_lblBankDefense.insets = new Insets(0, 0, 5, 0);
		gbc_lblBankDefense.gridx = 0;
		gbc_lblBankDefense.gridy = 0;
		panel_6_1.add(lblBankDefense, gbc_lblBankDefense);
		
		
		DefaultListModel<String> bankDefensesModel = new DefaultListModel<>();
		JList listBankDefenses = new JList(bankDefensesModel);
		GridBagConstraints gbc_listBankDefenses = new GridBagConstraints();
		gbc_listBankDefenses.fill = GridBagConstraints.BOTH;
		gbc_listBankDefenses.insets = new Insets(0, 0, 5, 0);
		gbc_listBankDefenses.gridx = 0;
		gbc_listBankDefenses.gridy = 1;
		panel_6_1.add(listBankDefenses, gbc_listBankDefenses);
		
		textFieldBankDefense = new JTextField();
		textFieldBankDefense.setColumns(10);
		GridBagConstraints gbc_textFieldBankDefense = new GridBagConstraints();
		gbc_textFieldBankDefense.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldBankDefense.anchor = GridBagConstraints.SOUTH;
		gbc_textFieldBankDefense.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldBankDefense.gridx = 0;
		gbc_textFieldBankDefense.gridy = 2;
		panel_6_1.add(textFieldBankDefense, gbc_textFieldBankDefense);
		
		JPanel panel_7_1 = new JPanel();
		GridBagConstraints gbc_panel_7_1 = new GridBagConstraints();
		gbc_panel_7_1.fill = GridBagConstraints.BOTH;
		gbc_panel_7_1.anchor = GridBagConstraints.SOUTH;
		gbc_panel_7_1.gridx = 0;
		gbc_panel_7_1.gridy = 3;
		panel_6_1.add(panel_7_1, gbc_panel_7_1);
		GridBagLayout gbl_panel_7_1 = new GridBagLayout();
		gbl_panel_7_1.columnWidths = new int[]{86, 290, 0};
		gbl_panel_7_1.rowHeights = new int[]{0, 0};
		gbl_panel_7_1.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_7_1.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_7_1.setLayout(gbl_panel_7_1);
		
		JButton btnRemoveBD = new JButton("Remove");
		btnRemoveBD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (bankDefensesModel.size() > 0) {
					bankDefensesModel.remove(bankDefensesModel.size()-1);
					deleteLastBDV();
				}
			}
		});
		GridBagConstraints gbc_btnRemoveBD = new GridBagConstraints();
		gbc_btnRemoveBD.anchor = GridBagConstraints.WEST;
		gbc_btnRemoveBD.insets = new Insets(0, 0, 0, 5);
		gbc_btnRemoveBD.gridx = 0;
		gbc_btnRemoveBD.gridy = 0;
		panel_7_1.add(btnRemoveBD, gbc_btnRemoveBD);
		
		//Need to send "this" to the guibankdefense, which can update the info without issue ####
		JButton btnAddBankDefense = new JButton("Add");
		btnAddBankDefense.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textFieldBankDefense.getText().trim().length() > 0) {
					BankDefenseValues tempBDV = new BankDefenseValues(textFieldBankDefense.getText());
					bdValues.add(tempBDV);
					bankDefensesModel.addElement(textFieldBankDefense.getText());
					textFieldBankDefense.setText("");
					for (int i = 0; i < weaponTypesModel.size(); i++) {
						tempBDV.weaponTypesDamage.put(weaponTypesModel.elementAt(i), 0);
					}
					new GuiBankDefenseWindow(tempBDV);
				}
			}
		});
		GridBagConstraints gbc_btnAddBankDefense = new GridBagConstraints();
		gbc_btnAddBankDefense.anchor = GridBagConstraints.EAST;
		gbc_btnAddBankDefense.gridx = 1;
		gbc_btnAddBankDefense.gridy = 0;
		panel_7_1.add(btnAddBankDefense, gbc_btnAddBankDefense);
		
		JPanel panel_4_1 = new JPanel();
		panelNGMDefenseBank.add(panel_4_1, BorderLayout.SOUTH);
		GridBagLayout gbl_panel_4_1 = new GridBagLayout();
		gbl_panel_4_1.columnWidths = new int[]{111, 115, 115, 0};
		gbl_panel_4_1.rowHeights = new int[]{29, 0};
		gbl_panel_4_1.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_4_1.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_4_1.setLayout(gbl_panel_4_1);
		
		JButton btnCancel_3_1 = new JButton("Cancel");
		btnCancel_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		});
		GridBagConstraints gbc_btnCancel_3_1 = new GridBagConstraints();
		gbc_btnCancel_3_1.anchor = GridBagConstraints.WEST;
		gbc_btnCancel_3_1.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancel_3_1.gridx = 1;
		gbc_btnCancel_3_1.gridy = 0;
		panel_4_1.add(btnCancel_3_1, gbc_btnCancel_3_1);
		
		JButton btnNext_3_1 = new JButton("Next");
		btnNext_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout)(frame.getContentPane().getLayout());
                cl.show(frame.getContentPane(), "panelNewGameMode4");  
                
				//------
                //We finally have enough info to start building the Game, so let's create it.                
                //------
                //First, we gotta make sure the variables are all cleared in Game.
                GameMode.getInstance().factions = new HashMap<String, Faction>();
                GameMode.getInstance().weaponTypes = new HashSet<String>();
                GameMode.getInstance().bankDefenseProjectileTypeDamage = new HashMap<String, HashMap<String, Integer>>();
				GameMode.getInstance().distances = new HashMap<Integer, String>();
				GameMode.getInstance().components = new HashMap<String, Component>();
				//FIXME: components page of NewGameMode isn't reset, need to reset it.
                //Other variables in Game shouldn't be relevant (famous last words, ha ha.)
                
                //factions...
                int i = factionNameModel.getSize();
                for (int j = 0; j < i; j++) {
                	GameMode.getInstance().factions.put(factionNameModel.get(j), new Faction(factionNameModel.get(j), Integer.parseInt(repairTimeModel.get(j)), Integer.parseInt(fatalityPercModel.get(j))));
                }
                
                //weaponTypes...
                for (int j = 0; j < weaponTypesModel.getSize(); j++) {
                	GameMode.getInstance().weaponTypes.add(weaponTypesModel.get(j));
                }
                
                //bankDefenses...
                for (BankDefenseValues bdv : bdValues) {
                	GameMode.getInstance().bankDefenseProjectileTypeDamage.put(bdv.name, bdv.weaponTypesDamage);
                }
                
                //distances...
                i = distancesModel.getSize(); //cant iterate over model's, so this is a bit of a workaround  //CLOSEST TO FURTHEST, closest is 0.
                for (int j = 0; j < i; j++) {
                	GameMode.getInstance().distances.put(j, distancesModel.get(j));
                }
				MapManager.distances = GameMode.getInstance().distances;
                
                //------
			}
		});
		GridBagConstraints gbc_btnNext_3_1 = new GridBagConstraints();
		gbc_btnNext_3_1.anchor = GridBagConstraints.EAST;
		gbc_btnNext_3_1.gridx = 2;
		gbc_btnNext_3_1.gridy = 0;
		panel_4_1.add(btnNext_3_1, gbc_btnNext_3_1);
		
		//=====================================================================
		JPanel panelNewGameMode4 = new JPanel();
		frame.getContentPane().add(panelNewGameMode4, "panelNewGameMode4");
		panelNewGameMode4.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1_2 = new JPanel();
		panelNewGameMode4.add(panel_1_2, BorderLayout.CENTER);
		panel_1_2.setLayout(null);
		
		JLabel lblNewLabel_2_ = new JLabel("Components");
		lblNewLabel_2_.setBounds(171, 16, 110, 20);
		panel_1_2.add(lblNewLabel_2_);

		JList listComponents = new JList(componentsModel);
		listComponents.setBounds(15, 48, 269, 378);
		panel_1_2.add(listComponents);
		
		JComboBox comboBoxComponentTypes = new JComboBox();
		
		comboBoxComponentTypes.addItem("Augment");
		comboBoxComponentTypes.addItem("Defense-Bank");
		comboBoxComponentTypes.addItem("Defense-Rolling");
		comboBoxComponentTypes.addItem("Defense-Static");
		comboBoxComponentTypes.addItem("Passive Defense");
		comboBoxComponentTypes.addItem("Power Supply");
		comboBoxComponentTypes.addItem("Weapon");
		comboBoxComponentTypes.addItem("Jump Drive");
		comboBoxComponentTypes.setBounds(299, 46, 143, 26);
		panel_1_2.add(comboBoxComponentTypes);
		
		JButton btnNewComponent = new JButton("New Component");
		btnNewComponent.setEnabled(false);
		btnNewComponent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String augmentType = (String) comboBoxComponentTypes.getSelectedItem();
				
				switch (augmentType) {
				case "Augment": //TODO: implement augments eventually.
					break;
				case "Defense-Bank":
	                new GuiCompDefenseBank(); 	
	                break;
				case "Defense-Rolling":
					new GuiCompDefenseRolling();
	                break;
				case "Defense-Static":
					new GuiCompDefenseStatic();
	                break;
				case "Passive Defense":
					new GuiCompPassiveDefense();
	                break;
				case "Power Supply":
					new GuiCompPowerSupply();
	                break;
				case "Weapon":
					new GuiCompWeapon();
	                break;
				case "Jump Drive":
					new GuiCompJumpDrive();
	                break;				
				}
			}
		});
		
		comboBoxComponentTypes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (comboBoxComponentTypes.getSelectedItem().equals("Augment")) {
					btnNewComponent.setEnabled(false);
				}
				else {
					btnNewComponent.setEnabled(true);
				}
			}
		});
		
		btnNewComponent.setBounds(299, 88, 143, 29);
		panel_1_2.add(btnNewComponent);
		
		JButton btnNewButton_1_ = new JButton("Edit Selected");
		btnNewButton_1_.setEnabled(false);
		btnNewButton_1_.setBounds(299, 133, 143, 29);
		panel_1_2.add(btnNewButton_1_);
		
		JPanel panel_3 = new JPanel();
		panelNewGameMode4.add(panel_3, BorderLayout.SOUTH);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{111, 115, 115, 0};
		gbl_panel_3.rowHeights = new int[]{29, 0};
		gbl_panel_3.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_3.setLayout(gbl_panel_3);
		
		JButton btnCancel_2 = new JButton("Cancel");
		btnCancel_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		});
		GridBagConstraints gbc_btnCancel_2 = new GridBagConstraints();
		gbc_btnCancel_2.anchor = GridBagConstraints.WEST;
		gbc_btnCancel_2.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancel_2.gridx = 1;
		gbc_btnCancel_2.gridy = 0;
		panel_3.add(btnCancel_2, gbc_btnCancel_2);
		
		JButton btnNext_2 = new JButton("Next");
		btnNext_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout)(frame.getContentPane().getLayout());
                cl.show(frame.getContentPane(), "panelNewGameMode5");  
			}
		});
		GridBagConstraints gbc_btnNext_2 = new GridBagConstraints();
		gbc_btnNext_2.anchor = GridBagConstraints.EAST;
		gbc_btnNext_2.gridx = 2;
		gbc_btnNext_2.gridy = 0;
		panel_3.add(btnNext_2, gbc_btnNext_2);
		
		JPanel panelNewGameMode5 = new JPanel();
		frame.getContentPane().add(panelNewGameMode5, "panelNewGameMode5");
		panelNewGameMode5.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_8 = new JPanel();
		panelNewGameMode5.add(panel_8, BorderLayout.CENTER);
		panel_8.setLayout(new MigLayout("", "[grow]", "[][]"));
		
		JLabel lblNewLabel_2 = new JLabel("New GameMode Save Name:");
		panel_8.add(lblNewLabel_2, "cell 0 0");
		
		textFieldSaveName = new JTextField();
		panel_8.add(textFieldSaveName, "cell 0 1,growx");
		textFieldSaveName.setColumns(10);
		
		JPanel panel_9 = new JPanel();
		panelNewGameMode5.add(panel_9, BorderLayout.SOUTH);
		panel_9.setLayout(new MigLayout("", "[][grow][]", "[]"));
		
		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		});
		panel_9.add(btnNewButton, "cell 0 0");
		
		JButton btnNewButton_1 = new JButton("Finish");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String saveName = textFieldSaveName.getText();
				Character[] INVALID_WINDOWS_SPECIFIC_CHARS = {'"', '\'', '*', ':', '.', '<', '>', '?', '\\', '|', 0x7F};

				if (saveName.length() < 1) {
					JOptionPane.showMessageDialog(new JFrame(), "Please enter a name for this GameMode.");
					return;
				}
				for(Character c : INVALID_WINDOWS_SPECIFIC_CHARS) {
					if (saveName.contains(String.valueOf(c))) {
						JOptionPane.showMessageDialog(new JFrame(), "Invalid character detected.");
						return;
					}
				}
				
				if (SaveManager.doesGameModeExist(saveName)) {
					int input = JOptionPane.showConfirmDialog(new JFrame(), "This GameMode already exists! Are you sure you want to overwrite this file?", "WARNING!", JOptionPane.YES_NO_OPTION);
					if (input != 0) {
						return;
					}
				}
				SaveManager.saveGameMode(saveName);
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		});
		panel_9.add(btnNewButton_1, "cell 2 0,alignx right");
		
	}
	
	public static void deleteLastBDV() {
		if (bdValues.size() > 0) {
			bdValues.remove(bdValues.size()-1);
		}
	}
	
	public static void addComponent(Component comp) {
		componentsModel.addElement(comp.componentName);
		GameMode.getInstance().components.put(comp.componentName, comp);
	}
}
