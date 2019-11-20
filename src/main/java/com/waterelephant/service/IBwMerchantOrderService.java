/******************************************************************************
 * Copyright (C) 2016 Wuhan Medical union Co.Ltd All Rights Reserved. 
 * 本软件为武汉水象科技有限公司开发研制。 未经本公司正式书面同意，其他任何个人、
 * 团体不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.waterelephant.service;

import com.waterelephant.entity.BwMerchantOrder;

/**
 * 
 * 
 * Module:
 * 
 * IBwMerchantOrderService.java
 * 
 * @author zhangchong
 * @since JDK 1.8
 * @version 1.0
 * @description: <描述>
 */
public interface IBwMerchantOrderService extends BaseCommonService<BwMerchantOrder, Long> {

	BwMerchantOrder selectByAtt(BwMerchantOrder bwMerchantOrder);

	Integer insertByAtt(BwMerchantOrder bwMerchantOrder);

	Integer updateByAtt(BwMerchantOrder bwMerchantOrder);
}
