/*Создаем основной контейнер
 * создаем панель для логина
 * 
 * Задачи:
 * 1. Ввод логина пользователя
 * 2. проверка логина
 * 4. если логин новый - создаём нового пользователя
 * 5. если логин старый - ничего не создаём, загружаем меню с имеющимся
 * 6. выходим из программы
 * 
 */


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class LoginClass extends JPanel {
	JLabel greetings;
	JLabel greetings2;
	JTextField userlogin;
	JButton usetExit;
	JLabel userList;
	JLabel userListLabel;
	
	String [] arrayList;
	JList<String> jlist;
	
	DataBase base;
	UserDictionary user;
	
	JPanel uppanel;
	JPanel downpanel;
	JScrollPane userListPane;

	int userinput;

	LoginClass(MainZubrilka zubrilkaFrame){
		
		/*
		 * Создаем панель входа
		 */
		uppanel = new JPanel();
		uppanel.setLayout(new GridLayout(5,1,5,5));
		downpanel = new JPanel();
		// есть ли файл, есть ли в файле список пользователей
		this.fileIsCreated();
		greetings = new JLabel("<html><font size = 6>Добро пожаловать в Зубрилку!<font>");
		greetings2 = new JLabel("<html><font size =4>Введи своё имя для входа. 10 букв - не больше!<font>");			
		greetings.setHorizontalAlignment(JLabel.CENTER);
		greetings2.setHorizontalAlignment(JLabel.CENTER);
		// получаем логин пользователя
		userlogin = new JTextField(10);
		userlogin.setFont(new Font("Serif",Font.BOLD,15));
		// пользователь ввёд своё имя
        userlogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//ищем пользователя в базе.
				//System.out.println("Пробую читать в списке!");
				try {
					user = base.getDataBase(userlogin.getText()); // присваиваем user ссылку на объект из базы, если там есть введённый логин
					if (user == null) {
						System.out.println("Пользователь не найден");
						userinput = JOptionPane.showConfirmDialog(null,"Создадим нового пользователя?", "", JOptionPane.YES_NO_OPTION);
						if (userinput == 0) {
							user = new UserDictionary(userlogin.getText());// новый пользователь создание
							//System.out.println("Новый пользователь создан " + user.username);
							
							// сохраняем объект в массив объектов
							base.setDataBase(user);
							// сохраняем массив в файле
							new WriteDictionary(base);
							
							
						}
					}	
				} catch (NullPointerException e) {	
					System.out.println("Не рабочий блок");
				}	
				if (user != null) {
					//System.out.println("Переключаю в меню!");
					zubrilkaFrame.menupanel.menuUserGreeting.setText("<html><font size = 6>Привет, " + user.username + "!<font>");
					zubrilkaFrame.menupanel.menuUserGreeting.setHorizontalAlignment(JLabel.CENTER);
					zubrilkaFrame.changeLayout.show(zubrilkaFrame.cardPanel, "menu");
				} 
			  }
		});
		// кнопка выхода
				usetExit = new JButton("<html><font size=4>Выход<font>");	
				usetExit.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						zubrilkaFrame.dispatchEvent(new WindowEvent(zubrilkaFrame, WindowEvent.WINDOW_CLOSING));
					}
				});
		userList = new JLabel("<html><font size =4>Список созданных пользователей:<font>");
		jlist = new JList<String>(arrayList);
		jlist.setFont(new Font("Serif", Font.BOLD, 15));
		jlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jlist.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) { // создаём меню для выбора действий с пользователем
				int indexList = jlist.getSelectedIndex();
				int userchoser;
				Object [] toConfirm = {"Войти", "Удалить"};
				/*
				 * забрать имя удалить или войти через меню возникающее
				 */
				userchoser = JOptionPane.showOptionDialog(null, "Войти или удалить этого пользователя?", 
						"Zubrilka", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, toConfirm, toConfirm[0] );
				if (userchoser == 0) {
					userlogin.setText(arrayList[indexList]);
					user = base.getDataBase(userlogin.getText());
					
					zubrilkaFrame.menupanel.menuUserGreeting.setText("<html><font size = 6>Привет, " + user.username + "!<font>");
					zubrilkaFrame.menupanel.menuUserGreeting.setHorizontalAlignment(JLabel.CENTER);
					zubrilkaFrame.changeLayout.show(zubrilkaFrame.cardPanel, "menu");
				} else if(userchoser == 1) {
					System.out.println(base.database);
					base.database.remove(indexList);
					System.out.println("Удаляем по индексу " + indexList);
					System.out.println(base.database);
					new WriteDictionary(base); // удалив пользователя сохраняем только базу!!!!
				} else {}
			}
		});
		
		userListPane = new JScrollPane(jlist);
		userListPane.setFont(new Font("SERIF", Font.PLAIN, 20));
		userListPane.setPreferredSize(new Dimension(415,200));		
				
		uppanel.add(greetings);
		uppanel.add(greetings2);		
		uppanel.add(userlogin);	
		uppanel.add(usetExit);	
		uppanel.add(userList);
		
		downpanel.add(userListPane);
				
		this.setLayout(new GridLayout(2,1));
		this.add(uppanel);
		this.add(downpanel);
		
	}
	
	void fileIsCreated() {
		//if(!new File("userdata").exists()) new File("userdata").mkdirs();
		File dir = new File("userdata");
		dir.mkdir();
		File file = new File("userdata", "userlibs.bin"); // создаём путь папка, файл
		try {
			if(file.createNewFile()) {
				System.out.println("Создаю файл"); //создаём файл в папке если его там нет
				if (base == null) {
					System.out.println("Создаю новую базу");
					base = new DataBase();
					arrayList = new String[base.database.size()];
					int n = 0;
					for (UserDictionary user: base.database) {
						arrayList[n] = user.username;
						n++;
						System.out.println(user.username);
					}
				}
			}
			else {
				//System.out.println("Новый файл не нужен. Читаю файл...");
				base = new ReadDictionary().getBase();
				arrayList = new String[base.database.size()];
				int n = 0;
				for (UserDictionary user: base.database) {
					arrayList[n] = user.username;
					n++;
					//System.out.println(user.username);
				}
				
				if (base == null) {
					//System.out.println("Создаю новую базу");
					base = new DataBase();
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	UserDictionary getUser() {
		return user;
	}
	
	DataBase getBase() {
		return base;
	}
	
	
}
