package DesignPattern.BuilderTest;

/**
 * 建造者抽象类
 *
 * Created by wangshaobin on 7/30/18.
 */

abstract class AbsBuilder<T> {
    /**
     * 建造者建造后要返回的对象
     * */
    abstract T build();

    /*
     * 相关操作
     * */
    abstract T planningDoor(String door);
    abstract T planningKitchen(String kitchen);
    abstract T planningToilet(String toilet);
    abstract T planningBathroom(String bathroom);
    abstract T planningStudy(String study);
}
