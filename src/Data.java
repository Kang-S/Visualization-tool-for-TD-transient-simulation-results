/**
 * 
 * @author huang
 *	Data类 为数据存储类 使用二维数组
 */
public class Data {
	double[] time;
	double[] value;
	public void savedata(double[] readtime,double[] readvalue){
		this.time=readtime;
		this.value=readvalue;
	}
	public double[] gettime(){
		return this.time;
	}
	public double[] getvalue(){
		return this.value;
	}
}
