package 设计模式.组合模式;

public class ImageFile extends File {

	private String name;

	public ImageFile(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @param file
	 */
	public void add(File file) {
		System.out.println("不能实现该方法！");
	}

	/**
	 * 
	 * @param file
	 */
	public void delete(File file) {
		System.out.println("不能实现该方法！");
	}

	/**
	 * 
	 * @param i
	 */
	public File getChild(int i) {
		return null;
	}

	public void killVirus() {
		System.out.println("图片文件杀毒！");
	}

}