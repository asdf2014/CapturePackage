package com.capture.packages.model;

/**
 * Created by Benedict Jin on 2016/4/22.
 */
public class StoreDao {
    private String fileName;
    private String modifyDate;
    private String path;

    public StoreDao() {
    }

    public StoreDao(String fileName, String modifyDate, String path) {
        this.fileName = fileName;
        this.modifyDate = modifyDate;
        this.path = path;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
