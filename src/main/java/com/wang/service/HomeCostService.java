package com.wang.service;

import java.util.List;

import com.wang.dao.HomeCostDao;
import com.wang.pojo.HomeCost;
import JavaBean.Page;

public class HomeCostService {
	
	HomeCostDao homeCostDao = new HomeCostDao();
	
	//���������˵�
	public int add(HomeCost homecost) {
		return homeCostDao.add(homecost);
	}
	
	//ɾ�������˵�
	public int delete(int id) {
		return homeCostDao.delete(id);
	}
	
	//�޸������˵�
	public int update(HomeCost homecost) {
		return homeCostDao.update(homecost);
	}
	
	//�ؼ��ֲ�ѯ
	public List<HomeCost> query(String keyword) {
		return homeCostDao.query(keyword);
	}
	
	//ȫ�����Ѽ�¼
	public List<HomeCost> list() {
		return homeCostDao.list();
	}
	
	//��id����ĳ�����Ѽ�¼
	public HomeCost getHomeCostById(int id) {
		return homeCostDao.getHomeCostById(id);
	}

	//��ҳ
	public Page<HomeCost> findHomeCost(int pageNum, int pageSize, String a) {
		//System.out.println("hi,is findHomeCost");
		//���书�ܴ�ʵ��
		List<HomeCost>allHomeCostList=list(); //a�����䣬���з���ͨ��a���ҵ����˵��б�
		Page<HomeCost> pager=new Page<HomeCost>(pageNum,pageSize,allHomeCostList);
		return pager;
	}
}
