// Simulated async operation using setTimeout
function simulateAsyncOperation(callback: (result: string) => void) {
    setTimeout(() => {
        const data = "Async operation is complete!";
        callback(data);
    }, 2000); // Simulate a 2-second delay
}

// Callback function to handle the result
function handleResult(result: string) {
    console.log(result);
}

// Call the async operation with a callback
simulateAsyncOperation(handleResult);

console.log("Waiting for async operation to complete...");
