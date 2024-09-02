package frontend;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

public class Serialize {
    public static String daoRoute() {
        String proyectRoute = System.getProperty("user.dir");
        String route = proyectRoute + "/src/main/java/frontend/dao/";
        return route;
    }
    
    public static boolean serializeObject(String fileAdress, Serializable object) {

        boolean sw = false;
        try (FileOutputStream fos = new FileOutputStream(fileAdress);
            ObjectOutputStream exit = new ObjectOutputStream(fos);) {
            exit.writeObject(object);
            sw = true;

        } catch(Exception e) {
            e.printStackTrace();
        }
        return sw;
    }

    @SuppressWarnings("unchecked")
    public static <E> E deserializeObject(String fileAdress, Class<E> objetiveClass) {
        E objeto = null;
        try (FileInputStream fis = new FileInputStream(fileAdress);
            ObjectInputStream exit = new ObjectInputStream(fis);) {
            objeto = (E) exit.readObject();
                
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objeto;
    }

    public static <E extends Serializable> boolean serializeList(String fileAdress, List<E> objectList) {
        return serializeObject(fileAdress, (Serializable) objectList);
    }

    @SuppressWarnings("unchecked")
    public static <E> List<E> deserializeList(String fileAdress, Class<E> objetiveClass) {
        List<E> objectList = null;
        try {
            objectList = (List<E>) deserializeObject(fileAdress, List.class);
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return objectList;
    }
}
