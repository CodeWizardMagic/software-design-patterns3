package com.example.softwaredesignpatterns3;
public class VisitorPattern {

    // Product interface
    interface Product {
        void accept(Visitor visitor);
    }

    // Concrete Product classes: Book and Electronics
    static class Book implements Product {
        private double price;

        public Book(double price) {
            this.price = price;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visitBook(this);
        }
    }

    static class Electronics implements Product {
        private double price;

        public Electronics(double price) {
            this.price = price;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visitElectronics(this);
        }
    }

    // Visitor interface
    interface Visitor {
        void visitBook(Book book);
        void visitElectronics(Electronics electronics);
    }

    // Concrete Visitors: DiscountVisitor and ShippingCostVisitor
    static class DiscountVisitor implements Visitor {
        @Override
        public void visitBook(Book book) {
            System.out.println("Applying discount to Book. Original Price: " + book.getPrice());
            book.setPrice(book.getPrice() * 0.9);  // 10% discount
            System.out.println("Discounted Price: " + book.getPrice());
        }

        @Override
        public void visitElectronics(Electronics electronics) {
            System.out.println("Applying discount to Electronics. Original Price: " + electronics.getPrice());
            electronics.setPrice(electronics.getPrice() * 0.8);  // 20% discount
            System.out.println("Discounted Price: " + electronics.getPrice());
        }
    }

    static class ShippingCostVisitor implements Visitor {
        @Override
        public void visitBook(Book book) {
            System.out.println("Shipping cost for Book: 5");
        }

        @Override
        public void visitElectronics(Electronics electronics) {
            System.out.println("Shipping cost for Electronics: 10");
        }
    }

    // Main method to demonstrate the Visitor Pattern
    public static void main(String[] args) {
        // Create products
        Book book = new Book(100);  // Price: $100
        Electronics electronics = new Electronics(200);  // Price: $200

        // Create visitors
        Visitor discountVisitor = new DiscountVisitor();
        Visitor shippingCostVisitor = new ShippingCostVisitor();

        // Apply the discount visitor
        System.out.println("Applying Discount Visitor:");
        book.accept(discountVisitor);
        electronics.accept(discountVisitor);

        // Apply the shipping cost visitor
        System.out.println("\nApplying Shipping Cost Visitor:");
        book.accept(shippingCostVisitor);
        electronics.accept(shippingCostVisitor);
    }
}
