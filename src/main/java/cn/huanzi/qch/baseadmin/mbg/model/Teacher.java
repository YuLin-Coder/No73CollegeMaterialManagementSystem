package cn.huanzi.qch.baseadmin.mbg.model;

import cn.huanzi.qch.baseadmin.common.pojo.PageCondition;

import java.io.Serializable;

public class Teacher extends PageCondition implements Serializable {
    private Integer id;

    private Integer userId;

    private String loginName;

    private String userName;

    private String dept;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDept() {
        return dept;
    } /*院部没有*/

    public void setDept(String dept) {
        this.dept = dept;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", loginName=").append(loginName);
        sb.append(", userName=").append(userName);
        sb.append(", dept=").append(dept);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}