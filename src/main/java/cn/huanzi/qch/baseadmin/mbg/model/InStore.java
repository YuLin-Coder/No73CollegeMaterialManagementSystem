package cn.huanzi.qch.baseadmin.mbg.model;

import cn.huanzi.qch.baseadmin.common.pojo.PageCondition;

import java.io.Serializable;
import java.util.Date;

public class InStore extends PageCondition implements Serializable {
    private Integer id;

    private String purchaseOrderInId;

    private String type;

    private String goodsName;

    private Integer count;

    private String storeName;

    private Date expireTime;

    private Integer operatorId;

    private String operator;

    private Date createTime;

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPurchaseOrderInId() {
        return purchaseOrderInId;
    }

    public void setPurchaseOrderInId(String purchaseOrderInId) {
        this.purchaseOrderInId = purchaseOrderInId;
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

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
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
        sb.append(", purchaseOrderInId=").append(purchaseOrderInId);
        sb.append(", type=").append(type);
        sb.append(", goodsName=").append(goodsName);
        sb.append(", count=").append(count);
        sb.append(", storeName=").append(storeName);
        sb.append(", expireTime=").append(expireTime);
        sb.append(", operatorId=").append(operatorId);
        sb.append(", operator=").append(operator);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}