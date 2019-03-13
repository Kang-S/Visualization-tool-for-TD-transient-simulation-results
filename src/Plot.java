import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.applet.*;
import org.knowm.xchart.*;
import org.knowm.xchart.internal.*;
import org.knowm.xchart.style.*;
import java.io.*;
import java.util.Arrays;
/**
 * 借助外界绘图包 进行绘图测试程序 主要对各种包进行测试 选取最优
 * @author huang
 *
 */

public class Plot {
//	int num=100;
//	private Data dtalab;
//	double[] i=new double [num];
	public static void main(String args[]){
			//测试12000个数据的读取方法与绘制
			//要对doubles数组进行截取		
			File target=new File("C:\\Users\\huang\\Desktop\\data.txt");
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
				int N=Var.length;
				int fs=100000;
				double[] f=new double[N];
				double[] mag=new double[N];
				for(i=0;i<N;i++){
					f[i]=(double)(i*fs/N);
				}
				FFT test=new FFT();
				mag=test.fft(Var);
				XYChart chart = QuickChart.getChart("time wave", "time", "Var", "y(x)", Arrays.copyOfRange(f,1,1000), Arrays.copyOfRange(mag,1,1000));
				new SwingWrapper(chart).displayChart();
			}
			catch(Exception h){
				System.out.println(h.getLocalizedMessage());
			}
//			String f="0.36080182429648E-02";
//			double dd = Double.valueOf(f); 
//			System.out.println(dd);
			//
//			double[] xData = new double[] { 0.0, 1.0, 2.0 };
//		    double[] yData = new double[] { 2.0, 1.0, 0.0 };
//
//		    // Create Chart
////		    Chart chart = QuickChart.getChart("Sample Chart", "X", "Y", "y(x)", xData, yData);
//
//		    // Show it
	}
}
