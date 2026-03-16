import java.io.*;
import java.util.TreeMap;

public class storageService {

    private static final String FILE_NAME = "user.dat";

    public void saveUsers(TreeMap<String , User> users){
        try(ObjectOutputStream oos =
                new ObjectOutputStream(new FileOutputStream(FILE_NAME))){
            oos.writeObject(users);

        }catch (IOException e){
            System.out.println("❌Error saving data.");
        }
    }
    public TreeMap<String, User>loadUsers(){
        File file =new File(FILE_NAME);
        if(!file.exists() || file.length() == 0){
            return new TreeMap<>();
        }
        try(ObjectInputStream ois =
                new ObjectInputStream(new FileInputStream(FILE_NAME))){

            Object data = ois.readObject();

            if (data instanceof TreeMap){
                return (TreeMap<String , User>) data;
            }

        }catch(Exception e){
            System.out.println("❌Corrupted data file. Starting fresh.");
        }
        return new TreeMap<>();
    }
}
