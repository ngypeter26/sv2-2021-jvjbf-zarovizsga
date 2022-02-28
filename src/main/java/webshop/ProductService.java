package webshop;

public class ProductService {
    private ProductRepository pr;

    public ProductService(ProductRepository pr) {
        this.pr = pr;
    }

    public void saleProduct(long id, int amount){
        Product product = pr.findProductById(id);

        if (product.getStock() >= amount){
            pr.updateProductStock(id,amount);
        }else{
            throw new IllegalArgumentException("Not enough stock");
        }
    }
}
