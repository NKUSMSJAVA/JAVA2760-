import javax.swing.JButton;

public class MyButton extends JButton{
	private JButton button=new JButton();
	public int i,j;//按键的坐标
	public boolean isMine=false;//默认为非雷
	private boolean isOpen=false;//是否翻开
	private boolean isFlag=false;//是否为旗子
	private boolean isFakeFlag=false;//是否为假旗子，false代表不是旗子或真旗子，true代表假旗子真地雷
	public int Minearound=0;//周围雷的数量

	MyButton(int i,int j,boolean b) {
		this.i=i;
		this.j=j;
		this.isMine=b;
	}

	public boolean isBomb(int i,int j) {
		if(this.i==i && this.j==j) {
			if(isMine) return true;
			else return false;
		}
		return false;
	}

	public void setBomb(boolean b) {
		this.isMine=b;
	}

	public void setOpen(boolean b) {
		this.isOpen=b;
	}

	public boolean getOpen() {
		return this.isOpen;
	}

	public void setMinearound(int n) {
			this.Minearound=n;
	}

	public void setFlag(boolean t) {
		this.isFlag=t;
	}

	public boolean getFlag() {
		return this.isFlag;
	}

	public void setFakeFlag(boolean t) {
		this.isFakeFlag=t;
	}

	public boolean getFakeFlag() {
		return this.isFakeFlag;
	}
}
