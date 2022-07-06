package UserInterface;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.JLabel;
import java.awt.Insets;
import javax.swing.JSpinner;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JComboBox;
import net.miginfocom.swing.MigLayout;
import javax.swing.JList;
import javax.swing.JOptionPane;

import ComponentClasses.DefenseBank;
import ComponentClasses.JumpDrive;
import GameEngine.Game;
import GameEngine.Game.Status;
import GameEngine.GameBoard;
import GameEngine.GameMode;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class GuiCompJumpDrive {

	private JFrame frmJumpDrive;
	private JTextField textFieldCompName;
	private final ButtonGroup buttonGroupDamage = new ButtonGroup();
	private final ButtonGroup buttonGroupRepairable = new ButtonGroup();
	private final ButtonGroup buttonGroupAugment = new ButtonGroup();
	private JTextField textField_JumpTime;
	private JTextField textField_JumpDistance;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public GuiCompJumpDrive() {
		initialize();
		frmJumpDrive.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() { //TODO: all of these need a price at some point
		frmJumpDrive = new JFrame();
		frmJumpDrive.setTitle("Jump Drive");
		//frmBankDefense.setAlwaysOnTop(true);
		frmJumpDrive.setBounds(100, 100, 505, 782);
		frmJumpDrive.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmJumpDrive.getContentPane().setLayout(new MigLayout("", "[grow]", "[][][grow][bottom]"));
		
		JPanel panelCompValues = new JPanel();
		frmJumpDrive.getContentPane().add(panelCompValues, "cell 0 0,alignx left,aligny top");
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
		frmJumpDrive.getContentPane().add(panel_Cost_Factions, "cell 0 1,grow");
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
		frmJumpDrive.getContentPane().add(panel_3, "cell 0 2,grow");
		panel_3.setLayout(new MigLayout("", "[grow]", "[grow]"));
				
		JPanel panel_4 = new JPanel();
		panel_3.add(panel_4, "cell 0 0,grow");
		panel_4.setLayout(new MigLayout("", "[grow][grow][grow]", "[][grow][]"));
		
		JLabel lblNewLabel_6 = new JLabel("Comp. Status");
		panel_4.add(lblNewLabel_6, "cell 0 0,alignx center");
		
		JLabel lblNewLabel_7 = new JLabel("Jump Time");
		panel_4.add(lblNewLabel_7, "cell 1 0,alignx center");
		
		JLabel lblNewLabel_8 = new JLabel("Jump Distance");
		panel_4.add(lblNewLabel_8, "cell 2 0,alignx center");
		
		JList<?> list_Status = new JList<Object>(Game.Status.values());
		list_Status.setEnabled(false);
		panel_4.add(list_Status, "cell 0 1,grow");
		
		DefaultListModel<Integer> timeModel = new DefaultListModel<>();
		JList<Integer> list_Time = new JList<Integer>(timeModel);
		list_Time.setEnabled(false);
		panel_4.add(list_Time, "cell 1 1,grow");

		DefaultListModel<Integer> distanceModel = new DefaultListModel<>();
		JList<Integer> list_Distance = new JList<Integer>(distanceModel);
		list_Distance.setEnabled(false);
		panel_4.add(list_Distance, "cell 2 1,grow");
		
		JButton btnNewButton = new JButton("Add Values");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int time = Integer.parseInt(textField_JumpTime.getText());
					int distance = Integer.parseInt(textField_JumpDistance.getText());
					
					if (distanceModel.size() < list_Status.getModel().getSize() && timeModel.size() < list_Status.getModel().getSize()) {
						distanceModel.addElement(distance);
						timeModel.addElement(time);
					}
					textField_JumpTime.setText("");
					textField_JumpDistance.setText("");
				}
				catch (Exception e) {
					//nothing happens, they didn't input an int.
				}
			}
		});
		
		JButton btnNewButton_3 = new JButton("Remove");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (timeModel.size() > 1 && distanceModel.size() > 1) {
					timeModel.remove(timeModel.size()-1);
					distanceModel.remove(distanceModel.size()-1);
				}
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_4.add(btnNewButton_3, "flowx,cell 0 2");
		panel_4.add(btnNewButton, "cell 0 2");
		
		textField_JumpTime = new JTextField();
		panel_4.add(textField_JumpTime, "cell 1 2,growx");
		textField_JumpTime.setColumns(10);
		
		textField_JumpDistance = new JTextField();
		panel_4.add(textField_JumpDistance, "cell 2 2,growx");
		textField_JumpDistance.setColumns(10);
		
		JPanel panel_6 = new JPanel();
		frmJumpDrive.getContentPane().add(panel_6, "cell 0 3,growx");
		panel_6.setLayout(new BorderLayout(0, 0));
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmJumpDrive.dispatchEvent(new WindowEvent(frmJumpDrive, WindowEvent.WINDOW_CLOSING));
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

				if (list_Status.getModel().getSize() != timeModel.size() || list_Status.getModel().getSize() != distanceModel.size()) {
					JOptionPane.showMessageDialog(new JFrame(), "Invalid Time and Distance values");
					return;
				}
				if (list_factions.getSelectedValuesList().size() == 0) {
					int input = JOptionPane.showConfirmDialog(new JFrame(), "No factions selected! No one owns this component.\nContinue?", "WARNING!", JOptionPane.YES_NO_OPTION);
					if (input != 0) {
						return;
					}
				}
				
				//make the component, inherited values first
				JumpDrive jd = new JumpDrive(textFieldCompName.getText().trim());
				try {spinnerPowerReq.commitEdit();} catch (ParseException e1) {	} //if they manually enter the value rather than using the up/down arrows, this makes sure we get it.
				jd.powerRequirement = (int) spinnerPowerReq.getValue(); //wrapped in an Object type, so we need to cast it
				jd.canBeDamaged = rdoDamageableTrue.isSelected();
				jd.repairable = rdoRepairableTrue.isSelected();
				jd.augmentSlot = rdoAugmentTrue.isSelected();
				jd.commons = (int) spinner_C.getValue();
				jd.energetics = (int) spinner_E.getValue();
				jd.xotics = (int) spinner_X.getValue();
				jd.owningFactions = (List<String>) list_factions.getSelectedValuesList();
				
				
				
				//jumpDrive specific values next... 
				for (int i = 0; i < list_Status.getModel().getSize(); i++) {
					jd.jumpTime.put((Status) list_Status.getModel().getElementAt(i), timeModel.getElementAt(i));
					jd.jumpDistance.put((Status) list_Status.getModel().getElementAt(i), distanceModel.getElementAt(i));
				}
				
				GuiNewGameMode.addComponent(jd);

				frmJumpDrive.dispatchEvent(new WindowEvent(frmJumpDrive, WindowEvent.WINDOW_CLOSING));
			}
		});
		panel_6.add(btnNewButton_2, BorderLayout.EAST);
		
	}

}
