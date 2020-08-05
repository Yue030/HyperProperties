package com.yue.Hyper;

import com.yue.Hyper.Exception.FileNotExistException;
import com.yue.Hyper.Properties.BasicHyperProperties;

import java.io.File;

public class Main {
    public static void main(String[] args) throws FileNotExistException {
        File file = HyperProperties.chooseFile();
        HyperProperties hp = new BasicHyperProperties(file);

        hp.setBackupName("test");
        hp.backup();

        System.exit(0);
    }
}
