function fetchUserData(userId: number): Promise<{ name: string; age: number }> {
    return new Promise((resolve, reject) => {
        setTimeout(() => {
            if (userId === 1) {
                resolve({ name: "Alice", age: 30 });
            } else if (userId === 2) {
                resolve({ name: "Bob", age: 25 });
            } else {
                reject("User not found");
            }
        }, 1000);
    });
}

async function getUserData(userId: number) {
    try {
        const userData = await fetchUserData(userId);
        console.log(`User: ${userData.name}, Age: ${userData.age}`);
    } catch (error) {
        console.error("Error:", error);
    }
}

console.log("Starting...");

// Using async/await to fetch and display user data
getUserData(1);
getUserData(2);
getUserData(3);

console.log("Fetching user data. Waiting for completion...");
