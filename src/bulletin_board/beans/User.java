package bulletin_board.beans;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String loginId;
	private String password;
	private String name;
	private int branchId;
	private int departmentId;
	private int isWorking;
	private String branchName;
	private String departmentName;
	/**
	 * idを取得します。
	 * @return id
	 */
	public int getId() {
	    return id;
	}
	/**
	 * idを設定します。
	 * @param id id
	 */
	public void setId(int id) {
	    this.id = id;
	}
	/**
	 * loginIdを取得します。
	 * @return loginId
	 */
	public String getLoginId() {
	    return loginId;
	}
	/**
	 * loginIdを設定します。
	 * @param loginId loginId
	 */
	public void setLoginId(String loginId) {
	    this.loginId = loginId;
	}
	/**
	 * passwordを取得します。
	 * @return password
	 */
	public String getPassword() {
	    return password;
	}
	/**
	 * passwordを設定します。
	 * @param password password
	 */
	public void setPassword(String password) {
	    this.password = password;
	}
	/**
	 * nameを取得します。
	 * @return name
	 */
	public String getName() {
	    return name;
	}
	/**
	 * nameを設定します。
	 * @param name name
	 */
	public void setName(String name) {
	    this.name = name;
	}
	/**
	 * branchIdを取得します。
	 * @return branchId
	 */
	public int getBranchId() {
	    return branchId;
	}
	/**
	 * branchIdを設定します。
	 * @param branchId branchId
	 */
	public void setBranchId(int branchId) {
	    this.branchId = branchId;
	}
	/**
	 * departmentIdを取得します。
	 * @return departmentId
	 */
	public int getDepartmentId() {
	    return departmentId;
	}
	/**
	 * departmentIdを設定します。
	 * @param departmentId departmentId
	 */
	public void setDepartmentId(int departmentId) {
	    this.departmentId = departmentId;
	}
	/**
	 * is_workingを取得します。
	 * @return isWorking
	 */
	public int getIsWorking() {
	    return isWorking;
	}
	/**
	 * is_workingを設定します。
	 * @param isWorking isWorking
	 */
	public void setIsWorking(int isWorking) {
	    this.isWorking = isWorking;
	}
	/**
	 * branchNameを取得します。
	 * @return branchName
	 */
	public String getBranchName() {
	    return branchName;
	}
	/**
	 * branchNameを設定します。
	 * @param branchName branchName
	 */
	public void setBranchName(String branchName) {
	    this.branchName = branchName;
	}
	/**
	 * departmentNameを取得します。
	 * @return departmentName
	 */
	public String getDepartmentName() {
	    return departmentName;
	}
	/**
	 * departmentNameを設定します。
	 * @param departmentName departmentName
	 */
	public void setDepartmentName(String departmentName) {
	    this.departmentName = departmentName;
	}
}
