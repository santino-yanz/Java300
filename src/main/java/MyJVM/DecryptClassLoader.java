package MyJVM;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Classname DecryptClassLoader
 * @Description TODO
 * @Date 6/11/20 19:51
 * @Created by mcyº
 */
public class DecryptClassLoader extends ClassLoader {
    private String rootDir;

    public DecryptClassLoader(ClassLoader parent, String rootDir) {
        super(parent);
        this.rootDir = rootDir;
    }

    public DecryptClassLoader(String rootDir) {
        this.rootDir = rootDir;
    }

    /**
     * Finds the class with the specified <a href="#name">binary name</a>.
     * This method should be overridden by class loader implementations that
     * follow the delegation model for loading classes, and will be invoked by
     * the {@link #loadClass <tt>loadClass</tt>} method after checking the
     * parent class loader for the requested class.  The default implementation
     * throws a <tt>ClassNotFoundException</tt>.
     *
     * @param name The <a href="#name">binary name</a> of the class
     * @return The resulting <tt>Class</tt> object
     * @throws ClassNotFoundException If the class could not be found
     * @since 1.2
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        Class<?> c = findLoadedClass(name);

        if (null != c) {
            return c;
        } else {
            ClassLoader parent = this.getParent();
            try {
                c = parent.loadClass(name); // 委派父类加载
            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
            }

            if (null != c) {
                return c;
            } else {
                byte[] classData = getClassData(name);
                if (null == classData) {
                    throw new ClassNotFoundException();
                } else {
                    c = defineClass(name, classData, 0, classData.length);
                }
            }
        }

        return c;

    }

    private byte[] getClassData(String name)  {

        String path = rootDir + "/" + name.replace('.', '/') +".class";

        InputStream inputStream = null;

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try {
            inputStream = new FileInputStream(path);
            int temp = -1;
            while (-1 != (temp = inputStream.read())) {
                byteArrayOutputStream.write(temp ^0xff);
            }
            return byteArrayOutputStream.toByteArray();

        } catch (IOException e) {
            e.printStackTrace();
            return  null;

        } finally {
            try {
                if (null != inputStream) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                byteArrayOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
