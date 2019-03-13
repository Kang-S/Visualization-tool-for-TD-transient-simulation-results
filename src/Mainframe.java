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
	/*���������*/
	//�������
	Data alldata=new Data();
	TimeFrame timeframe=new TimeFrame();
	FrequencyFrame frequencyframe= new FrequencyFrame();
	
	//�ڲ����    
	Label fileInfoLable = new Label("�����ļ�:");
	Label fileNameLable = new Label("");
	Button fileSelectButton = new Button("ѡ�����ļ�");
	Button decodeButton = new Button("ȷ�ϴ���");
	Button Timeplot =new Button("ʱ��չʾ");//չʾʱ���� ���°�ť�� ��ʱ���δ��� ����ͼ
	Button Frequencyplot =new Button("Ƶ�����");//չʾƵ����� ����г���������� ��ͼ
	FileDialog fd = new FileDialog(this);
	String eFilePath = null;
	String eFile = null;
	
	
	public Mainframe() {
		this.setTitle("ʱ����̬���������ӻ�����");
		setupComponent();
		setupActions();
	}
	
	/** ������ ʵ��������ļ򵥲���*/
	public void setupComponent(){
		//�Ϸ���壬����ѡ���ΰ�ť���ļ���Ϣ�ı�ǩ���Լ����д洢�İ�ť
		Panel upPanel = new Panel();
		//�·���������ѡ���� ����������ť ʱ���κ�Ƶ�׷���
		Panel downPanel = new Panel();
		// ͼƬ��� �������� 
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
	/** ���������İ�ť��Ӧ*/
	public void setupActions(){
		
		/**�رմ���*/
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		/**ѡ���ļ���ť */
		fileSelectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fd.setMode(FileDialog.LOAD);
				fd.setVisible(true);
				eFilePath = fd.getDirectory();
				eFile = fd.getFile();
				fileNameLable.setText(eFile);
			}
		});
		
		/**���д���ť ����ȡout�ļ� �洢����data 
		 * PSCAD out��� ��һ����ʱ�� ֮��ļ����ǲ���*/
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
					
					double[] time=new double[num];//ʱ�����
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
					//�洢��alldata
					alldata.savedata(time, Var);
				}
				catch(IOException f){
					System.out.println(f.getLocalizedMessage());
				}
			}
		});
		
		/** ��ʾʱ�򴰿�*/
		Timeplot.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				timeframe.data=alldata;
				timeframe.setupComponent();
				timeframe.setupActions();
				timeframe.setVisible(true);
			}
		});
		
		/** ��ʾƵ�򴰿�*/
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

