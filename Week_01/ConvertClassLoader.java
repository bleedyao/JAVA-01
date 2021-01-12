import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class ConvertClassLoader extends ClassLoader {
    public static void main(String[] args) {
        try {
            Object obj = new ConvertClassLoader().findClass("Week_01/Hello.xlass").newInstance();
            obj.getClass().getMethod("hello").invoke(obj);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        File file = new File(name);
        if (!file.exists()) {
            throw new ClassNotFoundException("Not Found the class file");
        }
        byte[] bs = new byte[(int) file.length()];
        int index = 0;
        FileInputStream fr = null;
        // FileOutputStream fo = null;
        // System.out.println("file size: " + bs.length);
        try {
            fr = new FileInputStream(file);
            // fo = new FileOutputStream(new File("Hello.class"));
            int i;
            while ((i = fr.read()) != -1) {
                // System.out.println(i);
                i = 255 - i;
                bs[index++] = (byte) i;
                // fo.write(i);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fr.close();
                // fo.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return defineClass("Hello", bs, 0, bs.length);
    }

}
