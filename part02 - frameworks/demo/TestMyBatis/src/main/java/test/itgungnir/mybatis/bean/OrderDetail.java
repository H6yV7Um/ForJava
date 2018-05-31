package test.itgungnir.mybatis.bean;

public class OrderDetail {
    public int id;
    public int orderId;
    public int itemsId;
    public int itemsNum;

    public OrderDetail() {
    }

    public OrderDetail(int id, int orderId, int itemsId, int itemsNum) {
        this.id = id;
        this.orderId = orderId;
        this.itemsId = itemsId;
        this.itemsNum = itemsNum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getItemsId() {
        return itemsId;
    }

    public void setItemsId(int itemsId) {
        this.itemsId = itemsId;
    }

    public int getItemsNum() {
        return itemsNum;
    }

    public void setItemsNum(int itemsNum) {
        this.itemsNum = itemsNum;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", itemsId=" + itemsId +
                ", itemsNum=" + itemsNum +
                '}';
    }
}