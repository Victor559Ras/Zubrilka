/*
 * создаем панель общую для двух игр
 * делаем ее основой для двух реализаций одинаковых игр
 * 
 * создай панель
 * получить карту слов
 * получить слово для перевода
 * проверить было ли оно отгадано (создать карту отгалданных ключей) 
 * отобразить слово
 * получить перевод
 * если верный очки плюс
 * если неверный очки минус
 * 
 * проверить условия победы и проигрыша
 * 
 *  закончить игру или запустить новое слово
 * 
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JOptionPane;

import java.util.Random;

public class EngRusTrainer extends PlayPanel implements ActionListener{
	UserDictionary user;
	DataBase base;
	
	Map <String, String> userDictionary;
	String keyWordToTranslate;
	ArrayList<String> listOfTranslatedWords;
	int userinput;
	boolean programStatus = false;
	Random random = new Random() ;
	
	int winpoint = 0;
	int losepoint = 0;
	
	public EngRusTrainer(MainZubrilka zubrilkaFrame) {
		super(zubrilkaFrame);
		
		super.toMenuButton.addActionListener(this);
		super.toMenuButton.setActionCommand("tomenu");
		
		super.startButton.addActionListener(this);
		super.startButton.setActionCommand("startButton");	
		
		super.wordFromUser.addActionListener(this);
		super.wordFromUser.setActionCommand("userWord");
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("startButton")) {
			user = zubrilkaFrame.loginpanel.getUser();
			base = zubrilkaFrame.loginpanel.getBase();
			
			userDictionary = zubrilkaFrame.loginpanel.getUser().userDict;// берём словарь пользователя
			listOfTranslatedWords = zubrilkaFrame.loginpanel.getUser().translatedWords;
			
			if (userDictionary.isEmpty()) JOptionPane.showConfirmDialog(null,"Вы не записали в словарик слова для тренировки", "", JOptionPane.CLOSED_OPTION);
			
			keyWordToTranslate = getRandomWordToTranslate();// случайное слово из словаря
			programStatus = true;
			super.wordToTranslate.setText("<html><font size = 6>" + keyWordToTranslate + "<font>");
			
		} else if(e.getActionCommand().equals("userWord") && programStatus == false ) {
			// текст введен но игра не начата 
			JOptionPane.showConfirmDialog(null,"Игра не запущена", "", JOptionPane.CLOSED_OPTION);
		} else if(e.getActionCommand().equals("userWord") && programStatus == true) {
			// текст введен и игра начата
			// сравнить 2 слова: от пользователя и из словаря - значение!!!!
			
			if(wordFromUser.getText().equals(userDictionary.get(keyWordToTranslate))) {
				
				//System.out.println("Сохраняем слово " + keyWordToTranslate + " как переведённое");
				
				super.infoPanel.setText("Правильно!");
				winpoint ++;
				super.winpoints.setText("Очки победы: " + winpoint);
				wincheck(winpoint);
				
				listOfTranslatedWords.add(keyWordToTranslate); 
				
				// сохраняем объект в массив объектов
				base.setDataBase(user);
				// сохраняем массив в файле
				new WriteDictionary(base);
			
				wordFromUser.setText("");
			} else {
				super.infoPanel.setText("Неверно!");
				wordFromUser.setText("");
				losepoint++;
				super.losepoints.setText("Очки поражения: " + losepoint);
				winpoint = 0;
				losecheck(losepoint);
				
			}
			keyWordToTranslate = getRandomWordToTranslate();// случайное слово из словаря
			System.out.println("новое слово " + keyWordToTranslate);
			super.wordToTranslate.setText("<html><font size = 6>" + keyWordToTranslate + "<font>");
			
		} else if (e.getActionCommand().equals("tomenu")) {
			programStatus = false;
			winpoint = 0;
			losepoint = 0;
			zubrilkaFrame.changeLayout.show(zubrilkaFrame.cardPanel, "menu");
		}	
		
	}
	
	// функция выбора случайного слова из словаря пользователя
	
		String getRandomWordToTranslate() {
			int counter = 0;
			int numKey = 0;
			String keyWord = "";
			numKey = random.nextInt(userDictionary.size() + 1);
			System.out.println("случайное число " + numKey);
			for(Entry<String, String> entry: userDictionary.entrySet()) {
				 keyWord = entry.getKey();
				 if (counter == numKey) break;
				 counter++;
			}
			// проверяем слово не было ли оно перевено
			// если слов таких нет вызываем панель очистки
			if (isNotTranslated(keyWord)) {
				return keyWord;
			} else {
				if (checkWord()) keyWord = getRandomWordToTranslate();
			}
			return keyWord;
		}
		
		// проверить слово было ли оно переведено
		
		boolean isNotTranslated (String wordToCheck) {
			boolean respond = true;	
			for (String str: listOfTranslatedWords) {
				if (wordToCheck.equals(str)) {
					respond = false;
					break;
				}
			} 
			return respond;
		}
		
		// сверяем словари, если одинаковые предлагаем очистить список переведенных
		
		Boolean checkWord() {
			if(userDictionary.size() == listOfTranslatedWords.size() ) { 
				Object [] toConfirm = {"Да", "Нет"};
				userinput = JOptionPane.showOptionDialog(null,"Все слова отгаданы. Сбросим угаданное?", "Zubrilka", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, toConfirm, toConfirm[0] );
				switch(userinput) {
					case 0 -> {
						listOfTranslatedWords.clear();
					}
					case 1 -> {
						programStatus = false;
						zubrilkaFrame.changeLayout.show(zubrilkaFrame.cardPanel, "training");
					}
					default -> System.out.println("Не работает свитч-лэйс");
				}
				return false;
			} else {
				return true;	
			}
		}
		
		//условия победы и поражения
		
		void wincheck(int winpoint) {
			if (winpoint == 10) {
				JOptionPane.showConfirmDialog(null,"Победа!!!", "", JOptionPane.CLOSED_OPTION);
				winpoint = 0;
				losepoint = 0;
				programStatus = false;
				zubrilkaFrame.changeLayout.show(zubrilkaFrame.cardPanel, "training");
			}
		}
		
		void losecheck(int losepoint) {
			if (losepoint == 10) {
				JOptionPane.showConfirmDialog(null,"Поражение!!!", "", JOptionPane.CLOSED_OPTION);
				losepoint = 0;
				winpoint = 0;
				programStatus = false;
				zubrilkaFrame.changeLayout.show(zubrilkaFrame.cardPanel, "training");
			}
		}
		
}
	

