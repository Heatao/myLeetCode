package DesignPattern.BuilderTest;

/**
 * 售楼处类
 *
 * Created by wangshaobin on 7/30/18.
 */
class HomeProvider {
    /**
     * 售楼处将用户需求交给建造者，由建造者去创建房屋对象
     *
     * @param level 档次
     * */
    static MyHome commandBuilder(String level) {
        HomeBuilder homeBuilder = new HomeBuilder();
        return homeBuilder
                .planningBathroom(level+"浴室")
//                .planningDoor(level+"门")
                .planningKitchen(level+"厨房")
                .planningStudy(level+"书房")
                .planningToilet(level+"厕所")
                .build();
    }
    /*
    调用代码
    MyHome myHome = HomeProvider.commandBuilder("高级");
    myHome.entering();
     */
}
