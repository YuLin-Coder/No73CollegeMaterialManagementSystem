package cn.huanzi.qch.baseadmin.mbg.model;

import cn.huanzi.qch.baseadmin.common.pojo.PageCondition;

import java.io.Serializable;
import java.util.Date;

public class OutStore extends PageCondition implements Serializable {
    private Integer id;

    private String purchaseOrderOutId;

    private String type;

    private String goodsName;

    private Integer count;

    private String storeName;

    private Integer operatorId;

    private String operator;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPurchaseOrderOutId() {
        return purchaseOrderOutId;
    }

    public void setPurchaseOrderOutId(String purchaseOrderOutId) {
        this.purchaseOrderOutId = purchaseOrderOutId;
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

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", purchaseOrderOutId=").append(purchaseOrderOutId);
        sb.append(", type=").append(type);
        sb.append(", goodsName=").append(goodsName);
        sb.append(", count=").append(count);
        sb.append(", storeName=").append(storeName);
        sb.append(", operatorId=").append(operatorId);
        sb.append(", operator=").append(operator);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}