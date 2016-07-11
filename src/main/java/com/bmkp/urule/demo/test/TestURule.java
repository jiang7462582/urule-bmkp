package com.bmkp.urule.demo.test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.bmkp.urule.demo.model.User;
import com.bstek.urule.Utils;
//import com.bstek.urule.demo.model.OrderDetail;
import com.bstek.urule.runtime.KnowledgePackage;
import com.bstek.urule.runtime.KnowledgeSession;
import com.bstek.urule.runtime.KnowledgeSessionFactory;
import com.bstek.urule.runtime.service.KnowledgeService;

/**
 *@author Kevin.yang
 *@since 2015年6月15日
 */
public class TestURule {
	
	
	public Double test() throws Exception {
		//从Spring中获取KnowledgeService接口实例
		KnowledgeService knowledgeService = (KnowledgeService) Utils.getApplicationContext()
				.getBean(KnowledgeService.BEAN_ID);
		//通过KnowledgeService接口获取资源包rs－exp
		KnowledgePackage knowledgePackage = knowledgeService.getKnowledge("bmkp/cost");
                //KnowledgePackage knowledgePackage = knowledgeService.getKnowledge("rs-exp");
       //通过取到的KnowledgePackage对象创建KnowledgeSession对象
		KnowledgeSession session = KnowledgeSessionFactory.newKnowledgeSession(knowledgePackage);
		Map<String, Object> params = new HashMap<String, Object>();
		//通过参数的方式设置获取经验的方式为购买经验
		params.put("userCost", "用户消费金额");
                User user = new User();
                user.setDistance(5.0);
                user.setWaitTime(700L);
                user.setCost(0.0);
                session.insert(user);
                session.fireRules(params);
//		OrderDetail orderDetail = new OrderDetail();
//		//设置订单的实际价格为300
//		orderDetail.setRealPrice(new BigDecimal(300));
//		Customer customer = new Customer();
//		//初始化客户经验值为100
//		customer.setExp(100);
//		//关联订单详情orderDetail对象
//		session.insert(orderDetail);
//		//关联客户customer对象
//		session.insert(customer);
//		//设置参数并执行规则
//		session.fireRules(params);
//		return customer.getExp();
		return user.getCost();
	}
}
