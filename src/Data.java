/**
 * 
 * @author huang
 *	Data�� Ϊ���ݴ洢�� ʹ�ö�ά����
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
