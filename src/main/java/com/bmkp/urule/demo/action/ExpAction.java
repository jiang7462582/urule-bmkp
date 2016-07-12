package com.bmkp.urule.demo.action;

import java.text.DateFormat;
import java.util.Date;
import java.util.Set;

//import com.bstek.urule.demo.model.Order;

/**
 *@author Kevin.yang
 *@since 2015年5月19日
 */

public class ExpAction {
	
	public boolean isNotToday(Date date) {
		String dateStr=  DateFormat.getDateInstance().format(date);
		String today=  DateFormat.getDateInstance().format(new Date());
		if (today.equals(dateStr)) {
			return false;
		}
		return true;
	}
	
//	public int getCustomerBuyDay (Set<Order> orders) {
//		return orders.size();
//	}

    public Double costMoney(Double distance,Long waitTime,Long minDistance,Long minBaseCost,
            Double gapMoneyForMile,Double gapMoneyForTime,Long minMinute){
        Long minute =new Double(Math.ceil(waitTime/60.0)).longValue();
        Long distance_=new Double(Math.ceil(distance)).longValue();
        Long time = waitTime<minMinute? 0L:waitTime-minMinute;
        if(distance_<minDistance){
            return minBaseCost+time*gapMoneyForTime;
        }else{
            return (distance_- minDistance)*gapMoneyForMile+ minBaseCost+time*gapMoneyForTime;
        }

    }

}
