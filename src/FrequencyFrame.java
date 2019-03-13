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
 *	 //设置退出键 而不是右上角的关闭
 */

public class FrequencyFrame extends JFrame{
	public Data data;
	public FrequencyFrame() {
		this.setTitle("频谱分析");
		setupComponent();
		setupActions();
	}
	public void setupComponent(){
		this.setSize(500, 500);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//绘制波形图
		//频域坐标
		try{
		int N=data.getvalue().length;
		int fs=100000;
		double[] f=new double[N];
		double[] mag=new double[N];
		for(int i=0;i<N;i++){
			f[i]=(double)(i*fs/N);
		}
		//幅值坐标
		FFT test=new FFT();
		mag=test.fft(data.getvalue());
		XYChart chart = QuickChart.getChart("frequency wave", "f/Hz", "幅值", "y(x)", Arrays.copyOfRange(f,1,1000), Arrays.copyOfRange(mag,1,1000));
		JPanel ChartPanel=new XChartPanel(chart);//显示波形图
		setLayout(new BorderLayout());
		add(ChartPanel,"Center");
		pack();
		}
		catch(Exception h){
			System.out.println(h.getLocalizedMessage());
		}

	}
	public void setupActions(){
		/**关闭窗口*/
		
	}
}
