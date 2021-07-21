package InstanceCode.Singleton;

public final class DoubleCheckedLockingSingleton {
    private static DoubleCheckedLockingSingleton instance = null;

    private DoubleCheckedLockingSingleton(){};

    public static DoubleCheckedLockingSingleton getInstance() {
        if (instance == null) {
            synchronized (DoubleCheckedLockingSingleton.class) {
                if (instance == null) {
                    instance = new DoubleCheckedLockingSingleton();
                }
            }
        }

        return instance;
    }
}
