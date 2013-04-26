package act.model;
import java.awt.event.ActionEvent;

public class ModelEvent extends ActionEvent {
	private static final long serialVersionUID = 1L;
	private double amount;
	private String status;
	private int opsCompleted;
	
	
	
	public ModelEvent(Object obj, int id, String message, double amount){
		super(obj, id, message);
		this.amount = amount;
	}
	
	public ModelEvent(Object obj, int id, String message, double amount, String status, int opsCompleted){
		super(obj, id, message);
		this.amount = amount;
		this.status = status;
		this.opsCompleted = opsCompleted;
	}
	public double getAmount(){return amount;}
	public String getStatus() { return status; }
	public int getOpsCompleted() { return opsCompleted; }
}
