package ShoppingService.Model;

/**
 * @author Nguyen Nhat Minh - s3932112
 */

import java.util.Scanner;

public class PhysicalProduct extends Product {
    private double weight;

    public PhysicalProduct(String name, String description, int quantityAvailable, double price, double weight, boolean isGiftable, String taxType) {
        super(name, description, quantityAvailable, price, isGiftable, taxType);
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public void updateWeight() {
        System.out.printf("Current weight: %s\n", getWeight());
        System.out.print("Enter new weight available (or press enter to keep current weight): ");
        Scanner scanner = new Scanner(System.in);
        String newWeight = scanner.nextLine();
        if (!newWeight.isEmpty()) {
            this.setWeight(Double.parseDouble(newWeight));
        }
    }


    @Override
    public String getType() {
        return "PHYSICAL";
    }
}