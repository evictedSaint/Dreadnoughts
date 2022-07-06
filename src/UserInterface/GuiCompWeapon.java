package UserInterface;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JLabel;
import java.awt.Insets;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JComboBox;
import net.miginfocom.swing.MigLayout;
import javax.swing.JList;
import javax.swing.JOptionPane;
import ComponentClasses.Weapon;
import GameEngine.Game;
import GameEngine.Game.Status;
import GameEngine.GameMode;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.CardLayout;
import java.awt.Component;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

public class GuiCompWeapon {

	private JFrame frmWeapon;
	private JTextField textFieldCompName;
	private final ButtonGroup buttonGroupDamage = new ButtonGroup();
	private final ButtonGroup buttonGroupRepairable = new ButtonGroup();
	private final ButtonGroup buttonGroupAugment = new ButtonGroup();
	private Weapon weap = new Weapon("");
	private DefaultTableModel speedModel = new DefaultTableModel();
	private DefaultTableModel toHitModel = new DefaultTableModel();
	private DefaultTableModel firedModel = new DefaultTableModel();

	
	private boolean speedSet = false;
	private boolean toHitSet = false;
	private boolean firedSet = false;

	/**
	 * Create the application.
	 */
	public GuiCompWeapon() {
		initialize();
		frmWeapon.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() { 
		frmWeapon = new JFrame();
		frmWeapon.setTitle("Weapon");
		frmWeapon.setBounds(100, 100, 505, 618);
		frmWeapon.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmWeapon.getContentPane().setLayout(new CardLayout(0, 0));
		
		JPanel panel_CV1 = new JPanel();
		frmWeapon.getContentPane().add(panel_CV1, "CV1");
		panel_CV1.setLayout(new MigLayout("", "[grow]", "[][][grow][bottom]"));
		
		JPanel panelCompValues = new JPanel();
		panel_CV1.add(panelCompValues, "cell 0 0,alignx left,aligny top");
		panelCompValues.setLayout(new MigLayout("", "[154px][315px]", "[26px][26px][29px][29px][29px]"));
		
		JLabel lblNewLabel = new JLabel("Component Name");
		panelCompValues.add(lblNewLabel, "cell 0 0,grow");
		
		textFieldCompName = new JTextField();
		textFieldCompName.setColumns(10);
		panelCompValues.add(textFieldCompName, "cell 1 0,grow");
		
		JLabel lblNewLabel_1 = new JLabel("Power Required");
		panelCompValues.add(lblNewLabel_1, "cell 0 1,grow");
		
		JSpinner spinnerPowerReq = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		panelCompValues.add(spinnerPowerReq, "cell 1 1,grow");
		
		JLabel lblNewLabel_2 = new JLabel("Damageable");
		panelCompValues.add(lblNewLabel_2, "cell 0 2,grow");
		
		JPanel panel = new JPanel();
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JRadioButton rdoDamageableTrue = new JRadioButton("True");
		rdoDamageableTrue.setSelected(true);
		buttonGroupDamage.add(rdoDamageableTrue);
		GridBagConstraints gbc_rdoDamageableTrue = new GridBagConstraints();
		gbc_rdoDamageableTrue.insets = new Insets(0, 0, 0, 5);
		gbc_rdoDamageableTrue.gridx = 0;
		gbc_rdoDamageableTrue.gridy = 0;
		panel.add(rdoDamageableTrue, gbc_rdoDamageableTrue);
		
		JRadioButton rdoDamageableFalse = new JRadioButton("False");
		buttonGroupDamage.add(rdoDamageableFalse);
		GridBagConstraints gbc_rdoDamageableFalse = new GridBagConstraints();
		gbc_rdoDamageableFalse.gridx = 1;
		gbc_rdoDamageableFalse.gridy = 0;
		panel.add(rdoDamageableFalse, gbc_rdoDamageableFalse);
		panelCompValues.add(panel, "cell 1 2,grow");
		
		JLabel lblNewLabel_3 = new JLabel("Repairable");
		panelCompValues.add(lblNewLabel_3, "cell 0 3,grow");
		
		JPanel panel_1 = new JPanel();
		panelCompValues.add(panel_1, "cell 1 3,grow");
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JRadioButton rdoRepairableTrue = new JRadioButton("True");
		rdoRepairableTrue.setSelected(true);
		buttonGroupRepairable.add(rdoRepairableTrue);
		GridBagConstraints gbc_rdoRepairableTrue = new GridBagConstraints();
		gbc_rdoRepairableTrue.insets = new Insets(0, 0, 0, 5);
		gbc_rdoRepairableTrue.gridx = 0;
		gbc_rdoRepairableTrue.gridy = 0;
		panel_1.add(rdoRepairableTrue, gbc_rdoRepairableTrue);
		
		JRadioButton rdoRepairableFalse = new JRadioButton("False");
		buttonGroupRepairable.add(rdoRepairableFalse);
		GridBagConstraints gbc_rdoRepairableFalse = new GridBagConstraints();
		gbc_rdoRepairableFalse.gridx = 1;
		gbc_rdoRepairableFalse.gridy = 0;
		panel_1.add(rdoRepairableFalse, gbc_rdoRepairableFalse);
		
		JLabel lblNewLabel_4 = new JLabel("Augment");
		panelCompValues.add(lblNewLabel_4, "cell 0 4,grow");
		
		JPanel panel_2 = new JPanel();
		panelCompValues.add(panel_2, "cell 1 4,grow");
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 0, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JRadioButton rdoAugmentTrue = new JRadioButton("True");
		buttonGroupAugment.add(rdoAugmentTrue);
		GridBagConstraints gbc_rdoAugmentTrue = new GridBagConstraints();
		gbc_rdoAugmentTrue.insets = new Insets(0, 0, 0, 5);
		gbc_rdoAugmentTrue.gridx = 0;
		gbc_rdoAugmentTrue.gridy = 0;
		panel_2.add(rdoAugmentTrue, gbc_rdoAugmentTrue);
		
		JRadioButton rdoAugmentFalse = new JRadioButton("False");
		rdoAugmentFalse.setSelected(true);
		buttonGroupAugment.add(rdoAugmentFalse);
		GridBagConstraints gbc_rdoAugmentFalse = new GridBagConstraints();
		gbc_rdoAugmentFalse.gridx = 1;
		gbc_rdoAugmentFalse.gridy = 0;
		panel_2.add(rdoAugmentFalse, gbc_rdoAugmentFalse);
		
		JPanel panel_Cost_Factions = new JPanel();
		panel_CV1.add(panel_Cost_Factions, "cell 0 1,grow");
		panel_Cost_Factions.setLayout(new MigLayout("", "[][50px:n][grow]", "[grow][][]"));
		
		JLabel lblNewLabel_11 = new JLabel("C:");
		panel_Cost_Factions.add(lblNewLabel_11, "cell 0 0");
		
		JSpinner spinner_C = new JSpinner();
		panel_Cost_Factions.add(spinner_C, "cell 1 0,grow");
		
		JList<?> list_factions = new JList<Object>(GameMode.getInstance().factions.keySet().toArray());
		list_factions.setSelectionModel(new DefaultListSelectionModel() {
			private int i0 = -1;
			private int i1 = -1;

			@Override
			public void setSelectionInterval(int index0, int index1) {
				if (i0 == index0 && i1 == index1) {
					if (getValueIsAdjusting()) {
						setValueIsAdjusting(false);
						setSelection(index0, index1);
					}
				} else {
					i0 = index0;
					i1 = index1;
					setValueIsAdjusting(false);
					setSelection(index0, index1);
				}
			}

			private void setSelection(int index0, int index1) {
				if (super.isSelectedIndex(index0)) {
					super.removeSelectionInterval(index0, index1);
				} else {
					super.addSelectionInterval(index0, index1);
				}
			}
		});
		panel_Cost_Factions.add(list_factions, "cell 2 0 1 3,grow");
		
		JLabel lblNewLabel_10 = new JLabel("E:");
		panel_Cost_Factions.add(lblNewLabel_10, "cell 0 1");
		
		JSpinner spinner_E = new JSpinner();
		panel_Cost_Factions.add(spinner_E, "cell 1 1,grow");
		
		JLabel lblNewLabel_9 = new JLabel("X:");
		panel_Cost_Factions.add(lblNewLabel_9, "cell 0 2");
		
		JSpinner spinner_X = new JSpinner();
		panel_Cost_Factions.add(spinner_X, "cell 1 2,grow");
		
		JPanel panel_3 = new JPanel();
		panel_CV1.add(panel_3, "cell 0 2,grow");
		panel_3.setLayout(new MigLayout("", "[grow]", "[][grow]"));
		
		JPanel panel_5 = new JPanel();
		panel_3.add(panel_5, "cell 0 0,growx,aligny top");
		panel_5.setLayout(new MigLayout("", "[][grow]", "[][][]"));
		
		JLabel lblNewLabel_5 = new JLabel("Weapon Type");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.LEFT);
		panel_5.add(lblNewLabel_5, "cell 0 0");
		
		JComboBox<String> comboBox = new JComboBox<String>();
		panel_5.add(comboBox, "cell 1 0,growx");
		for (String weapType : GameMode.getInstance().weaponTypes) {
			comboBox.addItem(weapType);
		}
		
		JLabel lblNewLabel_12 = new JLabel("Projectile Health");
		panel_5.add(lblNewLabel_12, "cell 0 1");
		
		JSpinner spinnerHealth = new JSpinner();
		panel_5.add(spinnerHealth, "cell 1 1,growx");
		
		JLabel lblNewLabel_13 = new JLabel("Overkill");
		panel_5.add(lblNewLabel_13, "cell 0 2");
		
		JSpinner spinnerOverkill = new JSpinner();
		panel_5.add(spinnerOverkill, "cell 1 2,growx");
		
		JPanel panel_4 = new JPanel();
		panel_3.add(panel_4, "cell 0 1,grow");
		panel_4.setLayout(new MigLayout("", "[][grow][grow]", "[][][]"));
		
		JButton btnSpeed = new JButton("Set Speed");
		btnSpeed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
                BuildSpeedTable();
                CardLayout cl = (CardLayout)(frmWeapon.getContentPane().getLayout());
                cl.show(frmWeapon.getContentPane(), "CV2");  
                speedSet = true;
			}
		});
		panel_4.add(btnSpeed, "cell 0 0,growx");
		
		JButton btnHitChance = new JButton("Set Hit Chance"); 
		btnHitChance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                BuildToHitTable();
                CardLayout cl = (CardLayout)(frmWeapon.getContentPane().getLayout());
                cl.show(frmWeapon.getContentPane(), "CV3");  
                toHitSet = true;
			}
		});
		panel_4.add(btnHitChance, "cell 0 1,growx");
		
		JButton btnShotsFired = new JButton("Set Shots Fired");
		btnShotsFired.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout)(frmWeapon.getContentPane().getLayout());
                cl.show(frmWeapon.getContentPane(), "CV4");  
                BuildFiredTable();
                firedSet = true;
			}
		});
		panel_4.add(btnShotsFired, "cell 0 2");
		
		JPanel panel_6 = new JPanel();
		panel_CV1.add(panel_6, "cell 0 3,growx");
		panel_6.setLayout(new BorderLayout(0, 0));
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmWeapon.dispatchEvent(new WindowEvent(frmWeapon, WindowEvent.WINDOW_CLOSING));
			}
		});
		panel_6.add(btnNewButton_1, BorderLayout.WEST);
		
		JButton btnNewButton_2 = new JButton("Finish");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textFieldCompName.getText().trim().length() < 1 || GameMode.getInstance().components.containsKey(textFieldCompName.getText().trim())) {
					JOptionPane.showMessageDialog(new JFrame(), "Invalid name");
					return;
				}
				if (comboBox.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(new JFrame(), "No Weapon Type selected");
					return;
				}

				if (list_factions.getSelectedValuesList().size() == 0) {
					int input = JOptionPane.showConfirmDialog(new JFrame(), "No factions selected! No one owns this component.\nContinue?", "WARNING!", JOptionPane.YES_NO_OPTION);
					if (input != 0) {
						return;
					}
				}
				
				if (!speedSet) {
					int input = JOptionPane.showConfirmDialog(new JFrame(), "Weapon 'Speed' has not been changed from default.\nContinue?", "WARNING!", JOptionPane.YES_NO_OPTION);
					if (input != 0) {
						return;
					}
				}
				if (!toHitSet) {
					int input = JOptionPane.showConfirmDialog(new JFrame(), "Weapon 'Hit Chance' has not been changed from default.\nContinue?", "WARNING!", JOptionPane.YES_NO_OPTION);
					if (input != 0) {
						return;
					}
				}
				if (!firedSet) {
					int input = JOptionPane.showConfirmDialog(new JFrame(), "Weapon 'Shots Fired' has not been changed from default.\nContinue?", "WARNING!", JOptionPane.YES_NO_OPTION);
					if (input != 0) {
						return;
					}
				}
				
				//make the component, inherited values first
				weap.componentName = textFieldCompName.getText().trim();
				try {spinnerPowerReq.commitEdit();} catch (ParseException e1) {	} //if they manually enter the value rather than using the up/down arrows, this makes sure we get it.
				weap.powerRequirement = (int) spinnerPowerReq.getValue(); //wrapped in an Object type, so we need to cast it
				weap.canBeDamaged = rdoDamageableTrue.isSelected();
				weap.repairable = rdoRepairableTrue.isSelected();
				weap.augmentSlot = rdoAugmentTrue.isSelected();
				weap.commons = (int) spinner_C.getValue();
				weap.energetics = (int) spinner_E.getValue();
				weap.xotics = (int) spinner_X.getValue();
				weap.owningFactions = (List<String>) list_factions.getSelectedValuesList();
				
				
				
				//Weapon specific values next...
				weap.weaponType = (String) comboBox.getSelectedItem();
				weap.projectileHealth = (int) spinnerHealth.getValue(); 
				weap.overkill = (int) spinnerOverkill.getValue();
				//Speed, toHit, and Fired should be set by their respective buttons, so we don't need to do it here.
				
				GuiNewGameMode.addComponent(weap);

				frmWeapon.dispatchEvent(new WindowEvent(frmWeapon, WindowEvent.WINDOW_CLOSING));
			}
		});
		panel_6.add(btnNewButton_2, BorderLayout.EAST);
		
		
		//====================================================================================
		//Card View 2
		//====================================================================================
		JPanel panel_CV2 = new JPanel();
		frmWeapon.getContentPane().add(panel_CV2, "CV2");
		panel_CV2.setLayout(new MigLayout("", "[grow]", "[][][grow][]"));
		
		JLabel lblNewLabel_6 = new JLabel("Turns Until Projectile Hits");
		panel_CV2.add(lblNewLabel_6, "cell 0 0");
		
		JLabel lblNewLabel_7 = new JLabel("NOTE: \"0\" hits instantly, empty means weapon can't fire in those conditions");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_CV2.add(lblNewLabel_7, "cell 0 1");
		

		JPanel panel_8_Speed = new JPanel();
		panel_CV2.add(panel_8_Speed, "cell 0 2,grow");
		
		JTable jTable1 = new JTable() {
			//These overrides hack together a row header that can't be edited.  For some reason this isn't a default option in swing. ¯\_(ツ)_/¯
			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
				if (col == 0) {
					return this.getTableHeader().getDefaultRenderer().getTableCellRendererComponent(this,
							this.getValueAt(row, col), false, false, row, col);
				} else {
					return super.prepareRenderer(renderer, row, col);
				}
			}
			@Override
		    public boolean isCellEditable(int row, int column) {
		        return column != 0 ? true : false;
		    }
		};
		
		jTable1.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 10));
	    jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	    jTable1.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
		jTable1.getTableHeader().setReorderingAllowed(false);
		jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jTable1.getColumnModel().getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jTable1.setRowSelectionAllowed(false);
		jTable1.setColumnSelectionAllowed(false);
		jTable1.setCellSelectionEnabled(true);
		jTable1.setAutoCreateRowSorter(false);
		jTable1.setModel(speedModel);
		panel_8_Speed.setLayout(new MigLayout("", "[grow]", "[grow]"));
		JScrollPane scrollPane = new JScrollPane(jTable1);
		panel_8_Speed.add(scrollPane, "cell 0 0,grow");
		
		JPanel panel_7 = new JPanel();
		panel_CV2.add(panel_7, "cell 0 3,growx,aligny bottom");
		panel_7.setLayout(new MigLayout("", "[][grow][]", "[]"));
		
		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout)(frmWeapon.getContentPane().getLayout());
                cl.show(frmWeapon.getContentPane(), "CV1");  
			}
		});
		panel_7.add(btnNewButton, "cell 0 0,aligny bottom");
		
		JButton btnNewButton_3 = new JButton("Finish");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				jTable1.getColumnCount();
				jTable1.getRowCount();
				
				for (int i = 0; i < jTable1.getRowCount(); i++) {
					for (int j = 1; j < jTable1.getColumnCount(); j++) { //start at 1 because the first column is really our row header 
						try {
							weap.speed.get(Game.Status.valueOf((String) jTable1.getValueAt(i,0))).put(Integer.valueOf(j), Integer.valueOf((String) jTable1.getValueAt(i, j)));
						}
						catch(Exception exception) {
							//do nothing, couldn't convert the value to an int either because it's null, or the input characters instead of numbers.
						}
					}
				}				
                CardLayout cl = (CardLayout)(frmWeapon.getContentPane().getLayout());
                cl.show(frmWeapon.getContentPane(), "CV1");  
			}
		});
		panel_7.add(btnNewButton_3, "cell 2 0");
		
		

		//====================================================================================
		//Card View 3
		//====================================================================================
		JPanel panel_CV3 = new JPanel();
		frmWeapon.getContentPane().add(panel_CV3, "CV3");
		
		panel_CV3.setLayout(new MigLayout("", "[grow]", "[][][grow][]"));
		
		JLabel lblNewLabel_6_1 = new JLabel("Minimum Die Roll Needed to Hit");
		panel_CV3.add(lblNewLabel_6_1, "cell 0 0");
		
		JLabel lblNewLabel_7_1 = new JLabel("NOTES: meets beats, die is 2d6, empty means weapon can't fire in those conditions");
		lblNewLabel_7_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_CV3.add(lblNewLabel_7_1, "cell 0 1");
		

		JPanel panel_8_ToHit = new JPanel();
		panel_CV3.add(panel_8_ToHit, "cell 0 2,grow");
		
		JTable jTable2 = new JTable() {
			//These overrides hack together a row header that can't be edited.  For some reason this isn't a default option in swing. ¯\_(ツ)_/¯
			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
				if (col == 0) {
					return this.getTableHeader().getDefaultRenderer().getTableCellRendererComponent(this,
							this.getValueAt(row, col), false, false, row, col);
				} else {
					return super.prepareRenderer(renderer, row, col);
				}
			}
			@Override
		    public boolean isCellEditable(int row, int column) {
		        return column != 0 ? true : false;
		    }
		};
		
		jTable2.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 10));
	    jTable2.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	    jTable2.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
		jTable2.getTableHeader().setReorderingAllowed(false);
		jTable2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jTable2.getColumnModel().getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jTable2.setRowSelectionAllowed(false);
		jTable2.setColumnSelectionAllowed(false);
		jTable2.setCellSelectionEnabled(true);
		jTable2.setAutoCreateRowSorter(false);
		jTable2.setModel(toHitModel);
		panel_8_ToHit.setLayout(new MigLayout("", "[grow]", "[grow]"));
		JScrollPane scrollPane_1 = new JScrollPane(jTable2);
		panel_8_ToHit.add(scrollPane_1, "cell 0 0,grow");
		
		JPanel panel_7_1 = new JPanel();
		panel_CV3.add(panel_7_1, "cell 0 3,growx,aligny bottom");
		panel_7_1.setLayout(new MigLayout("", "[][grow][]", "[]"));
		
		JButton btnNewButton__1 = new JButton("Cancel");
		btnNewButton__1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout)(frmWeapon.getContentPane().getLayout());
                cl.show(frmWeapon.getContentPane(), "CV1");  
			}
		});
		panel_7_1.add(btnNewButton__1, "cell 0 0,aligny bottom");
		
		JButton btnNewButton_3_1 = new JButton("Finish");
		btnNewButton_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				jTable2.getColumnCount();
				jTable2.getRowCount();
				
				for (int i = 0; i < jTable2.getRowCount(); i++) {
					for (int j = 1; j < jTable2.getColumnCount(); j++) { //start at 1 because the first column is really our row header 
						try {
							weap.hitChance.get(Game.Status.valueOf((String) jTable2.getValueAt(i,0))).put(Integer.valueOf(j), Integer.valueOf((String) jTable2.getValueAt(i, j)));
						}
						catch(Exception exception) {
							//do nothing, couldn't convert the value to an int either because it's null, or the input characters instead of numbers.
						}
					}
				}				
                CardLayout cl = (CardLayout)(frmWeapon.getContentPane().getLayout());
                cl.show(frmWeapon.getContentPane(), "CV1");  
			}
		});
		panel_7_1.add(btnNewButton_3_1, "cell 2 0");

		//====================================================================================
		//Card View 4
		//====================================================================================
		JPanel panel_CV4 = new JPanel();
		frmWeapon.getContentPane().add(panel_CV4, "CV4");
				
		panel_CV4.setLayout(new MigLayout("", "[grow]", "[][][grow][]"));
		
		JLabel lblNewLabel_6_2 = new JLabel("Number of Shots Fired Each Turn");
		panel_CV4.add(lblNewLabel_6_2, "cell 0 0");
		
		JLabel lblNewLabel_7_2 = new JLabel("NOTES: 0 or empty means weapon can't fire in those conditions");
		lblNewLabel_7_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_CV4.add(lblNewLabel_7_2, "cell 0 1");
		

		JPanel panel_8_Fired = new JPanel();
		panel_CV4.add(panel_8_Fired, "cell 0 2,grow");
		
		JTable jTable3 = new JTable() {
			//These overrides hack together a row header that can't be edited.  For some reason this isn't a default option in swing. ¯\_(ツ)_/¯
			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
				if (col == 0) {
					return this.getTableHeader().getDefaultRenderer().getTableCellRendererComponent(this,
							this.getValueAt(row, col), false, false, row, col);
				} else {
					return super.prepareRenderer(renderer, row, col);
				}
			}
			@Override
		    public boolean isCellEditable(int row, int column) {
		        return column != 0 ? true : false;
		    }
		};
		
		jTable3.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 10));
	    jTable3.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	    jTable3.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
		jTable3.getTableHeader().setReorderingAllowed(false);
		jTable3.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jTable3.getColumnModel().getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jTable3.setRowSelectionAllowed(false);
		jTable3.setColumnSelectionAllowed(false);
		jTable3.setCellSelectionEnabled(true);
		jTable3.setAutoCreateRowSorter(false);
		jTable3.setModel(firedModel);
		panel_8_Fired.setLayout(new MigLayout("", "[grow]", "[grow]"));
		JScrollPane scrollPane_2 = new JScrollPane(jTable3);
		panel_8_Fired.add(scrollPane_2, "cell 0 0,grow");
		
		JPanel panel_7_2 = new JPanel();
		panel_CV4.add(panel_7_2, "cell 0 3,growx,aligny bottom");
		panel_7_2.setLayout(new MigLayout("", "[][grow][]", "[]"));
		
		JButton btnNewButton__2 = new JButton("Cancel");
		btnNewButton__2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout)(frmWeapon.getContentPane().getLayout());
                cl.show(frmWeapon.getContentPane(), "CV1");  
			}
		});
		panel_7_2.add(btnNewButton__2, "cell 0 0,aligny bottom");
		
		JButton btnNewButton_3_2 = new JButton("Finish");
		btnNewButton_3_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				jTable3.getColumnCount();
				jTable3.getRowCount();
				
				for (int i = 0; i < jTable3.getRowCount(); i++) {
					for (int j = 1; j < jTable3.getColumnCount(); j++) { //start at 1 because the first column is really our row header 
						try {
							//public HashMap<Status, Integer> projectilesFired = new HashMap<Status, Integer>(); //amount of projectiles this gun fires per salvo
							weap.projectilesFired.put(Game.Status.valueOf((String) jTable3.getValueAt(i,0)), Integer.valueOf((String) jTable3.getValueAt(i, j)));
						}
						catch(Exception exception) {
							weap.projectilesFired.put(Game.Status.valueOf((String) jTable3.getValueAt(i,0)), 0); //just slap a zero there.  I know, I know, lazy error handling.
						}
					}
				}				
                CardLayout cl = (CardLayout)(frmWeapon.getContentPane().getLayout());
                cl.show(frmWeapon.getContentPane(), "CV1");  
			}
		});
		panel_7_2.add(btnNewButton_3_2, "cell 2 0");
		
	}
	
	
	

	private void BuildSpeedTable() {
		//Making the table's header
		//There's gotta be a more efficient way to do this, but I've cut my loses and given up.  
		List<String> tempArray = new ArrayList<String>();
		tempArray.add(""); //first column is blank
		for (int i = 0; i < GameMode.getInstance().distances.size(); i++) { //adding all the distances...
			tempArray.add(GameMode.getInstance().distances.get(i));
		}
		String[] header = tempArray.toArray(new String[tempArray.size()]);
		
		
		//Now to create the body
		int size = Game.Status.values().length;
		String[][] body = new String[size][header.length];
		for (int i = 0; i < size; i++) { //status (rows)
			for (int j = 0; j < header.length; j++) { //distance (columns)
				if (j == 0) {
					body[i][j] = Game.Status.values()[i].toString();
				}
				else {
					 //don't judge me
					if (weap.speed.get(Game.Status.values()[i]).get(j) == null) {
						body[i][j] = "";
					}
					else {
						body[i][j] = weap.speed.get(Game.Status.values()[i]).get(j).toString(); 
					}
				}
			}
		}
		speedModel.setDataVector(body, header);
	}
	
	
	private void BuildToHitTable() {
		//Making the table's header
		//There's gotta be a more efficient way to do this, but I've cut my loses and given up.  
		List<String> tempArray = new ArrayList<String>();
		tempArray.add(""); //first column is blank
		for (int i = 0; i < GameMode.getInstance().distances.size(); i++) { //adding all the distances...
			tempArray.add(GameMode.getInstance().distances.get(i));
		}
		String[] header = tempArray.toArray(new String[tempArray.size()]);
		
		
		//Now to create the body
		int size = Game.Status.values().length;
		String[][] body = new String[size][header.length];
		for (int i = 0; i < size; i++) { //status (rows)
			for (int j = 0; j < header.length; j++) { //distance (columns)
				if (j == 0) {
					body[i][j] = Game.Status.values()[i].toString();
				}
				else {
					 //don't judge me
					if (weap.hitChance.get(Game.Status.values()[i]).get(j) == null) {
						body[i][j] = "";
					}
					else {
						body[i][j] = weap.hitChance.get(Game.Status.values()[i]).get(j).toString(); 
					}
				}
			}
		}
		toHitModel.setDataVector(body, header);
	}
	
	private void BuildFiredTable() {
		// Making the table's header
		// There's gotta be a more efficient way to do this, but I've cut my loses and
		// given up.

		// Now to create the body
		int size = Game.Status.values().length;
		String[][] body = new String[size][2];
		for (int i = 0; i < size; i++) { // status (rows)
			for (int j = 0; j < 2; j++) { // distance (columns)
				if (j == 0) {
					body[i][j] = Game.Status.values()[i].toString();
				} else {
					// don't judge me
					if (weap.projectilesFired.get(Game.Status.values()[i]) == null) {
						body[i][j] = "";
					} else {
						body[i][j] = weap.projectilesFired.get(Game.Status.values()[i]).toString();
					}
				}
			}
		}
		String[] header = {"",""};
		firedModel.setDataVector(body, header);
	}
}
