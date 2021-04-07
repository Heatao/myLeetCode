package DesignPattern.BuilderTest;

public class TestHome {
    public static void main(String[] args) {
        MyHome myHome = HomeProvider.commandBuilder("高级");
        myHome.entering();
        System.out.println(myHome.getDoor());                   //这种没有被build的属性默认就是null，反正也不会被用到
        System.out.println(myHome.getKitchen());
    }
}
