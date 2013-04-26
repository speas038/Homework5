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
public class WithdrawAgent extends JFrameView{
	

	public static final String AGENT_STOP = "Stop Withdraw Agents";
	public static final String GENERATOR_DISMISS = "Withdraw Agent Dismiss";
	public static final String VIEW_TYPE = "WITHDRAW AGENT";
	
	private JTextField amountRef;
	private JTextField transferredRef;
	private JTextField opsSecondRef;
	private JTextField stateRef;
	private JTextField editRef;
	private JTextField opsCompletedRef;
	
	public WithdrawAgent(AccountModel model, AccountController controller ){
		super(model, controller);
		
//		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		JPanel buttonPanel = new JPanel();
		Handler l = new Handler();
		this.setTitle("Withdraw Agent");
		
//		JLabel idLabel = new JLabel("Agent ID: ");
//		JTextField idTextField = new JTextField();
		JLabel amountLabel = new JLabel("Amount in $");
		JTextField amountTextField = new JTextField();
		amountRef = amountTextField;
		
		JLabel opsSecondLabel = new JLabel("Ops/second ");
		JTextField opsSecondTextField = new JTextField();
		opsSecondRef = opsSecondTextField;
		
		JLabel stateLabel = new JLabel("State");
		JTextField labelTextField = new JTextField();
		stateRef = labelTextField;
		
		JLabel amountTransferredLabel = new JLabel("Amount Transferred: ");
		JTextField amountTransferredTextField = new JTextField();
		transferredRef = amountTransferredTextField;
		
		JLabel opsCompletedLabel = new JLabel("Ops completed: ");
		JTextField opsCompletedTextField = new JTextField();
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
		opsCompletedRef.setText(Integer.toString(event.getWithdrawOpsCompleted()));
		opsCompletedRef.updateUI();
		stateRef.setText(event.getState());
		stateRef.updateUI();
		this.repaint();
	}
	
	class Handler implements ActionListener { 
		// Event handling is handled locally
		public void actionPerformed(ActionEvent e) {
			((AccountController)getController()).operation(e.getActionCommand());
	    } 
	}
}

