package 设计模式.适配器模式;

public class Adaptee {

    /**
     * @param key
     * @param p
     */
    public void doEncrypt(int key, String p) {
        System.out.println("key = " + key + ", p = " + p);
    }

}