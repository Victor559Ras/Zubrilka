import java.awt.CardLayout;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainZubrilka extends JFrame {
	JPanel cardPanel; // панель для других панелей
	CardLayout changeLayout;
	
	LoginClass loginpanel;
	MenuClass menupanel;
	TrainPanel trainPanel;
	FixDictionary dictFixPanel;
	AllWordsPanel dictPanel;
	PlayPanel engRusTrainer;
	PlayPanel rusEngTrainer;
	
	
	public MainZubrilka() {
		this.setTitle("Zubrilka");
		this.getContentPane().setLayout(new FlowLayout());
		this.setSize(500,600);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Основная панель программы.
		// Цепляем на неё Логин-панель, Меню-панель, Тренировка панель, Панель словаря
						
		cardPanel = new JPanel(); // создаём панель панелей
		changeLayout = new CardLayout(); // создаём переменную для компновщика
		cardPanel.setLayout(changeLayout); // панель панелей получает компановку кардлэйаут
		
		loginpanel = new LoginClass(this);// создаём панель логина
		menupanel = new MenuClass(this); //создаём панель меню
		trainPanel = new TrainPanel(this); // создаём панель треннинга
		dictFixPanel = new FixDictionary(this);
		dictPanel = new AllWordsPanel(this);
		engRusTrainer = new EngRusTrainer(this);
		rusEngTrainer = new RusEngTrainer(this);
		
		
		// добавляем обе панели на панель панелей, а потом панель панелей внутрь фрэйма
		
		cardPanel.add(loginpanel, "login");
		cardPanel.add(menupanel, "menu");
		cardPanel.add(trainPanel, "training");
		cardPanel.add(dictFixPanel,"dictPanel");
		cardPanel.add(dictPanel, "allWordsPanel");
		cardPanel.add(engRusTrainer, "engruspanel");
		cardPanel.add(rusEngTrainer, "rusengpanel");
		getContentPane().add(cardPanel);
		setVisible(true);
		
		
	}

}
