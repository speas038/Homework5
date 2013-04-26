package act.controller;
import act.model.Model;
import act.view.View;

public abstract class AbstractController implements Controller {
	private View view;
	private View view2 = null;
	private View withdrawAgent = null;
	private View depositAgent = null;
	private View depositAgentGenerator;
	private View withdrawAgentGenerator;
	private Model model;
	
	public void setModel(Model model){this.model = model;}
	
	public Model getModel(){return model;}
	
	public View getView(){return view;}
	
	public View getView2(){ return view2; }
	
	public void setView(View view){this.view = view;}
	
	public void swapView(View view){
		view2 = this.view;
		this.view = view;
	}
	
	public void returnView(){
		if(view2 != null){
			this.view = view2;
			view2 = null;
		}else{
			System.out.println("controller trying to switch to null view");
		}
	}
	
	public void setDepositAgent(View depositAgent) {
		this.depositAgentGenerator = null;
		this.depositAgent = depositAgent;
	}

	public View getDepositAgent() {
		return depositAgent;
	}

	public void setWithdrawAgent(View withdrawAgent) {
		this.withdrawAgent = withdrawAgent;
	}

	public View getWithdrawAgent() {
		return withdrawAgent;
	}

	public void setWithdrawAgentGenerator(View withdrawAgentGenerator) {
		this.withdrawAgentGenerator = withdrawAgentGenerator;
	}

	public View getWithdrawAgentGenerator() {
		return withdrawAgentGenerator;
	}

	public void setDepositAgentGenerator(View depositAgentGenerator) {
		this.depositAgentGenerator = depositAgentGenerator;
	}

	public View getDepositAgentGenerator() {
		return depositAgentGenerator;
	}
}
