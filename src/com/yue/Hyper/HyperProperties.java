package com.yue.Hyper;

import com.yue.Hyper.Exception.FileNotFoundException;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class HyperProperties {

    private File file = null;

    public static void main(String[] args) {

    }

    public HyperProperties(File file) {
        this.file = file;
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
            throw new FileNotFoundException("The File in \"" + file + "\" is exists");
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
            throw new FileNotFoundException("The File in \"" + file + "\" is exists");
        try (FileInputStream inputStream = new FileInputStream(file)){
            properties.load(inputStream);

            StringBuilder builder = new StringBuilder();

            for (String key : keys) {
                if (properties.getProperty(key.toLowerCase()) != null)
                    builder.append(key.toLowerCase()).append(": ").append(properties.getProperty(key.toLowerCase())).append("\n");
            }

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
            throw new FileNotFoundException("The File in \"" + file + "\" is exists");
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
            throw new FileNotFoundException("The File in \"" + file + "\" is exists");
        try (FileInputStream inputStream = new FileInputStream(file)){
            properties.load(inputStream);

            StringBuilder builder = new StringBuilder();

            for (String key : keys) {
                builder.append(key.toLowerCase()).append(": ").append(
                        properties.getProperty(key.toLowerCase(), "Can't get the value from Key [" + key.toLowerCase() +"]")).append("\n");
            }

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
    public @NotNull String getPropValue(String key) throws FileNotFoundException {
        if(!file.exists())
            throw new FileNotFoundException("The File in \"" + file + "\" is exists");
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
    public @NotNull List<String> getPropValue(List<String> keys) throws FileNotFoundException {
        List<String> list = new ArrayList<>();

        if(!file.exists())
            throw new FileNotFoundException("The File in \"" + file + "\" is exists");
        try (FileInputStream inputStream = new FileInputStream(file)){
            properties.load(inputStream);

            for (String key : keys) {
                if (properties.getProperty(key.toLowerCase()) != null)
                    list.add(properties.getProperty(key.toLowerCase()));
            }

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
            throw new FileNotFoundException("The File in \"" + file + "\" is exists");
        try (FileInputStream inputStream = new FileInputStream(file)){
            properties.load(inputStream);

            for (String key : keys) {
                list.add(properties.getProperty(key.toLowerCase(), "Can't get the value from Key [" + key.toLowerCase() +"]"));
            }

            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Get properties value. (Nullable)
     * @param key Key
     * @return value or null
     * @throws FileNotFoundException if file is not exists
     */
    public @NotNull String getPropValueOfNullable(String key) throws FileNotFoundException {
        if(!file.exists())
            throw new FileNotFoundException("The File in \"" + file + "\" is exists");
        try (FileInputStream inputStream = new FileInputStream(file)){
            properties.load(inputStream);

            return properties.getProperty(key.toLowerCase(), "Can't get the value from Key [" + key.toLowerCase() +"]");
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
        if(!file.exists()) {
            try (FileOutputStream out = new FileOutputStream(file)) {

                for(String key : map.keySet()) {
                    properties.setProperty(key.toLowerCase(), map.get(key));
                }

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
}
