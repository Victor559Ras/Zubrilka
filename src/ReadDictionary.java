import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ReadDictionary {
	
	DataBase usersDataBase;
	
	public ReadDictionary() {
		try {
			FileInputStream instream = new FileInputStream("userdata/userlibs.bin");
			ObjectInputStream inputstream = new ObjectInputStream(instream);
			
			usersDataBase = (DataBase) inputstream.readObject(); // что если список не найден?
				
			inputstream.close();
		} catch (FileNotFoundException e) {
			System.out.println("Файл не найден");	
		} catch (EOFException e) {
			System.out.println("Файл прочитан!!");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			//usersDataBase = new DataBase();
		}
		
		
	}
	
	public DataBase getBase() {
		return usersDataBase; // возвращаем объект с базой
	
	}
}











