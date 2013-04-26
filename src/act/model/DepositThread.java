package act.model;

public class DepositThread implements Runnable{

	private AccountModel model;
	private String amount;
	private int ops;
	public String name;
	
	public DepositThread(AccountModel m, String amt, int ops, String name){
		this.model = m;
		this.amount = amt;
		this.ops = ops;
		this.name = name;
	}
	@Override
	public void run() {
		
			try {
					while(model.stopDepositAgents != 1){
						model.threadDeposit(amount);
						Thread.sleep(1000/ops);
						System.out.println(name + " " + amount + " " + ops);
					}
			} catch (Exception e) {
				// TODO Auto-generated catch block
//				System.out.println("Thread Error");
				e.printStackTrace();
			}
//		}
	}

}
