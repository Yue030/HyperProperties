package com.yue.Hyper.Properties;

import com.yue.Hyper.Exception.FileNotFoundException;
import com.yue.Hyper.HyperProperties;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class BasicHyperProperties implements HyperProperties {
    /**
     * File
     */
    private File file = null;

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
    public String readProp(String key) throws FileNotFoundException {
        if(!file.exists())
            throw new FileNotFoundException("The File in \"" + file + "\" is not exists");
        try (FileInputStream inputStream = new FileInputStream(file)){
            properties.load(inputStream);

            return key.toLowerCase() + "=" +
                    properties.getProperty(key.toLowerCase(), "Null for [" + key.toLowerCase() +"]")
                    + "\n";
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
    public String readProp(List<String> keys) throws FileNotFoundException {
        if(!file.exists())
            throw new FileNotFoundException("The File in \"" + file + "\" is not exists");
        try (FileInputStream inputStream = new FileInputStream(file)){
            properties.load(inputStream);

            StringBuilder builder = new StringBuilder();

            keys.forEach((key) -> builder.append(key.toLowerCase()).append("=").append(
                    properties.getProperty(key.toLowerCase(), "Null for [" + key.toLowerCase() +"]")).append("\n"));

            return builder.toString();
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
     * @throws FileNotFoundException if file is not exists
     */
    @Override
    public String getPropValue(String key) throws FileNotFoundException {
        if(!file.exists())
            throw new FileNotFoundException("The File in \"" + file + "\" is not exists");
        try (FileInputStream inputStream = new FileInputStream(file)){
            properties.load(inputStream);

            return properties.getProperty(key.toLowerCase(), "Null for [" + key.toLowerCase() +"]");
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
     * @throws FileNotFoundException if file is not exists
     */
    @Override
    public List<String> getPropValue(List<String> keys) throws FileNotFoundException {
        List<String> list = new ArrayList<>();

        if(!file.exists())
            throw new FileNotFoundException("The File in \"" + file + "\" is not exists");
        try (FileInputStream inputStream = new FileInputStream(file)){
            properties.load(inputStream);
            keys.forEach((key) -> list.add(properties.getProperty(key.toLowerCase(), "Null for [" + key.toLowerCase() +"]")));
            return list;
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
    public boolean setProperty(Map<String, Object> map) {
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
     *
     * @return List
     */
    @Override
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
    @Override
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
     *
     * @return List
     */
    @Override
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
    @Override
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
     *
     * @return String
     */
    @Override
    public Map<String, Object> getAll() throws FileNotFoundException {
        if(!file.exists())
            throw new FileNotFoundException("The File in \"" + file + "\" is not exists");
        try (FileInputStream in = new FileInputStream(file)){
            properties.load(in);
            Set<Object> set = properties.keySet();
            Map<String, Object> map = new HashMap<>();
            set.forEach((o -> map.put(o.toString(), properties.get(o))));

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
