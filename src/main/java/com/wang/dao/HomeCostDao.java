package com.wang.dao;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.wang.pojo.HomeCost;
import com.wang.utils.DBUtils;

public class HomeCostDao {
	
	/**
	 * updatesql()����ִ�� insert/update/delete���
	 * @param sql �����sql���
	 * @return ����-1��˵��ִ��ʧ�ܣ�����ΪӰ����������
	 */
	public int updatesql(String sql) {
		Connection conn = DBUtils.getConn();//��ȡ���Ӷ���
        Statement state = null;
        try {
            state = conn.createStatement();
            return state.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(state, conn);
        }
        return -1;       
	}
	
	
	//���
    public int add(HomeCost homecost) {
    	//insert��䣬����insert into ����(�ֶ�1���ֶ�2) values('ֵ1','ֵ2');
        String sql = "insert into home(name,money,mess) values('"+ homecost.getName() + "','" + homecost.getMoney() +  "','"+homecost.getMess()+"')";
        return updatesql(sql);
    }
    
    //ɾ��
    public int delete (int id) {
    	//delete��䣬����delete from ���� where id='ֵ';
        String sql = "delete from home where id='" + id + "'";
        return updatesql(sql);
    }
     
    //�޸�
    public int update(HomeCost homecost) {
    	//update��䣬����update ���� set �ֶ�1 = 'ֵ1',�ֶ�2 = 'ֵ2'where id = 'ֵ3';
        String sql = "update home set name='" + homecost.getName() + "', money='" 
        		+ homecost.getMoney()+ "' , mess='"+homecost.getMess()+"' where id='" + homecost.getId() + "'";
        return updatesql(sql);
        
    }
    
    //��ѯ
    public List<HomeCost> query(String keyword) {
    	String sql = "select * from home WHERE name LIKE '%"+keyword+"%' OR money LIKE '%"+keyword
    			+"%' OR mess LIKE '%" +keyword +"%'OR date LIKE '%"+keyword+ "%'";
        List<HomeCost> list = new ArrayList<>();
        Connection conn = DBUtils.getConn();
        Statement state = null;
        ResultSet rs = null;
        try {
            state = conn.createStatement();
            rs = state.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");//��ȡ��ѯ����е�id
                String name = rs.getString("name");//��ȡ��ѯ����е�name
                BigDecimal money = rs.getBigDecimal("money");//��ȡ��ѯ����е�money
                String mess=rs.getString("mess");
                String date = rs.getString("date");//��ȡ��ѯ����е�date
                HomeCost homeCost = new HomeCost(id,name,money,mess,date);//���ù��췽����ֵ
                list.add(homeCost);//��ӵ�list������
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(rs, state, conn);
        }
        return list;
    }
    
    //ͨ��id�ҵ�ĳ����Ϣ
    public HomeCost getHomeCostById(int id) {
        String sql = "select * from home where id ='" + id + "'";
        Connection conn = DBUtils.getConn();
        Statement state = null;
        ResultSet rs = null;
        HomeCost homeCost = null;
        try {
            state = conn.createStatement();
            rs = state.executeQuery(sql);
            while (rs.next()) {
                String name = rs.getString("name");
                BigDecimal money = rs.getBigDecimal("money");
                String mess = rs.getString("mess");
                String date = rs.getString("date");
                homeCost = new HomeCost(id,name,money,mess,date);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(rs, state, conn);
        }
        return homeCost;
    }
    
    /**
     * ͨ��id����������Ѽ�¼�ۼ����ѽ��
     * @return BigDecimal����money
     */
    public BigDecimal queryMoneySum(int id) {
        String sql = "select money from home where id <="+id;
        BigDecimal sum = new BigDecimal("0.00");
        Connection conn = DBUtils.getConn();
        Statement state = null;
        ResultSet rs = null;
        try {
            state = conn.createStatement();
            rs = state.executeQuery(sql);
            while (rs.next()) {
            	BigDecimal money = rs.getBigDecimal("money");
            	//sum��money�ۼ�ֵ
                sum = sum.add(money);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(rs, state, conn);
        }
        return sum;
    }
         
    //��ȡȫ������  
    public List<HomeCost> list() {
        String sql = "select * from home";
        List<HomeCost> list = new ArrayList<>();
        Connection conn = DBUtils.getConn();
        Statement state = null;
        ResultSet rs = null;
        try {
            state = conn.createStatement();
            rs = state.executeQuery(sql);
            HomeCost homeCost = null;
            while (rs.next()) {
                int id = rs.getInt("id");
                BigDecimal sum = queryMoneySum(id);
                String name = rs.getString("name");
                BigDecimal money = rs.getBigDecimal("money");
                String mess = rs.getString("mess");
                String date = rs.getString("date");
                homeCost = new HomeCost(id,name,money,mess,date,sum);
                list.add(homeCost);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(rs, state, conn);
        }
        return list;
    }

   /* // ��ѯָ��ҳ������Ѽ�¼�б�
    public List<HomeCost> getHomeCostList(int currentPage, int pageSize) {
        List<HomeCost> homeCostList = new ArrayList<>();
        // ��ȡ���ݿ�����
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConn();
            String sql = "SELECT * FROM home.home LIMIT ?, ?";
            int startIndex = (currentPage - 1) * pageSize;
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, startIndex);
            pstmt.setInt(2, pageSize);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double money = rs.getDouble("money");
                Date date = rs.getDate("date");
                HomeCost homeCost = new HomeCost(id, name, money, date);
                homeCostList.add(homeCost);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(rs);
            DBUtils.close(pstmt);
            DBUtils.close(conn);
        }
        return homeCostList;
    */
}