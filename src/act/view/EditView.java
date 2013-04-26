package act.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import act.model.ModelEvent;
import act.model.AccountModel;
import act.model.Account;
import act.controller.AccountController;


/**
 * Class that controls edit view to edit account balances
 * @author Alex
 *
 */
@SuppressWarnings("serial")
public class EditView extends JFrameView{
	

	public static final String editDeposit = "Deposit";
	public static final String editWithdraw = "Withdraw";
	public static final String editDismiss = "Dismiss";
	
	private JTextField balanceRef;
	private JTextField editRef;
	
	public EditView(AccountModel model, AccountController controller ){
		super(model, controller);
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		JPanel buttonPanel = new JPanel();
		Handler l = new Handler();
		
		JLabel balanceLabel = new JLabel("Balance:");
		JTextField accountTextField = new JTextField(((Account)model.getCurrentAccount()).getBalanceString());
		accountTextField.setEditable(false);
		balanceRef = accountTextField;
		JTextField editTextField = new JTextField("0");
		editRef = editTextField;
		JButton jButtonDeposit = new JButton(editDeposit);
		jButtonDeposit.addActionListener(l);
		JButton jButtonWithdraw = new JButton(editWithdraw);
		jButtonWithdraw.addActionListener(l);
		JButton jButtonDismiss = new JButton(editDismiss);
		jButtonDismiss.addActionListener(l);
		
		buttonPanel.setLayout(new GridLayout(3, 2, 20, 20));
		this.getContentPane().add(buttonPanel, BorderLayout.CENTER);
		
		buttonPanel.add(balanceLabel);
		buttonPanel.add(accountTextField);
		buttonPanel.add(editTextField);
		buttonPanel.add(jButtonDeposit);
		buttonPanel.add(jButtonWithdraw);
		buttonPanel.add(jButtonDismiss);
		
		pack();
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
	public void modelChanged(ModelEvent event) {
		// TODO Auto-generated method stub
		balanceRef.setText(Double.toString(event.getAmount()));
		balanceRef.updateUI();
	}
	

	class Handler implements ActionListener { 
		// Event handling is handled locally
		public void actionPerformed(ActionEvent e) {
			((AccountController)getController()).operation(e.getActionCommand());
	    } 
	}

}
