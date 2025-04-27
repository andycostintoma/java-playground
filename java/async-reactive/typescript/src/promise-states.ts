function simulateAsyncOperation(success: boolean): Promise<string> {
    return new Promise((resolve, reject) => {
        setTimeout(() => {
            if (success) {
                resolve("Operation succeeded!"); // Resolve the Promise
            } else {
                reject("Operation failed!"); // Reject the Promise
            }
        }, 1000); // Simulate a 1-second delay
    });
}

console.log("Starting...");

// Create a Promise that will resolve
const resolvedPromise = simulateAsyncOperation(true);

// Create a Promise that will reject
const rejectedPromise = simulateAsyncOperation(false);

// Demonstrating Promise states
resolvedPromise
    .then((result) => {
        console.log("Resolved Promise:", result);
    })
    .catch((error) => {
        console.error("Resolved Promise Error:", error);
    });

rejectedPromise
    .then((result) => {
        console.log("Rejected Promise:", result);
    })
    .catch((error) => {
        console.error("Rejected Promise Error:", error);
    });

console.log("Promises created. Waiting for completion...");
