package DesignPattern.BuilderTest;

/**
 * 示例类,房子
 * */
public class MyHome {
    private String mDoor;
    private String mKitchen;
    private String mToilet;
    private String mBathroom;
    private String mStudy;

    public String getDoor() {
        return mDoor;
    }
    public void setDoor(String mDoor) {
        this.mDoor = mDoor;
    }
    public String getKitchen() {
        return mKitchen;
    }
    public void setKitchen(String mKitchen) {
        this.mKitchen = mKitchen;
    }
    public String getToilet() {
        return mToilet;
    }
    public void setToilet(String mToilet) {
        this.mToilet = mToilet;
    }
    public String getBathroom() {
        return mBathroom;
    }
    public void setBathroom(String mBathroom) {
        this.mBathroom = mBathroom;
    }
    public String getStudy() {
        return mStudy;
    }
    public void setStudy(String mStudy) {
        this.mStudy = mStudy;
    }

    public MyHome() {
    }

    public void entering() {
        System.out.println("I entering in myHome");
    }

}
