import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import matlabcontrol.*;

/**
 * @author huang
 * 实现与MATLAB的交互 
 * 使用matlab里的fft来进行分析
 */
public class FFT{
	public double[] fft(double[] input) throws Exception{
		double[] output;
		MatlabProxyFactory factory=new MatlabProxyFactory();
		MatlabProxy proxy=factory.getProxy();
		proxy.setVariable("x", input);
		proxy.eval("abs(fft(x))");
		output=(double[])proxy.getVariable("ans");
//		proxy.eval("exit");
		return output;		
	}
//	public void fft()throws Exception{
//		MatlabProxyFactory factory=new MatlabProxyFactory();
//		MatlabProxy proxy=factory.getProxy();
//		proxy.eval("disp('hello world')");
//		proxy.eval("exit");
//	}	
	/**
	 * test of fft
	 */
//	public static void main(String args[]) throws Exception{
//			//
//			File target=new File("C:\\Users\\huang\\Desktop\\data.txt");
//			FileReader fnum=new FileReader(target);
//			BufferedReader bnum=new BufferedReader(fnum);
//			int num=0;
//			String numberline=null;
//			numberline=bnum.readLine();
//			while(numberline!=null){
//				num=num+1;
//				numberline=bnum.readLine();
//			}
//			bnum.close();
//			fnum.close();		
//			double[] time=new double[num];//时间变量
//			double[] Var=new double[num];
//			FileReader fr=new FileReader(target);
//			BufferedReader btag=new BufferedReader(fr);
//			String readline=null;
//			int i=0;
//			readline=btag.readLine();
//			readline=btag.readLine();
//			System.out.println(readline);
//			System.out.println(readline.split(" +")[1]);
//			while((readline!=null)&&(i<119999)){
//				time[i]=Double.valueOf(readline.split(" +")[1]);
//				Var[i]=Double.valueOf(readline.split(" +")[2]);
//				i++;
//				readline=btag.readLine();
//			}
//			//
//			FFT test=new FFT();
//			test.fft(Var);
//			
//	}
}