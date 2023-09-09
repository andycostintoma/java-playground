function step1(callback: () => void) {
    setTimeout(() => {
        console.log("Step 1 complete");
        callback();
    }, 1000);
}

function step2(callback: () => void) {
    setTimeout(() => {
        console.log("Step 2 complete");
        callback();
    }, 1000);
}

function step3(callback: () => void) {
    setTimeout(() => {
        console.log("Step 3 complete");
        callback();
    }, 1000);
}

console.log("Starting...");


// Example of callback hell (nested callbacks)
step1(() => {
    step2(() => {
        step3(() => {
            console.log("All steps complete");
        });
    });
});
