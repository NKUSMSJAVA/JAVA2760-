package com.music.player.model;



public class MusicModel  {
    /**
     * 文件名
     */
    private String fileName;
    /**
     * 文件路径
     */
    private String filePath;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * 重写toString方法的目的是为了JList是用文件名来展示
     * @return
     */
    @Override
    public String toString() {
        return fileName;
    }
}
