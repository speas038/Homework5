package act.controller;

import act.model.Account;
import act.model.AccountModel;
import act.model.DepositThread;
import act.model.WithdrawThread;
import act.view.AccountView;
import act.view.DepositAgent;
import act.view.DepositAgentGenerator;
import act.view.EditView;
import act.view.JFrameView;
import act.view.WithdrawAgent;
import act.view.WithdrawAgentGenerator;

import javax.swing.JComboBox;

/**
 * Abstract Controller is called from main responsible for initializing the model and view
 * it switches views based on user input and handles all user input to catch exceptions from the Model
 * and sends appropriate messages to the view to open dialog boxes
 * @author Alex
 *
 */
@SuppressWarnings("unused")
public class AccountController extends AbstractController {
	
	private String currentSelection = AccountView.USD;
	private DepositThread t;
	
	//It makes sense that the arguments would get passed to the conroller then passed to the view.
	
	public AccountController(String [] args){
		setModel(new AccountModel(args) );
		setView( new AccountView( (AccountModel)getModel(), this ));
		((JFrameView)getView()).setVisible(true);
		((JFrameView)getView()).setTitle("Speas Accounting Software");
		
	}
	
	/**
	 * Accepts a string opton and executes the aproppriate model code and sends appropriate messages
	 * to the view as well as exceptions
	 * @param option
	 */
	public void operation(String option){
		
//		System.out.println("Controller option: " + option);
		
		if(option == AccountView.USD){
			
			((AccountModel)getModel()).setcurrentRate(AccountView.USD);
			swapView( new EditView( (AccountModel)getModel(), this));
			((JFrameView)getView()).setVisible(true);
			//set the title of the JFrame window
			String title = new String(((AccountModel)getModel()).getCurrentAccount().getName() + " - " + Integer.toString(((AccountModel)getModel()).getCurrentAccount().getID()));
			((JFrameView)getView()).setTitle(title);
			currentSelection = AccountView.USD;
			
			
		}else if(option == AccountView.EUROS){
			((AccountModel)getModel()).setcurrentRate(AccountView.EUROS);
			swapView( new EditView( (AccountModel)getModel(), this));
			((JFrameView)getView()).setVisible(true);	
			//set the title of the JFrame window
			String title = new String(((AccountModel)getModel()).getCurrentAccount().getName() + " - " + Integer.toString(((AccountModel)getModel()).getCurrentAccount().getID()));
			((JFrameView)getView()).setTitle(title);
			currentSelection = AccountView.EUROS;
			
		}else if(option == AccountView.YEN){
			((AccountModel)getModel()).setcurrentRate(AccountView.YEN);
			swapView( new EditView( (AccountModel)getModel(), this));
			((JFrameView)getView()).setVisible(true);	
			//set the title of the JFrame window
			String title = new String(((AccountModel)getModel()).getCurrentAccount().getName() + " - " + Integer.toString(((AccountModel)getModel()).getCurrentAccount().getID()));
			((JFrameView)getView()).setTitle(title);
			currentSelection = AccountView.YEN;
			
		}else if(option == AccountView.SAVE){
			((AccountModel)getModel()).save();
			
		}else if(option == AccountView.EXIT){
			((AccountModel)getModel()).exit();
			System.exit(0);
			
		}else if(option == EditView.editDeposit){
			try {
				((AccountModel)getModel()).deposit(((EditView)getView()).getAmountChanged());
			} catch (Exception e) {
				((EditView)getView()).displayDialog(e.getMessage());
			}
			((EditView)getView()).resetEditField();
			
		}else if(option == EditView.editDismiss){
			((JFrameView)getView()).setVisible(false);
			returnView();
			
		}else if(option == "comboBoxChanged"){
			((AccountModel)getModel()).setCurrentAccount((((AccountView)getView()).getSelectedItem()));
			
		}else if(option == EditView.editWithdraw){
			try{
				((AccountModel)getModel()).withdraw(((EditView)getView()).getAmountChanged());
				((EditView)getView()).resetEditField();
			}catch(Exception e){
				((EditView)getView()).displayDialog(e.getMessage());
			}
			
		}else if( option == AccountView.CREATE_DEPOSIT_AGENT_GENERATOR){
			setDepositAgentGenerator( new DepositAgentGenerator( (AccountModel)getModel(), this));
			((JFrameView)getDepositAgentGenerator()).setVisible(true);
			//set the title of the JFrame window
			currentSelection = AccountView.CREATE_DEPOSIT_AGENT_GENERATOR;
			
		}else if (option == DepositAgentGenerator.DEPOSIT_AGENT_START){
			((AccountModel)getModel()).stopDepositAgents = 0;
			String amt = ((DepositAgentGenerator)getDepositAgentGenerator()).getAmount();
			int ops = ((DepositAgentGenerator)getDepositAgentGenerator()).getOps();
			String agentID = ((DepositAgentGenerator)getDepositAgentGenerator()).getAgentID();
			
			
			((JFrameView)getDepositAgentGenerator()).setVisible(false);
			
			setDepositAgent(new DepositAgent((AccountModel)getModel(), this));
			((DepositAgent)getDepositAgent()).setAmount(amt);
			((DepositAgent)getDepositAgent()).setOpsPerSecond(ops);
			((DepositAgent)getDepositAgent()).setState("Running");
			
			((JFrameView)getDepositAgent()).setVisible(true);
			currentSelection = DepositAgentGenerator.DEPOSIT_AGENT_START;
			
			DepositThread d = new DepositThread((AccountModel)getModel(), amt, ops, agentID);
			Thread D = new Thread(d);
			D.start();
			System.out.println("Deposit agent start");
		
		}else if(option == DepositAgent.AGENT_STOP){
			((AccountModel)getModel()).stopDepositAgents = 1;
			
		}else if(option == DepositAgent.GENERATOR_DISMISS){
			((AccountModel)getModel()).stopDepositAgents = 1;
			((JFrameView)getDepositAgent()).setVisible(false);
			
		}else if( option == AccountView.CREATE_WITHDRAW_AGENT_GENERATOR){
			setWithdrawAgentGenerator( new WithdrawAgentGenerator( (AccountModel)getModel(), this));
			((JFrameView)getWithdrawAgentGenerator()).setVisible(true);
			//set the title of the JFrame window
			currentSelection = AccountView.CREATE_WITHDRAW_AGENT_GENERATOR;
			
		}else if (option == WithdrawAgentGenerator.WITHDRAW_AGENT_START){
			((AccountModel)getModel()).stopWithdrawAgents = 0;
			String amt = ((WithdrawAgentGenerator)getWithdrawAgentGenerator()).getAmount();
			int ops = ((WithdrawAgentGenerator)getWithdrawAgentGenerator()).getOps();
			String agentID = ((WithdrawAgentGenerator)getWithdrawAgentGenerator()).getAgentID();
			
			
			((JFrameView)getWithdrawAgentGenerator()).setVisible(false);
			
			setWithdrawAgent(new WithdrawAgent((AccountModel)getModel(), this));
			((WithdrawAgent)getWithdrawAgent()).setAmount(amt);
			((WithdrawAgent)getWithdrawAgent()).setOpsPerSecond(ops);
			((WithdrawAgent)getWithdrawAgent()).setState("Running");
			
			((JFrameView)getWithdrawAgent()).setVisible(true);
			currentSelection = WithdrawAgentGenerator.WITHDRAW_AGENT_START;
			
			WithdrawThread w = new WithdrawThread((AccountModel)getModel(), amt, ops, agentID);
			Thread W = new Thread(w);
			W.start();
			System.out.println("Withdraw agent start");
		
		}else if(option == WithdrawAgent.AGENT_STOP){
			((AccountModel)getModel()).stopWithdrawAgents = 1;
			
		}else if(option == WithdrawAgent.GENERATOR_DISMISS){
			((AccountModel)getModel()).stopWithdrawAgents = 1;
			((JFrameView)getWithdrawAgent()).setVisible(false);
		}
			
	}
}
