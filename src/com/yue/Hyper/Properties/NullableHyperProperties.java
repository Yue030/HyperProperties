package com.yue.Hyper.Properties;

import com.yue.Hyper.HyperProperties;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Stream;

public class NullableHyperProperties implements HyperProperties {

    /**
     * File
     */
    private File file = null;

    /**
     * Save data.
     */
    private Map<String, Object> save = null;

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
    public NullableHyperProperties(File file, Map<String, Object> map) {
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

            keys.stream()
                    .filter((key)-> properties.getProperty(key.toLowerCase()) != null)
                    .map(String::toLowerCase)
                    .forEach((key) -> builder.append(key.toLowerCase()).append("=").append(properties.getProperty(key.toLowerCase())).append("\n"));

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

            keys.stream()
                    .filter((key)-> properties.getProperty(key.toLowerCase()) != null)
                    .map(String::toLowerCase)
                    .forEach((key) -> list.add(properties.getProperty(key, "Null for [" + key +"]")));
            return list;
        } catch (NullPointerException e) {
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    /**
     * Set property value to key, The key must be exist.
     *
     * @param map Map
     * @return boolean
     */
    @Override
    public boolean setProperty(Map<String, Object> map) {
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
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * show all key.
     */
    @Override
    public void showAllKey() {
        try (FileInputStream in = new FileInputStream(file)) {
            properties.load(in);
            Set<Object> set = properties.keySet();

            Stream.of(set.toArray())
                    .map(Object::toString)
                    .forEach(System.out::println);
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

            Stream.of(set.toArray())
                    .map(Object::toString)
                    .forEach(list::add);
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

            Stream.of(set.toArray())
                    .map(Object::toString)
                    .forEach((o)-> System.out.println(properties.get(o)));
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

            Stream.of(set.toArray())
                    .map(Object::toString)
                    .forEach((key)-> list.add(properties.getProperty(key)));

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

            Stream.of(set.toArray())
                    .map(Object::toString)
                    .forEach((o)-> System.out.println(o + "=" + properties.get(o)));
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
    public Map<String, Object> getAll() {
        Set<Object> set = properties.keySet();
        Map<String, Object> map = new HashMap<>();
        try (FileInputStream in = new FileInputStream(file)){
            properties.load(in);
            set.forEach((o -> map.put(o.toString(), properties.get(o))));

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
    public boolean createProp(Map<String, Object> map) {
        try (FileOutputStream out = new FileOutputStream(file)) {
            if (!file.exists()) {
                map.forEach((key, value) -> properties.setProperty(key.toLowerCase(), value.toString()));
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

    /**
     * Save the data.
     *
     * @return boolean
     */
    @Override
    public boolean save() {
        save = new HashMap<>();
        boolean isNull = getAll() == null ? true : false;

        if (!isNull) {
            save = getAll();
            return true;
        }

        return false;
    }

    /**
     * Restore the data.
     *
     * @return boolean
     */
    @Override
    public boolean restore() {
        if (save != null) {
            removeProp();
            createProp(save);
            return true;
        }
        return false;
    }
}
