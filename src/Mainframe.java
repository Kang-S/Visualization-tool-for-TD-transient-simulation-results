import java.awt.*;
import javax.swing.*;

import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;

import java.io.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
/**
 * 
 * @author huang
 *
 */
public class Mainframe extends Frame {
	/*类变量配置*/
	//交互组件
	Data alldata=new Data();
	TimeFrame timeframe=new TimeFrame();
	FrequencyFrame frequencyframe= new FrequencyFrame();
	
	//内部组件    
	Label fileInfoLable = new Label("波形文件:");
	Label fileNameLable = new Label("");
	Button fileSelectButton = new Button("选择波形文件");
	Button decodeButton = new Button("确认储存");
	Button Timeplot =new Button("时域展示");//展示时域波形 按下按钮后 打开时域波形窗口 曲线图
	Button Frequencyplot =new Button("频域分析");//展示频域分析 各次谐波分量比例 饼图
	FileDialog fd = new FileDialog(this);
	String eFilePath = null;
	String eFile = null;
	
	
	public Mainframe() {
		this.setTitle("时域暂态仿真结果可视化工具");
		setupComponent();
		setupActions();
	}
	
	/** 添加组件 实现主界面的简单布局*/
	public void setupComponent(){
		//上方面板，放置选择波形按钮，文件信息的标签，以及进行存储的按钮
		Panel upPanel = new Panel();
		//下方的面板放置选择功能 放置两个按钮 时域波形和频谱分析
		Panel downPanel = new Panel();
		// 图片面板 放在最下 
		Panel picPanel = new Panel();
//		Panel p = new ImgPanel(Toolkit.getDefaultToolkit().createImage("C:\\Users\\huang\\Desktop\\pic.jpg"));
//		p.setBounds(80, 80, 80, 80);
		
		upPanel.setLayout(new GridLayout(1,5));
		upPanel.add(fileInfoLable);
		upPanel.add(fileNameLable);
		upPanel.add(fileSelectButton);
		upPanel.add(decodeButton);
		
		downPanel.setLayout(new GridLayout(1,2));
		downPanel.add(Timeplot);
		downPanel.add(Frequencyplot);
		Timeplot.setBackground(Color.yellow);
		Frequencyplot.setBackground(Color.green);
		
	    JLabel label = new JLabel(new ImageIcon("C:\\Users\\huang\\Desktop\\pic.png"));
//		picPanel.setLayout(new GridLayout(1,2));
//		picPanel.add(label);
		setLayout(new BorderLayout());
		add(upPanel, "North");
		add(downPanel, "Center");
		add(label,"South");
		pack();
	}
	/** 添加主界面的按钮响应*/
	public void setupActions(){
		
		/**关闭窗口*/
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		/**选择文件按钮 */
		fileSelectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fd.setMode(FileDialog.LOAD);
				fd.setVisible(true);
				eFilePath = fd.getDirectory();
				eFile = fd.getFile();
				fileNameLable.setText(eFile);
			}
		});
		
		/**进行处理按钮 即读取out文件 存储进入data 
		 * PSCAD out输出 第一列是时间 之后的几列是波形*/
		decodeButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				File target=new File(eFilePath,eFile);
				try{
					FileReader fnum=new FileReader(target);
					BufferedReader bnum=new BufferedReader(fnum);
					int num=0;
					String numberline=null;
					numberline=bnum.readLine();
					while(numberline!=null){
						num=num+1;
						numberline=bnum.readLine();
					}
					bnum.close();
					fnum.close();
					
					double[] time=new double[num];//时间变量
					double[] Var=new double[num];
					FileReader fr=new FileReader(target);
					BufferedReader btag=new BufferedReader(fr);
					String readline=null;
					int i=0;
					readline=btag.readLine();
					readline=btag.readLine();
					System.out.println(readline);
					System.out.println(readline.split(" +")[1]);
					while((readline!=null)&&(i<119999)){
						time[i]=Double.valueOf(readline.split(" +")[1]);
						Var[i]=Double.valueOf(readline.split(" +")[2]);
						i++;
						readline=btag.readLine();
					}
					//存储至alldata
					alldata.savedata(time, Var);
				}
				catch(IOException f){
					System.out.println(f.getLocalizedMessage());
				}
			}
		});
		
		/** 显示时域窗口*/
		Timeplot.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				timeframe.data=alldata;
				timeframe.setupComponent();
				timeframe.setupActions();
				timeframe.setVisible(true);
			}
		});
		
		/** 显示频域窗口*/
		Frequencyplot.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				frequencyframe.data=alldata;
				frequencyframe.setupComponent();
				frequencyframe.setupActions();
				frequencyframe.setVisible(true);
			}
		});
	}
	
	
	public static void main(String [] args) {
		Mainframe frame = new Mainframe();
		frame.setVisible(true);	
	}
}

