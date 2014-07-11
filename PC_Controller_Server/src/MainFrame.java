import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;


public class MainFrame extends JFrame{
	
	private static MainFrame instance;
	
	private JButton exitButton;
	private JButton startButton;
	private JList<String> console;
	private JScrollPane consoleScroll;
	private JTextField portField;
	private DefaultListModel<String> consoleModel = new DefaultListModel<String>();
	
	public static void main(String[] args){
		MainFrame mf = MainFrame.getInstance();
		mf.printConsole("Program initialized!");
	}

	
	private MainFrame(){
		initComponents();
	}
	
	public static MainFrame getInstance(){
		if (instance == null){
			instance = new MainFrame();
		}
		return instance;
	}
	
	private void initComponents(){
		JPanel pane = new JPanel();
		pane.setPreferredSize(new Dimension(350,250));
		pane.setLayout(null);
		
		exitButton = new JButton("Exit");
		exitButton.addActionListener(new BL(BL.BLtype.exit));
		exitButton.setBounds(5,225,60,20);
		pane.add(exitButton);
		
		startButton = new JButton("Start");
		startButton.addActionListener(new BL(BL.BLtype.start));
		startButton.setBounds(275,225,70,20);
		pane.add(startButton);
		
		portField = new JTextField();
		portField.setText("27016");
		portField.setBounds(230,225,40,21);
		pane.add(portField);
		
		console = new JList<String>();
		console.setModel(consoleModel);
		console.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		console.setAutoscrolls(true);
		//console.setCellRenderer(new LogRenderer());
		console.setBackground(Color.decode("#FAFAFA"));
		
		
		consoleScroll = new JScrollPane(console,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		consoleScroll.setBounds(5,5,340,215);
		consoleScroll.setAutoscrolls(true);
		
		consoleScroll.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {  
			@Override
			public void adjustmentValueChanged(AdjustmentEvent e) {  
		        e.getAdjustable().setValue(e.getAdjustable().getMaximum());  
		    }
		});
		pane.add(consoleScroll);
		
		this.setResizable(false);		
		this.setVisible(true);
		this.add(pane);
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Server");
	}
	
	public void printConsole(String text){
		consoleModel.add(consoleModel.size(), text);
		consoleScroll.revalidate();
	}
	
	public String getPort(){
		return portField.getText();
	}

	public JScrollBar getConsoleScrollbar(){
		return consoleScroll.getVerticalScrollBar();
	}
	
	
	private static class BL implements ActionListener{
		
		enum BLtype {
			exit,
			start,
			stop
		}
		
		private BLtype t;
		
		public BL(BLtype t){
			this.t = t;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			Object s = e.getSource();
			MainFrame mf = MainFrame.getInstance();
			
			if (this.t == BLtype.exit){
				System.exit(0);
			}
			if (this.t == BLtype.start){
				String port = mf.getPort();
				
				mf.printConsole("Attempting to start server on port "+mf.getPort());
				
				try  
				{  
					int iPort = Integer.parseInt(port);
				}  
					catch(NumberFormatException nfe)  
				{  
					mf.printConsole("Couldn't start server, "+mf.getPort()+" is not a valid port number!");
				}  
				
				
			}
		}
	}
}
