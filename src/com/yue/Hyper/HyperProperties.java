package com.yue.Hyper;

import com.yue.Hyper.Exception.FileNotFoundException;

import java.awt.*;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public interface HyperProperties {

    /**
     * Choose a File with Dialog.
     * @return
     */
    static File chooseFile() {
        FileDialog dialog = new FileDialog((Frame)null, "Select File to Load", FileDialog.LOAD);

        dialog.setFilenameFilter((file, name) -> name.endsWith(".properties"));

        dialog.setVisible(true);
        File file = new File(dialog.getDirectory() + dialog.getFile());
        System.out.println("You chose to open this file: " + file);

        return file;
    }

    /**
     * Get properties.
     * @return Properties
     */
    Properties getProperties();

    /**
     * Get the file.
     * @return File URL
     */
    File getFile();

    /**
     * Set the file.
     * @param file File URL
     */
    void setFile(File file);

    /**
     * read Properties File URL
     * @param key String
     * @return String
     */
    String readProp(String key) throws FileNotFoundException;
    /**
     * read Properties File URL
     * @param keys List
     * @return String
     */
    String readProp(List<String> keys) throws FileNotFoundException;

    /**
     * Get properties value.
     * @param key Key
     * @return value or null
     * @throws FileNotFoundException if file is not exists
     */
    String getPropValue(String key) throws FileNotFoundException;
    /**
     * Get properties value.
     * @param keys Keys List
     * @return list or null
     * @throws FileNotFoundException if file is not exists
     */
    List<String> getPropValue(List<String> keys) throws FileNotFoundException;

    /**
     * Set property value to key, The key must be exist.
     * @param map Map
     * @return boolean
     */
    boolean setProperty(Map<String,Object> map);

    /**
     * show all key.
     */
    void showAllKey() throws FileNotFoundException;
    /**
     * get all key.
     * @return List
     */
    List<String> getAllKey() throws FileNotFoundException;
    /**
     * show all value.
     */
    void showAllValue() throws FileNotFoundException;
    /**
     * get all value.
     * @return List
     */
    List<String> getAllValue() throws FileNotFoundException;
    /**
     * show all key and value.
     */
    void showAll() throws FileNotFoundException;
    /**
     * get all key and value.
     * @return map
     */
    Map<String, Object> getAll() throws FileNotFoundException;

    /**
     * Create Properties.
     * @param key Key
     * @param value Value
     * @return File exist, return false, else create the properties file and return true
     */
    boolean createProp(String key, String value);
    /**
     * Create Properties.
     * @param map Map
     * @return File exist, return false, else create the properties file and return true
     */
    boolean createProp(Map<String, Object> map);

    /**
     * Remove the properties.
     * @return if remove success, return true
     */
    boolean removeProp();
}
