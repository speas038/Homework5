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
public class WithdrawAgentGenerator extends JFrameView{
	

	public static final String WITHDRAW_AGENT_START = "Start Withdraw Agent";
	public static final String VIEW_TYPE = "WithdrawAgentGenerator";
	
	public JTextField agentID;
	public JTextField opsPerSecond;
	public JTextField amount;
	private JTextField editRef;
	
	public WithdrawAgentGenerator(AccountModel model, AccountController controller ){
		super(model, controller);
		
		JPanel buttonPanel = new JPanel();
		Handler l = new Handler();
		this.setTitle("Withdraw Agent Settings");
		
		JLabel idLabel = new JLabel("Agent ID: ");
		JTextField idTextField = new JTextField();
		agentID = idTextField;
		JLabel amountLabel = new JLabel("Amount: ");
		JTextField amountTextField = new JTextField();
		amount = amountTextField;
		JLabel opsSecondLabel = new JLabel("Ops/second ");
		JTextField opsSecondTextField = new JTextField();
		opsPerSecond = opsSecondTextField;
		
		JButton jButtonDeposit = new JButton(WITHDRAW_AGENT_START);
		jButtonDeposit.addActionListener(l);
		
		buttonPanel.setLayout(new GridLayout(5,5,7,7));
		this.getContentPane().add(buttonPanel, BorderLayout.CENTER);
		
		buttonPanel.add(idLabel);
		buttonPanel.add(idTextField);
		buttonPanel.add(amountLabel);
		buttonPanel.add(amountTextField);
		buttonPanel.add(opsSecondLabel);
		buttonPanel.add(opsSecondTextField);
		buttonPanel.add(jButtonDeposit);
		
		pack();
	}
	
	/**
	 * returns value entered by user in the edit box
	 * @return String
	 */
	public String getAmountChanged(){
		return editRef.getText();
	}
	
	public int getOps(){
		return Integer.parseInt(opsPerSecond.getText());
	}
	
	public String getAgentID(){
		return agentID.getText();
	}
	
	public String getAmount(){
		return amount.getText();
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
	public void modelChanged(ModelEvent event) {
//		// TODO Auto-generated method stub
//		balanceRef.setText(/*Double.toString(event.getAmount())*/"changed");
//		balanceRef.updateUI();
	}
	

	class Handler implements ActionListener { 
		// Event handling is handled locally
		public void actionPerformed(ActionEvent e) {
			((AccountController)getController()).operation(e.getActionCommand());
	    } 
	}

}
