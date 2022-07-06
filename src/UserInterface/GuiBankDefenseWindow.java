package UserInterface;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JPanel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTextField;

import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class GuiBankDefenseWindow {

	private JFrame frame;
	private JTextField textFieldDamage;
	private String bdName;
	private BankDefenseValues bdv;

	/**
	 * Create the application.
	 */
	public GuiBankDefenseWindow(BankDefenseValues tempBDV) {
		this.bdName = tempBDV.name;
		this.bdv = tempBDV;
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 455);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{40, 207, 47, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JPanel panel_4_ = new JPanel();
		GridBagConstraints gbc_panel_4_ = new GridBagConstraints();
		gbc_panel_4_.insets = new Insets(0, 0, 5, 0);
		gbc_panel_4_.fill = GridBagConstraints.BOTH;
		gbc_panel_4_.gridx = 0;
		gbc_panel_4_.gridy = 0;
		frame.getContentPane().add(panel_4_, gbc_panel_4_);
		GridBagLayout gbl_panel_4_ = new GridBagLayout();
		gbl_panel_4_.columnWidths = new int[]{0, 0, 0};
		gbl_panel_4_.rowHeights = new int[]{0, 0};
		gbl_panel_4_.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_4_.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_4_.setLayout(gbl_panel_4_);
		
		JLabel lblNewLabel_3 = new JLabel("Bank Type Name:");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 0;
		panel_4_.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		JLabel lblNewLabel_2 = new JLabel(bdName);
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 0;
		panel_4_.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		JPanel panel_3_ = new JPanel();
		GridBagConstraints gbc_panel_3_ = new GridBagConstraints();
		gbc_panel_3_.insets = new Insets(0, 0, 5, 0);
		gbc_panel_3_.fill = GridBagConstraints.BOTH;
		gbc_panel_3_.gridx = 0;
		gbc_panel_3_.gridy = 1;
		frame.getContentPane().add(panel_3_, gbc_panel_3_);
		GridBagLayout gbl_panel_3_ = new GridBagLayout();
		gbl_panel_3_.columnWidths = new int[]{261, 0, 0};
		gbl_panel_3_.rowHeights = new int[]{0, 179, 44, 0};
		gbl_panel_3_.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_3_.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		panel_3_.setLayout(gbl_panel_3_);
		
		JLabel lblNewLabel = new JLabel("Weapon Type");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panel_3_.add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Damage to Projectile");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 0;
		panel_3_.add(lblNewLabel_1, gbc_lblNewLabel_1);

		DefaultListModel<String> weaponTypesModel = new DefaultListModel<>();
		JList listWeaponTypes = new JList(weaponTypesModel);
		for (String wt : bdv.weaponTypesDamage.keySet()) {
			weaponTypesModel.addElement(wt);
		}
		GridBagConstraints gbc_listWeaponTypes = new GridBagConstraints();
		gbc_listWeaponTypes.insets = new Insets(0, 0, 5, 5);
		gbc_listWeaponTypes.fill = GridBagConstraints.BOTH;
		gbc_listWeaponTypes.gridx = 0;
		gbc_listWeaponTypes.gridy = 1;
		panel_3_.add(listWeaponTypes, gbc_listWeaponTypes);

		DefaultListModel<String> damageModel = new DefaultListModel<>();
		JList listDamage = new JList(damageModel);
		GridBagConstraints gbc_listDamage = new GridBagConstraints();
		gbc_listDamage.insets = new Insets(0, 0, 5, 0);
		gbc_listDamage.fill = GridBagConstraints.BOTH;
		gbc_listDamage.gridx = 1;
		gbc_listDamage.gridy = 1;
		panel_3_.add(listDamage, gbc_listDamage);
		
		JLabel lblNewLabel_4 = new JLabel("(Note: High=Better Defense)");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_4.gridx = 0;
		gbc_lblNewLabel_4.gridy = 2;
		panel_3_.add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		textFieldDamage = new JTextField();
		GridBagConstraints gbc_textFieldDamage = new GridBagConstraints();
		gbc_textFieldDamage.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldDamage.gridx = 1;
		gbc_textFieldDamage.gridy = 2;
		panel_3_.add(textFieldDamage, gbc_textFieldDamage);
		textFieldDamage.setColumns(10);
		
		JPanel panel_ = new JPanel();
		GridBagConstraints gbc_panel_ = new GridBagConstraints();
		gbc_panel_.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_.gridx = 0;
		gbc_panel_.gridy = 2;
		frame.getContentPane().add(panel_, gbc_panel_);
		GridBagLayout gbl_panel_ = new GridBagLayout();
		gbl_panel_.columnWidths = new int[]{0, 0, 0};
		gbl_panel_.rowHeights = new int[] {0, 0};
		gbl_panel_.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_.rowWeights = new double[]{0.0, 0.0};
		panel_.setLayout(gbl_panel_);
		
		JButton btnNewButton = new JButton("Delete Last");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (damageModel.size() > 0) {
					damageModel.remove(damageModel.size()-1);
				}
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 0;
		panel_.add(btnNewButton, gbc_btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Add Value");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int damage = Integer.parseInt(textFieldDamage.getText());
					if (damageModel.size() < weaponTypesModel.size()) {
						damageModel.addElement(textFieldDamage.getText());
						textFieldDamage.setText("");
						bdv.weaponTypesDamage.put(weaponTypesModel.get(damageModel.size()-1), damage);
					}
				}
				catch (Exception exception) {
					//just don't allow it.  if i get around to it maybe I'll add fancy error handling here. TODO: fancy error handling.
				}
			}
		});
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.anchor = GridBagConstraints.EAST;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_1.gridx = 1;
		gbc_btnNewButton_1.gridy = 0;
		panel_.add(btnNewButton_1, gbc_btnNewButton_1);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GuiNewGameMode.deleteLastBDV(); //abort this bdv, delete the tempbdv that got added and close the window
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		});
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.anchor = GridBagConstraints.WEST;
		gbc_btnCancel.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancel.gridx = 0;
		gbc_btnCancel.gridy = 1;
		panel_.add(btnCancel, gbc_btnCancel);
		
		JButton btnFinish = new JButton("Finish");
		btnFinish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (weaponTypesModel.size() != damageModel.size()) {
					JOptionPane.showMessageDialog(new JFrame(), "Invalid damage values");
					return;
				}
				//not much else to do; everything should be handled (the bdv we're modifying is from the earlier page).
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		});
		GridBagConstraints gbc_btnFinish = new GridBagConstraints();
		gbc_btnFinish.anchor = GridBagConstraints.EAST;
		gbc_btnFinish.gridx = 1;
		gbc_btnFinish.gridy = 1;
		panel_.add(btnFinish, gbc_btnFinish);
	}

}
