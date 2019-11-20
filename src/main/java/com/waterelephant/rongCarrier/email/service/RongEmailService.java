package com.waterelephant.rongCarrier.email.service;

import java.util.Map;

public interface RongEmailService {

	/**
	 * rong360公共认证调用接口
	 * @param paramMap
	 * @return
	 */
	public String collectuserCommon(Map<String, String> paramMap);
	
	/**
	 * rong360获取京东数据
	 * @param userId
	 * @return
	 */
	public String getData(String userId, String searchId);
}
