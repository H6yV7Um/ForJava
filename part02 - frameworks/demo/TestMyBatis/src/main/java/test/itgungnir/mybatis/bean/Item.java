package test.itgungnir.mybatis.bean;

public class Item {
    public int id;
    public String name;
    public double price;
    public String detail;
    public String pic;
    public String createTime;

    public Item() {
    }

    public Item(int id, String name, double price, String detail, String pic, String createTime) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.detail = detail;
        this.pic = pic;
        this.createTime = createTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", detail='" + detail + '\'' +
                ", pic='" + pic + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}