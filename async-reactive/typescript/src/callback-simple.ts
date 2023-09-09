/**
 * This is an example of a higher-order function and a callback function in JavaScript.
 * In functional programming, a higher-order function is a function that takes another
 * function (callback) as an argument or returns it as a result. Callback functions are
 * functions that are passed as arguments to other functions and are executed at a later time.
 *
 * @param {() => void} callback - The callback function that will be executed by the higher-order function.
 * @returns {void}
 */
function higherOrderFunction(callback: () => void): void {
    console.log("Inside Higher Order Function");
    callback(); // Call the callback function
}

/**
 * This is an example of a callback function.
 * Callback functions are functions that can be passed as arguments to other functions.
 * In this case, the 'myCallback' function is passed to 'higherOrderFunction' and
 * executed when 'higherOrderFunction' is called.
 *
 * @returns {void}
 */
function myCallback(): void {
    console.log("Inside callback");
}

// Calling the higher-order function with 'myCallback' as the callback function.
higherOrderFunction(myCallback);
