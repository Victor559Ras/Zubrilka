/*
 * класс создания базы пользователей 
 * все пользователи хранятся в одном массиве
 * массиа в одном файле
 * 
 */


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DataBase implements Serializable {
	private static final long serialVersionUID = -62871798906781205L;
	List <UserDictionary> database;
	
	public DataBase() {
		if(Objects.isNull(database)) database = new ArrayList <UserDictionary>();
	}
	
	// сохраняем базу в новом состоянии после ее изменении. Старую стираем, новую записываем
	void setDataBase(UserDictionary user){
		database.remove(user);
		
		//System.out.println("База до  ---" + database);
		database.add(user);
		//System.out.println("База после  ---" + database);
	}
	
	// ищем в базе пользователя по имени
	UserDictionary getDataBase(String nameOfUser){
		UserDictionary user = null;
		for(UserDictionary userInBase:database) {
			if (nameOfUser.equals(userInBase.username)) {
				user = userInBase;
			}
		}
		return user; // нужна проверка на null
	}
}
