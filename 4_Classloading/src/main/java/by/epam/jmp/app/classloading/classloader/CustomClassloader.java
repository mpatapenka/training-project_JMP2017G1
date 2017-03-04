package by.epam.jmp.app.classloading.classloader;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CustomClassloader extends ClassLoader {

    private static final Logger LOG = Logger.getLogger(CustomClassloader.class);

    private String pathToDir;
    private final Map<String, Class<?>> classCache = new HashMap<String, Class<?>>();

    private Class<?> findCachedClass(String name) {
        return classCache.get(name);
    }

    private void putInCache(String name, Class<?> clazz) {
        classCache.put(name, clazz);
    }

    private byte[] fetchClass(String fullPath) throws IOException {
        return FileUtils.readFileToByteArray(new File(fullPath));
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        synchronized (getClassLoadingLock(name)) {
            LOG.debug("loadClass for " + name + ": started");
            Class<?> clazz = findCachedClass(name);
            if (clazz == null) {
                try {
                    if (getParent() != null) {
                        clazz = getParent().loadClass(name);
                    } else {
                        clazz = findSystemClass(name);
                    }
                } catch (ClassNotFoundException e) {
                    // ClassNotFoundException thrown if class not found
                    // from the non-null parent class loader
                }

                if (clazz == null) {
                    clazz = findClass(name);
                }
            }
            if (resolve) {
                resolveClass(clazz);
            }

            LOG.debug("loadClass " + name + ": ended");
            return clazz;
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            LOG.debug("findClass for " + name + ": started");
            String simpleName = StringUtils.substringAfterLast(name, ".");
            byte[] bytes = fetchClass(pathToDir + simpleName + ".class");
            Class<?> clazz = defineClass(StringUtils.replaceAll(name, File.separator, "."), bytes, 0, bytes.length);
            putInCache(name, clazz);
            LOG.debug("findClass for " + name + ": ended");
            return clazz;
        } catch (Exception e) {
            LOG.debug("findClass for " + name + ": ended");
            return super.findClass(name);
        }
    }

    public CustomClassloader(String pathToDir) {
        this();
        this.pathToDir = pathToDir + File.separator;
    }

    public CustomClassloader() {
        super(CustomClassloader.class.getClassLoader());
    }

}