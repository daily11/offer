package 设计模式.适配器模式;

public abstract class Subject {

	private String password;

	public String getPassword() {
		return this.password;
	}

	/**
	 * 
	 * @param p
	 */
	public void setPassword(String p) {
		this.password = p;
	}

	/**
	 * 
	 * @param key
	 * @param p
	 */
	public abstract void doEncrypt(int key, String p);

}