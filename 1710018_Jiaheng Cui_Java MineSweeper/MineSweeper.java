import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

public class MineSweeper extends JFrame implements ActionListener,MouseListener{
    private int gametime=0;//游戏时间
    private int SIZEL=9;//地图高度
    private int SIZEW=9;//地图宽度
    private int MineNum=10;//地雷数量
    private int leftMineNum=MineNum;//剩余地雷数目
    private int leftbutt=SIZEL*SIZEW;//剩余格子数
    private int leftnotMineNum=leftbutt-leftMineNum;//非剩余非雷数

    private JPanel base=new JPanel();//最底层的panel
    private JPanel timebar=new JPanel();//顶部游戏时间和已扫雷的数目bar
    private JPanel map=new JPanel();//地图面板
    private JPanel difficulty=new JPanel();//难度选择面板

    private JLabel time=new JLabel("        用时:");//游戏时间提示
    private JLabel timetip=new JLabel(gametime+"                  ");
    private JLabel findb=new JLabel(" 剩余数目:");//剩余数目提示
    private JLabel findbtip=new JLabel(leftMineNum+"                  ");

    private ButtonGroup group=new ButtonGroup();//难度选择器按钮群
    private JRadioButton select1,select2,select3;//难度选择

    private int touch=0;//记录点击次数，用于计时器控制
    private boolean threadflag=true;//计时器线程控制
    public boolean over=false;//有没有遍历完中键所选格子的周围

    private bomb_address[] ba;//存储地雷的地址数组
    private MyButton[][] b;//地图上某一个格子

    private ImageIcon fl=new ImageIcon(getClass().getResource("/image/flag.png"));//旗子的图标
    private ImageIcon bo=new ImageIcon(getClass().getResource("/image/bomb.jpg"));//地雷的图标
    private ImageIcon nbo=new ImageIcon(getClass().getResource("/image/notbomb.jpg"));//假地雷的图标
    private ImageIcon ebo=new ImageIcon(getClass().getResource("/image/exploded bomb.png"));//爆炸地雷的图标

