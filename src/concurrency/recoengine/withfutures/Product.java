package concurrency.recoengine.withfutures;


public class Product {
	
	private int id;
	private String name;
	private float price;
	private int qoh;
	
	public Product() {
		
	}

	public Product(int id, String name, float price, int qoh) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.qoh = qoh;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public float getPrice() {
		return price;
	}

	public int getQoh() {
		return qoh;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", qoh=" + qoh + "]";
	}
	
	

}
