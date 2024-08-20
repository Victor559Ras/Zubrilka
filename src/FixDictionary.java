/*
 * Панель редактирования словаря
 * Добавляем, удаляем пары слов
 * 
 */

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FixDictionary extends JPanel implements ActionListener {
	
	UserDictionary user;
	DataBase base;
	
	MainZubrilka zubrilkaFrame;
	WriteDictionary saveobject;
	
	String word = "";
	String perevod = "";
	
	JLabel title;
	
	JLabel addWord;
	JTextField addword;
	JLabel addTranslation;
	JTextField addtranslation;
	
	JButton saveButtton;
	JButton allDictionaryView;
	
	
	JLabel findWord;
	JTextField findword;
	JLabel findTranslation;
	JTextField findtranslation;
	JButton deleteButton;
	
	JButton returnToMenu;
	int fontSize = 4;
	
	
	public FixDictionary(MainZubrilka zubrilkaFrame) {
		
		this.zubrilkaFrame = zubrilkaFrame;
		
		
		this.setLayout(new GridLayout(9,2, 10, 10));
		
	//	System.out.println("Создаём словарь для " + object.user.username);
		
		
		title = new JLabel("<html><font size = fontSize>Меняем ваш словарь<font>"); //<html><font size = 4> <font>
	
		addWord = new JLabel("<html><font size = fontSize>Добавьте английское слово:<font>");
		addword = new JTextField(10);
		addword.setFont(new Font(Font.SERIF,Font.PLAIN,20));
		addword.setActionCommand("addenglishword");
		addword.addActionListener(this);
		
		addTranslation = new JLabel("<html><font size = fontSize>Добавьте перевод:<font>");
		addtranslation = new JTextField(10);
		addtranslation.setFont(new Font(Font.SERIF,Font.PLAIN,20));
		addtranslation.setActionCommand("addwordsTranslation");
		addtranslation.addActionListener(this);
		
		saveButtton = new JButton("<html><font size = fontSize>Сохранить?<font>");// сохранение пары слов
		saveButtton.addActionListener(this);
		saveButtton.setActionCommand("savecouple");
		
		allDictionaryView = new JButton("<html><font size = fontSize>Показать весь словарь<font>");
		allDictionaryView.setActionCommand("dictionary");
		allDictionaryView.addActionListener(this);
		
		findWord = new JLabel("<html><font size = fontSize>Иностранное слово: <font>");
		findword = new JTextField(10);
		findword.setFont(new Font(Font.SERIF,Font.PLAIN,20));
		findword.addActionListener(this);
		findword.setActionCommand("findenglishword");
		
		findTranslation = new JLabel("<html><font size = fontSize>Перевод слова: <font>"); 
		findtranslation = new JTextField(10);
		findtranslation.setFont(new Font(Font.SERIF,Font.PLAIN,20));
		findtranslation.addActionListener(this);
		findtranslation.setActionCommand("findtranslation");
		
		deleteButton = new JButton("<html><font size = fontSize>Удалить пару<font>");// удаление пары слов
		deleteButton.setActionCommand("deleteWords");
		deleteButton.addActionListener(this);
		
		returnToMenu = new JButton("<html><font size = fontSize>Вернуться в меню<font>");
		returnToMenu.setActionCommand("возвратВМеню");
		returnToMenu.addActionListener(this);
		
		this.add(title);
		this.add(new JLabel());
		this.add(addWord);
		this.add(addword);
		
		this.add(addTranslation);
		this.add(addtranslation);
		
		this.add(saveButtton);
		this.add(allDictionaryView);

		this.add(findWord);
		this.add(findword);
		
		this.add(findTranslation);
		this.add(findtranslation);
		
		this.add(deleteButton);
		this.add(new JLabel());
		this.add(returnToMenu);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		user = zubrilkaFrame.loginpanel.getUser();
		base = zubrilkaFrame.loginpanel.getBase();
		
		
		if (e.getActionCommand().equals("возвратВМеню")) {
			zubrilkaFrame.changeLayout.show(zubrilkaFrame.cardPanel, "menu");
		}
		if (e.getActionCommand().equals("savecouple")) { // сохранить пару
			word = addword.getText();
			perevod = addtranslation.getText();
			if(word.equals("") || perevod.equals("")) {
				new JOptionPane().showConfirmDialog(null,"Слова введены некорректно!", perevod, JOptionPane.CLOSED_OPTION);
			} else {
				// сохраняем слова в объект Пользователь
				user.setTwoWordSet(word, perevod);
				// сохраняем объект в массив объектов
				base.setDataBase(user);
				// сохраняем массив в файле
				new WriteDictionary(base);
				addword.setText("");
				addtranslation.setText("");
			}
		}
		if (e.getActionCommand().equals("findenglishword")) {
			//ввели ключ - английское слово
			String translation = "не нашел((";
			translation = user.getTranslation(findword.getText());
			//вывести значение 
			this.findtranslation.setText(translation);
		}
		
		if (e.getActionCommand().equals("findtranslation")) {
		// ввели значение - слово на русском
			String translation = "do not find";
			translation = user.getKeyWord(findtranslation.getText());
			this.findword.setText(translation);
			System.out.println("Ищу слово " + findtranslation.getText() + ". Нахожу " + translation);
		}
		
		if (e.getActionCommand().equals("deleteWords")) {
			String wordToDelete = findword.getText();
			user.deleteWords(wordToDelete);
		}
		
		if(e.getActionCommand().equals("dictionary")) {
			Map <String, String> userMap = user.userDict;
			String dictionary = zubrilkaFrame.dictPanel.formatMap(userMap);
			zubrilkaFrame.dictPanel.text.setFont(new Font ("Serif", Font.BOLD, 15));
			zubrilkaFrame.dictPanel.text.setText(dictionary);
			zubrilkaFrame.changeLayout.show(zubrilkaFrame.cardPanel, "allWordsPanel");
		}	
	}
}
