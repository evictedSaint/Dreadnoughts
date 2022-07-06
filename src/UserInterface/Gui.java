package UserInterface;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;

import java.awt.Component;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import GameEngine.Game;
import GameEngine.GameMode;
import GameEngine.SaveManager;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JInternalFrame;
import java.awt.FlowLayout;
import javax.swing.Box;
import javax.swing.SpringLayout;
import javax.swing.JComboBox;

public class Gui {

	private JFrame frame;

	public Gui() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 479, 527);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		JPanel panelHome = new JPanel();
		frame.getContentPane().add(panelHome, "panelHome");
		GridBagLayout gbl_panelHome = new GridBagLayout();
		gbl_panelHome.columnWidths = new int[] {0};
		gbl_panelHome.rowHeights = new int[] {0};
		gbl_panelHome.columnWeights = new double[]{1.0};
		gbl_panelHome.rowWeights = new double[]{1.0};
		panelHome.setLayout(gbl_panelHome);
		
		JPanel panelHomeButtons = new JPanel();
		GridBagConstraints gbc_panelHomeButtons = new GridBagConstraints();
		gbc_panelHomeButtons.anchor = GridBagConstraints.NORTH;
		panelHome.add(panelHomeButtons, gbc_panelHomeButtons);
		GridBagLayout gbl_panelHomeButtons = new GridBagLayout();
		gbl_panelHomeButtons.columnWidths = new int[] {0};
		gbl_panelHomeButtons.rowHeights = new int[] {0, 0, 0, 0, 0};
		gbl_panelHomeButtons.columnWeights = new double[]{0.0};
		gbl_panelHomeButtons.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelHomeButtons.setLayout(gbl_panelHomeButtons);
		
		JButton btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SaveManager.loadGameMode("Dreadnoughts v1_0 GameMode");
			}
		});
		GridBagConstraints gbc_btnNewGame = new GridBagConstraints();
		gbc_btnNewGame.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewGame.insets = new Insets(0, 0, 5, 5);
		panelHomeButtons.add(btnNewGame, gbc_btnNewGame);
		
		JButton btnLoadGame = new JButton("Load Game");
		GridBagConstraints gbc_btnLoadGame = new GridBagConstraints();
		gbc_btnLoadGame.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnLoadGame.insets = new Insets(0, 0, 5, 5);
		gbc_btnLoadGame.gridx = 0;
		gbc_btnLoadGame.gridy = 1;
		panelHomeButtons.add(btnLoadGame, gbc_btnLoadGame);
		
		JButton btnNewGameMode = new JButton("New GameMode");
		btnNewGameMode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new GuiNewGameMode();
			}
		});
		GridBagConstraints gbc_btnNewGameMode = new GridBagConstraints();
		gbc_btnNewGameMode.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewGameMode.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewGameMode.gridx = 0;
		gbc_btnNewGameMode.gridy = 2;
		panelHomeButtons.add(btnNewGameMode, gbc_btnNewGameMode);
		
		JButton btnEditGameMode = new JButton("Edit GameMode");
		GridBagConstraints gbc_btnEditGameMode = new GridBagConstraints();
		gbc_btnEditGameMode.insets = new Insets(0, 0, 0, 5);
		gbc_btnEditGameMode.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnEditGameMode.gridx = 0;
		gbc_btnEditGameMode.gridy = 3;
		panelHomeButtons.add(btnEditGameMode, gbc_btnEditGameMode);
		
		//=======================
		
	}
}
