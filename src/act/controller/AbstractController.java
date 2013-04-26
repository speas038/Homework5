package act.controller;
import act.model.Model;
import act.view.View;

public abstract class AbstractController implements Controller {
	private View view;
	private View view2 = null;
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
	
	public void swapGenerator(View view){
		this.view2 = view;
	}
}
