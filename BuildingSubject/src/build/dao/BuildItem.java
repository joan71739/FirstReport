package build.dao;

import java.io.Serializable;

public class BuildItem implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private int bd_id;
	private String bd_region;
	private String bd_dealdate;
	private String bd_buildtype;
	private String bd_loc;
	private double bd_area;
	private int bd_price;
	private String bd_parking;
	private String bd_remark;
	
	public BuildItem () {
		
	}
	
	

	



	public void setBd_id(int bd_id) {
		this.bd_id = bd_id;
	}

	public int getBd_id() {
		return bd_id;
	}

	public String getBd_region() {
		return bd_region;
	}

	public void setBd_region(String bd_region) {
		this.bd_region = bd_region;
	}

	public String getBd_dealdate() {
		return bd_dealdate;
	}

	public void setBd_dealdate(String bd_dealdate) {
		this.bd_dealdate = bd_dealdate;
	}


	public String getBd_buildtype() {
		return bd_buildtype;
	}

	public void setBd_buildtype(String bd_buildtype) {
		this.bd_buildtype = bd_buildtype;
	}

	public String getBd_loc() {
		return bd_loc;
	}

	public void setBd_loc(String bd_loc) {
		this.bd_loc = bd_loc;
	}

	public double getBd_area() {
		return bd_area;
	}

	public void setBd_area(double bd_area) {
		this.bd_area = bd_area;
	}

	public int getBd_price() {
		return bd_price;
	}

	public void setBd_price(int bd_price) {
		this.bd_price = bd_price;
	}

	public String getBd_parking() {
		return bd_parking;
	}

	public void setBd_parking(String bd_parking) {
		this.bd_parking = bd_parking;
	}


	public String getBd_remark() {
		return bd_remark;
	}

	public void setBd_remark(String bd_remark) {
		this.bd_remark = bd_remark;
	}
	
	
	
	
	
	
}
