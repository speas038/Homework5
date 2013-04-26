package act.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JButton;
//import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import act.model.ModelEvent;
import act.model.AccountModel;
//import act.model.Account;
import act.controller.AccountController;


/**
 * Class that controls edit view to edit account balances
 * @author Alex
 *
 */
@SuppressWarnings("serial")
public class DepositAgent extends JFrameView{
	

	public static final String AGENT_STOP = "Stop Deposit Agents";
	public static final String GENERATOR_DISMISS = "Deposit Agent Dismiss";
	public static final String VIEW_TYPE = "Deposit AGENT";
	
	private JTextField amountRef;
	private JTextField transferredRef;
	private JTextField opsSecondRef;
	private JTextField stateRef;
	private JTextField editRef;
	private JTextField opsCompletedRef;
	
	public DepositAgent(AccountModel model, AccountController controller ){
		super(model, controller);
		
//		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		JPanel buttonPanel = new JPanel();
		Handler l = new Handler();
		this.setTitle("Deposit Agent");
		
		JLabel amountLabel = new JLabel("Amount in $");
		JTextField amountTextField = new JTextField();
		amountTextField.setEditable(false);
		amountRef = amountTextField;
		
		JLabel opsSecondLabel = new JLabel("Ops/second ");
		JTextField opsSecondTextField = new JTextField();
		opsSecondTextField.setEditable(false);
		opsSecondRef = opsSecondTextField;
		
		JLabel stateLabel = new JLabel("State");
		JTextField labelTextField = new JTextField();
		labelTextField.setEditable(false);
		stateRef = labelTextField;
		
		JLabel amountTransferredLabel = new JLabel("Amount Transferred: ");
		JTextField amountTransferredTextField = new JTextField();
		transferredRef = amountTransferredTextField;
		
		JLabel opsCompletedLabel = new JLabel("Ops completed: ");
		JTextField opsCompletedTextField = new JTextField();
		opsCompletedTextField.setEditable(false);
		opsCompletedRef = opsCompletedTextField;
		
		JButton stopButton = new JButton(AGENT_STOP);
		stopButton.addActionListener(l);
		JButton dismissButton = new JButton(GENERATOR_DISMISS);
		dismissButton.addActionListener(l);
		
		
//		accountTextField.setEditable(false);
//		balanceRef = accountTextField;
		JButton jButtonDeposit = new JButton(AGENT_STOP);
		jButtonDeposit.addActionListener(l);
		
		buttonPanel.setLayout(new GridLayout(7,7,3,3));
		this.getContentPane().add(buttonPanel, BorderLayout.CENTER);
		
		
		buttonPanel.add(amountLabel);
		buttonPanel.add(amountTextField);
		buttonPanel.add(opsSecondLabel);
		buttonPanel.add(opsSecondTextField);
		buttonPanel.add(stateLabel);
		buttonPanel.add(labelTextField);
		buttonPanel.add(amountTransferredLabel);
		buttonPanel.add(amountTransferredTextField);
		buttonPanel.add(opsCompletedLabel);
		buttonPanel.add(opsCompletedTextField);
		buttonPanel.add(stopButton);
		buttonPanel.add(dismissButton);
		
		
		pack();
	}
	
	public void setAmount( String amount ){
		amountRef.setText(amount);
		amountRef.updateUI();
	}
	
	public void setOpsPerSecond(int ops){
		opsSecondRef.setText(Integer.toString(ops));
	}
	public void setState(String state){
		stateRef.setText(state);
	}
	
	
	/**
	 * returns value entered by user in the edit box
	 * @return String
	 */
	public String getAmountChanged(){
		return editRef.getText();
	}
	
	/**
	 * Sets edit field to 0
	 */
	public void resetEditField(){
		editRef.setText("0");
	}
	
	/**
	 * Displays dialog based message passed
	 * @param String message
	 */
	public void displayDialog(String message){
		JOptionPane.showMessageDialog(this, message);
	}

	/**
	 * Updates balance based on model changed event
	 */
	@Override
	// TODO this needs to be changed to a Thread Event
	public void modelChanged(ModelEvent event) {
		// TODO Auto-generated method stub
		transferredRef.setText(Double.toString(event.getAmount()));
		transferredRef.updateUI();
		opsCompletedRef.setText(Integer.toString(event.getOpsCompleted()));
		opsCompletedRef.updateUI();
		this.repaint();
	}
	
	class Handler implements ActionListener { 
		// Event handling is handled locally
		public void actionPerformed(ActionEvent e) {
			((AccountController)getController()).operation(e.getActionCommand());
	    } 
	}
}
