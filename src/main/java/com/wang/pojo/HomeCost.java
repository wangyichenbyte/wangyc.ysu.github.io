package com.wang.pojo;

import java.math.BigDecimal;

public class HomeCost {
	
	private int id;//id
    private String name;//消费名称
    private BigDecimal money;//消费金额
    private String mess;//消费备注
    private String date;//消费日期
    private BigDecimal sum;//累计消费

    
	public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }



    public BigDecimal getMoney() {
        return money;
    }
    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getMess(){return mess;}
    public void setMess(String mess){
        this.mess=mess;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public BigDecimal getSum() {
		return sum;
	}
	public void setSum(BigDecimal sum) {
		this.sum = sum;
	}
	
	
       
    @Override
	public String toString() {
		return "HomeCost [id=" + id + ", name=" + name + ",mess="+mess+", money=" + money + ", date=" + date + ", sum=" + sum + "]";
	}
    
	public HomeCost() {}
    
    public HomeCost(String name, BigDecimal money) {
		super();
		this.name = name;
		this.money = money;
	}

    /*public HomeCost(String name,BigDecimal money, String date) {
        super();
        this.name = name;
        this.money=money;
        this.date=date;
    }*/
    public HomeCost(int id,String name,BigDecimal money, String date) {
    	super();
        this.id=id;
        this.name = name;
        this.money=money;
        this.date=date;
    }
	public HomeCost(int id, String name, BigDecimal money, String mess,String date, BigDecimal sum) {
		super();
		this.id = id;
		this.name = name;
		this.money = money;
        this.mess=mess;
		this.date = date;
		this.sum = sum;
	}
    public HomeCost(int id,String name,BigDecimal money, String mess,String date) {
        super();
        this.id=id;
        this.name = name;
        this.mess=mess;
        this.money=money;
        this.date=date;
    }
    public HomeCost(String name,BigDecimal money,String mess) {
        super();
        this.name = name;

        this.money=money;
        this.mess=mess;
    }
}
