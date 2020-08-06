package com.yue.Hyper.Properties;

import com.yue.Hyper.Exception.FileNotExistException;
import com.yue.Hyper.HyperProperties;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Stream;

public class SyncHyperProperties implements HyperProperties {
    /**
     * File
     */
    private File file = null;

    /**
     * Backup file name.
     */
    private String backupName = null;

    /**
     * Constructor.
     * @param file FileURL
     */
    public SyncHyperProperties(File file) {
        setFile(file);
    }

    /**
     * Constructor and call {@link #createProp(String, String)}.
     * @param file File URL
     * @param key Key
     * @param value Value
     */
    public SyncHyperProperties(File file, String key, String value) {
        setFile(file);
        createProp(key, value);
    }

    /**
     * Constructor and call {@link #createProp(Map)}.
     * @param file File URL
     * @param map Map
     */
    public SyncHyperProperties(File file, Map<String, Object> map) {
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
    public synchronized Properties getProperties() {
        return this.properties;
    }

    /**
     * Get the file.
     *
     * @return File URL
     */
    @Override
    public synchronized File getFile() {
        return this.file;
    }

    /**
     * Set the file.
     *
     * @param file File URL
     */
    @Override
    public synchronized void setFile(File file) {
        this.file = file;
    }

    /**
     * Get backup name.
     *
     * @return String
     */
    @Override
    public String getBackupName() {
        return backupName;
    }

    /**
     * Set Backup name
     *
     * @param name Name
     */
    @Override
    public void setBackupName(String name) {
        this.backupName = name.concat(".properties");
    }

    /**
     * read Properties File URL
     *
     * @param key String
     * @return String
     */
    @Override
    public synchronized String readProp(String key) throws FileNotExistException {
        try (FileInputStream inputStream = new FileInputStream(file)){
            properties.load(inputStream);

            return key.toLowerCase() + "=" +
                    properties.getProperty(key.toLowerCase(), "Null for [" + key.toLowerCase() +"]")
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
    public synchronized String readProp(List<String> keys) throws FileNotExistException {
        try (FileInputStream inputStream = new FileInputStream(file)){
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
    public synchronized String getPropValue(String key) throws FileNotExistException {
        try (FileInputStream inputStream = new FileInputStream(file)){
            properties.load(inputStream);

            return properties.getProperty(key.toLowerCase(), "Null for [" + key.toLowerCase() +"]");
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
    public synchronized List<String> getPropValue(List<String> keys) throws FileNotExistException {
        List<String> list = new ArrayList<>();

        try (FileInputStream inputStream = new FileInputStream(file)){
            properties.load(inputStream);
            keys.stream()
                    .map(String::toLowerCase).forEach(
                            (key) -> list.add(properties.getProperty(key, "Null for [" + key +"]")));
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
    public synchronized boolean setProperty(Map<String, Object> map) throws FileNotExistException{
        try {
            InputStream in = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
            StringBuffer outstr = new StringBuffer();
            String line;
            boolean flag = false;

            while ((line = br.readLine()) != null) {
                if (line.equals(""))
                {
                    outstr.append("\n");
                } else {
                    if (line.startsWith("#"))
                    {
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
    public synchronized void showAllKey() throws FileNotExistException {
        try (FileInputStream in = new FileInputStream(file)){
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
    public synchronized List<String> getAllKey() throws FileNotExistException {
        List<String> list = new ArrayList<>();

        try (FileInputStream in = new FileInputStream(file)){
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
    public synchronized void showAllValue() throws FileNotExistException {
        try (FileInputStream in = new FileInputStream(file)){
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
    public synchronized List<String> getAllValue() throws FileNotExistException {
        List<String> list = new ArrayList<>();

        try (FileInputStream in = new FileInputStream(file)){
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
    public synchronized void showAll() throws FileNotExistException {
        try (FileInputStream in = new FileInputStream(file)){
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
    public synchronized Map<String, Object> getAll() throws FileNotExistException {
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
     * @param key Key
     * @param value Value
     * @return File exist, return false, else create the properties file and return true
     */
    @Override
    public synchronized boolean createProp(String key, String value) {
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
    public synchronized boolean createProp(Map<String, Object> map) {
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
    public synchronized boolean removeProp() {
        if (!file.exists())
            return false;
        return file.delete();
    }

    /**
     * Backup properties.
     *
     * @return boolean
     */
    @Override
    public synchronized boolean backup() {
        clearBackup();
        if (backupName != null) {
            try (FileOutputStream out = new FileOutputStream(SyncHyperProperties.class.getClassLoader() + backupName)) {
                try (FileInputStream in = new FileInputStream(file)) {
                    properties.load(in);

                    properties.store(out, null);
                }
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * Restore properties.
     *
     * @return boolean
     */
    @Override
    public synchronized boolean restore() {
        if (backupName != null) {
            try (FileInputStream in = new FileInputStream(SyncHyperProperties.class.getClassLoader() + backupName)) {

                properties.load(in);
                Set<Object> set = properties.keySet();
                Map<String, Object> map = new HashMap<>();
                set.forEach((o -> map.put(o.toString(), properties.get(o))));

                removeProp();
                createProp(map);

                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * Clear Backup.
     *
     * @return boolean
     */
    @Override
    public synchronized boolean clearBackup() {
        if (backupName != null) {
            File file = new File(SyncHyperProperties.class.getClassLoader() + backupName);

            return file.exists() ? file.delete() : false;
        }
        return false;
    }

    /**
     * toString
     * @return String
     */
    @Override
    public synchronized String toString() {
        try {
            return this.getAll().toString();
        } catch (FileNotExistException e) {
            e.printStackTrace();
        }
        return null;
    }
}
