import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class MenuClass extends JPanel {
	
	JLabel menuUserGreeting;
	JButton startTraining;
	JButton dictionaryEnter;
	JButton returnToUserChoose;
	MainZubrilka zubrilkaFrame;
	
	UserDictionary user; // нужен для работы со словарем пользователя

	
	public MenuClass(MainZubrilka zubrilkaFrame) {
		System.out.println("Создаём меню");
		setLayout(new GridLayout(8,1,10,10));
		setOpaque(true);
		
		menuUserGreeting = new JLabel("");
		startTraining = new JButton("<html><font size =4>Стартуем Тренировку<font>"); //<html><font size =3>Введи своё имя для входа. 10 букв - не больше!<font>
		startTraining.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				zubrilkaFrame.changeLayout.show(zubrilkaFrame.cardPanel, "training");
				
			}
		});
		
		
		dictionaryEnter = new JButton("<html><font size =4>Работа со словарём<font>");
		dictionaryEnter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				zubrilkaFrame.changeLayout.show(zubrilkaFrame.cardPanel, "dictPanel");
				
			}
		});
		
		
		returnToUserChoose = new JButton("<html><font size =4>К выбору пользователя<font>");
		returnToUserChoose.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				zubrilkaFrame.changeLayout.show(zubrilkaFrame.cardPanel, "login");
				
			}
		});
		
		this.add(menuUserGreeting);
		this.add(startTraining);
		this.add(dictionaryEnter);
		this.add(returnToUserChoose);
		
		
	}
}
