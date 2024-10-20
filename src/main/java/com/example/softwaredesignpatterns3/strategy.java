// Payment Strategy interface
class PaymentStrategy {
    pay(amount) {
        throw new Error("This method should be overridden");
    }
}

// Concrete Payment Strategies
class CreditCardPayment extends PaymentStrategy {
    pay(amount) {
        console.log(`Paid $${amount} using Credit Card`);
    }
}

class PayPalPayment extends PaymentStrategy {
    pay(amount) {
        console.log(`Paid $${amount} using PayPal`);
    }
}

class BitcoinPayment extends PaymentStrategy {
    pay(amount) {
        console.log(`Paid $${amount} using Bitcoin`);
    }
}

// Discount Strategy interface
class DiscountStrategy {
    applyDiscount(amount) {
        throw new Error("This method should be overridden");
    }
}

// Concrete Discount Strategies
class NoDiscount extends DiscountStrategy {
    applyDiscount(amount) {
        return amount;
    }
}

class SeasonalDiscount extends DiscountStrategy {
    applyDiscount(amount) {
        console.log("Applying 10% seasonal discount");
        return amount * 0.9;
    }
}

class LoyaltyDiscount extends DiscountStrategy {
    applyDiscount(amount) {
        console.log("Applying 20% loyalty discount");
        return amount * 0.8;
    }
}

// Context for Payment
class PaymentContext {
    constructor(paymentStrategy, discountStrategy) {
        this.paymentStrategy = paymentStrategy;
        this.discountStrategy = discountStrategy;
    }

    setPaymentStrategy(paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    setDiscountStrategy(discountStrategy) {
        this.discountStrategy = discountStrategy;
    }

    processOrder(amount) {
    const finalAmount = this.discountStrategy.applyDiscount(amount);
        this.paymentStrategy.pay(finalAmount);
    }
}

// Client code
const creditCard = new CreditCardPayment();
const payPal = new PayPalPayment();
const bitcoin = new BitcoinPayment();

const noDiscount = new NoDiscount();
const seasonalDiscount = new SeasonalDiscount();
const loyaltyDiscount = new LoyaltyDiscount();

const paymentContext = new PaymentContext(creditCard, noDiscount);

// Scenario 1: Pay with credit card, no discount
console.log("Scenario 1:");
paymentContext.processOrder(100);  // Output: Paid $100 using Credit Card

// Scenario 2: Apply seasonal discount and pay with PayPal
console.log("\nScenario 2:");
paymentContext.setPaymentStrategy(payPal);
paymentContext.setDiscountStrategy(seasonalDiscount);
paymentContext.processOrder(200);  // Output: Applying 10% seasonal discount / Paid $180 using PayPal

// Scenario 3: Apply loyalty discount and pay with Bitcoin
console.log("\nScenario 3:");
paymentContext.setPaymentStrategy(bitcoin);
paymentContext.setDiscountStrategy(loyaltyDiscount);
paymentContext.processOrder(300);  // Output: Applying 20% loyalty discount / Paid $240 using Bitcoin
