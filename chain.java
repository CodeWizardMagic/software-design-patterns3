// Abstract Handler
class SupportHandler {
    setNext(handler) {
        this.nextHandler = handler;
        return handler;
    }

    handleRequest(request) {
        if (this.nextHandler) {
            return this.nextHandler.handleRequest(request);
        } else {
            console.log("Request could not be handled.");
        }
    }
}

// Concrete Handlers
class BillingSupportHandler extends SupportHandler {
    handleRequest(request) {
        if (request.type === "billing") {
            console.log(`Billing Support: Resolved issue '${request.issue}'`);
        } else {
            return super.handleRequest(request);
        }
    }
}

class TechnicalSupportHandler extends SupportHandler {
    handleRequest(request) {
        if (request.type === "technical") {
            console.log(`Technical Support: Resolved issue '${request.issue}'`);
        } else {
            return super.handleRequest(request);
        }
    }
}

class GeneralSupportHandler extends SupportHandler {
    handleRequest(request) {
        if (request.type === "general") {
            console.log(`General Support: Resolved issue '${request.issue}'`);
        } else {
            return super.handleRequest(request);
        }
    }
}

class UnhandledRequestHandler extends SupportHandler {
    handleRequest(request) {
        console.log(`No department could handle the issue: '${request.issue}'`);
    }
}

// Client code
const billingSupport = new BillingSupportHandler();
const technicalSupport = new TechnicalSupportHandler();
const generalSupport = new GeneralSupportHandler();
const unhandledRequest = new UnhandledRequestHandler();

// Chain setup: Billing -> Technical -> General -> Unhandled
billingSupport
        .setNext(technicalSupport)
  .setNext(generalSupport)
  .setNext(unhandledRequest);

// Create some requests
const billingRequest = { type: "billing", issue: "Incorrect charge on bill" };
        const technicalRequest = { type: "technical", issue: "Cannot connect to server" };
        const generalRequest = { type: "general", issue: "How do I reset my password?" };
        const unknownRequest = { type: "unknown", issue: "Is there an office nearby?" };

// Process requests
        billingSupport.handleRequest(billingRequest);  // Output: Billing Support: Resolved issue 'Incorrect charge on bill'
billingSupport.handleRequest(technicalRequest);  // Output: Technical Support: Resolved issue 'Cannot connect to server'
billingSupport.handleRequest(generalRequest);  // Output: General Support: Resolved issue 'How do I reset my password?'
billingSupport.handleRequest(unknownRequest);  // Output: No department could handle the issue: 'Is there an office nearby?'
