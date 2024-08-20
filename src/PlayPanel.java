/*
 * 
 * Этот класс служит моделью дизайна для классов 2х типов игры
 * 
 * 
 * 
 * 
 */


import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PlayPanel extends JPanel{
	MainZubrilka zubrilkaFrame;
	JLabel actionToMake;
	JLabel wordToTranslate;
	JLabel actionToMake2;
	JTextField wordFromUser;
	JLabel infoPanel;
	JLabel winpoints;
	JLabel losepoints;
	JPanel panelForButtons;
	JButton startButton;
	JButton toMenuButton;
	String str = "";
	
	public PlayPanel(MainZubrilka zubrilkaFrame) {
		this.setLayout(new GridLayout(8,1,4,4));
		this.zubrilkaFrame = zubrilkaFrame;
		
		actionToMake = new JLabel("Слово для перевода");
		wordToTranslate = new JLabel("<html><font size = 6>" + str + "<font>");
		
		actionToMake2 = new JLabel("Введи перевод");
		wordFromUser = new JTextField(10);
		wordFromUser.setFont(new Font(Font.SERIF,Font.PLAIN,30));
		
		infoPanel = new JLabel("");
		
		winpoints = new JLabel("Очки победы: ");
		losepoints = new JLabel("Очки поражения: ");
		panelForButtons = new JPanel();
	
		startButton = new JButton("Старт");
		toMenuButton = new JButton("В меню");
		
		panelForButtons.add(startButton);
		panelForButtons.add(toMenuButton);
		
		add(actionToMake);
		add(wordToTranslate);
		
		add(actionToMake2);
		add(wordFromUser);
		
		add(infoPanel);
		
		add(winpoints);
		add(losepoints);
		
		add(panelForButtons);
	}


}
