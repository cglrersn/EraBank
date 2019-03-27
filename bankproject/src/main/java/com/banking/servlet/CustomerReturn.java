package com.banking.servlet;

public class CustomerReturn {
	private int id;
	private String first_name;
	private String last_name;
	private int money;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	@Override
	public String toString() {
		/* return " [id=" + id + ", first_name=" + first_name + ", last_name=" + last_name + ", money="
				+ money + "]"; */
		return first_name + " " + last_name + "<br>Id="+ id + "<br>Balance="+ money;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}

}
