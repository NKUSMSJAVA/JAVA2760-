package com.music.player.utils;


import com.music.player.model.MusicModel;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * 文件工具类
 */
public class FileUtils {
    /**
     * 导入文件
     */
    public static List<MusicModel> importFiles(Frame frame) {
        //初始化返回map
        List<MusicModel> list = new ArrayList<>();
        //文件选择弹窗
        JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));
        //设置可以选择多个文件
        fileChooser.setMultiSelectionEnabled(true);
        //设置只能选择文件不能选择文件夹
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        //mp3文件后缀过滤
        FileNameExtensionFilter filter = new FileNameExtensionFilter("只能选择音频后缀文件", "mp3","wav");
        fileChooser.setFileFilter(filter);
        //打开文件选择对话框
        int returnVal = fileChooser.showOpenDialog(frame);
        //判断文件浏览对话框打开成功
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            //获取所有选中的文件
            File[] files = fileChooser.getSelectedFiles();
            for (File file : files) {
                //文件名作为map的key,文件路径作为map的value
                MusicModel musicModel = new MusicModel();
                musicModel.setFileName(file.getName());
                musicModel.setFilePath(file.getPath());
                list.add(musicModel);
            }
        } else {
            JOptionPane.showMessageDialog(frame, "文件浏览对话框打开失败", "提示", JOptionPane.WARNING_MESSAGE);
        }
        return list;
    }
}
