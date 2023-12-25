package cn.huanzi.qch.baseadmin.mbg.model;

import cn.huanzi.qch.baseadmin.common.pojo.PageCondition;

import java.io.Serializable;

public class Distribute extends PageCondition implements Serializable {
    private Integer id;

    private String type;

    private String goodsName;

    private Integer count;

    private String status;

    private Integer teacherId;

    private String teacher;

    private String dept;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getDept() {
        return dept;
    }

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
        sb.append(", type=").append(type);
        sb.append(", goodsName=").append(goodsName);
        sb.append(", count=").append(count);
        sb.append(", status=").append(status);
        sb.append(", teacherId=").append(teacherId);
        sb.append(", teacher=").append(teacher);
        sb.append(", dept=").append(dept);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}