    public MineSweeper(){
        this.setTitle("扫雷");
        base.setLayout(new BorderLayout());

        //难度选择
        difficulty.setBorder(new LineBorder(new Color(110, 110, 110)));
        select1=new JRadioButton("简单(9*9 10个雷)",true);//默认选择简单
        select1.setFont(new Font(Font.DIALOG_INPUT,Font.BOLD,15));
        group.add(select1);
        difficulty.add(select1);
        select1.addActionListener(this);
        select2=new JRadioButton("中等(16*16 40个雷)");
        select2.setFont(new Font(Font.DIALOG_INPUT,Font.BOLD,15));
        group.add(select2);
        difficulty.add(select2);
        select2.addActionListener(this);
        select3=new JRadioButton("困难(16*30 99个雷)");//?
        select3.setFont(new Font(Font.DIALOG_INPUT,Font.BOLD,15));
        group.add(select3);
        difficulty.add(select3);
        select3.addActionListener(this);

        //创建时间和扫雷显示器
        CreateTimeBar();
        time.setFont(new Font(Font.DIALOG_INPUT,Font.BOLD,15));
        findb.setFont(new Font(Font.DIALOG_INPUT,Font.BOLD,15));

        //添加到base
        base.add(difficulty,BorderLayout.SOUTH);
        base.add(map,BorderLayout.CENTER);
        base.add(timebar, BorderLayout.NORTH);
        this.add(base);
        this.setSize(600, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        CreateMap(SIZEL,SIZEW);
        map.addMouseListener(this);
    }

    public void run() {//计时器
        try {
            gametime=0;
            while(true) {
                if(threadflag) {
                    while(true) {
                        if(!threadflag) break;//进行任何动作，则开始计时
                        this.timetip.setText(this.gametime+"                  ");
                    }
                }
                Thread.sleep(1000);//每1000ms跳一下计时器
                this.gametime++;
                this.timetip.setText(this.gametime+"                  ");
                System.gc();//进行垃圾收集
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    private void CreateTimeBar() {//创建时间计数与剩余地雷数的面板
        timebar.setLayout(new BorderLayout());
        timebar.setBorder(new LineBorder(new Color(110, 110, 110)));
        timebar.setBackground(Color.WHITE);
        JPanel fir=new JPanel(new BorderLayout());
        fir.setBackground(Color.WHITE);
        fir.add(time,BorderLayout.WEST);
        fir.add(timetip,BorderLayout.EAST);
        JPanel sec=new JPanel(new BorderLayout());
        sec.setBackground(Color.WHITE);
        sec.add(findb,BorderLayout.WEST);
        sec.add(findbtip,BorderLayout.EAST);
        timebar.add(fir,BorderLayout.WEST);
        timebar.add(sec,BorderLayout.EAST);
    }

    private void CreateMap(int m, int n) {//构建地图
        leftbutt=m*n;
        leftMineNum=MineNum;
        leftnotMineNum=leftbutt-leftMineNum;
        map.removeAll();//重置地图
        base.remove(map);
        m=SIZEL;
        n=SIZEW;
        map.setLayout(new GridLayout(m, n));//地图是一个m*n的grid
        findbtip.setText(leftMineNum+"                  ");

        b=new MyButton[m][n];//注意第一个坐标为上下，第二个坐标为左右
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {//创建地图时，所有的按钮都没有雷
                b[i][j]=new MyButton(i,j,false);
                b[i][j].addMouseListener(this);
                b[i][j].setBackground(Color.white);
                b[i][j].setBorder(BorderFactory.createEtchedBorder());
                b[i][j].setMargin(new Insets(0, 0, 0, 0));//设置图片充满整个按钮
                b[i][j].setFocusPainted(false);
                map.add(b[i][j]);
            }
        }
        base.add(map,BorderLayout.CENTER);
        base.setBorder(new LineBorder(new Color(110, 110, 110)));

        //初始化游戏数据
        base.updateUI();
        touch=0;
        threadflag=true;
        gametime=0;
        over=false;
        leftMineNum=MineNum;
        this.setVisible(true);
    }

    private static int[] ReservoirSampling(int[] data,int k){//蓄水池抽样算法
        if(data==null){
            return new int[0];
        }
        if(data.length<k){
            return new int[0];
        }
        int[] sample=new int[k];
        int n=data.length;
        for(int i=0;i<n;i++){
            if(i<k){
                sample[i]=data[i];
            }else{
                int j=new Random().nextInt(i);
                if(j<k){
                    sample[j]=data[i];
                }
            }
        }
        return sample;
    }

    private void OpenTile(MyButton butt) {//翻开格子
        if (!over && !butt.getOpen()){
            butt.setOpen(true);
            GetMineAround(butt);
            if(butt.Minearound>0){//格子旁边有雷，则显示个数
                if(butt.Minearound==1)  {
                    butt.setText("1");
                }
                else if(butt.Minearound==2)  {
                    butt.setText("2");
                }
                else if(butt.Minearound==3)  {
                    butt.setText("3");
                }
                else if(butt.Minearound==4) {
                    butt.setText("4");
                }
                else if(butt.Minearound==5) {
                    butt.setText("5");
                }
                else if(butt.Minearound==6)  {
                    butt.setText("6");
                }
                else if(butt.Minearound==7) {
                    butt.setText("7");
                }
                else if(butt.Minearound==8) {
                    butt.setText("8");
                }
            }
            else{//格子周围有0个雷
                EmptyCell(butt);
            }
            butt.setBackground(Color.getHSBColor(110, 110, 110));
            leftbutt--;
            leftnotMineNum=leftbutt-leftMineNum;
            if(leftnotMineNum == 0 && over) {//翻开所有非雷的格子则胜利
                showAllMine(butt);
                YouWin();
                over=false;
            }
        }

    }

    private int GetMineAround(MyButton butt) {//得到自己周围有多少雷
        int count = 0;
        for(int x=butt.i-1;x<=butt.i+1;x++){
            for(int y=butt.j-1;y<=butt.j+1;y++){
                if(x>=0 && y>=0 && x<SIZEL && y<SIZEW && b[x][y].isBomb(x, y)){
                    count++;
                }
            }
        }
        if(count==0) EmptyCell(butt);//格子周围有0个雷
        butt.setMinearound(count);
        return butt.Minearound;
    }

    private int GetFlagAround(MyButton butt) {//得到自己周围有多少旗子
        int count = 0;
        for(int x=butt.i-1;x<=butt.i+1;x++){
            for(int y=butt.j-1;y<=butt.j+1;y++){
                if(x>=0 && y>=0 && x<SIZEL && y<SIZEW && b[x][y].getFlag()){
                    count++;
                }
            }
        }
        return count;
    }

    private void EmptyCell(MyButton butt){//点击按钮，若周围没雷，则翻开周围非旗子按钮
        int x = butt.i;
        int y = butt.j;
        for(int i=x-1;i<=x+1;i++){
            for(int j=y-1;j<=y+1;j++){//周围8个格子都翻一遍
                if(i>=0 && j>=0 && i<SIZEL && j<SIZEW){//保证找的格子valid
                    if(!b[i][j].isMine && !b[i][j].getOpen() && !b[i][j].getFlag()) {
                        OpenTile(b[i][j]);
                    }
                }
            }
        }
    }

    private void YouWin() {//游戏胜利
        threadflag=true;
        over=true;
        JDialog win=new JDialog(this,"你赢了!",true);
        JPanel Pwin=new JPanel(new BorderLayout());
        JLabel showwin=new JLabel("一共用时"+this.gametime+"秒!\n");
        JButton again=new JButton("再来一局");
        again.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                win.setVisible(false);
                CreateMap(SIZEL,SIZEW);
                findbtip.setText(leftMineNum+"                  ");
            }
        });
        win.add(Pwin);
        Pwin.add(showwin,BorderLayout.CENTER);
        Pwin.add(again,BorderLayout.SOUTH);
        win.setSize(200,100);
        win.setLocationRelativeTo(this);
        win.setVisible(true);   
    }

