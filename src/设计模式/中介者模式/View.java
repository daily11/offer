package 设计模式.中介者模式;

public class View extends Component {

	private Mediator mediator;

	public View(Mediator mediator){
		this.mediator = mediator;
	}

	/**
	 *
	 * @param id
	 */
	public void delete(int id) {
		this.mediator.componentChanged(this, id);
	}

}