package com.waterelephant.service;

import java.util.Map;

/**
 * 
 * Module:
 * 
 * SystemCapitalCentersService.java
 * 
 * @author 崔雄健
 * @since JDK 1.8
 * @version 1.0
 * @description: <描述>
 * @date 2018年4月16日
 */
public interface SystemQfqCapitalCentersService {

	String updateCapitalBaofooQuery(String orderNo);

	String updateCapitalBaofooNotity(String orderNo, String state, String loanDate, String msg);

	String saveCapitalBaofooQfq(Map<String, Object> map);

}
