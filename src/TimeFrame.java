import java.awt.*;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;

import org.knowm.xchart.*;
/**
 * 
 * @author huang
 * 此窗口为时域波形展示窗口 //设置退出键 而不是右上角的关闭
 * 实现功能：
 * 	展示时域波形
 *  画出波形 
 *  可以放大缩小 
 *  可以页面快照 
 *  可以发送邮件
 */
public class TimeFrame extends JFrame{
	public int a,b,c,d;
	public Data data;
	public XYChart chart;
	private Button share;//发送邮件分享
	private Button save;//页面快照
	public TimeFrame() {
		this.setTitle("时域波形展示");
	}
	public void setupComponent(){
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//设置上部
//		a=this.getBounds().x;
//		b=this.getBounds().y;
//		c=this.getWidth();
//		d=this.getHeight();
		XYChart chart = QuickChart.getChart("time wave", "time", "Var", "y(x)", data.gettime(), data.getvalue());
		JPanel ChartPanel=new XChartPanel(chart);//显示波形图
		Panel downPanel=new Panel();//放两个功能按钮
		
		share=new Button("一键分享");
		save =new Button("页面快照");
		downPanel.add(share);
		downPanel.add(save);
		
		
		setLayout(new BorderLayout());
		add(ChartPanel,"North");
		add(downPanel, "South");
		pack();
//		a=this.getBounds().x;
//		b=this.getBounds().y;
//		c=(int)this.getWidth();
//		d=(int)this.getHeight();
	}
	public void setupActions(){
		//一键存储 截屏动态化
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				a=TimeFrame.getFrames()[1].getBounds().x;
				b=TimeFrame.getFrames()[1].getBounds().y;
				c=TimeFrame.getFrames()[1].getWidth();
				d=TimeFrame.getFrames()[1].getHeight();
				 Test cam= new Test("C:\\Users\\huang\\Desktop\\screen", "png");//
				 cam.snapShot(a,b,c,d);
//				try{
//					 Object[] options = { "OK", "CANCEL" }; 
//					 JOptionPane.showOptionDialog(null, "Click OK to continue", "储存成功", 
//					 JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, 
//					 null, options, options[0]); 
//					 BitmapEncoder.saveBitmap(chart,"C:\\Users\\huang\\Desktop\\Bitmap.png", BitmapEncoder.BitmapFormat.PNG);			
//				}
//				catch(IOException f){
//					System.out.println(f.getLocalizedMessage());
//				}
			}
		});
		//一键分享 网络编程
		share.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
			MailTest ff=new MailTest();
			try{
			ff.send();}
			catch(Exception a){
				System.out.println(a.getLocalizedMessage());
			}
		}
		});
	}
}
