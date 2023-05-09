package ShoppingService.Utils;

import ShoppingService.Model.Coupon;
import ShoppingService.Model.Product;

import java.util.Scanner;

public class Validations {
    public static String validateName(String name, Scanner sc) {
        do {
            name = Validations.validateEmpty(name, sc);
            if (Product.checkIfNameExisted(name)) {
                System.out.println("The name of the product has already been taken. Please choose another name!");
                name = sc.nextLine();
            } else {
                break;
            }
        } while (true);
        return name;
    };

    public static String validateId(String id, Scanner sc) {
        do {
            id = Validations.validateEmpty(id, sc);
            if (Coupon.checkIfCouponExisted(id)) {
                System.out.println("The id of the coupon has already been taken. Please choose another id!");
                id = sc.nextLine();
            } else {
                break;
            }
        } while (true);
        return id;
    }

    public static String validateEmpty(String variable, Scanner sc) {
        do {
            if (variable == null || variable.trim().isEmpty()) {
                System.out.println("Your string is empty! Please re-input!");
                variable = sc.nextLine();
            } else {
                break;
            }
        } while (true);
        return variable;
    }

    public static int validateNumber(String string, Scanner sc) {
        int number = 0;
        boolean valid = false;
        while (!valid) {
            try {
                number = Integer.parseInt(string);
                if (number <= 0) {
                    System.out.println("The number cannot be less than 0, please re-input the number:");
                } else {
                    valid = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid positive number:");
            }
            if (!valid) string = sc.nextLine();
        }
        return number;
    }

    public static int validatePrice(String priceInput, Scanner sc) {
        int price = 0;
        boolean valid = false;
        while (!valid) {
            try {
                price = Integer.parseInt(priceInput);
                if (price <= 0) {
                    System.out.println("The product price cannot be less than 0, please re-input the price:");
                } else {
                    valid = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid positive number:");
            }
            if (!valid) priceInput = sc.nextLine();
        }
        return price;
    }

    public static boolean validateBoolean(String giftInput, Scanner sc) {
        while (true) {
            if (giftInput.equals("y")) {
                return true;
            } else if (giftInput.equals("n")) {
                return false;
            } else {
                System.out.println("Invalid input. Please enter 'y' or 'n'.");
                giftInput = sc.nextLine();
            }
        }
    }


    public static double validatePercentCouponValue(String input, Scanner sc) {
        int value = 0;
        boolean valid = false;
        while (!valid) {
            try {
                value = Integer.parseInt(input);
                if (value <= 0) {
                    System.out.println("The coupon value cannot be less than 0, please re-input the value:");
                } else if (value > 99) {
                    System.out.println("The coupon value cannot be larger than 99, please re-input the value:");
                } else {
                    valid = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid positive number:");
            }
            if (!valid) input = sc.nextLine();
        }
        return value;
    }

    public static double validatePriceCouponValue(String input, Scanner sc) {
        int value = 0;
        boolean valid = false;
        while (!valid) {
            try {
                value = Integer.parseInt(input);
                if (value <= 0) {
                    System.out.println("The coupon value cannot be less than 0, please re-input the value:");
                } else {
                    valid = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid positive number:");
            }
            if (!valid) input = sc.nextLine();
        }
        return value;
    }

    public static String validateTaxType(String input, Scanner sc) {
        while (true) {
            switch (input) {
                case "1" -> {
                    return "FreeTaxRate";
                }
                case "2" -> {
                    return "NormalTaxRate";
                }
                case "3" -> {
                    return "LuxuryTaxRate";
                }
                default -> {
                    System.out.println("Your input should only be 1, 2 or 3 ");
                    System.out.println("Please enter the product tax type: \n\t - Free Tax Rate (1) \n\t - Normal Tax Rate (2) \n\t - Luxury Tax Rate (3)");
                    input = sc.nextLine();
                }
            }
        }
    }


    public static String validateOption(String input, Scanner sc) {
        while (true) {
            if (input.equals("1") || input.equals("2")) {
                return input;
            } else {
                System.out.println("Your input should only be 1 or 2 ");
                System.out.println("Do you want to remove an item in cart (1), or remove all product items of a product (2)?");
                input = sc.nextLine();
            }
        }
    }

    public static int validateIndex(String input, int size, Scanner sc) {
        int value = 0;
        boolean valid = false;
        while (!valid) {
            try {
                value = Integer.parseInt(input);
                if (value <= 0) {
                    System.out.println("The index value cannot be less than 0, please re-input the index:");
                } else if (value > size) {
                    System.out.println("The index value is out of range, please re-input the index:");
                } else {
                    valid = true;

                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid positive number:");
            }
            if (!valid) input = sc.nextLine();
        }
        return value;
    }
}
