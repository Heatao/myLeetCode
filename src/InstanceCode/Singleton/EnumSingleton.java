package InstanceCode.Singleton;

/**
 * 调用方法
 * String name = World.INSTANCE.getName();
 */
public enum EnumSingleton {
    INSTANCE;

    private String name = "EnumSingleton";

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
