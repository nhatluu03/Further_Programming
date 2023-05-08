package ShoppingService.Model;

/**
 * @author Nguyen Nhat Minh - s3932112
 */

import ShoppingService.Utils.Validations;

import java.util.*;

public abstract class Product {
    // Attribute declarations
    protected String name;
    protected String description;
    protected int quantityAvailable;
    protected double price;
    protected String tax;
    protected boolean isGiftable;
    protected List<Coupon> coupons;
    private final double shippingFee = 0.1;
    private final double taxFreeRate = 0.0;
    private final double normalTaxRate = 0.1;
    private final double luxuryTaxRate = 0.2;
    public static Map<String, Product> products = new HashMap<>();

    // Constructors
    public Product(String name, String description, int quantityAvailable, double price, boolean isGiftable, String taxType) {
        if (Product.checkIfNameExisted(name)) {
            System.out.println("The name of the product has already be taken. Please choose another name!");
        } else {
            this.name = name;
            this.description = description;
            this.quantityAvailable = quantityAvailable;
            this.price = price;
            this.isGiftable = isGiftable;
            this.tax = taxType;
            coupons = new ArrayList<>();
            products.put(name, this);
        }
    }

    public Product(String name, String description, int quantityAvailable, double price, boolean isGiftable, String taxType, Map<String, Product> products) {
        if (Product.checkIfNameExisted(name)) {
            System.out.println("The name of the product has already been taken. Please choose another name!");
        } else {
            this.name = name;
            this.description = description;
            this.quantityAvailable = quantityAvailable;
            this.price = price;
            this.isGiftable = isGiftable;
            this.tax = taxType;
            coupons = new ArrayList<>();
            products.put(name, this);
        }
    }

    // Getters & Setters
    public String getName() {
        return name;
    }

    public String getTaxType() {
        return this.tax;
    }
    public double getTax() {
        if (tax.equals("FreeTaxRate")) {
            return taxFreeRate;
        } else if (tax.equals("NormalTaxRate")) {
            return normalTaxRate;
        } else if (tax.equals("LuxuryTaxRate")) {
            return luxuryTaxRate;
        }
        return 0;
    }
    public List<Coupon> getCoupons() {
        return coupons;
    }
    public void setGiftable(boolean giftable) {
        isGiftable = giftable;
    }
    public void setTax(String tax) {
        this.tax = tax;
    }
    public void setCoupons(ArrayList<Coupon> coupons) {
        this.coupons = coupons;
    }

    public boolean isGiftable() {
        return isGiftable;
    }


    public String getDescription() {
        return description;
    }

    public int getQuantityAvailable() {
        return quantityAvailable;
    }

    public double getPrice() {
        return price;
    }

    public abstract String getType();

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setQuantityAvailable(int quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    public void setQuantityAvailable(String quantityAvailable) {
        Scanner sc = new Scanner(System.in);
        int quantity = 0;
        boolean valid = false;
        while (!valid) {
            try {
                quantity = Integer.parseInt(quantityAvailable);
                if (quantity < 0) {
                    System.out.println("The quantity available cannot be less than 0, please re-input the quantity:");
                } else {
                    valid = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid positive integer number:");
            }
            if (!valid) quantityAvailable = sc.nextLine();
        }
        this.quantityAvailable = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public static Product getProduct(String name) {
        return products.get(name);
    }

    public static Map<String, Product> getProducts() {
        return products;
    }

    public static void setProducts(Map<String, Product> products) {
        Product.products = products;
    }

    public void addCoupon(Scanner sc) {
        String input;
        System.out.println("Please input the coupon id!");
        input = sc.nextLine();
        String id = Validations.validateId(input, sc);

        System.out.println("Please choose the coupon type: Percent Coupon (1) or Price Coupon (2)");
        input = sc.nextLine();
        Coupon coupon = null;
        if (input.equals("1")) {
            System.out.println("Please input the value of the coupon: (1-99)");
            input = sc.nextLine();
            double value = Validations.validatePercentCouponValue(input, sc);
            coupon = new PercentCoupon(id, value);
        } else if (input.equals("2")) {
            System.out.println("Please input the value of the coupon: > 0 ");
            input = sc.nextLine();
            double value = Validations.validatePriceCouponValue(input, sc);
            coupon = new PriceCoupon(id, value);
        }
        coupons.add(coupon);
    }

    public void addCoupon(Coupon coupon) {
        coupons.add(coupon);
    }


    @Override
    public String toString() {
        return getType() + " - " + getName();
    }
    public abstract void updateWeight();
    public static boolean checkIfNameExisted(String name) {
        return products.containsKey(name);
    }
    public static void iterateUsingForEach() {
        System.out.println("List of Products:");
        for (Map.Entry<String, Product> entry : products.entrySet()) {
            Product value = entry.getValue();
            System.out.println("-  " + value);
        }
    }
}

