import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class WriteDictionary {

	FileOutputStream writeToFile;
	ObjectOutputStream outStream;
	
	

	public WriteDictionary(DataBase usersDatabase) {
		
		try {
			System.out.println("Пишу в файл!");
			writeToFile = new FileOutputStream("userdata/userlibs.bin");
			outStream = new ObjectOutputStream(writeToFile);
			outStream.writeObject(usersDatabase);
			outStream.close();
		} catch (IOException e) {
			System.out.println("Не записал!");
			e.printStackTrace();
		}
	}
}
