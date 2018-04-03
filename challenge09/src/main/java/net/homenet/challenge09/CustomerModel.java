package net.homenet.challenge09;

public class CustomerModel {
    private int resourceId;
    private String name;
    private String birthday;
    private String telNo;

    public int getResourceId() {
        return resourceId;
    }

    public String getName() {
        return name;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getTelNo() {
        return telNo;
    }

    public CustomerModel(int resourceId, String name, String birthday, String telNo) {
        this.resourceId = resourceId;
        this.name = name;
        this.birthday = birthday;
        this.telNo = telNo;
    }
}
