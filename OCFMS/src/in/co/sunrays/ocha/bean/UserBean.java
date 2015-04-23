package in.co.sunrays.ocha.bean;

import java.sql.Timestamp;
import java.util.Date;

/**
 * User JavaBean encapsulates User attributes
 * 
 * @author SUNRAYS Technologies
 * @version 1.0
 * @Copyright (c) SUNRAYS Technologies
 * 
 */

public class UserBean extends BaseBean {
	
	/**
     * Lock Active constant for User
     */
    public static final String ACTIVE = "Active";
    /**
     * Lock Inactive constant for User
     */
    public static final String INACTIVE = "Inactive";
    /**
     * First Name of User
     */
    private String firstName;
    /**
     * Last Name of User
     */
    private String lastName;
    private String collegeCode;
    private String collegeId;
    private String branch;
    private String year;
    private String fatherName;
    private String motherName;
    private String address;
    /**
     * Login of User
     */
    private String login;
  

	/**
     * Password of User
     */
    private String password;

    /**
     * Confirm Password of User
     */
    private String confirmPassword;
    /**
     * Date of Birth of User
     */
    private Date dob;
    /**
     * MobielNo of User
     */
    private String mobileNo;
    /**
     * Role of User
     */
    private long roleId;
    /**
     * Number of unsuccessful login attempt
     */
    private int unSuccessfulLogin;
    /**
     * Gender of User
     */
    private String gender;
    /**
     * Last login timestamp
     */
    private Timestamp lastLogin;
    /**
     * User Lock
     */
    private String lock = INACTIVE;
    /**
     * IP Address of User from where User was registred.
     */
    private String registeredIP;
    /**
     * IP Address of User of his last login
     */
    private String lastLoginIP;

    /**
     * accessor
     */

    public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getLock() {
		return lock;
	}

	public void setLock(String lock) {
		this.lock = lock;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}
    public String getCollegeCode() {
		return collegeCode;
	}

	public void setCollegeCode(String collegeCode) {
		this.collegeCode = collegeCode;
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public int getUnSuccessfulLogin() {
		return unSuccessfulLogin;
	}

	public void setUnSuccessfulLogin(int unSuccessfulLogin) {
		this.unSuccessfulLogin = unSuccessfulLogin;
	}

	public String getRegisteredIP() {
		return registeredIP;
	}

	public void setRegisteredIP(String registeredIP) {
		this.registeredIP = registeredIP;
	}

	public String getLastLoginIP() {
		return lastLoginIP;
	}

	public void setLastLoginIP(String lastLoginIP) {
		this.lastLoginIP = lastLoginIP;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Timestamp getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Timestamp lastLogin) {
		this.lastLogin = lastLogin;
	}
	  public String getCollegeId() {
			return collegeId;
		}

		public void setCollegeId(String collegeId) {
			this.collegeId = collegeId;
		}

		public String getFatherName() {
			return fatherName;
		}

		public void setFatherName(String fatherName) {
			this.fatherName = fatherName;
		}

		public String getMotherName() {
			return motherName;
		}

		public void setMotherName(String motherName) {
			this.motherName = motherName;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

	@Override
	public String getKey() {
		return id +"";
	}

	@Override
	public String getValue() {
		return firstName + " " + lastName;
	}

}
