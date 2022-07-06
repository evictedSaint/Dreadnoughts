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

import ComponentClasses.DefenseRolling;
import GameEngine.Game;
import GameEngine.Game.Status;
import GameEngine.GameMode;

import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import net.miginfocom.swing.MigLayout;
import java.awt.Font;

public class GuiRollingDefenseWindow {

	private JFrame frame;
	private JTextField textRollReq;
	private Status statusName;
	private DefenseRolling dr;
	private JTextField textProjectileDamage;

	/**
	 * Create the application.
	 */
	public GuiRollingDefenseWindow(DefenseRolling dr, Status status) {
		this.statusName = status;
		this.dr = dr;
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
		
		JLabel lblNewLabel_3 = new JLabel("Component Status:");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 0;
		panel_4_.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		JLabel lblNewLabel_2 = new JLabel(statusName.toString());
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
		panel_3_.setLayout(new MigLayout("", "[0,grow][grow][grow]", "[20px][217px,grow][26px]"));
		
		JLabel lblNewLabel = new JLabel("Weapon Type");
		panel_3_.add(lblNewLabel, "cell 0 0,alignx center,aligny center");
		
		JLabel lblNewLabel_5 = new JLabel("DamageToProjectile");
		panel_3_.add(lblNewLabel_5, "cell 1 0");
		
		JLabel lblNewLabel_1 = new JLabel("Roll Required");
		panel_3_.add(lblNewLabel_1, "cell 2 0,alignx center,aligny center");

		DefaultListModel<String> weaponTypesModel = new DefaultListModel<>();
		JList listWeaponTypes = new JList(weaponTypesModel);
		for (String wt : GameMode.getInstance().weaponTypes) {
			weaponTypesModel.addElement(wt);
		}
		panel_3_.add(listWeaponTypes, "cell 0 1,grow");

		DefaultListModel<String> projDamModel = new DefaultListModel<>();
		JList listProjDam = new JList(projDamModel);
		panel_3_.add(listProjDam, "cell 1 1,grow");

		DefaultListModel<String> damageModel = new DefaultListModel<>();
		JList listDamage = new JList(damageModel);
		panel_3_.add(listDamage, "cell 2 1,grow");
		
		JLabel lblNewLabel_4 = new JLabel("(Note: High Roll=Worse Defense)");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_3_.add(lblNewLabel_4, "cell 0 2,alignx trailing,aligny center");
		
		textProjectileDamage = new JTextField();
		panel_3_.add(textProjectileDamage, "cell 1 2,growx");
		textProjectileDamage.setColumns(10);
		
		textRollReq = new JTextField();
		panel_3_.add(textRollReq, "cell 2 2,growx,aligny center");
		textRollReq.setColumns(10);
		
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
				if (damageModel.size() > 0 && projDamModel.size() > 0) {
					dr.projectileTypeRoll.get(statusName).put(weaponTypesModel.elementAt(damageModel.getSize()-1), 0);
					damageModel.remove(damageModel.size()-1);
					dr.projectileTypeDamage.get(statusName).put(weaponTypesModel.elementAt(projDamModel.getSize()-1), 0);
					projDamModel.remove(damageModel.size()-1);
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
					int rollReq = Integer.parseInt(textRollReq.getText());
					int damageToProj = Integer.parseInt(textProjectileDamage.getText());
					if (damageModel.size() < weaponTypesModel.size() || projDamModel.size() < weaponTypesModel.size()) {
						damageModel.addElement(textRollReq.getText());
						projDamModel.addElement(textProjectileDamage.getText());
						textRollReq.setText("");
						textProjectileDamage.setText("");
						dr.projectileTypeRoll.get(statusName).put(weaponTypesModel.elementAt(damageModel.getSize()-1), rollReq);
						dr.projectileTypeDamage.get(statusName).put(weaponTypesModel.elementAt(weaponTypesModel.getSize()-1), damageToProj);
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
					JOptionPane.showMessageDialog(new JFrame(), "Invalid roll values");
					return;
				}
				GuiCompDefenseRolling.updatePerformanceList();
				//not much else to do; everything should be handled (the dr we're modifying is from the earlier page).
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
