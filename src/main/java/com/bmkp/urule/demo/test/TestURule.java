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
                user.setWaitTime(20L);
                user.setCost(0.0);
                session.insert(user);
                session.fireRules(params);
		return user.getCost();
	}


    // 自定义规则
    public Double userRuleTest() throws Exception {
        //从Spring中获取KnowledgeService接口实例
        KnowledgeService knowledgeService = (KnowledgeService) Utils.getApplicationContext()
                .getBean(KnowledgeService.BEAN_ID);
        //通过KnowledgeService接口获取资源包rs－exp
        KnowledgePackage knowledgePackage = knowledgeService.getKnowledge("bmkp/usercost");
        //KnowledgePackage knowledgePackage = knowledgeService.getKnowledge("rs-exp");
        //通过取到的KnowledgePackage对象创建KnowledgeSession对象
        KnowledgeSession session = KnowledgeSessionFactory.newKnowledgeSession(knowledgePackage);
        Map<String, Object> params = new HashMap<String, Object>();
        //通过参数的方式设置获取经验的方式为购买经验
        params.put("userRuleCost", "自定义用户消费金额");
        User user = new User();
        user.setDistance(7.0);
        user.setWaitTime(30L);
        user.setCost(0.0);
        session.insert(user);
        session.fireRules(params);
        return user.getCost();
    }

    public Double userRuleTest(User user) throws Exception {
        //从Spring中获取KnowledgeService接口实例
        KnowledgeService knowledgeService = (KnowledgeService) Utils.getApplicationContext()
                .getBean(KnowledgeService.BEAN_ID);
        //通过KnowledgeService接口获取资源包rs－exp
        KnowledgePackage knowledgePackage = knowledgeService.getKnowledge("bmkp/usercost");
        //KnowledgePackage knowledgePackage = knowledgeService.getKnowledge("rs-exp");
        //通过取到的KnowledgePackage对象创建KnowledgeSession对象
        KnowledgeSession session = KnowledgeSessionFactory.newKnowledgeSession(knowledgePackage);
        Map<String, Object> params = new HashMap<String, Object>();
        //通过参数的方式设置获取经验的方式为购买经验
        params.put("userRuleCost", "自定义用户消费金额");
        session.insert(user);
        session.fireRules(params);
        return user.getCost();
    }



}
