package com.bmkp.urule.demo;

import org.hibernate.EmptyInterceptor;

import com.bstek.dorado.util.proxy.ProxyBeanUtils;

public class UnByteCodeProxyInterceptor extends EmptyInterceptor {
	public static final String BEAN_ID="test.unByteCodeProxyInterceptor";
	private static final long serialVersionUID = -6422637558312349795L;
	@Override
	public String getEntityName(Object object) {
		if (object != null) {
			Class<?> cl = ProxyBeanUtils.getProxyTargetType(object);
			return cl.getName();
		} else {
			return null;
		}
	}

}