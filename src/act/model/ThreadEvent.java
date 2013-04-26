package act.model;
import java.awt.event.ActionEvent;

public class ThreadEvent extends ActionEvent {
	private static final long serialVersionUID = 1L;
	private double newBalance;
	private String status;
	private int opsCompleted;
	
	public ThreadEvent(Object obj, int id, String message, double newBalance, String status, int opsCompleted ){
		super(obj, id, message);
		this.newBalance = newBalance;
		this.status = status;
		this.opsCompleted = opsCompleted;
	}
	
	public double getNewBalance(){return newBalance;}
	public String getStatus(){ return status; }
	public int getOpsCompleted() { return opsCompleted; }
}