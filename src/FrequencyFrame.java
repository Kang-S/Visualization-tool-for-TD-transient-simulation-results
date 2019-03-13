import java.awt.*;

import javax.swing.*;
import java.util.*;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;

import java.math.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
/**
 * 
 * @author huang
 *	 //�����˳��� ���������ϽǵĹر�
 */

public class FrequencyFrame extends JFrame{
	public Data data;
	public FrequencyFrame() {
		this.setTitle("Ƶ�׷���");
		setupComponent();
		setupActions();
	}
	public void setupComponent(){
		this.setSize(500, 500);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//���Ʋ���ͼ
		//Ƶ������
		try{
		int N=data.getvalue().length;
		int fs=100000;
		double[] f=new double[N];
		double[] mag=new double[N];
		for(int i=0;i<N;i++){
			f[i]=(double)(i*fs/N);
		}
		//��ֵ����
		FFT test=new FFT();
		mag=test.fft(data.getvalue());
		XYChart chart = QuickChart.getChart("frequency wave", "f/Hz", "��ֵ", "y(x)", Arrays.copyOfRange(f,1,1000), Arrays.copyOfRange(mag,1,1000));
		JPanel ChartPanel=new XChartPanel(chart);//��ʾ����ͼ
		setLayout(new BorderLayout());
		add(ChartPanel,"Center");
		pack();
		}
		catch(Exception h){
			System.out.println(h.getLocalizedMessage());
		}

	}
	public void setupActions(){
		/**�رմ���*/
		
	}
}
