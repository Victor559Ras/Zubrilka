/*
 * Класс создания пользователя и его словаря
 * 
 * 
 * - получаем имя 
 * - создаём словарь
 * - добавляем слова в словарь (ключ - знвчение)
 * - получаем слова (ключи)
 * - получаем слова (значения) 
 * - заносим нового пользователя в массив пользователей
 * 
 */


import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.*;

public class UserDictionary implements Serializable {
	private static final long serialVersionUID = -7607365590149025089L;
	String username; // имя пользователя
	Map <String, String> userDict; // словарь пользователя
	ArrayList<String> translatedWords;
		
	//конструктор
	public UserDictionary(String userName) {
		username = userName;
		userDict = new HashMap<>();
		translatedWords = new ArrayList<>();
		
	}	
	
	//записать новое слово и перевод в словарь
	void setTwoWordSet(String keyWord, String translationWord) {
		userDict.put(keyWord, translationWord);
	}
	
	// Это будет работать корректно если ключ не пустота!!!!!!! Нужна проверка null
	String getKeyWord(String translationWord) {
		String keyWord = "";
		for(Map.Entry<String, String> entry: userDict.entrySet()) {
			if(translationWord.equals(entry.getValue())) keyWord = entry.getKey();
			System.out.println(translationWord +" --- "+ entry.getValue());
		}
		return keyWord;
	}
	
	String getTranslation(String keyword) {
		return userDict.get(keyword);
	}
	
	void deleteWords(String keyword) {
		System.out.println(userDict);
		userDict.remove(keyword);
		System.out.println(userDict);
	}
	
	
	
	
	
	
	
}
