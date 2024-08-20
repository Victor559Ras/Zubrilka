/*
 * Данный класс создан для отображения всего словаря пользователя
 * Также класс может сортировать словарь по русскому и английскому алфавиту 
 * 
 */


import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class AllWordsPanel extends JPanel implements ActionListener{
	String viewDict = "****";
	JButton toMenu;
	JButton toFixDictionary;
	JScrollPane dictPane;
	JTextArea text;
	Map <String, String> map;
	MainZubrilka zubrilkaFrame;
	
	JButton engOrder;
	JButton rusOrder;
	
	
	 
	AllWordsPanel(MainZubrilka zubrilkaFrame){
		
		this.zubrilkaFrame = zubrilkaFrame;
		
		this.setLayout(new BorderLayout());
		text = new JTextArea(viewDict);//создаём компонент с текстом
		dictPane = new JScrollPane(text);// создаём компонент со скроллом и компонентом с текстом
		dictPane.setPreferredSize(getPreferredSize());
		
		toMenu = new JButton("В меню");
		toMenu.addActionListener(this);
		toMenu.setActionCommand("tomenu");
		
		toFixDictionary = new JButton("В словарь");
		toFixDictionary.addActionListener(this); 
		toFixDictionary.setActionCommand("toDict");
		
		// кнопка слртировки по английскому алфавиту
		engOrder = new JButton("<html>E<br>n<br>g<br>l<br>i<br>s<br>h</html>");
		engOrder.addActionListener(this);
		engOrder.setActionCommand("engorder");
		
		// кнопка сортировки по русскому алфвиту
		rusOrder = new JButton("<html>R<br>u<br>s<br>s<br>i<br>a<br>n</html>");
		rusOrder.addActionListener(this);
		rusOrder.setActionCommand("rusorder"); 
				
		this.add(dictPane, BorderLayout.CENTER);
		
		// панель для кнопок в панели БордерЛэйаут
		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayout(1,2));
		panel1.add(toMenu);
		panel1.add(toFixDictionary);
		
		
		this.add(panel1, BorderLayout.SOUTH);
		this.add(engOrder, BorderLayout.WEST);
		this.add(rusOrder, BorderLayout.EAST);
		
	}
	
	// создаём неупорядоченный словарь в виде строки
	String formatMap(Map <String, String> map) { 
		String dictViewFull = "";
		for(Map.Entry<String, String> entry: map.entrySet()) {
			String key = entry.getKey();
			dictViewFull = dictViewFull + key + " - ";
			String value = entry.getValue();
			dictViewFull = dictViewFull + value + "\n";
			}
		return dictViewFull;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("tomenu")) {
			zubrilkaFrame.changeLayout.show(zubrilkaFrame.cardPanel, "menu");
		}
		if(e.getActionCommand().equals("toDict")) {
			zubrilkaFrame.changeLayout.show(zubrilkaFrame.cardPanel,"dictPanel");
		}
		if(e.getActionCommand().equals("engorder")) {
			// расставить ключи по алфавиту
			String engString = engSort(zubrilkaFrame.dictFixPanel.user.userDict);
			text.setFont(new Font ("Serif", Font.BOLD, 15));
			this.text.setText(engString);
		}
		if(e.getActionCommand().equals("rusorder")) {
			//расставить значения по афавиту
			String rusString = rusSort(zubrilkaFrame.dictFixPanel.user.userDict);
			text.setFont(new Font ("Serif", Font.BOLD, 15));
			this.text.setText(rusString);
		}
	}
	
	// Сортировка по английскому алфавиту
	
	String engSort(Map <String, String> map) {
		List<String> englishKey = new ArrayList<>(map.keySet());
		Collections.sort(englishKey);
		//System.out.println(englishKey);
		String dictViewFull = "";
		// перебрать список ключей
		// каждый ключ сохранять в стринг
		// для каждого ключа найти значение
		// сохранить значение в стринг
		for (String entry: englishKey) {
			dictViewFull = dictViewFull + entry + " - " + map.get(entry) + "\n";
		}
		return dictViewFull;
	}
	
	// Сортируем по русскому алфавиту
	
	String rusSort(Map <String, String> map) {
		List<String> rusValue = new ArrayList<>(map.values());
		Collections.sort(rusValue);
		String dictViewFull = "";
		String keyStr = "";
		// перебираем отсортированные значения из ArrayList
		for(String entryValue: rusValue) {
			dictViewFull = dictViewFull + entryValue + " - ";
		// подбираем значения и сохраняем ключ под значение	
			for(Map.Entry<String, String> entry: map.entrySet()) {
				if(entryValue.equals(entry.getValue())) keyStr = entry.getKey();
			}
			dictViewFull = dictViewFull + keyStr + "\n";
		}
		return dictViewFull;
	}
}
