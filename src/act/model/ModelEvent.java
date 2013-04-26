package act.model;
import java.awt.event.ActionEvent;

public class ModelEvent extends ActionEvent {
	private static final long serialVersionUID = 1L;
	private double amount;
	public ModelEvent(Object obj, int id, String message, double amount){
		super(obj, id, message);
		this.amount = amount;
	}
	public double getAmount(){return amount;}
}
