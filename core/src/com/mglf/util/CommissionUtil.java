package com.mglf.util;


/**
 * 
 * @author zhanghuichun
 *	佣金计算公式类
 */
public class CommissionUtil {
	
	static double str = 1000;
	static double end = 10000;
	static double x = 0;
	
	
	//吉利数字
	static int[] luckynumber={1,3,5,6,8};
	
	//所得税税金
	public static double getTax(double m){
		double tax=0;
		int[] border={1000000000,10000,8000,4000,3500};
		int[] rate={30,20,15,10,5};
		double wage=m;
		 for(int i = 0;i < rate.length; i++){
			   if(wage - border[i] > 0){ //有超出对应级数金额
			    //应纳个人所得税税额= 应纳税所得额 × 适用税率 累加
			    tax += ((wage-border[i])*rate[i])/100 ;
			    wage -= (wage - border[i]);//计算下一级
			   }
			}
		return tax;
	} 
	
	//劳务税税金
	public static double  getServiceTax(double m){
		double personUnexpectedRate = 0.2;
		double tax = 0;
		double rate = 0;
		double balan = 0;
		double tmp = 0;
		
		if(m < 800){
			return tax;
		}else if(m < 4000){
			tax = (m - 800) * personUnexpectedRate;
			return tax;
		}else{
			tmp = m * (1 - personUnexpectedRate);
			if(tmp <= 20000)
            {
				rate = 0.2;
				balan = 0;
            } else if(tmp <= 50000)
            {
            	rate = 0.3;
            	balan = 2000;
            } else
            {
            	rate = 0.4;
            	balan = 7000;
            }
			tax = m * (1 - personUnexpectedRate) * rate - balan;
			return tax;
			
		}
	}
		
	
	
	//旧的算佣金法
	//获取佣金
	public static int getCommission(int min,int max) {
		double commission=0;
		int luckCommission =0;
//		//最大佣金
//		int max=(m*40)/100;
//		//最低佣金
//		int zong=(m*30)/100;

		System.out.println("输入值"+min+"-"+max);
		x = getVibrate(min,max);
		System.out.println("计算值"+(min+(max-min)*x));
		double baseCommission = (min+(max-min)*x)*30/100;
		System.out.println("基值"+baseCommission);
		double tax=getTax(baseCommission);
		System.out.println("税金"+tax);
		//默认佣金
		//commission=(baseCommission-tax);
		commission = baseCommission;
		System.out.println("税后"+commission);
		//佣金十位数向上取吉利数字
		luckCommission = getLuck(commission);
		
		return luckCommission;
	}
	
	//获取佣金
	public static int calculateCommission(int commission) {
		double tax = 0;
		tax = getServiceTax(commission);
		commission -= tax;
		if(tax > 0){
			commission = getDownLuck(commission);
		}
		
		return commission;
	}
	
	//计算推荐值
	public static int getCommendCommission(int min,int max){
		double commission=0;
		x = getVibrate(min,max);
		commission = (min+(max-min)*x)*30/100;
		System.out.println("基值"+commission);
		commission += 50-commission % 50;
		
		return (int)commission;
	}
	
	//佣金向上取吉利数字
	public static int getLuck(double commission){
			
			//TODO 使用吉利数字08 18 58 68 88 
		
		int finCommission =  (int)commission/100;
		finCommission = finCommission*100;
		double unLuck = commission%100;
		if(unLuck > 88){
			finCommission += 108;
		}else if(unLuck > 68){
			finCommission += 88;
		}else if(unLuck > 58){
			finCommission += 68;
		}else if(unLuck > 18){
			finCommission += 58;
		}else{
			finCommission += 8;
		}
			

		return finCommission;
	}
	
	//佣金向下取吉利数字
		public static int getDownLuck(double commission){
				
				//TODO 使用吉利数字08 18 28 58 68 88 98
			
			int finCommission =  (int)commission/100;
			finCommission = finCommission*100;
			double unLuck = commission%100;
			if(unLuck >= 98){
				finCommission += 98;
			}else if(unLuck >= 88){
				finCommission += 88;
			}else if(unLuck >= 68){
				finCommission += 68;
			}else if(unLuck >= 58){
				finCommission += 58;
			}else if(unLuck >= 28){
				finCommission += 28;
			}else if(unLuck >= 18){
				finCommission += 18;
			}else if(unLuck >= 8){
				finCommission += 8;
			}else{
				finCommission += 0;
			}
				
			return finCommission;
		}
	
	//string [] 转int
	public static int listToInt(String[] array){
		int ret=0;
		ret=Integer.valueOf(array.toString());
		return ret;
	}
	
	//佣金向上取吉利数
	public static int getLucky(int k){
		int ret=k;
		for (int i = 0; i < luckynumber.length; i++) {
			if(luckynumber[i]>k){
				ret=luckynumber[i];
				break;
			}
		}
		return ret;
	}
	
	//测试
	public static void main(String[] args) {
		System.out.println(getCommendCommission(10000,15000));
 	}
	
	//获取震荡幅
	public static double getVibrate(int min,int max) {
		int dif = max-min;
		if(dif < str){
			return 0.5;
		}
		if(dif > end){
			return 0;
		}
		double vibrate = 0.5 * (1-(max - min -str) * (max - min -str) / (end -str) / (end - str));
		return vibrate;
	}
	
	
}
