package com.yue.Hyper;

import com.yue.Hyper.Exception.FileNotFoundException;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.*;

public class HyperProperties {
    public static void main(String[] args) throws FileNotFoundException {
    }

    /**
     * File
     */
    private File file = null;

    /**
     * Constructor.
     * @param file FileURL
     */
    public HyperProperties(File file) {
        setFile(file);
    }

    /**
     * Constructor and call {@link #createProp(String, String)}.
     * @param file File URL
     * @param key Key
     * @param value Value
     */
    public HyperProperties(File file, String key, String value) {
        setFile(file);
        createProp(key, value);
    }

    /**
     * nstructor cand all {@link #createProp(Map)}.
     * @param file File URL
     * @param map Map
     */
    public HyperProperties(File file, Map<String, String> map) {
        setFile(file);
        createProp(map);
    }

    /**
     * Properties.
     */
    private final Properties properties = new Properties();

    /**
     * Get properties.
     * @return Properties
     */
    public Properties getProperties() {
        return this.properties;
    }

    /**
     * Get the file.
     * @return File URL
     */
    public File getFile() {
        return this.file;
    }

    /**
     * Set the file.
     * @param file File URL
     */
    public void setFile(File file) {
        this.file = file;
    }

    /**
     * read Properties File URL
     * @param key String
     * @return String
     */
    public @NotNull String readProp(String key) throws FileNotFoundException {
        if(!file.exists())
            throw new FileNotFoundException("The File in \"" + file + "\" is not exists");
        try (FileInputStream inputStream = new FileInputStream(file)){
            properties.load(inputStream);

            StringBuilder builder = new StringBuilder();

            if (properties.getProperty(key.toLowerCase()) != null)
                builder.append(key.toLowerCase()).append(": ").append(properties.getProperty(key.toLowerCase())).append("\n");

            return builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Something Error in method \"readProb(File file)\"";
    }

    /**
     * read Properties File URL
     * @param keys List
     * @return String
     */
    public @NotNull String readProp(List<String> keys) throws FileNotFoundException {
        if(!file.exists())
            throw new FileNotFoundException("The File in \"" + file + "\" is not exists");
        try (FileInputStream inputStream = new FileInputStream(file)){
            properties.load(inputStream);

            StringBuilder builder = new StringBuilder();

            keys.forEach((key) -> {
                if (properties.getProperty(key.toLowerCase()) != null)
                    builder.append(key.toLowerCase()).append(": ").append(properties.getProperty(key.toLowerCase())).append("\n");
            });

            return builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Something Error in method \"readProb(File file)\"";
    }

    /**
     * read Properties File URL with Nullable
     * @param key String
     * @return String
     */
    public @NotNull String readPropOfNullable(String key) throws FileNotFoundException {
        if(!file.exists())
            throw new FileNotFoundException("The File in \"" + file + "\" is not exists");
        try (FileInputStream inputStream = new FileInputStream(file)){
            properties.load(inputStream);
            return key.toLowerCase() + ": " +
                    properties.getProperty(key.toLowerCase(), "Can't get the value from Key [" + key.toLowerCase() +"]")
                    + "\n";
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Something Error in method \"readProb(File file)\"";
    }

    /**
     * read Properties File URL
     * @param keys List
     * @return String
     **/
    public @NotNull String readPropOfNullable(List<String> keys) throws FileNotFoundException {
        if(!file.exists())
            throw new FileNotFoundException("The File in \"" + file + "\" is not exists");
        try (FileInputStream inputStream = new FileInputStream(file)){
            properties.load(inputStream);

            StringBuilder builder = new StringBuilder();

            keys.forEach((key) -> builder.append(key.toLowerCase()).append(": ").append(
                    properties.getProperty(key.toLowerCase(), "Can't get the value from Key [" + key.toLowerCase() +"]")).append("\n"));

            return builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Something Error in method \"readProb(File file)\"";
    }

    /**
     * Get properties value.
     * @param key Key
     * @return value or null
     * @throws FileNotFoundException if file is not exists
     */
    public String getPropValue(String key) throws FileNotFoundException {
        if(!file.exists())
            throw new FileNotFoundException("The File in \"" + file + "\" is not exists");
        try (FileInputStream inputStream = new FileInputStream(file)){
            properties.load(inputStream);

            return properties.getProperty(key.toLowerCase());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get properties value.
     * @param keys Keys List
     * @return list or null
     * @throws FileNotFoundException if file is not exists
     */
    public List<String> getPropValue(List<String> keys) throws FileNotFoundException {
        List<String> list = new ArrayList<>();

        if(!file.exists())
            throw new FileNotFoundException("The File in \"" + file + "\" is not exists");
        try (FileInputStream inputStream = new FileInputStream(file)){
            properties.load(inputStream);

            keys.forEach((key) -> {
                if (properties.getProperty(key.toLowerCase()) != null)
                    list.add(properties.getProperty(key.toLowerCase()));
            });

            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Get properties value. (Nullable)
     * @param keys Keys List
     * @return list or null
     * @throws FileNotFoundException if file is not exists
     */
    public @NotNull List<String> getPropValueOfNullable(List<String> keys) throws FileNotFoundException {
        List<String> list = new ArrayList<>();

        if(!file.exists())
            throw new FileNotFoundException("The File in \"" + file + "\" is not exists");
        try (FileInputStream inputStream = new FileInputStream(file)){
            properties.load(inputStream);
            keys.forEach((key) -> list.add(properties.getProperty(key.toLowerCase(), "Can't get the value from Key [" + key.toLowerCase() +"]")));

            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return keys;
    }

    /**
     * Get properties value. (Nullable)
     * @param key Key
     * @return value or null
     * @throws FileNotFoundException if file is not exists
     */
    public @NotNull String getPropValueOfNullable(String key) throws FileNotFoundException {
        if(!file.exists())
            throw new FileNotFoundException("The File in \"" + file + "\" is not exists");
        try (FileInputStream inputStream = new FileInputStream(file)){
            properties.load(inputStream);

            return properties.getProperty(key.toLowerCase(), "Can't get the value from Key [" + key.toLowerCase() +"]");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return key;
    }

    /**
     * show all key.
     */
    public void showAllKey() throws FileNotFoundException {
        if (!file.exists())
            throw new FileNotFoundException("The File in \"" + file + "\" is not exists");
        try (FileInputStream in = new FileInputStream(file)){
            properties.load(in);
            Set<Object> set = properties.keySet();

            set.forEach((k) -> {
                String key = (String)k;
                System.out.println(key);
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * get all key.
     * @return List
     */
    public List<String> getAllKey() throws FileNotFoundException {
        List<String> list = new ArrayList<>();
        if(!file.exists())
            throw new FileNotFoundException("The File in \"" + file + "\" is not exists");

        try (FileInputStream in = new FileInputStream(file)){
            properties.load(in);
            Set<Object> set = properties.keySet();

            set.forEach((k) -> {
                String key = (String)k;
                list.add(key);
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * show all value.
     */
    public void showAllValue() throws FileNotFoundException {
        if(!file.exists())
            throw new FileNotFoundException("The File in \"" + file + "\" is not exists");
        try (FileInputStream in = new FileInputStream(file)){
            properties.load(in);
            Set<Object> set = properties.keySet();

            set.forEach((k) -> {
                String key = (String)k;
                System.out.println(properties.getProperty(key));
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * get all value.
     * @return List
     */
    public List<String> getAllValue() throws FileNotFoundException {
        List<String> list = new ArrayList<>();
        if(!file.exists())
            throw new FileNotFoundException("The File in \"" + file + "\" is not exists");

        try (FileInputStream in = new FileInputStream(file)){
            properties.load(in);
            Set<Object> set = properties.keySet();

            set.forEach((k) -> {
                String key = (String)k;
                list.add(properties.getProperty(key));
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * show all key and value.
     */
    public void showAll() throws FileNotFoundException {
        if(!file.exists())
            throw new FileNotFoundException("The File in \"" + file + "\" is not exists");
        try (FileInputStream in = new FileInputStream(file)){
            properties.load(in);
            Set<Object> set = properties.keySet();

            set.forEach((k) -> {
                String key = (String)k;
                System.out.println(key+ ": " + properties.getProperty(key));
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * get all key and value.
     * @return String
     */
    public Map<Object, Object> getAll() throws FileNotFoundException {
        if(!file.exists())
            throw new FileNotFoundException("The File in \"" + file + "\" is not exists");

        try (FileInputStream in = new FileInputStream(file)){
            properties.load(in);
            Set<Object> set = properties.keySet();
            Map<Object, Object> map = new HashMap<>();
            set.forEach((o -> map.put(o, properties.get(o))));

            return map;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Create Properties.
     * @param key Key
     * @param value Value
     * @return File exist, return false, else create the properties file and return true
     */
    public boolean createProp(String key, String value) {
        if(!file.exists()) {
            try (FileOutputStream out = new FileOutputStream(file)) {
                properties.setProperty(key.toLowerCase(), value);

                properties.store(out, null);
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }
        return false;
    }

    /**
     * Create Properties.
     * @param map Map
     * @return File exist, return false, else create the properties file and return true
     */
    public boolean createProp(Map<String, String> map) {
        if (!file.exists()) {
            try (FileOutputStream out = new FileOutputStream(file)) {
                map.forEach((key, value) -> properties.setProperty(key.toLowerCase(), value));
                properties.store(out, null);
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }
        return false;
    }

    /**
     * Remove the properties.
     * @return if remove success, return true
     */
    public boolean removeProp() {
        if (!file.exists())
            return false;
        return file.delete();
    }

    /**
     * toString
     * @return String
     */
    @Override
    public String toString() {
        try {
            return this.getAll().toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
