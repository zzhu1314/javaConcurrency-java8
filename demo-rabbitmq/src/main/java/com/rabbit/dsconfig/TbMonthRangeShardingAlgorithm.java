package com.rabbit.dsconfig;


import com.google.common.collect.Range;
import com.rabbit.utils.Utils;
import io.shardingsphere.api.algorithm.sharding.RangeShardingValue;
import io.shardingsphere.api.algorithm.sharding.standard.RangeShardingAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Date;
import java.util.TreeSet;


/**
 * 处理 between and的路由
 * @author LiaoYao
 * @date：2018年9月4日
 */
public class TbMonthRangeShardingAlgorithm implements RangeShardingAlgorithm<String>{

	private static final Logger LOGGER = LoggerFactory.getLogger(TbMonthRangeShardingAlgorithm.class);
	
	@Override
	public Collection<String> doSharding(Collection<String> availableTargetNames,
			RangeShardingValue<String> shardingValue) {
		Collection<String> result = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
		Object[] arrTagetName = availableTargetNames.toArray();//开始分库 其后分表
		if(shardingValue.getColumnName().compareToIgnoreCase("swipe_time")==0){
			Range<String> valueRange = shardingValue.getValueRange();
			try {
				Date startDate = Utils.parseyyyy_MM_ddHHmmss(valueRange.lowerEndpoint());//TODO 这个地方需要判断valueRange.lowerEndpoint()的格式 不然时间会转换出错导致定位错误
				Date endDate = Utils.parseyyyy_MM_ddHHmmss(valueRange.upperEndpoint());
				String startMonth = Utils.MM(startDate);
				String endMonth = Utils.MM(endDate);
				String startYear = Utils.yyyy(startDate);
				String endYear = Utils.yyyy(endDate);
				System.out.println(String.format("startYear:%s,endYear:%s,startMonth:%s,endMonth:%s",startYear, endYear, startMonth, endMonth));
				int lower = 0;
				int upper = 0;
				boolean isBetween = true;
				if(startYear.compareTo(endYear) == 0){
//					System.out.println("年份相等");
					isBetween = true;
					lower = Integer.parseInt(startMonth) - 1;
					upper = Integer.parseInt(endMonth) - 1;
				}else{
					int startY = Integer.parseInt(startYear);
					int endY = Integer.parseInt(endYear);
					int startM = Integer.parseInt(startMonth);
					int endM = Integer.parseInt(endMonth);
					if((endY - startY ) != 1 || (startM - endM) <= 1){
//						System.out.println("全库查询");
						isBetween = true;
						lower = 0;//全年 所有月份
						upper = 11;
					}else{
//						System.out.println("使用in");
						lower = Integer.parseInt(startMonth) - 1;
						upper = Integer.parseInt(endMonth) - 1;
						isBetween = false;
					}
				}
				if(isBetween){
					for(int i=lower;i<upper + 1;++i){//按照季度找表
						result.add((String)arrTagetName[i]);
					}
				}else{
					for(int i = lower; i < 12; i++){
						result.add((String)arrTagetName[i]);
					}//从lower 到 12月份
					for(int i = 0; i < upper + 1; ++i){//从1月份到upper
						result.add((String)arrTagetName[i]);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		LOGGER.info("Range sharding:"+result.toString());
		return result;
	}

}
