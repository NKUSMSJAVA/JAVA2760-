package calculator;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class calculator extends Frame implements ActionListener, WindowListener
{
    private Container container;
    private GridBagLayout layout;
    private GridBagConstraints constraints; 
    private JTextField displayField;         //计算结果显示区
    private String lastCommand;           //保存+,-,*,/,=命令0
    private double result;               //保存计算结果
    private boolean start;           //判断是否为数字的开始
    private JMenuBar menubar;
    private JMenuItem m_exit;
    private JMenuItem m2_ejz;
    private JMenuItem m2_bjz;
    private Dialog dialog;
    private Label label_dialog;
    private JButton button_sqrt;
    private JButton button_plusminus;
    private JButton button_CE;
    private JButton button_cancel;
    private JButton button_1;
    private JButton button_2;
    private JButton button_3;
    private JButton button_4;
    private JButton button_5;
    private JButton button_6;
    private JButton button_7;
    private JButton button_8;
    private JButton button_9;
    private JButton button_0;
    private JButton button_plus;
    private JButton button_minus;
    private JButton button_multiply;
    private JButton button_divide;
    private JButton button_point;
    private JButton button_equal;
    private JButton button_log;
    private JButton button_tan;
    private JButton button_cos;
    private JButton button_sin;
    private JButton button_exp;

    public calculator()       //构造方法设置布局、为按钮注册事件监听器
    {
        super( "My Calculator" );
        this.setLocation( 350,150 );
        this.setSize( 450,400 );
        this.setResizable( true );
        this.setLayout( new GridLayout( 7,1 ) );
        this.addmyMenu();                   //调用成员方法添加菜单
        displayField = new JTextField( 30 );
        this.add( displayField );
        displayField.setEditable( true );

        start = true;
        result = 0;
        lastCommand = "=";

        JPanel panel0 = new JPanel();
        panel0.setLayout( new GridLayout( 1,4,4,4 ) );


        JPanel panel1 = new JPanel();
        panel1.setLayout( new GridLayout( 1,5,4,4 ) );
        this.add( panel1 );
        button_sqrt = new JButton( "sqrt" );//根号
        button_plusminus = new JButton( "+/-" );
        button_exp = new JButton( "exp" );//底数e的n次幂
        button_CE = new JButton( "退位");
        button_cancel = new JButton( "c" );//清除

        JPanel panel2 = new  JPanel();
        panel2.setLayout( new GridLayout( 1,5,4,4 ) );
        this.add( panel2 );
        button_7 = new JButton( "7" );
        button_8 = new JButton( "8" );
        button_9 = new JButton( "9" );
        button_log = new JButton( "log" );//对数
        button_divide = new JButton( "/" );//除

        JPanel panel3 = new JPanel();
        panel3.setLayout( new GridLayout(1,5,4,4) );
        this.add( panel3 );
        button_4 = new JButton( "4" );
        button_5 = new JButton( "5" );
        button_6 = new JButton( "6" );
        button_tan = new JButton( "tan" );//正切
        button_multiply = new JButton( "*" );//乘法

        JPanel panel4=new  JPanel();
        panel4.setLayout( new GridLayout( 1,5,4,4 ) );
        this.add(panel4);
        button_1 = new JButton( "1" );
        button_2 = new JButton( "2" );
        button_3 = new JButton( "3" );
        button_cos = new JButton( "cos");//余弦
        button_minus = new JButton( "-" );

        JPanel panel5 = new  JPanel();
        panel5.setLayout( new GridLayout( 1,5,4,4 ) );
        this.add( panel5 ); 
        button_0 = new JButton( "0" );
        button_point=new JButton( "." );
        button_equal = new JButton( "=" );
        button_sin = new JButton( "sin" );//正弦
        button_plus = new JButton( "+" );

        panel1.add( button_sqrt );
        panel1.add( button_plusminus );
        panel1.add( button_exp );
        panel1.add( button_CE );
        panel1.add( button_cancel );
        panel2.add( button_7 );
        panel2.add( button_8 );
        panel2.add( button_9 );
        panel2.add( button_log );
        panel2.add( button_divide );
        panel3.add( button_4 );
        panel3.add( button_5 );
        panel3.add( button_6 );
        panel3.add( button_tan );
        panel3.add( button_multiply );
        panel4.add( button_1 );
        panel4.add( button_2 ); 
        panel4.add( button_3 );
        panel4.add( button_cos );
        panel4.add( button_minus );
        panel5.add( button_0 );
        panel5.add( button_point );
        panel5.add( button_equal );
        panel5.add( button_sin );
        panel5.add( button_plus) ;

        button_sqrt.addActionListener( this );
        button_plusminus.addActionListener( this );
        button_exp.addActionListener( this );
        button_CE.addActionListener( this );
        button_cancel.addActionListener( this );
        button_7.addActionListener( this );
        button_8.addActionListener( this );
        button_9.addActionListener( this );
        button_log.addActionListener( this );
        button_divide.addActionListener( this );
        button_4.addActionListener( this );
        button_5.addActionListener( this );
        button_6.addActionListener( this );
        button_tan.addActionListener( this );
        button_multiply.addActionListener( this );
        button_1.addActionListener( this );
        button_2.addActionListener( this );
        button_3.addActionListener( this );
        button_cos.addActionListener( this );
        button_minus.addActionListener( this );
        button_0.addActionListener( this );
        button_point.addActionListener( this );
        button_equal.addActionListener( this );
        button_sin.addActionListener( this );
        button_plus.addActionListener( this );

        this.addWindowListener( new WinClose() );      //注册窗口监听器
        this.setVisible( true );
    }

    private void addmyMenu()        //菜单的添加
    {   
        JMenuBar menubar = new JMenuBar(); 
        this.add( menubar );
        JMenu m1 = new JMenu( "选项" );
        JMenu m2 = new JMenu( "进制转换" );

        JMenuItem m1_exit = new JMenuItem( "退出" );
        m1_exit.addActionListener( this );
        JMenuItem m2_ejz = new JMenuItem( "二进制" );
        m2_ejz.addActionListener( this );
        JMenuItem m2_bjz = new JMenuItem("八进制");
        m2_bjz.addActionListener( this );
        JMenuItem m2_sljz = new JMenuItem("十六进制");
        m2_sljz.addActionListener( this );

        JMenu m3 = new JMenu( "帮助" ); 
        JMenuItem m3_Help = new JMenuItem( "用法" ); 
        m3_Help.addActionListener( this ); 

        dialog = new Dialog( this, "提示" , true );     //模式窗口
        dialog.setSize( 240,80 );
        label_dialog = new Label("", Label.CENTER );   //标签的字符串为空，居中对齐
        dialog.add( label_dialog ); 
        dialog.addWindowListener( this );          //为对话框注册窗口事件监听器

        m1.add( m1_exit );  
        menubar.add( m1 );
        m2.add( m2_ejz );
        m2.add( m2_bjz );
        m2.add( m2_sljz );
        menubar.add( m2 );
        m3.add( m3_Help ); 
        menubar.add( m3 );  
    }

    public void actionPerformed(ActionEvent e)       //按钮的单击事件处理方法
    {
        if(
                e.getSource().equals( button_1 )||e.getSource().equals( button_2 )|| 
                e.getSource().equals( button_3 )||e.getSource().equals( button_4 )||
                e.getSource().equals( button_5 )|| e.getSource().equals( button_6 )||
                e.getSource().equals( button_7 )|| e.getSource().equals( button_8 )||
                e.getSource().equals( button_9 ) ||e.getSource().equals( button_0 )||
                e.getSource().equals( button_point )||e.getSource().equals( button_plusminus )||    
                e.getSource().equals( button_cancel )||e.getSource().equals( button_CE )
          )
        {      //非运算符的处理方法
            String input = e.getActionCommand();

            if ( start )
            {
                displayField.setText("");
                start = false;
                if( input.equals( "+/-" ) )
                    displayField.setText( displayField.getText()+ "-" ); 
            }
            if( !input.equals( "+/-" ) )
            {
                String str = displayField.getText();
                if( input.equals( "退格" ) )          //退格键的实现方法
                {       
                    if( str.length() > 0 )
                        displayField.setText( str.substring( 0,str.length() - 1 ) );
                }
                else if( input.equals( "C" ) )         //清零键的实现方法
                {
                    displayField.setText( "0" );
                    start = true;
                }   
                else
                    displayField.setText( displayField.getText() + input );
            }
        }
        else if ( e.getActionCommand() == "二进制" )   //二进制的转换
        {
            int n = Integer.parseInt( displayField.getText() );
            displayField.setText( Integer.toBinaryString( n ) );
        }
        else if ( e.getActionCommand() == "八进制" )    //八进制的转换
        {
            int n = Integer.parseInt( displayField.getText() );
            displayField.setText( Integer.toOctalString( n ) );
        }
        else if ( e.getActionCommand() == "十六进制" )    //十六进制的转换
        {
            int n = Integer.parseInt( displayField.getText() );
            displayField.setText( Integer.toHexString( n ) );
        }

        else if ( e.getActionCommand() == "退出" )      //选项中退出的处理方法
        {
            System.exit( 0 );
        }
        else if ( e.getActionCommand() == "用法" )      //按下'帮助'菜单栏中用法的处理方法
        {   
            label_dialog.setText( "sqrt,exp等键是先输运算符再输数字\n" ); 
            dialog.setLocation( 400,250 );
            dialog.setVisible( true );  
        }
        else        //各运算符的识别
        {
            String command = e.getActionCommand();        
            if( start )
            {
                lastCommand = command;
            }
            else
            {
                calculate( Double.parseDouble( displayField.getText() ) );
                lastCommand = command;
                start = true;
            }
         }
    }

    public void calculate( double x )          //各运算符的具体运算方法
    {
        double d = 0;
        if ( lastCommand.equals( "+" ) ) 
            result += x;    
        else if (lastCommand.equals( "-" ) ) 
            result -= x;
        else if ( lastCommand.equals( "*" ) ) 
            result *= x;   
        else if ( lastCommand.equals( "/" ) ) 
            result /= x;
        else if ( lastCommand.equals( "=" ) ) 
            result = x;
        else if ( lastCommand.equals( "sqrt" ) ) 
        {
            d = Math.sqrt( x );
            result = d;
        }
        else if ( lastCommand.equals( "exp" ) )
        {
            d = Math.exp( x );
            result = d;
        }
        else if ( lastCommand.equals( "log" ) )
        {
            d = Math.log( x );
            result = d;
        }
        else if ( lastCommand.equals( "tan" ) )
        {
            d = Math.tan(x);
            result = d;
        }
        else if ( lastCommand.equals( "cos" ) )
        {
            d = Math.cos( x );
            result = d;
        }
        else if ( lastCommand.equals( "sin" ) )
        {
            d = Math.sin( x );
            result = d;
        }
        displayField.setText( ""+ result );
     }   

    public void windowClosing( WindowEvent e )
    {
        if( e.getSource() == dialog )
            dialog.setVisible( false );           //隐藏对话框
        else
            System.exit( 0 ); 
    }

    public void windowOpened( WindowEvent e )         {  }
    public void windowActivated( WindowEvent e )      {  }
    public void windowDeactivated( WindowEvent e )    {  }
    public void windowClosed( WindowEvent e )         {  }
    public void windowIconified( WindowEvent e )      {  }
    public void windowDeiconified( WindowEvent e )    {  }

    public static void main( String args[] )          
    {
        calculator calculator = new calculator();
    }
}

class WinClose implements WindowListener
{
    public void windowClosing( WindowEvent e )    //单击窗口关闭按钮时触发并执行实现窗口监听器接口中的方法
    {
        System.exit( 0 );          //结束程序运行
    }
    public void windowOpened( WindowEvent e ){ }
    public void windowActivated( WindowEvent e ){}
    public void windowDeactivated( WindowEvent e){ }
    public void windowClosed( WindowEvent e ){ }
    public void windowIconified( WindowEvent e ){ }
    public void windowDeiconified( WindowEvent e ){ }
}
