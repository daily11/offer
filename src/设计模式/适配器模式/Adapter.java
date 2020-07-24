package 设计模式.适配器模式;

public class Adapter extends Subject {

	private Adaptee adaptee;

	/**
	 * 
	 * @param adaptee
	 */
	public Adapter(Adaptee adaptee) {
		this.adaptee = adaptee;
	}

	/**
	 * 
	 * @param key
	 * @param p
	 */
	public void doEncrypt(int key, String p) {
		this.adaptee.doEncrypt(key, p);
	}

}