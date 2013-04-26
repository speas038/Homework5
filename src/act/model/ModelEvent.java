package act.model;
import java.awt.event.ActionEvent;

public class ModelEvent extends ActionEvent {
	private static final long serialVersionUID = 1L;
	private double amount;
	private String state;
	private int opsCompleted;
	private int withdrawOpsCompleted;
	
	
	
	public ModelEvent(Object obj, int id, String message, double amount){
		super(obj, id, message);
		this.amount = amount;
	}
	
	public ModelEvent(Object obj, int id, String message, double amount, String state, int opsCompleted, int withdrawOpsCompleted){
		super(obj, id, message);
		this.amount = amount;
		this.state = state;
		this.opsCompleted = opsCompleted;
		this.withdrawOpsCompleted = withdrawOpsCompleted;
	}
	public double getAmount(){return amount;}
	public String getState() { return state; }
	public int getOpsCompleted() { return opsCompleted; }
	public int getWithdrawOpsCompleted() { return withdrawOpsCompleted; }
}
