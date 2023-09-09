function step1(): Promise<string> {
    return new Promise((resolve) => {
        setTimeout(() => {
            console.log("Step 1 complete");
            resolve("Result of Step 1");
        }, 1000);
    });
}

function step2(previousResult: string): Promise<string> {
    return new Promise((resolve) => {
        setTimeout(() => {
            console.log("Step 2 complete");
            resolve(`${previousResult} -> Result of Step 2`);
        }, 1000);
    });
}

function step3(previousResult: string): Promise<string> {
    return new Promise((resolve) => {
        setTimeout(() => {
            console.log("Step 3 complete");
            resolve(`${previousResult} -> Result of Step 3`);
        }, 1000);
    });
}

console.log("Starting...");

// Promise chaining
step1()
    .then((result) => {
        return step2(result);
    })
    .then((result) => {
        return step3(result);
    })
    .then((finalResult) => {
        console.log("All steps complete:", finalResult);
    })
    .catch((error) => {
        console.error("An error occurred:", error);
    });
