package DesignPattern.BuilderTest;

/**
 * 具体实现的建造者，核心类
 * */
class HomeBuilder extends AbsBuilder{

    private MyHome mMyHome = new MyHome();

    @Override
    MyHome build() {
        return mMyHome;
    }

    HomeBuilder planningDoor(String door) {
        mMyHome.setDoor(door);
        return this;
    }

    HomeBuilder planningKitchen(String kitchen) {
        mMyHome.setKitchen(kitchen);
        return this;
    }

    HomeBuilder planningToilet(String toilet) {
        mMyHome.setToilet(toilet);
        return this;
    }

    HomeBuilder planningBathroom(String bathroom) {
        mMyHome.setBathroom(bathroom);
        return this;
    }

    HomeBuilder planningStudy(String study) {
        mMyHome.setStudy(study);
        return this;
    }
}
