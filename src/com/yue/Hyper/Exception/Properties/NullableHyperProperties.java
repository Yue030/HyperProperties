package com.yue.Hyper.Exception.Properties;

import com.yue.Hyper.HyperProperties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class NullableHyperProperties implements HyperProperties {

    /**
     * File
     */
    private File file = null;

    /**
     * Constructor.
     */
    public NullableHyperProperties() {
        setFile(null);
    }

    /**
     * Constructor.
     * @param file FileURL
     */
    public NullableHyperProperties(File file) {
        setFile(file);
    }

    /**
     * Constructor and call {@link #createProp(String, String)}.
     * @param file File URL
     * @param key Key
     * @param value Value
     */
    public NullableHyperProperties(File file, String key, String value) {
        setFile(file);
        createProp(key, value);
    }

    /**
     * Constructor and call {@link #createProp(Map)}.
     * @param file File URL
     * @param map Map
     */
    public NullableHyperProperties(File file, Map<String, String> map) {
        setFile(file);
        createProp(map);
    }

    /**
     * Properties.
     */
    private final Properties properties = new Properties();

    /**
     * Get properties.
     *
     * @return Properties
     */
    @Override
    public Properties getProperties() {
        return this.properties;
    }

    /**
     * Get the file.
     *
     * @return File URL
     */
    @Override
    public File getFile() {
        return this.file;
    }

    /**
     * Set the file.
     *
     * @param file File URL
     */
    @Override
    public void setFile(File file) {
        this.file = file;
    }

    /**
     * read Properties File URL (Nullable)
     *
     * @param key String
     * @return String
     */
    @Override
    public String readProp(String key) {
        try (FileInputStream inputStream = new FileInputStream(file)) {
            properties.load(inputStream);

            StringBuilder builder = new StringBuilder();

            if (properties.getProperty(key.toLowerCase()) != null)
                builder.append(key.toLowerCase()).append("=").append(properties.getProperty(key.toLowerCase())).append("\n");

            return builder.toString();
        } catch (NullPointerException e) {
            return "File is Not Exist on method \"readProp(String key)\"";
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Something Error in method \"readProb(File file)\"";
    }

    /**
     * read Properties File URL (Nullable)
     *
     * @param keys List
     * @return String
     */
    @Override
    public String readProp(List<String> keys) {
        try (FileInputStream inputStream = new FileInputStream(file)) {
            properties.load(inputStream);

            StringBuilder builder = new StringBuilder();

            keys.forEach((key) -> {
                if (properties.getProperty(key.toLowerCase()) != null)
                    builder.append(key.toLowerCase()).append("=").append(properties.getProperty(key.toLowerCase())).append("\n");
            });

            return builder.toString();
        } catch (NullPointerException e) {
            return "File is Not Exist on method \"readProp(List<String> keys)\"";
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Something Error in method \"readProb(File file)\"";
    }

    /**
     * Get properties value. (Nullable)
     *
     * @param key Key
     * @return value or null
     */
    @Override
    public String getPropValue(String key) {
        try (FileInputStream inputStream = new FileInputStream(file)) {
            properties.load(inputStream);

            return properties.getProperty(key.toLowerCase());
        } catch (NullPointerException e) {
            return "File is Not Exist on method \"getPropValue(String key)\"";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return key;
    }

    /**
     * Get properties value. (Nullable)
     *
     * @param keys Keys List
     * @return list or null
     */
    @Override
    public List<String> getPropValue(List<String> keys) {
        List<String> list = new ArrayList<>();
        try (FileInputStream inputStream = new FileInputStream(file)) {
            properties.load(inputStream);
            keys.forEach((key) -> {
                if (properties.getProperty(key.toLowerCase()) != null)
                    list.add(properties.getProperty(key.toLowerCase()));
            });
            return list;
        } catch (NullPointerException e) {
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    /**
     * show all key.
     */
    @Override
    public void showAllKey() {
        try (FileInputStream in = new FileInputStream(file)) {
            properties.load(in);
            Set<Object> set = properties.keySet();

            set.forEach((k) -> {
                String key = (String) k;
                System.out.println(key);
            });
        } catch (NullPointerException e) {
            System.out.println("File is not exist on method \"showAllKey()\"");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * get all key.
     *
     * @return List
     */
    @Override
    public List<String> getAllKey() {
        List<String> list = new ArrayList<>();
        try (FileInputStream in = new FileInputStream(file)) {
            properties.load(in);
            Set<Object> set = properties.keySet();

            set.forEach((k) -> {
                String key = (String) k;
                list.add(key);
            });
        } catch (NullPointerException e) {
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * show all value.
     */
    @Override
    public void showAllValue() {
        try (FileInputStream in = new FileInputStream(file)){
            properties.load(in);
            Set<Object> set = properties.keySet();

            set.forEach((k) -> {
                String key = (String)k;
                System.out.println(properties.getProperty(key));
            });
        } catch (NullPointerException e) {
            System.out.println("File is Not Exist on method \"showAllValue()\"");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * get all value.
     *
     * @return List
     */
    @Override
    public List<String> getAllValue() {
        List<String> list = new ArrayList<>();
        try (FileInputStream in = new FileInputStream(file)){
            properties.load(in);
            Set<Object> set = properties.keySet();

            set.forEach((k) -> {
                String key = (String)k;
                list.add(properties.getProperty(key));
            });
        } catch (NullPointerException e) {
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * show all key and value.
     */
    @Override
    public void showAll() {
        try (FileInputStream in = new FileInputStream(file)){
            properties.load(in);
            Set<Object> set = properties.keySet();

            set.forEach((k) -> {
                String key = (String) k;
                System.out.println(key + ": " + properties.getProperty(key));
            });
        } catch (NullPointerException e) {
            System.out.println("File is Not Exist on method \"showAll()\"");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * get all key and value.
     *
     * @return String
     */
    @Override
    public Map<Object, Object> getAll() {
        Set<Object> set = properties.keySet();
        Map<Object, Object> map = new HashMap<>();
        try (FileInputStream in = new FileInputStream(file)){
            properties.load(in);
            set.forEach((o -> map.put(o, properties.get(o))));

            return map;
        } catch (NullPointerException e) {
            return map;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Create Properties.
     *
     * @param key   Key
     * @param value Value
     * @return File exist, return false, else create the properties file and return true
     */
    @Override
    public boolean createProp(String key, String value) {
        try (FileOutputStream out = new FileOutputStream(file)) {
            if (!file.exists()) {
                properties.setProperty(key.toLowerCase(), value);
                properties.store(out, null);

                return true;
            }
        } catch (NullPointerException e) {
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    /**
     * Create Properties.
     *
     * @param map Map
     * @return File exist, return false, else create the properties file and return true
     */
    @Override
    public boolean createProp(Map<String, String> map) {
        try (FileOutputStream out = new FileOutputStream(file)) {
            if (!file.exists()) {
                map.forEach((key, value) -> properties.setProperty(key.toLowerCase(), value));
                properties.store(out, null);

                return true;
            }
        } catch (NullPointerException e) {
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    /**
     * Remove the properties.
     *
     * @return if remove success, return true
     */
    @Override
    public boolean removeProp() {
        try {
            return file.delete();
        } catch (NullPointerException e) {
            return false;
        }
    }

    /**
     * toString
     * @return String
     */
    @Override
    public String toString() {
        return this.getAll().toString();
    }
}
