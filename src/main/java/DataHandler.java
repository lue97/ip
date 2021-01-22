import java.io.*;

public class DataHandler {

    public static boolean saveBytes(String path, byte[] data) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(path)) {
            fileOutputStream.write(data);
        } catch (IOException ioException) {
            return false;
        }
        return true;
    }

    public static byte[] loadBytes(String path) {
        try (FileInputStream fileInputStream = new FileInputStream(path)) {
            return fileInputStream.readAllBytes();
        } catch (IOException fileNotFoundException) {
            return null;
        }
    }

    public static <T> T deserialize(byte[] data, Class<T> type) {
        Object object = null;
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data)) {
            try (ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream)) {
                object = objectInputStream.readObject();
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return type.cast(object);
    }

    public static <T> byte[] serialize(T object) {
        byte[] data = null;
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream)) {
                objectOutputStream.writeObject(object);
                objectOutputStream.flush();
                data = byteArrayOutputStream.toByteArray();
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return data;
    }
}
