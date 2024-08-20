import javax.swing.SwingUtilities;

public class MainTrainer {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new MainZubrilka();
				
			}
		});

	}

}