    private void GameOver() {//游戏失败
        threadflag=true;
        JDialog lose=new JDialog(this,"你输了!",true);
        JPanel Plose=new JPanel(new BorderLayout());
        JLabel showlose=new JLabel("一共用时"+this.gametime+"秒!\n");
        JButton again=new JButton("再来一局");
        again.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                lose.setVisible(false);
                CreateMap(SIZEL,SIZEW);
                findbtip.setText(leftMineNum+"                  ");
            }
        });
        lose.add(Plose);
        Plose.add(showlose,BorderLayout.CENTER);
        Plose.add(again,BorderLayout.SOUTH);
        lose.setSize(200,100);
        lose.setLocationRelativeTo(this);
        lose.setVisible(true);
    }

    private void showAllMine(MyButton butt) {//出现所有地雷
        Image temp1 = bo.getImage().getScaledInstance(butt.getWidth(), butt.getHeight(), bo.getImage().SCALE_DEFAULT);
        bo = new ImageIcon(temp1);
        Image temp2 = ebo.getImage().getScaledInstance(butt.getWidth(), butt.getHeight(), ebo.getImage().SCALE_DEFAULT);
        ebo = new ImageIcon(temp2);

        for(int k=0;k<MineNum;k++) {
            if(b[ba[k].i][ba[k].j].getFakeFlag())  b[ba[k].i][ba[k].j].setIcon(ebo);
            else b[ba[k].i][ba[k].j].setIcon(bo);
            b[ba[k].i][ba[k].j].setOpen(true);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {//难度选择
        if(e.getSource()==select1) {
            SIZEL=9;
            SIZEW=9;
            MineNum=10;
            CreateMap(SIZEL,SIZEW);
            findbtip.setText(leftMineNum+"                  ");
        }
        if(e.getSource()==select2) {
            SIZEL=16;
            SIZEW=16;
            MineNum=40;
            CreateMap(SIZEL,SIZEW);
            findbtip.setText(leftMineNum+"                  ");
        }
        if(e.getSource()==select3) {
            SIZEL=16;
            SIZEW=30;
            MineNum=99;
            CreateMap(SIZEL,SIZEW);
            findbtip.setText(leftMineNum+"                  ");
        }
    }

    private boolean GetisMine(MyButton butt) {//检查自己是否为雷
        return butt.isMine;
    }

    @Override
    public void mouseClicked(MouseEvent arg0) {
        if((arg0.getButton())==MouseEvent.BUTTON1) {//鼠标左键点击
            if(!((MyButton)arg0.getSource()).getFlag() && !((MyButton)arg0.getSource()).getOpen()){//左键点击非旗子的空格，则翻开
                touch++;
                if(touch==1){
                    threadflag=false;//第一次左键点击则开始计时

                    int sign=0;//第一次左击后再布雷
                    ba=new bomb_address[MineNum];
                    int[] mineL=new int[MineNum];
                    int[] mineW=new int[MineNum];//b[i][j]为雷的地址，其中i属于mineL，j属于mineW
                    int[] data=new int[SIZEL*SIZEW-1];//data为所有格子的编号，取值范围为{0,...,SIZEL*SIZEW-1}\{xi*SIZEW+yi}
                    for(int i=0;i<SIZEL*SIZEW;i++){
                        if(i<((MyButton)arg0.getSource()).i*SIZEW+((MyButton)arg0.getSource()).j)    data[i]=i;
                        else if(i>((MyButton)arg0.getSource()).i*SIZEW+((MyButton)arg0.getSource()).j) data[i-1]=i;
                    }
                    int[] sample=ReservoirSampling(data,MineNum);//用蓄水池算法从data中抽样出m个样本作为雷的地址
                    for(int i=0;i<MineNum;i++) {
                        mineL[i]=sample[i]/SIZEW;
                        mineW[i]=sample[i]%SIZEW;
                    }

                    for(int k=0;k<MineNum;k++)  ba[k]=new bomb_address(mineL[k],mineW[k]);
                    for(int i=0;i<SIZEL;i++) {//创建有雷的按钮
                        for(int j=0;j<SIZEW;j++) {
                            for(int g=0;g<ba.length;g++) {
                                if(ba[g].i==i && ba[g].j==j) b[i][j].setBomb(true);//这个格子有雷
                            }
                        }
                    }
                }
                ((MyButton)arg0.getSource()).setOpen(true);
                leftbutt--;
                leftnotMineNum=leftbutt-leftMineNum;
                if(GetisMine(((MyButton)arg0.getSource()))) {//翻开的格子是雷，则失败
                    this.showAllMine(((MyButton)arg0.getSource()));
                    GameOver();
                }
                else{//翻开的格子不是雷
                    if(GetMineAround(((MyButton)arg0.getSource()))==0) {//此格子周围没有雷，则不显示文本
                        ((MyButton)arg0.getSource()).setBackground(Color.getHSBColor(110, 110, 110));
                    }else {//此格子周围有雷，则显示周围雷数
                        ((MyButton)arg0.getSource()).setText(Integer.toString(GetMineAround(((MyButton)arg0.getSource()))));
                        ((MyButton)arg0.getSource()).setBackground(Color.getHSBColor(110, 110, 110));
                    }
                }
                if(leftnotMineNum == 0) {//翻开所有非雷的格子则胜利
                    showAllMine((MyButton)arg0.getSource());
                    YouWin();
                }
            }
        }

        if((arg0.getButton())==MouseEvent.BUTTON2) {//鼠标中键按下且松开的按钮是同一个按钮，判定在released之后
            int flagnum=GetFlagAround(((MyButton)arg0.getSource()));
            over=false;
            boolean shouldover=false;//游戏应该结束？
            if(((MyButton)arg0.getSource()).getOpen() && flagnum>0 && flagnum==GetMineAround(((MyButton)arg0.getSource()))){//中键点击已翻开，且旁边旗子数等于周围雷数(>0)的格子，则翻开周围所有格子
                int x = ((MyButton)arg0.getSource()).i;
                int y = ((MyButton)arg0.getSource()).j;
                for(int i=x-1;i<=x+1;i++){
                    for(int j=y-1;j<=y+1;j++){//周围8个格子都翻一遍
                        if(i>=0 && j>=0 && i<SIZEL && j<SIZEW && !b[i][j].getOpen() && !over){//保证找的格子valid
                            if(b[i][j].getFlag() && !b[i][j].isMine){//如果插旗子的地方没有雷，则为假地雷，游戏结束
                                Image temp = nbo.getImage().getScaledInstance(((MyButton)arg0.getSource()).getWidth(), ((MyButton)arg0.getSource()).getHeight(), nbo.getImage().SCALE_DEFAULT);
                                nbo = new ImageIcon(temp);
                                b[i][j].setIcon(nbo);
                                shouldover=true;
                            }
                            if(b[i][j].isMine && !b[i][j].getFlag()){//有假地雷的同时，一定有雷没有被发现，则为爆炸地雷，游戏结束
                                b[i][j].setFakeFlag(true);
                                shouldover=true;
                            }
                            else if(!b[i][j].getFlag() && !over){//若该格子是安全的，且不是旗子，则翻开
                                OpenTile(b[i][j]);
                            }
                        }
                    }
                }
                over=true;
                if(leftnotMineNum == 0 && over) {//翻开所有非雷的格子则胜利
                    showAllMine((MyButton)arg0.getSource());
                    YouWin();
                    over=false;
                }
                if(shouldover)//如果有雷标错，游戏结束
                {
                    this.showAllMine(((MyButton)arg0.getSource()));
                    GameOver();
                }
            }
        }

        if(arg0.getButton()==arg0.BUTTON3) {//鼠标右键点击
            if(!((MyButton)arg0.getSource()).getOpen()) {//必须右键点击未翻开的格子
                if(((MyButton)arg0.getSource()).getFlag()) {//右键旗子，则取消旗子
                    ((MyButton)arg0.getSource()).setFlag(false);
                    ((MyButton)arg0.getSource()).setIcon(null);
                    leftMineNum++;
                    leftbutt++;
                    leftnotMineNum=leftbutt-leftMineNum;
                    findbtip.setText(leftMineNum+"                  ");
                }else {//右键空地，则插旗子
                    Image temp = fl.getImage().getScaledInstance(((MyButton)arg0.getSource()).getWidth(), ((MyButton)arg0.getSource()).getHeight(), fl.getImage().SCALE_DEFAULT);
                    fl = new ImageIcon(temp);
                    ((MyButton)arg0.getSource()).setIcon(fl);
                    ((MyButton)arg0.getSource()).setFlag(true);
                    leftMineNum--;
                    leftbutt--;
                    leftnotMineNum=leftbutt-leftMineNum;
                    findbtip.setText(leftMineNum+"                  ");
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent arg0) {
        if((arg0.getButton())==MouseEvent.BUTTON2) {//中键按下格子，格子周围未翻开的格子会变蓝
            int x = ((MyButton)arg0.getSource()).i;
            int y = ((MyButton)arg0.getSource()).j;
            for(int i=x-1;i<=x+1;i++){
                for(int j=y-1;j<=y+1;j++){//周围8个格子都翻一遍
                    if(i>=0 && j>=0 && i<SIZEL && j<SIZEW && (i!=x || j!=y) && !b[i][j].getOpen() && !b[i][j].getFlag()){//保证找的格子valid，且并没有打开，并没有插旗
                        b[i][j].setBackground(new Color(187, 255, 255));
                    }
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        if((arg0.getButton())==MouseEvent.BUTTON2) {//鼠标中键松开，格子周围未翻开的格子重新变白，因为clicked在released之后，直接变白即可
            int x = ((MyButton)arg0.getSource()).i;
            int y = ((MyButton)arg0.getSource()).j;
            for(int i=x-1;i<=x+1;i++){
                for(int j=y-1;j<=y+1;j++){//周围8个格子都翻一遍
                    if(i>=0 && j>=0 && i<SIZEL && j<SIZEW && !b[i][j].getOpen() && !b[i][j].getFlag()){//保证找的格子valid，且并没有打开，并没有插旗
                        b[i][j].setBackground(Color.white);
                    }
                }
            }
        }
    }

    //抽象方法必须被覆盖
    @Override
    public void mouseEntered(MouseEvent arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        // TODO Auto-generated method stub
    }

}
