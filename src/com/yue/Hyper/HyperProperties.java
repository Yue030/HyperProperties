package com.yue.Hyper;

import com.yue.Hyper.Exception.FileNotExistException;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.prefs.Preferences;

public interface HyperProperties {

    String defaultBackupName = "backup.properties";

    Preferences defaultBackup = Preferences.userNodeForPackage(HyperProperties.class);

    /**
     * Choose a File with Dialog.
     * @return File
     */
    static File chooseFile() {
        FileDialog dialog = new FileDialog((Frame)null, "Select File to Load", FileDialog.LOAD);

        dialog.setFilenameFilter((file, name) -> name.endsWith(".properties"));

        dialog.setVisible(true);
        File file = new File(dialog.getDirectory() + dialog.getFile());
        System.out.println("You choose to open this file: " + file);

        return file;
    }

    /**
     * Delete the backup from name
     * @param backupName backup file name
     * @return boolean
     */
    static boolean deleteBackup(String backupName) {
        if (backupName != null) {
            backupName = backupName.concat(".properties");
            File file = new File(backupName);

            return file.exists() && file.delete();
        }
        return false;
    }

    /**
     * Backup properties.
     * @param file File
     * @param backupName Backup Name
     * @return boolean
     */
    static boolean backupProperties(File file, String backupName) {
        Properties properties = new Properties();

        deleteBackup(backupName);
        if (backupName != null) {
            backupName = backupName.concat(".properties");
            try (FileOutputStream out = new FileOutputStream(backupName)) {
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
     * Backup properties.
     * @param file File
     * @param backupName Backup name
     * @return boolean
     */
    static boolean restoreProperties(File file, String backupName) {
        Properties properties = new Properties();

        if (file.exists()) {
            file.delete();
        } else {
            return false;
        }

        if (backupName != null) {
            backupName = backupName.concat(".properties");
            try (FileInputStream in = new FileInputStream(backupName)) {

                properties.load(in);
                Set<Object> set = properties.keySet();
                Map<String, Object> map = new HashMap<>();
                set.forEach((o -> map.put(o.toString(), properties.get(o))));

                if (!file.exists()) {
                    try (FileOutputStream out = new FileOutputStream(file)) {
                        map.forEach((key, value) -> properties.setProperty(key.toLowerCase(), value.toString()));
                        properties.store(out, null);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return false;
                    }
                } else {
                    return false;
                }

                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * Set Default Backup.
     * @param isDefaultBackup true or false
     */
    static void setDefaultBackup(boolean isDefaultBackup) {
        defaultBackup.put("backup", String.valueOf(isDefaultBackup));
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
     * Get backup name.
     * @return String
     */
    String getBackupName();

    /**
     * Set Backup name
     * @param name Name
     */
    void setBackupName(String name);

    /**
     * init the Backup Name.
     */
    void initBackupName();

    /**
     * read Properties File URL
     * @param key String
     * @return String
     */
    String readProp(String key) throws FileNotExistException;
    /**
     * read Properties File URL
     * @param keys List
     * @return String
     */
    String readProp(List<String> keys) throws FileNotExistException;

    /**
     * Get properties value.
     * @param key Key
     * @return value or null
     * @throws FileNotExistException if file is not exists
     */
    String getPropValue(String key) throws FileNotExistException;
    /**
     * Get properties value.
     * @param keys Keys List
     * @return list or null
     * @throws FileNotExistException if file is not exists
     */
    List<String> getPropValue(List<String> keys) throws FileNotExistException;

    /**
     * Set property value to key, The key must be exist.
     * @param map Map
     * @return boolean
     */
    boolean setProperty(Map<String,Object> map) throws FileNotExistException;

    /**
     * show all key.
     */
    void showAllKey() throws FileNotExistException;
    /**
     * get all key.
     * @return List
     */
    List<String> getAllKey() throws FileNotExistException;
    /**
     * show all value.
     */
    void showAllValue() throws FileNotExistException;
    /**
     * get all value.
     * @return List
     */
    List<String> getAllValue() throws FileNotExistException;
    /**
     * show all key and value.
     */
    void showAll() throws FileNotExistException;
    /**
     * get all key and value.
     * @return map
     */
    Map<String, Object> getAll() throws FileNotExistException;

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

    /**
     * Backup properties.
     * @return boolean
     */
    boolean backup();

    /**
     * Restore properties.
     * @return boolean
     */
    boolean restore();

    /**
     * Clear Backup.
     * @return boolean
     */
    boolean clearBackup();
}

