package 设计模式.组合模式;

public abstract class File {

	/**
	 * 
	 * @param file
	 */
	public abstract void add(File file);

	/**
	 * 
	 * @param file
	 */
	public abstract void delete(File file);

	/**
	 * 
	 * @param i
	 */
	public abstract File getChild(int i);

	public abstract void killVirus();

}