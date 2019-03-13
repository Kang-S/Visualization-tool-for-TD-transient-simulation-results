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
 * �˴���Ϊʱ����չʾ���� //�����˳��� ���������ϽǵĹر�
 * ʵ�ֹ��ܣ�
 * 	չʾʱ����
 *  �������� 
 *  ���ԷŴ���С 
 *  ����ҳ����� 
 *  ���Է����ʼ�
 */
public class TimeFrame extends JFrame{
	public int a,b,c,d;
	public Data data;
	public XYChart chart;
	private Button share;//�����ʼ�����
	private Button save;//ҳ�����
	public TimeFrame() {
		this.setTitle("ʱ����չʾ");
	}
	public void setupComponent(){
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//�����ϲ�
//		a=this.getBounds().x;
//		b=this.getBounds().y;
//		c=this.getWidth();
//		d=this.getHeight();
		XYChart chart = QuickChart.getChart("time wave", "time", "Var", "y(x)", data.gettime(), data.getvalue());
		JPanel ChartPanel=new XChartPanel(chart);//��ʾ����ͼ
		Panel downPanel=new Panel();//���������ܰ�ť
		
		share=new Button("һ������");
		save =new Button("ҳ�����");
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
		//һ���洢 ������̬��
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
//					 JOptionPane.showOptionDialog(null, "Click OK to continue", "����ɹ�", 
//					 JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, 
//					 null, options, options[0]); 
//					 BitmapEncoder.saveBitmap(chart,"C:\\Users\\huang\\Desktop\\Bitmap.png", BitmapEncoder.BitmapFormat.PNG);			
//				}
//				catch(IOException f){
//					System.out.println(f.getLocalizedMessage());
//				}
			}
		});
		//һ������ ������
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
