package UserInterface;

import java.awt.*;
import java.awt.event.*;
import java.awt.BorderLayout;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import GameEngine.Game;

import javax.swing.*;

public class Interface {
	/** Please note that this is my first time implementing Swing, or really any
	 * dedicated java gui.  If this is written poorly, know that I'm doing my best.
	 * 
	 */
	
	static JPanel cards; //a panel that uses CardLayout
    final static String HOMEPANEL = "Card with Home Panel";
    final static String NEWGAMEMODEPANEL = "Card with New Game Mode";
    private JFrame frame = new JFrame("Dreadnoughts " + Game.version);
    
   
	public Interface() {
		/* Use an appropriate Look and Feel */

        try {
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);
        
        
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();

            }
        });

		//HomePage();
	}
	
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private void createAndShowGUI() {
        //Create and set up the window.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Create and set up the content pane.
        addComponentsToPane(frame.getContentPane());
        
        //Display the window.
        frame.pack();
        frame.setVisible(true);
		frame.setSize(400, 500);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null); //puts window in center of screen
    }
	
    public void addComponentsToPane(Container pane) {
        //Put the JComboBox in a JPanel to get a nicer look.
        JPanel comboBoxPane = new JPanel(); //use FlowLayout
        
        //Create the "cards".
        //Create the panel that contains the "cards".
        cards = new JPanel(new CardLayout());
        cards.add(makeHomeCard(), HOMEPANEL);
        cards.add(makeNGMCard(), NEWGAMEMODEPANEL);
        
        pane.add(comboBoxPane, BorderLayout.PAGE_START);
        pane.add(cards, BorderLayout.CENTER);
        
        
    }
    
    /*
    public void itemStateChanged(ItemEvent evt) {
        CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, (String)evt.getItem());
    }*/
    
    public JPanel makeNGMCard() {
		JPanel ngmPanel = new JPanel();
		/* What all do we need to set in a New Game Mode?
		 * 
		 * Distances, first.
		 * Then Factions.
		 * 
		 * Then components
		 * -Menu displaying current components (allow selecting for modification)
		 * -New Component (drop-down with options listing faction or "ALL", and drop-down for Component Type (passive, static, power supply, etc))
		 * -Cardview to the new panel, dependent on component.
		 * 
		 * 
		 */
		
	    Box vBox;
        vBox = Box.createVerticalBox();

        JButton btnAdd = new JButton("Add new JTextField!");
        btnAdd.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
                vBox.add(new JTextField(20));
            }

        });
        JPanel centerPanel = new JPanel();
        JPanel contentPanel = (JPanel) frame.getContentPane();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.add(btnAdd, "South");
        contentPanel.add(centerPanel, "Center");
        centerPanel.add(vBox);
		
		
		
		
		return ngmPanel;
    }

    public JPanel makeHomeCard() {
		JPanel controlPanel = new JPanel();
		JPanel buttonPanel = new JPanel();

		controlPanel.setLayout(new FlowLayout());
		buttonPanel.setLayout(new GridLayout(0, 1, 10, 15));
		
		JButton ng = new JButton("New Game");
		JButton lg = new JButton("Load Game");
		JButton dg = new JButton("Delete Game");
		JButton ngm = new JButton("New GameMode");
		
		ng.setSize(100, 30);		
		lg.setSize(100, 30);
		dg.setSize(100, 30);
		ngm.setSize(100, 30);
		ngm.addActionListener(new ActionListener(){
			@Override
            public void actionPerformed(ActionEvent e)
            {
                CardLayout cl = (CardLayout)(cards.getLayout());
                cl.show(cards, NEWGAMEMODEPANEL);                
            }
        });
		
		buttonPanel.add(ng);
		buttonPanel.add(lg);
		buttonPanel.add(dg);
		buttonPanel.add(ngm);
		
		controlPanel.add(buttonPanel);
		buttonPanel.setVisible(true);
		
		return controlPanel;
    }
    
    
    
    
    
    
    /*
    //==============================================================
    //Defunct	
	public static void HomePage() {
		//mainFrame holds all the "cards
		JFrame mainFrame = new JFrame("Dreadnoughts " + Game.version);
		JPanel controlPanel = new JPanel();
		
		
		JPanel buttonPanel = new JPanel();
		
		controlPanel.setLayout(new FlowLayout());
		buttonPanel.setLayout(new GridLayout(0, 1, 10, 15));
		
		JButton ng = new JButton("New Game");
		JButton lg = new JButton("Load Game");
		JButton dg = new JButton("Delete Game");
		JButton ngm = new JButton("New GameMode");
		
		ng.setSize(100, 30);
		lg.setSize(100, 30);
		dg.setSize(100, 30);
		ngm.setSize(100, 30);
		
		buttonPanel.add(ng);
		buttonPanel.add(lg);
		buttonPanel.add(dg);
		buttonPanel.add(ngm);
		
		mainFrame.add(controlPanel);
		controlPanel.add(buttonPanel);
		buttonPanel.setVisible(true);
		
		mainFrame.setSize(400, 500);
		mainFrame.setVisible(true);
		mainFrame.setLocationRelativeTo(null); //puts window in center of screen
		
		ng.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//action
			}
		});
		
		lg.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//action
			}
		});

		dg.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//action
			}
		});

		ngm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//action
			}
		});
	}

	
	*/
}
