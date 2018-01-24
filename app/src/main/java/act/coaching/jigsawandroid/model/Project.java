package act.coaching.jigsawandroid.model;

/**
 * Created by actmember on 2018. 1. 24..
 */

public class Project {
    private String _id;
    private String employeeNumber;
    private String projectCode;
    private int __v;
    private String assignType;
    private String projectName;
    private String duty;
    private String rank;
    private String department;
    private String team;
    private String cellPhoneNumber;
    private String gender;
    private String email;
    private String nickName;
    private String name;

    public String get_id() {
        return _id;
    }

    public Project set_id(String _id) {
        this._id = _id;
        return this;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public Project setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
        return this;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public Project setProjectCode(String projectCode) {
        this.projectCode = projectCode;
        return this;
    }

    public int get__v() {
        return __v;
    }

    public Project set__v(int __v) {
        this.__v = __v;
        return this;
    }

    public String getAssignType() {
        return assignType;
    }

    public Project setAssignType(String assignType) {
        this.assignType = assignType;
        return this;
    }

    public String getProjectName() {
        return projectName;
    }

    public Project setProjectName(String projectName) {
        this.projectName = projectName;
        return this;
    }

    public String getDuty() {
        return duty;
    }

    public Project setDuty(String duty) {
        this.duty = duty;
        return this;
    }

    public String getRank() {
        return rank;
    }

    public Project setRank(String rank) {
        this.rank = rank;
        return this;
    }

    public String getDepartment() {
        return department;
    }

    public Project setDepartment(String department) {
        this.department = department;
        return this;
    }

    public String getTeam() {
        return team;
    }

    public Project setTeam(String team) {
        this.team = team;
        return this;
    }

    public String getCellPhoneNumber() {
        return cellPhoneNumber;
    }

    public Project setCellPhoneNumber(String cellPhoneNumber) {
        this.cellPhoneNumber = cellPhoneNumber;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public Project setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Project setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getNickName() {
        return nickName;
    }

    public Project setNickName(String nickName) {
        this.nickName = nickName;
        return this;
    }

    public String getName() {
        return name;
    }

    public Project setName(String name) {
        this.name = name;
        return this;
    }
}
