/******************************************************************************
 * Copyright (C) 2016 Wuhan Water Elephant Co.Ltd All Rights Reserved. 
 * 本软件为武汉水象科技有限公司开发研制。 未经本公司正式书面同意，其他任何个人、
 * 团体不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.waterelephant.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.waterelephant.entity.BwCreditCloudReason;

import tk.mybatis.mapper.common.Mapper;

/**
 * 
 * 
 * 
 * Module:
 * 
 * BwCreditCloudReasonMapper.java
 * 
 * @author 崔雄健
 * @since JDK 1.8
 * @version 1.0
 * @description: <描述>
 * @date 2019年4月22日
 */
public interface BwCreditCloudReasonMapper extends Mapper<BwCreditCloudReason> {

	@Select("select a.* from bw_credit_cloud_reason a where a.credit_push_no =#{creditPushNo}  order by a.id desc limit 1")
	public BwCreditCloudReason findBwCreditCloudReason(@Param("creditPushNo") String creditPushNo);
}
