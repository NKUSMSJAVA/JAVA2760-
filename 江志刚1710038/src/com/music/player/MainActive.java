package com.music.player;

import com.music.player.model.MusicModel;
import com.music.player.utils.FileUtils;
import javazoom.jl.player.Player;
import org.apache.commons.collections4.CollectionUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class MainActive extends JFrame {
    //左右面板
    JPanel west, east;
    //文件列表面板
    JList jList;
    //文件面板的数据集合
    DefaultListModel<MusicModel> defaultListModel = new DefaultListModel<>();
       Player player;
    private String threadName = "play";

    public MainActive()  {
        //设置点关闭按钮关闭进程
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //禁用最大化
        setResizable(false);
        //设置窗体属性
        setLayout(new BorderLayout());
        setTitle("MP3播放器");
        Toolkit tk = Toolkit.getDefaultToolkit();
        //获取屏幕大小
        Dimension dimension = tk.getScreenSize();
        int width = dimension.width;
        int height = dimension.height;
        //设置主界面宽高
        int frameWidth = width / 2;
        int frameHeight = height / 2;
        //设置界面在屏幕中展示位置
        int x = (width - frameWidth) / 2;
        int y = (height - frameHeight) / 2;
        setBounds(x, y, frameWidth, frameHeight);
        JFrame jFrame = this;

        //设置左面板宽度
        int westWidth = frameWidth - (frameWidth / 3);


        west = new JPanel() {
            //设置背景图
            public void paintComponent(Graphics g) {
                Image image = new ImageIcon("img/play.gif").getImage();
                // 图片随窗体大小而变化
                g.drawImage(image, 0, 0, westWidth, frameHeight, jFrame);
            }
        };
        //左面板宽高
        west.setPreferredSize(new Dimension(westWidth, frameHeight));
        //设置背景色
        west.setBackground(Color.WHITE);
        // 设置面板的布局，使用的是绝对布局
       west.setLayout(null);
        //添加到窗体的左边
        add(west, BorderLayout.WEST);

        JButton btPre = new JButton("上一曲");// 上一首按钮
        btPre.setBounds(105, 450, 93, 23);
        btPre.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                //判断点击一次
                if (e.getClickCount() == 1) {
                    //获取当前选中
                    int index = jList.getSelectedIndex();
                    //当前选中不为第一条
                    if (index != 0) {
                        //获取上一条数据
                        MusicModel musicModel = defaultListModel.get(index - 1);
                        System.out.println(musicModel);
                        play(musicModel);
                        //设置list选中上一条
                        jList.setSelectedIndex(index - 1);
                    }
                }
            }
        });
        west.add(btPre);

        JButton btPlay = new JButton("播放");//播放按钮
        btPlay.setBounds(205, 450, 93, 23);
        btPlay.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                //恢复线程(达到重新播放的目的)
                Thread thread = findThread(threadName);
                if (thread != null) {
                    thread.resume();
                } else {
                    //如果播放线程不存在点击播放按钮播放当前选中的
                    int index = jList.getSelectedIndex();
                    MusicModel musicModel = defaultListModel.get(index);
                    play(musicModel);
                }
            }
        });
        west.add(btPlay);

        JButton btPause = new JButton("暂停");//暂停按钮
        btPause.setBounds(305, 450, 93, 23);
        btPause.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                //暂停线程(达到暂停歌曲的目的)
                Thread thread = findThread(threadName);
                if (thread != null) {
                    thread.suspend();
                }
            }
        });
        west.add(btPause);

        JButton btNext = new JButton("下一首");//下一首按钮
        btNext.setBounds(405, 450, 93, 23);
        btNext.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                //判断点击一次
                if (e.getClickCount() == 1) {
                    //获取当前选中
                    int index = jList.getSelectedIndex();
                    //判断是否小于列表中的最大数值
                    if (index < jList.getLastVisibleIndex()) {
                        //获取下一条数据
                        MusicModel musicModel = defaultListModel.get(index + 1);
                        System.out.println(musicModel);
                        //调用播放
                        play(musicModel);
                        //设置选中下一条
                        jList.setSelectedIndex(index + 1);
                    }

                }
            }
        });
        west.add(btNext);


        //设置右面板
        int eastWidth = frameWidth - westWidth;
        east = new JPanel();
        east.setPreferredSize(new Dimension(eastWidth, frameHeight));
        add(east, BorderLayout.EAST);
        //设置右面板背景色
        east.setBackground(new Color(38, 28, 37));

        //设置滚动面板
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setSize(new Dimension(eastWidth, frameHeight));
        //去除滚动面板默认的边框
        scrollPane.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.WHITE));
        //设置文件列表
        jList = new JList();
        //设置列表背景色
        jList.setBackground(new Color(38, 28, 37));
        jList.setMinimumSize(new Dimension(westWidth, frameHeight));
        //设置列表字体颜色
        jList.setForeground(Color.WHITE);
        //添加文件列表双击事件
        jList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    MusicModel musicModel = defaultListModel.get(jList.getSelectedIndex());
                    System.out.println(musicModel);
                    play(musicModel);
                }
            }
        });
        //将文件列表放到滚动面板中
        scrollPane.setViewportView(jList);
        //将滚动面板放入右面板中
        east.add(scrollPane);

        //设置导入按钮菜单
        JMenuBar jMenuBar = new JMenuBar();
        JMenu menu = new JMenu("导入");

        JMenuItem itemFile = new JMenuItem("导入mp3文件");

        //添加导入按钮事件
        itemFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                List<MusicModel> list = FileUtils.importFiles(jFrame);
                //判断选中文件是否为空
                if (CollectionUtils.isNotEmpty(list)) {
                    //循环选中的文件，没有在数据列表中的才添加
                    for (MusicModel musicModel : list) {
                        if (!defaultListModel.contains(musicModel)) {
                            defaultListModel.addElement(musicModel);
                        }
                    }
                    jList.setModel(defaultListModel);
                    //如果列表是小于等于list则说明是第一次导入歌曲默认选中第一条
                    if (jList.getLastVisibleIndex() <= list.size() - 1) {
                        jList.setSelectedIndex(0);
                    }
                }
            }
        });

        menu.add(itemFile);
        jMenuBar.add(menu);
        setJMenuBar(jMenuBar);


        //设置主界面是否可见
        setVisible(true);
    }

    public static void main(String[] args)  {
        new MainActive();
    }


    public void play(MusicModel musicModel) {
        try {
            Thread thread = findThread(threadName);
            if (thread != null) {
                //先终止正在执行的线程
                thread.interrupt();
            }
            //需要开起线程否则播放时会阻塞主线程，导致不能操作
            new Thread(new Runnable() {

                @Override
                public void run() {
                    try {
                        //先关闭再播放
                        if (player != null) {
                            player.close();
                        }
                        //读取文件流
                        File file = new File(musicModel.getFilePath());
                        FileInputStream stream = new FileInputStream(file);
                        player = new Player(stream);
                        //执行播放
                        player.play();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }, threadName).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //播放开始后开起检测线程，检测到播放线程结束自动播放下一曲
        check();

    }

    /**
     * 判断播放线程是否已经结束
     */
    private void check() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Thread thread = findThread(threadName);
                    //判断播放线程是否结束
                    if (thread == null) {
                        //获取当前选中
                        int index = jList.getSelectedIndex();
                        //判断是否小于列表中的最大数值
                        if (index < jList.getLastVisibleIndex()) {
                            //获取下一条数据
                            MusicModel musicModel = defaultListModel.get(index + 1);
                            System.out.println(musicModel);
                            //调用播放
                            play(musicModel);
                            //设置选中下一条
                            jList.setSelectedIndex(index + 1);
                        }


                    }

                }
            }
        }).start();
    }

    /**
     * 根据线程名获取线程
     *
     * @param threadName
     * @return
     */
    public static Thread findThread(String threadName) {
        ThreadGroup group = Thread.currentThread().getThreadGroup();
        while (group != null) {
            Thread[] threads = new Thread[(int) (group.activeCount() * 1.2)];
            int count = group.enumerate(threads, true);
            for (int i = 0; i < count; i++) {
                if (threadName.equals(threads[i].getName())) {
                    return threads[i];
                }
            }
            group = group.getParent();
        }
        return null;
    }

}
