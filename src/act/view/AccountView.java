package act.view;

import javax.swing.*; 

import java.awt.*; 
import java.awt.event.*;

import act.model.AccountModel;
import act.model.ModelEvent;
import act.controller.AccountController;
import act.model.Account;


/**
 * View portion of MVC pattern controls all UI components
 * @author Alex
 *
 */
@SuppressWarnings("serial")
public class AccountView extends JFrameView {
	
//	private String [] accounts;
	public static final String USD = "Edit in USD"; 
	public static final String EUROS = "Edit in Euros"; 
	public static final String YEN = "Edit in Yen"; 
	public static final String SAVE = "Save"; 
	public static final String EXIT = "Exit"; 
	public static final String CREATE_WITHDRAW_AGENT_GENERATOR = "Create Withdraw Agent Generator";
	public static final String CREATE_DEPOSIT_AGENT_GENERATOR = "Create Deposit Agent Generator";
	
	private JComboBox combo;
	
	
	/**
	 * AccountView initializes the view and opens the file specified by the command line.
	 * @param AccountModel
	 * @param AccountController
	 */
	public AccountView(AccountModel model, AccountController controller){
		super(model, controller);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		JPanel buttonPanel = new JPanel();
		Handler l = new Handler();
		
//		accounts = readAccounts(model.accounts);
		JComboBox accountsCombo = new JComboBox(model.getAccounts().toArray());
		accountsCombo.addActionListener(l);
		this.combo = accountsCombo;
		model.setCurrentAccount((Account)accountsCombo.getSelectedItem());
		
		JButton jButtonUSD = new JButton(USD);
		jButtonUSD.addActionListener(l);
		JButton jButtonEuros = new JButton(EUROS);
		jButtonEuros.addActionListener(l);
		JButton jButtonYen = new JButton(YEN);
		jButtonYen.addActionListener(l);
		JButton jButtonSave = new JButton(SAVE);
		jButtonSave.addActionListener(l);
		JButton jButtonExit = new JButton(EXIT);
		jButtonExit.addActionListener(l);
		JButton jButtonWithdrawAgent = new JButton(CREATE_WITHDRAW_AGENT_GENERATOR);
		jButtonWithdrawAgent.addActionListener(l);
		JButton jButtonDepositAgent = new JButton(CREATE_DEPOSIT_AGENT_GENERATOR);
		jButtonDepositAgent.addActionListener(l);
		
		
		buttonPanel.setLayout(new GridLayout(5, 5, 7, 7));
		this.getContentPane().add(buttonPanel, BorderLayout.CENTER);
		
		buttonPanel.add(accountsCombo);
		buttonPanel.add(jButtonUSD);
		buttonPanel.add(jButtonEuros);
		buttonPanel.add(jButtonYen);
		buttonPanel.add(jButtonSave);
		buttonPanel.add(jButtonExit);
		buttonPanel.add(jButtonWithdrawAgent);
		buttonPanel.add(jButtonDepositAgent);
		
		pack();
		
	}
	
	/**
	 * Returns the account pointed at by the current selection of the combo box
	 * @return Account
	 */
	public Account getSelectedItem(){
		return (Account)combo.getSelectedItem();
	}
	
	/**
	 * Updates values of combo box based on changes to the model
	 */
	@Override
	public void modelChanged(ModelEvent event) {
		((Account)combo.getSelectedItem()).setBalance(event.getAmount());
		combo.updateUI();
		 
	}
	
	/**
	 * Event Handler class
	 * @author Alex
	 *
	 */
	public class Handler implements ActionListener { 
		// Event handling is handled locally
		public void actionPerformed(ActionEvent e) {
			((AccountController)getController()).operation(e.getActionCommand());
	    } }
	
	/**
	 * Main is located inside of the view class.  It accepts the path of the input file as an argument
	 * @param String []
	 */
	public static void main(String [] args) { new AccountController(args);}
}
