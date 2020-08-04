package com.yue.Hyper.Properties;

import com.yue.Hyper.Exception.FileNotExistException;
import com.yue.Hyper.HyperProperties;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import java.util.stream.Stream;

public class BasicHyperProperties implements HyperProperties {
    /**
     * File
     */
    private File file = null;

    /**
     * Save data map.
     */
   private Map<String, Object> saveMap = new HashMap<>();

    /**
     * Save data preferences.
     */
    private final Preferences savePreferences = Preferences.userNodeForPackage(BasicHyperProperties.class);

    /**
     * Constructor.
     * @param file FileURL
     */
    public BasicHyperProperties(File file) {
        setFile(file);
    }

    /**
     * Constructor and call {@link #createProp(String, String)}.
     * @param file File URL
     * @param key Key
     * @param value Value
     */
    public BasicHyperProperties(File file, String key, String value) {
        setFile(file);
        createProp(key, value);
    }

    /**
     * Constructor and call {@link #createProp(Map)}.
     * @param file File URL
     * @param map Map
     */
    public BasicHyperProperties(File file, Map<String, Object> map) {
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
     * read Properties File URL
     *
     * @param key String
     * @return String
     */
    @Override
    public String readProp(String key) throws FileNotExistException {
        try (FileInputStream inputStream = new FileInputStream(file)) {
            properties.load(inputStream);

            return key.toLowerCase() + "=" +
                    properties.getProperty(key.toLowerCase(), "Null for [" + key.toLowerCase() + "]")
                    + "\n";
        } catch (FileNotFoundException e) {
            throw new FileNotExistException("The File in \"" + file + "\" is not exists", e);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Something Error in method \"readProb(File file)\"";
    }

    /**
     * read Properties File URL
     *
     * @param keys List
     * @return String
     */
    @Override
    public String readProp(List<String> keys) throws FileNotExistException {
        try (FileInputStream inputStream = new FileInputStream(file)) {
            properties.load(inputStream);

            StringBuilder builder = new StringBuilder();

            keys.stream().map(String::toLowerCase)
                    .forEach(
                            (key) -> builder.append(key).append("=").append(
                                    properties.getProperty(key, "Null for [" + key +"]")).append("\n"));

            return builder.toString();
        } catch (FileNotFoundException e) {
            throw new FileNotExistException("The File in \"" + file + "\" is not exists", e);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Something Error in method \"readProb(File file)\"";
    }

    /**
     * Get properties value.
     *
     * @param key Key
     * @return value or null
     * @throws FileNotExistException if file is not exists
     */
    @Override
    public String getPropValue(String key) throws FileNotExistException {
        try (FileInputStream inputStream = new FileInputStream(file)) {
            properties.load(inputStream);

            return properties.getProperty(key.toLowerCase(), "Null for [" + key.toLowerCase() + "]");
        } catch (FileNotFoundException e) {
            throw new FileNotExistException("The File in \"" + file + "\" is not exists", e);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get properties value.
     *
     * @param keys Keys List
     * @return list or null
     * @throws FileNotExistException if file is not exists
     */
    @Override
    public List<String> getPropValue(List<String> keys) throws FileNotExistException {
        List<String> list = new ArrayList<>();

        try (FileInputStream inputStream = new FileInputStream(file)) {
            properties.load(inputStream);
            keys.stream()
                    .map(String::toLowerCase)
                    .forEach((key) -> list.add(properties.getProperty(key, "Null for [" + key +"]")));
            return list;
        } catch (FileNotFoundException e) {
            throw new FileNotExistException("The File in \"" + file + "\" is not exists", e);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Set property value to key, The key must be exist.
     *
     * @param map Map
     * @return boolean
     */
    @Override
    public boolean setProperty(Map<String, Object> map) throws FileNotExistException {
        try {
            InputStream in = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
            StringBuffer outstr = new StringBuffer();
            String line;
            boolean flag = false;

            while ((line = br.readLine()) != null) {
                if (line.equals("")) {
                    outstr.append("\n");
                } else {
                    if (line.startsWith("#")) {
                        outstr.append(line).append("\n");
                    } else {
                        String _line = line.trim();
                        int charNum = _line.indexOf("=");
                        if (charNum != -1) {
                            Iterator<String> it = map.keySet().iterator();
                            boolean find = false;
                            while (it.hasNext()) {
                                String key = it.next();
                                String _key = _line.substring(0, charNum);
                                if (_key.equals(key)) {
                                    String _value = map.get(key) + "";
                                    outstr.append(_key).append("=").append(_value).append("\n");
                                    map.remove(key);
                                    flag = true;
                                    find = true;
                                    break;
                                }
                            }
                            if (!find) {
                                outstr.append(line).append("\n");
                            }
                        } else {
                            outstr.append(line).append("\n");
                        }
                    }
                }
            }
            System.out.println(outstr);
            br.close();
            in.close();
            if (flag) {
                OutputStreamWriter fos = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8);
                BufferedWriter bw = new BufferedWriter(fos);
                bw.write(outstr.toString());
                bw.close();
                fos.close();
            }
        } catch (FileNotFoundException e) {
            throw new FileNotExistException("The File in \"" + file + "\" is not exists", e);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * show all key.
     */
    @Override
    public void showAllKey() throws FileNotExistException {
        try (FileInputStream in = new FileInputStream(file)) {
            properties.load(in);
            Set<Object> set = properties.keySet();

            Stream.of(set.toArray())
                    .map(Object::toString)
                    .forEach(System.out::println);
        } catch (FileNotFoundException e) {
            throw new FileNotExistException("The File in \"" + file + "\" is not exists", e);
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
    public List<String> getAllKey() throws FileNotExistException {
        List<String> list = new ArrayList<>();

        try (FileInputStream in = new FileInputStream(file)) {
            properties.load(in);
            Set<Object> set = properties.keySet();

            Stream.of(set.toArray())
                    .map(Object::toString)
                    .forEach(list::add);

        } catch (FileNotFoundException e) {
            throw new FileNotExistException("The File in \"" + file + "\" is not exists", e);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * show all value.
     */
    @Override
    public void showAllValue() throws FileNotExistException {
        try (FileInputStream in = new FileInputStream(file)) {
            properties.load(in);
            Set<Object> set = properties.keySet();

            Stream.of(set.toArray())
                    .map(Object::toString)
                    .forEach((o)-> System.out.println(properties.get(o)));
        } catch (FileNotFoundException e) {
            throw new FileNotExistException("The File in \"" + file + "\" is not exists", e);
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
    public List<String> getAllValue() throws FileNotExistException {
        List<String> list = new ArrayList<>();

        try (FileInputStream in = new FileInputStream(file)) {
            properties.load(in);
            Set<Object> set = properties.keySet();

            Stream.of(set.toArray())
                    .map(Object::toString)
                    .forEach((key)-> list.add(properties.getProperty(key)));

        } catch (FileNotFoundException e) {
            throw new FileNotExistException("The File in \"" + file + "\" is not exists", e);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * show all key and value.
     */
    @Override
    public void showAll() throws FileNotExistException {
        try (FileInputStream in = new FileInputStream(file)) {
            properties.load(in);
            Set<Object> set = properties.keySet();

            Stream.of(set.toArray())
                    .map(Object::toString)
                    .forEach((o)-> System.out.println(o + "=" + properties.get(o)));

        } catch (FileNotFoundException e) {
            throw new FileNotExistException("The File in \"" + file + "\" is not exists", e);
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
    public Map<String, Object> getAll() throws FileNotExistException {
        try (FileInputStream in = new FileInputStream(file)){
            properties.load(in);
            Set<Object> set = properties.keySet();
            Map<String, Object> map = new HashMap<>();
            set.forEach((o -> map.put(o.toString(), properties.get(o))));

            return map;
        } catch (FileNotFoundException e) {
            throw new FileNotExistException("The File in \"" + file + "\" is not exists", e);
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
     *
     * @param map Map
     * @return File exist, return false, else create the properties file and return true
     */
    @Override
    public boolean createProp(Map<String, Object> map) {
        if (!file.exists()) {
            try (FileOutputStream out = new FileOutputStream(file)) {
                map.forEach((key, value) -> properties.setProperty(key.toLowerCase(), value.toString()));
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
     *
     * @return if remove success, return true
     */
    @Override
    public boolean removeProp() {
        if (!file.exists())
            return false;
        return file.delete();
    }

    /**
     * Save the data to class.
     *
     * @return boolean
     */
    @Override
    public boolean backupOnClass() {
        boolean isClear = clearClassBackup();

        try {
            if (isClear) {
                Map<String, Object> map = getAll();
                map.keySet().forEach((k)-> savePreferences.put(k, map.get(k).toString()));

                return true;
            }

        } catch (FileNotExistException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Restore the data from class.
     *
     * @return boolean
     */
    @Override
    public boolean restoreFromClass() {
        try {
            String[] keys = savePreferences.keys();
            Map<String, Object> map = new HashMap<>();

            for (String key : keys) {
                map.put(key, savePreferences.get(key, null));
            }

            removeProp();
            createProp(map);

            return true;
        } catch (BackingStoreException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Save the data to map.
     *
     * @return boolean
     */
    @Override
    public boolean backupOnMap() {
        clearMapBackup();
        try {
            boolean isNull = getAll() == null;

            if (!isNull) {
                saveMap = getAll();
                return true;
            }

            return false;
        } catch (FileNotExistException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Restore the data from map.
     *
     * @return boolean
     */
    @Override
    public boolean restoreFromMap() {
        if (saveMap != null) {
            removeProp();
            createProp(saveMap);
            return true;
        }
        return false;
    }

    /**
     * Clear map save.
     *
     * @return boolean
     */
    @Override
    public boolean clearMapBackup() {
        saveMap.clear();
        return true;
    }

    /**
     * Clear class save.
     *
     * @return boolean
     */
    @Override
    public boolean clearClassBackup() {
        try {
            savePreferences.clear();
            return true;
        } catch (BackingStoreException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * toString
     * @return String
     */
    @Override
    public String toString() {
        try {
            return this.getAll().toString();
        } catch (FileNotExistException e) {
            e.printStackTrace();
        }
        return null;
    }
}
