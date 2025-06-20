import java.util.Arrays;
import java.util.Comparator;

public class Ecommerce {
	 public static Product linearSearch(Product[] products, String name) {
	        for (Product product : products) {
	            if (product.productName.equalsIgnoreCase(name)) {
	                return product;
	            }
	        }
	        return null;
	    }

	    public static Product binarySearch(Product[] products, String name) {
	        int left = 0;
	        int right = products.length - 1;
	        while (left <= right) {
	            int mid = (left + right) / 2;
	            int compare = products[mid].productName.compareToIgnoreCase(name);
	            if (compare == 0) return products[mid];
	            else if (compare < 0) left = mid + 1;
	            else right = mid - 1;
	        }
	        return null;
	    }
	    public static void main(String[] args) {
	        Product[] products = {
	            new Product(101, "Laptop", "Electronics"),
	            new Product(102, "Shoes", "Fashion"),
	            new Product(103, "Smartphone", "Electronics"),
	            new Product(104, "Book", "Education"),
	            new Product(105, "Tablet", "Electronics")
	        };
	        System.out.println("Linear Search:");
	        Product found1 = linearSearch(products, "Book");
	        System.out.println(found1 != null ? found1 : "Product not found");
	        Arrays.sort(products, Comparator.comparing(p -> p.productName.toLowerCase()));
	        System.out.println("\nBinary Search:");
	        Product found2 = binarySearch(products, "Book");
	        System.out.println(found2 != null ? found2 : "Product not found");
	    }
}
