function fetchData(url: string): Promise<string> {
    return new Promise((resolve, reject) => {
        setTimeout(() => {
            console.log(`Fetching data from ${url}`);
            resolve(`Data from ${url}`);
        }, Math.random() * 2000); // Simulate random delays for fetching data
    });
}

console.log("Starting...");

const urls = [
    "https://api.example.com/data1",
    "https://api.example.com/data2",
    "https://api.example.com/data3",
];

// Using Promise.all() to fetch data from multiple URLs in parallel
const allPromises = urls.map((url) => fetchData(url));

Promise.all(allPromises)
    .then((results) => {
        console.log("All data fetched:", results);
    })
    .catch((error) => {
        console.error("An error occurred:", error);
    });

// Using Promise.race() to fetch data from multiple URLs and see which one completes first
const racePromises = urls.map((url) => fetchData(url));

Promise.race(racePromises)
    .then((result) => {
        console.log("First data fetched:", result);
    })
    .catch((error) => {
        console.error("An error occurred in the race:", error);
    });
