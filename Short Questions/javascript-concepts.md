# JavaScript Homework - Short Questions

## Question 3: let vs var - Variable Hoisting

### Key Differences

**var**: Function-scoped, gets hoisted and initialized with `undefined`
**let**: Block-scoped, gets hoisted but not initialized (temporal dead zone)

### Code Examples

```javascript
// Variable Hoisting with var
console.log(myVar); // undefined (not an error)
var myVar = 5;

// This is what actually happens behind the scenes:
var myVar;
console.log(myVar); // undefined
myVar = 5;

// Variable Hoisting with let
console.log(myLet); // ReferenceError: Cannot access before initialization
let myLet = 10;

// Scope differences
function scopeExample() {
    if (true) {
        var varVariable = 'I am var';
        let letVariable = 'I am let';
    }
    
    console.log(varVariable); // 'I am var' - accessible
    console.log(letVariable); // ReferenceError - not accessible
}
```

## Question 4: Closure

A closure is when a function "remembers" variables from its outer scope even after the outer function has finished executing.

```javascript
function outerFunction(x) {
    // This is the outer function's scope
    
    function innerFunction(y) {
        // This inner function has access to 'x'
        return x + y;
    }
    
    return innerFunction;
}

const addFive = outerFunction(5);
console.log(addFive(3)); // 8

// Practical example - counter
function createCounter() {
    let count = 0;
    
    return {
        increment: () => ++count,
        decrement: () => --count,
        getCount: () => count
    };
}

const counter = createCounter();
console.log(counter.increment()); // 1
console.log(counter.increment()); // 2
console.log(counter.getCount()); // 2
```

## Question 5: Callback Hell

Callback hell happens when you have multiple nested callbacks, making code hard to read and maintain.

```javascript
// Callback Hell Example
getData(function(a) {
    getMoreData(a, function(b) {
        getEvenMoreData(b, function(c) {
            getFinalData(c, function(d) {
                // This is getting messy!
                console.log('Final result:', d);
            });
        });
    });
});

// Simulated async functions that cause callback hell
function fetchUser(userId, callback) {
    setTimeout(() => {
        callback({ id: userId, name: 'John' });
    }, 1000);
}

function fetchPosts(userId, callback) {
    setTimeout(() => {
        callback(['Post 1', 'Post 2']);
    }, 1000);
}

function fetchComments(postId, callback) {
    setTimeout(() => {
        callback(['Comment 1', 'Comment 2']);
    }, 1000);
}

// This creates callback hell
fetchUser(123, function(user) {
    fetchPosts(user.id, function(posts) {
        fetchComments(posts[0], function(comments) {
            console.log('User:', user.name);
            console.log('Posts:', posts);
            console.log('Comments:', comments);
        });
    });
});
```

## Question 6: Promise, Async, Await

### Promise
A Promise represents a value that may be available now, later, or never.

```javascript
// Creating a Promise
const myPromise = new Promise((resolve, reject) => {
    const success = Math.random() > 0.5;
    
    setTimeout(() => {
        if (success) {
            resolve('Operation successful!');
        } else {
            reject('Operation failed!');
        }
    }, 1000);
});

// Using Promise
myPromise
    .then(result => console.log(result))
    .catch(error => console.log(error));

// Chaining Promises (solves callback hell)
function fetchUserPromise(userId) {
    return new Promise(resolve => {
        setTimeout(() => resolve({ id: userId, name: 'John' }), 1000);
    });
}

function fetchPostsPromise(userId) {
    return new Promise(resolve => {
        setTimeout(() => resolve(['Post 1', 'Post 2']), 1000);
    });
}

fetchUserPromise(123)
    .then(user => fetchPostsPromise(user.id))
    .then(posts => console.log('Posts:', posts))
    .catch(error => console.log('Error:', error));
```

### Async/Await
Makes asynchronous code look and behave more like synchronous code.

```javascript
// Using async/await
async function getUserData() {
    try {
        const user = await fetchUserPromise(123);
        const posts = await fetchPostsPromise(user.id);
        
        console.log('User:', user.name);
        console.log('Posts:', posts);
        
        return { user, posts };
    } catch (error) {
        console.log('Error:', error);
    }
}

// Call async function
getUserData();

// Parallel execution with async/await
async function getMultipleData() {
    try {
        const [user1, user2, user3] = await Promise.all([
            fetchUserPromise(1),
            fetchUserPromise(2),
            fetchUserPromise(3)
        ]);
        
        console.log('All users:', [user1, user2, user3]);
    } catch (error) {
        console.log('Error:', error);
    }
}
```

## Question 9: JavaScript Asynchronous Non-blocking Feature

JavaScript uses an event-driven, single-threaded model with an event loop to handle asynchronous operations without blocking the main thread.

### Event Loop, Macrotasks, and Microtasks

```javascript
// Event Loop Example
console.log('1 - Start');

setTimeout(() => console.log('2 - Timeout (Macrotask)'), 0);

Promise.resolve().then(() => console.log('3 - Promise (Microtask)'));

console.log('4 - End');

// Output order: 1, 4, 3, 2
// Microtasks (Promises) have higher priority than Macrotasks (setTimeout)

// Detailed example showing execution order
console.log('=== Event Loop Demo ===');

// Synchronous code
console.log('1 - Synchronous');

// Macrotask (Timer)
setTimeout(() => {
    console.log('5 - setTimeout (Macrotask)');
}, 0);

// Microtask (Promise)
Promise.resolve()
    .then(() => console.log('3 - Promise 1 (Microtask)'))
    .then(() => console.log('4 - Promise 2 (Microtask)'));

// More synchronous code
console.log('2 - More synchronous');

// Another macrotask
setImmediate(() => console.log('6 - setImmediate (Macrotask)'));

// Expected output:
// 1 - Synchronous
// 2 - More synchronous
// 3 - Promise 1 (Microtask)
// 4 - Promise 2 (Microtask)
// 5 - setTimeout (Macrotask)
// 6 - setImmediate (Macrotask)
```

### How it works:
1. **Call Stack**: Executes synchronous code
2. **Web APIs**: Handle async operations (timers, HTTP requests, etc.)
3. **Callback Queue (Macrotask Queue)**: Stores callbacks from timers, events
4. **Microtask Queue**: Stores Promise callbacks, has higher priority
5. **Event Loop**: Moves tasks from queues to call stack when it's empty

The event loop always processes all microtasks before moving to the next macrotask, ensuring Promises resolve before timers execute.