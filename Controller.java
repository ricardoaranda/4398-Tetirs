public class Controller {

	private String name;
	private DoesSomething action;

	public void main() {
		action = new DoesSomething();
		action.changeName(name);
		throw new UnsupportedOperationException();
	}

}