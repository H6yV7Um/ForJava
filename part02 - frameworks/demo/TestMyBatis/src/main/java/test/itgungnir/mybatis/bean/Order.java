package test.itgungnir.mybatis.bean;

public class Order {
    public int id;
    public int user_id;
    public String number;
    public String createTime;
    public String note;

    public Order() {
    }

    public Order(int id, int user_id, String number, String createTime, String note) {
        this.id = id;
        this.user_id = user_id;
        this.number = number;
        this.createTime = createTime;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", number='" + number + '\'' +
                ", createTime='" + createTime + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}