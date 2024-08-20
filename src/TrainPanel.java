import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TrainPanel extends JPanel implements ActionListener{
	JLabel greetingLabel;
	JButton engRus;
	JButton rusEng;
	JButton toMenu;
	
	JTextField wordToTranslate;
	MainZubrilka zubrilkaFrame;
	PlayPanel panelForPlay;
	
	
	public TrainPanel(MainZubrilka zubrilkaFrame) {
		this.zubrilkaFrame = zubrilkaFrame;
		
		
		setLayout(new GridLayout(8,1,10, 10));
		greetingLabel = new JLabel("<html><font size = 4>Для победы переведи правильно 10 слов подряд.<br> Проиграешь, если ошибёшься 10 раз.<br>Выбери тип игры<font>");
		greetingLabel.setHorizontalAlignment(JLabel.CENTER);
		
		engRus = new JButton("English - Русский");
		engRus.setActionCommand("engrus");
		engRus.addActionListener(this);
		
		rusEng = new JButton("Русский - English");
		rusEng.setActionCommand("ruseng");
		rusEng.addActionListener(this);
		
		toMenu = new JButton("В меню");
		toMenu.setActionCommand("tomenu");
		toMenu.addActionListener(this);
		
		add(greetingLabel);
		add(engRus);
		add(rusEng);
		add(toMenu);
		
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("engrus")) {
			zubrilkaFrame.changeLayout.show(zubrilkaFrame.cardPanel, "engruspanel");
		}
			
		if(e.getActionCommand().equals("ruseng")) {
			zubrilkaFrame.changeLayout.show(zubrilkaFrame.cardPanel, "rusengpanel");
		}
			
		if(e.getActionCommand().equals("tomenu")) {
			zubrilkaFrame.changeLayout.show(zubrilkaFrame.cardPanel, "menu");
		}
		
	}

}
